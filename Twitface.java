package socialNetwork;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Twitface {

	private Graph<String> friends;
	private ArrayList<Thread> threads;
	private String key;

	//constructor
	public Twitface() {
		friends = new Graph<String>();
		threads = new ArrayList<Thread>();
		key = "key";
	}

	//adds a new friend to the graph of users and returns true if successful
	//and false otherwise
	public boolean addUser(String name) {
		return friends.addVertexToGraph(name);
	}

	//returns a collection of all users currently in the graph
	public Collection<String> getAllUsers() {
		return friends.getVertices();
	}

	//adds edges(friendships) between the passed in users in the graph if they
	//are not already friends and returns true if successful and false
	//otherwise
	public boolean friend(String user1, String user2) {
		boolean result = false;
		if (!user1.equals(user2) && !friends.hasEdge(user1, user2)) {
			friends.addEdgeToGraph(user1, user2, 0);
			friends.addEdgeToGraph(user2, user1, 0);
			result = true;
		}
		return result;
	}

	//returns a collection of all users that are friends of the passed in user
	//in the graph
	public Collection<String> getFriends(String user) {
		return friends.neighborsOfVertex(user);
	}

	//deletes the edge(friendship) between the two passed in users and returns
	//true if successful and false if not
	public boolean unfriend(String user1, String user2) {
		friends.removeAllEdges(user1, user2);
		return friends.removeAllEdges(user2, user1);
	}

	//returns a collection of all of the passed in user's friends' friends
	public Collection<String> peopleYouMayKnow(String user) {
		Collection<String> friendSuggestions = new HashSet<String>();
		Collection<String> friendsOfUser= friends.neighborsOfVertex(user);
		for (String s : friendsOfUser) {
			friendSuggestions.addAll(friends.neighborsOfVertex(s));
		}
		friendSuggestions.remove(user);
		return friendSuggestions;
	}

	//creates a new Thread for each url in the passed in list, then starts them
	//all so they run concurrently
	public void readFriendData(List<String> urls) {
		for (String s : urls) {
			threads.add(new Thread(new ThreadFriend(s)));
		} 
		for (Thread t : threads) {
			t.start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//inner class that implements Runnable, to be used to facilitate the use of
	//concurrency and thread in this project
	private class ThreadFriend implements Runnable {

		private String url;

		//constructor that initializes the String url to the passed in url
		public ThreadFriend(String url) {
			this.url = url;
		}

		@Override
		//this method contains the code to be run when start is called on a thread,
		//and analyzes the url that is associated with the thread, adding users
		//and friendships to the Twitface object of the outer class based on the
		//data collected from the webpage
		public void run() {
			String curr = "";
			
			try {
				//establishes the connection to the webpage as well as a Scanner
				//to read information from the page
				
				URL url1 = new URL(url);
				URLConnection con1 = url1.openConnection();
				Scanner inputStream = new Scanner(new InputStreamReader(con1.getInputStream()));

				//passes over any lines before the start of the body
				while (!curr.contains("<body>")) {
					curr = inputStream.nextLine();
				}

				//when the body is reached, the data in from the webpage will be
				//processed until the body ends
				while(!curr.contains("</body>")) {
					curr = inputStream.next();

					if (curr.contains("addperson")) { //if adding user
						//stores a temporary string, the name of the user to be added
						curr = inputStream.next();
						synchronized(key) {//locks the addition no duplicates are added
							addUser(curr);//adds the user
						}
					}
					else if (curr.contains("addfriend")) { //if adding friendship
						//stores two temporary strings of the two users who to be friends
						curr = inputStream.next();
						String friendToAdd = inputStream.next();
						synchronized(key) {//locks the addition so no duplicates are added
							friend(curr, friendToAdd);//creates the friendship
						}
					}
					curr = inputStream.next();//moves the reader to the next line
				}
				inputStream.close();
			} catch (IOException e) {//catches any exceptions that may be encountered
				e.printStackTrace();
			}
		}
	}
}
