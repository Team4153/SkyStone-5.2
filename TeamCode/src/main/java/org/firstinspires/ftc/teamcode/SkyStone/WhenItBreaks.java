
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
//
        double lfA=0, lbA=0, rfA=0, rbA=0;
        int runs = 10;

        for(int i=0; i<runs; i++) {
            double start = System.currentTimeMillis();
            oneMotor(lf, 3, .5);
            double end = System.currentTimeMillis();
            telemetry.addData("lf", end - start);
            lfA+=end-start;
            start = System.currentTimeMillis();
            oneMotor(lb, 3, .5);
            end = System.currentTimeMillis();
            telemetry.addData("lb", end - start);
            lbA+=end-start;
            start = System.currentTimeMillis();
            oneMotor(rf, 3, .5);
            end = System.currentTimeMillis();
            telemetry.addData("rf", end - start);
            rfA+=end-start;
            start = System.currentTimeMillis();
            oneMotor(rb, 3, .5);
            end = System.currentTimeMillis();
            telemetry.addData("rb", end - start);
            rbA+=end-start;
            telemetry.update();
            //sleep(100);
        }
        lfA /=runs;
        lbA /=runs;
        rfA /=runs;
        rbA /=runs;

        telemetry.addData("lfA",lfA);
        telemetry.addData("lbA",lbA);
        telemetry.addData("rfA",rfA);
        telemetry.addData("rbA",rbA);
        telemetry.update();
        sleep(5000);

        if(lfA>lbA && lfA>rbA && lfA>rfA){
            lbA /= lfA;
            rbA /= lfA;
            rfA /= lfA;
            lfA = 1;
        } else if(lbA>lfA && lbA>rbA && lbA>rfA){
            lfA /= lbA;
            rbA /= lbA;
            rfA /= lbA;
            lbA = 1;
        } else if(rbA>lfA && rbA>lbA && rbA>rfA){
            lbA /= rbA;
            rfA /= rbA;
            lfA /= rbA;
            rbA = 1;
        } else{
            lbA /= rfA;
            rbA /= rfA;
            lfA /= rfA;
            rfA = 1;
        }

        telemetry.addData("new","----");
        telemetry.addData("lfA",lfA);
        telemetry.addData("lbA",lbA);
        telemetry.addData("rfA",rfA);
        telemetry.addData("rbA",rbA);
        telemetry.update();
        sleep(5000);
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

