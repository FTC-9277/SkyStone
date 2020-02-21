package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robot.ExplosivesRobot;

@Autonomous(name = "RED ONLY FOUNDATION - MIDPARK", group = "foundation")
public class OnlyFoundationRedMidpark extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();

        waitForStart();

        robot.driveTime(-1.0,500);

        robot.stop();
        wait(100);

        robot.strafe(0.3, ExplosivesRobot.Direction.LEFT);

        while(robot.sonicTheWarthog.getDistance(DistanceUnit.INCH) > 12) {
            telemetry.addData("sonic",robot.sonicTheWarthog.getDistance(DistanceUnit.INCH));
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

        robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
        wait(2000);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,550);

        robot.stop();
        wait(100);

        robot.unhook();
        wait(1000);

        robot.strafeStraight(1.0, ExplosivesRobot.Direction.RIGHT,250);

        robot.driveTime(1.0,2000);

        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
