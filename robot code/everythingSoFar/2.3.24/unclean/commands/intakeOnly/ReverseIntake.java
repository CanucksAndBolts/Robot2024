// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeOnly;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ReverseIntake extends Command {
  /** Creates a new reverseIntake. */  

  public ReverseIntake() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { //intake reverse 
    RobotContainer.m_intake.intakeReverse();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { //stop intake
    RobotContainer.m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
