
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Red Building Zone")
@Disabled
public class BuildingZoneRed extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        addDelay();

        waitForStart();
        //start with front on the wall

        encoderDrive(-3.8, -3.8, .7);//forward
        lPlatform.setPosition(0.9);
        rPlatform.setPosition(0.75);//drop the platform grabbers
        sleep(1300);//wait
        encoderDrive(3.7,3.7,0.7);//go back
        sleep(200);//wait
        lPlatform.setPosition(0.2);
        rPlatform.setPosition(1);//raise grabbers
        sleep(500);//wait
        encoderStrafe(10,RIGHT);//go right
        encoderDrive(-1,-1);//go back
        encoderStrafe(5,LEFT);//go left
        encoderStrafe(5,RIGHT);//go right

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