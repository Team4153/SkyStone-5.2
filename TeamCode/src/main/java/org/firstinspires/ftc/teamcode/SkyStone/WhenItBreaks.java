
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="When it Breaks")
//@Disabled
public class WhenItBreaks extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        String[] choices = {"distance","time","gayFix","colorMove","eachMotor","rainbow","sampleTest"};
        int run = 0;

        while(!opModeIsActive()){
            if(gamepad1.a) {
                run++;
                while (gamepad1.a){
                    run = (int)Math.pow(run,2);
                    run = (int)Math.sqrt(run);
                }
            } else if(gamepad1.b){
                run--;
                while (gamepad1.b){
                    run = (int)Math.pow(run,2);
                    run = (int)Math.sqrt(run);
                }
            }
            if(run <0) run=0;
            else if(run>=choices.length) run = choices.length-1;

            telemetry.addData("--Test--",choices[run]);
            telemetry.update();
        }

        waitForStart();


        switch (run){
            case 0: distanceCalibration();
            case 1: timeCalibration();
            case 2: gayFix();
            case 3: colorMove();
            case 4: eachMotor();
            case 5: rainbow();
            case 6: sampleTest();
        }

    }
    private void distanceCalibration(){
        COUNTS_PER_INCH -=COUNTS_PER_INCH*.4;
        int distance = 2;
        int x = 0;
        while(opModeIsActive()) {
            x++;
            encoderDrive(distance);
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
    }  //makes sure we can go exact distances

    private void timeCalibration(){
        double lfA=0, lbA=0, rfA=0, rbA=0;
        int runs = 10;
        int x = 0;

        while(x!=runs && !gamepad1.a) {
            x++;
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
        lfA /=x;
        lbA /=x;
        rfA /=x;
        rbA /=x;

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
        sleep(500000000);
    } //adjusts uneven motor qualities/powers

    private void gayFix(){
        gay = 500;
        while(!gamepad1.a){
            gayDrive(2,2);
            sleep(100);
            gayDrive(-2,-2);
            telemetry.addData("gay",gay);
            telemetry.update();
            sleep(500);
            gay+=100;
        }
    } //is gay


    private void colorMove(){
        setP(.5,.5,.5,.5);
        while (!(colorSensor.red() > 5 || colorSensor.blue() > 5 || colorSensor.green() > 5)){
            idle();
        }
        setP(0,0,0,0);
    }

    private void eachMotor(){
        while(!gamepad1.a){
            encoderDrive(2,2,.5,true);
        }
    }

    private void rainbow(){
        int runs = 100;
        int count = 0;
        while(!gamepad1.a && count < runs) {
            count++;
            encoderStrafe(10, LEFT);
            encoderStrafe(10, RIGHT);
        }
    }

    private void sampleTest(){
        for(int i=1; i<=2;i++){
            if(!isYellow()){
                telemetry.addData("yellow",i);
                break;
            } else if(i==2){
                telemetry.addData("yellow",3);
            }
            else{
                encoderStrafe(2,RIGHT);
            }
        }
        telemetry.update();
        sleep(500000000);
    }

    private boolean isYellow(){
        int[] yellowMin = {82,49,28};
        //int[] yellowMax = {316,193,100};

        int red, green, blue;

        red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();

        return(red > yellowMin[0] && green > yellowMin[1] && blue > yellowMin[2]);
    }
}

