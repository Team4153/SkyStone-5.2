
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Loading zone auto")
//@Disabled
public class LoadingZoneAuto extends Hardware {
    //private Hardware         robot   = new Hardware();

    //make local stuff

    @Override
    public void runOpMode() {
        //init stuff

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        telemetry.addData("Status","Running");
        telemetry.update();
        //run stuff


        reverseDrive(2.25,2.25);
        dropGrab();
        reverseDrive(-1.25,-1.25);
        turn(90,CLOCKWISE);
        reverseDrive(4.5,4.5);
        liftGrab();
        reverseDrive(-1,-1);


    }
}