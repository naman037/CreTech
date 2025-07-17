import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class ToDoListApp {
    private JFrame frame;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;

    public ToDoListApp() {
        frame = new JFrame("To-Do List App");
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");
        JButton deleteButton = new JButton("Delete Task");

        addButton.addActionListener(e -> addTask());
        completeButton.addActionListener(e -> markCompleted());
        deleteButton.addActionListener(e -> deleteTask());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);

        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addTask() {
        String title = JOptionPane.showInputDialog(frame, "Enter task title:");
        String description = JOptionPane.showInputDialog(frame, "Enter task description:");
        String dateInput = JOptionPane.showInputDialog(frame, "Enter due date (YYYY-MM-DD):");

        if (title != null && !title.isEmpty() && dateInput != null) {
            try {
                LocalDate dueDate = LocalDate.parse(dateInput);
                Task task = new Task(title, description, dueDate);
                taskListModel.addElement(task);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Invalid date format!");
            }
        }
    }

    private void markCompleted() {
        Task selected = taskList.getSelectedValue();
        if (selected != null && !selected.isCompleted()) {
            selected.markCompleted();
            taskList.repaint();
        }
    }

    private void deleteTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            taskListModel.removeElement(selected);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListApp::new);
    }
}
