package org.firstinspires.ftc.teamcode;
// This is the current Competition 1, 2023/2024 driving code with all motors + servos integrated in
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Star Cup", group="Linear Opmode")
public class MarioKart extends LinearOpMode {
    private DcMotor backLeft = null;
    private DcMotor  backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor llSlide = null;
    private DcMotor rlSlide = null;
    private DcMotor intake = null;
    private Servo box = null;
    private Servo arm = null;


    private
    //Defines Info for Claw Servo
    static final double INCREMENT   = 0.25;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    // 0 gets to the mid setting of servo button x closes
    // 0.5 gets to the left setting of servo button b opensstatic final double MAX_POS     =  0.5;     // Maximum rotational position
    public Servo pixelServo = null;

    private int count;
    private int count2;
    private int countArm;
    private int countPre;
    private int countPixel = 0;

    public void pixelControl(){
        sleep(500);
        pixelServo.setPosition(Servo.MAX_POSITION);
        sleep(500);
        /*if(countPixel % 2 != 0 ){
            pixelServo.setPosition(Servo.MAX_POSITION);

        } else if(countPixel % 2 == 0 ){
            pixelServo.setPosition(Servo.MIN_POSITION);
        }*/
    }
    //in the else if statement: "&& countPixel != 0"


    //variable that holds the amount of time is running
    private ElapsedTime runtime = new ElapsedTime();
    public void armControl(){
        if(countArm % 2 == 0 && countArm != 0){
            llSlide.setPower(-0.35);
            rlSlide.setPower(-0.35);
            sleep(300);
        } else if (countArm % 2 != 0) {
            llSlide.setPower(0.35);
            rlSlide.setPower(0.35);
            sleep(300);
        }
    }
    public void attempt() {
        //The first time this method is called and every other time after it should preset the arm and servo to dropping position
        if (countPre % 2 != 0) {
            llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
            rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
            llSlide.setPower(0.25);
            rlSlide.setPower(0.25);
            sleep(300);
            arm.setPosition(Servo.MIN_POSITION);
            sleep(500);
            llSlide.setPower(1.3);
            rlSlide.setPower(1.3);
            sleep(500);
            llSlide.setPower(0);
            rlSlide.setPower(0);
        //The second time this method is called and every other time after it should preset the arm and servo to receiving position
        } else if (countPre % 2 == 0 && countPre != 0) {
            llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
            rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
            llSlide.setPower(-0.35);
            rlSlide.setPower(-0.35);
            sleep(500);
            arm.setPosition(0.5);
            sleep(1000);
            llSlide.setPower(-1.5);
            rlSlide.setPower(-1.);
            sleep(1000);
            llSlide.setPower(0);
            rlSlide.setPower(0);
        }
    }


    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontRight = hardwareMap.get(DcMotor.class, "rightFront_Drive");
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront_Drive");
        backRight = hardwareMap.get(DcMotor.class, "rightBack_Drive");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack_Drive");
        llSlide = hardwareMap.get(DcMotor.class, "leftlinear_slide");
        rlSlide = hardwareMap.get(DcMotor.class, "rightlinear_slide");
        intake = hardwareMap.get(DcMotor.class, "intake");
        box = hardwareMap.get(Servo.class, "box");
        arm = hardwareMap.get(Servo.class, "arm");
        pixelServo = hardwareMap.get(Servo.class, "pixelServo");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        // Wait for the game to start (driver presses PLAY)
        arm.setPosition(0.5);
        pixelServo.setPosition(Servo.MAX_POSITION);
        waitForStart();
        runtime.reset();

        //  run until the end of the match (driver presses STOP)
        while (opModeIsActive()){
            // Show the elapsed game time and wheel power.
           // telemetry.addData(countPixel);
            //telemetry.update();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            double max;
            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = -1*(axial + lateral + yaw);
            double rightFrontPower = -1*(axial - lateral - yaw);
            double leftBackPower   = -1*(axial - lateral + yaw);
            double rightBackPower  = -1*(axial + lateral - yaw);

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.5) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }


            // Send calculated power to wheels
            frontLeft.setPower(leftFrontPower);
            frontRight.setPower(rightFrontPower);
            backLeft.setPower(leftBackPower);
            backRight.setPower(rightBackPower);
            // adds precesion mode when bumper pressed


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.update();
            pixelServo.setPosition(Servo.MIN_POSITION);


            //Forward brings linear slide up
            if(gamepad1.left_trigger > 0)
            {
                llSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                rlSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                llSlide.setPower(0.6);
                rlSlide.setPower(0.6);
            }else if(gamepad1.right_trigger > 0)
            {
                llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                llSlide.setPower(0.9);
                rlSlide.setPower(0.9);
            } else {
                llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                llSlide.setPower(0.09);
                rlSlide.setPower(0.09);
            }

            if(gamepad1.y){
                count++;
                if (count % 2 != 0) {
                    intake.setDirection(DcMotorSimple.Direction.REVERSE);
                    intake.setPower(0.6);
                    //motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                    //motor2.setPower(1);
                } else {
                    intake.setPower(0);
                    //motor2.setPower(0);
                }
            }

            if(gamepad2.right_bumper){
                box.setPosition(Servo.MAX_POSITION);
            }

            if(gamepad2.left_bumper){
                box.setPosition(Servo.MIN_POSITION);
            }

            /*if(gamepad1.a){
                count++;
                if (count % 2 == 1){
                    box.setPosition(Servo.MAX_POSITION);
                } else {
                    box.setPosition(Servo.MIN_POSITION);
                }
            }
             */

            if(gamepad1.b){
                countPre++;
                attempt();
            }
            if(gamepad1.x){
                countArm++;
                armControl();
            }
            if(gamepad1.a){
                countPixel++;
                pixelControl();
            }




        }

    }
}


