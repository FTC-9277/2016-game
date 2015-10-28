package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.hardware.ModernRoboticsOpticalDistanceSensor;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LegacyModule;

/**
 * Created by Vsrun and Sachin on 10/22/2015.
 * Edited by Tyler on 10/24/2015.
 */

public class SensorTestTeleop extends OpMode {
    Telemetry telemetry;
    //TouchSensor touchSensor;
    ColorSensor colorSensor;
    ModernRoboticsOpticalDistanceSensor lightSensor;

    @Override
    public void init() {
        // touchSensor = hardwareMap.touchSensor.get("touchSensor");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        lightSensor = (ModernRoboticsOpticalDistanceSensor) hardwareMap.opticalDistanceSensor.get("light_Sensor");
    }

    @Override // just to clarify, this is capitalised(with an "s" u dumb americans)
    public void loop() {

        int redVal, blueVal, greenVal;
        redVal = colorSensor.red();
        blueVal = colorSensor.blue();
        greenVal = colorSensor.green();

        telemetry.addData("red_value", redVal);
        telemetry.addData("blue_value", blueVal);
        telemetry.addData("green_value", greenVal);

        if (blueVal > redVal)
        {
            telemetry.addData("blue detected", 1);
        }
        else
        {
            telemetry.addData("red detected", 1);
        }

        double reflectance = lightSensor.getLightDetected();
        telemetry.addData("reflectance", reflectance);

        if (reflectance >= 0.5)
        {
            telemetry.addData("High Reflect", 2);
        }
        else
        {
            telemetry.addData("Low Reflect", 2);
        }

        /*if (!touchSensor.isPressed())
        {
            telemetry.addData("Not pressed", 3);
        }
        else
        {
            telemetry.addData("Pressed", 3);
        }*/
    }

}
