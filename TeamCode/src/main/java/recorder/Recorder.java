package recorder;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Recorder implements Runnable {

    private boolean isRecording = false;

    private String filename = "";
    private ArrayList<RecordMotor> motors = null;

    private long startTime;

    private Thread t = null;

    //The number of milliseconds in-between each stored value
    private final int RECORD_DELAY = 100;

    public void record(String name, ArrayList<RecordMotor> motors) {
        isRecording = true;
        if (!filename.contains(".txt")) {
            filename = name + ".txt";
        }
        this.motors = motors;

        //Create string array of motor names
        String motorStr = "";
        for (RecordMotor motor : motors) {
            motorStr = motorStr + "\"" + motor.getName() + "\" (" + motor.getMotor().getDeviceName() + "), ";
        }
        motorStr = motorStr.substring(0,motorStr.length()-2);
        motorStr = motorStr + ";;";

        writeToFile("9277 HazMat Robotics Explosives - Robot Recorder V1.0\nRecorded Motors: " + motorStr);

        t = new Thread(this);
        t.start();
    }

    private ArrayList<Double> storedValues = new ArrayList<>();

    /*
     * Record when the motors change value (to be used in the Thread only)
     */
    private void record(ArrayList<RecordMotor> motors) {

        startTime = System.currentTimeMillis();
        long newTime = System.currentTimeMillis() + RECORD_DELAY;

        //Add initial values
        for (int i = 0; i < motors.size(); i++) {
            storedValues.add(0.0);
        }

        while(isRecording) {

            while(System.currentTimeMillis() < newTime) {
                //Wait until time reached
            }

            int num = 0;
            boolean keepGoing = false;
            for (RecordMotor motor : motors) {
                if (motor.getMotor().getPower() != storedValues.get(num)) {
                    keepGoing = true;
                    break;
                }
                num ++;
            }

            if (keepGoing) {

                String valueStr = (System.currentTimeMillis() - startTime + ": ");
                for (RecordMotor motor : motors) {
                    valueStr = (valueStr + motor.getMotor().getPower()) + ", ";
                }
                valueStr = valueStr.substring(0, valueStr.length() - 2);
                valueStr = (valueStr + ";");

                if (writeToFile(valueStr) == false) {
                    System.err.println("ERROR: Could not write to file");
                    return;
                }

                //Add stored values to ArrayList
                storedValues.clear();
                for (RecordMotor motor : motors) {
                    storedValues.add(motor.getMotor().getPower());
                }

            }

            newTime = System.currentTimeMillis() + RECORD_DELAY;

        }
    }

    public void stop() {
        isRecording = false;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean writeToFile(String data) {

        String absPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        try {
            new File(absPath).mkdir();
            File file = new File(absPath + filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException e) {
            e.printStackTrace();
        }  catch(IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean isRecording() {
        return isRecording;
    }

    @Override
    public void run() {
        record(motors);
    }
}
