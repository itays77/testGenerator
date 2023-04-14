import java.util.Arrays;

public class QuestionRepository {

    private Question[] questionsRepository = new Question[20];


    // constructors
    public QuestionRepository() {

    }

    //getters and setters

    public Question[] getQuestionsRepository() {
        return questionsRepository;
    }

    public void setQuestionsInRepository(int num) {
        this.questionsRepository[num] = new Question();
    }

    public Question getQuestionFromRepository(int qNum) {
        return questionsRepository[qNum - 1];
    }

    public Question getQuestion(int qNum) {
        return this.questionsRepository[qNum-1];
    }

    // methods


    public void addQuestionToRepository(String questionTitle, int numOfAnswers) {
        int index = indexOfFirstNull();

        if (numOfAnswers < 9) {
            //if the questionsRepository is full, copy the array to a double length;
            if (indexOfFirstNull() == questionsRepository.length) {
                questionsRepository = Arrays.copyOf(questionsRepository, questionsRepository.length * 2);
            }
            // calling constructor and create new Question Object in the first index that is "free"
            questionsRepository[index] = new Question(questionTitle, numOfAnswers);
        }
    }

    public void removeQuestionFromRepository(int qNum) {
        int index = qNum - 1;

        // check if the qNum is valid, and set it to null than shift the other question objects
        if (index >= 0 && index < indexOfLastQuestion()+1) {
            questionsRepository[index] = null;
            System.out.println("The question has deleted");

            for (int i = index; i < questionsRepository.length - 1; i++) {
                questionsRepository[i] = questionsRepository[i + 1];
            }
            questionsRepository[questionsRepository.length - 1] = null;

            for (int i = 0; i < questionsRepository.length; i++) {
                if (questionsRepository[i] != null) {
                    questionsRepository[i].setQuestionNumber(i + 1);
                }
            }
        }
        else {
            System.out.println("Wrong number has been selected, try again");
        }
    }

    // add answer that by user input by calling the method from the Question class
    public void addAnswerToExistingQuestion(String title, int qNum) {
        this.questionsRepository[qNum-1].addAnswerToQuestion(title);
    }

    // add answer to new question (part of the Adding new Question to repository only)
    public void addAnswerToNewQuestion(String ans, int i) {
        this.questionsRepository[indexOfLastQuestion()].getAnswersForThisQuestion()[i].setAnswerTitle(ans);
    }

    // finish the creation of new question by setting the answer that user want as "TRUE"
    public void setStatusForNewQuestion(int aNum) {
        this.questionsRepository[indexOfLastQuestion()].getAnswersForThisQuestion()[aNum-1].setStatus(true);
        System.out.println("New question has been successfully added to the repository");
    }

    // remove answer that user select by calling the method from the Question class
    public void removeAnswerForQuestion(int num1, int num2) {
        this.questionsRepository[num1-1].removeAnswerForQuestion(num2);
    }



    public int indexOfFirstNull() {    // return the first index in the repository that equal to null (no Question object)
        int index = 0;
        for (int i = 0; i < questionsRepository.length; i++) {
            if (questionsRepository[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int indexOfLastQuestion() {      // return the index in the repository of the last Question object
        int index = 0;
        for (int i = 0; i < questionsRepository.length; i++) {
            if (questionsRepository[i] == null) {
                index = i - 1;
                break;
            }
        }
        return index;
    }

    public void printRepository() {        // print the Questions objects that are in the repository, and for each question the questing number and related answers
        for (int i = 0; i < questionsRepository.length; i++) {
            if (questionsRepository[i] == null)
                break;
            else {
                System.out.println("Question " + questionsRepository[i].getQuestionNumber() + " => " +
                        questionsRepository[i].getQuestionTitle());
                questionsRepository[i].printAnswersForQuestion();
                System.out.println();
            }
        }
    }

    public void printRepositoryQuestions() {         // print ONLY print the Questions objects that are in the repository, NO ANSWERS.
        for(int i =0; i< questionsRepository.length; i++) {
            if(questionsRepository[i] == null) {
                break;
            }
            else {
                System.out.println("Question " +
                        questionsRepository[i].getQuestionNumber() + ". " +
                        questionsRepository[i].getQuestionTitle());
            }
        }
    }



}



