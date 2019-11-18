package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.ExplosivesRobot;

@Autonomous(name = "TicksTest", group = "other")
public class TicksTest extends LinearOpMode {

    ExplosivesRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();

        waitForStart();

        wait(1000);

        robot.driveTicks(0.5,2000);

        wait(500);

        robot.driveTicks(-0.5,-2000);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
