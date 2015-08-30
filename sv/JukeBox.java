package sv;

import java.util.Queue;
import java.util.Set;

public class JukeBox {
	
	private CDPlayer cdplayer;
	private User user;
	private Set<CD> cdCollection;
	private TrackSelector ts;
	public JukeBox(CDPlayer cdplayer, User user, Set<CD> cdCollection,
			TrackSelector ts) {
		this.cdplayer = cdplayer;
		this.user = user;
		this.cdCollection = cdCollection;
		this.ts = ts;
	}
	
	public Song getCurrentSong(){
		return null;
	}
	
	public void changUser(User user){
		this.user = user;
	}
	
	
	
	
}

class CDPlayer {
	private CD cd;
	private PlayList pl;
	private boolean play;
	public CDPlayer(CD cd, PlayList pl, boolean play) {
		super();
		this.cd = cd;
		this.pl = pl;
		this.play = play;
	}
	
	
}

class PlayList{
	private Queue<Song> queue;
	
	public PlayList(Queue<Song> queue) {
		super();
		this.queue = queue;
	}

	public void addSong(Song song){
		queue.add(song);
	}
}

class User{
	private String name;
}

class CD{
	
}

class Song{
	
}

class TrackSelector{
	
}