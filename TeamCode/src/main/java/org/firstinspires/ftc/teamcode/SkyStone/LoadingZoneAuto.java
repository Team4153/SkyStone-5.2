
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Loading zone auto")
//@Disabled
public class LoadingZoneAuto extends Hardware {
    //private Hardware         robot   = new Hardware();

    //make new stuff

    @Override
    public void runOpMode() {
        //init stuff

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        //run stuff

        encoderDrive(15,15);
        turn(90,LEFT);



    }
}