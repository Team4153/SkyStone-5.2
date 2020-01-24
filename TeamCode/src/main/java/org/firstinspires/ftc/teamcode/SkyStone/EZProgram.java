package org.firstinspires.ftc.teamcode.SkyStone;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="EZProgram that does stuff")
//@Disabled
public class EZProgram extends OpMode
{
    /*
    DcMotor drive1;
    DcMotor drive2;
    CRServo spin;
    CRServo s1,s2,s3,s4,s5;

    boolean Classy = true;
    //*/

    private ColorSensor getTHATskystone = null;


    int yMax[] = {-1,-1,-1}; //rgb values {red, green, blue}
    int yMin[] = {900000000,900000000,900000000};


    int red, green, blue;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        getTHATskystone = hardwareMap.get(ColorSensor.class, "getTHATskystone");

        telemetry.addData("Controlls:","rt: swap opModes, lt: init");
        telemetry.update();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        /*
        if(gamepad1.right_trigger>0.5){ //detect the button press
            while(gamepad1.right_trigger>0.5){  //lock up so this happens once per press
                Classy = !Classy;   //toggle Classy
                telemetry.addData("running opMode 1",Classy? "tank drive" : "servo test");
                telemetry.update();
            }
        }
        else if(Classy && gamepad1.left_trigger>0.5){
            drive1 = hardwareMap.get(DcMotor.class, "left_drive");
            drive2 = hardwareMap.get(DcMotor.class, "right_drive");
            spin = hardwareMap.get(CRServo.class, "spin");


            drive1.setDirection(DcMotor.Direction.FORWARD);
            drive2.setDirection(DcMotor.Direction. REVERSE);
        }
        else if (!Classy && gamepad1.left_trigger > 0.5){
            s1 = hardwareMap.crservo.get("s1");
            s2 = hardwareMap.crservo.get("s2");
            s3 = hardwareMap.crservo.get("s3");
            s4 = hardwareMap.crservo.get("s4");
            s5 = hardwareMap.crservo.get("s5");

        }
        //*/

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */


    @Override
    public void start(){
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        /*
        while(Classy){
            double cl;
            cl   = Range.clip( 0, -1.0, 1.0) ;

            drive1.setPower(gamepad1.left_stick_y);
            drive2.setPower(gamepad1.right_stick_y);
            if (gamepad1.a){
                spin.setPower(0.8);
            } else if (gamepad1.y){
                spin.setPower(-0.8);
          } else {
                spin.setPower(0);
            }
        }
        while (!Classy){
            if(gamepad1.a){
                s1.setPower(1);
                s2.setPower(1);
                s3.setPower(1);
                s4.setPower(1);
                s5.setPower(1);
            } if(gamepad1.b) {
                s1.setPower(-1);
                s2.setPower(-1);
                s3.setPower(-1);
                s4.setPower(-1);
                s5.setPower(-1);
            }
            else {
                s1.setPower(0);
                s2.setPower(0);
                s3.setPower(0);
                s4.setPower(0);
                s5.setPower(0);
            }

        }
        //*/

        red = getTHATskystone.red();
        green = getTHATskystone.green();
        blue = getTHATskystone.blue();

        if(gamepad1.a){
            telemetry.addData("red min",yMin[0]);
            telemetry.addData("green min",yMin[1]);
            telemetry.addData("blue min",yMin[2]);

            telemetry.addData("red max",yMax[0]);
            telemetry.addData("green max", yMax[1]);
            telemetry.addData("blue max",yMax[2]);

        }
        else {
            telemetry.addData("red", red);
            telemetry.addData("green", green);
            telemetry.addData("blue", blue);
        }
        telemetry.update();
        /*
        * find the min for all rgb if it sees yellow
        * find the max for all rgb if it sees yellow
        *
        * find the min for black
        * find max for black
        * */

        if(red > yMax[0]){
            yMax[0] = red;
        }
        if(green > yMax[1]){
            yMax[1] = green;
        }
        if(blue > yMax[2]){
            yMax[2] = blue;
        }
        if(red < yMin[0]){
            yMin[0] = red;
        }
        if(green < yMin[1]){
            yMin[1] = green;
        }
        if(blue < yMin[2]){
            yMin[2] = blue;
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}


/*
*
*  5 9 7 6 8 7 7 7 6 6
*  -------------------
*  0 1 2 3 4 5 6 7 8 9
*
*
* */