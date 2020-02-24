package robot;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;

public class ExplosivesRobot {

    private OpMode opMode = null;

    public DcMotor fleft, bleft, bright, fright;
    public Servo intake;
    public Gyro gyro;
    public Servo hook, cap, leftI, rightI;
    public DcMotor leftLift, rightLift;
//    public CRServo intake;
    public ModernRoboticsI2cRangeSensor sonicTheFish,sonicTheWarthog;

    private ArrayList<DcMotor> allMotors = new ArrayList<>();

    public ExplosivesRobotParameters parameters;

    public enum Direction {
        LEFT, RIGHT
    }

    public ExplosivesRobot(OpMode op/*, ExplosivesRobotParameters parameters*/) {
        opMode = op;
        this.parameters=parameters;
    }

    public void init() {
        fleft = opMode.hardwareMap.get(DcMotor.class, "fleft");
        bleft = opMode.hardwareMap.get(DcMotor.class, "bleft");
        bright = opMode.hardwareMap.get(DcMotor.class, "bright");
        fright = opMode.hardwareMap.get(DcMotor.class, "fright");

        leftI = opMode.hardwareMap.get(Servo.class, "leftI");
        rightI = opMode.hardwareMap.get(Servo.class, "rightI");

        intake = opMode.hardwareMap.get(Servo.class, "intake");

        hook = opMode.hardwareMap.get(Servo.class,"hooker");
//
        cap = opMode.hardwareMap.get(Servo.class, "marker");

//        lintake = opMode.hardwareMap.get(DcMotor.class,"lintake");
//        rintake = opMode.hardwareMap.get(DcMotor.class,"rintake");
//
        leftLift = opMode.hardwareMap.get(DcMotor.class,"leftLift");
        rightLift = opMode.hardwareMap.get(DcMotor.class,"rightLift");

        sonicTheFish = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class,"sonicRight");
        sonicTheWarthog = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class,"sonicLeft");

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
////        left.setDirection(DcMotorSimple.Direction.REVERSE);

//        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);
//        rintake.setDirection(DcMotorSimple.Direction.REVERSE);


//        liftIntake();

//        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftI.setPosition(-0.5);
        rightI.setPosition(0.5);

        unhook();
