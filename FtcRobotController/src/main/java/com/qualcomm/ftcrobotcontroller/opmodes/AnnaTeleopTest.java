package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics on 10/9/2015.
 */
public class AnnaTeleopTest extends OpMode
{
    DcMotor armMotor1;
    DcMotor armMotor2;

    @Override
    public void init()
    {
        armMotor1 = hardwareMap.dcMotor.get("dcMotor1");
        armMotor2 = hardwareMap.dcMotor.get("dcMotor2");
    }

    @Override
    public void loop()
    {
        if (gamepad1.a)
        {
            armMotor1.setPower(1);
          ///  wait(100);
            armMotor2.setPower(1);
           // wait(100);
        }
    }
}
