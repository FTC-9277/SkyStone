package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Vision.Sampler;
import recorder.Player;
import robot.ExplosivesRobot;

@TeleOp(name = "FullTeleOp")
public class FullTele extends OpMode {

    ExplosivesRobot robot;

    @Override
    public void init() {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();
        robot.leftI.setPosition(-1.0);
        robot.rightI.setPosition(1.0);
//        sampler = new Sampler(this);
    }

//    int vision = -12;
//    Sampler sampler;

    int leftLiftTarget = 0;
    int rightLiftTarget = 0;

    @Override
    public void loop() {

        if(Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot.fright.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.bright.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.fleft.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) - gamepad1.right_stick_x);
            robot.bleft.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) - gamepad1.right_stick_x);
        } else {
            robot.stop();
        }

        if(gamepad1.dpad_up) {
            robot.unhook();
        } else if (gamepad1.dpad_down) {
            robot.hook();
        }

        if(gamepad2.dpad_down) {
            robot.liftCapstone();
        } else if (gamepad2.dpad_up) {
            robot.dropCapstone();
        }

        if(Math.abs(gamepad2.left_stick_y) > 0.1) {
            if(gamepad2.left_stick_y>0) {
//                if(leftLiftTarget>0) {
                    leftLiftTarget -= 10;
//                }
            } else {
                leftLiftTarget += 10;
            }
        }

        //Get leftLift to position
//        if(Math.abs(robot.leftLift.getCurrentPosition())-Math.abs(leftLiftTarget)>10) {
            robot.leftLift.setPower(-(robot.leftLift.getCurrentPosition() - leftLiftTarget) / 100.0);
//        }

        if(Math.abs(gamepad2.left_stick_y) > 0.1) {
            if(gamepad2.left_stick_y>0) {
//                if(leftLiftTarget>0) {
                rightLiftTarget -= 10;
//                }
            } else {
                rightLiftTarget += 10;
            }
        }

        //Get rightLift to position
//        if(Math.abs(robot.rightLift.getCurrentPosition())-Math.abs(rightLiftTarget)>10) {
            robot.rightLift.setPower((robot.rightLift.getCurrentPosition() - rightLiftTarget) / 100.0);
//        }

        telemetry.addData("LeftLift encoder",robot.leftLift.getCurrentPosition());
        telemetry.addData("LTarget",leftLiftTarget);
        telemetry.addData("LPower",-(robot.leftLift.getCurrentPosition()-leftLiftTarget)/100.0);
        telemetry.addData("RightLift encoder",robot.rightLift.getCurrentPosition());
        telemetry.addData("RTarget",rightLiftTarget);
        telemetry.addData("RPower",(robot.rightLift.getCurrentPosition()-rightLiftTarget)/100.0);
        telemetry.update();


//        if (Math.abs(gamepad2.right_stick_y) > 0.1) {
//            robot.rightLift.setPower(gamepad2.right_stick_y);
//        } else {
//            robot.rightLift.setPower(0.0);
//        }

        if(gamepad2.y) {
            robot.outtake();
        } else if(gamepad2.a) {
            robot.intake();
        } else {
            robot.lintake.setPower(0.0);
            robot.rintake.setPower(0.0);
        }


        if(gamepad2.right_bumper) {
            robot.dropIntake();
        } else if (gamepad2.left_bumper) {
            robot.liftIntake();
        }
    }
}
