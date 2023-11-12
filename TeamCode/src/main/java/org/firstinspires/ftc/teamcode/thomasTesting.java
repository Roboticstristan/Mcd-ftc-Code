package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class thomasTesting {
    @TeleOp(name="intakeThomas", group="Linear Opmode")
    public class Intake extends LinearOpMode {
        private DcMotor motor1 = null;
        private ElapsedTime runtime = new ElapsedTime();

        @Override
        public void runOpMode() {
            int daCount = 0;
            waitForStart();
            runtime.reset();
            while(opModeIsActive()){
                motor1 = hardwareMap.get(DcMotor.class, "motorTest");
                if(gamepad1.a){
                    daCount++;
                }
                if(daCount % 2 != 0){
                    motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor1.setPower(1);
                }
                else {
                    motor1.setPower(0);
                }
            }
        }

        }
    }


