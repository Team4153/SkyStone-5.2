
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Blue Building Zone")
@Disabled
public class BuildingZoneBlue extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        addDelay();

        waitForStart();

        encoderDrive(-3.8, -3.8, .7);
        lPlatform.setPosition(0.9);
        rPlatform.setPosition(0.75);
        sleep(1150);
        encoderDrive(3.7,3.7,0.55);
        sleep(200);
        lPlatform.setPosition(0.2);
        rPlatform.setPosition(1);
        sleep(500);
        encoderStrafe(10,LEFT);
        encoderDrive(-1,-1);
        encoderStrafe(5,RIGHT);
        encoderStrafe(5,LEFT);



    }
    }