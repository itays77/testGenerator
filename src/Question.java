
public abstract class Question {

    public enum eDifficultlyLevel {
        Easy, Medium, Hard
    };


    protected String questionTitle;
    protected static int QUESTION_NUMBER_COUNTER;
    private int questionNumber;
    protected eDifficultlyLevel diffLevel;





    public Question() {
        this.questionNumber = ++QUESTION_NUMBER_COUNTER;
        //this.questionTitle = q

    }



    public static int getQuestionNumberCounter() {
        return QUESTION_NUMBER_COUNTER;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getQuestionNumber() {
        return this.questionNumber = questionNumber;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setDiffLevel ( eDifficultlyLevel diffLevel) {
        this.diffLevel = diffLevel;
    }

    public String getDiffLevel() {
        return this.diffLevel.name();
    }


    //methods

    public abstract void setAnswerForThisQuestion(int ansNum, String title);

    public abstract void printAnswersForQuestion();



    //public abstract getAnswersForThisQuestion();




}
