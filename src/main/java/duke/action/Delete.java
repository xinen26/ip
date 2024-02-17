package duke.action;

import duke.exception.*;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an action to delete tasks.
 */
public class Delete implements Action {
    private int[] indices;
    private List<Task> deletedTasks;
    private TaskList tasks;
    private static final int DELETE_START_INDEX = 7;

    /**
     * Constructs a Delete action with the specified indices and task list.
     *
     * @param indices The indices of the tasks to be deleted.
     * @param tasks   The task list from which the tasks will be deleted.
     * @throws WrongIndexException If any index is invalid.
     */
    public Delete(int[] indices, TaskList tasks) throws WrongIndexException {
        this.indices = indices;
        this.deletedTasks = new ArrayList<>();
        for (int index : indices) {
            if (!tasks.validateIndex(index)) {
                throw new WrongIndexException();
            }
            this.deletedTasks.add(tasks.get(index)); // Retrieve the tasks before deletion
        }
        this.tasks = tasks;
        Arrays.sort(indices);
        deleteTasks();
    }

    public static Delete parse(String command, TaskList taskList) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String[] indicesString = command.substring(DELETE_START_INDEX).trim().split(" ");
            if (indicesString.length > 0) {
                int[] indices = Action.parseIndices(indicesString);
                Action.checkForDuplicateIndices(indices);
                return new Delete(indices, taskList);
            } else {
                throw new NoIndexException();
            }
        } else {
            throw new MissingIndexException();
        }
    }



    /**
     * Deletes the tasks specified by the indices.
     */
    private void deleteTasks() {
        for (int i = indices.length - 1; i >= 0; i--) {
            tasks.deleteTask(indices[i]);
        }
    }

    /**
     * Gets the response message indicating the successful deletion of tasks.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        StringBuilder response = new StringBuilder("Noted. I've removed the following tasks:\n");
        for (Task deletedTask : deletedTasks) {
            response.append(deletedTask.toString()).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString();
    }
}



