
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Just Park")
//@Disabled
public class JustPark extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        int delay = 0;
        while(!opModeIsActive()){

            if(gamepad1.a){
                delay ++;
                sleep(400);
            } else if(gamepad1.b){
                delay--;
                sleep(400);
            }
            telemetry.addData("Delay",delay);
            telemetry.update();
        }

        waitForStart();

        sleep(delay*1000);
        setP(1,1,1,1);
        sleep(700);
        setP(0,0,0,0);
        }
    }

