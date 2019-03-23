import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class input_data {

    ArrayList<TrafficInformation> inputList = new ArrayList<TrafficInformation>();

    public ArrayList<TrafficInformation> getInputList(String filename) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.readLine();

            String line = null;
            int number = 0;
//            ArrayList<TrafficInformation> list = new ArrayList<TrafficInformation>();
            while ((line = reader.readLine()) != null) {

                String[] item = line.split(",");
                int len = item.length;
                String device_num;
                String vehicleplatenumber;
                double direction_angle;
                double lng, lat;
                Date location_time;
                int acc_state;
                int right_turn_signals;
                int left_turn_signals;
                int hand_brake;
                int foot_brake;
                double gps_speed;
                double mileage;
                vehicleplatenumber = item[0];
                device_num = item[1];
                direction_angle = Double.valueOf(item[2]);
                lng = Double.valueOf(item[3]);
                lat = Double.valueOf(item[4]);
                acc_state = Integer.parseInt(item[5]);
                right_turn_signals = Integer.parseInt(item[6]);
                left_turn_signals = Integer.parseInt(item[7]);
                hand_brake = Integer.parseInt(item[8]);
                foot_brake = Integer.parseInt(item[9]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                location_time = sdf.parse(item[10]);
                gps_speed = Double.valueOf(item[11]);
                mileage = Double.valueOf(item[12]);
                TrafficInformation trafficInformation = new TrafficInformation(device_num, vehicleplatenumber, direction_angle, lng, lat, location_time, acc_state, right_turn_signals, left_turn_signals, hand_brake, foot_brake, gps_speed, mileage);
                inputList.add(trafficInformation);
//                System.out.println(trafficInformation.toString());
                number++;
            }
//            System.out.println(number);
//            System.out.println(inputList.get(inputList.size() - 1).toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList;
    }

}
