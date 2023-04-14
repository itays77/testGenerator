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

    public int getCorrectAnswersNum() {
        int index = 0;
        for(int i = 0; i<answersForThisQuestion.length; i++) {
            if(answersForThisQuestion[i].getAnswerStatus() == true) {
                index = i + 1;
                break;
            }
        }
        return index;
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





    public void correctAnswers(int correctAnswersNum) {
        if (correctAnswersNum < 1 || correctAnswersNum > answersForThisQuestion.length) {
            System.out.println("Number is out of range");
        } else {
            for (int i = 0; i < correctAnswersNum; i++) {
                System.out.println("The first correct answer is ");
                Scanner scan = new Scanner(System.in);
                int ansNum = scan.nextInt();
                if (ansNum != 0 && ansNum < numOfAnswers) {
                    answersForThisQuestion[ansNum - 1].setStatus(true);
                }
            }
        }
    }

    public void removeAnswerForQuestion(int aNum) {
        int index = aNum-1;

        if(index >=0 && index < this.answersForThisQuestion.length) {
            this.answersForThisQuestion[index] = null;

            for(int i = index; i< answersForThisQuestion.length-1; i++) {
                this.answersForThisQuestion[i] = answersForThisQuestion[i + 1];
            }
        }

        answersForThisQuestion[answersForThisQuestion.length-1] = null;

        for (int i = 0; i < answersForThisQuestion.length; i++) {
            if (answersForThisQuestion[i] != null && answersForThisQuestion[i].getAnswerNumber() >= 1) {
                System.out.print(answersForThisQuestion[i].getAnswerNumber());
                this.answersForThisQuestion[i].setAnswerNumber(i+1);
                System.out.print(answersForThisQuestion[i].getAnswerNumber());

            }
        }
    }

    public void addAnswerToQuestion(String title) {
        int index = indexOfFirstNull();
        if(indexOfFirstNull() < 8) {
            this.answersForThisQuestion[index] = new Answer();
            this.answersForThisQuestion[index].setAnswerTitle(title);
            this.answersForThisQuestion[index].setAnswerNumber(index+1);
        } else {
            System.out.println("Cannot add another answer, this question reached the maximum its allowed");
        }
    }

    public void setAnswerStatus(int aNum) {
        answersForThisQuestion[aNum].setStatus(true);
    }

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
