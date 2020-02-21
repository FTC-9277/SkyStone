package Vision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Orient {

    private OpMode opMode;
    private Camera camera;

    public Orient(OpMode opMode){
        this.opMode = opMode;
        this.camera = new Camera(opMode.hardwareMap, false);
    }

    public double orient(){
        int[] pixels;
        int w, h;
        nativePipeline pipeline = new nativePipeline();
        camera.cycle();
        pixels = camera.bitmapToArray(camera.bitmap);
        w = camera.bitmap.getWidth();
        h = camera.bitmap.getHeight();
        camera.bitmap.recycle();

        return pipeline.orient(pixels, w, h);
    }

}
