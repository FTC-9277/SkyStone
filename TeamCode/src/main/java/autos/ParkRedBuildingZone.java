package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Park - Red Building Zone")
public class ParkRedBuildingZone extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        robot.drive(0.5);
        wait(750);

        robot.strafe(0.5, ExplosivesRobot.Direction.LEFT);
        wait(500);

        robot.drive(0.5);
        wait(500);

        robot.stop();
    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
