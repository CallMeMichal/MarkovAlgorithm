import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj wartość n: ");
        int n = scanner.nextInt();

        System.out.print("Podaj długość generowanego tekstu: ");
        int length = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Podaj sekwencję startową: ");
        String seed = scanner.nextLine();

        System.out.print("Podaj ścieżkę do pliku tekstowego z danymi uczącymi: ");
        String filePath = scanner.nextLine();

        TextGenerator generator = new TextGenerator(n);
        generator.train(filePath);

        generator.displayProbabilities();

        String generatedText = generator.generateText(seed, length);
        System.out.println("Wygenerowany tekst: " + generatedText);
    }
}


