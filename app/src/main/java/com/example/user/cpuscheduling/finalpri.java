package com.example.user.cpuscheduling;

/**
 * Created by USER on 9/3/2017.
 */
import java.util.*;
public class finalpri implements AlgorithmInterface {

    private static int n;
    private static int y;
    private static int ts;
    private List<String> AT = new ArrayList<String>();
    private List<String> BT = new ArrayList<String>();
    private List<String> PT = new ArrayList<String>();
    private List<String> RT;
    int gp = 0;
    public static float avgtt = 0;
    public static float avgwt = 0;
    public int TAT[] = new int[100];
    public int WT[] = new int[100];
    public int gantt[] = new int[100];

    public finalpri(ArrayList<String> aT, ArrayList<String> bT, int noProcess, ArrayList<String> pT) {
        n = noProcess;
        AT = aT;
        BT = bT;
        PT = pT;
    }

    @Override
    public void exec(){

        RT = new ArrayList<String>(BT);
        int smallest, time, remain = n;
        //PT.set(n, String.valueOf(9999));
        PT.add(n, String.valueOf(9999));
        for (time = 0; remain != 0; time++) {
            smallest = n;
            for (int i = 0; i < n; i++) {
                if (Integer.parseInt(AT.get(i)) <= time && Integer.parseInt(PT.get(i)) < Integer.parseInt(PT.get(smallest)) && Integer.parseInt(RT.get(i)) > 0) {
                    smallest = i;

                }

            }
            gantt[gp] = smallest + 1;
            gp++;


            RT.set(smallest, String.valueOf(Integer.parseInt(RT.get(smallest)) - 1));
            if (Integer.parseInt(RT.get(smallest)) == 0) {
                remain--;
                TAT[smallest] = time + 1 - Integer.parseInt(AT.get(smallest));
                WT[smallest] = time + 1 - Integer.parseInt(AT.get(smallest)) - Integer.parseInt(BT.get(smallest));
                avgwt += WT[smallest];
                avgtt += TAT[smallest];
            }

        }
    }

    @Override
    public int[] getWt() {
        return WT;
    }

    @Override
    public int[] getTat() {
        return TAT;
    }

    @Override
    public int[] getGnattchart() {
        return gantt;
    }

    @Override
    public float getAtat() {
        return avgtt;
    }

    @Override
    public float getAwt() {
        return avgwt;
    }

    @Override
    public int getTbt() {
        return gp;
    }
}
