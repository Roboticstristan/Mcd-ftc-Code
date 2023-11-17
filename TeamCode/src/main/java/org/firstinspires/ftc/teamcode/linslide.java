package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="linslide", group="Linear Opmode")
public class linslide extends LinearOpMode {
    private DcMotor linearSlide = null;
    private DcMotor linearSlide1 = null;
    private ElapsedTime runtime = new ElapsedTime();
        public void runOpMode() {
            linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
            linearSlide1 = hardwareMap.get(DcMotor.class, "linear_slide2");
            waitForStart();
            runtime.reset();
            while (opModeIsActive()){
                if(gamepad1.left_trigger > 0)
                {
                    linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                    linearSlide1.setDirection(DcMotorSimple.Direction.FORWARD);
                    linearSlide.setPower(0.6);
                    linearSlide1.setPower(0.6);
                }else if(gamepad1.right_trigger > 0)
                {
                    linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                    linearSlide1.setDirection(DcMotorSimple.Direction.REVERSE);
                    linearSlide.setPower(0.9);
                    linearSlide1.setPower(0.9);
                } else {
                    linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                    linearSlide1.setDirection(DcMotorSimple.Direction.FORWARD);
                    linearSlide.setPower(0.09);
                    linearSlide1.setPower(0.09);
                    }
                }
            }
        }

