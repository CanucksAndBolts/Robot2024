// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.testAutosCommands;

import frc.robot.subsystems.motors.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class testAutoCommand extends Command {
  /** Creates a new testAutoCommand. */

  //make subsystem
  private final Drivetrain m_drivetrain;

  //make a timer
  private Timer testingTimer = new Timer();

  public testAutoCommand(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    testingTimer.reset();
    testingTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double elapsedTime = testingTimer.get();

    if (elapsedTime < 3.0) {
      m_drivetrain.goBrrrrr();
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
    return testingTimer.get() >= 3.0;
  }
}
