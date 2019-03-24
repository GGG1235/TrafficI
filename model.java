import java.util.ArrayList;

public class model {


    public ArrayList<TrafficState> check_isFatigue_driving(ArrayList<TrafficState> trafficStates)
    {

        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);
//        for(int i=0;i<10;i++) {
//            System.out.println(trafficStates.get(i).location_time);
//        }
        int acc_state1 = 0;
        int acc_state0 = 0;
        //trafficstatess[0].acc_state==0? 1:2;
        mylist.add(trafficstatess[0]);
        for(int i = 1; i < len; i++ )
        {
            if ( trafficstatess[i].acc_state == 1 )
            {
                acc_state1 += (trafficstatess[i].location_time.getTime() - trafficstatess[i-1].location_time.getTime())/1000;
                acc_state0 = 0;
            }
            else{
                //acc_state1++;
                acc_state0 += (trafficstatess[i].location_time.getTime() - trafficstatess[i-1].location_time.getTime())/1000;
            }

            if(acc_state0 >= 20 * 60)
            {
                acc_state1 = 0;
            }
            if(acc_state1 >=4 * 60 * 60)
            {
                trafficstatess[i].fatigue_driving = true;
            }

//            if(trafficstatess[i].fatigue_driving==true)
//            {
//                System.out.println(trafficstatess[i].location_time);
//            }
            mylist.add(trafficstatess[i]);
//            System.out.println(mylist.get(i-1).location_time);
        }

