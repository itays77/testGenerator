import java.io.IOException;

public class ManualExam extends Test {


    public ManualExam(int numOfQuestions) {
        super(numOfQuestions);
    }

    @Override
    public void createExam(Question [] questions) throws IOException {
       createQuestionAndAnswersFile(questions);
       createSolutionFile(questions);
    }
}
