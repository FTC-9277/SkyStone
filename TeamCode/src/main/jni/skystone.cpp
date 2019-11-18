//
// Created by Alex on 9/25/19.
//

#include <deque>
#include <jni.h>
#include <math.h>

extern "C" {
JNIEXPORT jint JNICALL Java_Vision_nativePipeline_Detect(JNIEnv *env, jobject obj, jintArray arr, jint width, jint height);
    int getRed(int c);
    int getGreen(int c);
    int getBlue(int c);
    bool isB(int r, int g, int b);
    bool isBlack(int c);
//    bool isY(int r, int g, int b);
//    bool isYellow(int c);
    int sample(int *pixels, int w, int h);
}

typedef struct my_struct_t {

    int w, h;

//    int yellow_threshold = 160;
    int black_threshold = 100;

    int *pixels;

} my_struct_t;

my_struct_t my_struct;

//public native int Detect(int[] a, int w, int h);
JNIEXPORT jint JNICALL Java_Vision_nativePipeline_Detect(JNIEnv *env, jobject obj, jintArray arr, jint width, jint height) {
    jboolean * b;

    return true;

//    my_struct = my_struct_t();
//    my_struct.pixels = env->GetIntArrayElements(arr, b);
//
//    my_struct.w = (int) width;
//    my_struct.h = (int) height;
//
//    bool output = sample(my_struct.pixels, (int) width, (int) height);
//
//    //RELEASE
//    env->ReleaseIntArrayElements(arr, my_struct.pixels, 0);
//
//    return output;
}

//True Skystone
//False Stone
int sample(int *pixels, int w, int h) {

    //Returns value from 0-4
    //0: very left, 1: left, 2: middle, 3: right, 4: very right

    //Divisions MUST BE ODD
    int divisions = 5;

//    for(int pos = 0; pos<divisions; pos = pos + 1) {
        //Find range
        int w_start = 2 * (w / 5);
//    int w_start = 0;
        int w_end = 3 * (w / 5);
//    int w_end = w;
        int h_start = 2 * (h / 5);
//    int h_start = 0;
        int h_end = 3 * (h / 5);
//    int h_end = h;

        int viewH = h_end - h_start;
        int viewW = w_end - w_start;

        //Check to see if the average is above this value; RANGE: 0.0-1.0
        double average_threshold = 0.5;

        int num_black = 0;

        for (int i = h_start; i < h_end; i = i + 1) {
            //For each row
            for (int j = w_start; j < w_end; j = j + 1) {
                //For each column
                if (isBlack(pixels[(i * w) + j])) {
                    num_black = num_black + 1;
                }
            }
        }

        if (num_black/*/((w)*(h))*/ >
            ((viewH * viewW) / 4)/*average_threshold(((w_end-w_start)*(h_end-h_start))/500)*/) {
            return true;
        } else {
            return false;
        }
//    }

//    return -1;

    //MULTIPLE TESTS

    //Returns value from 0-4
    //0: very left, 1: left, 2: middle, 3: right, 4: very right

//    //Divisions MUST BE ODD
//    int divisions = 1;
//    int i = 2;
////    for(int i = 0; i < divisions; i = i + 1) {
//    //Find range
//        int w_start = i*(my_struct.w/divisions);
////        int w_start = 0;
//        int w_end = (i+1)*(my_struct.w/divisions);
////        int w_end = my_struct.w;
//        int h_start = ((int)(divisions/2))*(my_struct.h/divisions);
////        int h_start = 0;
//        int h_end = (round(((double)divisions)/2))*(my_struct.h/divisions);
////        int h_end = my_struct.h;
//
////        //Check to see if the average is above this value
////        int average_threshold = 0.8;
//
//        int num_black = 0;
//
////        for(int i = h_start; i < h_end; i = i + 1) {
//            //For each row
////            int t=my_struct.h*my_struct.w;
//            if(isBlack(pixels[0])) {
//                num_black = num_black + 1;
//            }
//////            for(int j = w_start; j < w_end; j++) {
//////                //For each column
//////                if(isBlack(pixels[(i*my_struct.w)+j])) {
//////                    num_black++;
//////                }
//////            }
////        }
//
//        if (num_black/*/((w)*(h))*/ > 0/*((h*w)/4)/*average_threshold(((w_end-w_start)*(h_end-h_start))/500)*/) {
//            return 100;
//        } else {
//            return -2;
//        }
////    }

    return -1;
}

//bool isYellow(int c) {
//    return isY(getRed(c),getGreen(c),getBlue(c));
//}

//bool isY(int r, int g, int b){
//    return (255-b > my_struct.yellow_threshold) && (255-g <= my_struct.yellow_threshold) && (255-r <= my_struct.yellow_threshold);
//}

bool isBlack(int c) {
    return isB(getRed(c),getGreen(c),getBlue(c));
}

bool isB(int r, int g, int b) {
    return ((r < my_struct.black_threshold) && (g < my_struct.black_threshold) && (b < my_struct.black_threshold));
}

int getRed  (int c){return ((c >> 16) & 0xff);}
int getGreen(int c){return ((c >>  8) & 0xff);}
int getBlue (int c){return ((c      ) & 0xff);}

extern "C"
JNIEXPORT jint JNICALL
Java_Vision_nativePipeline_Java_1Vision_1nativePipeline_1Detect(JNIEnv *env, jobject instance,
                                                                jintArray a_, jint w, jint h) {
    jint *a = env->GetIntArrayElements(a_, NULL);

    bool output = sample(a,w,h);
    output = true;
//
//    output = true;
//    bool output = isBlack(a[0]);
//
    env->ReleaseIntArrayElements(a_, a, 0);

    return true;

}

extern "C"
JNIEXPORT jint JNICALL
Java_Vision_nativePipeline_Java_1Vision_1nativePipeline_1Sample(JNIEnv *env, jobject instance,
                                                                jintArray a_, jint w, jint h) {
    jint *a = env->GetIntArrayElements(a_, NULL);

    int output = sample(a,w,h);
//    output = isBlack(a[0]);

    env->ReleaseIntArrayElements(a_, a, 0);

    return output;
}