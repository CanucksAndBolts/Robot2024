// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.leds;

import java.util.Optional;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class LEDs extends SubsystemBase {
  /** Creates a new leds. */

  AddressableLED light;
  AddressableLEDBuffer lightBuff;

  public LEDs() {

    light = new AddressableLED(0);
    lightBuff = new AddressableLEDBuffer(256);
    light.setLength(lightBuff.getLength());
    light.setData(lightBuff);
    light.start();
  }
  



  // public void rainbow() {
//     light = new AddressableLED(0);
//     lightBuff = new AddressableLEDBuffer(256);
//      light.setLength(lightBuff.getLength());
//      light.setData(lightBuff);
//      light.start();
//      int pixelHue = 0;

// for (int i = 0; i < lightBuff.getLength(); i++) {

//  final int hue = (pixelHue + (i * 180 / lightBuff.getLength())) % 180;

//  lightBuff.setHSV(i, hue, 255, 128);
// }

// pixelHue += 3;

// pixelHue %= 180;
// light.close();

//}











  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  //   // Stuff for LED

  //    // Get the alliance color
  //    Optional<Alliance> alliance = DriverStation.getAlliance();

  //    System.out.println("Our Alliance: " + alliance);

  //    if (alliance.isPresent()) {
  //       if (alliance.get() == Alliance.Red) {
  //            // Perform actions for the Red alliance
  //            System.out.println("Red Alliance Action");
  //            for (int i = 0; i < lightBuff.getLength(); i++) {
  //     lightBuff.setRGB(i, 255, 0, 0);
  //       }
  //        } else if (alliance.get() == Alliance.Blue) {
  //            // Perform actions for the Blue alliance
  //            System.out.println("Blue Alliance Action");
  //            for (int i = 0; i < lightBuff.getLength(); i++) {
  //     lightBuff.setRGB(i, 0, 0, 255);
  //       }
  //        }
  //    } else {
  //        // Handle the case where no alliance color is available yet
  //        System.out.println("No Alliance Color Yet Action");
  //        for (int i = 0; i < lightBuff.getLength(); i++) {
  //     lightBuff.setRGB(i, 0, 255, 0);
  //       }
  //    }
  //   light.setData(lightBuff);
  //   light.close();
  }
}