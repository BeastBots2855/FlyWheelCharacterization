package frc.robot;

import java.util.HashMap;
import java.util.List;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Shooter;
import frc.robot.FeedForwardCharacterization.FeedForwardCharacterizationData;


public class Autos {

    
    
    private SendableChooser<String> autoChooser;
    private HashMap<String, Command> m_commandMap;
   
    ShuffleboardTab autoTab = Shuffleboard.getTab("Autonomous");
    public Autos(Shooter m_Shooter){
            m_commandMap.put("ShooterCharacterization", new FeedForwardCharacterization(m_Shooter, true, new FeedForwardCharacterizationData("Shooter"),
            m_Shooter::runCharacterizationVolts , m_Shooter::getCharacterizationVelocity));
        autoTab.add(autoChooser);
    }
 

    public Command getAutoCommand() {
        String auto = autoChooser.getSelected();
        return m_commandMap.get(auto);
    }





}