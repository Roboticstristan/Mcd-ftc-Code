package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//This is board side Blue
//RedBoredSide
@Autonomous(name="RedBoardSide", group="Auto2022")
public class GavinoAutoIIRed extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;

    private DcMotor llSlide = null;
    private DcMotor rlSlide = null;

    //private DcMotor llSlide = null;

    //private DcMotor rlSlide = null;
    private DistanceSensor sensorRange1;
    private DistanceSensor sensorRange2;
    public Servo boxServo = null;
    public Servo pixelServo = null;
    public Servo armServo = null;

    private Servo droneServo = null;
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

    public void LINEAR_SLIDE_DRIVE(float distance_in_in, double power) {
        float ticksPerInch = 450.149432158f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        //753.1 ticks per revolution
        //1.673 in per revolution (circumference)
        //450.149432158 ticks per in
        if (power > 0) {
            //go up
            llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
            rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            //go down
            llSlide.setDirection(DcMotorSimple.Direction.FORWARD);
            rlSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        llSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        llSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        llSlide.setPower(power);
        llSlide.setTargetPosition(ticks);
        llSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rlSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rlSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rlSlide.setPower(power);
        rlSlide.setTargetPosition(ticks);
        rlSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("slide", "> is running to position");
        telemetry.update();
        while (rlSlide.getCurrentPosition() <= (rlSlide.getTargetPosition() - 50) && llSlide.getCurrentPosition() <= (llSlide.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("slide", "> is strafing to position");
            telemetry.addData("ticks", ">" + rlSlide.getCurrentPosition() + " need to get to " + rlSlide.getTargetPosition());
            telemetry.addData("slide", "> is strafing to position");
            telemetry.addData("ticks", ">" + llSlide.getCurrentPosition() + " need to get to " + llSlide.getTargetPosition());
            telemetry.update();
        }
    }


    //  public void getData() {
    //     sensorRange1.getDistance(DistanceUnit.CM);
    //  sensorRange2.getDistance(DistanceUnit.CM);
    //  }




    public void markerDetection() {
        sleep(500);
        if (sensorRange1.getDistance(DistanceUnit.CM) > sensorRange2.getDistance(DistanceUnit.CM) && (sensorRange1.getDistance(DistanceUnit.CM) < 20 || sensorRange2.getDistance(DistanceUnit.CM) < 20)) {  //Runs following code only if the block is on the left
            telemetry.addData("Block Placement:", "Left");  //Adds information to the phone
            DRIVE_DISTANCE_FORWARD(7,0.8);
            sleep(500);
            TURN(1, 20f);   // Turning to the right 90 degrees
            sleep(1000);    // Wait 1 second
            pixelServo.setPosition(Servo.MAX_POSITION); // Places the pixel
            sleep(1500);    // Wait 1.5 seconds
            //Set the purple pixel servo to the minimum preset position (set back to upward position)
            pixelServo.setPosition(Servo.MIN_POSITION);
            // Waiting a second so that the servo is out of way before moving
            sleep(1000);
            // Turn back in the opposite direction with the front facing the starting position again
            TURN(-1, 20f);
            sleep(750);
            circumnavigate(1);

            // The second scenario within an else if statement that evaluates if the first is false. The distance sensors are checked under these different peramaters
        } else if (sensorRange1.getDistance(DistanceUnit.CM) < sensorRange2.getDistance(DistanceUnit.CM) && (sensorRange1.getDistance(DistanceUnit.CM) < 20 || sensorRange2.getDistance(DistanceUnit.CM) < 20)) {   // Runs following code only if block is on the right
            // In this else if statement is true then this telemetry dada will be translated to the phone telling us that the marker is on the right
            telemetry.addData("Block Placement:", "Right");
            //Fix allignment before placing
            //Drive forword before placing pixel
            // Put gate up after dropping pixel
            // Turn left to face alliance marker
            //DRIVE_DISTANCE_RIGHT(5);
            //DRIVE_DISTANCE_LEFT(5);
            TURN(-1, 20f);
            sleep(500);
            DRIVE_DISTANCE_FORWARD(6,0.8);
            DRIVE_DISTANCE_FORWARD(5,-0.8);
            DRIVE_DISTANCE_LEFT(8);
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
            //DRIVE_DISTANCE_FORWARD(-2,1);
            sleep(760);
            DRIVE_DISTANCE_LEFT(13);
            sleep(600);
            DRIVE_DISTANCE_FORWARD(36.5f,0.7f);
            sleep(500);
            DRIVE_DISTANCE_RIGHT(28);
            //check in comp ( increase for more left decreace for mor right)
            DRIVE_DISTANCE_FORWARD(1,1);
            //This is 38 - 5.3 which takes away time *** we added changes
            //DRIVE_DISTANCE_FORWARD(3f,0.8);
            sleep(750);
            //DRIVE_DISTANCE_RIGHT(5.3f); //Newly Added Code
            TURN(1,40);
            place();
            sleep(500);
            sleep(750);
            DRIVE_DISTANCE_LEFT(18f); // 25 -5.3 which takes away the correction; keeps it but shortens time
            sleep(400);
            DRIVE_DISTANCE_FORWARD(22.5f,-1);


            // turning to set up circumnavigate

            // Turn back to face original starting position

            // The final iteration which is the third option so only requires an else; not an else if
        } else {    // Runs following code only if block neither on right or left
            // Through process of elimination we determine that if the block is not to the left or right of us then it is in front of us
            telemetry.addData("Block Placement:", "Forward");
            // Turn 180 degrees to have the front of the robot facing the team prop assigned line
            DRIVE_DISTANCE_FORWARD(4.5f,1);
            TURN(-1, 40f);
            // Wait 1 second for efficiency
            sleep(1000);
            // As before, set the servo to outward position so that the pixel can drop
            pixelServo.setPosition(Servo.MAX_POSITION);
            // Wait 1.5 seconds to ensure pixel fall out properly
            sleep(1500);
            //Sets back to og position.
            pixelServo.setPosition(Servo.MIN_POSITION);
            // Wait 1 second
            sleep(1000);
            // Turn to face original
            TURN(1, 40f);

            DRIVE_DISTANCE_FORWARD(4,1);
            circumnavigate(0);
        }
    }

    public void place(){
        sleep(200);
        llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        sleep(300);
        llSlide.setPower(0.2);
        rlSlide.setPower(0.2);
        sleep(300);
        armServo.setPosition(Servo.MIN_POSITION);
        sleep(500);
        LINEAR_SLIDE_DRIVE(2.6f,0.5);
        sleep(500);
        //boxServo.setPosition(Servo.MIN_POSITION);
        DRIVE_DISTANCE_FORWARD(-10,0.7);
        sleep(500);
        boxServo.setPosition(Servo.MIN_POSITION);
        //Check to see if servo can get pixel to drop by backing up a little
        sleep(1000);
        DRIVE_DISTANCE_FORWARD(10,0.7);
        sleep(500);
        boxServo.setPosition(Servo.MAX_POSITION / 2);
        sleep(500);
        LINEAR_SLIDE_DRIVE(2.2f,-0.5);
        sleep(500);
        armServo.setPosition(Servo.MAX_POSITION);
        sleep(200);
    }
    public void circumnavigate(int pplace){
        //DRIVE_DISTANCE_FORWARD(26f,0.8);
        //sleep(500);
        DRIVE_DISTANCE_FORWARD(-4,1);

        sleep(500);

        DRIVE_DISTANCE_LEFT(28f);

        sleep(500);

        if(pplace == 0){
            DRIVE_DISTANCE_FORWARD(-8,1);
            //check in comp
            sleep(500);
        }
        TURN(1,20);
        sleep(300);
        DRIVE_DISTANCE_FORWARD(-5f,0.8);
        sleep(500);
        //Release
        sleep(500);
        DRIVE_DISTANCE_FORWARD(3f,0.8);
        if (pplace == 1){
            DRIVE_DISTANCE_RIGHT(7.5f); //Newly Added Code
            //check in comp ( increase for more left decreace for mor right)
        }
        place();
        sleep(500);
        DRIVE_DISTANCE_LEFT(5.3f);
        //sleep(1000);
        DRIVE_DISTANCE_LEFT(30);
        sleep(500);
        DRIVE_DISTANCE_FORWARD(25,-1);
        sleep(500);
        DRIVE_DISTANCE_FORWARD(5f,-1);

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
        llSlide = hardwareMap.get(DcMotor.class, "leftlinear_slide");
        rlSlide = hardwareMap.get(DcMotor.class, "rightlinear_slide");
        pixelServo = hardwareMap.get(Servo.class, "pixelServo");
        boxServo = hardwareMap.get(Servo.class, "box");
        armServo = hardwareMap.get(Servo.class,"arm");
        droneServo = hardwareMap.get(Servo.class, "droneServo");




        /*if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }
        claw.setPosition(0);
        waitForStart();
        runtime.reset();
         */
        pixelServo.setPosition(Servo.MIN_POSITION);
        armServo.setPosition(Servo.MAX_POSITION);
        droneServo.setPosition(Servo.MAX_POSITION/2);
        waitForStart();
        runtime.reset();


        //run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            DRIVE_DISTANCE_FORWARD(-32,0.8);
            sleep(2000);
            markerDetection();
            //  circumnavigate();
            //DRIVE_DISTANCE_FORWARD(28,1.2);
            //DRIVE_DISTANCE_RIGHT(20.4f);
            //DRIVE_DISTANCE_FORWARD(-24,1.2);
            //TURN(1,20);
        }
        sleep(1000);
    }

//Tristan is smelly
}
