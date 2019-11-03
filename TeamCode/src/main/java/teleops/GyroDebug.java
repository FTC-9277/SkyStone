package teleops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.Gyro;

//@TeleOp(name = "Gyro Debug")
public class GyroDebug extends OpMode {

    Gyro gyro;

    @Override
    public void init() {
        gyro = new Gyro(this);
    }

    @Override
    public void loop() {
        telemetry.addData("Heading: ", gyro.heading());
        telemetry.addData("Roll: ", gyro.roll());
        telemetry.addData("Pitch: ", gyro.pitch());
        telemetry.update();
    }
}
