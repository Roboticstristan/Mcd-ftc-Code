package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

// (Bottom Right Square )
@TeleOp(name="sensor_colorb", group="Auto2023")
public class ColorSensorTest extends LinearOpMode implements Runnable{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    /*private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DcMotor linearSlide = null;*/
    int colorC = 0;
    //declare color sensor
    private NormalizedColorSensor colorSensor = null;
    //private DcMotor colorSensor = null;
    public Servo claw = null;





    public void storeColor(){
        sleep(1600);


        NormalizedRGBA color = colorSensor.getNormalizedColors();

        //first option: forward and left
        if (color.blue > color.red && color.blue > color.green) {
            colorC = 1;
            telemetry.addData("Color is: ", "blue");
            telemetry.update();
        }
        // second option: forward
        if (color.red > color.blue && color.red > color.green) {
            colorC = 2;
            telemetry.addData("Color is: ", "red");
            telemetry.update();
        }
        //third option: forward and right
        if (color.green > color.red && color.green > color.blue) {

            colorC = 3;
            telemetry.addData("Color is: ", "green");
            telemetry.update();
        }
    }








    //SLEEVE FUNCTION
    public void senseColor() {
        //If one of the colors on our sleeve is detected stronger than the other two, it is that color
        NormalizedRGBA color = colorSensor.getNormalizedColors();
        //first option: forward and right
        if (color.blue > color.red && color.blue > color.green) {
            //claw.setPosition(0.5);

            telemetry.addData("Color is: ", "blue");

            telemetry.update();
        }
        // second option: forward
        if (color.red > color.blue && color.red > color.green) {
            //claw.setPosition(0.5);

            telemetry.addData("Color is: ", "red");
            telemetry.update();
        }
        //third option: forward and left
        if (color.green > color.red && color.green > color.blue) {
            //claw.setPosition(0.5);

            telemetry.addData("Color is: ", "green");
            telemetry.update();

        }
    }

    @Override
    public void run() {
        claw.setPosition(0.5);
        //for (int i = 0; i < 5; i++) {

        //telemetry.addData("Number is: ", + i);
        //telemetry.update();
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e ){
        //}
        //}
    }


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_colorb");
        //claw = hardwareMap.get(Servo.class, "claw");

        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);
        }
        //claw.setPosition(0);
        waitForStart();
        runtime.reset();

    }
}