package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

import recorder.RecordMotor;
import recorder.Recorder;
import robot.SmolBoi;

@TeleOp(name = "Smol Boi")
public class SmolBoiTeleOp extends OpMode {

    SmolBoi robot = new SmolBoi(this);
    Recorder recorder = new Recorder();

    @Override
    public void init() {
        robot.init();

        ArrayList<RecordMotor> motors = new ArrayList<RecordMotor>() {{
            add(new RecordMotor("left", robot.left));
//            add(new RecordMotor("bright", robot.bright));
//            add(new RecordMotor("fleft", robot.fleft));
//            add(new RecordMotor("fright", robot.fright));
//            add(new RecordMotor("bleft", robot.bleft));
        }};

        recorder.record("first", motors);
    }

    @Override
    public void loop() {

        if(Math.abs(gamepad1.left_stick_y) > 0.1) {
            robot.left.setPower(gamepad1.left_stick_y);
        } else {
            robot.left.setPower(0.0);
        }

        if(Math.abs(gamepad1.right_stick_y) > 0.1) {
            robot.right.setPower(gamepad1.right_stick_y);
        } else {
            robot.right.setPower(0.0);
        }



    }

}
