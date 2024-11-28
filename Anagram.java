import java.util.Random;

public class Anagram {

    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent", "listen"));  // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

        // Tests the preProcess function.
        System.out.println(preProcess("What? No way!!!"));  // should output "whatnoway"

        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        // Performs a stress test of randomAnagram 
        String str = "1234567";
        Boolean pass = true;
        // 10 can be changed to much larger values, like 1000
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");

        // Additional Test 5 for complex anagram
        boolean test5 = isAnagram("William Shakespeare", "I am a weakish speller");
        System.out.println("Test 5 (complex anagram): " + (test5 ? "PASS" : "FAIL"));
    }

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String str1, String str2) {
        // Preprocess both strings to remove spaces and make them lowercase
        str1 = preProcess(str1);
        str2 = preProcess(str2);

        // If the lengths are not the same, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert both strings to character arrays
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        // Sort both arrays
        java.util.Arrays.sort(charArray1);
        java.util.Arrays.sort(charArray2);

        // Compare the sorted arrays
        return new String(charArray1).equals(new String(charArray2));
    }

    // Returns a preprocessed version of the given string:
    // all letter characters are converted to lower-case,
    // and all other characters (except spaces) are deleted.
    public static String preProcess(String str) {
        String result = "";
        // Convert the string to lowercase and only add letters to the result string
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) { // Only add letters (ignore spaces and punctuation)
                result += Character.toLowerCase(c);
            }
        }
        return result;
    }

    // Returns a random anagram of the given string.
    // The random anagram consists of the same characters as the given string, rearranged randomly.
    public static String randomAnagram(String str) {
        // We will work directly with the string and remove characters as we go
        String remainingChars = preProcess(str);  // Preprocess to remove unwanted characters
        String result = "";
        Random rand = new Random();

        // Loop through and randomly select characters
        while (remainingChars.length() > 0) {
            // Pick a random index from the remaining characters
            int randomIndex = rand.nextInt(remainingChars.length());
            // Get the character at the random index
            char randomChar = remainingChars.charAt(randomIndex);
            // Add this character to the result
            result += randomChar;
            // Remove the selected character from the remaining string
            remainingChars = remainingChars.substring(0, randomIndex) + remainingChars.substring(randomIndex + 1);
        }

        return result;
    }
}
