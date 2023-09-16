package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


public class BasicOpModeLinear test{


    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;

    private ElapsedTime runtime = new ElapsedTIme();

    private void runOpMode(){
       // claw = hardwareMap.get(Servo.class."claw");
        backLeft = hardwareMap.get(Servo.class."back_left");
        backRight = hardwareMap.get(Servo.class."back_right");
        frontLeft = hardwareMap.get(Servo.class."front_left");
        rightLeft = hardwareMap.get(Servo.class."front_Left");

        backLeft.setDirection(DcMotor.Direction.Forward);
        backRight.setDirection(DcMotor.Direction.Reverse);
        frontLeft.setDirection(DcMotor.Direction.Forward);
        frontRight.setDirection(DcMotor.Direction.Reverse);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.Float)
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.Float)
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.Float)
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.Float)

        waitForStart();
        runtime.restart;


        while(runOpMode();)
            // Left joystick is movement(Forward,Backward,Left,Right) and strafing
            //Right joystick is turning
            double axial = -gamepad1.left_stick_y; //Left + RIght
            double lateral = gamepad1.left_stick_x; // Up + Down
            double yaw = gamepad1.right_stick_x;

            double leftFrontPower = -1(axial + lateral + yaw);
            double rightFrontPower = -1(axial - lateral - yaw);


        }


}
