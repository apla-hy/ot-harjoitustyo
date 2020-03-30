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
public class Task {
    private String Question;
    private String Answer;

    public Task(String Question, String Answer) {
        this.Question = Question;
        this.Answer = Answer;
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnswer() {
        return Answer;
    }
    
    
    
    
}
