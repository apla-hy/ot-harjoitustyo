
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
    private String notes;
    private boolean irregular;

    public Task(Language questionLanguage, Language answerLanguage, WordType type, WordTense tense, String question, ArrayList<String> answer, String notes, boolean irregular) {
        this.questionLanguage = questionLanguage;
        this.answerLanguage = answerLanguage;
        this.type = type;
        this.tense = tense;
        this.question = question;
        this.answer = answer;
        this.notes = notes;
        this.irregular = irregular;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public boolean isIrregular() {
        return irregular;
    }
    
    @Override
    public int compareTo(Task task) {
        return this.answer.get(0).compareTo(task.getAnswer().get(0));
    }
    
}
