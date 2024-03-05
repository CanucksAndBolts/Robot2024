// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ampArmOnly;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class MoveAmpArm extends Command {
  /** Creates a new MoveAmpArm. */
  public MoveAmpArm() {
    // Use addRequirements() here to declare subsystem dependencies.
  //  addRequirements(RobotContainer.m_ampArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double armRotation = RobotContainer.m_operatorController.getRawAxis(1);

  //  RobotContainer.m_ampArm.MoveArm(-armRotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.m_ampArm.MoveArm(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
