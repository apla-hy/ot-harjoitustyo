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


public class Exercise {
    private ArrayList<Task> tasks;
    private Language questionLanguage;
    private Language answerLanguage;
    private ArrayList<WordType> types;
    private ArrayList<WordTense> tenses;
    private int numberOfTasks;
    private ArrayList<Task> exerciseTasks;
    private int currentTask;

    public Exercise(ArrayList<Task> tasks, Language questionLanguage, Language answerLanguage, ArrayList<WordType> types, ArrayList<WordTense> tenses, int numberOfTasks) {
        this.tasks = tasks;
        this.questionLanguage = questionLanguage;
        this.answerLanguage = answerLanguage;
        this.types = types;
        this.tenses = tenses;
        this.numberOfTasks = numberOfTasks;
        this.exerciseTasks = new ArrayList<>();
        this.currentTask = 1;
        this.createExercise();
    }
    
    private void createExercise() {
        for (Task task: tasks) {
            if (task.getQuestionLanguage().equals(this.questionLanguage) &&
                    task.getAnswerLanguage().equals(this.answerLanguage) &&
                    this.types.contains(task.getType()) &&
                    this.tenses.contains(task.getTense())) {
                if (this.exerciseTasks.size() < this.numberOfTasks) {
                    this.exerciseTasks.add(task);
                }
            }
        }
    }

    public Task getNextTask() {
        if (this.currentTask > this.exerciseTasks.size()) {
            return null;        
        }
        this.currentTask++;
        return this.exerciseTasks.get(this.currentTask-2);
    }
    
}
