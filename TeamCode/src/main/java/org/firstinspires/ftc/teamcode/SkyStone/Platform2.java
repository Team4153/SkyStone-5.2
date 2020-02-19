
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Platform2")
@Disabled
public class Platform2 extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        //**
        encoderDrive(6.8,6.8);
        turn(90+45,COUNTER_CLOCKWISE);
        encoderDrive(2,2);
        turn(30,COUNTER_CLOCKWISE);
        encoderStrafe(2.5,RIGHT);
        encoderDrive(2.5,2.5);//push platform
        encoderDrive(-.2,-.2);
        encoderStrafe(4,LEFT);
        encoderDrive(1.28-.3,1.28-.3);
        encoderStrafe(10.2,LEFT);   //cross side
        encoderDrive(-.6,-.6);
        encoderDrive(1,1);
        encoderStrafe(8.2,RIGHT);
        encoderStrafe(3,LEFT);
        //*/
        }
    }

