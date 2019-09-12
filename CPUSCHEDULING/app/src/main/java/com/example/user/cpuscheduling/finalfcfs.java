package com.example.user.cpuscheduling;

import java.util.*;

class finalfcfs
{
	private static int n;
	private List<String> AT = new ArrayList<String>();
	private List<String> BT = new ArrayList<String>();


	public  float awt=0;
	public  float atat=0;
	public int tbt=0;
	public int y,p,u;
	public     int WT[]= new int[100];
	public  int TAT[]= new int[100];
	public  int queue[]= new int[100];
	public  int gantchart[]= new int[1000];


	public int getTbt() {
		return y;
	}
	public float getAtat() {
		return atat;
	}
	public float getAwt() {
		return awt;
	}
	public int[] getWT(){
		return WT;
	}

	public int[] getTAT(){
		return TAT;
	}
	public int[] getGantchart(){
		return gantchart;
	}

	public int[] getqueue(){
		return queue;
	}



	public finalfcfs(ArrayList<String> aT, ArrayList<String> bT,int noProcess){
		n = noProcess;
		AT =  aT;
		BT =  bT;
	}


	public void excuteFCFS() {

		for(int i=0;i<n;i++)
		{

			tbt+= Integer.parseInt(BT.get(i));
		}


		int maxat=Integer.parseInt(AT.get(0));

		for(int i=1;i<n;i++)
		{
			if (Integer.parseInt(AT.get(i))>maxat)
				maxat=Integer.parseInt(AT.get(i));
		}
		int c=0;
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

	public int[] exec(){
		return WT;
	}

}