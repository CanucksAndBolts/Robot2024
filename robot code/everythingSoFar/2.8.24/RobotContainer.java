// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.ampArmOnly.ToggleAmpClaw;
import frc.robot.commands.climberOnly.ToggleClimber;
import frc.robot.commands.intakeAndShooters.ReverseShoot;
import frc.robot.commands.intakeAndShooters.ShootForward;
import frc.robot.commands.intakeOnly.ReverseIntake;
import frc.robot.commands.intakeOnly.RunIntake;
import frc.robot.commands.intakeOnly.StopIntaking;
import frc.robot.commands.shootersOnly.StopShooting;
import frc.robot.commands.sourceRampOnly.ToggleSourceRamp;
import frc.robot.subsystems.motors.Drivetrain;
import frc.robot.subsystems.motors.Intake;
import frc.robot.subsystems.motors.Shooter;
import frc.robot.subsystems.motorsAndSolenoids.AmpArm;
import frc.robot.subsystems.solenoids.Climber;
import frc.robot.subsystems.solenoids.SourceRamp;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Shooter m_shooter = new Shooter();
  public static final Intake m_intake = new Intake();
  public static final Climber m_climber = new Climber();
  public static final SourceRamp m_sourceRamp = new SourceRamp();
  public static final AmpArm m_ampArm = new AmpArm();

  // Make Controllers
  public static final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  public static final CommandXboxController m_operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  //Make Joystick
  public static final Joystick m_operatorJoystick = new Joystick(OperatorConstants.kOperatorJoystickPort);

  //make buttons
  Trigger yButton = m_operatorController.y(); // forward shoot
  Trigger aButton = m_operatorController.a(); // reverse shooters
  Trigger RBButton = m_operatorController.rightBumper(); //intake
  Trigger RTButton = m_operatorController.rightTrigger(); //intake go out
  Trigger LTButton = m_operatorController.leftTrigger(); //climber toggle
  Trigger LBButton = m_operatorController.leftBumper(); //source ramp toggle

  //make amp arm buttons 
  Trigger clawButton = new JoystickButton(m_operatorJoystick, 1); //toggle amp claw (open and close claw)

  //toggle variables
  public static boolean isShootingOn = false;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureBindings();

    //set default commands on subsystems
    m_drivetrain.setDefaultCommand(new DriveArcade());
    m_shooter.setDefaultCommand(new StopShooting());
    m_intake.setDefaultCommand(new StopIntaking());
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //button bindings - connect button to command

    // shooters
    yButton.onTrue(new ShootForward()); //button press
    // aButton.whileTrue(new ReverseShoot()); //button hold

    //intake
    RBButton.whileTrue(new RunIntake()); //button hold
    RTButton.whileTrue(new ReverseIntake()); //button hold

    //climber
    LTButton.onTrue(new ToggleClimber()); //button press

    //source ramp
    LBButton.onTrue(new ToggleSourceRamp()); //button press)
    
    //amp 
    clawButton.onTrue(new ToggleAmpClaw()); //button press
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Return a command
    return null;
  }
}
