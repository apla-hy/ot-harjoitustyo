
package languagetrainer.domain;

import java.util.ArrayList;

/**
 * This class defines an individual task 
 */
public class Task implements Comparable<Task> {
    private Language questionLanguage;
    private Language answerLanguage;
    private WordType type;
    private WordTense tense;
    private String question;
    private ArrayList<String> answer;

    public Task(Language questionLanguage, Language answerLanguage, WordType type, WordTense tense, String question, ArrayList<String> answer) {
        this.questionLanguage = questionLanguage;
        this.answerLanguage = answerLanguage;
        this.type = type;
        this.tense = tense;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public Language getQuestionLanguage() {
        return questionLanguage;
    }

    public Language getAnswerLanguage() {
        return answerLanguage;
    }

    public WordType getType() {
        return type;
    }
    
    public WordTense getTense() {
        return tense;
    }

    @Override
    public int compareTo(Task task) {
        return this.answer.get(0).compareTo(task.getAnswer().get(0));
    }
    
}
