import java.util.ArrayList;

//[*] This Object is created when user starts a true/false game.
public class QuizBinary extends Quiz {

    //[*] QuizMulti inherits fields and methods from the Quiz Object.
    //[*] Inherits a DatabaseConnector Object(database) to access database methods.
    public QuizBinary() {
        super();
    }

    //[*] This method starts the quiz game.
    //[*] Arraylist is created and stores the ArrayLists created by the getBinaryQuiz method in the database Object.
    //[*] countScore is declared at the start of each round with the value of 0;
    //[*] The first for loop adds one question-ArrayList at the time and stores it in the quiz-ArrayList. The ArrayList is later shuffled in the randomizeQuestions method.
    //[*] The iterator (int i) from the loop is used in the SQL query to select a new row each time. The +1 is because the database stores the first row as 1 and not 0.
    //[*] Second loop prints one question( printQuestions() ) along with the alternative answers and waits for user-input and checks if it´s correct.
    //[*] The countScore increases when correct and is updated to the Scoreboard Object, then stored in the Database.
    public void initQuiz(Scoreboard player){
        ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
        int countScore = 0;

        for (int i = 0; i < totalNumberOfBinaryQuestions; i++){
            quizArray.add(database.getBinaryQuiz(i+1));
        }

        randomizeQuestions(quizArray);

        for (int i = 0; i < maxNumberOfQuestions; i++){
            printQuestions(quizArray.get(i));
            if (checkAnswer(quizArray.get(i))) countScore++;
        }

        player.setScore(countScore);
        player.storeScoreInDB();
        System.out.println("Well done! Your final score is: " + player.getScore());
    }

    public void printQuestions(ArrayList<String> questionArray){
        System.out.println(questionArray.get(0));
        System.out.println("[1] -> True");
        System.out.println("[0] -> False");
    }
}
