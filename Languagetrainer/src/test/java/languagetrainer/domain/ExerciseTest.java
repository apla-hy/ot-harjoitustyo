/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.domain;

import java.util.ArrayList;
import languagetrainer.domain.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lauri
 */
public class ExerciseTest {
    
    Exercise exercise1;
    
    public ExerciseTest() {
    }
    
    
    @Before
    public void setUp() {
        ArrayList<String> files = new ArrayList<>();
        files.add("espanjanverbilista.csv");
        TaskList taskList1 = new TaskList(files);
        ArrayList<Task> tasks = taskList1.getTasks();
        ArrayList<WordType> types = new ArrayList<>();
        types.add(WordType.VERB);
        ArrayList<WordTense> tenses = new ArrayList<>();
        tenses.add(WordTense.PRESENT);
        this.exercise1 = new Exercise(tasks, Language.FINNISH, Language.SPANISH, types, tenses, 2, ExerciseOrder.ASCENDING);
    }
    
    
    @Test
    public void exercise1Created() {
        assertTrue(this.exercise1!=null);      
    }
    
    @Test
    public void correctQuestionLanguageInExercise1() {
        Task task1 = this.exercise1.getNextTask();
        assertEquals(Language.FINNISH,task1.getQuestionLanguage());      
    }
    
    @Test
    public void correctAnswerLanguageInExercise1() {
        Task task1 = this.exercise1.getNextTask();
        assertEquals(Language.SPANISH,task1.getAnswerLanguage());      
    }
    
    @Test
    public void correctTypeInExercise1() {
        Task task1 = this.exercise1.getNextTask();
        assertEquals(WordType.VERB,task1.getType());      
    }
    
    @Test
    public void correctTenseInExercise1() {
        Task task1 = this.exercise1.getNextTask();
        assertEquals(WordTense.PRESENT,task1.getTense());      
    }
    
    @Test
    public void correctNumberOfTasksInExercise1() {
        Task task1 = this.exercise1.getNextTask();
        Task task2 = this.exercise1.getNextTask();
        Task task3 = this.exercise1.getNextTask();
        assertTrue(task1!=null);
        assertTrue(task2!=null);
        assertTrue(task3==null);
    }
    
    @Test
    public void correctOrderInExercise1() {
        Task task1 = this.exercise1.getNextTask();
        assertEquals("avata",task1.getQuestion());      
    }

    
}
