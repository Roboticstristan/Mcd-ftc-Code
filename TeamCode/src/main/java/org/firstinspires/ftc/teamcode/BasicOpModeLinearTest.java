
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

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class BasicOpModeLinearTest extends LinearOpMode {
    // Declare OpMode members.
    int pCounter = 0;
    //Defines Info for Claw Servo
    static final double INCREMENT = 0.25;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    // 0 gets to the mid setting of servo button x closes
    // 0.5 gets to the left setting of servo button b opens
    static final double MAX_POS = 0.5;     // Maximum rotational position
    static final double MIN_POS = 0;     // Minimum rotational position
    public Servo claw = null;
    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    //Declares motors
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor linearSlide = null;


    //variable that holds the amount of time is running
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {

        claw = hardwareMap.get(Servo.class, "claw");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, " back_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //  run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Moves the claw servo
            if (gamepad1.b) {
                position += INCREMENT;
                if (position >= MAX_POS) {
                    position = MAX_POS;
                }
            } else if (gamepad1.x) {
                position -= INCREMENT;
                if (position <= MIN_POS) {
                    position = MIN_POS;
                }
            } else {
                position = (MAX_POS - MIN_POS) / 2;
            }

            claw.setPosition(position);

            if (gamepad1.left_trigger > 0) {
                //go down
                linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                linearSlide.setPower(1);
            } else if (gamepad1.right_trigger > 0) {
                //go up
                linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                linearSlide.setPower(0.8);
            } else {
                linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                linearSlide.setPower(0.05);
            }


            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            //Axial = Forward/Backward
            //Lateral = Left/Right
            double axial = gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower = -1 * (axial + lateral + yaw);
            double rightFrontPower = -1 * (axial - lateral - yaw);
            double leftBackPower = -1 * (axial - lateral + yaw);
            double rightBackPower = -1 * (axial + lateral - yaw);

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }


            // Send calculated power to wheels
            frontLeft.setPower(leftFrontPower);
            frontRight.setPower(rightFrontPower);
            backLeft.setPower(leftBackPower);
            backRight.setPower(rightBackPower);
        }
    }
}
