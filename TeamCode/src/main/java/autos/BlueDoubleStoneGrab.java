package autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Vision.Sampler;
import robot.ExplosivesRobot;

//@Autonomous(name = "BLUE - Double Stone Grab", group = "stone")
public class BlueDoubleStoneGrab extends LinearOpMode {

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

        robot.driveTime(0.5,850);

        wait(500);

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

        robot.fright.setPower(0.3);
        robot.bright.setPower(-0.3);
        robot.fleft.setPower(-0.3);
        robot.bleft.setPower(0.3);
        wait(50);

        robot.stop();
//        wait(500);

        robot.driveTime(0.75,1000);

//        wait(500);

        robot.gyroTurn(0.8,200);

//        wait(500);

        robot.driveTime(0.8,600);

//        wait(500);

        robot.gyroTurn(0.8,83);

//        wait(500);

        robot.driveTime(1.0, (175*sampleCount+750));

//        wait(500);

        robot.driveTime(-1.0,(175*sampleCount)+2300);

//        wait(500);

        robot.gyroTurn(0.8,90);

//        sampleCount = 0;
//
//        while(opModeIsActive()) {
//            telemetry.addData("EYESSSSS: ", stone);
//            telemetry.update();
//
//            stone = sample.sample();
//            sampleCount++;
//            wait(100);
//
//            if(stone == 0) {
//                robot.fright.setPower(0.3);
//                robot.bright.setPower(-0.3);
//                robot.fleft.setPower(-0.3);
//                robot.bleft.setPower(0.3);
//            } else {
//                break;
//            }
//        }

        robot.stop();
//        wait(500);

        robot.driveTime(0.75,1000);

//        wait(500);

        robot.gyroTurn(0.8,200);

//        wait(500);

        robot.driveTime(0.8,1000);

//        wait(500);

        robot.gyroTurn(0.8,80);

//        wait(500);

        robot.driveTime(1.0, (175*sampleCount+2500));

//        wait(500);

        robot.driveTime(-0.75,1500);

    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
