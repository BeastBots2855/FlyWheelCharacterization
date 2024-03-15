// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final CANSparkMax m_ShooterMotor;
  private final RelativeEncoder m_relativeEncoder;
  private boolean isCharacterizing = false;
  private double characterizationVolts = 0;
  
  public Shooter() {
    m_ShooterMotor = new CANSparkMax(ShooterConstants.ShooterMotorCANID, MotorType.kBrushless);
    m_relativeEncoder = m_ShooterMotor.getEncoder();
  }

  public void setMotorOutput(double output){
    m_ShooterMotor.set(output);
  }

  public void runCharacterizationVolts(double volts){
    isCharacterizing = true;
    characterizationVolts = volts;
  }

  public void setDriveVoltage(double voltage)
  {
    m_ShooterMotor.setVoltage(voltage);
  }

  public void runCharacterization(double volts){ 
    //reminder, this is different in AdvantageKit
    setDriveVoltage(volts);
  }

  public double getCharacterizationVelocity(){
    return m_relativeEncoder.getVelocity() * 2 * Math.PI; // rads per sec 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(isCharacterizing){
      runCharacterization(characterizationVolts);
    }
  }
}
