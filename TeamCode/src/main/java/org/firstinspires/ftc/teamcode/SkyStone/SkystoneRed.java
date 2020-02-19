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

        driveToStack();
        int skyStones = 0;
        for(int stonez = 0; stonez < 6; stonez++){
            if(skyStones>=2){
                break;
            }
            if(isYellow()&& skyStones<=2){
                encoderStrafe(STONE_LENGTH,LEFT);
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
            sleep(100);
            telemetry.addData("red",colorSensor.red());
            telemetry.addData("green",colorSensor.green());
            telemetry.addData("blue",colorSensor.blue());
            telemetry.update();
            int yellowMin[] = {82,49,28};
            int yellowMax[] = {316,193,100};
            if(colorSensor.red() < 316 && colorSensor.red() > 82 && colorSensor.green() >49 && colorSensor.green() < 193 && colorSensor.blue() >28 && colorSensor.blue() <100){
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

        void driveToStack(){
            setP(.3);
            while (!(colorSensor.red() > 82 || colorSensor.green() > 49 || colorSensor.blue() > 28)){
                idle();
                telemetry.addData("red",colorSensor.red());
                telemetry.addData("green",colorSensor.green());
                telemetry.addData("blue",colorSensor.blue());
                telemetry.update();
            }
            setP(0);
        }

    }

