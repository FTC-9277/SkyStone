package teleops;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robot.Gyro;

@TeleOp(name = "Sensor Debug")
public class SensorDebug extends OpMode {

    Gyro gyro;
    ModernRoboticsI2cRangeSensor sonicTheFish;
    DcMotor motor;

    @Override
    public void init() {
        gyro = new Gyro(this);
        sonicTheFish = hardwareMap.get(ModernRoboticsI2cRangeSensor.class,"sonic");
//        motor = hardwareMap.get(DcMotor.class,"fright");
    }

    @Override
    public void loop() {
        telemetry.addData("Heading: ", gyro.heading());
        telemetry.addData("Roll: ", gyro.roll());
        telemetry.addData("Pitch: ", gyro.pitch());
        telemetry.addData("Sonic the fish: ", sonicTheFish.getDistance(DistanceUnit.INCH));
//        telemetry.addData("Encoder", motor.getCurrentPosition());
        telemetry.update();
    }
}
