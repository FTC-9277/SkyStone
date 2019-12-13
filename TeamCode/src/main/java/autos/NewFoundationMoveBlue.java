package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import robot.ExplosivesRobot;

@Autonomous(name = "BLUE - Found. Move", group = "foundation")
public class    NewFoundationMoveBlue extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();


        robot.driveTime(-1.0, 1500);
        wait(1500);

        robot.stop();

        wait(100);

        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(500);
        robot.stop();
        wait(500);

        robot.hook();
        wait(1000);

        robot.driveTime(0.75, 2000);
        wait(500);
        robot.stop();

        wait(100);

        robot.unhook();
        wait(1000);

        robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        wait(1500);

        robot.stop();
        wait(500);

        robot.driveTime(-1.0,500);

        robot.stop();
        wait(650);

        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(1000);

        robot.stop();
        wait(500);

        robot.strafe(.25, ExplosivesRobot.Direction.LEFT);
        wait(800);

        robot.stop();
        wait(500);

        robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(440);

        robot.stop();
        wait(500);

        robot.driveTime(-1.0,1000);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
