
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Building Zone")
//@Disabled
public class BuildingZoneBlue extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //addDelay();

        waitForStart();
        encoderDrive(-4, -4, .7);
        lPlatform.setPosition(0.9);
        rPlatform.setPosition(0.75);
        encoderDrive(3.8,3.8,0.7);
        lPlatform.setPosition(0.2);
        rPlatform.setPosition(1);
        encoderStrafe(4,LEFT);



    }
    }