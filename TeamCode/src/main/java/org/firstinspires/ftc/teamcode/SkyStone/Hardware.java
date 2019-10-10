
package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware
{
    public DcMotor  leftDrive   = null;


    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public Hardware(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        leftDrive.setPower(0);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
 }

