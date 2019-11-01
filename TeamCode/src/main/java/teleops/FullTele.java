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
        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();
//        sampler = new Sampler(this);
    }

//    int vision = -12;
//    Sampler sampler;

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

        if(Math.abs(gamepad2.left_stick_y) > 0.2) {
            if(gamepad2.left_stick_y < 0) {
                robot.intakeDown();
            } else {
                robot.intakeUp();
            }
        }

//        if(Math.abs(gamepad2.right_stick_y) > 0.2) {
//            robot.intake.setPower(gamepad2.right_stick_y);
//        } else {
//            robot.intake.setPower(0.0);
//        }

    }
}
