package teleops;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Vision.Sampler;
import org.firstinspires.ftc.teamcode.Player;
import org.firstinspires.ftc.teamcode.R;

import robot.ExplosivesRobot;

@TeleOp(name = "FullTeleOp with sounds")
public class FullTeleWithSounds extends OpMode {

    ExplosivesRobot robot;

    @Override
    public void init() {
        robot = new ExplosivesRobot(this);
//        robot.setDriveTrainType(ExplosivesRobot.DriveTrainType.MECANUM);
        robot.init();
//        sampler = new Sampler(this);
        player = MediaPlayer.create(hardwareMap.appContext, R.raw.bruh);
        cantinaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.cantina);
    }

//    int vision = -12;
//    Sampler sampler;

    boolean bruh = false;
    boolean cantina = false;

    MediaPlayer player;
    MediaPlayer cantinaPlayer;

    @Override
    public void loop() {

        if(Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot.fright.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.bright.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) + gamepad1.right_stick_x);
            robot.fleft.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x) - gamepad1.right_stick_x);
            robot.bleft.setPower((gamepad1.left_stick_y+gamepad1.left_stick_x) - gamepad1.right_stick_x);
        } else {
            robot.stop();
        }

        telemetry.addData("Servo: ", robot.hook.getPosition());
        telemetry.update();

        if(gamepad1.dpad_up) {
            robot.unhook();
        } else if (gamepad1.dpad_down) {
            robot.hook();
        }

        if(Math.abs(gamepad2.left_stick_y) > 0.2) {
            if(gamepad2.left_stick_y < 0) {
                robot.intakeDown();
            } else {
                robot.intakeUp();
            }
        }

        if(gamepad2.dpad_down) {
            robot.liftCapstone();
        } else if (gamepad2.dpad_up) {
            robot.dropCapstone();
        }

        //Sounds
        if(gamepad2.b) {
            if(!bruh) {
                bruh = true;
                player.seekTo(0);
                player.start();
            }
        } else {
            bruh = false;
        }

        if(gamepad2.x) {
            if(!cantina) {
                cantina = true;
                if(!cantinaPlayer.isPlaying()) {
                    cantinaPlayer.seekTo(0);
                    cantinaPlayer.start();
                } else {
                    cantinaPlayer.stop();
                }
            }
        } else {
            cantina = false;
        }

//        if(Math.abs(gamepad2.right_stick_y) > 0.2) {
//            robot.intake.setPower(gamepad2.right_stick_y);
//        } else {
//            robot.intake.setPower(0.0);
//        }

    }
}
