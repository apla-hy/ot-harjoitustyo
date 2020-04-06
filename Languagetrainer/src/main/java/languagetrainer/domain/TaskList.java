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

    public TaskList(ArrayList<String> dataFiles) {
        this.tasks = new ArrayList<>();
        for (String dataFile: dataFiles) {
            this.LoadVerbsSpanish(dataFile);
        }
    }
    
    private void LoadVerbsSpanish(String sourceFile) {
        
        try (Scanner fileReader = new Scanner(new File(sourceFile))) {
            
            // Read header row
            String row = fileReader.nextLine();
            String[] parts = row.split(",");
            Language questionLanguage = Language.valueOf(parts[0].trim());
            Language answerLanguage = Language.valueOf(parts[1].trim());
            WordType type = WordType.valueOf(parts[2].trim());
            WordTense tense = WordTense.valueOf(parts[3].trim());
            
            // Read data rows
            while (fileReader.hasNextLine()) {
                row = fileReader.nextLine();
                parts = row.split(",");
                String question = parts[7].trim();
                ArrayList<String> answers = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    answers.add(parts[i].trim());
                }
                Task taskNew = new Task(questionLanguage, answerLanguage, type, tense, question, answers);
                this.tasks.add(taskNew);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    
    // Return possible question languages based on available tasks
    public ArrayList<Language> getQuestionLanguages() {
        ArrayList<Language> questionLanguages = new ArrayList<>();
        for (Task task: this.tasks) {
            Language questionLanguage = task.getQuestionLanguage();
            if (!questionLanguages.contains(questionLanguage)) {
                questionLanguages.add(questionLanguage);
            }
        }
        return questionLanguages;
    }
    
    // Return possible answer languages based on available tasks
    public ArrayList<Language> getAnswerLanguages() {
        ArrayList<Language> answerLanguages = new ArrayList<>();
        for (Task task: this.tasks) {
            Language answerLanguage = task.getAnswerLanguage();
            if (!answerLanguages.contains(answerLanguage)) {
                answerLanguages.add(answerLanguage);
            }
        }
        return answerLanguages;
    }

    // Return possible word types based on available tasks
    public ArrayList<WordType> getTypes() {
        ArrayList<WordType> types = new ArrayList<>();
        for (Task task: this.tasks) {
            WordType type = task.getType();
            if (!types.contains(type)) {
                types.add(type);
            }
        }
        return types;
    }
    
    // Return possible word tenses based on available tasks
    public ArrayList<WordTense> getTenses() {
        ArrayList<WordTense> tenses = new ArrayList<>();
        for (Task task: this.tasks) {
            WordTense tense = task.getTense();
            if (!tenses.contains(tense)) {
                tenses.add(tense);
            }
        }
        return tenses;
    }
}
