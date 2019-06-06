/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX leftTalon, rightTalon;
  private VictorSPX rightVictor1, rightVictor2, leftVictor1, leftVictor2;

  public DriveTrain(){
    this(RoboMisc.standTalonSRXSetup(RobotMap.Ports.leftTalon, 
        RobotMap.Ports.leftVictor1,
        RobotMap.Ports.leftVictor2, 
        false),
      RoboMisc.standTalonSRXSetup(RobotMap.Ports.rightTalon, 
        RobotMap.Ports.rightVictor1,
        RobotMap.Ports.rightVictor2, 
        false)
  }

  public setVolts(double left, double right){
    leftTalon.set(ControlMode.PercentOutput, left);
    rightTalon.set(ControlMode.PercentOutput, right);
  }

  public double leftEncoderTicks() {
    leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    return leftTalon.getSelectedSensorPosition(0);
  }

  public double rightEncoderTicks() {
    rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    return rightTalon.getSelectedSensorPosition(0);
  }

  public double leftEncoderVelocity() {
    leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    return leftTalon.getSelectedSensorVelocity(0);
  }

  /**
   * Gets the right talon's velocity output
   * 
   * @return Current tick velocity of the right talon's encoder
   */
  public double rightEncoderVelocity() {
    rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    return rightTalon.getSelectedSensorVelocity(0);
  }

  /**
   * Resets the encoders for both talons to 0
   */
  public void resetEncoders() {
    leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
    leftTalon.setSelectedSensorPosition(0, 0, 10);
    rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
    rightTalon.setSelectedSensorPosition(0, 0, 10);
  }

  public void setPIDValues() {
    // Make these reference a passed in table so we can write tests.
    double p = table.getEntry("P").getDouble(1);
    double i = table.getEntry("I").getDouble(0);
    double d = table.getEntry("D").getDouble(0);
    setPIDValues(p, i, d);
  }

  public void setPIDValues(double p, double i, double d) {
    // Calling the correct config_kx for each parameter might make this work better...;-)
    leftTalon.config_kP(0, p, 0);
    rightTalon.config_kP(0, p, 0);
    leftTalon.config_kI(0, i, 0);
    rightTalon.config_kI(0, i, 0);
    leftTalon.config_kD(0, d, 0);
    rightTalon.config_kD(0, d, 0);
  }

  @Override
  public void initDefaultCommand() {
    etDefaultCommand(new ArcadeDrive());
  }
}