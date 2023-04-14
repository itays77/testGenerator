import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Test {

    //members
    private int numOfQuestions;
    private Question[] testQuestions;


    //constructors
    public Test(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
        this.testQuestions = new Question[numOfQuestions];
        for(int i = 0; i<numOfQuestions; i++ ) {
            testQuestions[i] = new Question();
            testQuestions[i].setQuestionNumber(i+1);
        }
    }


    //getters and setters

    public Question[] getTestQuestions() {
        return testQuestions;
    }


    //methods

    public void addQuestionForThisTest(int j, int qNum, QuestionRepository q, int numOfAnswers) {
        this.testQuestions[j].setQuestionTitle(q.getQuestionFromRepository(qNum).getQuestionTitle());

        for (int i = 0; i < numOfAnswers + 2; i++) {
            this.testQuestions[j].getAnswersForThisQuestion()[i] = new Answer();
            this.testQuestions[j].getAnswersForThisQuestion()[i].setAnswerNumber(i + 1);

        }
        this.testQuestions[j].getAnswersForThisQuestion()[numOfAnswers].setAnswerTitle("None of the above");
        this.testQuestions[j].getAnswersForThisQuestion()[numOfAnswers+1].setAnswerTitle("More than one answer");

    }


    public void addAnsForThisQuestion(int i,int aNum, QuestionRepository q, int qAdd, int j) {

        this.testQuestions[i].getAnswersForThisQuestion()[j].setAnswerTitle(q.getQuestionsRepository()[qAdd - 1].getAnswersForThisQuestion()[aNum - 1].getAnswerTitle());
    }

    public void setStatus(int numOfCorrectAnswers, int numAns, int i) {
        if (numOfCorrectAnswers == 0) {
            this.testQuestions[i].getAnswersForThisQuestion()[numAns].setStatus(true);
        } else if (numOfCorrectAnswers > 1) {
            this.testQuestions[i].getAnswersForThisQuestion()[numAns + 1].setStatus(true);
        }
    }



    public void printQuestions() {
        for (int i = 0; i < testQuestions.length; i++) {
            System.out.println(testQuestions[i].getQuestionTitle());
        }
    }

    public int indexOfFirstNull() {
        int index = 0;
        for (int i = 0; i < testQuestions.length; i++) {
            if (testQuestions[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int indexOfLastQuestion() {
        int index = 0;
        for (int i = 0; i < testQuestions.length; i++) {
            if (testQuestions[i] == null) {
                index = i - 1;
                break;
            }
        }
        return index;
    }

    public void createQuestionAndAnswersFile(Question[] questions) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
        String formattedDateTime = now.format(formatter);

        // Create the file with date format
        String fileName = "exam_" + formattedDateTime + ".txt";
        File file = new File(fileName);

        FileWriter writer = new FileWriter(file);
        // Write the questions and answers to the file (objects only)
        for (Question question : questions) {
            if (question != null) {
                writer.write(question.getQuestionNumber() +". "+ question.getQuestionTitle() + "\n");
                for (int i = 0; i < question.getAnswersForThisQuestion().length; i++) {
                    Answer answer = question.getAnswersForThisQuestion()[i];
                    if (answer != null && answer.getAnswerTitle() != null) {
                        writer.write(answer.getAnswerNumber() + " " + answer.getAnswerTitle() + "\n");
                    }
                }
                writer.write("\n");
            }
        }
        writer.close();
    }

    public void createSolutionFile(Question[] questions) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
        String formattedDateTime = now.format(formatter);

        // Create the file with date format
        String fileName = "solution_" + formattedDateTime + ".txt";
        File file = new File(fileName);

        FileWriter writer = new FileWriter(file);
        // Write the questions and the solution's to the file
        for (Question question : questions) {
            if (question != null) {
                writer.write(question.getQuestionNumber() +". "+ question.getQuestionTitle() + "\n");
                for (int i = 0; i < question.getAnswersForThisQuestion().length; i++) {
                    Answer answer = question.getAnswersForThisQuestion()[i];
                    if (answer != null && answer.getAnswerTitle() != null && answer.getAnswerStatus() == true) {
                        writer.write(answer.getAnswerNumber() + " " + answer.getAnswerTitle() + "\n");
                    }
                }
                writer.write("\n");
            }
        }
        writer.close();
    }
}
