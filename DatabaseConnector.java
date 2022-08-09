import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {

    //Tests the connection to the database using JDBC.
    public void testCon(){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            System.out.println(con);
        } catch (SQLException e) {
            System.out.println("testCon" + e);
        }
    }

    //[*] Adds an Arraylist of questions and answers to database.
    //[*] Arraylist is passed inn as a parameter when the method is called.
    //[*] SQL query uses a preparedStatement which is replaced by the strings in the ArrayList( stmt.setString() ).
    public boolean addMultiQuizToDB(ArrayList<String> questionArray){

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "INSERT INTO quiz2DB.multiQuiz (question, alternativeA, alternativeB, alternativeC, alternativeD, correct) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setString(1, questionArray.get(0));
            stmt.setString(2, questionArray.get(1));
            stmt.setString(3, questionArray.get(2));
            stmt.setString(4, questionArray.get(3));
            stmt.setString(5, questionArray.get(4));
            stmt.setString(6, questionArray.get(5));

            stmt.executeUpdate();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            System.out.println("addMultiQuizToDB " + e);
            return false;
        }
    }

    public boolean addBinaryQuizToDB(String question, String answer){

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "INSERT INTO quiz2DB.binaryQuiz (question, correct) VALUES (?, ?)";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setString(1, question);
            stmt.setString(2, answer);

            stmt.executeUpdate();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            System.out.println("addBinaryQuizToDB" + e);
            return false;
        }
    }


    //[*] Counts how many rows of questions is in the multiQuiz table of the database and returns it as an int.
    //[*] Used as a parameter when retrieving questions during the game.
    public int countMultiQuestionsInDB() {

        int totalNumber = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT COUNT(ID) FROM quiz2DB.multiQuiz";
            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            ResultSet result = stmt.executeQuery();

            if (result.next()){
                totalNumber = result.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("countMultiQuestionsInDB " + e);
        }
        return totalNumber;
    }

    public int countBinaryQuestionsInDB() {

        int totalNumber = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT COUNT(*) FROM quiz2DB.binaryQuiz";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);

            ResultSet result = stmt.executeQuery();

            if (result.next()){
                totalNumber = result.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("countBinaryQuestionsInDB " + e);
        }
        return totalNumber;
    }

    public int countNumberOfRecords(){
        int numberOfRecords = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT COUNT(id) FROM quiz2DB.scoreboard";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()){
                numberOfRecords = resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("getNumberOfRecords" + e);
        }
        return numberOfRecords;
    }

    public int countPersonalRecord(String username){
        int numberOfRecords = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT COUNT(id) FROM quiz2DB.scoreboard WHERE username = ?";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()){
                numberOfRecords = resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("getNumberOfRecords" + e);
        }
        return numberOfRecords;
    }

    //[*] Retrieves one question with the corresponding answers and returns as an ArrayList<String>.
    //[*] This method is called in a loop until all the questions have been stored in an ArrayList<Arraylist>.
    //[*] The iterator from the loop is passed in as the index parameter and used in the query to select a new row in the table.
    public ArrayList<String> getMultiQuiz(int index){
        ArrayList<String> questionArray = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT * FROM quiz2DB.multiQuiz WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setInt(1, index);
            ResultSet result = stmt.executeQuery();

            while (result.next()){
                questionArray.add(result.getString(2));
                questionArray.add(result.getString(3));
                questionArray.add(result.getString(4));
                questionArray.add(result.getString(5));
                questionArray.add(result.getString(6));
                questionArray.add(result.getString(7));
            }
        }
        catch (SQLException e) {
            System.out.println("getMultiQuiz" + e);

        }
        return questionArray;
    }


    public ArrayList<String> getBinaryQuiz(int index){
        ArrayList<String> questionArray = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT * FROM quiz2DB.binaryQuiz WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setInt(1, index);
            ResultSet result = stmt.executeQuery();

            while (result.next()){
                questionArray.add(result.getString(2));
                questionArray.add(result.getString(3));
            }
        }
        catch (SQLException e) {
            System.out.println("getBinaryQuiz" + e);

        }
        return questionArray;
    }

    //[*] Stores the values of the Scoreboard Object in the database.
    public void setScore(String username, int score, String topic ){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "INSERT INTO quiz2DB.scoreboard (username, score, topic) VALUES (?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setString(1, username);
            stmt.setString(2, String.valueOf(score));
            stmt.setString(3, topic);

            stmt.executeUpdate();
            con.commit();

            System.out.println("Record added to Scoreboard");
        }
        catch (SQLException e) {
            System.out.println("setScore " + e);
        }
    }

    //[*] Retrieves one row from the scoreboard table of the database and returns as an Arraylist<String>.
    //[*] This method is called in a loop and uses the iterator as a parameter to get a new line each time.
    //[*] The query first orders all the rows by score, the limits the output to 1.
    //[*] OFFSET is used to change the line that is selected, this is where the index is used to get a new line.
    public ArrayList<String> getScoreboard (int index){
        ArrayList<String> record = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT * FROM quiz2DB.scoreboard ORDER BY score DESC LIMIT 1 OFFSET ?";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setInt(1, index);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                record.add(resultSet.getString(2));
                record.add(resultSet.getString(3));
                record.add(resultSet.getString(4));
            }
        }
        catch (SQLException e) {
            System.out.println("getScoreboard" + e);
        }
        return record;
    }

    //[*] This method is the same as getScoreboard but passes in the username to only query for a specific player.
    public ArrayList<String> getPersonalBestScore(String username, int index){
        ArrayList<String> record = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz2DB?useSSL=false", "admin", "adminroot")) {
            con.setAutoCommit(false);

            String preparedstmt = "SELECT * FROM quiz2DB.scoreboard WHERE username = ? ORDER BY score DESC LIMIT 1 OFFSET ?";

            PreparedStatement stmt = con.prepareStatement(preparedstmt);
            stmt.setString(1, username);
            stmt.setInt(2, index);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                record.add(resultSet.getString(2));
                record.add(resultSet.getString(3));
                record.add(resultSet.getString(4));
            }
        }
        catch (SQLException e) {
            System.out.println("getScoreboard" + e);
        }
        return record;
    }




}