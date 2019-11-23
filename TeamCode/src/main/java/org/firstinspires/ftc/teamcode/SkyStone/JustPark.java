
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
        int dist = 7;
        boolean selection = true;
        String[] selectionName = {"move","arm drop"};

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
            else if(gamepad1.dpad_up){
                selection = false;
                while (gamepad1.dpad_up){
                    telemetry.addData("selection",selectionName[0]);
                    telemetry.update();
                }
           } else if(gamepad1.dpad_down){
                selection = true;
                while (gamepad1.dpad_down){
                    telemetry.addData("selection",selectionName[1]);
                    telemetry.update();
                }
           }
        }

        waitForStart();
        //if(selection){
            //dropGrab();
        //}
        //else{
            sleep(delay * 1000);
            setP(1, 1, 1, 1);
            sleep(100 * dist);
            setP(0, 0, 0, 0);
        //}
        }
    }

