package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name ="Autoshot", group ="Auto2023")


public class AutoDistance extends Autonomous{
    private DistanceSensor sensorRange1;
    private DistanceSensor sensorRange2;
    private ElapsedTime runtime = new ElapsedTime();
    @Override

    public void distanceInput(){
        sensorRange1 = hardwareMap.get(DistanceSensor.class, "Distance1");
        sensorRange2 = hardwareMap.get(DistanceSensor.class, "Distance2");

        double m1 = sensorRange1.getDistance(DistanceUnit.CM);
        double m2 = sensorRange2.getDistance(DistanceUnit.CM);

        if(m1 > m2 && m1 > 0.5 && m2 > 0.5){
            return 1;
        } else if(m2 > m1 && m1 > 0.5 && m2 > 0.5){
            return 2;
        } else {
            return 3;
        }


    }

    public void runOpMode() {
        // you can use this as a regular DistanceSensor.

        waitForStart();
        while(opModeIsActive()) {


        }

    }
}
