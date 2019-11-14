package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Vision.Sampler;
import robot.ExplosivesRobot;

@Autonomous(name = "BLUE - Vision Stone Grab", group = "stone")
public class BlueVisionStoneGrab extends LinearOpMode {

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

        robot.drive(0.5);
        wait(750);

        robot.stop();
        wait(500);

        while(opModeIsActive()) {
            telemetry.addData("EYESSSSS: ", stone);
            telemetry.update();

            stone = sample.sample();
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

        robot.fright.setPower(-0.3);
        robot.bright.setPower(0.3);
        robot.fleft.setPower(0.3);
        robot.bleft.setPower(-0.3);
        wait(250);

        robot.stop();
        wait(500);

        robot.drive(0.75);
        wait(1000);

        robot.stop();
        wait(500);

        robot.turn(0.8, ExplosivesRobot.Direction.LEFT);
        wait(1350);

        robot.stop();
        wait(500);

        robot.drive(0.8);
        wait(650);

        robot.stop();
        wait(500);

        robot.turn(0.8, ExplosivesRobot.Direction.RIGHT);
        wait(350);

        robot.stop();
        wait(500);

        robot.drive(1.0);
        wait(1000);

        robot.stop();
        wait(500);

        robot.drive(-0.75);
        wait(500);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
