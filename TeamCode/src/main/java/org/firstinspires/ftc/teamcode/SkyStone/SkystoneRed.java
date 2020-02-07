package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class SkystoneRed extends Hardware {


    @Override
    public void runOpMode() {


        waitForStart();

        //do STUFF

        int skyStones = 0;
        for(int stonez = 0; stonez < 6; stonez++){
            if(skyStones>=2){
                break;
            }
            if(isYellow()){
                encoderStrafe(1,LEFT);
            } else{
                skyStones++;
                intake();
                goThere(stonez);
                outtake();
                goBack(stonez);
            }

        }

        /*
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
        void goBack(int stonez){
            double feetToBridge = 4;
            double stoneLength = 1;
            encoderStrafe(stoneLength * stonez + feetToBridge, LEFT);
        }
        void goThere(int stonez){
            double feetToBridge = 4;
            double stoneLength = 1;
            encoderStrafe(stoneLength * stonez + feetToBridge, RIGHT);
        }

    }

