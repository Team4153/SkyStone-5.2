
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
        double[] choices = {0,0,0};
        String[] names = {"forward","tiny back","drag back"};
        int selected = 0;
        while (!opModeIsActive()){
            if(gamepad1.a){
                selected++;
                while(gamepad1.a){
                    telemetry.addData("selectd",names[selected]);
                    telemetry.update();
                }
            } else if(gamepad1.b){
                selected--;
                while (gamepad1.b){
                    telemetry.addData("selected",names[selected]);
                    telemetry.update();
                }
            } else if(gamepad1.x){
                choices[selected]++;
                while (gamepad1.x){
                    telemetry.addData("chosen",choices[selected]);
                    telemetry.update();
                }
            } else if(gamepad1.y){
                choices[selected]--;
                while (gamepad1.y){
                    telemetry.addData("chosen",choices[selected]);
                    telemetry.update();
                }
            }
        }
        waitForStart();

        encoderDrive(-3.4-choices[0],-3.4-choices[0]);
        //encoderDrive(.3+choices[1]/10.0,.3+choices[selected]/10.0,.3);
        dropGrab();
        encoderDrive(3+choices[2],3+choices[2], .3);
        liftGrab();
        encoderStrafe(/*7*/10,LEFT);
        /*
        encoderDrive(3.5,3.5);
        //grabber down
        encoderDrive(-2,-2);
        encoderStrafe(3,RIGHT);
        //grabber up
        encoderDrive(-2,-2);
        //*/
        }
    }

/*
* __________
*|          |
*|   O  o   |
*|     >    |
*|  (  -  ) |
*|__________|
*
* */