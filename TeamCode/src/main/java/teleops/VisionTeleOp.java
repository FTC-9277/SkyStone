package teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Vision.Sampler;
import robot.ExplosivesRobot;

@TeleOp(name = "Vision TeleOp")
public class VisionTeleOp extends OpMode {

//    ExplosivesRobot robot;

    @Override
    public void init() {
//        sampler = new Sampler(this);
    }

    int vision = -12;
    Sampler sampler;

    @Override
    public void loop() {
//        telemetry.addData("Vision: ", vision);
//
//        boolean going = false;
//        if(gamepad1.a) {
//            vision = sampler.sample();
//            if(going == false) {
//                going = true;
//            }
//        }
    }
}
