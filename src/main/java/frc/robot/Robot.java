/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//updated 2-21-19

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Cone;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pecker;
import frc.robot.RobotMap;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  

  private String m_autoSelected;
/*private WPI_TalonSRX frwheel;
  private WPI_TalonSRX flwheel;
  private WPI_TalonSRX brwheel;
  private WPI_TalonSRX blwheel;
  */
  private  CANSparkMax lift;
  public static final Pecker p_subsystem= new Pecker();
  public static final Cone c_subsystem = new Cone();
  public static final Drivetrain d_subsystem = new Drivetrain();
  //public static Drivetrain d_subsystem = new Drivetrain(blwheel, blwheel, blwheel, blwheel);
  public static OI m_oi;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
   @Override
  public void robotInit() {
    m_oi = new OI();
    /*
    frwheel = new WPI_TalonSRX(RobotMap.frw);
    flwheel = new WPI_TalonSRX(RobotMap.flw);
    brwheel = new WPI_TalonSRX(RobotMap.brw);
    blwheel = new WPI_TalonSRX(RobotMap.blw);
    */
    lift = new CANSparkMax(RobotMap.lift,MotorType.kBrushless);
    CameraServer camera = CameraServer.getInstance();
    VideoSource front = camera.startAutomaticCapture("cam0" , 0);
    VideoSource back = camera.startAutomaticCapture("cam1" , 1);
    
    VideoSource[] cameras = {front, back};
    for(int i = 0; i<cameras.length; i++)
    {
      cameras[i].setFPS(25);
      cameras[i].setResolution(16,9);
    }
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();


  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
 /* @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
   /* if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during aut
   * onomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    double t = m_oi.getJoystick().getThrottle();
  
    if(t>=.0)
    {
      t = .9;
    }
    else
    {
      t = .65;
    }

    //drive
    d_subsystem.stickdrive(t);

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
   //  this line or comment it out.
   /* if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    
    }
    */

  }

  /** 
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //d_subsystem.stickdrive(m_oi.getStickY(),m_oi.getStickAngle());
    System.out.println("tick");
    //System.out.println("stick y and angle are "+m_oi.getStickY()+" , "+m_oi.getStickAngle());
    
    //this part *should* be implemented as a command class but it was causing problems so I'm just bypassing it

    //get joystick doubles

    double t = m_oi.getJoystick().getThrottle();
    
    if(t>=0.0)
    {
      t = .9;
    }
    else
    {
      t = .65;
    }

    //drive
    d_subsystem.stickdrive(t);
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
