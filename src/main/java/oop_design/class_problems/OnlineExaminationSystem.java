package abstraction.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Question {

    protected String questionText;
    protected int marks;

    public Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);

    public int getMarks() {
        return marks;
    }
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(
            String questionText,
            String correctAnswer,
            int marks) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
            String questionText,
            boolean correctAnswer,
            int marks) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    public ShortAnswerQuestion(
            String questionText,
            String correctAnswer,
            int marks) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Attempt {

    private Question question;
    private String answer;
    private boolean evaluated;

    public Attempt(Question question) {
        this.question = question;
        this.evaluated = false;
    }

    public void recordAnswer(String answer) {

        if (evaluated) {
            System.out.println(
                    "Cannot change answer after evaluation."
            );
            return;
        }

        this.answer = answer;

        System.out.println(
                "Answer recorded."
        );
    }

    public int evaluate() {

        evaluated = true;

        if (question.evaluate(answer)) {
            System.out.println(
                    "Correct (" + question.getMarks()
                            + " points)"
            );

            return question.getMarks();
        }

        System.out.println(
                "Incorrect (0 points)"
        );

        return 0;
    }
}

class Examination {

    private String examName;
    private List<Attempt> attempts;
    private boolean submitted;

    public Examination(String examName) {
        this.examName = examName;
        this.attempts = new ArrayList<>();
        this.submitted = false;
    }

    public void start() {

        System.out.println(
                "Exam started: " + examName
        );
    }

    public Attempt addQuestion(Question question) {

        if (submitted) {
            System.out.println(
                    "Cannot add question after submission."
            );
            return null;
        }

        Attempt attempt =
                new Attempt(question);

        attempts.add(attempt);

        return attempt;
    }

    public void submit() {

        if (submitted) {
            System.out.println(
                    "Exam already submitted."
            );
            return;
        }

        submitted = true;

        int totalScore = 0;

        System.out.println(
                "Exam submitted by Student 1."
        );

        for (Attempt attempt : attempts) {
            totalScore += attempt.evaluate();
        }

        System.out.println(
                "Total score: " + totalScore
        );
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Examination exam =
                new Examination("Exam A");

        exam.start();

        Question q1 =
                new MultipleChoiceQuestion(
                        "Which language is used for Android?",
                        "C",
                        5
                );

        Question q2 =
                new TrueFalseQuestion(
                        "Java supports inheritance.",
                        true,
                        5
                );

        Attempt attempt1 =
                exam.addQuestion(q1);

        Attempt attempt2 =
                exam.addQuestion(q2);

        attempt1.recordAnswer("C");
        attempt2.recordAnswer("false");

        exam.submit();

        // Attempt to change answer
        attempt1.recordAnswer("B");
    }
}