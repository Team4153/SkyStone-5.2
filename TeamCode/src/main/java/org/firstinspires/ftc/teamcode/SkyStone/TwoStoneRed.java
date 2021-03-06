package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Two Stone Red")
//@Disabled
public class TwoStoneRed extends Hardware {


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
            telemetry.addData("Platform Divert",platformDivert);
            telemetry.addData("End Position", endPosition? "bridge" : "wall");
            telemetry.update();

        }
        waitForStart();
        encoderDrive(3,3,1);
        driveToStack2();
        intake4();
        turn(130,CLOCKWISE);
        encoderDrive(4.5, 4.5,1);
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        encoderDrive(-7,-7,1);
        turn(90,COUNTER_CLOCKWISE);
        intake2();
        turn(85,CLOCKWISE);
        encoderDrive(6.1,6.1,1);
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        if(endPosition){ //bridge
            encoderStrafe(3,LEFT);
        } else{
            encoderStrafe(5,RIGHT);
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

