// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.autoMinCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.Intake;
import frc.robot.subsystems.motors.Shooter;

public class autoShootCommand extends Command {
  /** Creates a new autoShoot. */

  //make subsystems
  private final Shooter m_shooter;
  private final Intake m_intake;

  //make a timer
  private Timer shootingTimer = new Timer();

  public autoShootCommand(Shooter shooter, Intake intake) {
    m_shooter = shooter;
    m_intake = intake;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(intake);
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

    if (elapsedTime < 0.25) {
      //shooter turns on
      m_shooter.forwardShooters();
    } else if (elapsedTime < 1.25) {
      //intake turns on
      m_intake.shootIntake();
    } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //everything off
    m_shooter.stopShooters();
    m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shootingTimer.get() >= 1.25;
  }
}
