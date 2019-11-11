package autos;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.R;

import Vision.Sampler;
import recorder.Player;

@Autonomous(name = "Anime", group = "other")
public class VisionAuto extends LinearOpMode {

    int sample = 42;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        Sampler sampler = new Sampler(this);
        Thread.sleep(2000);

//        while(opModeIsActive()) {
//
//        }

//        playMusic();

//        telemetry.addData("Sample: ", sampler.sample());



//        DcMotor motor = hardwareMap.get(DcMotor.class, "intake");
//        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        int sample = sampler.sample();



        while(opModeIsActive()) {
            telemetry.addData("EYES: ", sample);
            telemetry.update();

//            if(sample) {
//                motor.setPower(1.0);
//            } else {
//                motor.setPower(-0.5);
//            }

//            Thread.sleep(1000);

//            motor.setPower(0.0);

            Thread.sleep(200);

            sample = sampler.sample();
        }

//        mediaPlayer.stop();

    }

//    private static MediaPlayer mediaPlayer;
//
//    public void playMusic() {
//        mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.giorno);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.seekTo(71500);
//        mediaPlayer.start();
//    }
}
