
package languagetrainer.domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 * This class loads and saves tasks using the data file. Loaded tasks are stored in a list.   
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private String dataFile;

    public TaskList(String dataFile) {
        this.dataFile = dataFile;
        this.tasks = new ArrayList<>();
        boolean result = this.load();
        if (!result) {
            throw new IllegalArgumentException("Tiedoston " + this.dataFile + " lukeminen ei onnistunut");
        }
    }
   
    /**
     * This method reads the data file and creates tasks based on the data in the file
     * 
     * @return true if the reading of the file was successful, false if not 
     */
    private boolean load() {
        
        try (Scanner fileReader = new Scanner(new File(this.dataFile))) {
            
            // Read data rows
            while (fileReader.hasNextLine()) {
                String[] parts = fileReader.nextLine().split(";");
                ArrayList<String> answers = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    answers.add(parts[i + 4].trim());
                }
                boolean irregular = false;
                if (parts[13].trim().equals("1")) {
                    irregular = true;
                }
                this.tasks.add(new Task(Language.valueOf(parts[0].trim()), Language.valueOf(parts[1].trim()), WordType.valueOf(parts[2].trim()), WordTense.valueOf(parts[3].trim()), parts[11].trim(), answers, parts[12].trim(), irregular));
            }
            return true;
        } catch (Exception e) {
            return false;
        }        
    }
    
     /**
     * This method saves all tasks to the data file
     * 
     * @return true if the saving of the file was successful, false if not 
     */
    public boolean save() {
        
        try (FileWriter writer = new FileWriter(new File(this.dataFile))) {
            for (Task task : tasks) {
                String dataRow = task.getQuestionLanguage() + ";" + task.getAnswerLanguage() + ";" + task.getType() + ";" + task.getTense();
                ArrayList<String> answers = task.getAnswer();
                for (String answer: answers) {
                    dataRow = dataRow + ";" + answer;
                }
                dataRow = dataRow + ";" + task.getQuestion() + ";" + task.getNotes();
                String irregular = "0";
                if (task.isIrregular()) {
                    irregular = "1";
                }
                writer.write(dataRow + ";" + irregular + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
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
