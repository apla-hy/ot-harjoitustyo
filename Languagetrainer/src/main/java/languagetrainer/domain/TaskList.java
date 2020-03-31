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
import java.util.Scanner;
import java.io.File;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.LoadVerbsSpanish("espanjanverbilista.csv");
    }
    
    private void LoadVerbsSpanish(String sourceFile) {
        
        try (Scanner fileReader = new Scanner(new File(sourceFile))) {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] parts = row.split(",");
                String question = parts[7].trim();
                ArrayList<String> answers = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    answers.add(parts[i].trim());
                }
                Task taskNew = new Task(Language.FINNISH, Language.SPANISH, WordType.VERB, WordTense.PRESENT, question, answers);
                this.tasks.add(taskNew);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
}
