package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Driving Test", group = "other")
public class DrivingTest extends LinearOpMode {

    ExplosivesRobot robot = new ExplosivesRobot(this);

    @Override
    public void runOpMode() throws InterruptedException {
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
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

//        robot.driveTime(0.5,2000);
//
//        wait(1000);
//
//        robot.driveTime(-0.5,2000);

        long start = System.currentTimeMillis();

        while(start+5000 > System.currentTimeMillis()) {
            telemetry.addData("Gyro", robot.gyro());
            telemetry.update();
        }

        robot.turnToAngle(0.5,0);

//        wait(500);
//
//        robot.turnToAngle(0.5,45);
//
//        wait(500);
//
//        robot.turnToAngle(0.5,90);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
