import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextGenerator {
    private Map<String, List<Character>> nGrams;
    private int n;
    private Map<String, Map<Character, Double>> probabilities;

    public TextGenerator(int n) {
        this.n = n;
        this.nGrams = new HashMap<>();
        this.probabilities = new HashMap<>();
    }

    public void train(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    processLine(line);
                }
            }

            calculateProbabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        for (int i = 0; i < line.length() - n; i++) {
            String nGram = line.substring(i, i + n);
            char nextChar = line.charAt(i + n);
            nGrams.computeIfAbsent(nGram, k -> new ArrayList<>()).add(nextChar);
        }
    }

    private void calculateProbabilities() {
        for (Map.Entry<String, List<Character>> entry : nGrams.entrySet()) {
            String nGram = entry.getKey();
            List<Character> nextChars = entry.getValue();

            Map<Character, Integer> charCount = new HashMap<>();
            for (char nextChar : nextChars) {
                charCount.put(nextChar, charCount.getOrDefault(nextChar, 0) + 1);
            }

            int totalCount = nextChars.size();
            Map<Character, Double> charProbability = new HashMap<>();
            for (Map.Entry<Character, Integer> charEntry : charCount.entrySet()) {
                char nextChar = charEntry.getKey();
                int count = charEntry.getValue();
                double probability = (double) count / totalCount;
                charProbability.put(nextChar, probability);
            }

            probabilities.put(nGram, charProbability);
        }
    }

    public double getProbability(String nGram, char nextChar) {
        Map<Character, Double> charProbability = probabilities.get(nGram);
        if (charProbability != null) {
            return charProbability.getOrDefault(nextChar, 0.0);
        }
        return 0.0;
    }

    public String generateText(String seed, int length) {
        StringBuilder generatedText = new StringBuilder(seed);

        for (int i = 0; i < length-1; i++) {
            String currentGram = generatedText.substring(generatedText.length() - n);
            Map<Character, Double> charProbability = probabilities.get(currentGram);
            if (charProbability != null && !charProbability.isEmpty()) {
                char nextChar = getRandomCharacter(charProbability);
                generatedText.append(nextChar);
            } else {
                break;
            }
        }

        return generatedText.toString();
    }

    private char getRandomCharacter(Map<Character, Double> charProbability) {
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (Map.Entry<Character, Double> entry : charProbability.entrySet()) {
            char nextChar = entry.getKey();
            double probability = entry.getValue();
            cumulativeProbability += probability;

            if (randomValue <= cumulativeProbability) {
                return nextChar;
            }
        }

        //zwroci ostatni znak jak z jakiegos powodu nie mozna znalezc znaku atkualnego
        return (char) charProbability.keySet().toArray()[charProbability.size() - 1];
    }

    public void displayProbabilities() {
        for (Map.Entry<String, Map<Character, Double>> entry : probabilities.entrySet()) {
            String nGram = entry.getKey();
            Map<Character, Double> charProbability = entry.getValue();

            System.out.println("Prawdopodobieństwa dla n-gramu \"" + nGram + "\":");
            for (Map.Entry<Character, Double> charEntry : charProbability.entrySet()) {
                char nextChar = charEntry.getKey();
                double probability = charEntry.getValue();
                System.out.println("Znak \"" + nextChar + "\": " + probability);
            }
            System.out.println();
        }
    }


}
