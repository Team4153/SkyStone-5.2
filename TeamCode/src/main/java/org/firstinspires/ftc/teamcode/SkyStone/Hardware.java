
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Hardware extends LinearOpMode
{
    public DcMotor  lf   = null;
    public DcMotor  lb   = null;
    public DcMotor  rf   = null;
    public DcMotor  rb   = null;
    public DcMotor grabber = null;

    private static final double adjust = 0.4999999;
    private static final double lfA = 0.991  - adjust;
    private static final double lbA = 0.991 - adjust;
    private static final double rfA = 1;
    private static final double rbA = 1;

    private static final double     FEET                    = 14.75;    //adjusted
    private static final double     COUNTS_PER_MOTOR_REV    = 280*3 ;
    private static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    /*public static final*/ public double     COUNTS_PER_INCH         = 58.7207255495727;//(COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    private static final double     DRIVE_SPEED             = 0.7;
    private static final double           ADJUSTMENT              = 0;//0.15;
    //static final double     TURN_SPEED              = 0.5;
    private static final double     ROBOT_DIAMETER_FEET     = 16.0/FEET;
    private static final double     ROBOT_CIRCUMFRANCE      = (Math.PI * ROBOT_DIAMETER_FEET);

    public static final boolean     COUNTER_CLOCKWISE    = false;
    public static final boolean     CLOCKWISE   = true;
    public static final boolean     RIGHT   = false;
    public static final boolean     LEFT    = true;

    public int gay = 1000;


    private HardwareMap hwMap           =  null;
   // private Servo grabber = null;
   // double armPos;
    //private ElapsedTime period  = new ElapsedTime();

    public Hardware(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        lf  = hwMap.get(DcMotor.class, "lf");
        lf.setDirection(DcMotor.Direction.REVERSE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setPower(0);

        lb  = hwMap.get(DcMotor.class, "lb");
        lb.setDirection(DcMotor.Direction.REVERSE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setPower(0);

        rf  = hwMap.get(DcMotor.class, "rf");
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setPower(0);

        rb  = hwMap.get(DcMotor.class, "rb");
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setPower(0);

        grabber = hardwareMap.get(DcMotor.class, "grabber");
        grabber.setDirection(DcMotorSimple.Direction.REVERSE);
        grabber.setPower(0);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void addDelay(){
        double delay = 0;
        while (!opModeIsActive()){
            if(gamepad1.a){
                delay +=1;
                while (gamepad1.a){
                    telemetry.addData("delay",delay);
                    telemetry.update();
                }
            }
            else if(gamepad1.b){
                delay-=1;
                while (gamepad1.b){
                    telemetry.addData("delay",delay);
                    telemetry.update();
                }
            }
        }
        sleep((long)(100*delay));
    }

    public void setP(double lfPower, double lbPower, double rfPower, double rbPower){
        lf.setPower(lfPower);// * lfA);
        lb.setPower(lbPower);// * lbA);
        rf.setPower(rfPower);// * rfA);
        rb.setPower(rbPower);// * rbA);
    }

    public void gayP(double lfPower, double lbPower, double rfPower, double rbPower){
        lf.setPower(lfPower * lfA);
        lb.setPower(lbPower * lbA);
        rf.setPower(rfPower * rfA);
        rb.setPower(rbPower * rbA);
    }


    public void encoderDrive(double lFeet, double rFeet) {
        /**
         * This drives the robot using the encoders. Each side can be given powers.
         * Use a negative value to go backwards
         */
        int lTarget;
        int rTarget;

        double rSpeed, lSpeed;

        lTarget = ((int)(lFeet * COUNTS_PER_INCH * FEET));//lf.getCurrentPosition() +
        rTarget = ((int)(rFeet * COUNTS_PER_INCH * FEET));//rf.getCurrentPosition() +

        double ratio;   //power change
        if(Math.abs(lTarget)<Math.abs(rTarget)) {
            ratio = lTarget / rTarget;
            rSpeed = DRIVE_SPEED;//-ADJUSTMENT;
            lSpeed = DRIVE_SPEED * ratio;
        } else if(Math.abs(rTarget)<Math.abs(lTarget)){
            ratio = rTarget / lTarget;
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED * ratio;// - ADJUSTMENT;
        } else {
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED;// - ADJUSTMENT;
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
                    (lf.isBusy() || rf.isBusy()))) {
                idle();
            }
            setP(0, 0, 0, 0);

            lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive(double lFeet, double rFeet, double power) {
        /**
         * This drives the robot using the encoders. Each side can be given powers.
         * Use a negative value to go backwards
         */
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
                (lf.isBusy() || rf.isBusy()))) {
            idle();
        }
        setP(0, 0, 0, 0);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void gayDrive(double lFeet, double rFeet) {
        /**
         * This drives the robot using the encoders. Each side can be given powers.
         * Use a negative value to go backwards
         */
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

        gayP(lSpeed, lSpeed, rSpeed, rSpeed);


        while (opModeIsActive() && (
                (lf.isBusy() || rf.isBusy()))) {
            idle();
        }

        gayP(rSpeed,rSpeed,rSpeed,rSpeed);
        sleep(gay);
        gayP(0, 0, 0, 0);


        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderStrafe(double feet, boolean direction) {
        /**
         * This strafes the robot using the encoders. Each side can be given distances.
         * Direction is LEFT or RIGHT
         */
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

    public void turn(int degrees, boolean direction){
        /**
         * This turns the robot using encoders. This is not the best as of now, but it'll work.
         * Enter degrees (0-360), and a direction (CLOCKWISE / COUNTER_CLOCKWISE)
         */
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

    public void dropGrab(){
        grabber.setPower(.25);
        sleep(500);
        grabber.setPower(0);
    }

    public void liftGrab() {
        grabber.setPower(-.5);
        sleep(500);
        grabber.setPower(0);
    }

    public void reverseDrive(double lFeet, double rFeet){
        encoderDrive(-rFeet, -lFeet);
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

