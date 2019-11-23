
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
        int dist = 0;
        while(!opModeIsActive()){

           if(gamepad1.a){
               delay++;
               while(gamepad1.a){
                   telemetry.addData("delay",delay);
                   telemetry.update();
               }
           } else if(gamepad1.b){
               delay--;
               while (gamepad1.b){
                   telemetry.addData("delay",delay);
                   telemetry.update();
               }
           } else if(gamepad1.x){
               dist++;
               while (gamepad1.x){
                   telemetry.addData("dist",dist);
                   telemetry.update();
               }
           } else if(gamepad1.y){
               dist--;
               while (gamepad1.y){
                   telemetry.addData("dist",dist);
                   telemetry.update();
               }
           }
        }

        waitForStart();

        sleep(delay*1000);
        setP(1,1,1,1);
        sleep(700);                             //TODO: add application of 'dist'
        setP(0,0,0,0);
        }
    }

