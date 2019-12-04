package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="TELE OP")
//@Disabled
public class Tele extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor lf= null;
    private DcMotor lb = null;
    private DcMotor rf = null;
    private DcMotor rb = null;
    private DcMotor grabber = null;
    private DcMotor cap = null;
    private DcMotor lIntake = null;
    private DcMotor rIntake = null;
    //private Servo

    double lfPower=0;
    double lbPower=0;
    double rfPower=0;
    double rbPower=0;



    /*
    private static final double lfA = 0.991;
    private static final double lbA = 0.991;
    private static final double rfA = 1.0;
    private static final double rbA = 0.998;
    */

    double adjust = 0.499999;

    double lfA = 0.991 - adjust;
    double lbA = 0.991 - adjust;
    double rfA = 1.0;
    double rbA = 1.0;//0.998;


    private static double STRAFE_SPEED = 0.5;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");


        lf = hardwareMap.get(DcMotor.class, "lf");
        grabber = hardwareMap.get(DcMotor.class, "grabber");
        lb  = hardwareMap.get(DcMotor.class, "lb");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rb = hardwareMap.get(DcMotor.class, "rb");
        cap = hardwareMap.get(DcMotor.class, "cap");
        lIntake = hardwareMap.get(DcMotor.class, "lIntake");
        rIntake = hardwareMap.get(DcMotor.class, "rIntake");

        lf.setDirection(DcMotor.Direction.FORWARD);
        lb.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        grabber.setDirection(DcMotorSimple.Direction.FORWARD);

        lIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        rIntake.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {


        //lfA = 0.991 - adjust;
        //lbA = 0.991 - adjust;
        //rfA = 1.0;
        //rbA = 1.0;//0.998;


//newest update
        if (gamepad1.left_bumper) {
            lfPower = STRAFE_SPEED;
            lbPower = -STRAFE_SPEED;
            rfPower = -STRAFE_SPEED;
            rbPower = STRAFE_SPEED;
        } else if (gamepad1.right_bumper) {
            lfPower = -STRAFE_SPEED;
            lbPower = STRAFE_SPEED;
            rfPower = STRAFE_SPEED;
            rbPower = -STRAFE_SPEED;
        } else if (gamepad1.right_trigger > 0.25) {
            lfPower = gamepad1.left_stick_y / 2;
            lbPower = gamepad1.left_stick_y / 2;
            rfPower = gamepad1.right_stick_y / 2;
            rbPower = gamepad1.right_stick_y / 2;
        } else if (gamepad1.left_trigger > 0.25) {
            lfPower = gamepad1.left_stick_y / 5;
            lbPower = gamepad1.left_stick_y / 5;
            rfPower = gamepad1.right_stick_y / 5;
            rbPower = gamepad1.right_stick_y / 5;
        } else {
            lfPower = gamepad1.left_stick_y;
            lbPower = gamepad1.left_stick_y;
            rfPower = gamepad1.right_stick_y;
            rbPower = gamepad1.right_stick_y;
        }

        lf.setPower(lfPower);//*lfA);
        lb.setPower(lbPower);//*lbA);
        rf.setPower(rfPower);//*rfA);
        rb.setPower(rbPower);//*rbA);

        if (gamepad1.dpad_up) {
            grabber.setPower(.45);
        } else if (gamepad1.dpad_down) {
            grabber.setPower(-.45);
        } else {
            grabber.setPower(0);
        }

        if (gamepad1.a) {
            cap.setPower(.35);
        } else if (gamepad1.b) {
            cap.setPower(-.35);
        } else {
            cap.setPower(0);
        }

        if (gamepad2.x) {
            rIntake.setPower(0.75);
            lIntake.setPower(0.75);
        } else if (gamepad2.b) {
            rIntake.setPower(-0.75);
            lIntake.setPower(-0.75);
        } else if(gamepad2.left_trigger>0) {
            rIntake.setPower(gamepad2.left_trigger);
            lIntake.setPower(gamepad2.left_trigger);
        } else if (gamepad2.right_trigger>0){
            rIntake.setPower(-gamepad2.right_trigger);
            lIntake.setPower(-gamepad2.right_trigger);
        }else{
            rIntake.setPower(0);
            lIntake.setPower(0);
        }




        /*
        if(gamepad1.a){
            adjust+=0.05;
            while (gamepad1.a){
                telemetry.addData("Adjustment",adjust);
                telemetry.update();
            }
        }
        if(gamepad1.b){
            adjust-=0.05;
            while (gamepad1.b){
                telemetry.addData("Adjustment",adjust);
                telemetry.update();
            }
        }//*/




    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
