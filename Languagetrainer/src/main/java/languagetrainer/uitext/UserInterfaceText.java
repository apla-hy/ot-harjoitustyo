
package languagetrainer.uitext;

import languagetrainer.domain.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for text user interface logic. This class is not used in the current version of the application.
 */

public class UserInterfaceText {
    
    private Scanner reader;

    public UserInterfaceText(Scanner reader) {
        this.reader = reader;
    }
        
    public void start() {
        
        // Create task list
        ArrayList<String> files = new ArrayList<>();
        files.add("espanjanverbilista.csv");
        TaskList taskList = new TaskList(files);
        ArrayList<Task> tasks = taskList.getTasks();
        
        // Find out / specify what options are available
        ArrayList<Language> questionLanguages = taskList.getQuestionLanguages();
        ArrayList<Language> answerLanguages = taskList.getAnswerLanguages();
        ArrayList<WordType> types = taskList.getTypes();
        ArrayList<WordTense> tenses = taskList.getTenses();
        ExerciseOrder order[] = { ExerciseOrder.ASCENDING, ExerciseOrder.DESCENDING, ExerciseOrder.RANDOM };
        
        // Ask exercise options
        System.out.println("Luodaan uusi harjoitus.");
        
        // Question language
        System.out.println("Valitse kysymyksien kieli. Vaihtoehdot:");
        for (int i = 0; i < questionLanguages.size(); i++) {
            System.out.println(i + 1 + ": " + questionLanguages.get(i));
        }
        Language selectedQuestionLanguage = questionLanguages.get(Integer.valueOf(reader.nextLine()) - 1);
        
        // Answer language
        System.out.println("Valitse vastauksien kieli. Vaihtoehdot:");
        for (int i = 0; i < answerLanguages.size(); i++) {
            System.out.println(i + 1 + ": " + answerLanguages.get(i));
        }
        Language selectedAnswerLanguage = answerLanguages.get(Integer.valueOf(reader.nextLine()) - 1);

        // Word types
        System.out.println("Valitse sanaluokat pilkulla erotettuna. Vaihtoehdot:");
        for (int i = 0; i < types.size(); i++) {
            System.out.println(i + 1 + ": " + types.get(i));
        }
        String row = reader.nextLine();
        String parts[] = row.split(",");
        ArrayList<WordType> selectedTypes = new ArrayList<>();
        for (String part: parts) {
            WordType type = types.get(Integer.valueOf(part) - 1);
            selectedTypes.add(type);
        }
        
        // Tenses (if verbs selected)
        ArrayList<WordTense> selectedTenses = new ArrayList<>();
        if (selectedTypes.contains(WordType.VERB)) {
            System.out.println("Valitse aikamuodot pilkulla erotettuna. Vaihtoehdot:");
            for (int i = 0; i < tenses.size(); i++) {
                System.out.println(i + 1 + ": " + tenses.get(i));
            }
            row = reader.nextLine();
            parts = row.split(",");
            for (String part: parts) {
                WordTense tense = tenses.get(Integer.valueOf(part) - 1);
                selectedTenses.add(tense);
            }
        }
        
        // Number of questions
        System.out.println("Kuinka monta tehtävää?");
        row = reader.nextLine();
        int selectedNumberOfQuestions = Integer.valueOf(row);
        
        // Order of questions
        System.out.println("Valitse kysymysten järjestys. Vaihtoehdot:");
        for (int i = 0; i < order.length; i++) {
            System.out.println(i + 1 + ": " + order[i]);
        }
        row = reader.nextLine();
        int index = Integer.valueOf(row);
        ExerciseOrder selectedOrder = order[index - 1];
        
        // Create the exercise with selected options
        Exercise exercise = new Exercise(tasks, selectedQuestionLanguage, selectedAnswerLanguage, selectedTypes, selectedTenses, selectedNumberOfQuestions, selectedOrder);
        
        // Ask exercise questions

        Task currentTask = exercise.getNextTask();
        
        while (currentTask != null) {
            
            //Random rng = new Random();
            //int questionNumber = rng.nextInt(tasks.size());
            
            String questions[] = {"Perusmuoto", "Minä", "Sinä", "Hän", "Me", "Te", "He"};
            String answers[] = new String[7];
            
            System.out.println("Mitä on " + currentTask.getQuestion() + " espanjaksi?");
            for (int i = 0; i < questions.length; i++) {
                System.out.println(questions[i] + ": ");
                answers[i] = reader.nextLine().trim();
            }

            System.out.println("Vastaukset:");
            for (int i = 0; i < questions.length; i++) {
                System.out.println("Vastauksesi: " + answers[i] + ", Oikea vastaus: " + currentTask.getAnswer().get(i));
            }
            
            System.out.println("Jatketaanko? Lopetus painamalla q");
            row = reader.nextLine().trim();
            
            // Stop if input is "q"
            if (row.equals("q")) {
                break;
            } else {
                currentTask = exercise.getNextTask();
            }    
        }   
    }
}
    
