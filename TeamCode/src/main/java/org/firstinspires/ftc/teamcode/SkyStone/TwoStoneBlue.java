package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Two Stone Blue")
//@Disabled
public class TwoStoneBlue extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);
        boolean endPosition = true;
        boolean platformDivert = true;

        while (!opModeIsActive()){
            if(gamepad1.a){
                endPosition = false;
            }
            else if(gamepad1.b){
                endPosition = true;
            }
            if(gamepad1.x){
                platformDivert = true;
            } else if(gamepad1.y){
                platformDivert = false;
            }
            telemetry.addData("setup","left wheel on line");
            telemetry.addData("Platform Divert",platformDivert);
            telemetry.addData("End Position", endPosition? "bridge" : "wall");
            telemetry.update();

        }
        waitForStart();

        //driveToStack();
        encoderDrive(3,3,.8);
        driveToStack2();
        intake();
        turn(85,COUNTER_CLOCKWISE);
        encoderDrive(5, 5,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(80,COUNTER_CLOCKWISE);
        }
        turn(8,CLOCKWISE);
        encoderDrive(-6.6,-6.6,1);
        turn(90,CLOCKWISE);
        intake2();
        turn(90,COUNTER_CLOCKWISE);
        encoderDrive(6.8,6.8,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(92,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        if(endPosition){ //bridge
            encoderStrafe(1,RIGHT);
        } else{
            turn(8,CLOCKWISE);
            encoderStrafe(4,LEFT);
        }
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
    /*
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

     */
    private void driveToStack2(){
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

