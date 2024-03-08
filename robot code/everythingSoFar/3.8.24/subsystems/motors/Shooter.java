// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//for neo
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  //ids for shooter motors
  private static final int topShooterID = 7;
  private static final int bottomShooterID = 8;

  //make motors available
  CANSparkMax m_topShooter;
  CANSparkMax m_bottomShooter;

  public Shooter() {
    //initiate spinny motors - ignore motors
    m_topShooter = new CANSparkMax(topShooterID, MotorType.kBrushless);
    m_bottomShooter = new CANSparkMax(bottomShooterID, MotorType.kBrushless);

    //follow spinny motors
    m_bottomShooter.follow(m_topShooter);
  }

  //spinny motors go forward
  public void forwardShooters() {
    //motor speed go brrrrrrrrr - motor spins forward at 100% speed
    m_topShooter.set(1.0);
  }

  public void spit() {
    //half speed
    m_topShooter.set(0.30);
  }

  public void reverseShooters() {
    //motors spin backward at 25% speed
    m_topShooter.set(-0.25);
  }

  public void stopShooters() {
    //motors stop
    m_topShooter.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
