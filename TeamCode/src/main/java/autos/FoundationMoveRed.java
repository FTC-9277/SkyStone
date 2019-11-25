package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "RED - Untested Foundation Move", group = "foundation")
public class FoundationMoveRed extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        robot.drive(-0.5);
        wait(1250);

        robot.stop();

        robot.hook();
        wait(1000);

        robot.drive(0.5);
        wait(1250);

        robot.stop();

        robot.unhook();
        wait(1000);

        robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
        wait(1900);

        robot.stop();
        wait(500);

        robot.drive(-0.6);
        wait(1500);

        robot.stop();
        wait(500);

        robot.strafe(0.5, ExplosivesRobot.Direction.LEFT);
        wait(1250);

        robot.stop();
        wait(500);

        robot.drive(0.5);
        wait(2500);

        robot.stop();
        wait(500);

        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(2000);

        robot.stop();


    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
