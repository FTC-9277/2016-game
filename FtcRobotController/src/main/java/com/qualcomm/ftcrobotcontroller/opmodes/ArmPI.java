package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.utils.PIProvider;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class ArmPI extends OpMode
{

    int target;
    DcMotor rightDrive, leftDrive, armMotor;
    TouchSensor sensor;
    //PIProvider armPID;

    @Override
    public void init()
    {
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        armMotor = hardwareMap.dcMotor.get("armMotor");
        sensor = hardwareMap.touchSensor.get("touchSensor");

        //armPID = new PIProvider(armMotor, 0.001, 0.001); //Tune me here for stability
    }

    @Override
    public void loop()
    {
        rightDrive.setPower(gamepad2.left_stick_y);
        leftDrive.setPower(-gamepad2.right_stick_y);

        if (gamepad2.a)
        {
           // armPID.setEnabled(false);
            while(/*!sensor.isPressed()*/ true)
            {
                armMotor.setPower(1);
            }
            //armPID.setEnabled(true);
        }

        if (gamepad1.left_stick_y > 0.2)
        {
            //armPID.setTarget(armPID.getTarget() + 1); //Tune me here for speed
        }
        else if (gamepad1.left_stick_y < -0.2)
        {
            //armPID.setTarget(armPID.getTarget() - 1); //Tune me here for speed
        }

        //if (gamepad1.a)
        // {
          //  armPID.setZero();
        //}

        //armPID.writeDebug(telemetry);
        //armPID.update();
    }
}
