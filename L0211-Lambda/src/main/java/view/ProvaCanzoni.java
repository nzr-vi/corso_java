package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProvaCanzoni {

	public static void main(String[] args) {
//		String[][] canzoni = {
//				{"Brividi", "Mahmood e Blanco"},
//				{"0 passi", "Deddy"},
//				{"A un passo dalla luna", "Rocco Hunt & Ana Mena"},
//				{"Afterglow", "Ed Sheeran"},
//				{"Baby", "Sfera Ebbasta & J Balvin"}
//		};
//
//		Arrays
//			.stream(canzoni)
//			.filter(c -> c[0].startsWith("A"))
//			.forEach(c -> System.out.println(c[0]));

		Stream<String> canzoni;
		try {
			canzoni = Files.lines(Paths.get("files/canzoni.txt"));
			canzoni.forEach(System.out::println);
			List<String> stringhe = canzoni.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
