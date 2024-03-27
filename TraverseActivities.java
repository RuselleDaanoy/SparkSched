package com.example;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TraverseActivities {
    private Map<String, List<Task>> graph;
    private Set<String> visitedNodes;

    public TraverseActivities() {
        graph = new HashMap<>();
        visitedNodes = new HashSet<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TraverseActivities taskTracker = new TraverseActivities();

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numTasks; i++) {
            System.out.print("Enter task name: ");
            String taskName = scanner.nextLine();
            System.out.print("Enter task date and time (YYYY-MM-DD HH:mm): ");
            String dateTime = scanner.nextLine();

            Task task = new Task(taskName, dateTime);
            taskTracker.addTask(taskName, task);
        }

        System.out.print("Enter the starting task name: ");
        String startTask = scanner.nextLine();

        System.out.println("Starting from task '" + startTask + "':");
        taskTracker.dfs(startTask);

        scanner.close();
    }

    public void addTask(String taskName, Task task) {
        if (!graph.containsKey(taskName)) {
            graph.put(taskName, new ArrayList<>());
        }

        List<Task> tasks = graph.get(taskName);
        tasks.add(task);

        for (Map.Entry<String, List<Task>> entry : graph.entrySet()) {
            String existingTaskName = entry.getKey();
            if (!existingTaskName.equals(taskName)) {
                List<Task> existingTasks = entry.getValue();
                existingTasks.sort(Comparator.comparing(Task::getDateTime));
                List<Task> updatedTasks = new ArrayList<>(existingTasks);
                updatedTasks.add(task);
                updatedTasks.sort(Comparator.comparing(Task::getDateTime));
                graph.put(existingTaskName, updatedTasks);
            }
        }
    }

public void dfs(String start) {
    Stack<String> stack = new Stack<>();
    stack.push(start);
    visitedNodes.add(start); 
    
    while (!stack.isEmpty()) {
        String currentTask = stack.pop();
        System.out.println("Visited task: " + currentTask);
    
        List<Task> neighbors = graph.getOrDefault(currentTask, Collections.emptyList());
        neighbors.sort(Comparator.comparing(Task::getDateTime).reversed());
        for (Task neighbor : neighbors) {
            if (!visitedNodes.contains(neighbor.getName())) {
                stack.push(neighbor.getName());
                visitedNodes.add(neighbor.getName());
            }
        }

        for (String task : graph.keySet()) {
            if (!visitedNodes.contains(task)) {
                stack.push(task);
                visitedNodes.add(task);
            }
        }
    }
}

    static class Task {
        private String name;
        private Date dateTime;

        public Task(String name, String dateTimeStr) {
            this.name = name;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                this.dateTime = dateFormat.parse(dateTimeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public Date getDateTime() {
            return dateTime;
        }
    }
}
