package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "April_Tag_Tests", group = "Linear Opmode")
public class nuclearFission extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal malevolentShrine = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "sixEyes"))
                .setCameraResolution(new Size(640, 480))
                .setAutoStopLiveView(true)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();


        waitForStart();

        while (!isStopRequested() && opModeIsActive()){
            malevolentShrine.getCameraState();
            malevolentShrine.getFps();
            if (tagProcessor.getDetections().size() > 0){
                AprilTagDetection tagYoureIt = tagProcessor.getDetections().get(0);
                int idCode = tagYoureIt.id;

                telemetry.addData("x", tagYoureIt.ftcPose.x);
                telemetry.addData("y", tagYoureIt.ftcPose.y);
                telemetry.addData("z", tagYoureIt.ftcPose.z);
                telemetry.addData("roll", tagYoureIt.ftcPose.roll);
                telemetry.addData("pitch", tagYoureIt.ftcPose.pitch);
                telemetry.addData("yaw", tagYoureIt.ftcPose.yaw);
                telemetry.addData("id code", idCode);
                // trying to get the id code
            }


            telemetry.update();
        }
    }
}
