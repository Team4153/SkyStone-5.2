
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
    static final double     DRIVE_SPEED             = 0.4;
    static final double     TURN_SPEED              = 0.5;


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

    public void encoderDrive(double lfInches, double rfInches) {
        int leftTarget;
        int rightTarget;
        double rSpeed, lSpeed;
        leftTarget = (int)(lfInches * COUNTS_PER_INCH);//lf.getCurrentPosition() +
        rightTarget = (int)(rfInches * COUNTS_PER_INCH);//rf.getCurrentPosition() +
        /*
        * R & L
        * we want 0.5 to be the power of the lesser motor
        * we want the other motor to have the unadjusted power
        *
        * */
        double ratio = lfInches / rfInches;   //power change
        if(leftTarget < rightTarget){
            lSpeed = DRIVE_SPEED;
            rSpeed = lSpeed * ratio;
        } else{
            rSpeed = DRIVE_SPEED;
            lSpeed =  rSpeed * ratio;
        }


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


 }

/*
ok
ethan
i tried
but
i need
to go

You did exactly what you were supposed to do.
 You did a great job. I just added some logic
 stuff to make it run nicer.
 */