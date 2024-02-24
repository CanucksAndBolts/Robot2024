// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.motorsAndSolenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AmpArm extends SubsystemBase {
  /** Creates a new AmpArm. */

  //Make amp arm

  //Make a solenoid
  private final DoubleSolenoid m_ampClawSolenoid;

  public AmpArm() {
    //initialize solenoid
    m_ampClawSolenoid = new DoubleSolenoid(2, PneumaticsModuleType.REVPH, 5, 2);

    //default solenoid state
    m_ampClawSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void ampClawToggle() { //toggles amp claw
    m_ampClawSolenoid.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
