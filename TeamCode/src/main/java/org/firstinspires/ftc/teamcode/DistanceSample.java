package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class DistanceSample extends LinearOpMode {
    DistanceSensor distance;

    @Override
    public void runOpMode() {
        // Get the distance sensor and motor from hardwareMap
        distance = hardwareMap.get(DistanceSensor.class, "Distance");

        // Loop while the Op Mode is running
        waitForStart();
        while (opModeIsActive()) {
            // If the distance in centimeters is less than 10, set the power to 0.3
            if (distance.getDistance(DistanceUnit.INCH) > 4) {
                telemetry.addData("Distance in inches: ", distance.getDistance(DistanceUnit.INCH));
                telemetry.update();
            } else {  // Otherwise, stop the motor
                telemetry.addData("Distance in centimeters: ", distance.getDistance(DistanceUnit.CM));
                telemetry.update();
            }
        }
    }
}

