package com.example.user.cpuscheduling;

/**
 * Created by USER on 9/2/2017.
 */
import java.util.*;
public class finalrr
{
    private static int n;


    private static int y;
    private static int ts;
    private List<String> AT = new ArrayList<String>();
    private List<String> BT = new ArrayList<String>();
    private List<String> RT;
    public static float avgtt=0;
    public static float avgwt=0;
    public static float getAvgwt() {
        return avgwt;
    }



    public static float getAvgtt() {
        return avgtt;
    }


    public int tbt=0;
    public  int TAT[]= new int[100];
    public  int p[]= new int[100];
    public int queue[]= new int[100];

    public int[] getGanttchart() {
        return ganttchart;
    }

    public int ganttchart[]= new int[100];


    public int[] getWT() {
        return WT;
    }

    public  int WT[]= new int[100];

    public  int[] getTAT() {
        return TAT;
    }

    int gp = 0;

    public  int getTbt() {
        return gp;
    }


    public finalrr(ArrayList<String> aT, ArrayList<String> bT,int noProcess,int timeSlice){
        n = noProcess;
        AT =  aT;
        BT =  bT;
        ts=timeSlice;
    }

    public void execrr()
    {
        for(y=0;y<n;y++)
        {
            p[y]=y+1;
        }
        RT = new ArrayList<String>(BT);


        int a,j,temp;

        for(a=0;a<n;a++)
        {
            for(j=a+1;j<n;j++)
            {
                if(Integer.parseInt(AT.get(a)) > Integer.parseInt(AT.get(j)))
                {
                    temp = Integer.parseInt(AT.get(a));
                    AT.set(a, String.valueOf(Integer.parseInt(AT.get(j))));
                    AT.set(j, String.valueOf(temp));

                    temp = Integer.parseInt(BT.get(a));
                    BT.set(a, String.valueOf(Integer.parseInt(BT.get(j))));
                    BT.set(j, String.valueOf(temp));

                    temp = Integer.parseInt(RT.get(a));
                    RT.set(a, String.valueOf(Integer.parseInt(RT.get(j))));
                    RT.set(j, String.valueOf(temp));

                    temp = p[a];
                    p[a] = p[j];
                    p[j] = temp;


                }
            }
        }

        int time,remain=n;
        int i,l,flag=0;
        for(time=0,i=0;remain!=0;)
        {
            if(Integer.parseInt(RT.get(i))<=ts && Integer.parseInt(RT.get(i))>0)

            {
                time = time + Integer.parseInt(RT.get(i));
                for(l=0;l<Integer.parseInt(RT.get(i));l++)
                {
                    ganttchart[gp]=p[i];
                    gp++;
                }
                RT.set(i, String.valueOf(0));
                flag=1;
            }

            else if(Integer.parseInt(RT.get(i)) > 0)
            {
                //Integer.parseInt(RT.get(i)) = Integer.parseInt(RT.get(i)) - ts;
                RT.set(i, String.valueOf(Integer.parseInt(RT.get(i))-2));
                time = time + ts;
                for(l=0;l<ts;l++)
                {
                    ganttchart[gp]=p[i];
                    gp++;
                }
            }
            if(Integer.parseInt(RT.get(i))==0 && flag==1)
            {
                remain--;
                TAT[i] = time-Integer.parseInt(RT.get(i));
                WT[i] = time-Integer.parseInt(RT.get(i))-Integer.parseInt(BT.get(i));
                avgwt = avgwt + time-Integer.parseInt(RT.get(i))-Integer.parseInt(BT.get(i));
                avgtt = avgtt + time-Integer.parseInt(RT.get(i));
                flag=0;
            }
            if(i==n-1)
                i=0;
            else if(Integer.parseInt(AT.get(i+1)) <= time)
                i++;
            else
                i=0;
        }
    }
}