
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Platform")
//@Disabled
public class Platform extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        /**
        while(!opModeIsActive()){
            if(gamepad1.a){
                ADJUSTMENT += .05;
                sleep(400);
            } else if(gamepad1.b){
                ADJUSTMENT -= 0.05;
                sleep(400);
            }
            telemetry.addData("Adjustment",ADJUSTMENT);
            telemetry.update();
        }//*/

        waitForStart();

        //**

        encoderDrive(6.8,6.8);
        turn(90+45,CLOCKWISE);
        encoderDrive(2,2);
        turn(30,CLOCKWISE);
        encoderStrafe(2.5,LEFT);
        encoderDrive(2.5,2.5);//push platform
        encoderDrive(-.2,-.2);
        encoderStrafe(4,RIGHT);
        encoderDrive(1.28-.3,1.28-.3);
        encoderStrafe(10.2,RIGHT);//cross side
        encoderDrive(-.6,-.6);
        dropArm();
        encoderDrive(1,1);
        encoderStrafe(8.2,LEFT);
        liftArm();
        encoderStrafe(3,RIGHT);
        //*/
        //encoderDrive(5,3);

    }
    }

