package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Vision.Sampler;
import robot.ExplosivesRobot;

@Autonomous(name = "BLUE - Single Vision Stone Grab", group = "stone")
public class BlueVisionStoneGrab extends LinearOpMode {

    //yeeeeet

    ExplosivesRobot robot;
    Sampler sample;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();
        sample = new Sampler(this);

        waitForStart();

        int stone = 1;

        stone = sample.sample();

        robot.driveTime(0.5,750);

        wait(500);

        int sampleCount = 0;

        while(opModeIsActive()) {
            telemetry.addData("EYESSSSS: ", stone);
            telemetry.update();

            stone = sample.sample();
            sampleCount++;
            wait(100);

            if(stone == 0) {
                robot.fright.setPower(0.3);
                robot.bright.setPower(-0.3);
                robot.fleft.setPower(-0.3);
                robot.bleft.setPower(0.3);
            } else {
                break;
            }
        }

//        robot.fright.setPower(0.3);
//        robot.bright.setPower(-0.3);
//        robot.fleft.setPower(-0.3);
//        robot.bleft.setPower(0.3);
//        wait(250);

        robot.stop();
        wait(500);

        robot.driveTime(0.9,1250);

        wait(500);

        robot.gyroTurn(0.8,200);

        wait(500);

        robot.driveTime(0.8,650);

        wait(500);

        robot.gyroTurn(0.8,80);

        wait(500);

        robot.driveTime(1.0, (125*sampleCount+2000));

        wait(500);

        robot.driveTime(-0.75,(125*sampleCount+900));

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
