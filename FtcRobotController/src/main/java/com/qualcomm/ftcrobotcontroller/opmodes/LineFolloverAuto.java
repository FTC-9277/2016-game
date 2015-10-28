package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by robotics on 10/10/2015.
 */
public class LineFolloverAuto extends LinearOpMode {

    DcMotor leftDrive, rightDrive;
    ColorSensor colorSensor;
    TouchSensor touchSensor;
    Telemetry printToPhone;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        printToPhone.addData("Running", 0);
        while(!touchSensor.isPressed())
        {
            printToPhone.addData(getLowercaseColor(), 0);
            if(getLowercaseColor().equals("white"))
            {
                leftDrive.setPower(0.75);
                rightDrive.setPower(1);
            }
            else if(getLowercaseColor().equals("black"))
            {
                leftDrive.setPower(1);
                rightDrive.setPower(0.75);
            }
            else
            {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
                printToPhone.addData("NOT WORKING!!!!!", 0);
            }
        }
        printToPhone.addData("Finished", 0);

    }
    public String getColor()
    {
        return colorSensor.toString();
    }
    public String getLowercaseColor()
    {
        return getColor().toLowerCase();
    }
}
