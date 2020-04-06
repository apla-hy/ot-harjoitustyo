/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.domain;

/**
 *
 * @author lauri
 */
import java.util.ArrayList;

public class Task {
    private Language questionLanguage;
    private Language answerLanguage;
    private WordType type;
    private WordTense tense;
    private String question;
    private ArrayList<String> answer;

    public Task(Language questionLanguage, Language answerLanguage, WordType type,WordTense tense, String question, ArrayList<String> answer) {
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

    
    
}
