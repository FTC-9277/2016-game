package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by robotics on 10/10/2015.
 */
public class ProgrammingPlaybox extends OpMode
{
    DcMotor leftDrive, rightDrive;
    GyroSensor gyro;
    private int  clicksperdegree = 4;
    private double startingDirection = 0;
    private double currentDirection;

    @Override
    public void init()
    {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        gyro = hardwareMap.gyroSensor.get("gyro");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightDrive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void loop()
    {

    }

    public void turnToDegrees(int degrees)
    {
        leftDrive.setTargetPosition(degrees * clicksperdegree);
        rightDrive.setTargetPosition(-1*degrees * clicksperdegree);

        leftDrive.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightDrive.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);
         
        currentDirection = gyro.getRotation();
    }

    public void turnToDegree(double degrees)
    {
        double angle = startingDirection - currentDirection;


    }


}
