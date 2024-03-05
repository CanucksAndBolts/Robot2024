// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climberOnly;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ToggleClimber extends Command {
  /** Creates a new ToggleClimber. */

  //toggle checker
  private boolean isClimberToggled = false;

  public ToggleClimber() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Toggling climber");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_climber.climberToggle();
    isClimberToggled = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isClimberToggled;
  }
}
