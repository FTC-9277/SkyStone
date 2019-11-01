package autos;

import android.drm.DrmStore;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import recorder.Player;

//@Autonomous(name = "Playback Test")
public class PlaybackTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Player player = new Player();

        player.playRecording("first", this);

    }

}
