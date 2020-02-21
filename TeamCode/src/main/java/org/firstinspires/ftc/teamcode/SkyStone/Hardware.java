package org.firstinspires.ftc.teamcode.SkyStone;

//import android.graphics.Color;

//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Hardware extends LinearOpMode
{
    DcMotor  lf   = null;
    DcMotor  lb   = null;
    DcMotor  rf   = null;
    DcMotor  rb   = null;
    Servo lPlatform = null;
    Servo rPlatform = null;
    DcMotor lIntake = null;
    DcMotor rIntake = null;
    ColorSensor colorSensor = null;

    static final double FEET_TO_BRIDGE = 4;
    static final double STONE_LENGTH = 3;
    private static final double lfA = 1;//0.8979;
    private static final double lbA = 1;//0.92;
    private static final double rfA = .943;//0.91;
    private static final double rbA = .943;//.905

    private static final double     FEET                    = 14.75;    //adjusted
    //private static final double     COUNTS_PER_MOTOR_REV    = 280*3 ;
    //private static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    //private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    /*public static final*/ double     COUNTS_PER_INCH         = 62.4164083;//(COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    private static final double     DRIVE_SPEED             = 0.7;
    private static final double           ADJUSTMENT              = 0;//0.15;
    //static final double     TURN_SPEED              = 0.5;
    private static final double     ROBOT_DIAMETER_FEET     = 16.0/FEET;
    private static final double     ROBOT_CIRCUMFRANCE      = (Math.PI * ROBOT_DIAMETER_FEET);

    static final boolean     COUNTER_CLOCKWISE    = false;
    static final boolean     CLOCKWISE   = true;
    static final boolean     RIGHT   = false;
    static final boolean     LEFT    = true;

    int gay = 1000;



   // private Servo grabber = null;
   // double armPos;
    //private ElapsedTime period  = new ElapsedTime();

    public Hardware(){

    }

    public void init(HardwareMap ahwMap) {

        lf  = ahwMap.get(DcMotor.class, "lf");
        lf.setDirection(DcMotor.Direction.REVERSE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setPower(0);

        lb  = ahwMap.get(DcMotor.class, "lb");
        lb.setDirection(DcMotor.Direction.REVERSE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setPower(0);

        rf  = ahwMap.get(DcMotor.class, "rf");
        //rf.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setPower(0);

        rb  = ahwMap.get(DcMotor.class, "rb");
        //rb.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setPower(0);

        lPlatform = hardwareMap.get(Servo.class, "lPlatform");

        rPlatform = hardwareMap.get(Servo.class, "rPlatform");

        lIntake  = ahwMap.get(DcMotor.class, "lIntake");
        lIntake.setPower(0);

        rIntake  = ahwMap.get(DcMotor.class, "rIntake");
        rIntake.setPower(0);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        /*
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */

    }

    void addDelay(){
        double delay = 0;
        while (!opModeIsActive()){
            if(gamepad1.a){
                delay +=1;
                while (gamepad1.a){
                    telemetry.addData("delay",delay);
                    telemetry.update();
                    sleep(10);
                }
            }
            else if(gamepad1.b){
                delay-=1;
                while (gamepad1.b){
                    telemetry.addData("delay",delay);
                    telemetry.update();
                    sleep(10);
                }
            }
        }
        sleep((long)(100*delay));
    }

    /*
    void say(String title, String message){
        telemetry.addData(title, message);
        telemetry.update();
    } public void say(String title, double value){
        telemetry.addData(title, value);
        telemetry.update();
    }
     */

    void setP(double lfPower, double lbPower, double rfPower, double rbPower){
        lf.setPower(lfPower * lfA);
        lb.setPower(lbPower * lbA);
        rf.setPower(rfPower * rfA);
        rb.setPower(rbPower * rbA);
    }
    void setP(double power){
        setP(power,power,power,power);
    }

    private void gayPower(double lfPower, double lbPower, double rfPower, double rbPower){
        lf.setPower(lfPower * lfA);
        lb.setPower(lbPower * lbA);
        rf.setPower(rfPower * rfA);
        rb.setPower(rbPower * rbA);
    }

    void encoderDrive(double lFeet, double rFeet) {

        int lTarget;
        int rTarget;

        double rSpeed, lSpeed;

        lTarget = ((int)(lFeet * COUNTS_PER_INCH * FEET));//lf.getCurrentPosition() +
        rTarget = ((int)(rFeet * COUNTS_PER_INCH * FEET));//rf.getCurrentPosition() +

        double ratio;   //power change
        if(Math.abs(lTarget)<Math.abs(rTarget)) {
            ratio = lTarget / rTarget;
            rSpeed = DRIVE_SPEED;
            lSpeed = DRIVE_SPEED * ratio;
        } else if(Math.abs(rTarget)<Math.abs(lTarget)){
            ratio = rTarget / lTarget;
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED * ratio;
        } else {
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED;
        }

        lSpeed *= (lFeet<0? -1 : 1);
        rSpeed *= (rFeet<0? -1 : 1);

            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            lf.setTargetPosition(lTarget);
            lb.setTargetPosition(lTarget);
            rf.setTargetPosition(rTarget);
            rb.setTargetPosition(rTarget);

            lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            setP(lSpeed, lSpeed, rSpeed, rSpeed);


            while (opModeIsActive() && (
                    (lf.isBusy() && rf.isBusy() && lb.isBusy() && rb.isBusy()))) {
                idle();
            }
            setP(0, 0, 0, 0);

            lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void encoderDrive(double feet){
        encoderDrive(feet,feet);
    }

    void encoderDrive(double lFeet, double rFeet, double power) {

        int lTarget;
        int rTarget;

        double rSpeed, lSpeed;

        lTarget = ((int)(lFeet * COUNTS_PER_INCH * FEET));//lf.getCurrentPosition() +
        rTarget = ((int)(rFeet * COUNTS_PER_INCH * FEET));//rf.getCurrentPosition() +

        double ratio;   //power change
        if(Math.abs(lTarget)<Math.abs(rTarget)) {
            ratio = lTarget / rTarget;
            rSpeed = power;//-ADJUSTMENT;
            lSpeed = power * ratio;
        } else if(Math.abs(rTarget)<Math.abs(lTarget)){
            ratio = rTarget / lTarget;
            lSpeed = power;
            rSpeed = power * ratio;// - ADJUSTMENT;
        } else {
            lSpeed = power;
            rSpeed = power;// - ADJUSTMENT;
        }

        lSpeed *= (lFeet<0? -1 : 1);
        rSpeed *= (rFeet<0? -1 : 1);

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setTargetPosition(lTarget);
        lb.setTargetPosition(lTarget);
        rf.setTargetPosition(rTarget);
        rb.setTargetPosition(rTarget);

        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setP(lSpeed, lSpeed, rSpeed, rSpeed);


        while (opModeIsActive() && (
                (lf.isBusy() && rf.isBusy()))) {
            idle();
        }
        setP(0, 0, 0, 0);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void encoderDrive(double lFeet, double rFeet, double power, boolean verbose){

        int lTarget;
        int rTarget;

        double rSpeed, lSpeed;

        lTarget = -((int)(lFeet * COUNTS_PER_INCH * FEET));//lf.getCurrentPosition() +
        rTarget = ((int)(rFeet * COUNTS_PER_INCH * FEET));//rf.getCurrentPosition() +

        double ratio;   //power change
        if(Math.abs(lTarget)<Math.abs(rTarget)) {
            ratio = lTarget / rTarget;
            rSpeed = power;//-ADJUSTMENT;
            lSpeed = power * ratio;
        } else if(Math.abs(rTarget)<Math.abs(lTarget)){
            ratio = rTarget / lTarget;
            lSpeed = power;
            rSpeed = power * ratio;// - ADJUSTMENT;
        } else {
            lSpeed = power;
            rSpeed = power;// - ADJUSTMENT;
        }

        //lSpeed *= (lFeet<0? -1 : 1);
        //rSpeed *= (rFeet<0? -1 : 1);

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setTargetPosition(-lTarget);
        lb.setTargetPosition(-lTarget);
        rf.setTargetPosition(rTarget);
        rb.setTargetPosition(rTarget);

        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setP(lSpeed, lSpeed, rSpeed, rSpeed);


        while (opModeIsActive() && (
                (lf.isBusy() && rf.isBusy()))) {
            if(verbose) {
                telemetry.addData("left target", lTarget);
                telemetry.addData("right target", rTarget);
                telemetry.addData("left front", lf.getCurrentPosition());
                telemetry.addData("left back", lb.getCurrentPosition());
                telemetry.addData("right front", rf.getCurrentPosition());
                telemetry.addData("right bakc", rb.getCurrentPosition());
                telemetry.update();
            }
            idle();
        }
        setP(0, 0, 0, 0);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void gayDrive(double lFeet, double rFeet) {

        int lTarget;
        int rTarget;

        double rSpeed, lSpeed;

        lTarget = ((int)(lFeet * COUNTS_PER_INCH * FEET));//lf.getCurrentPosition() +
        rTarget = ((int)(rFeet * COUNTS_PER_INCH * FEET));//rf.getCurrentPosition() +

        double ratio;   //power change
        if(Math.abs(lTarget)<Math.abs(rTarget)) {
            ratio = lTarget / rTarget;
            rSpeed = DRIVE_SPEED-ADJUSTMENT;
            lSpeed = DRIVE_SPEED * ratio;
        } else if(Math.abs(rTarget)<Math.abs(lTarget)){
            ratio = rTarget / lTarget;
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED * ratio - ADJUSTMENT;
        } else {
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED - ADJUSTMENT;
        }

        lSpeed *= (lFeet<0? -1 : 1);
        rSpeed *= (rFeet<0? -1 : 1);

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setTargetPosition(lTarget);
        lb.setTargetPosition(lTarget);
        rf.setTargetPosition(rTarget);
        rb.setTargetPosition(rTarget);

        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        gayPower(lSpeed, lSpeed, rSpeed, rSpeed);


        while (opModeIsActive() && (
                (lf.isBusy() || rf.isBusy()))) {
            idle();
        }

        gayPower(rSpeed,rSpeed,rSpeed,rSpeed);
        sleep(gay);
        gayPower(0, 0, 0, 0);


        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void encoderStrafe(double feet, boolean direction) {

        double speed;
        int target;

        int rbTarget, rfTarget, lbTarget, lfTarget;

        speed = DRIVE_SPEED;

        target = ((int)((feet/2) * COUNTS_PER_INCH * FEET));//lf.getCurrentPosition() +

        if(direction){  //right
            lfTarget = -target;
            lbTarget = target;
            rfTarget = target;
            rbTarget = -target;
        } else {        //left
            lfTarget = target;
            lbTarget = -target;
            rfTarget = -target;
            rbTarget = target;
        }

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setTargetPosition(lfTarget);
        lb.setTargetPosition(lbTarget);
        rf.setTargetPosition(rfTarget);
        rb.setTargetPosition(rbTarget);

        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setP(speed, speed, speed, speed);


        while (opModeIsActive() && (
                (lf.isBusy() || rf.isBusy()))) {
            idle();
        }
        setP(0, 0, 0, 0);


        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void turn(int degrees, boolean direction){

        double ratio = degrees / 360.0;
        double target = ratio * ROBOT_CIRCUMFRANCE;
        telemetry.addData("ratio",ratio);
        telemetry.addData("target",target);

        if(direction){  // clockwise
            encoderDrive(target * 2, -target * 2); //adjusted
        } else {
            encoderDrive(-target * 2, target * 2); //adjusted
        }
        //sleep(500);
    }

    /*
    void reverseDrive(double lFeet, double rFeet){
        encoderDrive(-rFeet, -lFeet);
    }
     */

    void oneMotor(DcMotor motor, int target, double power){

        target *= (int)(COUNTS_PER_INCH * 12);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setTargetPosition(target);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(power);

        while (opModeIsActive() && (
                motor.isBusy()) ) {
            telemetry.addData("ticks",motor.getCurrentPosition());
            telemetry.update();
            idle();
        }
        motor.setPower(0);


        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    void intake (){
        lIntake.setPower(-0.7);
        rIntake.setPower(-0.7);
        //encoderDrive(1.3,1.3);
        encoderDrive(1.5,1.5);
        //lIntake.setPower(0.7);
        //rIntake.setPower(0.7);
        lIntake.setPower(0);
        rIntake.setPower(0);
        encoderDrive(-1.6,-1.6);
    }
    void intake2 (){
        lIntake.setPower(-0.7);
        rIntake.setPower(-0.7);
        //encoderDrive(1.3,1.3);
        encoderDrive(2.3,2.3);
        //lIntake.setPower(0.7);
        //rIntake.setPower(0.7);
        lIntake.setPower(0);
        rIntake.setPower(0);
        encoderDrive(-2.4,-2.4);
    }
    void intake3(){
        lIntake.setPower(-0.7);
        rIntake.setPower(-0.7);
        //encoderDrive(1.3,1.3);
        encoderDrive(2.6,2.6);
        //lIntake.setPower(0.7);
        //rIntake.setPower(0.7);
        lIntake.setPower(0);
        rIntake.setPower(0);
        encoderDrive(-2.6,-2.6);
    }

    void outtake (){
        lIntake.setPower(0.4);
        rIntake.setPower(0.4);
        sleep(800);
        lIntake.setPower(0);
        rIntake.setPower(0);
    }

 }

