import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");

            String input = scanner.nextLine();
            String[] command = input.split(" ");

            if (command[0].equalsIgnoreCase("echo")) {
                List<String> restOfCommand = List.of(Arrays.copyOfRange(command, 1, command.length));
                System.out.println(String.join(" ", restOfCommand));
            } else if (input.equalsIgnoreCase("exit 0")) {
                break;
            } else {
                System.out.println(input + ": command not found");
            }
        }
        scanner.close();
    }
}
