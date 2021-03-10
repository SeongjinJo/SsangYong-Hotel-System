package com.test.hotel.member;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.test.hotel.festival.Info;
import com.test.hotel.reservation.Main;

public class MemberManu {
	
	public static void main(String[] args,String num) throws IOException {
		
		System.out.println();
		String number = num; //회원 번호. 
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));
			

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				
				if(number.equals(temp[0])) {
					System.out.printf("%s 회원님 환영 합니다.\n",temp[1]);
				}

			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 경로

		// 회원메듀
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	     System.out.println("===========================================");
	     System.out.println("1. 호텔예약");
	      System.out.println("2. 이벤트 정보확인");
	      System.out.println("3. 마이페이지");
	      System.out.println("4. FAQ");
	      System.out.println("5. 로그아웃");
	      System.out.println("6. 문의");
	      System.out.println("===========================================");
	      System.out.print("번호 입력 : ");

		int count = Integer.parseInt(reader.readLine());
		if(count == 1) { //호텔예약
			Main reservation = new Main();
			reservation.main(args);
			
			
		} else if(count == 2) { // 이벤트정보확인
			Info infomation = new Info();
			infomation.main(args, number);
			
		} else if(count == 3) { // 마이페이지
			MyPage page = new MyPage();
			page.main(args, number);
			
		} else if(count == 4) { // FAQ
			// 올려 주심. 
		} else if(count == 5) { // 로그아웃
			System.out.println("로그아웃합니다");
			System.out.println("감사합니다.");
			
			Home home =new Home();
			home.main(args);
			
		} else if(count == 6) { // 문의
			// 문의 
		}
		
	
		
	}

}
