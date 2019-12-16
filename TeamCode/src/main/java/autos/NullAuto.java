package autos;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Player;
import org.firstinspires.ftc.teamcode.R;

import robot.ExplosivesRobot;

@Autonomous(name = "Null Auto", group = "other")
public class NullAuto extends LinearOpMode {

//    Player player = null;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

//        player = new Player(hardwareMap);

//        player.add("takeonme");

//        playMusic(0);

        Thread.sleep(5000);

//        player.play("takeonme");

        while(opModeIsActive()) {

        }

//        mediaPlayer.stop();


    }


//    private static MediaPlayer mediaPlayer;
//
//    public void playMusic(int id)
//    {
//
////        AssetFileDescriptor afd = getAssets().openFd(file.getName());
////        player = new MediaPlayer();
////        player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
////        player.prepare();
////        player.start();
////        mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.crabrave);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.seekTo(74000);
//        mediaPlayer.start();
//    }

}
