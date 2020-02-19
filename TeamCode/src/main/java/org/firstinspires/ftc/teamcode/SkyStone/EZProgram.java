package org.firstinspires.ftc.teamcode.SkyStone;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.qualcomm.robotcore.util.ElapsedTime;

//import static java.lang.Thread.sleep;

@TeleOp(name="EZProgram that does stuff")
@Disabled
public class EZProgram extends OpMode
{
    /*
    DcMotor drive1;
    DcMotor drive2;
    CRServo spin;
    CRServo s1,s2,s3,s4,s5;

    boolean Classy = true;
    //*/

   // private ModernRoboticsI2cColorSensor colorSensor = null;
    private ColorSensor colorSensor = null;


    private int[] yellowRGBMax = {-1,-1,-1}; //rgb values {red, green, blue}
    private int[] yellowRGBMin = {900000000,900000000,900000000};
    private int minSum = 900000000;
    private int maxSum = -1;



    private boolean mode = true;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        //colorSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "colorSensor");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        telemetry.addData("Controlls:","rt: swap opModes, lt: init");
        telemetry.update();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        if(gamepad1.a){
            mode = !mode;
            while(gamepad1.a){
                telemetry.addData("mode:",mode? "detection test" : "color train");
                telemetry.update();
            }
        }
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

        if(mode){
            testColor();
        }
        else {
            trainColor();
        }


        /*
        if(red > yellowRGBMax[0]){
            yellowRGBMax[0] = red;
        }
        if(green > yellowRGBMax[1]){
            yellowRGBMax[1] = green;
        }
        if(blue > yellowRGBMax[2]){
            yellowRGBMax[2] = blue;
        }
        if(red < yellowRGBMin[0]){
            yellowRGBMin[0] = red;
        }
        if(green < yellowRGBMin[1]){
            yellowRGBMin[1] = green;
        }
        if(blue < yellowRGBMin[2]){
            yellowRGBMin[2] = blue;
        }
        */
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("red min", yellowRGBMin[0]);
        telemetry.addData("green min", yellowRGBMin[1]);
        telemetry.addData("blue min", yellowRGBMin[2]);

        telemetry.addData("red max", yellowRGBMax[0]);
        telemetry.addData("green max", yellowRGBMax[1]);
        telemetry.addData("blue max", yellowRGBMax[2]);
        telemetry.update();

    }


    private void trainColor(){

        int red, green, blue;
        red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();

        int sum = red + green + blue;

        if(gamepad1.a){
            telemetry.addData("red min", yellowRGBMin[0]);
            telemetry.addData("green min", yellowRGBMin[1]);
            telemetry.addData("blue min", yellowRGBMin[2]);

            telemetry.addData("red max", yellowRGBMax[0]);
            telemetry.addData("green max", yellowRGBMax[1]);
            telemetry.addData("blue max", yellowRGBMax[2]);

        }
        else {
            telemetry.addData("red", red);
            telemetry.addData("green", green);
            telemetry.addData("blue", blue);
        }

        if (gamepad1.b){
            colorSensor.enableLed(true);
        } else if(gamepad1.x){
            colorSensor.enableLed(false);
        }
        telemetry.update();

        if(sum < minSum){
            minSum = sum;
            yellowRGBMin[0] = red;
            yellowRGBMin[1] = green;
            yellowRGBMin[2] = blue;
        } else if(sum > maxSum){
            maxSum = sum;
            yellowRGBMax[0] = red;
            yellowRGBMax[1] = green;
            yellowRGBMax[2] = blue;
        }
    }

    private void testColor(){
        int[] yellowMin = {82,49,28};
        //int[] yellowMax = {316,193,100};

        //int otherMin[] = {1,0,0};
        //int otherMax[] = {4,3,2};

        int red, green, blue;


            red = colorSensor.red();
            green = colorSensor.green();
            blue = colorSensor.blue();

            String whatItIs;

            if(red > yellowMin[0] && green > yellowMin[1] && blue > yellowMin[2]){
                whatItIs = "yellow";
            } else {
                whatItIs = "black";
            }


            telemetry.addData("this is...",whatItIs);

    }
}

