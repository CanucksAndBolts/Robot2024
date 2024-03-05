// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.autoMinCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.Drivetrain;

public class moveBack extends Command {
  /** Creates a new moveOut. */

  //make subsystem
  private final Drivetrain m_drivetrain;

  //make new timer
  private Timer drivingTimer = new Timer();

  public moveBack(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;

    // Use addRequirements() here to declare subsystem dependencies.
   addRequirements(drivetrain); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivingTimer.reset();
    drivingTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double elapsedTime = drivingTimer.get();

    if (elapsedTime < 1.0) {
      m_drivetrain.crawl(0.5);;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Stopping");
    m_drivetrain.justStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return drivingTimer.get() >= 1.0;
  }
}
