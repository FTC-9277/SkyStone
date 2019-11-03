package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Red - Stone Grab - Wall Park", group = "stone")
public class StoneGrabRedWallpark extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();

        waitForStart();

        robot.drive(0.7);
        wait(1500);

        robot.stop();
        wait(500);

        robot.turn(0.8, ExplosivesRobot.Direction.RIGHT);
        wait(1100);

        robot.stop();
        wait(500);

        robot.drive(1.0);
        wait(300);

        robot.stop();
        wait(500);

        robot.turn(0.5, ExplosivesRobot.Direction.LEFT);
        wait(500);

        robot.stop();
        wait(500);

        robot.drive(0.5);
        wait(500);

        robot.stop();
        wait(500);

        robot.drive(0.5);
        wait(1000);

        robot.stop();
        wait(500);

        robot.drive(-0.5);
        wait(500);

        robot.stop();
        wait(500);

        robot.strafe(0.5, ExplosivesRobot.Direction.LEFT);
        wait(1000);

        robot.stop();
    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
