import java.io.*;
import java.util.*;

class Song implements Comparable<Song>{
	String title;
	String artist;
	String rating;
	String bpm;
	
	public int compareTo(Song s) {
		return title.compareTo(s.getTitle());
	}
	
	Song (String t,String a,String r,String b){
		title=t;
		artist=a;
		rating=r;
		bpm=b;
	}
	
	public boolean equals(Object aSong) {
		Song s=(Song) aSong;
		return getTitle().equals(s.getTitle());
	}
	
	public int hashCode() {
		return title.hashCode();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getRating() {
		return rating;
	}
	
	public String getBpm() {
		return bpm;
	}
	
	public String toString() {
		return title;
	}
}

public class JukeBox1 {
	
	ArrayList<Song> songList=new ArrayList<Song>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JukeBox1().go();
	}
	
	class ArtistCompare implements Comparator<Song>{
		public int compare(Song one,Song two) {
			return one.getArtist().compareTo(two.getArtist());
		}
	}
	
	class BpmCompare implements Comparator<Song>{
		public int compare(Song one,Song two) {
			return one.getBpm().compareTo(two.getBpm());
		}
	}
	
	public void go() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		System.out.println(songList);
		
		ArtistCompare artistCompare=new ArtistCompare();
		Collections.sort(songList, artistCompare);
		System.out.println(songList);
		
		BpmCompare bpmCompare=new BpmCompare();
		Collections.sort(songList, bpmCompare);
		System.out.println(songList);
		
		HashSet<Song> songSet=new HashSet<Song>();
		songSet.addAll(songList);
		System.out.println(songSet);
	}
	
	public void getSongs() {
		try {
			File file=new File("SongList.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String line=null;
			while((line=reader.readLine())!=null) {
				addSong(line);
			}
			reader.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void addSong(String lineToParse) {
		String[] tokens=lineToParse.split("/");
		Song nextSong=new Song(tokens[0],tokens[1],tokens[2],tokens[3]);
		songList.add(nextSong);
	}

}
