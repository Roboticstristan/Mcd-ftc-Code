package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="blueSideClose", group="Auto2022")
public class GavinoAutoBlue extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DistanceSensor sensorRange1;
    private DistanceSensor sensorRange2;

    public Servo boxServo = null;
    public Servo pixelServo = null;

    public Servo armServo = null;
    public double ServoMax = 0.5;
    public double ServoMin = 0;




    public void setDirectionForward() {
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setDirectionBackward() {
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void STOP_AND_RESET_ENCODERS_ALL_WHEELS() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void SET_POWER_ALL_WHEELS(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    public void DRIVE_DISTANCE_FORWARD(float distance_in_in, double power) {
        float ticksPerInch = 59.6031746032f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        // 751.8 ticks per rotation

        if (power > 0) {
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        }


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        SET_POWER_ALL_WHEELS(power);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are running to position");
        telemetry.update();
        while (frontLeft.getCurrentPosition() <= (frontLeft.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("motors", "> Are strafing to position");
            telemetry.addData("ticks", ">" + frontLeft.getCurrentPosition() + " need to get to " + frontLeft.getTargetPosition());
            telemetry.update();
        }
    }

    void TURN(int power, float distance_in_in) {
        //turn positive power is left - turn negative power is right
        float ticksPerInch = 59.6031746032f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        // 1120 Ticks per revolution

        if (power > 0) {
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        //backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        //frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        //frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        //backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        SET_POWER_ALL_WHEELS(0.6);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are running to position");
        telemetry.update();
        while (frontLeft.getCurrentPosition() <= (frontLeft.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("motors", "> Are strafing to position");
            telemetry.addData("ticks", ">" + frontLeft.getCurrentPosition() + " need to get to " + frontLeft.getTargetPosition());
            telemetry.update();
        }

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();

        telemetry.addData("motors", "> have run to position");
        telemetry.update();
    }

    public void DRIVE_DISTANCE_RIGHT(float distance_in_in) {
        float ticksPerInch = 120.737061895f;
        float f_ticks = ((ticksPerInch * distance_in_in)/1.7f);
        int ticks = Math.round(f_ticks);
        // 1120 Ticks per revolution

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        SET_POWER_ALL_WHEELS(1);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are running to position");
        telemetry.update();
        while (frontLeft.isBusy()) {
            //Wait until job is finished
        }

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();

        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("motors", "> have run to position");
        telemetry.update();
    }

    public void DRIVE_DISTANCE_LEFT(float distance_in_in) {
        float ticksPerInch = 120.737061895f;
        float f_ticks = ((ticksPerInch * distance_in_in)/1.7f);
        int ticks = Math.round(f_ticks);
        // 1120 Ticks per revolution


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        SET_POWER_ALL_WHEELS(1);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are strafing to position");
        telemetry.update();
        while (frontLeft.getCurrentPosition() <= (frontLeft.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("motors", "> Are strafing to position");
            telemetry.addData("ticks", ">" + frontLeft.getCurrentPosition());
            telemetry.update();
        }

    }

    /*
    public void LINEAR_SLIDE_DRIVE(float distance_in_in, double power) {
        float ticksPerInch = 450.149432158f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        //753.1 ticks per revolution
        //1.673 in per revolution (circumference)
        //450.149432158 ticks per in
        if (power > 0) {
            //go up
            linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            //go down
            linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setPower(power);
        linearSlide.setTargetPosition(ticks);
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("slide", "> is running to position");
        telemetry.update();
        while (linearSlide.getCurrentPosition() <= (linearSlide.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("slide", "> is strafing to position");
            telemetry.addData("ticks", ">" + linearSlide.getCurrentPosition() + " need to get to " + linearSlide.getTargetPosition());
            telemetry.update();
        }
    }
     */

    //  public void getData() {
    //     sensorRange1.getDistance(DistanceUnit.CM);
    //  sensorRange2.getDistance(DistanceUnit.CM);
    //  }
    //20 inches is 90 degrees
    // 20 in = turn right
    //-20 in = turn left


    public void markerDetection() {
        // This is an if statement to determine if the marker is within 20 cm from the right distance sensor
        // We turn the opposite direction of the detected block placement because our robot strategically starts off backwards so our code can be more efficient-
        // and so we can navigate the board easier
        //If this if statement evaluates to true based on the parameters then the robot will execute the code only within this if statement and nothing else
        if (sensorRange1.getDistance(DistanceUnit.CM) > sensorRange2.getDistance(DistanceUnit.CM) &&
                (sensorRange1.getDistance(DistanceUnit.CM) < 20 || sensorRange2.getDistance(DistanceUnit.CM) < 20)) {
            // This is telemetry which is data that is uploaded to our phone telling us the live output from our sensors
            // This is helpful in parsing through our code to see our mistakes
            telemetry.addData("Block Placement:", "Left");
            // Turning to the right 90 degrees so we are now facing the alliance marker that we detected
            TURN(1, 20f);
            // Wait 1 second (No movement) to ensure that the servo is not moved while placing pixel
            sleep(1000);
            //Set the purple pixel servo to the max preset position (set to downward position)
            pixelServo.setPosition(Servo.MAX_POSITION);
            // Wait 1.5 seconds so the pixel doesn't get stuck on servo  (it gets a chance to drop out)
            sleep(1500);
            //Set the purple pixel servo to the minimum preset position (set back to upward position)
            pixelServo.setPosition(Servo.MIN_POSITION);
            // Waiting a second so that the servo is out of way before moving
            sleep(1000);
            // Turn back in the opposite direction with the front facing the starting position again
            TURN(-1, 20f);
            // The second scenario within an else if statement that evaluates if the first is false. The distance sensors are checked under these different peramaters
        } else if (sensorRange1.getDistance(DistanceUnit.CM) < sensorRange2.getDistance(DistanceUnit.CM) &&
                (sensorRange1.getDistance(DistanceUnit.CM) < 20 || sensorRange2.getDistance(DistanceUnit.CM) < 20)) {
            // In this else if statement is true then this telemetry dada will be translated to the phone telling us that the marker is on the right
            telemetry.addData("Block Placement:", "Right");
            // Turn left to face alliance marker
            TURN(-1, 20f);
            // Wait 1 second to make sure we are perfectly facing optimal drop spot
            sleep(1000);
            // Drop the pixel off of the servo
            pixelServo.setPosition(Servo.MAX_POSITION);
            // Wait 1.5 seconds so the pixel doesn't get stuck on servo  (it gets a chance to drop out)
            sleep(1500);
            // Verify the mechanism is at top by re-setting it to max position
            pixelServo.setPosition(Servo.MIN_POSITION);
            // sleep so that the servo is set up correctly and not poking out
            sleep(1000);
            // Turn back to face original starting position
            TURN(1, 20f);
            // The final iteration which is the third option so only requires an else; not an else if
        } else {
            // Through process of elimination we determine that if the block is not to the left or right of us then it is in front of us
            telemetry.addData("Block Placement:", "Forward");
            // Turn 180 degrees to have the front of the robot facing the team prop assigned line
            TURN(-1, 40f);
            // Wait 1 second for efficiency
            sleep(1000);
            // As before, set the servo to outward position so that the pixel can drop
            pixelServo.setPosition(Servo.MAX_POSITION);
            // Wait 1.5 seconds to ensure pixel fall out properly
            sleep(1500);
            //sets back to og position.
            pixelServo.setPosition(Servo.MIN_POSITION);

            sleep(1000);
            TURN(1, 40f);
        }
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontRight = hardwareMap.get(DcMotor.class, "rightFront_Drive");
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront_Drive");
        backRight = hardwareMap.get(DcMotor.class, "rightBack_Drive");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack_Drive");
        sensorRange1 = hardwareMap.get(DistanceSensor.class, "left_Distance");
        sensorRange2 = hardwareMap.get(DistanceSensor.class, "right_Distance");
        //linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_colorb");
        pixelServo = hardwareMap.get(Servo.class, "pixelServo");
        boxServo = hardwareMap.get(Servo.class, "box");
        armServo = hardwareMap.get(Servo.class,"arm");



        /*if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }
        claw.setPosition(0);
        waitForStart();
        runtime.reset();
         */
        pixelServo.setPosition(Servo.MIN_POSITION);
        armServo.setPosition(Servo.MAX_POSITION);
        waitForStart();
        runtime.reset();


        //run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            // TURN(1, 90f);

           // DRIVE_DISTANCE_FORWARD(-30f, 0.8);
            //sleep(1873);
            markerDetection();
        }
        sleep(2000);


        //DRIVE_DISTANCE_FORWARD(50f, 0.5);
       // DRIVE_DISTANCE_RIGHT(20.4f);

       // DRIVE_DISTANCE_FORWARD(5f, 1);

        //  TURN(12,1);
        // DRIVE_DISTANCE_RIGHT();
        }

    }



