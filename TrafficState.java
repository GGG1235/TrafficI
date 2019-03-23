import java.util.Date;

public class TrafficState extends TrafficInformation {
    public TrafficState(String device_num, String vehicleplatenumber, double direction_angle, double lng, double lat, Date location_time, int acc_state, int right_turn_signals, int left_turn_signals, int hand_brake, int foot_brake, double gps_speed, double mileage) {
        super(device_num, vehicleplatenumber, direction_angle, lng, lat, location_time, acc_state, right_turn_signals, left_turn_signals, hand_brake, foot_brake, gps_speed, mileage);
    }
    boolean fatigue_driving;
    boolean fast_add;
    boolean fast_del;
    boolean longtimefrom0to1;
    boolean longtimefrom1to1;
    boolean acc_state_0;
    boolean high_speed;
    boolean sudden_change;
    boolean sudden_change1;
    boolean sudden_change2;

    public TrafficState(String device_num, String vehicleplatenumber, double direction_angle, double lng, double lat, Date location_time, int acc_state, int right_turn_signals, int left_turn_signals, int hand_brake, int foot_brake, double gps_speed, double mileage, boolean fatigue_driving, boolean fast_add, boolean fast_del, boolean longtimefrom0to1, boolean longtimefrom1to1, boolean acc_state_0, boolean high_speed, boolean sudden_change, boolean sudden_change1, boolean sudden_change2) {
        super(device_num, vehicleplatenumber, direction_angle, lng, lat, location_time, acc_state, right_turn_signals, left_turn_signals, hand_brake, foot_brake, gps_speed, mileage);
        this.fatigue_driving = fatigue_driving;
        this.fast_add = fast_add;
        this.fast_del = fast_del;
        this.longtimefrom0to1 = longtimefrom0to1;
        this.longtimefrom1to1 = longtimefrom1to1;
        this.acc_state_0 = acc_state_0;
        this.high_speed = high_speed;
        this.sudden_change = sudden_change;
        this.sudden_change1 = sudden_change1;
        this.sudden_change2 = sudden_change2;
    }

}
