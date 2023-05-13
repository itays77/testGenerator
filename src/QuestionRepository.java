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

    public void setCloseQuestionsInRepository(int num) {
        this.questionsRepository[num] = new CloseQuestion();
    }



    public CloseQuestion getCloseQuestionFromRepository(int qNum) {
        return (CloseQuestion) questionsRepository[qNum - 1];
    }

    public void setOpenQuestionsInRepository(int num) {
        this.questionsRepository[num] = new OpenQuestion();
    }

    public OpenQuestion getOpenQuestionFromRepository(int qNum) {
        return (OpenQuestion) questionsRepository[qNum - 1];
    }



    // methods


    public void addCloseQuestionToRepository(String questionTitle, int numOfAnswers, Question.eDifficultlyLevel diffLevel) {
        int index = indexOfFirstNull();
            if (numOfAnswers < 9) {
                //if the questionsRepository is full, copy the array to a double length;
                if (indexOfFirstNull() == questionsRepository.length) {
                    questionsRepository = Arrays.copyOf(questionsRepository, questionsRepository.length * 2);
                }
                // calling constructor and create new Question Object in the first index that is "free"
                questionsRepository[index] = new CloseQuestion(questionTitle, numOfAnswers, diffLevel);
            }
    }

    public void addOpenQuestionToRepository(String questionTitle, String questionAnswer, Question.eDifficultlyLevel diffLevel) {
        int index = indexOfFirstNull();
        //if the questionsRepository is full, copy the array to a double length;
        if (indexOfFirstNull() == questionsRepository.length) {
            questionsRepository = Arrays.copyOf(questionsRepository, questionsRepository.length * 2);
        }
        // calling constructor and create new Question Object in the first index that is "free" ///////
        questionsRepository[index] = new OpenQuestion(questionTitle, questionAnswer, diffLevel);

    }




    public void removeQuestionFromRepository(int qNum) {
        int index = qNum - 1;

        // check if the qNum is valid, and set it to null than shift the other question objects
        if (index >= 0 && index < indexOfLastQuestion()+1) {
            questionsRepository[index] = null;

            for (int i = index; i < questionsRepository.length - 1; i++) {
                questionsRepository[i] = questionsRepository[i + 1];
            }
            questionsRepository[questionsRepository.length - 1] = null;

            for (int i = 0; i < questionsRepository.length; i++) {
                if (questionsRepository[i] != null) {
                    questionsRepository[i].setQuestionNumber(i + 1);
                }
            }
            Question.QUESTION_NUMBER_COUNTER--;
        }
    }

    // add answer that by user input by calling the method from the Question class
    public void addAnswerToExistingQuestion(String title, int qNum) {
        if (questionsRepository[qNum - 1] instanceof CloseQuestion) {
            ((CloseQuestion) questionsRepository[qNum - 1]).addAnswerToQuestion(title);
        }
    }

    // add answer to new question (part of the Adding new Question to repository only)
    public void addAnswerToNewQuestion(String ans, int i) {
        if (questionsRepository[indexOfLastQuestion()] instanceof CloseQuestion) {
            ((CloseQuestion)questionsRepository[indexOfLastQuestion()]).getAnswersForThisQuestion()[i].setAnswerTitle(ans);
        }
    }

    // finish the creation of new question by setting the answer that user want as "TRUE"
    public void setStatusForNewQuestion(int aNum) {
        if (questionsRepository[indexOfLastQuestion()] instanceof CloseQuestion) {
            ((CloseQuestion)questionsRepository[indexOfLastQuestion()]).getAnswersForThisQuestion()[aNum - 1].setStatus(true);
            System.out.println("New question has been successfully added to the repository");
        }
    }

    public void removeAnswerForQuestion(int num1, int num2) {
        //this.questionsRepository[num1-1].removeAnswerForQuestion(num2);
        if (questionsRepository[num1 - 1] instanceof CloseQuestion) {
            ((CloseQuestion) questionsRepository[num1 - 1]).removeAnswerForQuestion(num2);
        }
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
            else if(questionsRepository[i] instanceof CloseQuestion) {
                System.out.println("Question " + questionsRepository[i].getQuestionNumber() + ", **" + questionsRepository[i].getDiffLevel() +"**  => " +
                        questionsRepository[i].getQuestionTitle());
                questionsRepository[i].printAnswersForQuestion();
                System.out.println();
            }
            else if (questionsRepository[i] instanceof OpenQuestion){
                System.out.println("Question " + questionsRepository[i].getQuestionNumber() + ", **" + questionsRepository[i].getDiffLevel() +"** => "
                + questionsRepository[i].getQuestionTitle());
                System.out.println("The Answer is: " +  ((OpenQuestion) questionsRepository[i]).getQuestionAnswer());
                System.out.println();
            }
        }
    }

    public void printRepositoryQuestions() {         // print ONLY print the Open and Close Questions objects that are in the repository, NO ANSWERS.
        for(int i =0; i< questionsRepository.length; i++) {
            if(questionsRepository[i] == null) {
                break;
            }
            else if(questionsRepository[i] instanceof CloseQuestion || questionsRepository[i] instanceof OpenQuestion) {
                System.out.println("Question" + questionsRepository[i].getQuestionNumber()
                + ". " + questionsRepository[i].getQuestionTitle() + " (" + questionsRepository[i].getClass().getSimpleName() + ")");
            }
        }
    }

    public void printCloseRepositoryQuestions() {         // print ONLY print the Open and Close Questions objects that are in the repository, NO ANSWERS.
        for(int i =0; i< questionsRepository.length; i++) {
            if(questionsRepository[i] == null) {
                break;
            }
            else if(questionsRepository[i] instanceof CloseQuestion) {
                System.out.println("Question" + questionsRepository[i].getQuestionNumber()
                        + ". " + questionsRepository[i].getQuestionTitle() + " (" + questionsRepository[i].getClass().getSimpleName() + ")");
            }
        }
    }
    public void printOpenRepositoryQuestions() {         // print ONLY print the Open and Close Questions objects that are in the repository, NO ANSWERS.
        for(int i =0; i< questionsRepository.length; i++) {
            if(questionsRepository[i] == null) {
                break;
            }
            else if(questionsRepository[i] instanceof OpenQuestion) {
                System.out.println("Question" + questionsRepository[i].getQuestionNumber()
                        + ". " + questionsRepository[i].getQuestionTitle() + " (" + questionsRepository[i].getClass().getSimpleName() + ")");
            }
        }
    }




}



