// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.testAutosCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class doNothing extends Command {
  /** Creates a new doNothing. */

  //make timer
  private Timer doNothingTimer = new Timer();
  private double doNothingFor = 3;

  public doNothing() {
    // Use addRequirements() here to declare subsystem dependencies.
    doNothingFor = SmartDashboard.getNumber("doNothingFor", 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    doNothingTimer.reset();
    doNothingTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("I am doing nothing. Timer: " + doNothingTimer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return doNothingTimer.get() >= doNothingFor;
  }
}
