// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ShootForward extends Command {
  /** Creates a new ShootForward. */

  //toggle boolean
  private boolean isShootingOn = RobotContainer.isShootingOn;

  public ShootForward() {    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //check which mode
    if (isShootingOn == false) { // if not shooting, shoot
      RobotContainer.m_shooter.forwardShooters();
    } else if (isShootingOn == true) {// if shooting, stop shooting
      RobotContainer.m_shooter.stopShooters();
    }
    
    //switch toggle
    isShootingOn = !isShootingOn;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //set variable for checking
    RobotContainer.isShootingOn = isShootingOn;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
