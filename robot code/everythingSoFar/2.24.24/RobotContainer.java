// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.autos.Autos;
import frc.robot.commands.ampArmOnly.ToggleAmpClaw;
import frc.robot.commands.climberOnly.ToggleClimber;
import frc.robot.commands.drivetrain.DriveArcade;
import frc.robot.commands.drivetrain.IntakeFront;
import frc.robot.commands.intakeAndShooters.ReverseShoot;
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

  //make a compressor
  private Compressor comp;

  // Make Controllers
  public static final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  public static final CommandXboxController m_operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  //make driver switch button
  Trigger setDefaultButton = m_driverController.rightBumper(); //sets the front as the shooter
  Trigger switchFrontButton = m_driverController.leftBumper(); //sets the front as intake

  //make operator buttons
  Trigger xButton = m_operatorController.x(); // shooters only on
  Trigger yButton = m_operatorController.y(); // forward shoot
  Trigger aButton = m_operatorController.a(); // reverse shooters
  Trigger RBButton = m_operatorController.rightBumper(); //intake
  Trigger RTButton = m_operatorController.rightTrigger(); //intake go out
  Trigger LTButton = m_operatorController.leftTrigger(); //climber toggle
  Trigger LBButton = m_operatorController.leftBumper(); //source ramp toggle
  Trigger clawButton = m_operatorController.back(); //toggle amp claw (open and close claw)

  private String face;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //smart dashboard
    createSmartDashboardNumber("doNothingFor", 3.0);
    createSmartDashboardString("Auto Selector", "testingAuto");
    //robot direction
    face = Autos.FACE;

    //set front
    if (face == "intake") {
      m_drivetrain.setDefaultCommand(new IntakeFront());
    } else {
      m_drivetrain.setDefaultCommand(new DriveArcade());
    }


    //Turn On Camera
    CameraServer.startAutomaticCapture();

    //initialize compressor
    comp = new Compressor(2, PneumaticsModuleType.REVPH);

    //pressure switch
     boolean pressureSwitch = comp.getPressureSwitchValue();

    //enable pressure switch
    comp.enableDigital();

    if (pressureSwitch == false) {
      comp.disable();
    }

    // Configure the button bindings
    configureBindings();

    //set default commands on subsystems
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

    //set shooter as front (driver)
    setDefaultButton.onTrue(new DriveArcade()); //button press

    //set intake as front
    switchFrontButton.onTrue(new IntakeFront());

    // shooters only on command
    xButton.toggleOnTrue(new StartEndCommand(
    () -> {
      m_shooter.forwardShooters();
    },
    () -> {
      m_shooter.stopShooters();
    }, m_shooter
    )); //button press


    // shooting command
    yButton.onTrue(new InstantCommand(
    () -> {
      m_shooter.forwardShooters();
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      m_intake.shootIntake();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      m_shooter.stopShooters();
      m_intake.stopIntake();
    }, m_shooter, m_intake
    )); //button press

    aButton.whileTrue(new ReverseShoot()); //button hold

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
 * Initialize value on SmartDashboard for user input, but leave old value if already present.
 *
 * @param key The SmartDashboard key to associate with the value.
 * @param defValue The default value to assign if not already on dashboard.
 *
 * @return The current value that appears on the dashboard.
 */
public static double createSmartDashboardNumber(String key, double defValue) {

  // See if already on dashboard, and if so, fetch current value
  double value = SmartDashboard.getNumber(key, defValue);

  // Make sure value is on dashboard, puts back current value if already set
  // otherwise puts back default value
  SmartDashboard.putNumber(key, value);

  return value;
}
public static String createSmartDashboardString(String key, String defValue) {

  // See if already on dashboard, and if so, fetch current value
  String value = SmartDashboard.getString(key, defValue);

  // Make sure value is on dashboard, puts back current value if already set
  // otherwise puts back default value
  SmartDashboard.putString(key, value);

  return value;
}

/**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Return a command
    return Autos.selectAuto(m_shooter, m_intake, m_drivetrain);
  }
}
