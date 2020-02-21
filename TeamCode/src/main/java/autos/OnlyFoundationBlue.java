package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robot.ExplosivesRobot;

@Autonomous(name = "BLUE ONLY FOUNDATION - WALLPARK", group = "foundation")
public class OnlyFoundationBlue extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();

        waitForStart();

        robot.driveTime(-1.0,750);

        robot.stop();
        wait(100);

        robot.strafe(0.3, ExplosivesRobot.Direction.RIGHT);

        while(robot.sonicTheFish.getDistance(DistanceUnit.INCH) > 12) {
            telemetry.addData("sonic",robot.sonicTheFish.getDistance(DistanceUnit.INCH));
            telemetry.update();
            //wait
        }

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,550);

        robot.hook();
        wait(1250);

        robot.driveTime(1.0, 1000);

        robot.stop();
        wait(100);

        robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(2350);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,100);

        robot.stop();
        wait(100);

        robot.unhook();
        wait(1000);

        robot.strafeStraight(1.0, ExplosivesRobot.Direction.RIGHT,1500);

        robot.strafeStraight(1.0, ExplosivesRobot.Direction.LEFT,50);

        robot.driveTime(1.0,2250);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
