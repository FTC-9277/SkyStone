package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import recorder.Recorder;
import robot.ExplosivesRobot;

public class RecordTest extends OpMode {

    ExplosivesRobot robot = new ExplosivesRobot(this);

    Recorder recorder = new Recorder();

    @Override
    public void init() {
        robot.init();

//        recorder.record("");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            robot.bleft.setPower(gamepad1.left_stick_y);
            robot.bright.setPower(gamepad1.left_stick_y);
        } else {
            robot.bleft.setPower(0.0);
            robot.bright.setPower(0.0);
        }

        if(Math.abs(gamepad1.right_stick_y) > 0.1) {
            robot.fright.setPower(gamepad1.right_stick_y);
            robot.bright.setPower(gamepad1.right_stick_y);
        } else {
            robot.fright.setPower(0.0);
            robot.bright.setPower(0.0);
        }
    }
}
