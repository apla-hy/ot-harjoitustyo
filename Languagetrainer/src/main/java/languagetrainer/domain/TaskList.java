
package languagetrainer.domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * This class creates and stores all available tasks based on the given input files  
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<String> dataFiles) {
        this.tasks = new ArrayList<>();
        for (String dataFile: dataFiles) {
            this.loadVerbsSpanish(dataFile);
        }
    }
   
    /**
     * This method reads an input file and creates tasks based on the data in the input file
     * 
     * @param sourceFile the input file
     * 
     */
    private void loadVerbsSpanish(String sourceFile) {
        
        try (Scanner fileReader = new Scanner(new File(sourceFile))) {
            
            // Read header row
            String[] parts = fileReader.nextLine().split(",");
            Language questionLanguage = Language.valueOf(parts[0].trim());
            Language answerLanguage = Language.valueOf(parts[1].trim());
            WordType type = WordType.valueOf(parts[2].trim());
            WordTense tense = WordTense.valueOf(parts[3].trim());
            
            // Read data rows
            while (fileReader.hasNextLine()) {
                parts = fileReader.nextLine().split(",");
                String question = parts[7].trim();
                ArrayList<String> answers = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    answers.add(parts[i].trim());
                }
                this.tasks.add(new Task(questionLanguage, answerLanguage, type, tense, question, answers));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    /**
     * This method returns possible question languages based on the available tasks
     *
     * @return an ArrayList of the languages
     */
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
    
     /**
     * This method returns the possible answer languages based on the available tasks
     *
     * @return an ArrayList of the languages
     */
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

    /**
     * This method returns the possible word types based on the available tasks
     *
     * @return an ArrayList of the word types
     */
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
    
    /**
     * This method returns the possible word tenses based on the available tasks
     *
     * @return an ArrayList of the word tenses
     */    
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
