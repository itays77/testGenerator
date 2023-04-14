public class Answer {

    // member variables
    private String answerTitle;
    private int answerNumber;
    private boolean status;

    // constructors
    public Answer () {

    }

    //getters and setters

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public boolean getAnswerStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // methods


    @Override
    public String toString() {
        return "Answer{" +
                "answerTitle='" + answerTitle + '\'' +
                ", answerNumber=" + answerNumber +
                ", status=" + status +
                '}';
    }
}
