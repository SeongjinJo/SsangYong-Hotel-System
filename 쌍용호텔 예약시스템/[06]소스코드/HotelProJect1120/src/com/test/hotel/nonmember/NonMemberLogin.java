package com.test.hotel.nonmember;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class NonMemberLogin {
	
	public static void main(String[] args) {
		// txt
		
		System.out.println("=================비회원 로그인=================");
		System.out.println("***회원가입을 하시면 멤버쉽 혜택을 받을 수 있습니다***");
		System.out.println();
		
		
		try {

			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\noncustomer28.txt"));

			Scanner scan = new Scanner(System.in);

			System.out.print("이름(2~5자 사이 입력) 입력 : ");
			String inputName = scan.nextLine();

			boolean check = false;
			
			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");

				if (temp[1].equals(inputName)) { 

					System.out.print("이메일 주소 : ");
					String inputEmail = scan.nextLine();

					if (temp[2].equals(inputEmail)) { 
						System.out.println("사용 가능한 이메일 주소입니다.");
						check = true; 
						break; 
					}
				}

			}
			
			if(check == false) { 
				
				System.out.println("로그인 실패입니다.");
				System.out.println("다시 입력해주세요\n");
				pause();// 돌아가기 수정하기
				
			}
			
		} catch (Exception e) {
			System.out.println("NonMemberLogin.main()");
			e.printStackTrace();
		}

	}

	private static void pause() {
		main(null);

	}
}
