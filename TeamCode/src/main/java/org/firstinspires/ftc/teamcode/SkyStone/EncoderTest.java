package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Encoder Test", group = "Diagnostic")
public class EncoderTest extends OpMode {
    private DcMotor lf, lb, rf, rb;

    @Override
    public void init() {
        lf = hardwareMap.get(DcMotor .class, "lf");
        lb  = hardwareMap.get(DcMotor.class, "lb");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rb = hardwareMap.get(DcMotor.class, "rb");

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void driveMotor(final DcMotor motor, final boolean condition) {
        if (condition) {
            motor.setPower(gamepad1.left_stick_y);
        } else {
            motor.setPower(0.0);
        }
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper || gamepad1.left_bumper) {
            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
        if (gamepad1.dpad_up) {
            lf.setPower(.5);
            lb.setPower(.5);
            rf.setPower(.5);
            rb.setPower(.5);
        } else if (gamepad1.dpad_down) {
            lf.setPower(-.5);
            lb.setPower(-.5);
            rf.setPower(-.5);
            rb.setPower(-.5);
        } else {
            driveMotor(lf, gamepad1.a);
            driveMotor(lb, gamepad1.b);
            driveMotor(rf, gamepad1.x);
            driveMotor(rb, gamepad1.y);
        }

        telemetry.addData("lf", lf.getCurrentPosition());
        telemetry.addData("lb", lb.getCurrentPosition());
        telemetry.addData("rf", rf.getCurrentPosition());
        telemetry.addData("rb", rb.getCurrentPosition());
    }
}
