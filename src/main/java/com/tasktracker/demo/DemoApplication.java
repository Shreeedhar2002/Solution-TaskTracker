package com.tasktracker.demo;

import com.tasktracker.demo.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		TaskService service = new TaskService();
		if (args.length < 1) {
			printUsage();
			return;
		}
		switch (args[0]) {
			case "add":
				if (args.length < 2) printUsage();
				else service.addTask(String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length)));
				break;
			case "update":
				if (args.length < 3) printUsage();
				else service.updateTask(Integer.parseInt(args[1]), String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length)));
				break;
			case "delete":
				if (args.length < 2) printUsage();
				else service.deleteTask(Integer.parseInt(args[1]));
				break;
			case "progress":
				if (args.length < 2) printUsage();
				else service.changeStatus(Integer.parseInt(args[1]), "in-progress");
				break;
			case "done":
				if (args.length < 2) printUsage();
				else service.changeStatus(Integer.parseInt(args[1]), "done");
				break;
			case "list":
				service.listTasks(args.length > 1 ? args[1] : null);
				break;
			default:
				printUsage();
		}
	}

	private static void printUsage() {
		System.out.println("Usage:");
		System.out.println("tasktracker add <description>");
		System.out.println("tasktracker update <id> <description>");
		System.out.println("tasktracker delete <id>");
		System.out.println("tasktracker progress <id>");
		System.out.println("tasktracker done <id>");
		System.out.println("tasktracker list [all|done|notdone|in-progress]");
	}
	}
