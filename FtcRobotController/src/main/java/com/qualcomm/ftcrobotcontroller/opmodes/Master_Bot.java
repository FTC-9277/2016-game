package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by robotics on 10/26/2015.
 */

public class Master_Bot extends OpMode
{

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor arm;
    Telemetry telemetry;
    double speed = 0.5;

    @Override
    public void init()
    {
        leftMotor = hardwareMap.dcMotor.get("leftDrive");
        rightMotor = hardwareMap.dcMotor.get("rightDrive");
        arm = hardwareMap.dcMotor.get("arm");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop()
    {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);
        if (gamepad2.a)
        {
            if (arm.getPower() == 0)
            {
                arm.setPower(arm.getPower() * -1);
                try
                {
                    wait(1000);
                }
                catch (InterruptedException e)
                {
                    telemetry.addData(e.toString() + " - can't wait", "");
                    arm.setPower(0);
                    return;
                }
            }
            else
            {
                arm.setPower(speed);
            }
        }
        else
        {
            arm.setPower(0);
        }
    }
}
