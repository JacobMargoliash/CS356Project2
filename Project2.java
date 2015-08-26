import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Project2 implements ActionListener {
	private JFrame frame;
	private JFrame userView;
	private JTextArea jta;
	private JTextArea userId;
	private JTextArea groupId;
	private JTextArea following;
	private JTextArea newsFeed;
	private JTextArea userId2;
	private JTextArea tweet;

	public Project2() {
		frame = new JFrame("");
		frame.setLayout(new GridLayout(2, 2));
		frame.setSize(300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jta = new JTextArea("Tree View", 17, 25);
		jta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		jta.setEditable(false);
		frame.add(jta);

		userId = new JTextArea("User Id");
		groupId = new JTextArea("Group Id");

		JButton addUser = new JButton("Add User");
		JButton addGroup= new JButton("Add Group");
		JButton openUserView= new JButton("Open User View");
		JButton showUserTotal= new JButton("Show User Total");
		JButton showGroupTotal = new JButton("Show Group Total");
		JButton showMessagesTotal = new JButton("Show Messages Total");
		JButton showPositivePercentage = new JButton("Show Positive Percentage");

		addUser.addActionListener(this);
		addGroup.addActionListener(this);
		openUserView.addActionListener(this);
		showUserTotal.addActionListener(this);
		showGroupTotal.addActionListener(this);
		showMessagesTotal.addActionListener(this);
		showPositivePercentage.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 2));
		
		buttonPanel.add(userId);
		buttonPanel.add(addUser);
		buttonPanel.add(groupId);
		buttonPanel.add(addGroup);
		buttonPanel.add(openUserView);
		buttonPanel.add(showUserTotal);
		buttonPanel.add(showGroupTotal);
		buttonPanel.add(showMessagesTotal);
		buttonPanel.add(showPositivePercentage);
		
		frame.add(buttonPanel);
		frame.setVisible(true);

		userView = new JFrame("");
		userView.setLayout(new GridLayout(2, 2));
		userView.setSize(300,500);
		
		following = new JTextArea("Current Following", 17, 5);
		following.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		JButton followUser = new JButton("Follow User");
		userId2 = new JTextArea("User Id");
		
		JPanel followingPanel = new JPanel();
		followingPanel.setLayout(new GridLayout(2, 2));

		followingPanel.add(userId2);
		followingPanel.add(followUser);
		followingPanel.add(following);

		userView.add(followingPanel);

		tweet = new JTextArea("Tweet Message");
		JButton postTweet = new JButton("Post Tweet");
		newsFeed = new JTextArea("News Feed", 30, 5);
		newsFeed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		newsFeed.setEditable(false);

		postTweet.addActionListener(this);
		followUser.addActionListener(this);

		JPanel tweetPanel = new JPanel();
		tweetPanel.setLayout(new GridLayout(2, 2));

		tweetPanel.add(tweet);
		tweetPanel.add(postTweet);
		tweetPanel.add(newsFeed);
	
		userView.add(tweetPanel);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Add User")) {
			jta.append("\n" + userId.getText());

			User user = new User(userId.getText());
		}

		else if (ae.getActionCommand().equals("Add Group")) {
			jta.append("\n" + groupId.getText());

			Group group = new Group(groupId.getText());
		}

		else if (ae.getActionCommand().equals("Open User View")) {
			try {
				if (!jta.getSelectedText().equals("null")) {
					userView.setVisible(true);
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Nothing selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (ae.getActionCommand().equals("Show User Total")) {
			JOptionPane.showMessageDialog(frame, User.getUserTotal());	
		}

		else if (ae.getActionCommand().equals("Show Group Total")) {
			JOptionPane.showMessageDialog(frame, Group.getGroupTotal());	
		}

		else if (ae.getActionCommand().equals("Show Messages Total")) {
			JOptionPane.showMessageDialog(frame, User.getMessageTotal());	
		}

		else if (ae.getActionCommand().equals("Show Positive Percentage")) {
			String[] newsFeed = User.getNewsFeed();

			int positive = 0;
			int i = 0;
			while (!(newsFeed[i] == null)) {
				if (newsFeed[i].contains("good") || newsFeed[i].contains("great") || newsFeed[i].contains("excellent"))
					positive++;
				i++;
			}


			JOptionPane.showMessageDialog(frame, positive * 100.0/i + "%");	
		}

		else if (ae.getActionCommand().equals("Follow User")) {
			following.append("\n" + userId2.getText());
		}

		else if (ae.getActionCommand().equals("Post Tweet")) {
			User.postTweet(tweet.getText());
			newsFeed.append("\n" + jta.getSelectedText() + ": " + tweet.getText());
		}
	}


	public static void main(String args[]) {
        	SwingUtilities.invokeLater(new Runnable() {
            		public void run() {
                		new Project2();
            		}
        	});

    	}
}

class User {
	private String id;
	private String[] following;  //List<String>
	private String[] followers;
	private static int userTotal = 0;
	private static String[] newsFeed = new String[10];
	private static int currentIndex = 0;

	public User (String id) {
		this.id = id;
		userTotal++;
	}

	public static void postTweet (String text) {
		newsFeed[currentIndex] = text;
		currentIndex++;
	}
		
	public static int getUserTotal () {
		return userTotal;
	}
		
	public static String[] getNewsFeed () {
		return newsFeed;
	}

	public static int getMessageTotal () {
		return currentIndex;
	}

}

class Group {
	private String id;
	private String[] users;
	private static int groupTotal = 0;

	public Group(String id) {
		this.id = id;
		groupTotal++;
	}

	public static int getGroupTotal () {
		return groupTotal;
	}
}





