        return mylist;
    }



    public ArrayList<TrafficState> isFast_add (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);

        double accelerated_speed;
        double speed;
        double time;
        final double G=9.80665;
        mylist.add(trafficstatess[0]);
        for(int i=1;i<len;i++)
        {

            speed = (trafficstatess[i].gps_speed-trafficstatess[i-1].gps_speed)*1000.0/3600.0;
            time = (trafficstatess[i].location_time.getTime() - trafficstatess[i-1].location_time.getTime())/1000;
            accelerated_speed = speed/time;
            if( accelerated_speed >= 0.6*G )
            {
                trafficstatess[i].fast_add = true ;
            }
            mylist.add(trafficstatess[i]);
        }


        return mylist;
    }




    public ArrayList<TrafficState> isFast_del (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);
        mylist.add(trafficstatess[0]);
        double accelerated_speed;
        double speed;
        double time;
        final double G=9.80665;
        for(int i=1;i<len;i++)
        {

            speed = (trafficstatess[i].gps_speed-trafficstatess[i-1].gps_speed)*1000.0/3600.0;
            time = (trafficstatess[i].location_time.getTime() - trafficstatess[i-1].location_time.getTime())/1000;
            accelerated_speed = speed/time;
            if( accelerated_speed <= -0.6*G )
            {
                trafficstatess[i].fast_del= true ;
            }
            mylist.add(trafficstatess[i]);
        }


        return mylist;
    }
    /* 两种怠速预热判断方法, 1为默认acc=0且speed=0 作为长时间未启动  2为设置acc=0且speed时间超过阈值 作为长时间未启动  之后进行预热时间计算*/
    public ArrayList<TrafficState> islongtimefrom0to1 (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);

        int state1_cnt = 0;

        int ret;
        if ( trafficstatess[0].acc_state == 0 && trafficstatess[0].gps_speed == 0 ) {
            ret = 0;
        }
        else{
            ret = -1;
        }
        int cnt = 0;
        for(int i = 1; i < len; i++ )
        {
            if ( trafficstatess[i].acc_state == 1 && trafficstatess[i].gps_speed != 0 )
            {
                ret = -1;
                cnt = 0;
                mylist.add(trafficstatess[i]);
                continue;
            }
            if ( trafficstatess[i].acc_state == 0 && trafficstatess[i].gps_speed != 0 )
            {
                ret = -1;
                cnt = 0;
                mylist.add(trafficstatess[i]);
                continue;
            }

            if ( trafficstatess[i].acc_state == 0 && trafficstatess[i].gps_speed == 0 )
            {
                ret = 0;
                cnt = 0;
                mylist.add(trafficstatess[i]);
                continue;
            }
            if ( ret == 0 && trafficstatess[i].acc_state == 1 && trafficstatess[i].gps_speed == 0 ){
                cnt += (trafficstatess[i].location_time.getTime()-trafficstatess[i-1].location_time.getTime())/1000;
            }
            if ( cnt >= 5*60 )
            {
                trafficstatess[i].longtimefrom0to1 = true;
            }
            mylist.add(trafficstatess[i]);

        }


        return mylist;
    }

    /*
    public ArrayList<TrafficState> islongtimefrom0to1 (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);

        int state1_cnt = 0;

        int ret;
        if ( trafficstatess[0].acc_state == 0 && trafficstatess[0].gps_speed == 0 ) {
            ret = 0;
        }
        else{
            ret = -1;
        }
        int cnt = 0;
        for(int i = 1; i < len; i++ )
        {
            if ( trafficstatess[i].acc_state == 1 && trafficstatess[i].gps_speed != 0 ) //启动状态且在运行
            {
                ret = -1;
                cnt = 0;
                continue;
            }
            if ( trafficstatess[i].acc_state == 0 && trafficstatess[i].gps_speed != 0 ) //空挡滑行
            {
                ret = -1;
                cnt = 0;
                continue;
            }

            if ( trafficstatess[i].acc_state == 0 && trafficstatess[i].gps_speed == 0 ) //熄火状态且速度为0
            {
                ret += (trafficstatess[i].location_time.getTime()-trafficstatess[i-1].location_time.getTime())/1000;
                cnt = 0;
                continue;
            }

            //从熄火状态->点火状态且速度为0,进行时间累积计算 熄火状态时间

            final int acc_stata0_longtime = 5*60;
            if ( ret >= acc_stata0_longtime && trafficstatess[i].acc_state == 1 && trafficstatess[i].gps_speed == 0 ){
                cnt += (trafficstatess[i].location_time.getTime()-trafficstatess[i-1].location_time.getTime())/1000;
            }
            if ( cnt >= 5*60 )
            {
                trafficstatess[i].longtimefrom0to1 = true;
            }
        }

        return mylist;
    }

    */

    public ArrayList<TrafficState> islongtimefrom1to1 (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);
        mylist.add(trafficstatess[0]);

        int ret = 0;
        if ( trafficstatess[0].acc_state == 1 && trafficstatess[0].gps_speed != 0 )
        {
            ret = 1;
        }
        int cnt = 0;
        for(int i=1;i<len;i++)
        {

           if ( trafficstatess[i].acc_state == 1 && trafficstatess[0].gps_speed == 0)
           {
                cnt++;
                if ( cnt >= 300 )
                {
                    trafficstatess[i].longtimefrom1to1 = true;
                }

           }
           else {
               cnt = 0;
           }
            mylist.add(trafficstatess[i]);
        }

        return mylist;
    }

    public ArrayList<TrafficState> isacc_state_0 (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);
        mylist.add(trafficstatess[0]);

        int cnt = 0;
        for(int i = 0; i < len; i++ )
        {
            if ( trafficstatess[i].acc_state = 1 && trafficstatess[i].gps_speed !=0 && trafficstatess[i].hand_brake ==0 && trafficstatess[i].fot_brake ==0 )
            {
                cnt++;
                if ( cnt >= 5 )
                {
                    trafficstatess[i].acc_state_0 = true;
                }
            }
            else{
                cnt = 0;
            }
            mylist.add(trafficstatess[i]);
        }
        return mylist;
    }

    public ArrayList<TrafficState> ishighspeed (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);
        mylist.add(trafficstatess[0]);

        return mylist;
    }

    // 计算预计转角
    private
        double phi(double t)
        {
            double sig;
            double K=40.92;
            double Td=0.72;
            double Tr=0.2;
            double te=6;
            sig=Math.abs( K*Math.sin( Td*(t-Tr) ) );
            return sig;
        }



    public ArrayList<TrafficState> issuddenchange (ArrayList<TrafficState> trafficStates)
    {
        ArrayList<TrafficState> mylist=new ArrayList<TrafficState>();
        int len=trafficStates.size();
        TrafficState[] trafficstatess=new TrafficState[ trafficStates.size()] ;
        trafficstatess= trafficStates.toArray(trafficstatess);
        mylist.add(trafficstatess[0]);

        for(int i = 0; i < len; i++ )
        {
            for(int j=1;j<=6;j++)
            {
                if(phi(j)>Math.abs( trafficstatess[i].direction_angle -trafficstatess[i+j].direction_angle) )
                {
                    trafficstatess[i].sudden_change=true;
                    if(trafficstatess[i].right_turn_signals== 0 && trafficstatess[i].direction_angle-trafficstatess[i+j].direction_angle>0)
                    {
                        trafficstatess[i].sudden_change1=true;
                    }
                    if(trafficstatess[i].left_turn_signals== 0 && trafficstatess[i].direction_angle-trafficstatess[i+j].direction_angle<0)
                    {
                        trafficstatess[i].sudden_change2=true;
                    }

                }
            }
           mylist.add(trafficstatess[i]);
        }

        return mylist;
    }


}
