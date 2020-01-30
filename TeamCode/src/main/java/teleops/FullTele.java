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
        robot.leftI.setPosition(0.44);
        robot.rightI.setPosition(0.49);
//        sampler = new Sampler(this);
    }

//    int vision = -12;
//    Sampler sampler;

    int leftLiftTarget = 0;
    int rightLiftTarget = 0;

    boolean pressed=false;

    @Override
    public void loop() {

        if(Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot.fright.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.bright.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) + gamepad1.right_stick_x + 0.4);
            robot.fleft.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) - gamepad1.right_stick_x);
            robot.bleft.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) - gamepad1.right_stick_x + 0.4);
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



        if(gamepad2.y) {
            robot.outtake();
        } else if (gamepad2.a) {
            robot.intake();
        }

        if(gamepad2.x) {
//            robot.intake.setPosition();
        }

        if(Math.abs(gamepad2.right_stick_y)>0.2) {
            robot.leftLift.setPower(gamepad2.right_stick_y*0.5);
            robot.rightLift.setPower(-gamepad2.right_stick_y*0.5);
        } else {
            robot.leftLift.setPower(0.0);
            robot.rightLift.setPower(0.0);
        }


        if(gamepad2.dpad_left && pressed==false) {
            pressed=true;

            robot.intake();

            robot.leftI.setPosition(0.9);
            robot.rightI.setPosition(0.0);

            long start = System.currentTimeMillis();
            while(start+200 > System.currentTimeMillis()) {
            //wait
            }
            robot.outtake();


        } else if (gamepad2.dpad_right && pressed==false) {
            pressed=true;

            robot.outtake();

            long start = System.currentTimeMillis();
            while(start+2000 > System.currentTimeMillis()) {
            }
            robot.leftI.setPosition(0.43);
            robot.rightI.setPosition(0.5);

        } else {
            pressed=false;
        }


//        if(Math.abs(robot.leftLift.getCurrentPosition())-Math.abs(leftLiftTarget) > 50) {
//            if(robot.leftLift.getCurrentPosition()>leftLiftTarget) {
//                robot.leftLift.setPower();
//            }
//        }

        telemetry.addData("intakeL", robot.leftI.getPosition());
        telemetry.addData("intakeR", robot.rightI.getPosition());
        telemetry.addData("intakeROUNDER", robot.intake.getPosition());
        telemetry.update();


        if(gamepad2.right_bumper) {
            robot.liftIntake();
        } else if (gamepad2.left_bumper) {
            robot.dropIntake();
        }
    }
}
