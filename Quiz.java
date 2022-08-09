import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;


//[*] Quiz Contains fields and methods used when playing the game.
//[*] Quiz Object is abstract and is the parent of the two types of Quiz types(Multi and binary)
//[*] A Database Object is created to access methods to store and retrieve data from the quiz related tables in the database.

public abstract class Quiz {
    DatabaseConnector database = new DatabaseConnector();
    Scanner userInput = new Scanner(System.in);
    public int maxNumberOfQuestions = 5;
    public int totalNumberOfMultiQuestions = database.countMultiQuestionsInDB();
    public int totalNumberOfBinaryQuestions = database.countBinaryQuestionsInDB();

    //[*] General method to shuffle the order of an ArrayList containing ArrayLists.
    public void randomizeQuestions(ArrayList<ArrayList<String>> quizArray){
        Collections.shuffle(quizArray);
    }

    //[*] Method to check if the user has typed in the correct or the wrong answer during the game.
    //[*] The if statement returns true if the user-input is equal to the correct answer that is stored in an Arraylist along with the question.
    //[*] The correct answer in the multiple choice quiz is stored as a single capital letter in a string ( "A" , "B" , "C" or "D" ).
    //[*] The correct answer in the binary quiz is stored as 1(true) or 0(false). In the database this is a Boolean but is stored as a String in the ArrayList.
    //[*] The correct answer is stored as the last string in the ArrayList ( ArrayList.get(ArrayList.size()-1) ).
    //[*] The user-input is parsed to upper case to avoid mismatching.
    public boolean checkAnswer(ArrayList<String> questionArray){
        if (Objects.equals(userInput.next().toUpperCase(), questionArray.get(questionArray.size()-1))){
            System.out.println("Correct!");
            return true;
        }
        else {
            System.out.println("Wrong!");
            return false;
        }
    }
}
