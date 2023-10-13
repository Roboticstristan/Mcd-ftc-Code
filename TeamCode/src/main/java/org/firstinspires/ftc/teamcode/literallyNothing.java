package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Dont_Worry", group="Auto2023")
public class literallyNothing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Running: ",  "true");
        telemetry.update();
        if(opModeIsActive()){
            threadFun twistyTristy = new threadFun(1);
            twistyTristy.start();
        }
    }
}
