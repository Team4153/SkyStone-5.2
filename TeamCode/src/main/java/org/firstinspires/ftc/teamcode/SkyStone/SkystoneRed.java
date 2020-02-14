package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="SkystoneRed")
//@Disabled
public class SkystoneRed extends Hardware {


    @Override
    public void runOpMode() {


        init(hardwareMap);
        waitForStart();

        telemetry.addData("s","1");
        telemetry.update();
        encoderDrive(4,4);
        telemetry.addData("s","2");
        telemetry.update();
        int skyStones = 0;
        for(int stonez = 0; stonez < 6; stonez++){
            if(skyStones>=2){
                break;
            }
            if(isYellow()&& skyStones<=2){
                encoderStrafe(1,LEFT);
            } else if (skyStones<=1){
                skyStones++;
                intake();
                toBridge(stonez);
                outtake();
                fromBridge(stonez);

            } else {
                skyStones++;
                intake();
                toBridge(stonez + 3);
                outtake();
                encoderStrafe(1,LEFT);
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
        boolean isYellow(){
            if(colorSensor.red() < 46 && colorSensor.red() > 7 && colorSensor.green() >4 && colorSensor.green() < 31 && colorSensor.blue() >0 && colorSensor.blue() <4){
                return (true);
            } else {
                return (false);
            }
        }
        void fromBridge(int stonez){
            encoderStrafe(STONE_LENGTH * (stonez + 3) + FEET_TO_BRIDGE, LEFT);
        }
        void toBridge(int stonez){
            encoderStrafe(STONE_LENGTH * stonez + FEET_TO_BRIDGE, RIGHT);
        }

    }

