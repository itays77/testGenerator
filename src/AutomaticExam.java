import java.io.IOException;
import java.util.Random;

public class AutomaticExam extends Test{


    public AutomaticExam(int numOfQuestions) {
        super(numOfQuestions);
    }

    public void drillAndAddQuestions(int numOfQuestions, QuestionRepository q) {

       for(int i = 0; i< numOfQuestions; i++) {
           int r = getRandomNumber(q.indexOfLastQuestion());
           if(q.getQuestionsRepository()[r] instanceof CloseQuestion) {
               testQuestions[i] = new CloseQuestion();
               testQuestions[i].setQuestionNumber(i + 1);
               testQuestions[i].setDiffLevel(q.getQuestionsRepository()[r].diffLevel);
               ((CloseQuestion) testQuestions[i]).setQuestionTitle(q.getQuestionsRepository()[r].getQuestionTitle());

               //create new ans for this question
               for (int j = 0; j < 4; j++) {
                   ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j] = new Answer();
                   ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setAnswerNumber(j + 1);
                   drillAnswers(q, r, i, j);
               }


           }
           if(q.getQuestionsRepository()[r] instanceof OpenQuestion) {
               testQuestions[i] = new OpenQuestion();
               addOpenQuestionForThisTest(i, r, q);
           }
       }
    }

    public void drillAnswers (QuestionRepository q, int r, int i, int j) {
        int numOfAns = q.getCloseQuestionFromRepository(r+1).indexOfFirstNull();
        int drillNum = getRandomNumber(numOfAns);
        boolean trueExist = false;

        if(!q.getCloseQuestionFromRepository(r+1).getAnswersForThisQuestion()[drillNum].getAnswerStatus()) {
            ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setAnswerTitle(q.getCloseQuestionFromRepository(r+1).getAnswersForThisQuestion()[drillNum].getAnswerTitle());
            ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setStatus(false);
        }
        else if((q.getCloseQuestionFromRepository(r+1).getAnswersForThisQuestion()[drillNum].getAnswerStatus()) && trueExist == false) {
            ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setAnswerTitle(q.getCloseQuestionFromRepository(r+1).getAnswersForThisQuestion()[drillNum].getAnswerTitle());
            ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setStatus(true);
            trueExist = true;
        }
        else {
            int newNum = getRandomNumber(numOfAns);
            if (!q.getCloseQuestionFromRepository(r).getAnswersForThisQuestion()[newNum].getAnswerStatus()) {
                ((CloseQuestion) testQuestions[i]).getAnswersForThisQuestion()[j].setAnswerTitle(q.getCloseQuestionFromRepository(r+1).getAnswersForThisQuestion()[drillNum].getAnswerTitle());
            }
        }
    }






    public int getRandomNumber(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

    public boolean closeQuestionAnsNum(CloseQuestion question) {
        return question.indexOfFirstNull() < 3;
    }



    @Override
    public void createExam(Question[] questions) throws IOException {
        createQuestionAndAnswersFile(questions);
        createSolutionFile(questions);
    }
}
