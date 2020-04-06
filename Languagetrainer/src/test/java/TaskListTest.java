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
public class TaskListTest {
    
    TaskList taskList1;
    TaskList taskList2;
    
    public TaskListTest() {
    }
    
    @Before
    public void setUp() {
        ArrayList<String> files = new ArrayList<>();
        files.add("espanjanverbilista.csv");
        taskList1 = new TaskList(files);
        files.add("taskListForTestingOptions.csv");
        taskList2 = new TaskList(files);
    }
    
    @Test
    public void taskList1Created() {
        assertTrue(taskList1!=null);      
    }
    
    @Test
    public void taskList2Created() {
        assertTrue(taskList2!=null);      
    }

    @Test
    public void correctNumberOfTasksInList1() {
        assertEquals(105,taskList1.getTasks().size());      
    }

    @Test
    public void correctNumberOfTasksInList2() {
        assertEquals(106,taskList2.getTasks().size());      
    }
    
    @Test
    public void correctNumberOfQuestionLanguages() {
        assertEquals(2,taskList2.getQuestionLanguages().size());      
    }
    
    @Test
    public void correctNumberOfAnswerLanguages() {
        assertEquals(2,taskList2.getAnswerLanguages().size());      
    }
    
    @Test
    public void correctNumberOfWordTypes() {
        assertEquals(2,taskList2.getTypes().size());      
    }
    
    @Test
    public void correctNumberOfWordTenses() {
        assertEquals(2,taskList2.getTenses().size());      
    }
}
