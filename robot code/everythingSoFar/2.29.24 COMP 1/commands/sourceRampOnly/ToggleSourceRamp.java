// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.sourceRampOnly;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ToggleSourceRamp extends Command {
  /** Creates a new ToggleSourceRamp. */

  //toggle boolean
  private boolean isSourceRampToggled = false;

  public ToggleSourceRamp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_sourceRamp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Toggling source ramp");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_sourceRamp.sourceRampToggle();
    isSourceRampToggled = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isSourceRampToggled;
  }
}
