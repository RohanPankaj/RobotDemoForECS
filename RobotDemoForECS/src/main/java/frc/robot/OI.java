/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //#region Controller Data

  public double getLeftYAxis() {
    return condition_gamepad_axis(0.05, -gamepad1.getRawAxis(RobotMap.Buttons.leftYAxis), -1, 1);
  }

  public double getLeftYAxis2() {
    return condition_gamepad_axis(0.05, -gamepad3.getRawAxis(RobotMap.Buttons.leftYAxis), -1, 1);
  }

  public double getRightXAxis() {
    return condition_gamepad_axis(0.05, gamepad1.getRawAxis(RobotMap.Buttons.rightXAxis), -1, 1);
  }

  public double getRightYAxis() {
    return condition_gamepad_axis(0.05, -gamepad1.getRawAxis(RobotMap.Buttons.rightYAxis), -1, 1);
  }

  public int getPOV() { return gamepad3.getPOV(); }

  /**
   * Make the gamepad axis less sensitive to changes near their null/zero point.
   * 
   * @param value raw value from the gamepad axis
   * @param dead  value for the deadband size 
   * @return
   */
  public double deadBand(double value, double dead) {
    if (Math.abs(value) < dead) {
      return 0;
    } else {
      return value;
    }
  }

  /**
   * Clamp/Limit the value to only be within two limits
   * @param min lower limit
   * @param max upper limit
   * @param val value to check
   * @return
   */

  public double clamp(double min, double max, double val) {
    if (min > val) {
      return min;
    } else if (max < val) {
      return max;
    } else {
      return val;
    }
  }

  /**
   * Combine both a joystick limit and a clamp within standard limits.
   * 
   * I really wish programmers would name methods descriptively so that I do
   * not have to waste my time figuring out what things like "bing" and "stuff" do!
   * I am guessing that this does good "stuff" to my joystick. CCB.
   * 
   * @param dead deadband limit, no output within this limit. Normally 0.05
   * @param val raw value from axis
   * @param min lower limit for axis. Normally -1
   * @param max upper limit on axis. Normally +1
   * @return    conditioned value from axis (limited -1 to +1, with a )
   */
  public double condition_gamepad_axis(double dead, double val, double min, double max) {
    return clamp(min, max, deadBand(val, dead));
  }

  //#endregion

  //#region Configuration Methods

  /**
   * Purge the contorls
   * 
   * @deprecated Not need to remap controls. Just run one of the other configurations
   */
  
}
