package com.test.hotel.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.test.hotel.nonmember.NonMemberLogin;

public class Home {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//최초 화면 메뉴!!
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1. 회원 로그인");
	      System.out.println("2. 비회원 로그인");
	      System.out.println("3. 회원가입");
	      System.out.println("4. 아이디 비밀번호찾기");
	      System.out.println();
	      System.out.print("번호 입력 : ");

		int number = Integer.parseInt(reader.readLine());
		if(number == 1) {
			LoginMenu loge = new LoginMenu();
			loge.main(args);
		} else if(number == 2) {
			NonMemberLogin nonloge = new NonMemberLogin();
			nonloge.main(args);
		} else if(number == 3) {
			Sign sign = new Sign();
			sign.main(args);
		} else {
			System.out.println("번호 입력을 잘 못하셨습니다.");
			System.out.println();
			main(args);
			
		}
		
		
	}
}
