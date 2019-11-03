package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import robot.ExplosivesRobot;

//@TeleOp(name = "Intake Test")
public class IntakeTest extends OpMode {

    DcMotor intake = null;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class,"intake");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.right_trigger) > 0.2) {
            intake.setPower(gamepad1.right_trigger);
        } else if (Math.abs(gamepad1.left_trigger) > 0.2) {
            intake.setPower(-gamepad1.left_trigger);
        } else {
            intake.setPower(0.0);
        }
    }
}
