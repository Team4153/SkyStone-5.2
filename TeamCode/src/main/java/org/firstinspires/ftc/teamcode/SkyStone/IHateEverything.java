package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="I Hate Everything")
//@Disabled
public class IHateEverything extends Hardware {


    @Override
    public void runOpMode() {

        init(hardwareMap);

        boolean endPosition = true;
        boolean platformDivert = false;

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
        /*
        waitForStart();
        encoderDrive(3,3,.8);
        driveToStack2();
        intake();
        turn(105,CLOCKWISE);
        encoderDrive(4.5, 4.5,1);
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        encoderDrive(-6.6,-6.6,1);
        turn(90,COUNTER_CLOCKWISE);
        intake2();
        turn(90,CLOCKWISE);
        encoderDrive(6,6,1);
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        if(endPosition){ //bridge
            encoderStrafe(1.2,LEFT);
        } else{
            encoderStrafe(3,RIGHT);
        }

        */
        waitForStart();
        //encoderStrafe(3,RIGHT);
        encoderDrive(3,3,.8);
        driveToStack2();
        //encoderStrafe(3,LEFT);
        int stoneNum = skyTest();
        switch (stoneNum){
            case 1: stone1(platformDivert, endPosition);
            case 2: stone2(platformDivert, endPosition);
            case 3: stone3(platformDivert, endPosition);
        }
        /*
        if(stoneNum == 1 ){
            intake();
            turn(115,CLOCKWISE);
            encoderDrive(5.5,5.5,1);
            if(platformDivert){
                turn(90,COUNTER_CLOCKWISE);
            }
            outtake();
            if(platformDivert){
                turn(90,CLOCKWISE);
            }
            encoderDrive(-7.8,-7.8,1);
            turn(120,COUNTER_CLOCKWISE);
            intake2();
            turn(120,CLOCKWISE);
            encoderDrive(7.8,7.8,1);
            if(platformDivert){
                turn(90,COUNTER_CLOCKWISE);
            }
            outtake();
            if(platformDivert){
                turn(90,CLOCKWISE);
            }
            encoderDrive(-2,-2,1);
            if(endPosition){ //bridge
                encoderStrafe(1.2,LEFT);
            } else{
                encoderStrafe(3,RIGHT);
            }
        }else if(stoneNum == 2){//////////////////////////////////////////////////////////////////////////////////
            //encoderStrafe(1,RIGHT);
            encoderStrafe(0.2,LEFT);
            intake();
            turn(115,CLOCKWISE);
            encoderDrive(4.7,4.7,1);
            if(platformDivert){
                turn(90,COUNTER_CLOCKWISE);
            }
            outtake();
            if(platformDivert){
                turn(90,CLOCKWISE);
            }
            encoderDrive(-7.1,-7.1,1);
            turn(110,COUNTER_CLOCKWISE);
            intake2();
            turn(100,CLOCKWISE);
            encoderDrive(8.1,8.1,1);
            if(platformDivert){
                turn(90,COUNTER_CLOCKWISE);
            }
            outtake();
            if(platformDivert){
                turn(90,CLOCKWISE);
            }
            encoderDrive(-2,-2,1);
            if(endPosition){ //bridge
                encoderStrafe(2.5,LEFT);
            } else{
                encoderStrafe(3,RIGHT);
            }

        }
        //*/

    }

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
    private boolean isYellow(){
        int[] yellowMin = {82,49,28};
        //int[] yellowMax = {316,193,100};

        int red, green, blue;

        red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();

        return(red > yellowMin[0] && green > yellowMin[1] && blue > yellowMin[2]);
    }
    private int skyTest(){
        for(int i=1; i<=2;i++){
            if(!isYellow()){
                return i;
            }
            else{
                encoderStrafe(2,RIGHT);
            }
        }
        return 3;
    }

    private void stone1(boolean platformDivert, boolean endPosition){
        encoderStrafe(0.4,LEFT);
        intake();
        turn(90, COUNTER_CLOCKWISE);
        encoderDrive(4.3,4.3,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        endPark(endPosition);
    }
    private void stone2(boolean platformDivert, boolean endPosition){
        encoderStrafe(0.3,LEFT);
        intake();
        turn(90, COUNTER_CLOCKWISE);
        encoderDrive(4.3,4.3,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        endPark(endPosition);
    }
    private void stone3(boolean platformDivert, boolean endPosition){
        turn(10,CLOCKWISE);
        intake();
        turn(90, COUNTER_CLOCKWISE);
        encoderDrive(4.3,4.3,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        endPark(endPosition);
    }
    /*private void stone1(boolean platformDivert, boolean endPosition){
        //turn(5,CLOCKWISE);
        encoderStrafe(0.4,LEFT);
        intake();
        turn(95,COUNTER_CLOCKWISE);
        encoderDrive(6.3,6.3,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        turn(20,CLOCKWISE);
        encoderDrive(-7.7,-7.7,1);
        //encoderDrive(-.1);
        turn(110,CLOCKWISE);
        //encoderStrafe(.3,RIGHT);
        encoderDrive(1,1,1);
        intake2();
        turn(120,COUNTER_CLOCKWISE);
        encoderDrive(7.8,7.8,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        endPark(endPosition);
    }
    private void stone2(boolean platformDivert, boolean endPosition){
        //encoderStrafe(1,RIGHT);
        //encoderStrafe(0.2,LEFT);
        intake();
        turn(100,COUNTER_CLOCKWISE);
        encoderDrive(4.7,4.7,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-7.1,-7.1,1);
        turn(110,COUNTER_CLOCKWISE);
        intake2();
        turn(100,COUNTER_CLOCKWISE);
        encoderDrive(8.1,8.1,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        endPark(endPosition);
    }
    private void stone3(boolean platformDivert, boolean endPosition){
        //encoderStrafe(1,RIGHT);
        encoderStrafe(0.2,LEFT);
        intake();
        turn(115,COUNTER_CLOCKWISE);
        encoderDrive(4.7,4.7,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-7.1,-7.1,1);
        turn(110,COUNTER_CLOCKWISE);
        intake2();
        turn(100,COUNTER_CLOCKWISE);
        encoderDrive(8.1,8.1,1);
        if(platformDivert){
            turn(90,CLOCKWISE);
        }
        outtake();
        if(platformDivert){
            turn(90,COUNTER_CLOCKWISE);
        }
        encoderDrive(-2,-2,1);
        endPark(endPosition);
    }*/
    private void endPark(boolean endPosition){
        if(endPosition){ //bridge
            encoderStrafe(2.5,RIGHT);
        } else{
            encoderStrafe(3,LEFT);
        }
    }
}

