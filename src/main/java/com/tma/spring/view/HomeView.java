package com.tma.spring.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.tma.spring.entity.Music;
import com.tma.spring.mbean.MusicServiceImplMBean;

public class HomeView {

	public static void displayHome(MusicServiceImplMBean musicService) throws SQLException {
		
		// Display list music
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println("    |                                              MANAGERMENT MUSIC                                                 |");
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println("    |      Id       |       Name                  |      Author Name          |       Category Name                  |");
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");		
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");	
		for (Music music : musicService.displayRecords()) {
	    System.out.println("    |\t   "+music.getId()+"\t\t"+music.getName()+"\t\t\t"+music.getNameAuthor()+"\t\t\t"+ music.getNameCategory() + "\t\t     |");
		}
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		
		// Display menu graphics
		System.out.println();
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        |    Command Menu Managerment Music                 |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println("\t\t\t        | Options:                                          |                  ");
		System.out.println("\t\t\t        |        1. Create Music                            |                  ");
		System.out.println("\t\t\t        |        2. Update Music                            |                  ");
		System.out.println("\t\t\t        |        3. Delete Music                            |                  ");
		System.out.println("\t\t\t        |        4. Search Music                            |                  ");
		System.out.println("\t\t\t        |        0. Exit                                    |                  ");
		System.out.println("\t\t\t        *****************************************************                  ");
		System.out.println();

		System.out.print("Select option: ");

		@SuppressWarnings("resource")
		String MENU = new Scanner(System.in).next();
		while (!(MENU.equals("1") || MENU.equals("2") || MENU.equals("3") || MENU.equals("4") || MENU.equals("0"))) {
			System.out.print("Invalid selection \n");
			System.out.print("Select option again:");
			MENU = new Scanner(System.in).next();
		}
		switch (MENU) {
		case "1":
			new CreateView(musicService);
			break;
		case "2":
			new UpdateView(musicService);
			break;
		case "3":
			new DeleteView(musicService);
			break;
		case "4":
			new InfoView(musicService);
			break;
		case "0":
			System.out.println("See you again later!");
			System.exit(0);
			break;
		default:
			displayHome(musicService);
			break;
		}
	}

}
