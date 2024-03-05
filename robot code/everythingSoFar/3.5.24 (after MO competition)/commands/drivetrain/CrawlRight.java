// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class CrawlRight extends Command {
  /** Creates a new CrawlRight. */
  public CrawlRight() {
    // Use addRequirements() here to declare subsystem dependencies.
     addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String face = SmartDashboard.getString("FACE: ", "intake");
    if (face.equals("intake")) {
    RobotContainer.m_drivetrain.crawlSpin(-1, 1);
    } else {
      RobotContainer.m_drivetrain.crawlSpin(1, -1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.justStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
