/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.uitext;

import languagetrainer.domain.*;
import java.util.Scanner;

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
        
        Task task1 = new Task("Comer", "Syödä");
        
        System.out.println(task1.getQuestion());
        System.out.println(task1.getAnswer());
        
    }
    
}
