package com.qualcomm.ftcrobotcontroller.opmodes;


import android.hardware.Sensor;

import com.qualcomm.ftcrobotcontroller.utils.PIProvider;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Hazmat on 10/10/2015.
 *
 * @ authors: Michael, Varun, Tyler
 * @ editors: Sachin, Fu jr., Jeffrey
 */
public class ArmTeleopTest extends OpMode
{

    //create variables for motors/sensors
    DcMotor rightDrive, leftDrive, armTilt, arm1;//, arm2;
    PIProvider armPi;
    TouchSensor frontSensor, backSensor;
    ColorSensor beaconSensor;
    double drivePower = 0.75;

    @Override
    public void init()
    {
        //Link variables to corresponding motors/sensors
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        armTilt = hardwareMap.dcMotor.get("armTilt");
        arm1 = hardwareMap.dcMotor.get("arm1");
        // arm2 = hardwareMap.dcMotor.get("arm2");
        frontSensor = hardwareMap.touchSensor.get("TSFront");
        backSensor = hardwareMap.touchSensor.get("TSBack");
        // beaconSensor = hardwareMap.colorSensor.get("colorSensor");
        // Set the right drive to reverse direction
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        armTilt.setDirection(DcMotor.Direction.REVERSE);
        // initially 0.75, 0.005
        armPi = new PIProvider(armTilt, 0.75, 0.005);
        armPi.setReversed(false);
        armPi.setEnabled(true);

        //arm1.setPower(0);
        //armTilt.setPower(0);
    }

    @Override
    public void loop()
    {

        // Set the power of the arm and the drives to move them using the gamepad joysticks
        leftDrive.setPower(drivePower * gamepad1.left_stick_y);
        rightDrive.setPower(drivePower * gamepad1.right_stick_y);

        if (gamepad2.left_stick_y >= 0.2)
        {
            armPi.setTarget(armPi.getTarget() - 1);     //SLOWER: ADJUST 1
        }

        if (gamepad2.left_stick_y <= -0.2)
        {
            armPi.setTarget(armPi.getTarget() + 1);     //FASTER: ADJUST 1
        }

        if (gamepad2.left_stick_button)
        {
            armPi.setZero();
        }

        /*if (-gamepad2.right_stick_y > 0 && !frontSensor.isPressed())
        {
            arm1.setPower(-gamepad2.right_stick_y);
        }
        else if (-gamepad2.right_stick_y < 0 && !backSensor.isPressed())
        {
            arm1.setPower(-gamepad2.right_stick_y);
        }
        else if(!frontSensor.isPressed() && !backSensor.isPressed())
        {
            arm1.setPower(-gamepad2.right_stick_y);
        }*/

        if (!frontSensor.isPressed() && !backSensor.isPressed())
        {
            arm1.setPower(-gamepad2.right_stick_y);
        }
        else
        {
            arm1.setPower(0);
        }

        /*if(beaconSensor.blue() > beaconSensor.red())
        {
            telemetry.addData("blue", 1);
        }
        else
        {
            telemetry.addData("red", 1);
        }*/

        armPi.update();
        armPi.writeDebug(telemetry);

        telemetry.addData("front sensor pressed", frontSensor.isPressed());
        telemetry.addData("back sensor pressed", backSensor.isPressed());

    }
}
