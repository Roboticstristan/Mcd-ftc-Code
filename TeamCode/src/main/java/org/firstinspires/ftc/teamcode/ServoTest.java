package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Servo", group="Auto2022")
public class ServoTest extends LinearOpMode {
    private Servo droneServo = null;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        droneServo = hardwareMap.get(Servo.class,"droneServo");
        waitForStart();
        runtime.reset();
            droneServo.setPosition(Servo.MAX_POSITION/2);
        if(opModeIsActive()){
            droneServo.setPosition(Servo.MAX_POSITION/2);
            telemetry.addData("Duncan is: ", "Laughing at Build Team");
            telemetry.update();
        }
    }
}

