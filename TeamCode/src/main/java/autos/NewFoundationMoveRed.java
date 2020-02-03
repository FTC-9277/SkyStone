package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robot.ExplosivesRobot;

@Autonomous(name = "RED MID PARK - Found. Move (face forward)", group = "foundation")
public class NewFoundationMoveRed extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        robot.driveTime(1.0,750);

        robot.stop();
        wait(100);

        robot.strafe(0.3, ExplosivesRobot.Direction.RIGHT);

        while(robot.sonicTheFish.getDistance(DistanceUnit.INCH) > 15.0) {
            telemetry.addData("sonic",robot.sonicTheFish.getDistance(DistanceUnit.INCH));
            telemetry.update();
            //wait
        }

        robot.stop();
        wait(100);

        robot.gyroTurn(1.0,180);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,450);

        robot.hook();
        wait(1000);

        robot.driveTime(1.0, 25000);

        robot.stop();
        wait(100);

        robot.unhook();
        wait(1750);

        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(1250);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,2250);
//
//        robot.stop();
//        wait(100);

//        robot.driveTime(1.0,2000);

        robot.stop();
        wait(100);

        robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
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

        robot.driveTime(-1.0,1250);

        robot.stop();


    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
