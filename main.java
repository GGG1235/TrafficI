

import java.io.BufferedReader;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import  java.util.Date;

public class main {
    public static void main(String[] args) {
        input_data indata=new input_data();
        String name="C:\\Users\\Han.Shelby\\Desktop\\�����ھ�\\�����ھ�\\C��ʾ������\\����1-ʾ������-100����\\AA00001.csv";
        ArrayList<TrafficInformation> trafficInformationArrayList=new ArrayList<TrafficInformation>();
        trafficInformationArrayList=indata.getInputList(name);
        System.out.println(trafficInformationArrayList.get(0).toString());
    }

}
