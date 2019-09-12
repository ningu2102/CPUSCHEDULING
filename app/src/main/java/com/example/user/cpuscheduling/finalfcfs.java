package com.example.ningu.cpuscheduling;

import java.util.*;

class finalfcfs implements AlgorithmInterface
{
	private static int n,y,p,u,maxat,c;
	private static List<String> AT = new ArrayList<String>();
	private static List<String> BT = new ArrayList<String>();
	private static float awt=0,atat=0;
	private static int WT[]= new int[100];
	private static int TAT[]= new int[100];
	private static int queue[]= new int[100];
	private static int gantchart[]= new int[1000];

	public finalfcfs(ArrayList<String> aT, ArrayList<String> bT,int noProcess){
		n = noProcess;
		AT =  aT;
		BT =  bT;
	}

	@Override
	public float getAtat() {
		return atat;
	}

	@Override
	public float getAwt() {
		return awt;
	}

	@Override
	public int getTbt() {
		return p;
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
		return gantchart;
	}

	@Override
	public void exec(){

		maxat=Integer.parseInt(AT.get(0));

		for(int i=1;i<n;i++)
		{
			if (Integer.parseInt(AT.get(i))>maxat)
				maxat=Integer.parseInt(AT.get(i));
		}

		c=0;
		for(int a=0;a<=maxat;a++)
		{
			for(int b=0;b<n;b++)
			{
				if(a==Integer.parseInt(AT.get(b)))
				{
					try {
						queue[c]=b;
						c++;
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
			}
		}

		y=Integer.parseInt(AT.get(queue[0]));
		p=0;
		u=0;
		for(int i=0;i<n;i++)
		{
			try{
				u=Integer.parseInt(BT.get(queue[i]));
			}
			catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			if(Integer.parseInt(AT.get(queue[i])) > y)
			{
				while(y < Integer.parseInt(AT.get(queue[i])))
				{
					y++;
					gantchart[p] = 0;
					p++;
				}
			}
			for(int j=0;j<u;j++)
			{
				gantchart[p]=queue[i]+1;
				p++;
				y++;
			}
			TAT[queue[i]]=y-Integer.parseInt(AT.get(queue[i]));
			WT[i]=TAT[i]-Integer.parseInt(BT.get(i));
		}
		for(int a=0;a<c;a++)
		{
			awt+=WT[a];
			atat+=TAT[a];
		}
	}
}