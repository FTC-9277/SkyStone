package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Vision.Sampler;
import robot.ExplosivesRobot;

@Autonomous(name = "RED - Single Vision Stone Grab", group = "stone")
public class RedVisionStoneGrab extends LinearOpMode {

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

        wait(100);

        int sampleCount = 0;

        while(opModeIsActive()) {
            telemetry.addData("EYESSSSS: ", stone);
            telemetry.update();

            stone = sample.sample();
            sampleCount++;
            wait(100);

            if(stone == 0) {
                robot.fright.setPower(-0.3);
                robot.bright.setPower(0.3);
                robot.fleft.setPower(0.3);
                robot.bleft.setPower(-0.3);
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

        robot.driveTime(1.0,1250);

        wait(100);

        robot.gyroTurn(1.0,50);

        robot.gyroTurn(1.0,100);

        wait(100);

        robot.driveTime(1.0,650);

        wait(100);

        robot.gyroTurn(1.0,70);

        wait(100);

        robot.driveTime(1.0, (125*sampleCount+2000));

        wait(100);

        robot.driveTime(-1.0,(100*sampleCount+900));

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
