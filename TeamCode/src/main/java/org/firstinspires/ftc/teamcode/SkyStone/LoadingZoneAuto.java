
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
    Hardware         robot   = new Hardware();   // Use a Pushbot's hardware

    //make new stuff

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //init stuff


        waitForStart();
       //run stuff
        robot.leftPower(1.0);
        sleep(500);
        robot.leftPower(0);

        robot.rightPower(1.0);
        sleep(500);
        robot.rightPower(0);
    }
}
