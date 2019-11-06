
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Hardware extends LinearOpMode
{
    public DcMotor  lf   = null;
    public DcMotor  lb   = null;
    public DcMotor  rf   = null;
    public DcMotor  rb   = null;

    static final double     COUNTS_PER_MOTOR_REV    = 280 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;
    static final double     ROBOT_DIAMETER_INCHES   = 16.0;
    static final double     ROBOT_CIRCUMFRANCE      = Math.PI * ROBOT_DIAMETER_INCHES;

    public static final boolean     LEFT    = false;
    public static final boolean     RIGHT   = true;


    private HardwareMap hwMap           =  null;
   // private Servo arm = null;
   // double armPos;
    //private ElapsedTime period  = new ElapsedTime();

    public Hardware(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        lf  = hwMap.get(DcMotor.class, "lf");
        lf.setDirection(DcMotor.Direction.REVERSE);
        lf.setPower(0);

        lb  = hwMap.get(DcMotor.class, "lb");
        lb.setDirection(DcMotor.Direction.REVERSE);
        lb.setPower(0);

        rf  = hwMap.get(DcMotor.class, "rf");
        rf.setDirection(DcMotor.Direction.FORWARD);
        rf.setPower(0);

        rb  = hwMap.get(DcMotor.class, "rb");
        rb.setDirection(DcMotor.Direction.FORWARD);
        rb.setPower(0);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setP(double lfPower, double lbPower, double rfPower, double rbPower){
        lf.setPower(lfPower);
        lb.setPower(lbPower);
        rf.setPower(rfPower);
        rb.setPower(rbPower);
    }

    public void encoderDrive(double lInches, double rInches) {
        /**
         * This drives the robot using the encoders. Each side can be given powers.
         * Use a negative value to go backwards
         */
        int leftTarget;
        int rightTarget;
        leftTarget = Math.abs((int)(lInches * COUNTS_PER_INCH));//lf.getCurrentPosition() +
        rightTarget = Math.abs((int)(rInches * COUNTS_PER_INCH));//rf.getCurrentPosition() +

        double rSpeed, lSpeed;
        double ratio;   //power change
        if(leftTarget<rightTarget) {
            ratio = leftTarget / rightTarget;
            rSpeed = DRIVE_SPEED;
            lSpeed = DRIVE_SPEED * ratio;
        } else if(rightTarget<leftTarget){
            ratio = rightTarget / leftTarget;
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED * ratio;
        } else {
            lSpeed = DRIVE_SPEED;
            rSpeed = DRIVE_SPEED;
        }
            lSpeed *= (lInches<0? -1 : 1);
            rSpeed *= (rInches<0? -1 : 1);

            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            lf.setTargetPosition(leftTarget);
            lb.setTargetPosition(leftTarget);
            rf.setTargetPosition(rightTarget);
            rb.setTargetPosition(rightTarget);

            lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            lf.setPower(lSpeed);
            lb.setPower(lSpeed);
            rf.setPower(rSpeed);
            rb.setPower(lSpeed);

            while (opModeIsActive() && (
                    (lf.isBusy() || rf.isBusy()))) {
                idle();
            }
            lf.setPower(0);
            lb.setPower(0);
            rf.setPower(0);
            rb.setPower(0);

            lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turn(int degrees, boolean direction){
        /**
         * This turns the robot using encoders. This is not the best as of now, but it'll work.
         * Enter degrees (0-360), and a direction (GO_LEFT, GO_LEFT)
         */
        double ratio = degrees / 360;
        double target = ratio * ROBOT_CIRCUMFRANCE;
        if(direction){  // right/clockwise
            encoderDrive(target, -target);
        } else {
            encoderDrive(-target, target);
        }
    }

 }