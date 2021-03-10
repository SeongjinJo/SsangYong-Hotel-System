package com.test.hotel.festival;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.test.hotel.member.MemberManu;

public class Info {

   public static void main(String[] args, String info) throws NumberFormatException, IOException {

      
      // 달력 만들기
      // 1. 년, 월
      // 2. 해당월의 마지막 일?
      // - 윤년 계산
      // 3. 해당월 의 1일의 요일?
      // - 윤년 계산
      // - 누적일 계산

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("이벤트 정보를 확인하고 싶으신 달의 숫자를 입력하세요.");
      
      int year = 2020;

      System.out.print("월 : ");
      
      String firstInput = reader.readLine();
      
      
//      for (;;) {
//         if (!firstInput.equals("1") ||!firstInput.equals("2") ||
//            !firstInput.equals("3") ||!firstInput.equals("4") ||
//            !firstInput.equals("5") ||!firstInput.equals("6") ||
//            !firstInput.equals("7") ||!firstInput.equals("8") ||
//            !firstInput.equals("9") ||!firstInput.equals("10") ||
//            !firstInput.equals("11") ||!firstInput.equals("12")) {
//         System.out.println("올바른 숫자를 입력해주세요.");
//         
//         } else {
//            break;
//         }
//      }   
//         //입력값이 숫자인지 유효성 검사
      
      
      int month = Integer.parseInt(firstInput);
      
      createCalendar(year, month);
      
      System.out.println();
      
      try {

         
      String path = "D:\\class\\축제데이터.txt";
      
      BufferedReader reader3 = new BufferedReader(new FileReader(path));     //축제 데이터 읽어오기
   
      String line = "";
      
      
      while((line = reader3.readLine())!=null) {
         
         String[] temp = line.split(",");      //축제번호,축제이름,시작날짜,끝나는날짜,장소,비용,문의번호,지정특수문자
         
         
         if (temp[2].substring(5,7).equals(String.valueOf(month))) {      //두자리수인 달을 입력할 경우
            System.out.printf("\n%s %s : %s ~ %s\n", temp[7], temp[1], temp[2], temp[3]);
            
            if (temp[3].substring(5,7).compareTo(String.valueOf(month)) > 0 &&       
               temp[2].substring(5,7).compareTo(String.valueOf(month)) < 0) {
            System.out.printf("\n%s %s : %s ~ %s\n", temp[7], temp[1], temp[2], temp[3]);
               //두 자리수인 조건 + 당월은 아니지만 이벤트 기간내에 고객이 입력한 달의 날짜가 포함될 때   
            }
            
         } else if (temp[2].substring(5,7).equals(String.valueOf("0" + month))) {     //한자리수인 달을 입력할 경우   
            System.out.printf("\n%s %s : %s ~ %s\n", temp[7], temp[1], temp[2], temp[3]);
            if (temp[3].substring(5,7).compareTo(String.valueOf("0" + month)) > 0 &&       
                  temp[2].substring(5,7).compareTo(String.valueOf("0" + month)) < 0) {
               System.out.printf("\n%s %s : %s ~ %s\n", temp[7], temp[1], temp[2], temp[3]);
               //한 자리수인 조건 + 당월은 아니지만 이벤트 기간내에 고객이 입력한 달의 날짜가 포함될 때
            }
            
         }
   } //while1 - 첫페이지에 간략한 정보 출력
      
      
      System.out.println();
      System.out.println("더 자세한 정보를 확인하고 싶으시다면 엔터를 눌러주세요.");
      String enter = reader.readLine();
      
      String path4 = "D:\\class\\축제데이터.txt";
      
      BufferedReader reader4 = new BufferedReader(new FileReader(path4));     //축제 데이터 읽어오기
   
      String line2 = "";
      
      
      while((line2 = reader4.readLine())!=null) {
         
         String[] temp = line2.split(",");      //축제번호,축제이름,시작날짜,끝나는날짜,장소,비용,문의번호,지정특수문자
         
         
         if (temp[2].substring(5,7).equals(String.valueOf(month))) {      //두자리수인 달을 입력할 경우
            System.out.printf("\n[%s]%s\n시작날짜 : %s\n끝나는 날짜:%s\n장소 : %s\n비용: %s\n문의번호 : %s\n", temp[7], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
            
            if (temp[3].substring(5,7).compareTo(String.valueOf(month)) > 0 &&       
               temp[2].substring(5,7).compareTo(String.valueOf(month)) < 0) {
            System.out.printf("\n[%s]%s\n시작날짜 : %s\n끝나는 날짜:%s\n장소 : %s\n비용: %s\n문의번호 : %s\n", temp[7], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
               //두 자리수인 조건 + 당월은 아니지만 이벤트 기간내에 고객이 입력한 달의 날짜가 포함될 때   
            }
            
         } else if (temp[2].substring(5,7).equals(String.valueOf("0" + month))) {     //한자리수인 달을 입력할 경우   
            System.out.printf("\n[%s]%s\n시작날짜 : %s\n끝나는 날짜:%s\n장소 : %s\n비용: %s\n문의번호 : %s\n", temp[7], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
            if (temp[3].substring(5,7).compareTo(String.valueOf("0" + month)) > 0 &&       
                  temp[2].substring(5,7).compareTo(String.valueOf("0" + month)) < 0) {
               System.out.printf("\n[%s]%s\n시작날짜 : %s\n끝나는 날짜:%s\n장소 : %s\n비용: %s\n문의번호 : %s\n", temp[7], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
               //한 자리수인 조건 + 당월은 아니지만 이벤트 기간내에 고객이 입력한 달의 날짜가 포함될 때
            }
            
         }
      } //while2 - 다음페이지에 상세정보 출력
      
      
         reader3.close();
         reader4.close();
         

         } catch (Exception e) {
            System.out.println("Info.main()");
            e.printStackTrace();
         }
      
      	System.out.println("엔터를 입력하시면 메뉴로 돌아갑니다.");
      	String re = reader.readLine();
      	MemberManu manu = new MemberManu();
      	manu.main(args, info);
      
      
      

   }

   private static void createCalendar(int year, int month) {
      int lastDay = 0; // 마지막일
      int day_of_week = 0; // 요일

      

      lastDay = getLastDay(year, month);
      day_of_week = getDayOfWeek(year, month);


      // 출력하기
      System.out.println();
      System.out.println("===================================================");
      System.out.printf("\t\t\t%d년 %d월\n", year, month);
      System.out.println("===================================================");
      System.out.println("[월]\t[화]\t[수]\t[목]\t[금]\t[토]\t[일]\t");

      // 1일을 요일 위치와 맞추기 위해 탭 추가
      for (int i = 0; i < day_of_week; i++) {
         System.out.print("\t");
      }

      // 날짜 출력
      
      
      
      for (int i = 1; i <= lastDay; i++) {
            System.out.printf("%3d\t", i);
         
         
         // 현재 출력하는 날짜(i)가 토요일인지?
         // if (i % 7 == 3) {
         if ((i + day_of_week) % 7 == 0) {
            System.out.println();
            
            
            
         }
         
      }

   }

   private static int getDayOfWeek(int year, int month) {
      // 누적 변수
      int totalDays = 0;

      // 1.1 1~2019.12.31
      for (int i = 1; i < year; i++) {
         if (isLeafYear(i)) {
            totalDays += 366;
         } else {
            totalDays += 365;
         }
      }

      // 2020.1.1 ~ 2020.9.30
      for (int i = 1; i < month; i++) {
         totalDays += getLastDay(year, i);
      }

      // 2020.10.1
      totalDays++; // 1일까지의 누적변수 -> 요일ㅇ 알아내기

      return totalDays % 7; // 요일 변환
   }

   // 몇월이 며칠까지 알려주는 메서드
   private static int getLastDay(int year, int month) {

      switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
         return 31; // +break
      case 4:
      case 6:
      case 9:
      case 11:
         return 30;
      case 2:
         return isLeafYear(year) ? 29 : 28; // 윤년이면 true, 평년이면 false
      }
      return 0;
   }

   // 윤년인지 평년인지 알려주는 메서드
   private static boolean isLeafYear(int year) {

      if (year % 4 == 0) {
         if (year % 100 == 0) {
            return true;
         } else {
            if (year % 400 == 0) {
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

}


       
   
