/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.commands.Stickdrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.NeutralMode;


/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax frw = new CANSparkMax(RobotMap.frw,MotorType.kBrushless);
  CANSparkMax flw = new CANSparkMax(RobotMap.flw,MotorType.kBrushless);
  CANSparkMax brw = new CANSparkMax(RobotMap.brw,MotorType.kBrushless);
  CANSparkMax blw = new CANSparkMax(RobotMap.blw,MotorType.kBrushless);

  //DifferentialDrive m_drive;
  
  private static final double spinWheelWeight = .45;
  


  
  public Drivetrain()
  {
    frw.setIdleMode(IdleMode.kBrake);
    flw.setIdleMode(IdleMode.kBrake);
    brw.setIdleMode(IdleMode.kBrake);
    blw.setIdleMode(IdleMode.kBrake);
    //DifferentialDrive m_drive = new DifferentialDrive(flw, frw);

  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new Stickdrive(Robot.m_oi.getStickX(),Robot.m_oi.getStickAngle()));
    //.out.println("you're initializing the default command for stickdrive");
  }

  public void stickdrive(double power)
  {
    //change inputs to stick vals 
    double speed=Robot.m_oi.getJoystick().getY();
    double angle =Robot.m_oi.getStickAngle();


    /*
    frw.setNeutralMode(NeutralMode.Brake);
    flw.setNeutralMode(NeutralMode.Brake);
    brw.setNeutralMode(NeutralMode.Brake);
    blw.setNeutralMode(NeutralMode.Brake);
    */
    
    double rs=-(angle+speed)*power;
    double ls=(speed+angle)*power;


    double WWSG = -angle*spinWheelWeight; //Wheel Speed When SPinning in Place. They will be spinning at .45 percentn speed.
    
    if(angle>0.1){
      ls=(-(ls+1.0))/2.0;
      rs=0.0;
    }

    if(angle<-0.1)
    {
      rs=(rs+1.0)/2.0;
      ls=0.0;
    }

    if(Math.abs(speed)<.2){
      if(angle>0.0){
        flw.set(WWSG);
        blw.set(WWSG);
        frw.set(WWSG);
        brw.set(WWSG);
        return;
      }
  
      
      if(angle<0.0)
      {
        frw.set(WWSG);
        brw.set(WWSG);
        flw.set(WWSG);
        blw.set(WWSG);
        return;
      }
    }
    
    //System.out.println("you're calling stickdrive()! congratulations.");
    //System.out.println("speed/angle: "+speed+" , "+angle);
    System.out.println("s: "+speed);
    System.out.println("r: "+rs);
    System.out.println("l: "+ls);
    frw.set(rs);
    flw.set(ls);
    brw.set(rs);
    blw.set(ls);
    //System.out.println("motor speeds are being set to "+(angle-speed)+" and "+(speed+angle));

  }

  

}
