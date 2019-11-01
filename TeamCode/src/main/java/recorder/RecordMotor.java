package recorder;

import com.qualcomm.robotcore.hardware.DcMotor;


public class RecordMotor {
    private String name;
    private DcMotor motor;

    public RecordMotor(String name, DcMotor motor) {
        this.name = name;
        this.motor = motor;
    }

    public String getName() {
        return name;
    }

    public DcMotor getMotor() {
        return motor;
    }
}