//        liftCapstone();
        intake.setPosition(0.75);
    }

    public void drive(double speed) {
//        speed = -speed;
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
//        speed=-speed;
//        if (driveTrain == DriveTrainType.MECANUM) {
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
//        }
    }

    public void turn(double speed, Direction direction) {
        speed = -speed;
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

    public void lDrive(double speed) {
        bleft.setPower(speed);
        fleft.setPower(speed);
    }

    public void rDrive(double speed) {
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
        leftI.setPosition(1.0);
        rightI.setPosition(0.0);
    }

    public void intakeDown() {
        leftI.setPosition(0.0);
        rightI.setPosition(1.0);
    }


    public void hook() {
        hook.setPosition(0.625);
    }

    public void unhook() {
        hook.setPosition(0.0);
    }

    public void dropCapstone() {
        cap.setPosition(1.0);
    }

    public void liftCapstone() {
        cap.setPosition(0.1);
    }

    public void strafeStraight(double speed, int millis) {
        speed = -speed;
        long initT = System.currentTimeMillis()+millis;
        //For some reason, targetSpeed has to be negative
        double targetSpeed = -0.8*speed;
        double initG = gyro();
        while(System.currentTimeMillis() < initT) {
            double diff = gyro()-initG;
            opMode.telemetry.addData("GYRO: ", gyro());
            opMode.telemetry.addData("DIFF: ", diff);
            if(diff > 0) {
                bleft.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                fleft.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                fright.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                bright.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("Left: ", targetSpeed + Math.abs(diff/DIVISOR));
                opMode.telemetry.addData("Right: ", targetSpeed - Math.abs(diff/DIVISOR));
            } else {
                bleft.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                fleft.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                fright.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                bright.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("Left: ", targetSpeed - Math.abs(diff/DIVISOR));
                opMode.telemetry.addData("Right: ", targetSpeed + Math.abs(diff/DIVISOR));
            }
            opMode.telemetry.update();

        }
        stop();
    }

    double last = 0.0;

    public void gyroTurn(double speed, int degrees) {
//        double initG = gyro();
//        double target = initG+degrees;
//        double distance = initG-target;
//        int divisor = 100;
//
//        if(degrees < 0) {
//            lDrive(-speed);
//            rDrive(speed);
//
//            while(gyro()>target) {
//                lDrive(-speed*(1-(distance/divisor)));
//                rDrive(speed*(1-(distance/divisor)));
//                distance = initG-target;
//            }
//        } else {
//
//
//            while(gyro()<target) {
//                lDrive(speed*(1-(distance/divisor)));
//                rDrive(-speed*(1-(distance/divisor)));
//            }
//        }
//
//        stop();

        last = gyro();
        double current = gyro();
        double target = current+degrees;

        double difference = target-current;
        double overallDiff = target-current;

        if (target < -180){
            target+=360;
        } else if (target > 180) {
            target-=360;
        }

        long start = System.currentTimeMillis();

        while(Math.abs(gyro()-target)>2 && start+(overallDiff*(speed*20)) > System.currentTimeMillis()) {
            difference = target - gyro();

            if (difference > 50) {
                lDrive(-0.8*speed);
                rDrive(0.8*speed);
            } else if (difference > 30) {
                lDrive(-0.3*speed);
                rDrive(0.3*speed);
            } else if (difference > 20) {
                lDrive(-0.2*speed);
                rDrive(0.2*speed);
            } else if (difference < -50) {
                lDrive(0.8*speed);
                rDrive(-0.8*speed);
            } else if (difference < -30) {
                lDrive(0.3*speed);
                rDrive(-0.3*speed);
            } else if (difference < -20) {
                lDrive(0.2*speed);
                rDrive(-0.2*speed);
            }

            opMode.telemetry.addData(">", gyro());
            opMode.telemetry.addData("Targ", target);
            opMode.telemetry.update();
        }

    }

    public void turnToAngle(double speed, int target) {
        last = gyro();
        double current = gyro();

        double difference = target-current;
        double overallDiff = target-current;

        if (target < -180){
            target+=360;
        } else if (target > 180) {
            target-=360;
        }

        long start = System.currentTimeMillis();

        while(start+2000 > System.currentTimeMillis()) {
            opMode.telemetry.addData("Target", target);
            opMode.telemetry.addData("Diff", difference);
            opMode.telemetry.update();
        }

        start = System.currentTimeMillis();

        while(Math.abs(target)-Math.abs(gyro())>2 && start+(overallDiff*(speed*20)) > System.currentTimeMillis()) {
            difference = target - gyro();

            if (difference > 50) {
                lDrive(-0.8*speed);
                rDrive(0.8*speed);
            } else if (difference > 30) {
                lDrive(-0.3*speed);
                rDrive(0.3*speed);
            } else if (difference > 20) {
                lDrive(-0.2*speed);
                rDrive(0.2*speed);
            } else if (difference < -50) {
                lDrive(0.8*speed);
                rDrive(-0.8*speed);
            } else if (difference < -30) {
                lDrive(0.3*speed);
                rDrive(-0.3*speed);
            } else if (difference < -20) {
                lDrive(0.2*speed);
                rDrive(-0.2*speed);
            }

            opMode.telemetry.addData(">", gyro());
            opMode.telemetry.addData("Targ", target);
            opMode.telemetry.update();
        }
    }

    ///INCOMPLETE
    public void driveTicks(double speed, int ticks) {
        speed = -speed;
        //For some reason, targetSpeed has to be negative
        long initT = System.currentTimeMillis()+ticks;
        resetEncoders();
        double targetSpeed = -0.8*speed;
        double initG = gyro();
        double target = avgEncoder()+ticks;
//        int sign = 1;
//        if(avgEncoder()<0) {
//            sign = -1;
//        }
//        while(System.currentTimeMillis() < initT) {
        while(avgEncoder() < target) {
            double diff = gyro()-initG;
            opMode.telemetry.addData("GYRO: ", gyro());
            opMode.telemetry.addData("DIFF: ", diff);
            if(diff > 0) {
                bleft.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                fleft.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                fright.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                bright.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("fright",fright.getPower());
                opMode.telemetry.addData("fleft",fleft.getPower());
                opMode.telemetry.addData("bright",bright.getPower());
                opMode.telemetry.addData("bleft",bleft.getPower());
            } else {
                bleft.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                fleft.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                fright.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                bright.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("fright",fright.getPower());
                opMode.telemetry.addData("fleft",fleft.getPower());
                opMode.telemetry.addData("bright",bright.getPower());
                opMode.telemetry.addData("bleft",bleft.getPower());
            }
            opMode.telemetry.addData("Encoder: ", avgEncoder());
            opMode.telemetry.addData("Encoder Target: ", target);
            opMode.telemetry.update();

        }
        stop();
    }

    final int DIVISOR = 50;

    public void driveTime(double speed, int millis) {
//        speed = -speed;
        long initT = System.currentTimeMillis()+millis;
        //For some reason, targetSpeed has to be negative
        double targetSpeed = -0.8*speed;
        double initG = gyro();
        while(System.currentTimeMillis() < initT) {
            double diff = gyro()-initG;
            opMode.telemetry.addData("GYRO: ", gyro());
            opMode.telemetry.addData("DIFF: ", diff);
            if(diff > 0) {
                bleft.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                fleft.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                fright.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                bright.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("Left: ", targetSpeed + Math.abs(diff/DIVISOR));
                opMode.telemetry.addData("Right: ", targetSpeed - Math.abs(diff/DIVISOR));
            } else {
                bleft.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                fleft.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                fright.setPower((targetSpeed + Math.abs(diff/DIVISOR)));
                bright.setPower((targetSpeed - Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("Left: ", targetSpeed - Math.abs(diff/DIVISOR));
                opMode.telemetry.addData("Right: ", targetSpeed + Math.abs(diff/DIVISOR));
            }
            opMode.telemetry.update();

        }
        stop();
    }

    public void strafeStraight(double speed, Direction direction, int millis) {
        if(direction==Direction.RIGHT) {
            speed = -speed;
        }
        long initT = System.currentTimeMillis()+millis;
        //For some reason, targetSpeed has to be negative
        double targetSpeed = -0.8*speed;
        double initG = gyro();
        while(System.currentTimeMillis() < initT) {
            double diff = gyro()-initG;
            opMode.telemetry.addData("GYRO: ", gyro());
            opMode.telemetry.addData("DIFF: ", diff);
            if(diff > 0) {
                fright.setPower(((targetSpeed) - Math.abs(diff/DIVISOR)));
                bright.setPower(((-targetSpeed) - Math.abs(diff/DIVISOR)));
                fleft.setPower(((-targetSpeed) + Math.abs(diff/DIVISOR)));
                bleft.setPower(((targetSpeed) + Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("fright",fright.getPower());
                opMode.telemetry.addData("fleft",fleft.getPower());
                opMode.telemetry.addData("bright",bright.getPower());
                opMode.telemetry.addData("bleft",bleft.getPower());
            } else {
                fright.setPower(((targetSpeed) + Math.abs(diff/DIVISOR)));
                bright.setPower(((-targetSpeed) + Math.abs(diff/DIVISOR)));
                fleft.setPower(((-targetSpeed) - Math.abs(diff/DIVISOR)));
                bleft.setPower(((targetSpeed) - Math.abs(diff/DIVISOR)));
                opMode.telemetry.addData("fright",fright.getPower());
                opMode.telemetry.addData("fleft",fleft.getPower());
                opMode.telemetry.addData("bright",bright.getPower());
                opMode.telemetry.addData("bleft",bleft.getPower());
            }
            opMode.telemetry.update();

        }
        stop();
    }

    //Number of ticks to allow the encoder to differ from 0 before counting them as broken
    int ENCODER_FAILURE_ALLOWANCE = 10;

    public int avgEncoder() {
        return -fright.getCurrentPosition();
    }

    public void resetEncoders() {
        fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double gyro() {
        return -gyro.heading();
    }


    public void lift() {
        leftLift.setPower(0.3);
        rightLift.setPower(0.3);
    }

    public void fall() {
        leftLift.setPower(-0.3);
        rightLift.setPower(-0.3);
    }

    public void outtake() {
        if(intake.getPosition()>0.0) {
            intake.setPosition(intake.getPosition() - 0.02);
        }
    }

    public void intake() {
        intake.setPosition(intake.getPosition()+0.02);
    }

    final double INTAKE_MOVE_CONSTANT = 0.015;

    public void dropIntake() {
//        if(leftI.getPosition() < 0.94) {
            leftI.setPosition(leftI.getPosition() + INTAKE_MOVE_CONSTANT);
            rightI.setPosition(rightI.getPosition() - INTAKE_MOVE_CONSTANT);
//        }
    }

    public void liftIntake() {
        if(leftI.getPosition() > 0.25) {
            leftI.setPosition(leftI.getPosition() - INTAKE_MOVE_CONSTANT);
            rightI.setPosition(rightI.getPosition() + INTAKE_MOVE_CONSTANT);
        }
    }

}
