import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        QuestionRepository questionRepo = new QuestionRepository();
        fillQuestionRepo(questionRepo);
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

                    case2(questionRepo, scan);
                    break;



                }
                case 3: {

                    case3(questionRepo, scan);
                    break;
                }
                case 4: {

                    case4(questionRepo, scan);
                    break;
                }
                case 5: {

                    case5(questionRepo, scan);
                    break;
                }
                case 6: {

                    case6(questionRepo, scan);
                    break;
                }


                case 0: {
                    stop = true;
                    break;
                }
            }
        } while (stop == false);
    }




    public static void case2(QuestionRepository questionRepo, Scanner scan) {
        System.out.println("For which question you want to add? ");
        questionRepo.printCloseRepositoryQuestions();
        int qNum = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter answer title:");
        String title = scan.nextLine();
        questionRepo.addAnswerToExistingQuestion(title, qNum);
    }

    public static void case3(QuestionRepository questionRepo, Scanner scan) {
        System.out.println("Would type of question you like to add to the repository? ");
        System.out.println("1. Close Question");
        System.out.println("2. Open Question");
        int type = scan.nextInt();

        if(type == 1) {
            case3CloseQuestion(questionRepo, scan);
        }
        if(type == 2) {
            case3OpenQuestion(questionRepo, scan);
        }


    }

    private static void case3OpenQuestion(QuestionRepository questionRepo, Scanner scan) {
        scan.nextLine();
        System.out.println("Enter question title: ");
        String title = scan.nextLine();
        Question.eDifficultlyLevel diffLevel = setDifficultlyLevels(scan);
        scan.nextLine();
        System.out.println("Enter full answer to the question: ");
        String answer = scan.nextLine();
        questionRepo.addOpenQuestionToRepository(title, answer, diffLevel);

    }

    public static void case3CloseQuestion(QuestionRepository questionRepo, Scanner scan) {
        scan.nextLine();
        System.out.println("Enter question title: ");
        String title = scan.nextLine();

        Question.eDifficultlyLevel diffLevel = setDifficultlyLevels(scan);

        System.out.println("How many answers for this question?");
        int numOfAnswers = scan.nextInt();
        questionRepo.addCloseQuestionToRepository(title, numOfAnswers, diffLevel);
        scan.nextLine();
        for (int i = 0; i < numOfAnswers; i++) {
            System.out.println("Enter answer");
            String ans = scan.nextLine();
            questionRepo.addAnswerToNewQuestion(ans, i);
        }
        questionRepo.getQuestionsRepository()[questionRepo.indexOfLastQuestion()].printAnswersForQuestion();
        System.out.println("Which answer is correct?");
        int aNum = scan.nextInt();
        questionRepo.setStatusForNewQuestion(aNum);
    }

    public static Question.eDifficultlyLevel setDifficultlyLevels(Scanner scan) {
        System.out.println("Choose the difficultly level of the question: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        int d = scan.nextInt();
        if(d == 1) {
            return Question.eDifficultlyLevel.Easy;
        }
        else if(d == 2) {
            return Question.eDifficultlyLevel.Medium;
        }
        else if(d == 3) {
            return Question.eDifficultlyLevel.Hard;
        }
        else {
            System.out.println("Wrong number, try 1,2,3");
            setDifficultlyLevels(scan);
        }
        return Question.eDifficultlyLevel.Hard;
    }

    public static void case4(QuestionRepository questionRepo, Scanner scan) {
        questionRepo.printCloseRepositoryQuestions();
        System.out.println("Which question you would like to delete answer from?");
        int num1 = scan.nextInt();
        questionRepo.getQuestionsRepository()[num1-1].printAnswersForQuestion();
        System.out.println("Which answer you would like to delete?");
        int num2 = scan.nextInt();
        questionRepo.removeAnswerForQuestion(num1, num2);
    }

    public static void case5(QuestionRepository questionRepo, Scanner scan) {
        System.out.println("Which QUESTION you would like to delete from the repository?");
        questionRepo.printRepositoryQuestions();
        questionRepo.removeQuestionFromRepository(scan.nextInt());
    }

    public static void case6(QuestionRepository questionRepo, Scanner scan) throws IOException {
        System.out.println("How many questions will be in the test?");
        int numOfQuestions = scan.nextInt();
        Test t1 = new Test(numOfQuestions);

        for (int i = 0; i < numOfQuestions; i++) {
            questionRepo.printRepositoryQuestions();
            System.out.println("");
            System.out.println("Which questions you like to add?");
            int qAdd = scan.nextInt();

            if (questionRepo.getQuestionsRepository()[qAdd - 1] instanceof CloseQuestion) {
                addCloseQuestionToTest(questionRepo, t1, i, scan, qAdd);
            }
            if (questionRepo.getQuestionsRepository()[qAdd - 1] instanceof OpenQuestion) {
                addOpenQuestionToTheTest(questionRepo, t1, i, scan, qAdd);
            }
        }

        t1.createQuestionAndAnswersFile(t1.getTestQuestions());
        t1.createSolutionFile(t1.getTestQuestions());
        System.out.println("New test created");








    }

    private static void addOpenQuestionToTheTest(QuestionRepository questionRepo, Test t1, int index, Scanner scan, int qAdd) {
        t1.getTestQuestions()[index] = new OpenQuestion();

        t1.addOpenQuestionForThisTest(index, qAdd, questionRepo);


    }

    public static void addCloseQuestionToTest(QuestionRepository questionRepo, Test t1, int index, Scanner scan, int qAdd) {
        t1.getTestQuestions()[index] = new CloseQuestion();


        System.out.println("How much answers you like to add?");
        int numAns = scan.nextInt();

        t1.addCloseQuestionForThisTest(index, qAdd, questionRepo, numAns);
        for (int j = 0; j < numAns; j++) {
            questionRepo.getCloseQuestionFromRepository(qAdd).printAnswersForQuestion();
            System.out.println("Which answer you like to add?");
            int aNum = scan.nextInt();
            t1.addAnsForThisQuestion(index, aNum, questionRepo, qAdd, j);
        }
        System.out.println("How much answers are correct?");
        int numOfCorrectAnswers = scan.nextInt();
        if (numOfCorrectAnswers == 1) {
            System.out.println("Which answer is correct?");
            int correctAnswerNum = scan.nextInt();
            //t1.getTestQuestions()[i].getAnswersForThisQuestion()[correctAnswerNum-1].setStatus(true);
            //t1.getCloseQuestionFromTest(index).getAnswersForThisQuestion()[correctAnswerNum - 1].setStatus(true);
            t1.getCloseQuestionFromTest(index).getAnswersForThisQuestion()[correctAnswerNum-1].setStatus(true);
        }
        t1.setStatus(numOfCorrectAnswers, numAns, index);

    }









    public static void fillQuestionRepo(QuestionRepository questionRepo) {
        questionRepo.setCloseQuestionsInRepository(0);
        questionRepo.getCloseQuestionFromRepository(1).setDiffLevel(Question.eDifficultlyLevel.Easy);
        questionRepo.getCloseQuestionFromRepository(1).setQuestionTitle("How much is 10 + 10 ?");
        questionRepo.getCloseQuestionFromRepository(1).setAnswerForThisQuestion(1, "50");
        questionRepo.getCloseQuestionFromRepository(1).setAnswerForThisQuestion(2, "78");
        questionRepo.getCloseQuestionFromRepository(1).setAnswerForThisQuestion(3, "32");
        questionRepo.getCloseQuestionFromRepository(1).setAnswerForThisQuestion(4, "6");
        questionRepo.getCloseQuestionFromRepository(1).setAnswerForThisQuestion(5, "20");
        questionRepo.getCloseQuestionFromRepository(1).setAnswerForThisQuestion(6, "75");
        questionRepo.getCloseQuestionFromRepository(1).setNumOfCorrectAnswers(1);
        questionRepo.getCloseQuestionFromRepository(1).getAnswersForThisQuestion()[4].setStatus(true);


        questionRepo.setCloseQuestionsInRepository(1);
        questionRepo.getCloseQuestionFromRepository(2).setDiffLevel(Question.eDifficultlyLevel.Easy);
        questionRepo.getCloseQuestionFromRepository(2).setQuestionTitle("How much is 25 + 25 ?");
        questionRepo.getCloseQuestionFromRepository(2).setAnswerForThisQuestion(1, "50");
        questionRepo.getCloseQuestionFromRepository(2).setAnswerForThisQuestion(2, "35");
        questionRepo.getCloseQuestionFromRepository(2).setAnswerForThisQuestion(3, "20");
        questionRepo.getCloseQuestionFromRepository(2).setAnswerForThisQuestion(4, "100");
        questionRepo.getCloseQuestionFromRepository(2).setAnswerForThisQuestion(5, "20");
        questionRepo.getCloseQuestionFromRepository(2).setAnswerForThisQuestion(6, "75");
        questionRepo.getCloseQuestionFromRepository(2).setNumOfCorrectAnswers(1);
        questionRepo.getCloseQuestionFromRepository(2).getAnswersForThisQuestion()[0].setStatus(true);

        questionRepo.setCloseQuestionsInRepository(2);
        questionRepo.getCloseQuestionFromRepository(3).setDiffLevel(Question.eDifficultlyLevel.Medium);
        questionRepo.getCloseQuestionFromRepository(3).setQuestionTitle("How much is 50 + 35 ?");
        questionRepo.getCloseQuestionFromRepository(3).setAnswerForThisQuestion(1, "3");
        questionRepo.getCloseQuestionFromRepository(3).setAnswerForThisQuestion(2, "85");
        questionRepo.getCloseQuestionFromRepository(3).setAnswerForThisQuestion(3, "32");
        questionRepo.getCloseQuestionFromRepository(3).setNumOfCorrectAnswers(1);
        questionRepo.getCloseQuestionFromRepository(3).getAnswersForThisQuestion()[1].setStatus(true);

        questionRepo.setCloseQuestionsInRepository(3);
        questionRepo.getCloseQuestionFromRepository(4).setQuestionTitle("How much is 90 - 10 ?");
        questionRepo.getCloseQuestionFromRepository(4).setDiffLevel(Question.eDifficultlyLevel.Medium);
        questionRepo.getCloseQuestionFromRepository(4).setAnswerForThisQuestion(1, "13");
        questionRepo.getCloseQuestionFromRepository(4).setAnswerForThisQuestion(2, "66");
        questionRepo.getCloseQuestionFromRepository(4).setAnswerForThisQuestion(3, "100");
        questionRepo.getCloseQuestionFromRepository(4).setAnswerForThisQuestion(4, "80");
        questionRepo.getCloseQuestionFromRepository(4).setAnswerForThisQuestion(5, "35");
        questionRepo.getCloseQuestionFromRepository(4).setAnswerForThisQuestion(6, "65");
        questionRepo.getCloseQuestionFromRepository(4).setNumOfCorrectAnswers(1);
        questionRepo.getCloseQuestionFromRepository(4).getAnswersForThisQuestion()[3].setStatus(true);

        questionRepo.setCloseQuestionsInRepository(4);
        questionRepo.getCloseQuestionFromRepository(5).setQuestionTitle("How much is 22 + 22 ?");
        questionRepo.getCloseQuestionFromRepository(5).setDiffLevel(Question.eDifficultlyLevel.Hard);
        questionRepo.getCloseQuestionFromRepository(5).setAnswerForThisQuestion(1, "99");
        questionRepo.getCloseQuestionFromRepository(5).setAnswerForThisQuestion(2, "4");
        questionRepo.getCloseQuestionFromRepository(5).setAnswerForThisQuestion(3, "22");
        questionRepo.getCloseQuestionFromRepository(5).setAnswerForThisQuestion(4, "16");
        questionRepo.getCloseQuestionFromRepository(5).setAnswerForThisQuestion(5, "35");
        questionRepo.getCloseQuestionFromRepository(5).setAnswerForThisQuestion(6, "44");
        questionRepo.getCloseQuestionFromRepository(5).setNumOfCorrectAnswers(1);
        questionRepo.getCloseQuestionFromRepository(5).getAnswersForThisQuestion()[5].setStatus(true);

        questionRepo.setOpenQuestionsInRepository(5);
        questionRepo.getOpenQuestionFromRepository(6).setQuestionTitle("What is the color of the sun?");
        questionRepo.getOpenQuestionFromRepository(6).setDiffLevel(Question.eDifficultlyLevel.Medium);
        questionRepo.getOpenQuestionFromRepository(6).setQuestionAnswer("Yellow");
    }



}

