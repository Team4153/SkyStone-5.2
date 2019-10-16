
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Loading zone auto")
//@Disabled
public class LoadingZoneAuto extends LinearOpMode {
    private Hardware         robot   = new Hardware();   // Use a Pushbot's hardware

    //make new stuff

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //init stuff
        robot.left.setPower(0);
        robot.right.setPower(0);

        waitForStart();
       //run stuff

    }
}
