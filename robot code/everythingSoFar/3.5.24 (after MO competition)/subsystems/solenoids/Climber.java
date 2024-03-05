// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.solenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  //Make a solenoid
  private final DoubleSolenoid m_climberSolenoid;

  public Climber() {
    //initialize solenoid
    m_climberSolenoid = new DoubleSolenoid(2, PneumaticsModuleType.REVPH, 7, 4);

    //default solenoid state
    m_climberSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void climberToggle() { //toggles the climber solenoid
    m_climberSolenoid.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
