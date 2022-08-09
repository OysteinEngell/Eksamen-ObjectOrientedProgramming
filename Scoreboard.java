import java.util.ArrayList;

public class Scoreboard {

    //[*] Scoreboard Object is created at login and is updated during the game.
    //[*] Username is stored at the start of the menu and is stored in database. It´s also used for retrieving one player´s highscore.
    //[*] Score is initialized with value 0, and is updated at the end of a game, then stored in database.
    //[*] Topic is set when user chooses quiz category.
    DatabaseConnector database = new DatabaseConnector();
    private String username;
    private int score = 0;
    private String topic;

    //[*] Creates an ArrayList that stores ArrayLists returned from database.getScoreboard.
    //[*] for loop uses iterator ( int i ) to get new line each time.
    //[*] Loop uses two conditions, totalNumberOfRecords if less than 10, and 10 if more to limit the list to max 10.
    //[*] List is printed in second loop using printf() to format the output string.
    public void printLeaderboard(){
        ArrayList<ArrayList<String>> scoreboardArray = new ArrayList<>();
        int totalNumberOfRecords = database.countNumberOfRecords();

        for (int i = 0; i < totalNumberOfRecords && i < 10; i++) {
            scoreboardArray.add(database.getScoreboard(i));
        }

        System.out.println("------------");
        System.out.println("LEADERBOARD:");
        System.out.println("------------");

        for (int i = 0; i < scoreboardArray.size(); i++){
            System.out.printf("%s. %s..... SCORE: %s ..... TOPIC: %s%n",
                    i+1, scoreboardArray.get(i).get(0), scoreboardArray.get(i).get(1), scoreboardArray.get(i).get(2));
        }
    }

    //[*] Same as print leaderboard but uses the username field to get one specific player's score.
    public void printPersonalRecord(){
        ArrayList<ArrayList<String>> highscoreArray = new ArrayList<>();
        int totalNumberOfRecords = database.countPersonalRecord(username);

        for (int i = 0; i < totalNumberOfRecords && i < 10; i++) {
            highscoreArray.add(database.getPersonalBestScore(username, i));
        }

        System.out.println("-----------------");
        System.out.printf("%s's HIGHSCORE: %n", username);
        System.out.println("-----------------");

        for (int i = 0; i < highscoreArray.size(); i++){
            System.out.printf("%s. SCORE: %s ..... TOPIC: %s%n",
                    i+1, highscoreArray.get(i).get(1), highscoreArray.get(i).get(2));
        }
    }

    //[*] Passes the Scoreboard Object fields to the databaseConnector method and stores then in database
    public void storeScoreInDB(){
        database.setScore(username, score, topic);
    }

    //[*] Takes inn user-input as a parameter and checks that it is over one char long and less than 25 so that it fits in database.
    public boolean setUsername(String username) {
        if (username.length() > 25){
            System.out.println("Username is too long! Please try again");
            return false;
        }
        else if (username.length() < 1){
            System.out.println("Username is too short! PLease try again");
            return false;
        }
        else {
            this.username = username;
            return true;
        }
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}