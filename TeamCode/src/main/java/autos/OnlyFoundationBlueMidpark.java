package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robot.ExplosivesRobot;

@Autonomous(name = "BLUE ONLY FOUNDATION - MIDPARK", group = "foundation")
public class OnlyFoundationBlueMidpark extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();

        waitForStart();

        robot.driveTime(-1.0,500);

        robot.stop();
        wait(100);

        robot.strafe(0.3, ExplosivesRobot.Direction.RIGHT);

        while(robot.sonicTheFish.getDistance(DistanceUnit.INCH) > 8.0) {
            telemetry.addData("sonic",robot.sonicTheFish.getDistance(DistanceUnit.INCH));
            telemetry.update();
            //wait
        }

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,550);

        robot.hook();
        wait(1250);

        robot.driveTime(1.0, 1750);

        robot.stop();
        wait(100);

        robot.unhook();
        wait(1000);

        robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        wait(1250);

        robot.stop();
        wait(100);

        robot.driveTime(1.0,2000);

        robot.driveTime(-1.0,750);

        robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        wait(800);



        robot.stop();

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
