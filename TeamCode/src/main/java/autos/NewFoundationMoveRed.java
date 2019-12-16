package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robot.ExplosivesRobot;

<<<<<<< HEAD
@Autonomous(name = "RED MID PARK - Found. Move (face forward)", group = "foundation")
public class NewFoundationMoveRed extends LinearOpMode {
=======
@Autonomous(name = "Red - Found. Move", group = "foundation")
public class    NewFoundationMoveRed extends LinearOpMode {
>>>>>>> origin/master

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

<<<<<<< HEAD
        robot.driveTime(1.0,750);

        robot.stop();
        wait(100);

        robot.strafe(0.3, ExplosivesRobot.Direction.RIGHT);

        while(robot.sonicTheFish.getDistance(DistanceUnit.INCH) > 20.0) {
            telemetry.addData("sonic",robot.sonicTheFish.getDistance(DistanceUnit.INCH));
            telemetry.update();
            //wait
        }

        robot.stop();
        wait(100);

        robot.gyroTurn(1.0,180);
=======

        robot.driveTime(-1.0, 1500);
        wait(1500);
>>>>>>> origin/master

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,500);

        wait(100);

        robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        wait(500);
        robot.stop();
        wait(500);

        robot.hook();
        wait(2500);

<<<<<<< HEAD
        robot.driveTime(1.0, 1750);
=======
        robot.driveTime(0.75, 2000);
        wait(500);
        robot.stop();

        wait(100);

        robot.unhook();
        wait(1000);

        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(1500);
>>>>>>> origin/master

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,500);

        robot.stop();
        wait(650);

        robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        wait(1000);

<<<<<<< HEAD
        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(1250);

        robot.stop();
        wait(100);

        robot.driveTime(1.0,2250);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,2000);
=======
        robot.stop();
        wait(500);

        robot.strafe(.25, ExplosivesRobot.Direction.RIGHT);
        wait(800);

        robot.stop();
        wait(500);

        robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
        wait(440);

        robot.stop();
        wait(500);

        robot.driveTime(-1.0,1000);
>>>>>>> origin/master

        robot.stop();
        wait(100);

        robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        wait(1000);

        robot.stop();
        wait(100);

        robot.driveTime(0.5,3000);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,350);

        robot.stop();
        wait(100);

        robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(750);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,2250);

        robot.stop();


    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
