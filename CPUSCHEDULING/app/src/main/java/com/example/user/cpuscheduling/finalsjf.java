package com.example.user.cpuscheduling;

/**
 * Created by USER on 9/2/2017.
 */



import java.util.*;
public class finalsjf

{
    private static int n;
    private List<String> AT = new ArrayList<String>();
    private List<String> BT = new ArrayList<String>();


    public static float getAwt() {
        return awt;
    }

    public static float awt=0;

    public static float getAtat() {
        return atat;
    }

    public static float atat=0;

    public int getTbt() {
        return tbt;
    }

    public int tbt=0;

    public static int[] getWT() {
        return WT;
    }

    public static int WT[]= new int[100];

    public static int[] getTAT() {
        return TAT;
    }

    public static int TAT[]= new int[100];

    public static int[] getGantchart() {
        return gantchart;
    }

    //public static int queue[]= new int[100];
    public static int gantchart[]= new int[100];



    public finalsjf(ArrayList<String> aT, ArrayList<String> bT,int noProcess){
        n = noProcess;
        AT =  aT;
        BT =  bT;
    }

    public void excuteSJf()

    {
        //int tbt = 0;
        for(int i = 0; i < n; i++)
        {
            tbt += Integer.parseInt(BT.get(i));
        }
        //int ganttchart[] = new int[tbt];

        for(int i = 0; i < tbt; i++)
        {
            int p = 0;
            int min = 99999;
            for(int j = 0; j < n; j++)
            {
                if( Integer.parseInt(AT.get(j)) <= i)
                {
                    if( Integer.parseInt(BT.get(j)) < min &&  Integer.parseInt(BT.get(j)) != 0)
                    {
                        min =  Integer.parseInt(BT.get(j));
                        p = j;
                    }
                }
            }


            gantchart[i] =p+1;


            //BT.get(p)--;
            BT.set(p, String.valueOf(Integer.parseInt(BT.get(p))-1));

            for(int j = 0; j < n; j++)
            {
                if( Integer.parseInt(AT.get(j)) <= i)
                {
                    if( Integer.parseInt(BT.get(j)) != 0)
                    {
                        TAT[j]++;
                        if(j != p)
                            WT[j]++;
                    }
                    else if(j ==p)
                        TAT[j]++;
                }
            }




        }
    }

}
