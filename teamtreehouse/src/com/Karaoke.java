package com;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.teamtreehouse.KaraokeMachine;
import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.SongBook;

public class Karaoke {

	public static void main(String args[]) throws FileNotFoundException, IOException {

		SongBook songBook = new SongBook();
		songBook.importFrom("songs.txt");
		KaraokeMachine machine = new KaraokeMachine(songBook);
		machine.run();
		System.out.println("Saving book..");
		songBook.exportTo("songs.txt");
	}
}
