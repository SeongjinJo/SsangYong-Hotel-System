package com.test.hotel.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoginMenu {

	public static void main(String[] args) throws IOException {

		String number = "";
		System.out.println("1. 회원 로그인");
		System.out.println("2. 관리자 로그인");

		Scanner login = new Scanner(System.in);

		System.out.print("입력 : ");
		int choiceLogin = login.nextInt();

		if (choiceLogin == 1) {
			number = choiceMember();
		} else if (choiceLogin == 2) {
			choiceAdmin();
		}
		
		MemberManu menu = new MemberManu();
		menu.main(args, number);
		
		
	}
	// 관리자 로그인
	private static void choiceAdmin() {
		
		boolean result = true;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("아이디 입력 : ");
		String inputId = scan.nextLine();

		//관리자 아이디 1개만 있다 가정. 추후 수정 가능
		if (inputId.equals("admin123")) { 
			
			while(result) {
				
				System.out.print("비밀번호 : ");
				String inputPwd = scan.nextLine();
				
				if (inputPwd.equals("0000")) {
					System.out.println("admin123로그인 성공");
					// 첫번쨰 화면으로 돌아가기 
					result = false;
					break;
				} else {
					System.out.println("비밀번호가 맞지 않습니다.");
				}
				
			}

		} else {
			System.out.println("아이디를 다시 입력해주세요");
			choiceAdmin();
		}

	}
	
	// 회원 로그인 
	private static String choiceMember() {
		int number =0;
		String memNumber ="";
		try {

			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt")); // 경로

			Scanner scan = new Scanner(System.in);

			System.out.print("아이디 입력 : ");
			String inputId = scan.nextLine();

			boolean check = false;

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");

				if (temp[2].equals(inputId)) { // temp[2] 아이디랑 사용자 입력 아이디랑 일치하면 다음 코드 진행

					System.out.print("비밀번호 : ");
					String inputPwd = scan.nextLine();

					if (temp[3].equals(inputPwd)) { //// temp[2] 비번이랑 입력값이랑 일치하면 다음코드 진행
						number = Integer.parseInt(temp[0]);
						System.out.println("로그인이 완료되었습니다.");
						System.out.printf("%s님 환영합니다", temp[2]);
						memNumber = temp[0];
						//회원 메인 메뉴로 돌아가기 
						
						check = true; // 로그인 성공하면 check를 true로
						break;

					}
				}

			}

			if (check == false) { // 아이디 비번 실패시 출력

				System.out.println("아이디 또는 비밀번호가 틀립니다.");
				System.out.println("다시 입력해주세요\n");
				choiceMember();// 돌아가기 수정하기

			}

		} catch (Exception e) {
			System.out.println("LoginMenu.main()");
			e.printStackTrace();
		}
		return memNumber;
		

	}

	private static void pause() {
		main(null);

	}
}
