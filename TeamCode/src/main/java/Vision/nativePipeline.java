package Vision;

public class nativePipeline {

    public boolean sample(int[] a, int w, int h) {
        return Java_Vision_nativePipeline_Sample(a,w,h);
    }

    static {
        System.loadLibrary("skystone");
    }

    public native boolean Java_Vision_nativePipeline_Sample(int[] a, int w, int h);
}