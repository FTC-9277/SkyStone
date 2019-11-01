package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "WORKING Far left Stone Grab")
public class StoneGrab extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();

        waitForStart();

        robot.drive(0.6);
        wait(1500);

        robot.stop();
        wait(500);

        robot.turn(0.8, ExplosivesRobot.Direction.LEFT);
        wait(1150);

        robot.stop();
        wait(500);

        robot.drive(1.0);
        wait(1250);

        robot.stop();
        wait(500);

        robot.turn(0.5, ExplosivesRobot.Direction.RIGHT);
        wait(500);

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
