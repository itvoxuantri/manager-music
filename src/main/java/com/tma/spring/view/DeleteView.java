package com.tma.spring.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.tma.spring.entity.Music;
import com.tma.spring.mbean.MusicServiceImplMBean;

public class DeleteView {
	public DeleteView(MusicServiceImplMBean musicService) throws SQLException {
		displayMenu(musicService);
		System.out.print("Select option: ");
		@SuppressWarnings("resource")
		String MENU = new Scanner(System.in).next();
		while (!(MENU.equals("1") || MENU.equals("0"))) {
			System.out.print("Invalid selection \n");
			System.out.print("Select option again:");
			MENU = new Scanner(System.in).next();
		}

		switch (MENU) {
		case "1":
			System.out.println("Enter music id: ");
			int musicId = new Scanner(System.in).nextInt();
			Music music = musicService.findRecordById(musicId);
			while (music == null) {
				System.out.println("Music id not found \n");
				System.out.println("Enter music id again: ");
				musicId = new Scanner(System.in).nextInt();
				music = musicService.findRecordById(musicId);
			}
			
			musicService.deleteRecord(musicId);
			
			new DeleteView(musicService);
			break;
		case "0":
			HomeView.displayHome(musicService);
			break;
		default:
			new DeleteView(musicService);
			break;
		}

	}

	private static void displayMenu(MusicServiceImplMBean musicService) {
		System.out.println(
				"     ----------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"    |                                              Music Delete                                                      |");
		System.out.println(
				"     ----------------------------------------------------------------------------------------------------------------");
		System.out.println();

		System.out.println();
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        |       Command Menu Managerment Delete Music       |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        | Options:                                          |                  ");
		System.out.println("\t\t\t        |        1. Delete Music                            |                  ");
		System.out.println("\t\t\t        |        0. Back                                    |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println();
	}

}
