// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//for neo
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  //ids for motors - shooter is front
  private static final int frontLeftDeviceID = 3;
  private static final int backLeftDeviceID = 4;
  private static final int frontRightDeviceID = 5;
  private static final int backRightDeviceID = 6;

  //differential drive
  DifferentialDrive differentialDrive = null;

  //make motors
  CANSparkMax m_frontLeft;
  CANSparkMax m_backLeft;
  CANSparkMax m_frontRight;
  CANSparkMax m_backRight;

  public Drivetrain() {
    //initialize motors
    m_frontLeft = new CANSparkMax(frontLeftDeviceID, MotorType.kBrushless);
    m_backLeft = new CANSparkMax(backLeftDeviceID, MotorType.kBrushless);
    m_frontRight = new CANSparkMax(frontRightDeviceID, MotorType.kBrushless);
    m_backRight = new CANSparkMax(backRightDeviceID, MotorType.kBrushless);

    //motors go this way because i said so
    m_frontLeft.setInverted(true);
    m_frontRight.setInverted(false);

    //follow motors
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);

    //differential drive
    differentialDrive = new DifferentialDrive(m_frontLeft, m_frontRight);
  }

  //arcade drive
  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  //tank drive
  // public void tankDrive(double moveSpeed, double rotateSpeed) {
  //   differentialDrive.tankDrive(moveSpeed, rotateSpeed);
  // }




    //autonomous stuff
    public void goBrrrrr(){
      m_frontLeft.set(0.1);
      m_frontRight.set(0.1);
    }

    public void justStop() {
      m_frontLeft.set(0.0);
      m_frontRight.set(0.0);
    }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
