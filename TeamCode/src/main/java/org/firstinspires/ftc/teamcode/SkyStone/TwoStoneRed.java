package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Two Stone Red")
//@Disabled
public class TwoStoneRed extends Hardware {


    @Override
    public void runOpMode() {


        init(hardwareMap);
        waitForStart();

        //driveToStack();
        driveToStack2();
        //sleep(500);
        sleep(100);
        intake();
        //sleep(500);
        sleep(100);
        turn(105,CLOCKWISE);
        //sleep(500);
        sleep(100);
        encoderDrive(5, 5);
        sleep(100);
        outtake();
        sleep(100);
        encoderDrive(-6.6,-6.6);
        sleep(100);
        turn(90,COUNTER_CLOCKWISE);
        sleep(100);
        intake2();
        sleep(100);
        turn(90,CLOCKWISE);
        sleep(100);
        encoderDrive(6,6);
        sleep(100);
        outtake();
        sleep(100);
        encoderDrive(-2,-2);
        /*encoderDrive(10,10);
        sleep(500);
        outtake();
        sleep(500);
        encoderDrive(-10,-10);
        sleep(500);
        encoderDrive(-3,-3);
        sleep(500);
        turn(90,COUNTER_CLOCKWISE);
        sleep(500);
        intake();
        sleep(500);
        turn(90,CLOCKWISE);
        sleep(500);
        encoderDrive(13,13);
        sleep(500);
        outtake();*/

    }
        void driveToStack(){
            setP(.3);
            while (!(colorSensor.red() > 82 || colorSensor.green() > 49 || colorSensor.blue() > 28)){
                idle();
                telemetry.addData("red",colorSensor.red());
                telemetry.addData("green",colorSensor.green());
                telemetry.addData("blue",colorSensor.blue());
                telemetry.update();
            }
            setP(0);
        }
    void driveToStack2(){
        setP(.5);
        while (!(colorSensor.red() > 82 || colorSensor.green() > 49 || colorSensor.blue() > 28)){
            idle();
            telemetry.addData("red",colorSensor.red());
            telemetry.addData("green",colorSensor.green());
            telemetry.addData("blue",colorSensor.blue());
            telemetry.update();
        }
        setP(0);
    }
    }

