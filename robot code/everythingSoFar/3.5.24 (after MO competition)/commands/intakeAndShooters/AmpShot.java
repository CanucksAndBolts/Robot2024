// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeAndShooters;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class AmpShot extends Command {
  /** Creates a new AmpShot. */
  
  //make a timer
  Timer shootingTimer = new Timer();
  
  public AmpShot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_intake);
    addRequirements(RobotContainer.m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootingTimer.reset();
    shootingTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double elapsedTime = shootingTimer.get();

    if (elapsedTime > 1.0) {
      RobotContainer.m_shooter.stopShooters();
      RobotContainer.m_intake.stopIntake();
    } else if (elapsedTime > 0.25) {
      RobotContainer.m_intake.startIntake();
    } else {
      RobotContainer.m_shooter.amp();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_shooter.stopShooters();
    RobotContainer.m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shootingTimer.get() >= 1.0;
  }
}
