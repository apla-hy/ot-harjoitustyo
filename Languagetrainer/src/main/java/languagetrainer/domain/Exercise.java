
package languagetrainer.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class creates an exercise based on the given options  
 */

public class Exercise {
    private ArrayList<Task> tasks;
    private Language questionLanguage;
    private Language answerLanguage;
    private ArrayList<WordType> types;
    private ArrayList<WordTense> tenses;
    private int numberOfTasks;
    private ExerciseOrder order;
    private String startChar;
    private ArrayList<Task> exerciseTasks;
    private int currentTask;

    public Exercise(ArrayList<Task> tasks, Language questionLanguage, Language answerLanguage, ArrayList<WordType> types, ArrayList<WordTense> tenses, int numberOfTasks, ExerciseOrder order, String startChar) {
        this.tasks = tasks;
        this.questionLanguage = questionLanguage;
        this.answerLanguage = answerLanguage;
        this.types = types;
        this.tenses = tenses;
        this.numberOfTasks = numberOfTasks;
        this.order = order;
        this.startChar = startChar;
        this.exerciseTasks = new ArrayList<>();
        this.currentTask = -1;
        this.createExercise();
    }
    
    private void createExercise() {
        
        // Apply the selected order for tasks
        if (this.order == ExerciseOrder.ASCENDING) {
            Collections.sort(tasks);
        } else if (this.order == ExerciseOrder.DESCENDING) {
            Collections.sort(tasks, Collections.reverseOrder());
        } else if (this.order == ExerciseOrder.RANDOM) {
            Collections.shuffle(tasks);
        }
        
        // Add tasks for the exercise based on the options
        for (Task task: tasks) {
            if (this.exerciseTasks.size() < this.numberOfTasks) {
                if (task.getQuestionLanguage().equals(this.questionLanguage) &&
                    task.getAnswerLanguage().equals(this.answerLanguage) &&
                    this.types.contains(task.getType()) &&
                    this.tenses.contains(task.getTense()) &&
                    task.getAnswer().get(0).compareTo(this.startChar) > 0) {
                    this.exerciseTasks.add(task);
                }
            }
        }        
    }
    
    /**
     * This method returns the next task of the exercise
     * 
     * @return next task of the exercise or null if there is not a next task
     */
    
    public Task getNextTask() {
        if (this.currentTask + 1 >= this.exerciseTasks.size()) {
            return null;        
        }
        this.currentTask++;
        return this.exerciseTasks.get(this.currentTask);
    }
    
}
