// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeAndShooters;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ReverseShoot extends Command {
  /** Creates a new ReverseShoot. */

  //for double limitSwitch count
  private final Counter limitSwitchCounter = new Counter(RobotContainer.m_intake.limitSwitch);

  public ReverseShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_shooter);
    addRequirements(RobotContainer.m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //reset the counter
    limitSwitchCounter.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { //shooters and intake reverse - until limit switch x2
    while (limitSwitchCounter.get() < 2) { //while the limit switch has not changed twice - reverse
      RobotContainer.m_shooter.reverseShooters();
      RobotContainer.m_intake.intakeReverse();
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
