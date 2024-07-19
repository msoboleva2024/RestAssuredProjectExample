package testData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGeneration {

	public String generatePlaceId(int n) {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (alphaNumericString.length() * Math.random());

			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public int generateRandomInt(int min, int max) {

		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzäöüÄÖÜß";

	public String generateStreetName(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}

		return stringBuilder.toString() + "strasse";
	}

	public String generateLanguage() {

		List<String> exampleList = Arrays.asList("Englisch", "Deutsch", "Italienisch", "Russisch", "Spanisch");
		Random random = new Random();
		int randomIndex = random.nextInt(exampleList.size());
		return exampleList.get(randomIndex);

	}

	public String generateCity() {

		List<String> exampleList = Arrays.asList("Augsburg", "Berlin", "Dresden", "Munich", "Nürnberg");
		Random random = new Random();
		int randomIndex = random.nextInt(exampleList.size());
		return exampleList.get(randomIndex);

	}

	public double generateRandomDouble(double min, double max) {
		Random random = new Random();

		return min + (max - min) * random.nextDouble();
	}

	public String generatePhoneNumber() {

		return ("+49" + String.valueOf(generateRandomInt(3456789, 233453466)));
	}

	public String generateWebSite() {

		return (generatePlaceId(10) + ".test_domain.com");
	}

	public ArrayList<String> generateRandomArrayList(int subsetSize) {

		List<String> exampleList = Arrays.asList("one", "two", "three", "four", "five");
		List<String> listCopy = new ArrayList<String>(exampleList);

		// Shuffle the list
		Collections.shuffle(listCopy);

		// Handle case when subsetSize is greater than the size of the list
		if (subsetSize > listCopy.size()) {
			subsetSize = listCopy.size();
		}

		// Select a random subset
		List<String> randomSubset = listCopy.subList(0, subsetSize);

		// Create a new ArrayList from the subset and return it
		return new ArrayList<String>(randomSubset);
	}

}
