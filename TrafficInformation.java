import java.time.DateTimeException;
import java.text.SimpleDateFormat;
import  java.util.Date;
public class TrafficInformation {
    String device_num;
    String vehicleplatenumber;
    double direction_angle;
    double lng,lat;

    Date location_time;
    int acc_state;
    int right_turn_signals;
    int left_turn_signals;
    int hand_brake;
    int foot_brake;
    double gps_speed;
    double mileage;

    public TrafficInformation(String device_num, String vehicleplatenumber, double direction_angle, double lng, double lat, Date location_time, int acc_state, int right_turn_signals, int left_turn_signals, int hand_brake, int foot_brake, double gps_speed, double mileage) {
        this.device_num = device_num;
        this.vehicleplatenumber = vehicleplatenumber;
        this.direction_angle = direction_angle;
        this.lng = lng;
        this.lat = lat;
        this.location_time = location_time;
        this.acc_state = acc_state;
        this.right_turn_signals = right_turn_signals;
        this.left_turn_signals = left_turn_signals;
        this.hand_brake = hand_brake;
        this.foot_brake = foot_brake;
        this.gps_speed = gps_speed;
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "TrafficInformation{" +
                "device_num='" + device_num + '\'' +
                ", vehicleplatenumber='" + vehicleplatenumber + '\'' +
                ", direction_angle=" + direction_angle +
                ", lng=" + lng +
                ", lat=" + lat +
                ", location_time=" + location_time +
                ", acc_state=" + acc_state +
                ", right_turn_signals=" + right_turn_signals +
                ", left_turn_signals=" + left_turn_signals +
                ", hand_brake=" + hand_brake +
                ", foot_brake=" + foot_brake +
                ", gps_speed=" + gps_speed +
                ", mileage=" + mileage +
                '}';
    }
}
