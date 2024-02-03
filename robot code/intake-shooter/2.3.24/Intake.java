// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  //ids for intake parts - limit switch and motor
  private static final int intakeMotorID = 9;

  //make intake motor
  CANSparkMax m_intake;

  //make a limit switch - zero because we said so
  public final DigitalInput limitSwitch = new DigitalInput(0);

  public Intake() {
    //initiate motor
    m_intake = new CANSparkMax(intakeMotorID, MotorType.kBrushless);
  }

  public void startIntake() {
    //same as shoot but it's different because i said so - intake at 100% speed
    m_intake.set(1.0);
  }

  public void stopIntake() {
    //intake at 0% speed
    m_intake.set(0.0);
  }

  public void intakeReverse() {
    //intake at -100% speed
    m_intake.set(-1.0);
  }

  public void shootIntake(){
    //intake go brrrrrr - right now it's at 100% speed, don't know if it should be negative
    m_intake.set(1.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
