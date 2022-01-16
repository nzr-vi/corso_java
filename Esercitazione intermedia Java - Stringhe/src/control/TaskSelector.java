package control;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.*;

public class TaskSelector {

	private class ExerciseTaskInfo{
		Exercise task;
		float avgExecutionTime;
		int numberOfExecution;
		
		float getAvgSec() {
			return this.avgExecutionTime/10e8f;
		}
		
		ExerciseTaskInfo(Exercise taskToTrack) throws ExerciseException {
			if(taskToTrack == null)
				throw new ExerciseException("Task can't be null");
			this.task = taskToTrack;
			this.avgExecutionTime = 0;
			this.numberOfExecution = 0;
		}
		
		@Override
		public String toString() {
			return "Stats ("+task.getDescription() +")\n"
					+ "\tavgExecutionTime: " + this.getAvgSec()+" sec" 
					+ "\n\tnumberOfExecution: "+ numberOfExecution;
		}

		void run() throws ExerciseException {
			this.numberOfExecution++;
			System.out.print("\n");
			long runTime = System.nanoTime();
			this.task.run();
			runTime = System.nanoTime()-runTime;
			this.avgExecutionTime =
				(this.avgExecutionTime*(this.numberOfExecution-1)+runTime)/this.numberOfExecution;
		}
	}
	
	private List<ExerciseTaskInfo> tasks;
	private int selection;
	private Scanner inputScan;
	
	private <T extends Exercise> void createTask(Class<T> taskClass) {
		try {
			ExerciseTaskInfo taskData = new ExerciseTaskInfo(taskClass
					.getConstructor(Scanner.class).newInstance(this.inputScan));
			tasks.add(taskData);
		} catch (	InstantiationException	|IllegalAccessException|
					IllegalArgumentException|InvocationTargetException|
					NoSuchMethodException	|SecurityException	e) {
			e.printStackTrace();
		} catch (ExerciseException e) {
			System.err.print(e.getMessage());
		}
	}
	
	private void initializeTasks() {		
		tasks = new LinkedList<>();
		this.createTask(AverageLenght.class);
		this.createTask(DoubleSpaceRemover.class);
		this.createTask(SearchWord.class);
		this.tasks = new ArrayList<>(this.tasks);
	}
	
	public TaskSelector() {
		this.selection = 0;
		this.inputScan = new Scanner(System.in);
		this.initializeTasks();
	}
	
	private void waitForInput()
	{
		System.out.print("\nPress enter to continue...\n");
		this.inputScan.nextLine();
	}
	
	public void printMenu() {
		boolean haveToTerminate = false;
		
		do {
			System.out.println("Exercizes 1 Menu'");
			int id = 1;
			for (ExerciseTaskInfo exercise : this.tasks) 
				System.out.println("\t" + id++ + " - "+ exercise.task.getDescription());
			System.out.print("use id info (ex: \"1 stats\") to check stats about the item"
					+ "\nPlease make a choice ("+id+" to exit): ");

			try {
				String input = this.inputScan.nextLine();
				System.out.print("\n");
				String[] splitted = input.split(" ");
				this.selection = Integer.parseInt(splitted[0]);
				if(this.selection<1 || this.selection > id)
				{
					System.out.println("No such option...");
					continue;
				}
				
				if(splitted.length > 1) {
					if(splitted[1].toLowerCase().equals("stats")) {
						if(selection == id)
							System.out.println("Terminate the program");
						else 
							System.out.println(this.tasks.get(this.selection-1));
					}
					else {
						System.out.println("Unknown option");
					}
				}
				else if(selection != id) {
					this.tasks.get(this.selection-1).run();
				}
				else {
					System.out.println("Bye bye~");
					haveToTerminate = true;
				}

				if(!haveToTerminate)
					this.waitForInput();
				else
					Thread.sleep(1000);
			}
			catch(Exception ex) {
				System.out.println("ERROR: "+ex.getMessage());
			}
		
		}while(!haveToTerminate);
	}	
}
