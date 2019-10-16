/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Peck;
import frc.robot.commands.Bite;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;


/**
 * Add your docs here.
 */
public class Cone extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Solenoid coneOperation = new Solenoid(1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Bite(false));
    
  }


  public void openingNoid(boolean pressure)
  { 
    coneOperation.set(pressure);
  }

  

}
