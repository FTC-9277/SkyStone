package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Turning Test", group = "other")
public class TurningTest extends LinearOpMode {

    ExplosivesRobot robot = null;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();

        waitForStart();

        wait(1000);

        robot.gyroTurn(1.0,300);
    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
