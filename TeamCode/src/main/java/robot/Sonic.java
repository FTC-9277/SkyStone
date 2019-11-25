package robot;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;

public class Sonic {

    ModernRoboticsI2cRangeSensor sensor;

    HardwareMap hardwareMap;

    private boolean enabled;

    public boolean isUpdating() {
        return this.enabled;
    }

    public Sonic(HardwareMap hardwareMap) {
        this.hardwareMap=hardwareMap;
        sensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class,"sonic");
        resumeUpdates();
    }

    public void resumeUpdates() {
        enabled = true;
        loop();
    }

    ArrayList<Double> sonicValues = new ArrayList<>();

    private DistanceUnit unit = DistanceUnit.CM;

    public DistanceUnit getUnit() {
        return unit;
    }

    public void setUnit(DistanceUnit unit) {
        this.unit = unit;
    }

    //The difference between the current and last sonic value has to be less than this number else the current will be ignored
    final private double THRESHOLD = 10.0;

    final private int REFRESH_RATE = 100;



    public void loop() {
        long start = System.currentTimeMillis();

        sonicValues.add(current());

        while(System.currentTimeMillis()+start < REFRESH_RATE) {
            //Wait
        }

        if(enabled) {
            loop();
        }
    }

    public double getValue() {
        if(sonicValues.size() <= 2) {
            return current();
        }
        double last = sonicValues.get(sonicValues.size()-1);
        double current = sensor.getDistance(unit);

        if(Math.abs(last-current) > THRESHOLD) {
            return last;
        } else {
            return current;
        }
    }

    private double current() {
        return sensor.getDistance(unit);
    }

}
