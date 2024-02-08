// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeAndShooters;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ShootForward extends Command {
  /** Creates a new ShootForward. */

  //toggle boolean
  private boolean isShootingOn;

  public ShootForward() {    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_shooter);
    addRequirements(RobotContainer.m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isShootingOn = RobotContainer.isShootingOn;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { //shooting toggle
    isShootingOn = !isShootingOn; //toggle to on or off
    if (isShootingOn = false) { //if shooter is off
       RobotContainer.m_shooter.forwardShooters(); //turn on
    } else {
       RobotContainer.m_shooter.stopShooters();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.isShootingOn = isShootingOn;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
