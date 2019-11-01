package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "RED - Untested Found. Move")
public class NewFoundationMoveRed extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
        wait(1500);
        robot.stop();

        wait(100);

        robot.hook();
        wait(1000);

        robot.turn(0.5, ExplosivesRobot.Direction.LEFT);
        wait(1000);

        robot.drive(-0.5);
        wait(1500);

        robot.stop();

        robot.unhook();
        wait(1000);

        robot.drive(0.5);
        wait(1000);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
