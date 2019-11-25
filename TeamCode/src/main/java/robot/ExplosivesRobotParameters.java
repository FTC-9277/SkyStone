package robot;

public class ExplosivesRobotParameters {

    DriveTrainType driveTrain = DriveTrainType.MECANUM;

    public enum DriveTrainType {
        MECANUM, TANK
    }

    public boolean sonicEnabled = false;

    public ExplosivesRobotParameters() {}

}
