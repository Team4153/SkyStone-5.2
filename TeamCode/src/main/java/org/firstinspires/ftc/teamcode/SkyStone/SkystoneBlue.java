package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="SkystoneBlue")
@Disabled

public class SkystoneBlue extends Hardware {

    @Override
    public void runOpMode() {
        init(hardwareMap);
        waitForStart();

        encoderDrive(4,4);

        int skyStones = 0;
        for(int stonez = 0; stonez < 6; stonez++){
            if(skyStones>=2){
                break;
            }
            if(isYellow()){
                encoderStrafe(1,RIGHT);
            } else {
                skyStones++;
                intake();
                toBridgeBlue(stonez);
                outtake();
                fromBridgeBlue(stonez);
                intake();
                toBridgeBlue(stonez + 3);
                outtake();
                encoderStrafe(1,RIGHT);
            }

        }

            /*  /\
            min	    max
            2,0,0; 44,30,4
            9,5,0; 42,28,3
            11,7,0; 50,34,3

             *average*
             min	max
            7,4,0; 46,31,4
            */

}
    private boolean isYellow(){
        return(colorSensor.red() < 46 && colorSensor.red() > 7 && colorSensor.green() >4 && colorSensor.green() < 31 && colorSensor.blue() >0 && colorSensor.blue() <4);
    }
    private void fromBridgeBlue(int stonez){
        encoderStrafe(STONE_LENGTH * (stonez + 3) + FEET_TO_BRIDGE, RIGHT);
    }
    private void toBridgeBlue(int stonez){
        encoderStrafe(STONE_LENGTH * stonez + FEET_TO_BRIDGE, LEFT);
    }

}