package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class thomasTesting {
    @TeleOp(name="servoThomas", group="Linear Opmode")
    public class servo extends LinearOpMode {
        private Servo servo1 = null;
        private ElapsedTime runtime = new ElapsedTime();

        @Override
        public void runOpMode() {
            int daCount = 0;
            double maxPos = 0.5;
            double minPos = -0.5;
            waitForStart();
            runtime.reset();
            while(opModeIsActive()){
                servo1 = hardwareMap.get(Servo.class, "motorTest");
                if(gamepad1.a){
                    daCount++;
                }
                if(daCount % 2 != 0){
                    servo1.setDirection(Servo.Direction.REVERSE);
                    servo1.setPosition(Servo.MAX_POSITION);
                    sleep(1000);
                    servo1.setPosition(Servo.MIN_POSITION);
                }
                else {
                    servo1.setPosition(0);
                }
            }
        }

        }
    }


