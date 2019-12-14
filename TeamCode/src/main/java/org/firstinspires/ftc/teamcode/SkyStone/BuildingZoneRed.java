
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

        addDelay();

        waitForStart();
        //start with front on the wall

        encoderDrive(-3.8, -3.8, .7);
        lPlatform.setPosition(0.9);
        rPlatform.setPosition(0.75);
        sleep(1150);
        encoderDrive(3.7,3.7,0.7);
        sleep(200);
        lPlatform.setPosition(0.2);
        rPlatform.setPosition(1);
        sleep(500);
        encoderStrafe(10,RIGHT);
        encoderDrive(-1,-1);
        encoderStrafe(5,LEFT);
        encoderStrafe(5,RIGHT);

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