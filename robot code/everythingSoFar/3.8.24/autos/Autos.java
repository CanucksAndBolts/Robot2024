// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

//import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.motors.Drivetrain;
import frc.robot.subsystems.motors.Intake;
import frc.robot.subsystems.motors.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autos.autoMinCommands.autoShootCommand;
import frc.robot.autos.autoMinCommands.driveOutStartCommand;
import frc.robot.autos.autoMinCommands.moveBack;
import frc.robot.autos.autoMinRightCommand.turnLeftCommand;
import frc.robot.autos.eatNoteCommands.driveAndEat;
import frc.robot.autos.testAutosCommands.doNothing;
import frc.robot.autos.testAutosCommands.testAutoCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  // public static Command exampleAuto(ExampleSubsystem subsystem) {
  //   return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  // }

  //which way is front
  public static String FACE = "intake";

  public static Command testingAuto(Drivetrain drivetrain) {
    return Commands.sequence(new testAutoCommand(drivetrain));
  }

  public static Command justDrive(Drivetrain drivetrain) {
    return new SequentialCommandGroup(
      new doNothing(),
      new driveOutStartCommand(drivetrain)
    );
  }

  public static Command autoMin(Shooter shooter, Intake intake, Drivetrain drivetrain) {
    return new SequentialCommandGroup(
      new doNothing(),
      new moveBack(drivetrain),  
      new autoShootCommand(shooter, intake),
      new driveOutStartCommand(drivetrain)
    );
  }  

  public static Command autoMinRight(Shooter shooter, Intake intake, Drivetrain drivetrain) {
    return new SequentialCommandGroup(
      new doNothing(),
      new moveBack(drivetrain),  
      new autoShootCommand(shooter, intake),
      new turnLeftCommand(drivetrain),
      new driveOutStartCommand(drivetrain)
    );
  }

  public static Command eatNote(Shooter shooter, Intake intake, Drivetrain drivetrain) {
    return new SequentialCommandGroup(
      new doNothing(),
      new moveBack(drivetrain),
      new autoShootCommand(shooter, intake),
      new driveAndEat(drivetrain, intake)
    );
  }

  
  //select an autonomous command
  public static Command selectAuto(Shooter shooter, Intake intake, Drivetrain drivetrain) {
    String autoType = RobotContainer.autoChooser.getSelected();
    System.out.println("Selected: " + RobotContainer.autoChooser.getSelected());
    if (autoType.equals("testingAuto")) {
      return testingAuto(drivetrain);
    } else if (autoType.equals("justDrive")) {
      return justDrive(drivetrain);
    } else if (autoType.equals("eatNote")) {
      return eatNote(shooter, intake, drivetrain);
    } else if (autoType.equals("autoMinRight")) {
      return autoMinRight(shooter, intake, drivetrain);
    } else {
      return autoMin(shooter, intake, drivetrain);
    }
  } 

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
      
  }
}
