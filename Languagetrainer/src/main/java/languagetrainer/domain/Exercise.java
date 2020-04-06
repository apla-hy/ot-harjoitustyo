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


public class Exercise {
    private Language questionLanguage;
    private Language answerLanguage;
    private ArrayList<WordType> types;
    private ArrayList<WordTense> tense;
    private int numberOfTasks;

    public Exercise(Language questionLanguage, Language answerLanguage, ArrayList<WordType> types, ArrayList<WordTense> tense, int numberOfTasks) {
        this.questionLanguage = questionLanguage;
        this.answerLanguage = answerLanguage;
        this.types = types;
        this.tense = tense;
        this.numberOfTasks = numberOfTasks;
    }
    
    
    
}
