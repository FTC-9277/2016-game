package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by robotics on 10/1/2015.
 */
public class TeleOpTest extends OpMode
{

    DcMotor rightDrive, leftDrive;
    //DcMotor beaconMotor, triWheelMotor, pullupMotor;
    //DcMotorController driveController;

    @Override
    public void init()
    {
        //driveController = hardwareMap.dcMotorController.get("driveController");

        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

       // beaconMotor = hardwareMap.dcMotor.get("beacon_motor");
       // triWheelMotor = hardwareMap.dcMotor.get("triwheel_motor");
       // pullupMotor = hardwareMap.dcMotor.get("pulUp_motor");
    }

    @Override
    public void loop()
    {
        rightDrive.setPower(gamepad1.left_stick_y);
        leftDrive.setPower(gamepad1.right_stick_y);
        telemetry.addData("left motor power", leftDrive.getPower());
        telemetry.addData("right motor power", rightDrive.getPower());
    }
}
