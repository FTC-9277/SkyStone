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

//        long start = System.currentTimeMillis();
//
//        while(start+5000 > System.currentTimeMillis()) {
//            telemetry.addData("Gyro", robot.gyro());
//            telemetry.update();
//        }
//
//        robot.strafeStraight(0.5,4000);

//        wait(500);
//
//        robot.gyroTurn(1.0,90);
////
//        wait(1000);
//
//        robot.gyroTurn(1.0,180);
//
//        wait(1000);
//
//        robot.gyroTurn(1.0,270);
//
//        robot.turnToAngle(0.5,90);

        robot.strafeStraight(1.0, ExplosivesRobot.Direction.LEFT,3000);

        wait(1000);

        robot.strafeStraight(1.0, ExplosivesRobot.Direction.RIGHT,3000);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
