import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

    //members
    private int numOfQuestions;
    private Question[] testQuestions;


    //constructors
    public Test(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
        this.testQuestions = new Question[numOfQuestions];
    }


    //getters and setters

    public Question[] getTestQuestions() {
        return testQuestions;
    }

    public CloseQuestion getCloseQuestionFromTest(int i) {
        return (CloseQuestion) testQuestions[i];
    }

    public void addQuestionForThisTest() {
        for (int k = 0; k < numOfQuestions; k++) {

        }
    }
    //methods

    // add question for the test

    public void addOpenQuestionForThisTest(int j, int qNum, QuestionRepository q) {
        testQuestions[j].setQuestionNumber(j + 1);
        testQuestions[j].setDiffLevel(q.getQuestionsRepository()[qNum - 1].diffLevel);
        ((OpenQuestion) testQuestions[j]).setQuestionTitle(q.getOpenQuestionFromRepository(qNum).getQuestionTitle());
        ((OpenQuestion) testQuestions[j]).setQuestionAnswer(q.getOpenQuestionFromRepository(qNum).getQuestionAnswer());

    }

    public void addCloseQuestionForThisTest(int j, int qNum, QuestionRepository q, int numOfAnswers) {
        testQuestions[j].setQuestionNumber(j + 1);
        testQuestions[j].setDiffLevel(q.getQuestionsRepository()[qNum - 1].diffLevel);
        ((CloseQuestion) testQuestions[j]).setQuestionTitle(q.getCloseQuestionFromRepository(qNum).getQuestionTitle());
        //create new Answer object
        for (int i = 0; i < numOfAnswers + 2; i++) {
            ((CloseQuestion) testQuestions[j]).getAnswersForThisQuestion()[i] = new Answer();
            ((CloseQuestion) testQuestions[j]).getAnswersForThisQuestion()[i].setAnswerNumber(i + 1);

        }
        // add the default answers in first null objects of the array
        ((CloseQuestion) testQuestions[j]).getAnswersForThisQuestion()[numOfAnswers].setAnswerTitle("None of the above");
        ((CloseQuestion) testQuestions[j]).getAnswersForThisQuestion()[numOfAnswers + 1].setAnswerTitle("More than one answer");

    }

    public void addAnsForThisQuestion(int i, int aNum, QuestionRepository q, int qAdd, int j) {
        ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setAnswerTitle(q.getCloseQuestionFromRepository(qAdd).getAnswersForThisQuestion()[aNum - 1].getAnswerTitle());
    }

    // set boolean status of question object in the test by user choice
    public void setStatus(int numOfCorrectAnswers, int numAns, int i) {
        if (numOfCorrectAnswers == 0) {
            ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[numAns].setStatus(true);
        } else if (numOfCorrectAnswers > 1) {
            ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[numAns + 1].setStatus(true);
        }
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
                if (question instanceof CloseQuestion) {
                    writer.write(question.getQuestionNumber() + ". " + question.getQuestionTitle() + "\n");
                    for (int i = 0; i < ((CloseQuestion) question).getAnswersForThisQuestion().length; i++) {
                        Answer answer = ((CloseQuestion) question).getAnswersForThisQuestion()[i];
                        if (answer != null && answer.getAnswerTitle() != null) {
                            writer.write(answer.getAnswerNumber() + " " + answer.getAnswerTitle() + "\n");
                        }
                    }
                    writer.write("\n");
                }
                if (question instanceof OpenQuestion) {
                    writer.write(question.getQuestionNumber() + ". " + question.getQuestionTitle() + "\n");
                    writer.write("The answer is : ______________\n");
                }
            }
            writer.write("\n");
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
                writer.write(question.getQuestionNumber() + ". " + question.getQuestionTitle() + "\n");
                if (question instanceof CloseQuestion) {
                    for (int i = 0; i < ((CloseQuestion) question).getAnswersForThisQuestion().length; i++) {
                        Answer answer = ((CloseQuestion) question).getAnswersForThisQuestion()[i];
                        if (answer != null && answer.getAnswerTitle() != null && answer.getAnswerStatus() == true) {
                            writer.write(answer.getAnswerNumber() + " " + answer.getAnswerTitle() + "\n");
                        }
                    }
                    //writer.write("\n");
                }
                if (question instanceof OpenQuestion) {
                    writer.write("The answer is : " + ((OpenQuestion) question).getQuestionAnswer() + "\n");
                }
            }
            writer.write("\n");
        }

        writer.close();
        }
    }

