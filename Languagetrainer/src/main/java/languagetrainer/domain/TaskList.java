
package languagetrainer.domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * This class creates tasks based on the given input file and stores the tasks in a list   
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private String dataFile;

    public TaskList(String dataFile) {
        this.dataFile = dataFile;
        this.tasks = new ArrayList<>();
        this.load();
    }
   
    /**
     * This method reads the input file and creates tasks based on the data in the input file
     */
    private void load() {
        
        try (Scanner fileReader = new Scanner(new File(this.dataFile))) {
            
            // Read data rows
            while (fileReader.hasNextLine()) {
                String[] parts = fileReader.nextLine().split(",");
                Language questionLanguage = Language.valueOf(parts[0].trim());
                Language answerLanguage = Language.valueOf(parts[1].trim());
                WordType type = WordType.valueOf(parts[2].trim());
                WordTense tense = WordTense.valueOf(parts[3].trim());
                String question = parts[11].trim();
                ArrayList<String> answers = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    answers.add(parts[i+4].trim());
                }
                String notes = parts[12].trim();
                this.tasks.add(new Task(questionLanguage, answerLanguage, type, tense, question, answers, notes));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }        
    }
    
    public boolean save() {
        
        // Not implemented yet
        
        return false;
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
