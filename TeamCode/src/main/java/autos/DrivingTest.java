package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Driving Straight")
public class DrivingTest extends LinearOpMode {

    ExplosivesRobot robot = new ExplosivesRobot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        wait(1000);

        robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);

        wait(5000);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
