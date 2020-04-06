/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import languagetrainer.domain.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lauri
 */
public class TaskTest {
    Task task1;
    
    public TaskTest() {
    }
    
    @Before
    public void setUp() {
        ArrayList<String> answers1 = new ArrayList<>();
        String answers[] = {"abrir","abro","abres","abre","abrimos","abrís","abren"};
        for (int i=0; i < answers.length; i++) {
            answers1.add(answers[i]);
        }
        task1 = new Task(Language.FINNISH, Language.SPANISH, WordType.VERB, WordTense.PRESENT, "avata", answers1);
    }
        
    @Test
    public void taskCreated() {
        assertTrue(task1!=null);      
    }
    
    @Test
    public void questionLanguageCorrect() {
        assertEquals(Language.FINNISH, task1.getQuestionLanguage());
    }
    
    @Test
    public void answerLanguageCorrect() {
        assertEquals(Language.SPANISH, task1.getAnswerLanguage());
    }
    
    @Test
    public void typeCorrect() {
        assertEquals(WordType.VERB, task1.getType());
    }
    
    @Test
    public void tenseCorrect() {
        assertEquals(WordTense.PRESENT, task1.getTense());
    }

    @Test
    public void questionCorrect() {
        assertEquals("avata", task1.getQuestion());
    }
    
    @Test
    public void firstAnswerCorrect() {
        assertEquals("abrir", task1.getAnswer().get(0));
    }

    @Test
    public void lastAnswerCorrect() {
        assertEquals("abren", task1.getAnswer().get(6));
    }
}
