// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.autoMinRightCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.Drivetrain;

public class turnLeftCommand extends Command {
  /** Creates a new turnRightCommand. */

  //make subsystem
  Drivetrain m_drivetrain;

  //timer to time the turn
  Timer turningTimer = new Timer();

  public turnLeftCommand(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turningTimer.reset();
    turningTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double elapsedTime = turningTimer.get();
    if (elapsedTime < 2) {
      m_drivetrain.crawlSpin(1, -1);
    } else {
      m_drivetrain.justStop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.justStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return turningTimer.get() >= 2;
  }
}
