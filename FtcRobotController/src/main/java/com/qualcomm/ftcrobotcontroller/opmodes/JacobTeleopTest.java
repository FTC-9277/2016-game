package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class JacobTeleopTest extends OpMode
{

    DcMotor armDrive;

    // DcMotor.
    public void init()
    {
        armDrive = hardwareMap.dcMotor.get("armMotor");


    }
    public void loop()
    {
        armDrive.setPower(gamepad1.left_stick_y);


    }
}
