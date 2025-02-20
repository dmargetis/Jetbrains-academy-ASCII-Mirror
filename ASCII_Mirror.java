import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String pathToFile = scanner.nextLine();
        List<String> list = new ArrayList<>();

        File file = new File(pathToFile);
        try(Scanner scannerFile = new Scanner(file)) {
            while(scannerFile.hasNextLine()) {
                list.add(scannerFile.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        List<String> whiteList = addWhiteSpace(list);
        List<String> reversedList = mirror(whiteList);
        List<String> reflectedList = replaceReflectedChars(reversedList);
        printList(mergeLists(whiteList,reflectedList));
    }

    public static void printList(List<String> list) {
        for(String line : list) {
            System.out.println(line);
        }
    }

    public static List<String> replaceReflectedChars(List<String> list) {
        List<String> modifiedList = new ArrayList<>();
        for(String line : list) {
            char[] array = line.toCharArray();
            for (int i = 0; i < array.length; i++) {
                switch (array[i]) {
                    case '/' -> array[i] = '\\';
                    case '\\' -> array[i] = '/';
                    case '<' -> array[i] = '>';
                    case '>' -> array[i] = '<';
                    case '[' -> array[i] = ']';
                    case ']' -> array[i] = '[';
                    case '(' -> array[i] = ')';
                    case ')' -> array[i] = '(';
                }
            }
            modifiedList.add(String.valueOf(array));
        }
        return modifiedList;
    }

    public static ArrayList<String> mergeLists(List<String> list1, List<String> list2) {
        ArrayList<String> mergedList = new ArrayList<>();
        for(int i = 0; i < list1.size(); i++) {
            mergedList.add(list1.get(i) + " | " + list2.get(i));
        }
        return mergedList;
    }

    public static ArrayList<String> mirror(List<String> list) {
        ArrayList<String> reversedList = new ArrayList<>();
        for(String line : list) {
            StringBuilder builder = new StringBuilder(line);
            reversedList.add(builder.reverse().toString());
        }
        return reversedList;
    }

    public static ArrayList<String> addWhiteSpace(List<String> list) {
        ArrayList<String> listWithWhitespace = new ArrayList<>();
        int max = findMaxLength(list);
        for(String line : list) {
            int spaces = max - line.length();
            listWithWhitespace.add(line + " ".repeat(Math.max(0, spaces)));
        }
        return listWithWhitespace;
    }

    public static int findMaxLength(List<String> list) {
        int max = Integer.MIN_VALUE;
        for(String line : list) {
            int currentLength = line.length();
            if(currentLength > max){
                max = currentLength;
            }
        }
        return max;
    }
}
