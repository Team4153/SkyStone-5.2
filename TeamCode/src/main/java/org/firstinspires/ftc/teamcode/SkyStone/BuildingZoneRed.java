
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


        encoderDrive(4,4);
        //grabber down
        encoderDrive(-3.5,-3.5);
        //grabber up
        encoderStrafe(7,LEFT);
        encoderDrive(3.5,3.5);
        //grabber down
        encoderDrive(-2,-2);
        encoderStrafe(3,RIGHT);
        //grabber up
        encoderDrive(-2,-2);

        }
    }

