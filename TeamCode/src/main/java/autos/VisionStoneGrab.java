package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Vision.Sampler;
import robot.ExplosivesRobot;

//@Autonomous(name = "Vision Stone Grab", group = "other")
public class VisionStoneGrab extends LinearOpMode {

    ExplosivesRobot robot;
    Sampler sample;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();
        sample = new Sampler(this);

        waitForStart();

        int stone = 1;

        if(sample.sample()) {
            telemetry.addData("Vision: ", true);
            stone = 1;
        } else {
//            telemetry.addData("Vision: ", false);
//            robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
//            wait(500);
//
//            robot.stop();
//
//            if(sample.sample()) {
//                stone = 2;
//            } else {
//                robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
//                wait(500);
//
//                robot.stop();
//
//                if(sample.sample()) {
//                    stone = 3;
//                }
//            }
            robot.drive(-0.5);
            wait(5000);
        }

        robot.drive(0.6);
        wait(1500);

        robot.stop();
        wait(500);

        robot.turn(0.8, ExplosivesRobot.Direction.LEFT);
        wait(2000);

        robot.stop();
        wait(500);

        robot.drive(0.5);
        wait(500);

        robot.stop();
        wait(500);

        robot.turn(0.5, ExplosivesRobot.Direction.RIGHT);
        wait(1000);

        robot.stop();
        wait(500);

        robot.drive(1.0);
        wait(2500);

        robot.stop();
        wait(500);

        robot.drive(-0.5);
        wait(1000);

        robot.stop();
        wait(500);

        robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
        wait(500);

        robot.stop();
        wait(500);

        robot.drive(-1.0);
        wait(400);

        robot.stop();
    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
