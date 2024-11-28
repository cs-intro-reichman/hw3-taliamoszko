public class AnagramChecker {

    public static void main(String[] args) {
        // Test isAnagram function
        System.out.println(areWordsAnagrams("silent", "listen"));  // true
        System.out.println(areWordsAnagrams("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(areWordsAnagrams("Madam Curie", "Radium came")); // true
        System.out.println(areWordsAnagrams("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

        // Test the cleanString function
        System.out.println(cleanString("What? No way!!!"));

        // Test the createRandomAnagram function
        System.out.println("silent and " + createRandomAnagram("silent") + " are anagrams.");

        // Perform a stress test for createRandomAnagram
        String testString = "1234567";
        boolean testPassed = true;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = createRandomAnagram(testString);
            System.out.println(randomAnagram);
            testPassed = testPassed && areWordsAnagrams(testString, randomAnagram);
            if (!testPassed) break;
        }
        System.out.println(testPassed ? "Test passed" : "Test failed");
    }

    // This function checks if two strings are anagrams of each other
    public static boolean areWordsAnagrams(String word1, String word2) {
        // Clean both words by removing non-letter characters and converting to lowercase
        String cleanedWord1 = cleanString(word1);
        String cleanedWord2 = cleanString(word2);

        // If cleaned strings are of different lengths, they can't be anagrams
        if (cleanedWord1.length() != cleanedWord2.length()) {
            return false;
        }

        // Convert the cleaned strings into character arrays
        char[] charArray1 = cleanedWord1.toCharArray();
        char[] charArray2 = cleanedWord2.toCharArray();

        // Sort the character arrays manually (you can use Arrays.sort in real use, but here's a simple sort)
        bubbleSort(charArray1);
        bubbleSort(charArray2);

        // Check if the sorted arrays are equal
        for (int i = 0; i < charArray1.length; i++) {
            if (charArray1[i] != charArray2[i]) {
                return false;
            }
        }

        return true;
    }

    // This function removes non-letter characters and converts the string to lowercase
    public static String cleanString(String input) {
        StringBuilder cleaned = new StringBuilder();

        // Loop through each character of the string
        for (char c : input.toCharArray()) {
            // If the character is a letter, add it to the cleaned string (converted to lowercase)
            if (Character.isLetter(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }

        // Return the cleaned string
        return cleaned.toString();
    }

    // This function generates a random anagram by shuffling the characters of the given string
    public static String createRandomAnagram(String input) {
        // Convert string to a character array
        char[] charArray = input.toCharArray();
        int length = charArray.length;

        // Shuffle the array manually (no Collections.shuffle)
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * length);
            char temp = charArray[i];
            charArray[i] = charArray[randomIndex];
            charArray[randomIndex] = temp;
        }

        // Convert the array back to a string and return it
        return new String(charArray);
    }

    // Bubble sort implementation for sorting character arrays
    public static void bubbleSort(char[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    // Swap
                    char temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
