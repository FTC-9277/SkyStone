package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import Vision.Sampler;
import robot.ExplosivesRobot;

@Autonomous(name = "RED - Vision Stone Grab", group = "stone")
public class RedVisionStoneGrab extends LinearOpMode {

    ExplosivesRobot robot;
    Sampler sample;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
        robot.init();
        sample = new Sampler(this);

        waitForStart();

        int stone = 1;

        stone = sample.sample();

        robot.driveTime(0.5,750);

        wait(100);

        int sampleCount = 0;

        while(opModeIsActive()) {
            telemetry.addData("EYESSSSS: ", stone);
            telemetry.update();

            stone = sample.sample();
            sampleCount++;
            wait(100);

            if(stone == 0) {
                robot.fright.setPower(-0.3);
                robot.bright.setPower(0.3);
                robot.fleft.setPower(0.3);
                robot.bleft.setPower(-0.3);
            } else {
                break;
            }
        }

//        robot.fright.setPower(0.3);
//        robot.bright.setPower(-0.3);
//        robot.fleft.setPower(-0.3);
//        robot.bleft.setPower(0.3);
//        wait(250);

        robot.stop();

        wait(500);

        //Lift
        robot.leftLift.setPower(0.6);
        robot.rightLift.setPower(0.6);

        wait(400);

        robot.leftLift.setPower(0.0);
        robot.rightLift.setPower(0.0);

        robot.driveTime(1.0,1500);

        wait(100);

        //Fall
        robot.fall();

        robot.leftI.setPosition(0.5);
        robot.rightI.setPosition(-0.5);

        wait(800);

        robot.leftLift.setPower(0.0);
        robot.rightLift.setPower(0.0);

        wait(1000);

        robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
        wait(1500);

        //here 2929929
        robot.driveTime(1.0,700);

        robot.stop();
        wait(100);

        robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(600);

        robot.stop();
        wait(100);

        robot.driveTime(1.0,2000);

        robot.stop();
        wait(100);

        robot.driveTime(-1.0,1250);


        //PARK HERE


        robot.driveTime(-1.0,1650);

        robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(800);

        robot.strafeStraight(1.0, ExplosivesRobot.Direction.LEFT, 950);

        robot.stop();

        wait(500);

        //CHANGE BASED ON SIDE COLOUR
        //warthog is left
        if(robot.sonicTheWarthog.getDistance(DistanceUnit.INCH) < 3) {
            robot.strafeStraight(1.0, ExplosivesRobot.Direction.RIGHT, 350);
        }

        //Lift
        robot.leftLift.setPower(0.6);
        robot.rightLift.setPower(0.6);

        wait(400);

        robot.leftLift.setPower(0.0);
        robot.rightLift.setPower(0.0);

        robot.driveTime(1.0,1000);

        wait(100);

        //Fall
        robot.fall();

        robot.leftI.setPosition(0.5);
        robot.rightI.setPosition(-0.5);

        wait(600);

        robot.leftLift.setPower(0.0);
        robot.rightLift.setPower(0.0);

        wait(1000);

        robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
        wait(1500);

        robot.driveTime(1.0,1150);

        robot.stop();
        wait(100);

        robot.turn(1.0, ExplosivesRobot.Direction.RIGHT);
        wait(650);

        robot.stop();
        wait(100);

        robot.driveTime(1.0,3250);

        robot.driveTime(-1.0,1250);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
