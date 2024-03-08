// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.cameraserver.CameraServer;
// LED imports
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants.OperatorConstants;
import frc.robot.autos.Autos;
import frc.robot.commands.ampArmOnly.MoveAmpArm;
import frc.robot.commands.ampArmOnly.ToggleAmpClaw;
import frc.robot.commands.climberOnly.ToggleClimber;
import frc.robot.commands.drivetrain.CrawlBack;
import frc.robot.commands.drivetrain.CrawlLeft;
import frc.robot.commands.drivetrain.CrawlRight;
import frc.robot.commands.drivetrain.CrawlUp;
import frc.robot.commands.drivetrain.DriveArcade;
import frc.robot.commands.drivetrain.IntakeFront;
import frc.robot.commands.intakeAndShooters.ReverseShoot;
import frc.robot.commands.intakeAndShooters.SpitNote;
import frc.robot.commands.intakeOnly.ReverseIntake;
import frc.robot.commands.intakeOnly.RunIntake;
import frc.robot.commands.intakeOnly.StopIntaking;
import frc.robot.commands.shootersOnly.StopShooting;
import frc.robot.commands.sourceRampOnly.ToggleSourceRamp;
import frc.robot.subsystems.leds.LEDs;
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
  // public static final LEDs m_leds = new LEDs();

  //make a compressor
  private Compressor comp;

  // Make Controllers
  public static final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  public static final CommandXboxController m_operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  //make driver buttons
  Trigger setDefaultButton = m_driverController.rightBumper(); //sets the front as the shooter
  Trigger switchFrontButton = m_driverController.leftBumper(); //sets the front as intake
  Trigger crawlUpButton = m_driverController.povUp(); //makes robot go forward slowly
  Trigger crawlBackButton = m_driverController.povDown(); //makes robot go backward slowly
  Trigger crawlLeftButton = m_driverController.povLeft(); //makes robot spin left slowly
  Trigger crawlRightButton = m_driverController.povRight(); //makes robot spin right slowly
  Trigger changeSpeedButton = m_driverController.start(); //toggles robot speed


  //make operator buttons
  Trigger xButton = m_operatorController.x(); // shooters only on
  Trigger yButton = m_operatorController.y(); // forward shoot
  Trigger aButton = m_operatorController.a(); // reverse shooters
  Trigger RBButton = m_operatorController.rightBumper(); //intake
  Trigger RTButton = m_operatorController.rightTrigger(); //intake go out
  Trigger LTButton = m_operatorController.leftTrigger(); //source ramp toggle
  Trigger LBButton = m_operatorController.leftBumper(); //climber toggle
  Trigger clawButton = m_operatorController.back(); //toggle amp claw (open and close claw)
  Trigger spitButton = m_operatorController.b();



  private String face;
  public boolean areShootersRunning;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // // Stuff for LED

    // AddressableLED light = new AddressableLED(0);
    // AddressableLEDBuffer lightBuff = new AddressableLEDBuffer(256);

    // light.setLength(lightBuff.getLength());
    
    // lightBuff = new AddressableLEDBuffer(256);
    // light.setLength(lightBuff.getLength());
    // light.setData(lightBuff);
    // light.start();

    // int pixelHue = 0;


    // light.setData(lightBuff);
    // light.start();


     // Get the alliance color
    //  Optional<Alliance> alliance = DriverStation.getAlliance();

    //  if (alliance.isPresent()) {
    //     if (alliance.get() == Alliance.Red) {
    //          // Perform actions for the Red alliance
    //          System.out.println("Red Alliance Action");
    //         for (int i = 0; i < lightBuff.getLength(); i++) {
    //           lightBuff.setRGB(i, 128, 0, 0);
    //         }
    //      } else if (alliance.get() == Alliance.Blue) {
    //          // Perform actions for the Blue alliance
    //          System.out.println("Blue Alliance Action");
    //          for (int i = 0; i < lightBuff.getLength(); i++) {
    //             lightBuff.setRGB(i, 0, 0, 255);
    //         }
    //      }}
    //  } else {
    //     // Handle the case where no alliance color is available yet
    //     System.out.println("No Alliance Color Yet Action");
    //     for (int i = 0; i < lightBuff.getLength(); i++) {
    //     lightBuff.setRGB(i, 0, 255, 0);
    //     }
    //  }
    // if (RobotContainer.m_intake.limitSwitch.get() == true) { //disables if limitSwitch starts pressed
    //   for (int i = 0; i < lightBuff.getLength(); i++) {

    //     final int hue = (pixelHue + (i * 180 / lightBuff.getLength())) % 180;

    //       lightBuff.setHSV(i, hue, 255, 128);
    //     }
    //   pixelHue += 3;
    //   pixelHue %= 180;

    //   light.setData(lightBuff);
     
    //  } 

     
      // light.close();





    //smart dashboard
    createSmartDashboardNumber("doNothingFor", 3.0);
  
    //auto chooser
    createDropDown();

    //ARE SHOOTERS RUNNING
    areShootersRunning = false;
    SmartDashboard.putBoolean("Shooters", areShootersRunning);

    //robot direction
    face = Autos.FACE;

    //set front
    if (face == "intake") {
      m_drivetrain.setDefaultCommand(new IntakeFront());
    } else {
      m_drivetrain.setDefaultCommand(new DriveArcade());
    }

    createSmartDashboardString("FACE: ", face);

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
    m_ampArm.setDefaultCommand(new MoveAmpArm());
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   * 
   */
  private void configureBindings() {
    //button bindings - connect button to command

    //set shooter as front (driver)
    setDefaultButton.onTrue(new DriveArcade()); //button press

    //set intake as front
    switchFrontButton.onTrue(new IntakeFront());

    //move forward slowly
    crawlUpButton.whileTrue(new CrawlUp()); //button hold

    //move back slowly
    crawlBackButton.whileTrue(new CrawlBack()); //button hold

    //spin left slowly
    crawlLeftButton.whileTrue(new CrawlLeft()); //button hold

    //spin right slowly
    crawlRightButton.whileTrue(new CrawlRight()); //button hold

   //toggle robot speed
    // changeSpeedButton.toggleOnTrue(new StartEndCommand(
    // () -> {
    //   isSpeedLowered = true;
    //   SmartDashboard.putBoolean("slow: ", isSpeedLowered);
    // },
    // () -> {
    //   isSpeedLowered = true;
    //   SmartDashboard.putBoolean("slow: ", isSpeedLowered);
    // }, m_drivetrain
    // )); //button press

    

    // }

    //amp shot command
    spitButton.onTrue(new SpitNote());

    // shooters only on command
    xButton.toggleOnTrue(new StartEndCommand(
    () -> {
      m_shooter.forwardShooters();
      areShootersRunning = true;
      SmartDashboard.putBoolean("Shooters", areShootersRunning);
    },
    () -> {
      m_shooter.stopShooters();
      areShootersRunning = false;
      SmartDashboard.putBoolean("Shooters", areShootersRunning);
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
    LBButton.onTrue(new ToggleClimber()); //button press

    //source ramp
    LTButton.onTrue(new ToggleSourceRamp()); //button press)
    
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

public static SendableChooser<String> autoChooser;

public static void createDropDown(){
  autoChooser = new SendableChooser<>();
  autoChooser.setDefaultOption("Minimum", "autoMin");
  autoChooser.addOption("Minimum (Right Side)", "autoMinRight");
  autoChooser.addOption("Only drive out", "justDrive");
  autoChooser.addOption("Shoot and Intake", "eatNote");
  autoChooser.addOption("Test Auto", "testingAuto");
  SmartDashboard.putData("Auto Chooser", autoChooser);
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
