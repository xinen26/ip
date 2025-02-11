package maltese.action;

import java.util.ArrayList;
import java.util.Iterator;

import maltese.task.Task;


/**
 * Represents a list of tasks in the maltese application.
 */

public class TaskList extends ArrayList<Task> implements Action, Iterable<Task> {

    /**
     * The list containing tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index or null if the index is invalid.
     */
    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        } else {
            return null;
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        if (validateIndex(index)) {
            Task taskToMark = tasks.get(index);
            taskToMark.mark();
        }
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        if (validateIndex(index)) {
            Task taskToUnmark = tasks.get(index);
            taskToUnmark.unmark();
        }
    }


    /**
     * Validates the given index.
     *
     * @param index The index to be validated.
     * @return True if the index is valid, false otherwise.
     */
    public boolean validateIndex(int index) {
        if (index >= 0 && index < tasks.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Finds the matches.
     */
    public void matches(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
    }
    /**
     * Parses the command for the "list" action.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     * @return An Action object representing the "list" action.
     *         If the task list is initialized, returns the TaskList itself,
     *         otherwise, returns an Echo action with an error message.
     */
    public static Action parse(TaskList taskList) { // hmm might think of another way
        if (taskList != null) {
            return taskList;
        } else {
            return new Echo("Task list is not initialized.");
        }
    }

    /**
     * Provides an iterator over the tasks in the list.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Checks if the TaskList contains a specific task.
     *
     * @param task The task to be checked for existence in the TaskList.
     * @return {@code true} if the TaskList contains the task, {@code false} otherwise.
     */
    public boolean contains(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the response message containing the list of tasks.
     *
     * @return A string representing the response message.
     */
    public String getResponse() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append("  ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

}









