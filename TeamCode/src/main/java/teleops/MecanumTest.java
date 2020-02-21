package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.ExplosivesRobot;

@TeleOp(name = "Strafe Test")
public class MecanumTest extends OpMode {

    ExplosivesRobot robot;

    @Override
    public void init() {
        robot = new ExplosivesRobot(this);
        robot.init();
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_y) > 0.1 ) {
            robot.fleft.setPower(gamepad1.left_stick_y);
            robot.fright.setPower(gamepad1.right_stick_y);
            robot.bleft.setPower(gamepad1.right_stick_y);
            robot.bright.setPower(gamepad1.left_stick_y);
        } else {
            robot.stop();
        }
    }
}
