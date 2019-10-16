
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware
{
    public DcMotor  left   = null;
    public DcMotor right = null;


    private HardwareMap hwMap           =  null;
    //private ElapsedTime period  = new ElapsedTime();

    public Hardware(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        left  = hwMap.get(DcMotor.class, "left_drive");
        left.setDirection(DcMotor.Direction.REVERSE);
        left.setPower(0);
        right  = hwMap.get(DcMotor.class, "right_drive");
        right.setDirection(DcMotor.Direction.FORWARD);
        right.setPower(0);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


 }

