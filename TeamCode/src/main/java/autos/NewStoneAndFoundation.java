package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import Vision.Sampler;
import robot.ExplosivesRobot;

@Autonomous(name = "BLUE Foundation and stone", group = "both")
public class NewStoneAndFoundation extends LinearOpMode {

    ExplosivesRobot robot;
    Sampler sample;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();
        sample = new Sampler(this);

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
        wait(1500);

        robot.stop();
        wait(100);

        robot.driveTime(1.0,2000);

        robot.driveTime(-1.0,750);

//        robot.turnToAngle(1.0,90);
        robot.gyroTurn(1.0,100);

        robot.driveTime(-1.0,2000);

//        robot.gyroTurn(1.0,70);
        robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
        wait(750);

        robot.stop();

        int stone = 1;

        stone = sample.sample();

//        robot.driveTime(0.5,750);

        wait(100);

        int sampleCount = 0;

        while(opModeIsActive()) {
            telemetry.addData("EYESSSSS: ", stone);
            telemetry.update();

            stone = sample.sample();
            sampleCount++;
            wait(100);

            if(stone == 0) {
                robot.fright.setPower(0.3);
                robot.bright.setPower(-0.3);
                robot.fleft.setPower(-0.3);
                robot.bleft.setPower(0.3);
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

        robot.leftLift.setPower(0.5);
        robot.rightLift.setPower(0.5);

        wait(500);

        robot.leftLift.setPower(0.0);
        robot.rightLift.setPower(0.0);

        wait(250);

        robot.driveTime(1.0,1250);

        wait(100);

        robot.gyroTurn(1.0,180);

//        robot.gyroTurn(1.0,100);

        wait(100);

        robot.driveTime(1.0,650);

        wait(100);

//        robot.gyroTurn(1.0,290);
        robot.turn(1.0, ExplosivesRobot.Direction.LEFT);
        wait(750);

//        wait(100);

        robot.driveTime(1.0, (125*sampleCount+2000));

        wait(100);

        robot.driveTime(-1.0,(100*sampleCount+400));

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
