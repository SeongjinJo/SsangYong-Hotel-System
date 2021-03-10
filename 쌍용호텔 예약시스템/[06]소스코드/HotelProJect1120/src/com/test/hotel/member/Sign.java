package com.test.hotel.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sign {
	
public static void main(String[] args) throws IOException { 
		System.out.println("=환영합니다. 회원님 호텔입니다.^^=");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("======8~16자,영어소문자 +숫자=====");
		System.out.print("아이디를 입력해 주세요 : ");
		String id = reader.readLine();
		id = checkId(id);
		System.out.println("=======아이디 저장완료 굿!========");
		System.out.println();
		
		System.out.println("======8~16자,영어 문자 +숫자=====");
		System.out.print("비밀번호를 입력해 주세요 : ");
		String pw = reader.readLine();
		pw = checkPw(pw);
		System.out.println("==소중한 비밀번호 MyPrecious!===");
		System.out.println();
		
		System.out.println("======= 이름을 알려주게나 ========");
		System.out.println("====☆2~5글자 ☆한글만입력가능====");
		System.out.print("이름을 입력해 주세요 : ");
		String name = reader.readLine();
		name = checkName(name);
		System.out.println();
		
		System.out.println("==아래의 사이트만 가입가능합니다.==");
		System.out.println("======> @gmail.com @naver.com======");
		System.out.println("======> @nate.com  @daum.net=======");
		System.out.println("=========총 가능 이메일 4개========");
		System.out.print("이메일을 입력해 주세요 : ");
		String email = reader.readLine();
		email = checkEmail(email);
		System.out.println();
		
		System.out.println("======언제 선물 주면 될까요?=======");
		System.out.println("생년월일을 입력하세요. 예)19990101");
		System.out.print("생년월일을 입력해 주세요 : ");
		String birthday = reader.readLine();
		birthday = checkBirthday(birthday);
		System.out.println("");
		
		System.out.println("====핸드폰 번호를 입력해주세요.===");
		System.out.println("========11자리 혹은 10자리 =======");
		System.out.println("========예)01012345678, 0121231234");
		System.out.print("핸드폰번호를 입력해 주세요 : ");
		String phone = reader.readLine();
		phone = checkPhone(phone);
		System.out.println();
		
		System.out.println("======다왔어 조금만 힘내!!========");
		System.out.println("========주소를 입력해주세요.======");
		System.out.println("예)서울 영등포구 국제금융로");
		System.out.print("주소를 입력해 주세요 : ");
		String address = reader.readLine();
		System.out.println();
		
		System.out.println("입력하신 정보로 회원가입이 진행됩니다.");
		System.out.printf("ID : %s\nNAME : %s\nEMAIL : %s\nBIRTHDAY : %s\nPHONE : %s\naddress : %s\n",id,name,email,birthday,phone,address);
		
		String info = "";
		info = String.format("%s,%s,%s,%s,%s,%s,%s,0,0,0", name,id,pw,address,phone,birthday,email);
		//경로확인
		System.out.println();
		
		
		// 경로 출력
		String path = "C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt";
		System.out.println();
		System.out.println("==== 나의 동료가 되어 주게나 ...===");
		System.out.println("다시 시작하려면 'b' 를 입력해주세요");
		System.out.println("진행하시려면 enter 를 입력해주세요");
		String reStart =reader.readLine();
		if (reStart.equals("b")) {
			main(args);
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
			BufferedReader number = new BufferedReader(new FileReader(path));
			
			String line = null;
			String save = "";
			while((line=number.readLine())!=null) {
				String[] temp =line.split(","); // 콤마 구분자
				save = temp[0];
				 // 0번째인 것이 숫자 
			}
			
			number.close();
			int saveNumber =Integer.parseInt(save);
			
			
			saveNumber =saveNumber+1; // 마지막숫자 +1
				writer.newLine();
				writer.append(saveNumber+","+info);
				writer.close();
				System.out.println("====☆많은 이용 부탁드립니다.☆====");
				Home home =new Home();
				home.main(args); // 회원 가입후 홈화면으로 이동.
			
		} catch (IOException e) {
			System.out.println("error");
			System.out.println("관리자에게 문의 바랍니다. 02-1234-5678");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//main


	private static String checkPhone(String phone) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		boolean result = true; //자리수 검사
		//, 0=48 9=57
		int count = 0; //@체크용
		for(int i=0;i<phone.length();i++) {
			if(!((int)phone.charAt(i)>=48&&(int)phone.charAt(i)<=57)) {
				count++;
			}		
		}
		
		if (count>0) {
			System.out.println("숫자를 입력해주세요");
			System.out.println("====핸드폰 번호를 입력해주세요.===");
			System.out.println("======11자리 혹은 10자리 ======");
			System.out.println("=예)01012345678, 0121231234");
			phone = reader.readLine();
			checkPhone(phone);
			
		}
		

		while (result) { // 자릿수 검사
			if (!(phone.length() >= 10&&phone.length() <= 11 )) {
				System.out.println("자리수를 확인하세요");
				System.out.println("====핸드폰 번호를 입력해주세요.===");
				System.out.println("======11자리 혹은 10자리 ======");
				System.out.println("=예)01012345678, 0121231234");
				phone = reader.readLine();
	
			} else {
				break;
			}
		}
			
		
		
		return phone;
	
		
	
	}//phone


	private static String checkBirthday(String birthday) throws IOException {
		boolean result = true; //자리수 검사
		//, 0=48 9=57
		int count = 0; //@체크용
		for(int i=0;i<birthday.length();i++) {
			if(!((int)birthday.charAt(i)>=48&&(int)birthday.charAt(i)<=57)) {
				count++;
			}		
		}
		
		if (count>0) {
			System.out.println("숫자를 입력해주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("생년월일을 입력하세요. 예)19990101");
			System.out.print("생년월일을 입력해 주세요 : ");
			birthday = reader.readLine();
			checkBirthday(birthday);
			
		}
		

		while (result) { // 자릿수 검사
			if (!(birthday.length() == 8 )) {
				System.out.println("생년월일의 자리수를 확인해주세요");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("생년월일을 입력하세요. 예)19990101");
				System.out.print("생년월일을 입력해 주세요 : ");
				birthday = reader.readLine();
	
			} else {
				break;
			}
		}
		
		if (!(Integer.parseInt(birthday.substring(0,4))>=1930&&Integer.parseInt(birthday.substring(0,4))<=2010)) {
			System.out.println("년도를 확인해주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("생년월일을 입력하세요. 예)19990101");
			System.out.print("생년월일을 입력해 주세요 : ");
			birthday = reader.readLine();
			checkBirthday(birthday);
		} else if (!(Integer.parseInt(birthday.substring(4,6))>=1&&Integer.parseInt(birthday.substring(4,6))<=12)) {
			System.out.println("월를 확인해주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("생년월일을 입력하세요. 예)19990101");
			System.out.print("생년월일을 입력해 주세요 : ");
			birthday = reader.readLine();
			checkBirthday(birthday);
		}  else if (!(Integer.parseInt(birthday.substring(6))>=1&&Integer.parseInt(birthday.substring(6))<=31)) {
			System.out.println("일을 확인해주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("생년월일을 입력하세요. 예)19990101");
			System.out.print("생년월일을 입력해 주세요 : ");
			birthday = reader.readLine();
			checkBirthday(birthday);
		}
		
		
		
		
		
		return birthday;
	}// birthday


	private static String checkEmail(String email) throws IOException {
		int count = 0; //@체크용
		//@gmail.com, @naver.com, @nate.com, @daum.net
		for(int i=0;i<email.length()-1;i++) {
			if(email.charAt(i)=='@') {
				count =i;
			}		
		}
		if (count==0) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("도메인을 확인하세요");
			System.out.println("==아래의 사이트만 가입가능합니다.==");
			System.out.println("==> @gmail.com @naver.com==");
			System.out.println("==> @nate.com  @daum.net===");
			System.out.println("=======총 가능 이메일 4개=======");
			System.out.print("이메일을 입력해 주세요 : ");
			email = reader.readLine();
			checkEmail(email);
			
		}
		
		if(!(email.substring(count+1).equals("gmail.com")||email.substring(count+1).equals("naver.com")||
				email.substring(count+1).equals("nate.com")||email.substring(count+1).equals("daum.net"))) {
			System.out.println("주소를 확인해 주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("==아래의 사이트만 가입가능합니다.==");
			System.out.println("==> @gmail.com @naver.com==");
			System.out.println("==> @nate.com  @daum.net===");
			System.out.println("=======총 가능 이메일 4개=======");
			System.out.print("이메일을 입력해 주세요 : ");
			email = reader.readLine();
			checkEmail(email);
		}
		
		
		
		
		return email;
	}


	private static String checkName(String name) throws IOException {
	// 한글이름만 길이는 2-5글자. 가 =44032 힇 =55175
		
		boolean result = true;
		int count = 0;// 한글이름 확인

		while (result) { // 자릿수 검사
			if (!(name.length() >= 2 && name.length() <= 5)) {
				System.out.println("이름 자리수를 확인해주세요");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("====☆2~5글자 ☆한글만입력가능===");
				System.out.print("이름을 입력해 주세요 : ");
				name = reader.readLine();
	
			} else {
				break;
			}
		}
				
		char[] nameChar = new char[name.length()];
		for(int i=0; i<name.length();i++) {
			nameChar[i] = name.charAt(i);
		}
		
		
		for(int i=0; i<nameChar.length;i++) { // 가 =44032 힇 =55175
			if (!((int)nameChar[i]>=44032&&(int)nameChar[i]<=55175)) {
				count++;
			}
		
		}
		
		if (count>0) {
			System.out.println("한글만 입력하였는지 확인해 주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("====☆2~5글자 ☆한글만입력가능===");
			System.out.print("이름을 입력해 주세요 : ");
			name = reader.readLine();
			checkName(name);
		}
		
		
		return name;
		
	}//name


		
		//==================
		
		
	private static String checkPw(String pw) throws IOException {
		//A==65, Z==90, a ==97 ,z122
		//, 0=48 9=57
		boolean result = true;
		
		int count1 = 0; // 숫자체크
		int count2 = 0; // 소문자 체크
		int count3 = 0; // 대문자 체크 
		
		while(result) { //자릿수 검사 
			if (!(pw.length()>=8&&pw.length()<=16)){
				System.out.println("비밀번호 자리수를 확인해주세요");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("=====8~16자,영어 문자 +숫자====");
				System.out.print("비밀번호를 입력해 주세요 : ");
				pw = reader.readLine();
				
			} else {
				break;
			}
		}// pw
		
		
		
		char[] pwChar = new char[pw.length()];
		for(int i=0; i<pw.length();i++) {
			pwChar[i] = pw.charAt(i);
		}
		
		
		for(int i=0; i<pwChar.length;i++) { // 비밀번호 유효성 검사 대소문자
		
			if ((int)pwChar[i]>=49&&(int)pwChar[i]<=57) {
				count1++; 
			}else if ((int)pwChar[i]>=97&&(int)pwChar[i]<=122) {
				count2++;
			}else if ((int)pwChar[i]>=65&&(int)pwChar[i]<=90) {
				count3++;
			}
		}
		
		if (!(count1>0&&count2>0&&count3>0)){
			System.out.println("비밀번호 양식을 확인해주세요");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=====8~16자,영어 문자 +숫자====");
			System.out.print("비밀번호를 입력해 주세요 : ");
			pw = reader.readLine();
			pw = checkPw(pw);
		} 
		
		
		
		
		return pw;
	}


	private static String checkId(String id) throws IOException {
		boolean result = true;
		String path = "C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt";
		BufferedReader readerName = new BufferedReader(new FileReader(path));
		
		String line ="";
		
		boolean checkName = false;
		
		while ((line=readerName.readLine())!=null) {
			String[] oldName = line.split(",");
			String saveOldName = oldName[2];
			if(id.equals(saveOldName)) {
				checkName = true;
			}
		
		}
		readerName.close();
		
		while(result) {
			if (!(id.length()>=8&&id.length()<=16)){
				System.out.println("아이디 자리수를 확인해주세요");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("====8~16자,영어소문자 +숫자====");
				System.out.print("아이디를 입력해 주세요 : ");
				id = reader.readLine();
				
				
			}else if (checkName) {
				System.out.println("일치하는 아이디가 있습니다.");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("====8~16자,영어소문자 +숫자====");
				System.out.print("아이디를 입력해 주세요 : ");
				id = reader.readLine();
				
			} else {
				break;
			}
			
		}
		
		return id;
	}

}
	