package com.teamtreehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.teamtreehouse.model.SongBook;

public class KaraokeMachine {

	private SongBook mSongBook;
	private BufferedReader mReader;
	private Map<String, String> mMenu;

	public KaraokeMachine(SongBook songBook) {
		mSongBook = songBook;
		mReader = new BufferedReader(new InputStreamReader(System.in));
		mMenu = new HashMap<String, String>();
		mMenu.put("add", "add a new song");
		mMenu.put("quit", "give up");
	}

	private String promptAction() throws IOException {
		System.out.printf("There are %d song. Your option %n", mSongBook.getSongCount());
		for (Map.Entry<String, String> option : mMenu.entrySet()) {
			System.out.printf("%s - %s %n", option.getKey(), option.getValue());
		}
		System.out.print("What do?");
		String choice = mReader.readLine();
		return choice.trim().toLowerCase();
	}

	public void run() {
		String choice = "";
		do {
			try {
				choice = promptAction();
				switch (choice) {
				case "add":
					break;
				case "quit":
					System.out.println("Thanks for playing");
					break;
				default:
					System.out.printf("Unknown %s", choice);

				}
			} catch (IOException ioe) {
				System.out.println("Prob");
				ioe.printStackTrace();
			}
		} while (!choice.equals("quit"));

	}
}
