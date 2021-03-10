package com.test.hotel.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class MyPage {

	public static void main(String[] args, String num) throws IOException {

		String number = num;

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));
			

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				
				if(number.equals(temp[0])) {
				
					System.out.println(temp[1]+"회원님 마이페이지");
				}

			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 경로
		
		System.out.println(number);
		info(number);
		menu(number);
		MemberManu manu2 = new MemberManu();
		manu2.main(args, number);
		

	}
	//마이페이지 - 회원정보
	private static void info(String info) {

		System.out.println();
		System.out.println("1.회원정보 출력");
		System.out.println("===========================================================================================");

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				if(info.equals(temp[0])) {
					int vip = Integer.parseInt(temp[9]);
					// 아이디,이메일,핸드폰번호,생년월일,회원등급,마일리지,쿠폰내역확인
					System.out.printf(
							"- 아이디 : %s\n" + "- 이름 : %s\n" + "- 이메일 : %s\n" + "- 핸드폰번호 : %s\n" + "- 생년월일 : %s\n" + "- 회원등급 : %s\n"
									+ "- 마일리지 : %s\n",
							temp[2], temp[1], temp[7], temp[5], temp[6], vip > 30000000 ? "VIP" : vip > 10000000 ? "Patinum": vip > 100000 ? "Gold" : "Silver", temp[10]);
					System.out.println("===========================================================================================");
					System.out.println();
				}
				
			}

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	//마이페이지 - 메뉴
	private static void menu(String info) {
			
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			
			System.out.println("2. 변경 및 탈퇴를 하시려면 해당 번호를 입력해주세요.");
			System.out.println("===========================================================================================");
			System.out.println("1.이름\t2.비밀번호\t\t3.핸드폰번호\t4.이메일주소\t5.회원탈퇴\t\t6.마일리지 사용내역조회");
			System.out.println("===========================================================================================");
			System.out.println("단 이외의 값 입력시 메인메뉴로 복귀합니다.");
			System.out.print("번호입력 : ");
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				newName(info);
			} else if (sel.equals("2")) {
				newPassword(info);
			} else if (sel.equals("3")) {
				newTel(info);
			} else if (sel.equals("4")) {
				newEmail(info);
			} else if (sel.equals("5")) {
				delMember(info);
			} else if (sel.equals("6")) {
				viewMileage(info);
			} else {
				System.out.println();
				System.out.println("메뉴로 돌아 갑니다.");
				loop = false;
			}
		}


	}
	//마일리지 사용내역 조회
	
	private static void viewMileage(String info) {

			
		String id = "";
		
		String n = ""; // 구매 수
		String co = ""; // 금액
		String mi = ""; // 가상 마일리지
		
		try {
			

			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				
				if(temp[0].equals(info)) {
					
					mi = temp[10];
					co = temp[9];
					n = temp[8];
				break;
				}
			
			}
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		int mileage = Integer.parseInt(mi);
		int cost = Integer.parseInt(co);
		int num = Integer.parseInt(n);
		
		int prCost = cost; // 망일리지 출력금액
		String king = "";
		
		int vipSt = 30000000;
		int platinumSt = 20000000;
		int goldSt = 1000000;

		
		
		double rea =0; // 실제 마일리지
		if(cost>vipSt) {
			king = "VIP";
			rea = (cost-vipSt)*0.02+ (vipSt-platinumSt)*0.01
					+ (platinumSt-goldSt)*0.004+goldSt*0.001 ;
		} else if (cost>10000000) {
			rea = + (vipSt-platinumSt)*0.01
					+ (platinumSt-goldSt)*0.004+goldSt*0.001 ;
			king = "Platinum";
			
		}else if (cost>1000000) {
			rea = (platinumSt-goldSt)*0.004+goldSt*0.001 ;
			king = "Gold";
			prCost = (int) ((prCost-10000000)*0.01);
			
		}else {
			rea = cost*0.001;
			king = "Silver";
		}
	

		int subMi = (int)rea -mileage; 
		Calendar c1 =Calendar.getInstance();
		
		System.out.println("마일리지 사용내역 { - = 사용, + = 적립}(단위 : 점)");
		System.out.println("이름 출력");
		
		if (subMi>0) {
			System.out.println("\t\t\t지출금액");
			System.out.printf("%tF\t\t%d\n",c1,subMi);
		} else {
			System.out.println("\t\t\t이벤트 당첨");
		}
		System.out.println("최종 마일리지 : " + mileage);
		

		

	}
	//회원탈퇴
	private static void delMember(String info) {
				
		String password=""; //입력받을 비밀번호
	    String del = "";
	    int num=0;
	    int count=0;//
	      
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));
			BufferedWriter delWriter = new BufferedWriter(
					new FileWriter("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt",true));

			String line = null;
			String txt = "";//

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				num++;
				count++;//
								
				
				if(info.equals(temp[0])) {
					
			
				System.out.println();
				System.out.print("비밀번호를 입력해 주세요 : ");
				Scanner scan = new Scanner(System.in);
				password = scan.nextLine();
				
				//비밀번호값이 맞으면 진행가능
				if (password.equals(temp[3])) { 
					System.out.println();
					System.out.println("회원 탈퇴를 계속 진행하시려면 1번\n"
							+ "마이페이지로 돌아가시려면 2번을 입력해주세요.");
					System.out.print("번호입력 : ");
					scan = new Scanner(System.in);
					String sel = scan.nextLine();
						
						//탈퇴 진행시 데이터 삭제
						if(sel.equals("1")) {	
							if (num==count) {
							
								line = line.replace(line,"");
							}
							txt += line + "\n";
							
							delWriter.write(txt);
							
							for(int i=1; i<=10; i ++) {
								saveMemberInfo(num,i,"");
							}

							delWriter.newLine();
							delWriter.close();
							
							
							System.out.println();
							System.out.println("탈퇴가 완료 되었습니다.\n이용해 주셔서 감사합니다.");
							pause(info);//회원탈퇴 후 최초 메뉴로 돌아감
						}else {
							menu(info);
						}

					break;	
				}
			
				}else {
					System.out.println("비밀번호가 맞지않습니다.");
				}
				}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	//이메일변경
	private static void newEmail(String info) {
		String newEmail = ""; //새로운 이메일주소
		String email = ""; //기존 이메일
		int num= 0;
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				num++;
				

				if(info.equals(temp[0])) {
				//저장되어있던 이메일주소
				
				System.out.println();
				System.out.println("기존 이메일 : " + temp[7]);
				System.out.print("변경할 이메일 : ");
				Scanner scan = new Scanner(System.in);
				newEmail = scan.nextLine();

				break;
				}
			}
			System.out.println();
			System.out.println("맞으면 1, 다시 입력하시려면 2를 입력해주세요.");
			System.out.print("번호입력 : ");
			Scanner scan = new Scanner(System.in);
			String sel = scan.nextLine();
			System.out.println();
			
			boolean result = true;
			
			while(result) {
				
				if (sel.equals("1")) {
					System.out.println("성공하였습니다.");
					saveMemberInfo(num,7,newEmail);
					pause(info);
				} else if (sel.equals("2")) {
					System.out.print("변경할 이메일 : ");
					scan = new Scanner(System.in);
					newEmail = scan.nextLine();
					
					System.out.println("맞으면 1, 다시 입력하시려면 2를 입력해주세요.");
					System.out.print("번호입력 : ");
					scan = new Scanner(System.in);
					sel = scan.nextLine();

				}
			}

			

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	//전화번호 변경
	private static void newTel(String info) {
		String newTel = ""; //새로받을 전화번호
		String tel = ""; //기존 전화번호
		 //기존 전화번호
		
		
		int num=0;
		
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				num++;

				if(info.equals(temp[0])) {
				
				System.out.println();
				System.out.println("기존 전화번호 : " +temp[5]);
				System.out.print("변경할 전화번호 : ");
				Scanner scan = new Scanner(System.in);
				newTel = scan.nextLine();
				
				

				break;
				}
			}
			System.out.println();
			System.out.println("맞으면 1, 다시 입력하시려면 2를 입력해주세요.");
			System.out.print("번호입력 : ");
			Scanner scan = new Scanner(System.in);
			String sel = scan.nextLine();
			System.out.println();
			
			boolean result = true;
			
			while(result) {
				
				if (sel.equals("1")) {
					System.out.println("성공하였습니다.");
					saveMemberInfo(num,5,newTel);
					pause(info);
					
				} else if (sel.equals("2")) {
					System.out.print("변경할 전화번호 : ");
					scan = new Scanner(System.in);
					newTel = scan.nextLine();
					
					System.out.println("맞으면 1, 다시 입력하시려면 2를 입력해주세요.");
					System.out.print("번호입력 : ");
					scan = new Scanner(System.in);
					sel = scan.nextLine();
			}

			}

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	//비밀번호 변경
	private static void newPassword(String info) {
		System.out.println("비밀번호 ");
		String password = "";
			
		String newPassword1 = "";
		String newPassword2 = "";
		int num=0;
		
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));

			String line = null;
			System.out.println("비빔면");
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				num++;

				if (info.equals(temp[0])) {

					System.out.println();
					System.out.print("기존 비밀번호를 입력해 주세요 : ");
					Scanner scan = new Scanner(System.in);
					password = scan.nextLine();
					
					if (password.equals(temp[3])) {
						System.out.print("새로 사용하실 비밀번호를 입력해 주세요 : ");
						scan = new Scanner(System.in);
						newPassword1 = scan.nextLine();
						System.out.print("비밀번호를 다시 입력 해 주세요 : ");
						scan = new Scanner(System.in);
						newPassword2 = scan.nextLine();

						boolean result = true;

						while (result) {
							if (newPassword1.equals(newPassword2)) {
								break;
							} else {
								System.out.println("다시 입력해주세요.");
								System.out.print("새로 사용하실 비밀번호를 입력해 주세요 : ");
								scan = new Scanner(System.in);
								newPassword1 = scan.nextLine();
								System.out.print("비밀번호를 다시 입력 해 주세요 : ");
								scan = new Scanner(System.in);
								newPassword2 = scan.nextLine();
							}

						}

					} else {
						System.out.println("비밀번호가 일치하지않습니다.");
						pause(info);
					}
				}

			}
			
			System.out.println();
			System.out.println("비밀번호가 맞으면 1, 다시 입력하시려면 2를 입력해주세요."); // 바꾸고 싶은 번호 
			System.out.print("번호입력 : ");
			Scanner scan = new Scanner(System.in);
			String sel = scan.nextLine();
			System.out.println();

			
			num = Integer.parseInt(info);
			
			if (sel.equals("1")) {
				System.out.println("성공하였습니다.");
				saveMemberInfo(num,3,newPassword1);
				pause(info);
			} else if (sel.equals("2")) {
				newPassword(info);
			}

			reader.close();

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();

		}

	}
	//이름변경
	private static void newName(String info) {
		String newName = "";
		//사람이름 받아오기
		String name = "";
		
		int num=0;
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				num++;
				
				if(info.equals(temp[0])) {
					
					System.out.println();
					System.out.println("기존이름 : " + temp[1]);
					System.out.print("변경할 이름 : ");
					Scanner scan = new Scanner(System.in);
					newName = scan.nextLine();

					break;
				}
				
			}
			System.out.println();
			System.out.println("맞으면 1, 다시 입력하시려면 2를 입력해주세요.");
			System.out.print("번호입력 : ");
			Scanner scan = new Scanner(System.in);
			String sel = scan.nextLine();
			System.out.println();

			boolean result = true;

			while (result) {

				if (sel.equals("1")) {
					System.out.println("성공하였습니다.");
					saveMemberInfo(num,1,newName);
					pause(info);
				} else if (sel.equals("2")) {
					System.out.print("변경할 이름 : ");
					scan = new Scanner(System.in);
					newName = scan.nextLine();
					
					System.out.println("맞으면 1, 다시 입력하시려면 2를 입력해주세요.");
					System.out.print("번호입력 : ");
					scan = new Scanner(System.in);
					sel = scan.nextLine();

				}
			}

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private static void pause(String info) {
		System.out.println("전메뉴로 돌아가시려면 Enter를 입력해주세요");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		menu(info);

	}
	
	//새로받은 회원정보 저장
	private static void saveMemberInfo(int num, int number, String newinfo) { // 줄고유번호, 값 순서, 새로운 값

		int count=0;
		
		try {
			
			BufferedReader reader 
				= new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));
			
						
			String line = null;
			String txt = "";
	
			//9995,류웅유,zouged51240,eOTkul49872,울산수영구수영로,010-1885-5053,19651112,zouged51240@naver.com,29,18285457,81997
			while((line = reader.readLine())!=null) {
				String[] temp = line.split(",");
				count++;
				
				if (num==count) {
					line = line.replace(temp[number], newinfo);

				}

				txt += line + "\n";
			}							

			
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("C:\\Users\\user\\Desktop\\프로젝트\\데이터파일\\memberdata100.txt"));
			
			writer.write(txt);
			
			reader.close();
			writer.close();
			
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}
	
	}
}
