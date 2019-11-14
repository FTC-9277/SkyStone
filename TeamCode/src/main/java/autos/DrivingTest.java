package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Driving Test", group = "other")
public class DrivingTest extends LinearOpMode {

    ExplosivesRobot robot = new ExplosivesRobot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        wait(1000);



//        robot.gyroTurn(0.5,90);
//
//        wait(2000);
//
//        robot.gyroTurn(0.5,-90);
//
//        wait(2000);
//
//        robot.gyroTurn(0.5,180);
//
//        wait(2000);
//
//        robot.gyroTurn(0.5,-180);

        robot.driveS(0.5,8000);

        wait(1000);

        robot.driveS(-0.5,8000);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
