package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by robotics on 10/26/2015.
 */

public class P_Loop_Test
{
    DcMotor motor;
    Telemetry telemetry;
    double target;
    double p = 0;

    public void init(DcMotor _motor, double _target, double _p)
    {
        motor = _motor;
        target = _target;
        p = _p;
    }
    public void loop()
    {
        p = target - motor.getCurrentPosition();
        //motor.setPower(p);
        telemetry.addData("target", target);
        telemetry.addData("motor position", motor.getCurrentPosition());
        telemetry.addData("p", p);
    }
}
