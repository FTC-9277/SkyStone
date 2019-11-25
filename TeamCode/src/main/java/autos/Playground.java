package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Playground", group = "other")
public class Playground extends LinearOpMode {

    ExplosivesRobot robot = new ExplosivesRobot(this);

    @Override
    public void runOpMode() throws InterruptedException {
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        wait(1000);

//        robot.driveS(1.0,1500);

        wait(500);

        robot.gyroTurn(1.0,90);

        wait(500);

//        robot.driveS(-1.0,3000);

        wait(500);

        robot.gyroTurn(1.0,270);

        wait(500);

//        robot.driveS(1.0,1500);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
