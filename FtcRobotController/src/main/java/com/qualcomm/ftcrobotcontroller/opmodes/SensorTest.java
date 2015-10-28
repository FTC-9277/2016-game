package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by robotics on 10/8/2015.
 */
public class SensorTest extends OpMode {

    ColorSensor sensor1;
    @Override
    public void init() {
        sensor1 = hardwareMap.colorSensor.get("sensor1");
    }

    @Override
    public void loop()
    {
        //if(sensor1.)
    }
}
