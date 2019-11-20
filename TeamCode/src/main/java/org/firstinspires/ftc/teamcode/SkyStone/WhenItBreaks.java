
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="When it Breaks")
//@Disabled
public class WhenItBreaks extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();

        double start = System.currentTimeMillis();
        oneMotor(lf,3,.5);
        double end = System.currentTimeMillis();
        telemetry.addData("lf",end-start);
        start = System.currentTimeMillis();
        oneMotor(lb,3,.5);
        end = System.currentTimeMillis();
        telemetry.addData("lb",end-start);
        start = System.currentTimeMillis();
        oneMotor(rf,3,.5);
        end = System.currentTimeMillis();
        telemetry.addData("rf",end-start);
        start = System.currentTimeMillis();
        oneMotor(rb,3,.5);
        end = System.currentTimeMillis();
        telemetry.addData("rb",end-start);

        telemetry.update();
        sleep(50000000);
    }
        public void oneMotor(DcMotor motor, int target, double power){

            target *= (int)(COUNTS_PER_INCH * 12);

            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            motor.setTargetPosition(target);

            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor.setPower(power);

            while (opModeIsActive() && (
                    motor.isBusy()) ) {
                idle();
            }
            motor.setPower(0);


            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

        }

