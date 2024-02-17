// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.solenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SourceRamp extends SubsystemBase {
  /** Creates a new SourceRamp. */

  //Make a solenoid
  private final DoubleSolenoid m_sourceRampSolenoid;

  public SourceRamp() {
    //initialize solenoid
    m_sourceRampSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);
  }
  
  public void sourceRampToggle() { //toggles the source ramp solenoid
    m_sourceRampSolenoid.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
