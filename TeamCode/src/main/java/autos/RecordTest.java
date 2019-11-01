package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

import recorder.RecordMotor;
import recorder.Recorder;
import robot.ExplosivesRobot;
import robot.SmolBoi;

//@Autonomous(name = "Record Test")
public class RecordTest extends LinearOpMode {

    SmolBoi robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new SmolBoi(this);
        robot.init();

        waitForStart();


    }
}
