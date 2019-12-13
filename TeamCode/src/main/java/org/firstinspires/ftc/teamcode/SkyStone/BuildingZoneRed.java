
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Building Zone")
//@Disabled
public class BuildingZoneRed extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();
        //start with front on the wall
        encoderDrive(-4, -4, .7);
        lPlatform.setPosition(0.9);
        rPlatform.setPosition(0.75);
        encoderDrive(3.8,3.8,0.7);
        lPlatform.setPosition(0.2);
        rPlatform.setPosition(1);
        encoderStrafe(4,RIGHT);

        }
    }

/*
         encoderDrive(-3.4,-3.4);
        //encoderDrive(.3+choices[1]/10.0,.3+choices[selected]/10.0,.3);
        dropGrab();
        encoderDrive(3,3, .3);
        liftGrab();
        encoderStrafe(10,RIGHT);

        encoderDrive(3.5,3.5);
        //grabber down
        encoderDrive(-2,-2);
        encoderStrafe(3,RIGHT);
        //grabber up
        encoderDrive(-2,-2);

* */