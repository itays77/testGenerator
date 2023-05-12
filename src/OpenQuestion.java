public class OpenQuestion extends Question{

    //varibles
    String questionAnswer;

    public OpenQuestion(){
        super();

    }

    public OpenQuestion(String questionTitle, String questionAnswer, eDifficultlyLevel diffLevel) {
        super();
        this.setQuestionTitle(questionTitle);
        this.setQuestionAnswer(questionAnswer);
        this.diffLevel = diffLevel;


    }

    //getset

    public void setQuestionAnswer(String title) {
        this.questionAnswer = title;
    }

    public String getQuestionAnswer() {
       return this.questionAnswer;
    }



    @Override
    public void setAnswerForThisQuestion(int ansNum, String title) {
        this.questionAnswer = title;
    }

    //methods

    @Override
    public void printAnswersForQuestion() {
        System.out.println(questionAnswer);
    }
}
