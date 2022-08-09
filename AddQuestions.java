import java.util.ArrayList;

public class AddQuestions {
    //[*] This object is used to populate the database with questions and answers.

    static DatabaseConnector database = new DatabaseConnector();

    //[*] Passes in questions and answers as String parameters and stores them in an ArrayList.
    //[*] The Arraylist is used by the addMultiquizToDB method to store the questions.
    public static void addMultiQuizQuestion(String question, String altA, String altB, String altC, String altD, String correctAlt){
        ArrayList<String> multiQuiz = new ArrayList<>();
        multiQuiz.add(question);
        multiQuiz.add(altA);
        multiQuiz.add(altB);
        multiQuiz.add(altC);
        multiQuiz.add(altD);
        multiQuiz.add(correctAlt);

        if (database.addMultiQuizToDB(multiQuiz)){
            System.out.println("Successfully added question to database");
        }else {
            System.out.println("Could not add question to database");
        }
    }

    //[*] Parses java Boolean (true/false) into sql Boolean (1/0).
    public static void addBinaryQuizQuestion(String question, boolean answer){

        String bool = answer? "1" : "0";

        if (database.addBinaryQuizToDB(question, bool)){
            System.out.println("Successfully added question to database");
        }else {
            System.out.println("Could not add question to database");
        }
    }

    public static void historyQuiz1(){
        addMultiQuizQuestion("Whose death sparked World War 1?", "Kaiser Wilhelm", "Archbishop Ussher", "Queen Victoria", "Archduke Franz Ferdinand", "D");
        addMultiQuizQuestion("Where were the Aegean Bronze Age civilizations located?", "Algeria", "India", "Greece", "Spain", "C");
        addMultiQuizQuestion("How many kings of England have been named Henry?", "10", "8", "11", "9", "B");
        addMultiQuizQuestion("In Egyptian mythology, who was the wife of Osiris?", "Oslo", "Isis", "Ozzy", "Iris", "B");
        addMultiQuizQuestion("Who was the first Roman Emperor?", "Julius Caesar", "Claudius", "Augustus", "Nero", "C");
        addMultiQuizQuestion("When did the Cold War officially end?", "1983", "1985", "1989", "1990", "C");
        addMultiQuizQuestion("How many people have walked on the moon?", "9", "10", "11", "12", "D");
        addMultiQuizQuestion("Who painted the ceiling of the Sistine Chapel?", "El Greco", "Michelangelo", "Caravaggio", "Claude Monet", "B");
        addMultiQuizQuestion("In what year did Roald Amundsen of Norway reach the pole of Antarctica?", "1795", "1872", "1911", "1938", "C");
        addMultiQuizQuestion("The fall of Constantinople in 1453 fell to who?", "Christian crusaders", "Ottoman Turks", "Romans", "Mongol hordes", "B");
    }

    public static void triviaQuiz1(){
        addBinaryQuizQuestion("There are over 30,000 varieties of apples existing in the world.", true);
        addBinaryQuizQuestion("Thomas Jefferson was the second president of the U.S.A.", false);
        addBinaryQuizQuestion("The world’s longest word has no less than 189,819 letters and comes from the English language.", true);
        addBinaryQuizQuestion("Rio de Janeiro is the capital city of Brazil.", false);
        addBinaryQuizQuestion("The world’s best-selling music album of all times belongs to The Beatles.", false);
        addBinaryQuizQuestion("Pinocchio was Walt Disney’s first animated feature film in full color.", false);
        addBinaryQuizQuestion("In Scotland, the unicorn is their national animal.", true);
        addBinaryQuizQuestion("When going out of the cave, the bats always turn in the left direction.", true);
        addBinaryQuizQuestion("Switzerland’s currency is the Euro.", false);
        addBinaryQuizQuestion("The USA has the longest coastline in the world.", false);
    }

    //[*] Execute the method here.
    public static void main(String[] args) {
        //historyQuiz1();
        //triviaQuiz1();
    }
}