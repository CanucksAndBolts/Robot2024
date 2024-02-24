// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeAndShooters;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ReverseShoot extends Command {
  /** Creates a new ReverseShoot. */

  //boolean to check if limit switch is down at start
  private boolean startedPressed;

  //limit switch change counter
  private int limitSwitchCount;
  private boolean limitSwitchTracker;
  private boolean limitSwitchPressed;

  public ReverseShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_shooter);
    addRequirements(RobotContainer.m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //reset the counter
    limitSwitchCount = 0;
    limitSwitchPressed = false;

    if (RobotContainer.m_intake.limitSwitch.get() == false) { //disables if limitSwitch starts pressed
      startedPressed = true;
    } else {
      startedPressed = false;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { //shooters and intake reverse - until limit switch pressed x2
    if (startedPressed == false) {//if limit switch starts open

      limitSwitchTracker = !RobotContainer.m_intake.limitSwitch.get(); // open limit switch is true (so i made false)
      
      if (limitSwitchTracker != limitSwitchPressed) { //if limit switch state changes
        limitSwitchPressed = limitSwitchTracker;
        limitSwitchCount++; //increase change count
        System.out.println("Limit switch changed " + limitSwitchCount + " times.");
      }

      if (limitSwitchCount >= 3) { //while the limit switch has not changed three time (on, off, on) - reverse shooters
        RobotContainer.m_shooter.stopShooters();
        RobotContainer.m_intake.stopIntake();
      } else {
        RobotContainer.m_shooter.reverseShooters();
        RobotContainer.m_intake.intakeReverse();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { //shooters and intake stop
    RobotContainer.m_shooter.stopShooters();
    RobotContainer.m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
