package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robotics on 10/9/2015.
 */
public class ArmTest extends LinearOpMode {

    DcMotor rightDrive, leftDrive, armMotor;
    TouchSensor sensor;
    ElapsedTime timer;
    double driveSpeed = 0.5;
    @Override
    public void runOpMode() throws InterruptedException
    {
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        armMotor= hardwareMap.dcMotor.get("armMotor");
        sensor = hardwareMap.touchSensor.get("touchSensor");



        timer = new ElapsedTime();
        timer.startTime();

        while(timer.time()<=2)
        {
            rightDrive.setPower(driveSpeed);
            leftDrive.setPower(driveSpeed * -1);
        }
        rightDrive.setPower(0);
        leftDrive.setPower(0);

        timer.reset();

        while(!sensor.isPressed())
        {
            armMotor.setPower(1);
        }
        armMotor.setPower(0);

    }


}
