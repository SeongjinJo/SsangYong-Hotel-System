package com.test.hotel.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reservation {

	final private String memberpath = ".\\src\\data\\memberdata.dat";
	final private String hotelpath = ".\\src\\data\\hotelData.dat";
	final private String roompath = ".\\src\\data\\roomdata.dat";
	final private String servicepath = ".\\src\\data\\service.dat";
	private String theme;
	private int people;
	private String disorder;
	private String transportation;
	private int checkIn;
	private int checkOut;
	private String area;
	private int totalmoney;
	private String pet;
	private String[] servicelist = new String[6];

	private int roomnumber;

	public void reservationstart() {

		setTheme(thememenu());
//		System.out.println("선택하신 테마명 : " + this.getTheme()); // 확인용(지워도됨)
		setPeople(headcount());
//		System.out.println("인원 수 : " + this.getPeople()); // 확인용(지워도됨)
		setDisorder(disordercheck());
//		System.out.println("장애인 동반 여부 : " + this.disorder); // 확인용(지워도됨)
		setCheckIn(checkIn());
//		System.out.println("체크인 날짜 : " + this.getCheckIn()); // 확인용(지워도됨)
		setCheckOut(checkOut(this.checkIn));
//		System.out.println("체크아웃 날짜 : " + this.getCheckOut()); // 확인용(지워도됨)
		setPet(petable());
//		System.out.println(this.pet);
		setTransportation(transportationSelect());
//		System.out.println("교통수단 : " + getTransportation());
		roomCheck(roompath);
		setServicelist(serviceProvision(servicepath));
		this.totalmoney += Integer.parseInt(getServicelist(5));
//		System.out.println("토탈 금액" + this.totalmoney);
//		System.out.println("마지막 결제 진입 성공");
		paymentinformation(roomnumber, people, transportation, checkIn, checkOut, totalmoney, servicelist);

	}// menu 끝

	// 최종 결제 + 결제내역 생성
	public static void paymentinformation(int roomnumber, int people, String transportation, int checkIn, int checkOut,
			int totalmoney, String[] servicelist) {

		String theme;
		String count = String.valueOf(people);
		String checkin = String.valueOf(checkIn);
		String checkout = String.valueOf(checkOut);
		String memo ="";
		int finalmoney = 0;
		ArrayList<String> cardnum = new ArrayList<String>();

		String[] roominfo = new String[16]; // 여기서부터 방정보 저장 변수
		String hotel;
		String room;
		String disorder;
		String type;
		String view;
		String bed;
		String smoking;

		String[] memberinfo = new String[11];
		String[] hotelinfo = new String[14];
		System.out.println("고객님께서 선택하신 호텔과 객실에 대한 내역입니다");
		System.out.println("=================================================");

		Scanner scan = new Scanner(System.in);
		try {
			// 0.호텔고유넘버 1.호텔명, 2. 호텔 방번호 3. 테마 4.방사이즈 5. 룸타입 6. 뷰 7. 침대 8. 흡연여부
			// 9. 장애인여부 10. 애완동물 11. 수영장 여부 12. tv여부 13. 에어컨 여부 14. 미니바 여부 15. 가격
			BufferedReader reader = new BufferedReader(new FileReader(".\\src\\data\\roomdata.dat")); // 객실데이터
			BufferedReader reader2 = new BufferedReader(new FileReader(".\\src\\data\\hotelData.dat")); // 호텔 데이터
			BufferedReader reader3 = new BufferedReader(new FileReader(".\\src\\data\\memberdata.dat"));// 멤버 데이터
			BufferedReader reader4 = new BufferedReader(new FileReader(".\\src\\data\\reservationdata.dat"));// 예약정보 저장된 곳

			String line = null;
			while ((line = reader.readLine()) != null) { // 고객이 묵는 방 정보 가져옴
				String temp[] = line.split(",");
				if (String.valueOf(roomnumber).equals(temp[0])) {
					for (int i = 0; i < temp.length; i++) {
						roominfo[i] = temp[i];
					}
					break;
				}
			}
			reader.close();
			System.out.println(Arrays.toString(roominfo)); // 덤프
			System.out.println();
			System.out.println();

			// 호텔번호,호텔명,주차가능,환전가능,주소,엘레베이터,편의점,바,컨퍼런스룸,수화물보관,컨시어지 서비스,카페,피트니스 센터,전기차
			// 충전소,공항셔틀,ATM
			line = null;
			while ((line = reader2.readLine()) != null) { // 호텔정보 가져옴
				String temp[] = line.split(",");
				if (roominfo[1].equals(temp[1])) {
					for (int i = 0; i < temp.length; i++) {
						hotelinfo[i] = temp[i];
					}
					break;
				}
			}
			reader2.close();
			System.out.println(Arrays.toString(hotelinfo)); // 덤프
			System.out.println();
			System.out.println();

			line = null;
			while ((line = reader3.readLine()) != null) { // 회원 정보 데이터 추출
				String temp[] = line.split(",");
				if ("apkjls60294".equals(temp[2])) {
					for (int i = 0; i < temp.length; i++) {
						memberinfo[i] = temp[i];
					}
					break;
				}
			}
			System.out.println(Arrays.toString(memberinfo)); // 덤프
			reader3.close();

		} catch (Exception e) {
			System.out.println("Reservation.Payment()");
			e.printStackTrace();
		}
		System.out.println("\n\n\n\n\n선택한 부가 서비스들은 아래와 같습니다.");
		System.out.println("========================================");
		int cnt = 1;
		for (int i = 0; i < servicelist.length - 1; i++) {

			if (!servicelist[i].equals("")) {
				System.out.printf("%02d] %s", cnt++, servicelist[i]);
			}
		}
		System.out.println("========================================");
		System.out.printf("부가 서비스 합계 : %,d원\n\n", Integer.parseInt(servicelist[5]));
		pause("부가 서비스를 모두 확인하셨으면 엔터를 눌러주세요...");
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("회원님의 등급과 마일리지를 확인하고 사용 할 수 있는 페이지입니다.");
		System.out.println("==================================================================");
		System.out.printf("'%s'고객님의 회원등급은 Gold 등급입니다(할인율10%%)\n\n", memberinfo[1]);

		while (true) {
			System.out.printf("'%s'고객님의 마일리지는 %,dP 사용 가능하십니다.\n\n\n", memberinfo[1], Integer.parseInt(memberinfo[10]));
			System.out.println("마일리지를 사용 하시겠습니까? Y / N\n\n\n");
			System.out.print("입력 : ");
			String value = scan.nextLine();
			System.out.println("\n\n\n");
			if (value.equals("Y") || value.equals("y")) {
				System.out.printf("마일리지 %,dP를 사용합니다.\n", Integer.parseInt(memberinfo[10]));
				finalmoney = totalmoney - Integer.parseInt(memberinfo[10]);
				System.out.printf("%,d - %,d = %,d원\n\n\n", totalmoney, Integer.parseInt(memberinfo[10]), finalmoney);
				break;
			} else if (value.equals("N") || value.equals("n")) {
				System.out.println("마일리지를 사용하지 않습니다.");
				finalmoney = totalmoney;
				break;
			} else {
				System.out.println("정확한 값을 입력해주세요.");
				System.out.println();
			}
		}
		System.out.println("\n\n\n\n최종 결제전 확인 페이지입니다.");
		System.out.println("========================");
		System.out.printf("예약자명\t: %s\n",memberinfo[1]);
		System.out.printf("예약인원\t: %d명\n",people);
		System.out.printf("CheckIn\t\t: %d\n",checkIn);
		System.out.printf("CheckOut\t: %d\n",checkOut);
		System.out.printf("호텔명\t\t: %s 호텔\n",hotelinfo[1]);
		System.out.printf("객실명\t\t: %s\n",roominfo[2]);
		System.out.printf("선택한 테마\t: %s\n",roominfo[3]);
		System.out.printf("선택한 뷰\t: %s\n",roominfo[6]);
		System.out.printf("객실 가격\t: %s원\n\n\n",roominfo[15]);
		System.out.printf("부가서비스\n");
		System.out.println("-------------------------");
		for(int i=0; i<servicelist.length-1;i++) {
			System.out.print(servicelist[i]);
		}
		System.out.println("-------------------------");		
		System.out.println("\n\n\n");
		System.out.printf("가격\t: %,d원\n",finalmoney);
		System.out.printf("최종결제 금액 : %,d원\n\n\n", finalmoney);
		
		
		System.out.println("혹시 요구사항이 있으시면 입력해주세요\n\n");
		System.out.print("요구사항 입력 : ");
		memo = scan.nextLine();
		
		System.out.println("\n\n요구사항 입력이 완료되셨습니다.");
		
		pause("\n\n\n\n\n\n\n신용카드 결제 페이지로 이동하시려면 엔터를 눌러주세요...");
		
		cardnum=payment(finalmoney);
		pause("\n\n\n결제가 완료 되셨습니다. 최종 결과창으로 이동하시려면 Enter를 입력해주세요...");
		
		System.out.println("최종 결제 내역 입니다");
		System.out.println("============================================");
		System.out.printf("예약자명\t: %s\n",memberinfo[1]);
		System.out.printf("예약인원\t: %d명\n",people);
		System.out.printf("CheckIn\t\t: %d\n",checkIn);
		System.out.printf("CheckOut\t: %d\n",checkOut);
		System.out.printf("호텔명\t\t: %s 호텔\n",hotelinfo[1]);
		System.out.printf("객실명\t\t: %s\n",roominfo[2]);
		System.out.printf("선택한 테마\t: %s\n",roominfo[3]);
		System.out.printf("선택한 뷰\t: %s\n",roominfo[6]);
		System.out.printf("카드번호\t : %s\\n\\n\\n",cardnum.get(0));
		for(int i=1; i< cardnum.size();i++) {
			System.out.print("-"+cardnum.get(i));
		}
		System.out.printf("\n부가서비스\n");
		System.out.println("-----------------------------------");	
		for(int i=0; i<servicelist.length-1;i++) {
			System.out.print(servicelist[i]);
		}
		System.out.println("-----------------------------------\n");	
		System.out.printf("\n고객 요구사항\n");
		System.out.println("-----------------------------------");	
		System.out.printf("내용 : %s\n",memo);
		System.out.println("-----------------------------------");	
		

		System.out.println("\n");
		System.out.printf("최종 가격\t\t: %,d원\n",finalmoney);
		
		
		System.out.println("============================================");
		System.out.println("저희 호텔 시스템을 이용해 주셔서 감사합니다.\n\n");
		
		
		try {
			String[] reservationinfo = new String[10];
			BufferedReader reader = new BufferedReader(new FileReader(".\\src\\data\\reservationinfo.dat"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(".\\src\\data\\reservationinfo.dat", true));
			String line = null;
			while ((line = reader.readLine()) != null) { // 고객이 묵는 방 정보 가져옴
				String temp[] = line.split(",");
					for (int i = 0; i < temp.length; i++) {
						reservationinfo[i] = temp[i];
						System.out.println(Arrays.toString(temp));
						System.out.println();
						System.out.println(Arrays.toString(reservationinfo));
						
					}
				}
			reader.close();
			System.out.println(Arrays.toString(reservationinfo)); // 덤프
			int reservationinfocount = Integer.parseInt(reservationinfo[0])+1;
			String servicetotal = "";
			for(int i=0; i<servicelist.length-1;i++) {
				if (!servicelist[i].equals("")) {
				servicetotal += servicelist[i]+".";
				}
			}
			servicetotal = servicetotal.replace("\n", "");
			servicetotal=servicetotal.substring(0,servicetotal.length()-1);
			
			writer.write(String.format("%d,%s,%d,%d,%d,%d,%s,%s,%s,%s\n"
					,reservationinfocount
					,memberinfo[0]
					,people
					,checkIn
					,checkOut
					,finalmoney
					,memo
					,servicetotal
					,roominfo[0]
					, hotelinfo[0]));
			writer.close();

			System.out.println("저장 완료");
		} catch (Exception e) {
			System.out.println("Reservation.paymentinformation()");
			e.printStackTrace();
		}
		
		
		pause("메인메뉴로 돌아가려면 Enter를 입력...");

	}

	private static ArrayList<String> payment(int totalmoney) {
		
		ArrayList<String> cardnum = new ArrayList<String>();
		
//		BC : 94로 시작하는 14자리
//		Visa : 4로 시작하는 12자리나 16자리
//		MasterCard : 51~55로 시작하는 16자리

		System.out.println("\n\n\n\n\n\n\n\n\n\n신용카드 결제 페이지");
		System.out.println("사용하시는 신용카드의 종류를 선택해 주세요");
		System.out.println("===========================================");
		boolean loop = true;
		while (loop) {
			System.out.println("1. Visa 카드 2. Master 카드 3. BC카드\n\n\n\n");

			System.out.print("번호를 입력해주세요 : ");
			Scanner scan = new Scanner(System.in);
			String num = scan.nextLine();
			

			
			switch (num) {
			case "1":
				System.out.println("\n\n\n\n\n\nVisa 카드 결제 페이지 입니다\n");

				while (loop) {
					System.out.println("==============================================");
					System.out.printf("결제하실 금액은 %,d원 입니다.\n",totalmoney);
					System.out.printf("결제를 위한 Visa 카드번호를 입력해주세요\nEx.94XX-XXXX-XXXXXX숫자14자리 '-'까지 입력\n\n\n");
					System.out.print("카드번호 입력 : ");
					String num2 = scan.nextLine();
					String[] arr = num2.split("-");
					if (Integer.parseInt(arr[0]) >= 9400) {
						for (int i = 0; i < arr.length; i++) {
							cardnum.add(arr[i]);
						}
						System.out.println("올바른 Visa 카드 번호입니다.\n\n\n\n");
						System.out.print("CVC 번호 입력 : ");
						scan.nextLine();
						System.out.print("\n\n\n유효 기간 입력(mm/yy) : ");
						scan.nextLine();
						loop = false;
						break;
						
					} else {
						System.out.println("\n\n올바르지 않은 카드 번호입니다. 다시 입력해주세요");
					}

				}
				break;
			case "2":
				System.out.println("\n\n\n\n\n\nMaster 카드 결제 페이지 입니다\n");

				while (loop) {
					System.out.println("==============================================");
					System.out.printf("결제하실 금액은 %,d원 입니다.\n",totalmoney);
					System.out.printf("결제를 위한 Master 카드번호를 입력해주세요\nEx.4XXX-XXXX-XXXXXX숫자12~16자리 '-'까지 입력\n\n\n");
					System.out.print("카드번호 입력 : ");
					String num2 = scan.nextLine();
					String[] arr = num2.split("-");
					if (Integer.parseInt(arr[0]) >= 4000) {
						for (int i = 0; i < arr.length; i++) {
							cardnum.add(arr[i]);
						}
						System.out.println("올바른 Master 카드 번호입니다.");
						System.out.print("CVC 번호 입력 : ");
						scan.nextLine();
						System.out.print("\n\n\n유효 기간 입력(mm/yy) : ");
						scan.nextLine();
						loop = false;
						break;
					} else {
						System.out.println("\n\n올바르지 않은 카드 번호입니다. 다시 입력해주세요");
					}

				}
				break;
			case "3":
				System.out.println("\n\n\n\n\n\nBC 카드 결제 페이지 입니다\n");

				while (loop) {
					System.out.println("==============================================");
					System.out.printf("결제하실 금액은 %,d원 입니다.\n",totalmoney);
					System.out.printf("결제를 위한 BC 카드번호를 입력해주세요\nEx.51XX-XXXX-XXXX-XXXX숫자16자리 '-'까지 입력\n\n\n");
					System.out.print("카드번호 입력 : ");
					String num2 = scan.nextLine();
					String[] arr = num2.split("-");
					if (Integer.parseInt(arr[0]) >= 5100&&Integer.parseInt(arr[0]) <= 5500) {
						for (int i = 0; i < arr.length; i++) {
							cardnum.add(arr[i]);
						}
						System.out.println("올바른 BC 카드 번호입니다.");
						System.out.print("CVC 번호 입력 : ");
						scan.nextLine();
						System.out.print("\n\n\n유효 기간 입력(mm/yy) : ");
						scan.nextLine();
						loop = false;
						break;
					} else {
						System.out.println("\n\n올바르지 않은 카드 번호입니다. 다시 입력해주세요");
					}
				}
				break;
			default:
				System.out.println("제대로 된 숫자를 입력하시기 바랍니다.");
			} // 결제 스위치문 끝부분
			
			
		}
		return cardnum;
	}

	private void roomCheck(String path) {

		try {
			// 0.객실번호 1.호텔명 3.테마번호 4.룸 넘버 5.수용인원 6.룸 타입 7.객실 뷰 8.흡연가능 9.장애시설 10.반려동물동반여부
			// 11.수영장 포함 12.베드 13. TV여부(Y/N) 14. 에어컨 15. 미니바 16. 가격
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = null;
			int count = 1;
			int num = 1;
			ArrayList<Integer> arr = new ArrayList<Integer>();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("고객님께서 선호하실 호텔 리스트 입니다 ");
			System.out.println(
					"====================================================================================\n\n");
			System.out.printf("NUMBER\t\tHOTEL NAME\tHOTEL NUM\tRoom Type\tView\t\tBed\tfully occupied\tPrice(Won)\n");
			while ((line = reader.readLine()) != null) {

				String temp[] = line.split(",");
				if (this.theme.equals(temp[3]) && this.people <=Integer.parseInt(temp[4])
						&& this.disorder.equals(temp[9])) {
					if (temp[9].equals("true")) {
						temp[9] = "동반";
					} else {
						temp[9] = "미동반";
					}
					arr.add(Integer.parseInt(temp[0]));
					System.out.printf("%03d]\t\t", count);
					System.out.printf("%s\t\t%s\t\t%-10s\t%-10s\t%s\t\t%s\t%s\n", temp[1], temp[2], temp[5], temp[6],
							temp[7], temp[4], temp[15]);
					count++;

				}

				if (count > num * 15) {
					System.out.println("L 누르면 이전 페이지 R누르면 다음 페이지 그 방을 고르려면 번호 입력");
					Scanner scan = new Scanner(System.in);
					System.out.print("입력 : ");
					String check = scan.nextLine();

					if (check.equals("R") || check.equals("r")) {
						num++;
					} else {
						roomnumber = roomSelect(arr.get(Integer.parseInt(check) - 1));
						break;
					}
				}
			}
			reader.close();
			System.out.println("룸체크완료");

		} catch (Exception e) {
			System.out.println("memberpath오류");
			e.printStackTrace();
		}
	}

	private int roomSelect(int num) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(".\\src\\data\\roomdata.dat"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String temp[] = line.split(",");
				if (num == Integer.parseInt(temp[0])) {
					System.out.println("님께서 선택하신 방 정보 입니다.");
					System.out.println();
					System.out.println(
							"==================================================================================");
					System.out.printf(
							"HOTEL NAME\t\t : %s\nHOTEL NUM\t\t : %s\nRoom Type\t\t : %s\nView\t\t\t : %s\nBed\t\t\t : %s\nfully occupied\t\t : %s\nPrice\t\t\t : %s(원)\n",
							temp[1], temp[2], temp[5], temp[6], temp[7], temp[4], temp[15]);
					this.totalmoney += Integer.parseInt(temp[15]);
					break;
				}
			}
			reader.close();
			System.out.println("룸셀렉완료");
		} catch (Exception e) {
			System.out.println("memberpath오류");
			e.printStackTrace();
		}
		return num;
	}

	private static String[] serviceProvision(String servicepath) {
		String[] servicelist = new String[6];
		int count2 = 0;
		int total = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(servicepath));
			String line = null;
			int count = 1;
			Scanner scan = new Scanner(System.in);
			while ((line = reader.readLine()) != null) {
				String temp[] = line.split(",");
				System.out.println("부가서비스 정보창 입니다...");
				System.out.println("=============================================================================");
				System.out.println("서비스 명   \t:  " + temp[1]);
				System.out.println("서비스 내용   \t:  " + temp[2] + "입니다");
				System.out.println("\n\n\n");
				System.out.printf("서비스 금액(원) :  %,d\n", Integer.parseInt(temp[3]));
				System.out.println("=============================================================================");
				System.out.print("서비스를 이용 하시겠습니까? (Y  /  N) : ");
				String check = scan.nextLine();
				if (check.equals("y") || check.equals("Y")) {
					servicelist[count2++] = temp[1] + "\n";
					total += Integer.parseInt(temp[3]);
					System.out.println();
					System.out.println("=============================================================================");
					System.out.println("감사합니다! " + temp[1] + "를 이용 내역에 추가해드렸습니다.");
				} else {
					servicelist[count2++] = "";
					System.out.println(temp[1] + "를 선택하지 않으셨습니다.");
				}
				System.out.println(total);
				pause("다음 부가서비스를 이용하시려면 엔터를 눌러주세요..");
				System.out.println("부가서비스 정보창 입니다...");
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			}
			reader.close();
			System.out.println("부가서비스 완료");
		} catch (Exception e) {
			System.out.println("servicepath오류");
			e.printStackTrace();
		}
		servicelist[5] = String.valueOf(total);
		System.out.println(Arrays.toString(servicelist));
		return servicelist;
	}

	public static String thememenu() { // 테마 메뉴
		String theme = "";
		boolean loop = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (loop) {
			System.out.println("===========================================");
			System.out.println("원하시는 테마의 번호를 선택해주세요.");
			System.out.println("==========================================");
			System.out.println("1.호캉스 2.힐링 3.익사이팅 4.패밀리 5.쇼핑");
			System.out.println("\n\n\n\n\n");
			System.out.print("입력 : ");
			Scanner scan = new Scanner(System.in);
			String sel = scan.nextLine();
			switch (sel) {
			case "1":
				theme = "호캉스";
				loop = false;
				break;
			case "2":
				theme = "힐링";
				loop = false;
				break;
			case "3":
				theme = "익사이팅";
				loop = false;
				break;
			case "4":
				theme = "패밀리";
				loop = false;
				break;
			case "5":
				theme = "쇼핑";
				loop = false;
				break;
			default:
				System.out.println("제대로 된 번호를 입력해주세요.");
				break;
			}
		}
		return theme;
	} // 테마 메뉴 종료

	public static String transportationSelect() { // 테마 메뉴
		String transportation = "";
		boolean loop = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (loop) {
			System.out.println("=============================================");
			System.out.println("호텔방문 시 이용하는 교통수단을 선택해 주세요");
			System.out.println("=============================================");
			System.out.println("1.자가용 2.버스 3.기차");
			System.out.println();
			System.out.println();
			System.out.print("입력 : ");
			Scanner scan = new Scanner(System.in);
			String sel = scan.nextLine();
			switch (sel) {
			case "1":
				transportation = "자가용";
				loop = false;
				break;
			case "2":
				transportation = "버스";
				loop = false;
				break;
			case "3":
				transportation = "기차";
				loop = false;
				break;
			default:
				System.out.println("제대로 된 번호를 입력해주세요.");
				break;
			}
		}
		return transportation;
	} // 테마 메뉴 종료

	public static int headcount() {
		int count = 0;
		boolean loop = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (loop) {
			System.out.println("===============================================");
			System.out.println("숙박할 인원수를 입력해 주세요(숫자만 입력 가능) ");
			System.out.println("===============================================");
			System.out.println("\n\n\n\n");
			System.out.print("인원 수(명) : ");
			Scanner scan = new Scanner(System.in);
			count = Integer.parseInt(scan.nextLine());
			if (count <= 6) {
				loop = false;
			} else {
				System.out.println("인원을 7명 보다 작게 입력해주세요.");
				System.out.println();
			}
		}

		return count;
	}

	public static boolean disordercheck() {
		boolean check = true;
		boolean loop = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (loop) {
			System.out.println("========================================");
			System.out.println("장애인 동반 여부를 선택해주세요(Y & N)");
			System.out.println("\n\n\n\n");
			System.out.print("Y & N (대소문자 둘다 입력 가능) : ");
			Scanner scan = new Scanner(System.in);
			String data = scan.nextLine();
			if (data.equals("Y") || data.equals("y")) {
				check = true;
				loop = false;
			} else if (data.equals("N") || data.equals("n")) {
				check = false;
				loop = false;
			} else {
				System.out.println("정확한 값을 입력해주세요.");
				System.out.println();
			}
		}

		return check;

	}

	public static int checkIn() {

		int checkin = 0;
		boolean loop = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (loop) {
			System.out.println("========================================");
			System.out.println("Check In 날짜를 입력해 주세요(Ex.20201104)");
			System.out.println("\n\n\n\n");
			System.out.print("날짜 입력 : ");
			Scanner scan = new Scanner(System.in);

			checkin = Integer.parseInt(scan.nextLine());
			if (checkin > 20201117) { // 수정 필요
				loop = false;
			} else {
				System.out.println("체크인 날짜를 현재날짜보다 위로 설정해주세요.");
				System.out.println();
			}
		}

		return checkin;
	}

	public static int checkOut(int checkIn) {

		int checkout = 0;
		boolean loop = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		while (loop) {
			System.out.println("========================================");
			System.out.println("Check Out 날짜를 입력해 주세요(Ex.20201104)");
			System.out.println("\n\n\n\n");
			System.out.print("날짜 입력 : ");
			Scanner scan = new Scanner(System.in);

			checkout = Integer.parseInt(scan.nextLine());
			if (checkout > checkIn) { // 수정 필요
				loop = false;
			} else {
				System.out.println("Check Out날짜를 Check In날짜보다 위로 설정해주세요.");
				System.out.println();
			}
		}

		return checkout;
	}

	public static String petable() {

		String check = "";
		boolean loop = true;
		while (loop) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("================================================");
			System.out.println("애완동물(강아지,고양이) 동반 여부를 선택해주세요");
			System.out.println("\n\n\n\n");
			System.out.print("Y 나 N을 입력 해주세요.(대 소문자 모두 가능)");
			System.out.println();
			System.out.print("입력 : ");
			Scanner scan = new Scanner(System.in);
			String data = scan.nextLine();
			if (data.equals("Y") || data.equals("y")) {
				check = "true";
				loop = false;
			} else if (data.equals("N") || data.equals("n")) {
				check = "false";
				loop = false;
			} else {
				System.out.println("정확한 값을 입력해주세요.");
				System.out.println();
			}
		}

		return check;

	}

	public static void pause(String content) {

		Scanner scan = new Scanner(System.in);
		System.out.println(content);
		scan.nextLine();

	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public int getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(int checkIn) {
		this.checkIn = checkIn;
	}

	public int getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(int checkOut) {
		this.checkOut = checkOut;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDisorder() {
		return disorder;
	}

	public void setDisorder(boolean disordercheck) {
		if (disordercheck) {
			disorder = "true";
		} else {
			disorder = "false";
		}

	}

	public int getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getServicelist(int i) {
		return servicelist[i];
	}

	public void setServicelist(String[] servicelist) {
		this.servicelist = servicelist;
	}

}