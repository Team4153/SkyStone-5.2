
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

        distanceCalibration();


    }
    public void distanceCalibration(){
        COUNTS_PER_INCH -=COUNTS_PER_INCH*.4;
        int x = 0;
        while(opModeIsActive()) {
            x++;
            encoderDrive(2, 2);
            sleep(500);
            encoderDrive(-2,-2);
            sleep(1000);
            telemetry.addData("COUNTS_PER_INCH",COUNTS_PER_INCH);
            telemetry.addData("x",x);
            telemetry.update();
            COUNTS_PER_INCH *=1.1;
            if(x>10 || gamepad1.a){
                break;
            }
        }
        sleep(50000000);
    }

    public void timeCalibration(){
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

        if(lfA<lbA && lfA<rbA && lfA<rfA){
            lbA = lfA/lbA;
            rbA = lfA/rbA;
            rfA = lfA/rfA;
            lfA = 1;
        } else if(lbA<lfA && lbA<rbA && lbA<rfA){
            lfA = lbA/lfA;
            rbA = lbA/rbA;
            rfA = lbA/rfA;
            lbA = 1;
        } else if(rbA<lfA && rbA<lbA && rbA<rfA){
            lbA = rbA/lbA;
            rfA = rbA/rfA;
            lfA = rbA/lfA;
            rbA = 1;
        } else{
            lbA = rfA/lbA;
            rbA = rfA/rbA;
            lfA = rfA/lfA;
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
}

