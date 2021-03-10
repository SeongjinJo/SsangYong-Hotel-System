package com.test.hotel.hotelinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Main { // 보류 
	public static void main(String[] args) {

		
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\class\\java\\roomdata.dat"));
		Random rand = new Random();
		
		String[] htnamed = {"롯데","신라","힐튼","체인","하얏트"};
		String[] htnumberd = {"L-","S-","H-","C-","HY-"};
		int[] htnumberingd = {0,0,0,0,0};
		String[] themed = {"호캉스","힐링","익사이팅","패밀리","쇼핑"};
		int[] sized = {2,4,6};
		String[] typed = {"온돌","스위트","디럭스","더블디럭스","패밀리","슈페리어"};
		String[] viewd = {"Ocean","Garden","Mountain","City","Airport"};
		String[] badd = {"King","Queen","Double","Single"};
		boolean[] smokingd = {true,false};
		boolean[] disorderd = {true,false};
		boolean[] petd = {true,false};
		boolean[] poold = {true,false};
		boolean[] tvd = {true,false};
		boolean[] airconditionerd = {true,false};
		boolean[] bard = {true, false};
		
		int count = 5001;
		String[] htname = new String[count];
		String[] htnumber = new String[count];
		int[] htnumbering = new int[count];
		String[] theme = new String[count];
		int[] size = new int[count];
		String[] type = new String[count];
		String[] view = new String[count];
		String[] bad = new String[count];
		boolean[] smoking = new boolean[count];
		boolean[] disorder = new boolean[count];
		boolean[] pet = new boolean[count];
		boolean[] pool = new boolean[count];
		boolean[] tv = new boolean[count];
		boolean[] airconditioner = new boolean[count];
		boolean[] bar = new boolean[count];
		int[] money = new int[count];
		
		String[] result = new String[count];

		
		
		for(int i = 1; i < count; i++) {
			int num1= rand.nextInt(3);
			int num2=rand.nextInt(5);
			int num3 = rand.nextInt(5);
			htname[i] = htnamed[num3];
			htnumber[i] = htnumberd[num3];
			htnumbering[i] = ++htnumberingd[num3];
			theme[i] = themed[rand.nextInt(5)];
			size[i] = sized[num1];
			type[i] = typed[num2];
			view[i] = viewd[rand.nextInt(5)];
			bad[i] = badd[rand.nextInt(4)];
			smoking[i] = smokingd[rand.nextInt(2)];
			disorder[i] = disorderd[rand.nextInt(2)];
			pet[i] = petd[rand.nextInt(2)];
			pool[i] = poold[rand.nextInt(2)];
			tv[i] = tvd[rand.nextInt(2)];
			airconditioner[i] = airconditionerd[rand.nextInt(2)];
			bar[i] =bard[rand.nextInt(2)];
			money[i] = (num1+1) * 130000 + (num2+1) * 30000 +((rand.nextInt(300))*1000);
			result[i] = String.format("%d,%s,%s%d,%s,%d,%s,%s,%s,%b,%b,%b,%b,%b,%b,%b,%d",
										i
										,htname[i]
										,htnumber[i]
										,htnumbering[i]
										,theme[i]
										,size[i]
										,type[i]
										,view[i]
										,bad[i]
										,smoking[i]
										,disorder[i]
										,pet[i]
										,pool[i]
										,tv[i]
										,airconditioner[i]
										,bar[i]
										,money[i]);	
			writer.write(result[i]+"\n");
			System.out.println(i );
		}

		
		writer.close();
		
		
		
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	
	}
}
