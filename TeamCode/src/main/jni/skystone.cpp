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
    int sample(int *pixels, int w, int h);
}

typedef struct my_struct_t {

    int w, h;

    int black_threshold = 100;

    int *pixels;

} my_struct_t;

my_struct_t my_struct;

//public native int Detect(int[] a, int w, int h);
JNIEXPORT jint JNICALL Java_Vision_nativePipeline_Detect(JNIEnv *env, jobject obj, jintArray arr, jint width, jint height) {
    jboolean * b;

    return true;

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
}

bool isBlack(int c) {
    return isB(getRed(c),getGreen(c),getBlue(c));
}

bool isB(int r, int g, int b) {
    return ((r < my_struct.black_threshold) && (g < my_struct.black_threshold) && (b < my_struct.black_threshold));
}

int getRed  (int c){return ((c >> 16) & 0xff);}
int getGreen(int c){return ((c >>  8) & 0xff);}
int getBlue (int c){return ((c      ) & 0xff);}


bool isYellow(int c) {return (getBlue(c) < 80 && (getRed(c) > 110 && getRed(c) < 250) && (getGreen(c) > 60 && getGreen(c) < 200));}

const int SCAN_LENGTH=100;
const double THRESHOLD=0.2;

int orient(int *pixels, int w, int h) {

//    int count=0;
//
    bool leftStone=false;
//
//    //Scan line 1 left
//    for(int i=0; i<SCAN_LENGTH; i++) {
//        if(isYellow(pixels[(int)((h*0.4)*w)+i])) {count++;}
//    }
//
//    if((count)/(SCAN_LENGTH)>=0.5){
//        leftStone=true;
//    }
//
//    // Scan line 1 right
//    count=0;
    bool rightStone=false;
//
//    for(int i=0; i<SCAN_LENGTH; i++) {
//        if(isYellow(pixels[(int)((h*0.4)*(w+1))-i])) {count++;}
//    }
//
//    if((count)/SCAN_LENGTH>=THRESHOLD){
//        rightStone=true;
//    }

    int left[(w/3)*h],right[(w/3)*h];

    int count=0;
    for(int i=0;i<w*h;i++) {
        if(i<w/3) {
            left[count] = pixels[i];
        }
        count++;
    }

    return sample(left,w/3,h);

//    if(leftStone&&rightStone) {
//        return 0;
//    } else if (leftStone) {
//        return -1;
//    } else if (rightStone) {
//        return 1;
//    } else {
//        return 404;
//    }

}

extern "C"
JNIEXPORT jint JNICALL
Java_Vision_nativePipeline_Java_1Vision_1nativePipeline_1Detect(JNIEnv *env, jobject instance,
                                                                jintArray a_, jint w, jint h) {
    jint *a = env->GetIntArrayElements(a_, NULL);

    bool output = sample(a,w,h);
    output = true;(a[0]);

    env->ReleaseIntArrayElements(a_, a, 0);

    return true;

}

extern "C"
JNIEXPORT jint JNICALL
Java_Vision_nativePipeline_Java_1Vision_1nativePipeline_1Sample(JNIEnv *env, jobject instance,
                                                                jintArray a_, jint w, jint h) {
    jint *a = env->GetIntArrayElements(a_, NULL);

    int output = sample(a,w,h);

    env->ReleaseIntArrayElements(a_, a, 0);

    return output;
}

extern "C"
JNIEXPORT jint JNICALL
Java_Vision_nativePipeline_Java_1Vision_1nativePipeline_1Orient(JNIEnv *env, jobject instance,
                                                                jintArray a_, jint w, jint h) {
    jint *a = env->GetIntArrayElements(a_, NULL);

    int output = orient(a,w,h);

    env->ReleaseIntArrayElements(a_, a, 0);

    return output;
}