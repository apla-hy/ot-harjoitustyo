/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.uitext;

/**
 *
 * @author lauri
 */

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        UserInterfaceText uiText = new UserInterfaceText(reader);

        uiText.start();
    }
    
    
}
