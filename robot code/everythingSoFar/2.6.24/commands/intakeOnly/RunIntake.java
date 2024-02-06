// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeOnly;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class RunIntake extends Command {
  /** Creates a new runIntake. */

  //check switch
  private boolean isLimitSwitchClosed = RobotContainer.m_intake.limitSwitch.get();

  public RunIntake() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { //intake goes in - disable if limit switch is closed
    while (isLimitSwitchClosed == false) { //while limit switch is open
      RobotContainer.m_intake.startIntake();
      //check again
      isLimitSwitchClosed = RobotContainer.m_intake.limitSwitch.get();
    }
    //then stop it
    RobotContainer.m_intake.stopIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { //intake off
    RobotContainer.m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
