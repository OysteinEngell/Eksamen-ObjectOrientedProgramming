import java.util.Scanner;

public class UserInterface {

    Scanner userInput = new Scanner(System.in);

    //[*] The Scoreboard Object is created at the start and is used to create a user and keep track of the score.
    Scoreboard player = new Scoreboard();

    //[*] Before logging in the user is prompted to create a username.
    //[*] The user input is validated in the setUsername method and returned as a Boolean.
    //[*] If the input is not accepted, the method starts over.
    public void loginMenu(){
        System.out.println("Type in a username:");
        if (player.setUsername(userInput.next())){
            optionsMenu();
        }
        else{
        loginMenu();
        }
    }

    //[*] This is the main menu.
    //[*] The menu runs in a while loop using a Boolean as a condition. It ends when the user selects the option to quit.
    //[*] User-inputs trigger sub-menu-methods to be called. When they are completed, the user returns to this menu.
    public void optionsMenu(){
        boolean on = true;
        while (on) {
            System.out.println("------------------");
            System.out.println("[1] -> Select quiz");
            System.out.println("[2] -> Scoreboard");
            System.out.println("[3] -> Quit");

            switch (userInput.next()) {
                case "1" -> quizMenu();
                case "2" -> scoreMenu();
                case "3" -> {
                    System.out.println("Goodbye!");
                    on = false;
                }
                default -> System.out.println("Please enter valid option");
            }
        }
    }

    //[*] This is a sub-menu called from optionsMenu(main-menu) where the user can select the quiz category.
    //[*] In this game there are two types of quiz types. Multiple choice and true/false.
    //[*] A Quiz Object is created when the user selects their respective category.
    //[*] From the quiz Object the game is started. The Scoreboard Object is used as a parameter to update the score.
    public void quizMenu(){
        System.out.println("Select topic:");
        System.out.println("[1] -> History");
        System.out.println("[2] -> Trivia");
        System.out.println("[3] -> Return to main menu");

        player.setScore(0);
        switch (userInput.next()){
            case "1" -> {
                player.setTopic("History");
                QuizMulti quiz = new QuizMulti();
                quiz.initQuiz(player);}
            case "2" -> {
                player.setTopic("Trivia");
                QuizBinary quiz = new QuizBinary();
                quiz.initQuiz(player);}
            case "3" -> optionsMenu();
            default -> {System.out.println("Please enter valid option");
                quizMenu();}
        }
    }

    //[*] Sub-menu for displaying the scores stored in the database.
    public void scoreMenu(){
        System.out.println("Highscores");
        System.out.println("[1] -> Leaderboard");
        System.out.println("[2] -> Personal record");
        System.out.println("[3] -> Return to main menu");

        switch (userInput.next()){
            case "1" -> player.printLeaderboard();
            case "2" -> player.printPersonalRecord();
            case "3" -> optionsMenu();
            default -> {
                System.out.println("Please enter valid option");
                scoreMenu();
            }
        }
    }

}