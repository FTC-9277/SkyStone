package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "Teach")
public class teachClass extends OpMode {


    DcMotor exampleMotor;

    @Override
    public void init() {
        exampleMotor = hardwareMap.get(DcMotor.class, "example");
    }

    @Override
    public void loop() {
//        gamepad1.left_stick_x;
//        exampleMotor.setPower(  );
    }
}
