import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Question {

    // finals
    private static final int MAX_ANSWERS = 10;
    private static int QUESTION_NUMBER_COUNTER;

    // member variables
    private int questionNumber;
    private int numOfAnswers;
    private String questionTitle;
    private int numOfCorrectAnswers;
    private int correctAnswersNum;
    private Answer [] answersForThisQuestion;

    // constructors

    // no-args constructor <for hard coded questions>
    public Question() {
        this.questionNumber = ++QUESTION_NUMBER_COUNTER;
        this.answersForThisQuestion = new Answer[MAX_ANSWERS];
    }

    public Question(String questionTitle, int numOfAnswers) {
        this.questionNumber = ++QUESTION_NUMBER_COUNTER;
        this.setQuestionTitle(questionTitle);
        this.setNumOfAnswers(numOfAnswers);
        this.answersForThisQuestion = new Answer[MAX_ANSWERS];
        createAnswersForThisQuestion();
        this.setCorrectAnswersNum(correctAnswersNum);
    }


    // getters and setters
    public static int getQuestionNumberCounter() {
        return QUESTION_NUMBER_COUNTER;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getNumOfAnswers() {
        return numOfAnswers;
    }

    public void setNumOfAnswers(int numOfAnswers) {
        if(numOfAnswers <= MAX_ANSWERS-2) {
            this.numOfAnswers = numOfAnswers;
        }
    }


    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getNumOfCorrectAnswers() {
        return numOfCorrectAnswers;
    }

    public void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
        if(numOfCorrectAnswers > 0 && numOfCorrectAnswers < numOfAnswers) {
            this.numOfCorrectAnswers = numOfCorrectAnswers;
        }
    }

    public void setCorrectAnswersNum(int correctAnswersNum) {
        if(correctAnswersNum > 0 && correctAnswersNum <answersForThisQuestion.length) {
            this.answersForThisQuestion[correctAnswersNum - 1].setStatus(true);
        }
    }

    public Answer[] getAnswersForThisQuestion() {
        return answersForThisQuestion;
    }

    public void setAnswerForThisQuestion(int ansNum, String title) {
        this.answersForThisQuestion[ansNum-1] = new Answer();
        this.answersForThisQuestion[ansNum-1].setAnswerNumber(ansNum);
        this.answersForThisQuestion[ansNum-1].setAnswerTitle(title);
    }
    // methods

    public void printAnswersForQuestion() {
        for(int i = 0; i < answersForThisQuestion.length; i++) {
            if(answersForThisQuestion[i] != null && answersForThisQuestion[i].getAnswerNumber() > 0) {
                System.out.print(answersForThisQuestion[i].getAnswerNumber()+ ": " +
                        answersForThisQuestion[i].getAnswerTitle() + " (" +
                        answersForThisQuestion[i].getAnswerStatus() + ")");
                System.out.println();
            }
        }
    }

    public void createAnswersForThisQuestion() {
        for(int i=0; i<numOfAnswers; i++){
            this.answersForThisQuestion[i] = new Answer();
            this.answersForThisQuestion[i].setAnswerNumber(i+1);
        }
    }

    // remove Answer for question selected by user
    // check if num is valid and then shift the other Answer object
    // set new answer number for the answers in the array
    public void removeAnswerForQuestion(int aNum) {
        int index = aNum-1;

        if(index >=0 && index < indexOfFirstNull()) {
            this.answersForThisQuestion[index] = null;
            System.out.println("The answer has deleted.");

            for (int i = index; i < answersForThisQuestion.length - 1; i++) {
                this.answersForThisQuestion[i] = answersForThisQuestion[i + 1];
            }
            answersForThisQuestion[answersForThisQuestion.length - 1] = null;

            for (int i = 0; i < answersForThisQuestion.length; i++) {
                if (answersForThisQuestion[i] != null && answersForThisQuestion[i].getAnswerNumber() >= 1) {
                    System.out.print(answersForThisQuestion[i].getAnswerNumber());
                    this.answersForThisQuestion[i].setAnswerNumber(i + 1);
                    System.out.print(answersForThisQuestion[i].getAnswerNumber());
                }
            }
        }
        else
            System.out.println("Wrong number has been selected, try again");
    }

    // Add answer by user input=> create a new answer object in the array, setting title and answer number.
    public void addAnswerToQuestion(String title) {
        int index = indexOfFirstNull();
        if(indexOfFirstNull() < 8) {
            answersForThisQuestion[index] = new Answer();
            answersForThisQuestion[index].setAnswerTitle(title);
            answersForThisQuestion[index].setAnswerNumber(index+1);
            System.out.println("Answer has added!");

        } else {
            System.out.println("Cannot add more answers! it reached for the maximum number");
        }
    }

    // return the number of the first null inside the answers array.
    public int indexOfFirstNull() {
        int index = 0;
        for (int i = 0; i < answersForThisQuestion.length; i++) {
            if (answersForThisQuestion[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionNumber=" + questionNumber +
                ", questionTitle='" + questionTitle + '\'' +
                ", answersForThisQuestion=" + Arrays.toString(answersForThisQuestion) +
                '}';
    }
}
