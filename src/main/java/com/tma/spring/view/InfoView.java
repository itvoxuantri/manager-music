package com.tma.spring.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.tma.spring.entity.Music;
import com.tma.spring.mbean.MusicServiceImplMBean;

public class InfoView {
	public InfoView(MusicServiceImplMBean musicService) throws SQLException {
		displayMenu(musicService);
		System.out.print("Select option: ");
		@SuppressWarnings("resource")
		String MENU = new Scanner(System.in).next();
		while(!(MENU.equals("1") || MENU.equals("0"))){
			System.out.print("Invalid selection \n");
			System.out.print("Select option again:");
			MENU = new Scanner(System.in).next();
		}
		switch (MENU) {
		case "1":
			System.out.println("Enter music id: ");
			String musicId = new Scanner(System.in).next();
			Music music = musicService.findRecordById(Integer.parseInt(musicId));
			while (music == null) {
				System.out.println("Music id not found \n");
				System.out.println("Enter music id again: ");
				musicId = new Scanner(System.in).next();
				music = musicService.findRecordById(Integer.parseInt(musicId));
			}
			System.out.println("     ----------------------------------------------------------------------------------------------------------------");
			System.out.println("    |                                              Music Information                                                 |");
			System.out.println("     ----------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("     ----------------------------------------------------------------------------------------------------------------");
			System.out.println("    |      Id       |       Name                  |      Author Name          |       Category Name                  |");
			System.out.println("     ----------------------------------------------------------------------------------------------------------------");		
			System.out.println("     ----------------------------------------------------------------------------------------------------------------");	
		    System.out.println("    |\t   "+music.getId()+"\t\t"+music.getName()+"\t\t\t"+music.getNameAuthor()+"\t\t\t"+ music.getNameCategory() + "\t\t     |");
			System.out.println("     ----------------------------------------------------------------------------------------------------------------");
			new InfoView(musicService);
			break;
		case "0":
			HomeView.displayHome(musicService);
			break;
		default:
			new InfoView(musicService);
			break;
		}
		
	}
	private static void displayMenu(MusicServiceImplMBean musicService) throws SQLException {
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println("    |                                              Music Information                                                 |");
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		System.out.println();
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        |    Command Menu Managerment Information Music     |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        | Options:                                          |                  ");
		System.out.println("\t\t\t        |        1. Search Music                            |                  ");
		System.out.println("\t\t\t        |        0. Back                                    |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println();
	}
}
