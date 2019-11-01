package recorder;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Player {

    /*
     * Play a recording from a valid record file from an OpMode.
     *
     * @param name - the name of the record file you want to play
     * @param op - The OpMode used for the playback (NOTE: every motor in the recording file needs to have the same name in the configuration mode or the program will not function properly. If one or more of the motor names have changed, plug in each motor object manually in the counterpart method [playRecording(String name, ArrayList<DcMotor> motors)])
     *
     */
    public void playRecording(String name, OpMode op) {

        //Add .txt to name if not already present
        if (!name.contains(".txt")) {
            name = name + ".txt";
        }

        //Get file contents
        String file = getContentsOfFile(name);

        String[] data = file.split(";;");

        //Get motors
        final ArrayList<DcMotor> motors = new ArrayList<>();
        for (String motorStr : data[0].split("\n")[2].replace("Recorded Motors: ", "").split(", ")) {
            motors.add(op.hardwareMap.get(DcMotor.class, motorStr.split(" \\(")[0]));
        }

        Timer timer = new Timer();

        for (final String line : data[1].split(";")) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int num = 0;
                    for (String power : line.replace("\n", "").split(": ")[1].split(", ")) {
                        motors.get(num).setPower(Double.parseDouble(power));
                        num++;
                    }
                    System.out.println("");
                }
            }, Integer.parseInt(line.replace("\n", "").split(":")[0]));

        }

    }

    /*
     * Play a recording from a valid record file with a given motor input.
     *
     * @param name - the name of the record file you want to play
     * @param motors - in the case that the config modes (i.e. motor names) have changed since the file was recorded, you can specify the DcMotor objects that should correspond to the motors listed in the recording file.
     *
     */
    public void playRecording(String name, final ArrayList<DcMotor> motors) {

        //Add .txt to name if not already present
        if (!name.contains(".txt")) {
            name = name + ".txt";
        }

        //Get file contents
        String file = getContentsOfFile(name);

        String[] data = file.split(";;");

        Timer timer = new Timer();

        for (final String line : data[1].split(";")) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int num = 0;
                    for (String power : line.replace("\n", "").split(": ")[1].split(", ")) {
                        motors.get(num).setPower(Double.parseDouble(power));
                        num++;
                    }
                    System.out.println("");
                }
            }, Integer.parseInt(line.replace("\n", "").split(":")[0]));

        }

    }

    /*
     * Get the contents of a file in the external storage directory with a given name.
     *
     * @param name - the name of the text file (.txt included) you want to read
     * @return string contents of given file
     *
     */
    private String getContentsOfFile(String name) {
        String line = "";

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + name));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return line;
    }


}
