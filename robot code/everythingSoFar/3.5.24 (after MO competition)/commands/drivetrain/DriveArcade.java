// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class DriveArcade extends Command {
  /** Creates a new DriveArcade. */

  private boolean slow;

  public DriveArcade() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putString("FACE: ", "shooter");
    System.out.println("The shooter is front.");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    slow = SmartDashboard.getBoolean("slow: ", false);
    double speedMultiplier;

    if (slow == true) {
      speedMultiplier = 0.1;
    } else {
      speedMultiplier = 1;
    }

    //speed stuff
    double moveSpeed = RobotContainer.m_driverController.getRawAxis(1) * speedMultiplier;
    double rotateSpeed = RobotContainer.m_driverController.getRawAxis(4) * speedMultiplier;

    RobotContainer.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stop motors
    RobotContainer.m_drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
