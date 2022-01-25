package view;

import java.util.Scanner;

import model.Gestore;

public class MainView {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int returnCode = Menu.run(new CreationMenu(scanner),new OperationMenu(scanner));
		System.out.println("bye bye #"+returnCode);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
