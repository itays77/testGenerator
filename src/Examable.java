import java.io.IOException;

public interface Examable {

    void createExam(Question[] questions) throws IOException;
}
