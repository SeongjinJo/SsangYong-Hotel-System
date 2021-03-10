package com.test.hotel.reservation;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		Reservation rv = new Reservation();
		while (loop) {
			System.out.println("1. 호텔 예약");
			System.out.println("2. 호텔 예약 확인");
			System.out.println("3. 예약 취소");
			System.out.print("이동하실 메뉴의 번호를 입력해주세요 : ");

			String sel = scan.nextLine();

			switch (sel) {
			case "1":
				rv.reservationstart();
				break;

			case "2":
				rv.thememenu();
				pause();
				break;
			case "3":
				rv.thememenu();
				pause();
				break;

			default:
				loop = false;
				break;
			}

		}

	}

	private static void pause() {

		Scanner scan = new Scanner(System.in);
		System.out.println("엔터 입력..");
		scan.nextLine();

	}

}
