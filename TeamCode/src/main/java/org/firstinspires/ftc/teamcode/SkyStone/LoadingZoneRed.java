
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Loading zone auto")
//@Disabled
public class LoadingZoneRed extends Hardware {
    //private Hardware         robot   = new Hardware();

    //make local stuff

    @Override
    public void runOpMode() {
        //init stuff

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        encoderDrive(4,4);

        lIntake.setPower(0.8);
        rIntake.setPower(0.8);
        sleep(500);
        lIntake.setPower(0);
        rIntake.setPower(0);
        encoderDrive(3.8,3.8);
        turn(90,RIGHT);
        encoderDrive(5,5);
        lIntake.setPower(-0.8);
        rIntake.setPower(-0.8);
        sleep(500);
        lIntake.setPower(0);
        rIntake.setPower(0);
        encoderDrive(-1,-1);
    }
}


/*

        reverseDrive(2.25,2.25);
        dropGrab();
        reverseDrive(-1.25,-1.25);
        turn(90,CLOCKWISE);
        reverseDrive(4.5,4.5);
        liftGrab();
        reverseDrive(-1,-1);
 */