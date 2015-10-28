package com.qualcomm.ftcrobotcontroller.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class PIProvider
{

    /**
     author Dominic Canora
     */
    private DcMotor motor;
    private double kP, kI, p, i, error, setPower;
    private int setDegrees, targetDelta;
    private boolean enabled, reversed;

    public PIProvider(DcMotor m, double kPset, double kIset)
    {
        motor = m;
        kI = kIset/1000;
        kP = kPset/1000;

        setZero();
        setTarget(0);

        enabled = true;
    }

    public void setTarget(int tgt)
    {
        setDegrees = tgt - targetDelta;
    }

    public void setReversed(boolean rev)
    {
        reversed = rev;
    }

    public void setZero()
    {
        targetDelta = motor.getCurrentPosition();
        setTarget(0);
        p = 0;
        i = 0;
    }

    public void setEnabled(boolean isEnabled)
    {
        if (isEnabled)
        {
            if (!enabled)
            {
                setZero();
                motor.setPower(0);
            }
        }
        else
        {
            motor.setPower(0);
        }

        enabled = isEnabled;
    }

    public int getTarget()
    {
        return setDegrees - targetDelta;
    }

    public void update()
    {
        error = setDegrees - motor.getCurrentPosition();
        p = error;
        i += error;

        setPower = minMax(p * kP + i * kI, 0, 1);

        if (reversed) setPower *= -1;

        if(!enabled) return;

        motor.setPower(setPower);
    }

    public void writeDebug(Telemetry t)
    {
        t.addData("p", p);
        t.addData("i", i);
        t.addData("setpoint", (p * kP + i * kI));
        t.addData("position", motor.getCurrentPosition());
    }

    // Dominic's minMax method
    private double minMax(double val, double min, double max)
    {
        double ret = val;
        double sign = (val / Math.abs(val));
        if (Math.abs(val) < min) ret = 0;
        if (Math.abs(val) > max) ret = max * sign;

        return ret;
    }


    /*
    // Tyler's changes
    private double minMax(double val, double min, double max)
    {
        double sign = (val / Math.abs(val));
        if (Math.abs(val) < min) val = 0;   // val = min? val = sign * min?
        else if (Math.abs(val) > max) val = max * sign;
        return val;
    }
    */
}
