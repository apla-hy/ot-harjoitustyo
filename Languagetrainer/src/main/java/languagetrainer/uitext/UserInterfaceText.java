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
        
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTasks();
        
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
            String row = reader.nextLine().trim();
            
            // Stop if input is "q"
            if (row.equals("q")) {
                break;
            }
            
        }   
        
        
    }
        
}
    
