import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        QuestionRepository questionRepo = new QuestionRepository();

        // Adding 5 <!hard-coded!> questions to the repository
        questionRepo.setQuestionsInRepository(0);
        questionRepo.getQuestionFromRepository(1).setQuestionTitle("How much is 10 + 10 ?");
        questionRepo.getQuestionFromRepository(1).setAnswerForThisQuestion(1, "50");
        questionRepo.getQuestionFromRepository(1).setAnswerForThisQuestion(2, "78");
        questionRepo.getQuestionFromRepository(1).setAnswerForThisQuestion(3, "32");
        questionRepo.getQuestionFromRepository(1).setAnswerForThisQuestion(4, "6");
        questionRepo.getQuestionFromRepository(1).setAnswerForThisQuestion(5, "20");
        questionRepo.getQuestionFromRepository(1).setAnswerForThisQuestion(6, "75");
        questionRepo.getQuestionFromRepository(1).setNumOfCorrectAnswers(1);
        questionRepo.getQuestionFromRepository(1).getAnswersForThisQuestion()[4].setStatus(true);

        questionRepo.setQuestionsInRepository(1);
        questionRepo.getQuestionFromRepository(2).setQuestionTitle("How much is 25 + 25 ?");
        questionRepo.getQuestionFromRepository(2).setAnswerForThisQuestion(1, "50");
        questionRepo.getQuestionFromRepository(2).setAnswerForThisQuestion(2, "35");
        questionRepo.getQuestionFromRepository(2).setAnswerForThisQuestion(3, "20");
        questionRepo.getQuestionFromRepository(2).setAnswerForThisQuestion(4, "100");
        questionRepo.getQuestionFromRepository(2).setAnswerForThisQuestion(5, "20");
        questionRepo.getQuestionFromRepository(2).setAnswerForThisQuestion(6, "75");
        questionRepo.getQuestionFromRepository(2).setNumOfCorrectAnswers(1);
        questionRepo.getQuestionFromRepository(2).getAnswersForThisQuestion()[0].setStatus(true);

        questionRepo.setQuestionsInRepository(2);
        questionRepo.getQuestionFromRepository(3).setQuestionTitle("How much is 50 + 35 ?");
        questionRepo.getQuestionFromRepository(3).setAnswerForThisQuestion(1, "50");
        questionRepo.getQuestionFromRepository(3).setAnswerForThisQuestion(2, "85");
        questionRepo.getQuestionFromRepository(3).setAnswerForThisQuestion(3, "32");
        questionRepo.getQuestionFromRepository(3).setNumOfCorrectAnswers(1);
        questionRepo.getQuestionFromRepository(3).getAnswersForThisQuestion()[1].setStatus(true);

        questionRepo.setQuestionsInRepository(3);
        questionRepo.getQuestionFromRepository(4).setQuestionTitle("How much is 90 - 10 ?");
        questionRepo.getQuestionFromRepository(4).setAnswerForThisQuestion(1, "13");
        questionRepo.getQuestionFromRepository(4).setAnswerForThisQuestion(2, "50");
        questionRepo.getQuestionFromRepository(4).setAnswerForThisQuestion(3, "100");
        questionRepo.getQuestionFromRepository(4).setAnswerForThisQuestion(4, "80");
        questionRepo.getQuestionFromRepository(4).setAnswerForThisQuestion(5, "35");
        questionRepo.getQuestionFromRepository(4).setAnswerForThisQuestion(6, "65");
        questionRepo.getQuestionFromRepository(4).setNumOfCorrectAnswers(1);
        questionRepo.getQuestionFromRepository(4).getAnswersForThisQuestion()[3].setStatus(true);

        questionRepo.setQuestionsInRepository(4);
        questionRepo.getQuestionFromRepository(5).setQuestionTitle("How much is 22 + 22 ?");
        questionRepo.getQuestionFromRepository(5).setAnswerForThisQuestion(1, "99");
        questionRepo.getQuestionFromRepository(5).setAnswerForThisQuestion(2, "66");
        questionRepo.getQuestionFromRepository(5).setAnswerForThisQuestion(3, "22");
        questionRepo.getQuestionFromRepository(5).setAnswerForThisQuestion(4, "50");
        questionRepo.getQuestionFromRepository(5).setAnswerForThisQuestion(5, "35");
        questionRepo.getQuestionFromRepository(5).setAnswerForThisQuestion(6, "44");
        questionRepo.getQuestionFromRepository(5).setNumOfCorrectAnswers(1);
        questionRepo.getQuestionFromRepository(5).getAnswersForThisQuestion()[5].setStatus(true);

        menu(questionRepo);
    }

    public static void menu(QuestionRepository questionRepo) throws IOException {

        int answer;
        boolean stop = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nWelcome to the Q&A Repository!");
        System.out.println("\n\nReview, Update, Add new ones and create tets FAST!");

        do {

            System.out.println("\n\nWhat Would you like to do?");
            System.out.println("Enter 1 - Review existing questions and their answers from the repository");
            System.out.println("Enter 2 - Add new answer to existing question");
            System.out.println("Enter 3 - Add new question to the repository");
            System.out.println("Enter 4 - Delete answer to existing question");
            System.out.println("Enter 5 - Delete question from repository");
            System.out.println("Enter 6 - create ** TEST **");
            System.out.println("==> Enter 0 - Stop! <==");

            answer = scan.nextInt();
            scan.nextLine();

            switch (answer) {

                case 1: {
                    questionRepo.printRepository();

                    break;
                }
                case 2: {
                    System.out.println("For which question you want to add? ");
                    questionRepo.printRepositoryQuestions();
                    int qNum = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter answer title:");
                    String title = scan.nextLine();
                    questionRepo.getQuestionsRepository()[qNum - 1].addAnswerToQuestion(title);

                    break;
                }
                case 3: {
                    System.out.println("Enter question title: ");
                    String title = scan.nextLine();
                    System.out.println("How many answers for this question?");
                    int numOfAnswers = scan.nextInt();
                    questionRepo.addQuestionToRepository(title, numOfAnswers);
                    scan.nextLine();
                    for (int i = 0; i < numOfAnswers; i++) {
                        System.out.println("Enter answer");
                        String ans = scan.nextLine();
                        questionRepo.getQuestionsRepository()[questionRepo.indexOfLastQuestion()].getAnswersForThisQuestion()[i].setAnswerTitle(ans);
                    }
                    System.out.println("Which answer is correct?");
                    int aNum = scan.nextInt();
                    questionRepo.getQuestionsRepository()[questionRepo.indexOfLastQuestion()].printAnswersForQuestion();
                    questionRepo.getQuestionsRepository()[questionRepo.indexOfLastQuestion()].setAnswerStatus(aNum - 1);
                    System.out.println("New question has been successfully added to the repository");

                    break;
                }
                case 4: {
                    int num1 = scan.nextInt();
                    questionRepo.printRepositoryQuestions();
                    System.out.println("Which question you would like to delete answer from?");
                    questionRepo.getQuestionsRepository()[num1].printAnswersForQuestion();
                    System.out.println("Which answer you would like to delete?");
                    int num2 = scan.nextInt();
                    questionRepo.getQuestionsRepository()[num1].removeAnswerForQuestion(num2);
                    System.out.println("The answer has deleted");

                    break;
                }
                case 5: {
                    System.out.println("Which QUESTION you would like to delete from the repository?");
                    questionRepo.printRepositoryQuestions();
                    questionRepo.removeQuestionFromRepository(scan.nextInt());
                    System.out.println("The question has deleted");

                    break;
                }
                case 6: {

                    System.out.println("How many questions will be in the test?");
                    int numOfQuestions = scan.nextInt();
                    Test t1 = new Test(numOfQuestions);

                    for (int i = 0; i < numOfQuestions; i++) {
                        questionRepo.printRepositoryQuestions();
                        System.out.println("");
                        System.out.println("Which questions you like to add?");
                        int qAdd = scan.nextInt();
                        System.out.println("How much answers you like to add?");
                        int numAns = scan.nextInt();
                        t1.addQuestionForThisTest(i, qAdd, questionRepo, numAns);
                        for (int j = 0; j < numAns; j++) {
                            questionRepo.getQuestion(qAdd).printAnswersForQuestion();
                            System.out.println("Which answer you like to add?");
                            int aNum = scan.nextInt();
                            t1.addAnsForThisQuestion(i, aNum, questionRepo, qAdd, j);
                        }
                        System.out.println("How much answers are correct?");
                        int numOfCorrectAnswers = scan.nextInt();
                        if (numOfCorrectAnswers == 1) {
                            System.out.println("Which answer is correct?");
                            int correctAnswerNum = scan.nextInt();
                            t1.getTestQuestions()[i].getAnswersForThisQuestion()[correctAnswerNum-1].setStatus(true);
                        }
                        t1.setStatus(numOfCorrectAnswers, numAns, i);
                    }
                    t1.createQuestionAndAnswersFile(t1.getTestQuestions());
                    t1.createSolutionFile(t1.getTestQuestions());
                    System.out.println("New test created");
                }

                case 0: {
                    stop = true;
                    break;
                }
            }
        } while (stop == false);
    }
}

