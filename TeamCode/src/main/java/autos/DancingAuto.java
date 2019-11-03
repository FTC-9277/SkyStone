package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "Dance", group = "other")
public class DancingAuto extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        wait(1000);

//        for (int i = 0; i < 4; i++) {
//            robot.drive(0.5);
//            wait(500);
//            robot.stop();
//            wait(500);
//
//            robot.drive(-0.5);
//            wait(500);
//            robot.stop();
//            wait(500);
//        }
//
//        for (int i = 0; i < 4; i++) {
//            robot.strafe(0.5, ExplosivesRobot.Direction.LEFT);
//            wait(500);
//            robot.stop();
//            wait(500);
//
//            robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
//            wait(500);
//            robot.stop();
//            wait(500);
//        }
//
//        for (int i = 0; i < 2; i++) {
//            robot.turn(0.5, ExplosivesRobot.Direction.LEFT);
//            wait(500);
//            robot.stop();
//            wait(500);
//
//            robot.turn(0.5, ExplosivesRobot.Direction.RIGHT);
//            wait(500);
//            robot.stop();
//            wait(500);
//
//            robot.turn(0.5, ExplosivesRobot.Direction.RIGHT);
//            wait(500);
//            robot.stop();
//            wait(500);
//
//            robot.turn(0.5, ExplosivesRobot.Direction.LEFT);
//            wait(500);
//            robot.stop();
//            wait(500);
//        }

        for(int i = 0; i < 5; i++) {

            robot.drive(0.5);
            wait(300);
            robot.stop();

            robot.drive(-0.5);
            wait(300);
            robot.stop();

            robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
            wait(1000);
            robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
            wait(1000);

            robot.stop();

            wait(1000);


            robot.drive(0.5);
            wait(300);
            robot.stop();

            robot.drive(-0.5);
            wait(300);
            robot.stop();

            robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
            wait(1000);
            robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
            wait(1000);

            robot.stop();

            wait(1000);

        }

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
