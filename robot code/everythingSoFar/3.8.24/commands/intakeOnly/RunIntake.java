// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeOnly;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class RunIntake extends Command {
  
  AddressableLED light;
  AddressableLEDBuffer lightBuff;

  /** Creates a new runIntake. */

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
      RobotContainer.m_intake.startIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { //intake off
    RobotContainer.m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !RobotContainer.m_intake.limitSwitch.get(); //this defaults to true so we return opposite with ! operator
  }
}
