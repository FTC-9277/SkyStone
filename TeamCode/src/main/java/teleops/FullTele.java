package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.IOException;

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
        robot.leftI.setPosition(0.44);
        robot.rightI.setPosition(0.49);
//        sampler = new Sampler(this);

        initalLeftLiftEncoder = robot.leftLift.getCurrentPosition();
        initalRightLiftEncoder = robot.rightLift.getCurrentPosition();
    }

//    int vision = -12;
//    Sampler sampler;

    int leftLiftTarget = 0;
    int rightLiftTarget = 0;

    boolean pressed=false;
    boolean triggerPressed=false;

    final int DIVISOR=50;

    double initG=0.0;

    int initalLeftLiftEncoder=0;
    int initalRightLiftEncoder=0;

    @Override
    public void loop() {

        if(Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot.fright.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.bright.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.fleft.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) - gamepad1.right_stick_x);
            robot.bleft.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) - gamepad1.right_stick_x);
        } else if (gamepad1.left_trigger>0.1) {
            robot.strafe(1.0, ExplosivesRobot.Direction.LEFT);
        } else if (gamepad1.right_bumper) {
            robot.strafe(1.0, ExplosivesRobot.Direction.RIGHT);
        } else {
            robot.stop();
        }

        if(gamepad1.left_trigger>0.1) {
            if(triggerPressed) {
                //LOOP
                double speed=gamepad1.left_trigger;
                double targetSpeed = -0.8*speed;
                double diff = robot.gyro()-initG;
                telemetry.addData("GYRO: ", robot.gyro());
                telemetry.addData("DIFF: ", diff);
                if(diff > 0) {
                    robot.fright.setPower(((targetSpeed) - Math.abs(diff/DIVISOR)));
                    robot.bright.setPower(((-targetSpeed) - Math.abs(diff/DIVISOR)));
                    robot.fleft.setPower(((-targetSpeed) + Math.abs(diff/DIVISOR)));
                    robot.bleft.setPower(((targetSpeed) + Math.abs(diff/DIVISOR)));
                    telemetry.addData("fright",robot.fright.getPower());
                    telemetry.addData("fleft",robot.fleft.getPower());
                    telemetry.addData("bright",robot.bright.getPower());
                    telemetry.addData("bleft",robot.bleft.getPower());
                } else {
                    robot.fright.setPower(((targetSpeed) + Math.abs(diff/DIVISOR)));
                    robot.bright.setPower(((-targetSpeed) + Math.abs(diff/DIVISOR)));
                    robot.fleft.setPower(((-targetSpeed) - Math.abs(diff/DIVISOR)));
                    robot.bleft.setPower(((targetSpeed) - Math.abs(diff/DIVISOR)));
                    telemetry.addData("fright",robot.fright.getPower());
                    telemetry.addData("fleft",robot.fleft.getPower());
                    telemetry.addData("bright",robot.bright.getPower());
                    telemetry.addData("bleft",robot.bleft.getPower());
                }
                telemetry.update();
            } else {
                //INIT
                triggerPressed=true;
                initG = robot.gyro();
            }
        } else if (gamepad1.right_trigger>0.1) {
//            if(gamepad1.left_trigger>0.1) {
            if(triggerPressed) {
                //LOOP
                double speed=-gamepad1.right_trigger;
                double targetSpeed = -0.8*speed;
                double diff = robot.gyro()-initG;
                telemetry.addData("GYRO: ", robot.gyro());
                telemetry.addData("DIFF: ", diff);
                if(diff > 0) {
                    robot.fright.setPower(((targetSpeed) - Math.abs(diff/DIVISOR)));
                    robot.bright.setPower(((-targetSpeed) - Math.abs(diff/DIVISOR)));
                    robot.fleft.setPower(((-targetSpeed) + Math.abs(diff/DIVISOR)));
                    robot.bleft.setPower(((targetSpeed) + Math.abs(diff/DIVISOR)));
                    telemetry.addData("fright",robot.fright.getPower());
                    telemetry.addData("fleft",robot.fleft.getPower());
                    telemetry.addData("bright",robot.bright.getPower());
                    telemetry.addData("bleft",robot.bleft.getPower());
                } else {
                    robot.fright.setPower(((targetSpeed) + Math.abs(diff/DIVISOR)));
                    robot.bright.setPower(((-targetSpeed) + Math.abs(diff/DIVISOR)));
                    robot.fleft.setPower(((-targetSpeed) - Math.abs(diff/DIVISOR)));
                    robot.bleft.setPower(((targetSpeed) - Math.abs(diff/DIVISOR)));
                    telemetry.addData("fright",robot.fright.getPower());
                    telemetry.addData("fleft",robot.fleft.getPower());
                    telemetry.addData("bright",robot.bright.getPower());
                    telemetry.addData("bleft",robot.bleft.getPower());
                }
                telemetry.update();
            } else {
                //INIT
                triggerPressed=true;
                initG = robot.gyro();
            }
        } else {
            triggerPressed=false;
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


        if(gamepad2.y) {
            robot.outtake();
        } else if (gamepad2.a) {
            robot.intake();
        }

        if(gamepad2.x) {
//            robot.intake.setPosition();
        }

        if(Math.abs(gamepad2.right_stick_y)>0.2) {
            robot.leftLift.setPower(gamepad2.right_stick_y * 0.5);
            robot.rightLift.setPower(-gamepad2.right_stick_y * 0.5);
        } else {
            robot.leftLift.setPower(0.0);
            robot.rightLift.setPower(0.0);
        }


        if(gamepad2.dpad_left && pressed==false) {
            pressed=true;

            robot.intake.setPosition(0.5);

            wait(200);

            robot.leftI.setPosition(1.0);
            robot.rightI.setPosition(-0.1);

            wait(600);

            robot.intake.setPosition(1.0);


        } else if (gamepad2.dpad_right && pressed==false) {
            pressed=true;

            robot.leftI.setPosition(0.43);
            robot.rightI.setPosition(0.5);

            wait(200);

            robot.intake.setPosition(0.5);

        } else {
            pressed=false;
        }


//        if(Math.abs(robot.leftLift.getCurrentPosition())-Math.abs(leftLiftTarget) > 50) {
//            if(robot.leftLift.getCurrentPosition()>leftLiftTarget) {
//                robot.leftLift.setPower();
//            }
//        }

        telemetry.addData("left lift", robot.leftLift.getCurrentPosition());
        telemetry.addData("right lift", robot.rightLift.getCurrentPosition());
        telemetry.addData("intakeROUNDER", robot.intake.getPosition());
        telemetry.addData("left intake",robot.leftI.getPosition());
        telemetry.addData("right intake",robot.rightI.getPosition());
        telemetry.update();


        if(gamepad2.right_bumper) {
            robot.liftIntake();
        } else if (gamepad2.left_bumper) {
            robot.dropIntake();
        }
    }

    public void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
