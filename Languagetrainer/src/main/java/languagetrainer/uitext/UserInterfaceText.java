/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.uitext;

import languagetrainer.domain.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lauri
 */

public class UserInterfaceText {
    
    private Scanner reader;

    public UserInterfaceText(Scanner reader) {
        this.reader = reader;
    }
        
    
    public void start() {
        
        // Create task list
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTasks();
        
        // Find out what options are available
        ArrayList<Language> questionLanguages = taskList.getQuestionLanguages();
        ArrayList<Language> answerLanguages = taskList.getAnswerLanguages();
        ArrayList<WordType> types = taskList.getTypes();
        ArrayList<WordTense> tenses = taskList.getTenses();
        
        // Ask exercise options
        
        // Question language
        System.out.println("Valitse kysymyksen kieli. Vaihtoehdot:");
        for (int i=0; i < questionLanguages.size(); i++) {
            System.out.println(i+1 + ": " + questionLanguages.get(i));
        }
        Language selectedQuestionLanguage = questionLanguages.get(Integer.valueOf(reader.nextLine())-1);
        
        // Answer language
        System.out.println("Valitse vastauksen kieli. Vaihtoehdot:");
        for (int i=0; i < answerLanguages.size(); i++) {
            System.out.println(i+1 + ": " + answerLanguages.get(i));
        }
        Language selectedAnswerLanguage = answerLanguages.get(Integer.valueOf(reader.nextLine())-1);

        // Word types
        System.out.println("Valitse sanaluokat pilkulla erotettuna. Vaihtoehdot:");
        for (int i=0; i < types.size(); i++) {
            System.out.println(i+1 + ": " + types.get(i));
        }
        String row = reader.nextLine();
        String parts[] = row.split(",");
        ArrayList<WordType> selectedTypes = new ArrayList<>();
        for (String part: parts) {
            WordType type = types.get(Integer.valueOf(part)-1);
            selectedTypes.add(type);
        }
        
        // Tenses (if verbs selected)
        ArrayList<WordTense> selectedTenses = new ArrayList<>();
        if (selectedTypes.contains(WordType.VERB)) {
            System.out.println("Valitse aikamuodot pilkulla erotettuna. Vaihtoehdot:");
            for (int i=0; i < tenses.size(); i++) {
                System.out.println(i+1 + ": " + tenses.get(i));
            }
            row = reader.nextLine();
            parts = row.split(",");
            for (String part: parts) {
                WordTense tense = tenses.get(Integer.valueOf(part)-1);
                selectedTenses.add(tense);
            }
        }
        
        // Number of questions
        System.out.println("Kuinka monta kysymystä?");
        row = reader.nextLine();
        int selectedNumberOfQuestions = Integer.valueOf(row);
        
        // Check selection results        
        System.out.println(selectedQuestionLanguage);
        System.out.println(selectedAnswerLanguage);
        System.out.println(selectedTypes);
        System.out.println(selectedTenses);
        System.out.println(selectedNumberOfQuestions);
        
        // Create the exercise with selected options
        
        Exercise exercise = new Exercise(selectedQuestionLanguage, selectedAnswerLanguage, selectedTypes, selectedTenses, selectedNumberOfQuestions);
        
        System.exit(0);
        
        
        // Ask questions in random order
                
        while (true) {
            
            Random rng = new Random();
            int questionNumber = rng.nextInt(tasks.size());
            
            String questions[] = {"Perusmuoto"," Minä", "Sinä", "Hän", "Me", "Te", "He"};
            String answers[] = new String[7];
            
            System.out.println("Mitä on " + tasks.get(questionNumber).getQuestion() + " espanjaksi?");
            for (int i=0; i < questions.length; i++) {
                System.out.println(questions[i] + ": ");
                answers[i] = reader.nextLine().trim();
            }

            System.out.println("Vastaukset:");
            for (int i=0; i < questions.length; i++) {
                System.out.println("Vastauksesi: " + answers[i] + ", Oikea vastaus: " + tasks.get(questionNumber).getAnswer().get(i));
            }
            
            System.out.println("Jatketaanko? Lopetus painamalla q");
            row = reader.nextLine().trim();
            
            // Stop if input is "q"
            if (row.equals("q")) {
                break;
            }
            
        }   
        
        
    }
        
}
    
