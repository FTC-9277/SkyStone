package robot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

public class ExplosivesRobot {

    private OpMode opMode = null;

    public DcMotor fleft, bleft, bright, fright;//, intake;
    //    public DcMotor intake;
    public Gyro gyro;
    public Servo hook, leftI, rightI;
//    public CRServo intake;
    
    private ArrayList<DcMotor> allMotors = new ArrayList<>();

    DriveTrainType driveTrain;

    public enum DriveTrainType {
        MECANUM, TANK
    }

    public enum Direction {
        LEFT, RIGHT
    }

    public ExplosivesRobot(OpMode op) {
        opMode = op;
        driveTrain = DriveTrainType.TANK;
    }

    public void init() {
        fleft = opMode.hardwareMap.get(DcMotor.class, "fleft");
        bleft = opMode.hardwareMap.get(DcMotor.class, "bleft");
        bright = opMode.hardwareMap.get(DcMotor.class, "bright");
        fright = opMode.hardwareMap.get(DcMotor.class, "fright");

        leftI = opMode.hardwareMap.get(Servo.class, "leftI");
        rightI = opMode.hardwareMap.get(Servo.class, "rightI");

//        intake = opMode.hardwareMap.get(CRServo.class, "intake");

        hook = opMode.hardwareMap.get(Servo.class,"hooker");

//        intake = opMode.hardwareMap.get(DcMotor.class, "intake");
//        left = opMode.hardwareMap.get(CRServo.class, "left");
////        right = opMode.hardwareMap.get(CRServo.class, "right");

        allMotors.add(fleft);
        allMotors.add(bleft);
        allMotors.add(bright);
        allMotors.add(fright);

        gyro = new Gyro(opMode);

        fright.setDirection(DcMotorSimple.Direction.REVERSE);
        bright.setDirection(DcMotorSimple.Direction.REVERSE);
//        left.setDirection(DcMotorSimple.Direction.REVERSE);

        unhook();
    }

    public void setDriveTrainType(DriveTrainType type) {
        this.driveTrain = type;
    }

    public void drive(double speed) {
        speed = -speed;
        if(speed<=1&&speed>=-1) {
            fleft.setPower(speed);
            fright.setPower(speed);
            bleft.setPower(speed);
            bright.setPower(speed);
        }
    }

    public void stop() {
        for (DcMotor motor : allMotors) {
            motor.setPower(0);
        }
    }

//    private void driveTank(double speed) {
//        fleft.setPower(speed);
//        fright.setPower(speed);
//        bleft.setPower(speed);
//        bright.setPower(speed);
//    }
//
//    private void driveMechanum(double speed) {
//        //THIS DOES NOT WORK YET AND THIS IS TEMP CODE!!!
//        fleft.setPower(opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x);
//        fright.setPower(opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x);
//        bleft.setPower(opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x);
//        bright.setPower(opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x);
//    }

    public void strafe(double speed, Direction direction) {
        if (driveTrain == DriveTrainType.MECANUM) {
            if(direction == Direction.LEFT) {
                fright.setPower(-speed);
                bright.setPower(speed);
                fleft.setPower(speed);
                bleft.setPower(-speed);
            } else {
                fright.setPower(speed);
                bright.setPower(-speed);
                fleft.setPower(-speed);
                bleft.setPower(speed);
            }
        }
    }

    public void turn(double speed, Direction direction) {
        if (direction == Direction.RIGHT) {
            fleft.setPower(-speed);
            bleft.setPower(-speed);
            fright.setPower(speed);
            bright.setPower(speed);
        } else {
            fleft.setPower(speed);
            bleft.setPower(speed);
            fright.setPower(-speed);
            bright.setPower(-speed);
        }
    }

    final int ALLOWED_ERROR = 10;

    public void drive(double speed, int ticks) {
        int initL = leftEncoders();
        int initR = rightEncoders();

        int targetL = initL+ticks;
        int targetR = initR+ticks;

        while(Math.abs(initR-targetR) > ALLOWED_ERROR && Math.abs(initL-targetL) > ALLOWED_ERROR) {
            drive(speed);
        }

        stop();
    }

    private void lDrive(double speed) {
        bleft.setPower(speed);
        bright.setPower(speed);
    }

    private void rDrive(double speed) {
        bright.setPower(speed);
        fright.setPower(speed);
    }

    //Returns the average of the left side
    public int leftEncoders() {
        return (fleft.getCurrentPosition()+bleft.getCurrentPosition())/2;
    }

    //Returns the average of the right side
    public int rightEncoders() {
        return (fright.getCurrentPosition()+bright.getCurrentPosition())/2;
    }

    public void intakeUp() {
        leftI.setPosition(leftI.getPosition()+0.01);
        rightI.setPosition(rightI.getPosition()-0.01);
    }

    public void intakeDown() {
        leftI.setPosition(leftI.getPosition()-0.01);
        rightI.setPosition(rightI.getPosition()+0.01);
    }


    public void hook() {
        hook.setPosition(1.0);
    }

    public void unhook() {
        hook.setPosition(0.0);
    }

    ///INCOMPLETE
    public void driveStraight(int ticks) {

        double SPEED = 0.75;

        //Original encoder value
        int orgEnc = avgEncoder();
        int target = orgEnc + ticks;

        //Original gyro values
        double orgGyro = gyro.heading();

        //While encoders are 15 away from target or more
        while (Math.abs(target - avgEncoder()) > 15) {

            opMode.telemetry.addData("bleft: ", bleft.getCurrentPosition());
            opMode.telemetry.addData("fleft: ", fleft.getCurrentPosition());
            opMode.telemetry.addData("bright: ", bright.getCurrentPosition());
            opMode.telemetry.addData("fright: ", fright.getCurrentPosition());
            opMode.telemetry.addData("Avg: ", avgEncoder());
            opMode.telemetry.update();

//            //The higher, the more it will move
//            double multiplier = 50;
//
//            //Positive is clockwise, negative is counterclockwise
//            double difference = (orgGyro - gyro.heading());
//
//            double ADJSPEED = SPEED * ((multiplier / 100) * (difference / 100));
//            if (difference > 0) {
//                lDrive(SPEED);
//                rDrive(ADJSPEED);
//            } else {
//                lDrive(ADJSPEED);
//                rDrive(SPEED);
//            }

            lDrive(SPEED);
            rDrive(SPEED);


        }

        stop();
    }

    //Number of ticks to allow the encoder to differ from 0 before counting them as broken
    int ENCODER_FAILURE_ALLOWANCE = 10;

    public int avgEncoder () {
//        if (leftEncoders() == 0 && Math.abs(rightEncoders()) > ENCODER_FAILURE_ALLOWANCE) {
//            //Left is probably broken
//            return rightEncoders();
//        } else if (rightEncoders() == 0 && Math.abs(rightEncoders()) > ENCODER_FAILURE_ALLOWANCE) {
//            //Right is probably broken
//            return leftEncoders();
//        } else {
//            return (leftEncoders() + rightEncoders()) / 2;
//        }
        return bright.getCurrentPosition();
    }

}
