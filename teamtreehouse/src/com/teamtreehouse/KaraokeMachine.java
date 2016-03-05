package com.teamtreehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.SongBook;

public class KaraokeMachine {

	private SongBook mSongBook;
	private BufferedReader mReader;
	private Queue<Song> mSongQueue;
	private Map<String, String> mMenu;

	public KaraokeMachine(SongBook songBook) {
		mSongBook = songBook;
		mReader = new BufferedReader(new InputStreamReader(System.in));
		mSongQueue = new ArrayDeque<Song>();
		mMenu = new HashMap<String, String>();
		mMenu.put("add", "add a new song");
		mMenu.put("play", "play");
		mMenu.put("choose", "choose");
		mMenu.put("quit", "give up");
	}

	private String promptAction() throws IOException {
		System.out.printf("%n%nThere are %d song and %d in queue. Your option %n", mSongBook.getSongCount(),
				mSongQueue.size());
		for (Map.Entry<String, String> option : mMenu.entrySet()) {
			System.out.printf("%s - %s %n", option.getKey(), option.getValue());
		}
		System.out.print("What do? ");
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
					Song song = promptNewSong();
					mSongBook.addSong(song);
					System.out.printf("%s added! %n%n", song);
					break;
				case "choose":
					String artist = promptArtist();
					Song artistSong = promptSongForArtist(artist);
					mSongQueue.add(artistSong);
					System.out.printf("You chose %s %n", artistSong);
					break;
				case "play":
					playNext();
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

	private String promptArtist() throws IOException {
		System.out.println("available artist");
		List<String> artists = new ArrayList<>(mSongBook.getArtists());
		int index = promptForIndex(artists);
		return artists.get(index);
	}

	private Song promptSongForArtist(String artist) throws IOException {
		List<Song> songs = mSongBook.getSongsForArtist(artist);
		List<String> songTitles = new ArrayList<>();
		for (Song song : songs) {
			songTitles.add(song.getmTitle());
		}
		System.out.printf("Available songs for %s: %n", artist);
		int index = promptForIndex(songTitles);
		return songs.get(index);
	}

	private Song promptNewSong() throws IOException {
		System.out.println("Enter artist");
		String artist = mReader.readLine();
		System.out.println("Enter title");
		String title = mReader.readLine();
		System.out.println("Enter URL");
		String videoUrl = mReader.readLine();
		return new Song(artist, title, videoUrl);
	}

	private int promptForIndex(List<String> options) throws IOException {
		int counter = 1;
		for (String option : options) {
			System.out.printf("%d %s %n", counter, option);
			counter++;
		}
		System.out.println("Choice");
		String optionAsString = mReader.readLine();
		int choice = Integer.parseInt(optionAsString.trim());
		return choice - 1;
	}

	public void playNext() {
		Song song = mSongQueue.poll();
		if (song == null) {
			System.out.println("Sorry null");
		} else {
			System.out.printf("Open %s %s %s", song.getmVideoUrl(), song.getmTitle(), song.getmArtist());
		}
	}
}
