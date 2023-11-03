package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Autobots", group = "Auto2023")
public class DuncanAuto extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DistanceSensor sensorRange1;
    private DistanceSensor sensorRange2;
    //private DcMotor linearSlide = null;
    //declare color sensor
    //private NormalizedColorSensor colorSensor = null;
    //private DcMotor colorSensor = null;
    //public Servo claw = null;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        //frontRight  = hardwareMap.get(DcMotor.class, "front_right");
        //frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        //backRight = hardwareMap.get(DcMotor.class, "back_right");
        //backLeft = hardwareMap.get(DcMotor.class, "back_left");
        sensorRange1 = hardwareMap.get(DistanceSensor.class, "Distance1");
        sensorRange2 = hardwareMap.get(DistanceSensor.class, "Distance2");
        //linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_colorb");
        //claw = hardwareMap.get(Servo.class, "claw");

        //AutoMethods duncan = new AutoMethods(frontLeft, frontRight, backLeft, backRight, sensorRange1, sensorRange2);
        AutoMethods duncan = new AutoMethods(sensorRange1, sensorRange2);

        /*if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }
        claw.setPosition(0);
        waitForStart();
        runtime.reset();
         */
        waitForStart();
        runtime.reset();

        //run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            //duncan.DRIVE_DISTANCE_FORWARD(12f, 0.5);
            if (duncan.SIX_EYES() == 1){
                telemetry.addData("Block Placement:", "Left");
            } else if (duncan.SIX_EYES() == 2) {
                telemetry.addData("Block Placement:", "Right");
            } else {
                telemetry.addData("Block Placement:", "Forward");
            }
        }
    }
}
