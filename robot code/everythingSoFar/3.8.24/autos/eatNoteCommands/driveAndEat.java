// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.eatNoteCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.Drivetrain;
import frc.robot.subsystems.motors.Intake;

public class driveAndEat extends Command {
  /** Creates a new driveOutStartCommand. */

  //make subsystems
  private final Drivetrain m_drivetrain;
  private final Intake m_intake;

  //make new timer
  private Timer drivingTimer = new Timer();

  public driveAndEat(Drivetrain drivetrain, Intake intake) {
    m_drivetrain = drivetrain;
    m_intake = intake;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    addRequirements(intake);
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

    m_intake.startIntake();
    if (elapsedTime < 2.0) {
      m_drivetrain.goBrrrrr();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.justStop();
    m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
