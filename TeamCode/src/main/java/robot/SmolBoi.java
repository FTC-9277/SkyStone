package robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SmolBoi {

    private OpMode opMode = null;

    public DcMotor left, right;

    public SmolBoi(OpMode op) {
        opMode = op;
    }

    public void init() {
        left = opMode.hardwareMap.get(DcMotor.class, "left");
        right = opMode.hardwareMap.get(DcMotor.class, "right");
    }

}
