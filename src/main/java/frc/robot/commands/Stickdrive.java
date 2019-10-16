/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/




//UNUSED



package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class Stickdrive extends Command {
  private double speed;
  private double angle;
  private double power;
  public Stickdrive(double s,double a,double p) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    
    speed = s;
    angle = a;
    power = p;
    requires(Robot.d_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("you're executing the Stickdrive class.");
    Robot.d_subsystem.stickdrive(power);
    //System.out.println("speed and angle are "+ speed+", "+angle);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
