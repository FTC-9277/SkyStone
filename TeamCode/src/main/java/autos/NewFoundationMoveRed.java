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

        robot.drive(-0.5);
        wait(1250);

        robot.stop();

        robot.hook();
        wait(1000);

//        robot.drive(0.8);
        robot.lDrive(-1.0);
        robot.rDrive(-0.3);
        wait(1700);

        robot.stop();
        wait(500);

        robot.unhook();
        wait(1000);

        robot.strafe(0.5, ExplosivesRobot.Direction.RIGHT);
        wait(3000);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
