package teleops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import robot.Gyro;

@TeleOp(name = "Sensor Debug")
public class SensorDebug extends OpMode {

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
