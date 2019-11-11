package Vision;

public class nativePipeline {

    public int sample(int[] a, int w, int h) {
        return Java_Vision_nativePipeline_Sample(a,w,h);
    }

    static {
        System.loadLibrary("skystone");
    }

    public native int Java_Vision_nativePipeline_Sample(int[] a, int w, int h);
}