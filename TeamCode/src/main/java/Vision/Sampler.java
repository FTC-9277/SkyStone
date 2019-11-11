package Vision;


import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Sampler {

    private OpMode opMode;
    private Camera camera;

    public Sampler(OpMode opMode){
        this.opMode = opMode;
        this.camera = new Camera(opMode.hardwareMap, false);
    }

    public int sample(){
        int[] pixels;
        int w, h;
        nativePipeline pipeline = new nativePipeline();
        camera.cycle();
        pixels = camera.bitmapToArray(camera.bitmap);
        w = camera.bitmap.getWidth();
        h = camera.bitmap.getHeight();
        camera.bitmap.recycle();

        return pipeline.sample(pixels, w, h);
    }
}