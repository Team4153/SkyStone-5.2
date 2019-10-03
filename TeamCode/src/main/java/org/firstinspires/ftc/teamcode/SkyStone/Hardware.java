package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Hardware extends LinearOpMode
{
    /* Public OpMode members. */
    public DcMotor lf, lb, rf, rb;




    public void left(double power){
        lf.setPower(power);
        lb.setPower(power);
    }


    /* Constructor /
    public Hardware(){

    }//*/

    /* Initialize standard Hardware interfaces */
    @Override
    public void runOpMode() {
        // Save reference to Hardware map
        lf  = hardwareMap.get(DcMotor.class, "lf");
        lb = hardwareMap.get(DcMotor.class, "lb");
        rf  = hardwareMap.get(DcMotor.class, "rf");
        rb = hardwareMap.get(DcMotor.class, "rb");
    }
 }

