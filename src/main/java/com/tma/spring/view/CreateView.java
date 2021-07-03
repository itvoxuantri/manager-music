package com.tma.spring.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.tma.spring.entity.Music;
import com.tma.spring.mbean.MusicServiceImplMBean;

public class CreateView {
	public CreateView(MusicServiceImplMBean musicService) throws SQLException  {
		displayMenu(musicService);
		System.out.print("Select option: ");
		@SuppressWarnings("resource")
		String MENU = new Scanner(System.in).next();
		while(!(MENU.equals("1") || MENU.equals("0"))){
			System.out.print("Invalid selection \n");
			System.out.print("Select option again:");
			MENU = new Scanner(System.in).next();
		}
		Music music = new Music();
		String musicInfor = null;
		switch (MENU) {
		case "1":
			System.out.println("Enter music name: ");
			musicInfor = new Scanner(System.in).nextLine();
			while(musicInfor.length() == 0) {
				System.out.println("Please, Enter music name again: ");
				musicInfor = new Scanner(System.in).nextLine();
			}
			music.setName(musicInfor);
			System.out.println("Enter music name author : ");
			musicInfor = new Scanner(System.in).nextLine();
			while(musicInfor.length() == 0) {
				System.out.println("Please, Enter music name again: ");
				musicInfor = new Scanner(System.in).nextLine();
			}
			music.setNameAuthor(musicInfor);
			System.out.println("Enter music name category : ");
			musicInfor = new Scanner(System.in).nextLine();
			while(musicInfor.length() == 0) {
				System.out.println("Please, Enter music name again: ");
				musicInfor = new Scanner(System.in).nextLine();
			}
			music.setNameCategory(musicInfor);
			
			musicService.createRecord(music);
			
			new CreateView(musicService);
			break;
		case "0":
			HomeView.displayHome(musicService);
			break;
		default:
			new CreateView(musicService);
			break;
		}
		
	}

	private static void displayMenu(MusicServiceImplMBean musicService) {
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println("    |                                              Music Create                                                      |");
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		System.out.println();
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        |       Command Menu Managerment Create Music       |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        | Options:                                          |                  ");
		System.out.println("\t\t\t        |        1. Create Music                            |                  ");
		System.out.println("\t\t\t        |        0. Back                                    |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println();
	}
}
