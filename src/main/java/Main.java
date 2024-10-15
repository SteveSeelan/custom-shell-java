import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Map<String, String> responseFromCommand = new HashMap<>();
        responseFromCommand.put("echo", " is a shell builtin");
        responseFromCommand.put("type", " is a shell builtin");
        responseFromCommand.put("exit", " is a shell builtin");
        responseFromCommand.put("cat", " is /bin/cat");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");

            String input = scanner.nextLine();
            String[] shellInput = input.split(" ");
            String[] restOfCommand = Arrays.copyOfRange(shellInput, 1, shellInput.length);

            String command = shellInput[0];

            if (command.equalsIgnoreCase("echo")) {
                System.out.println(String.join(" ", restOfCommand));
            } else if (command.equalsIgnoreCase("type")) {
                if (restOfCommand.length == 0) {
                    System.out.println(command + ": command not found");
                    continue;
                }
                String type = restOfCommand[0];

                if (responseFromCommand.containsKey(type)) {
                    System.out.println(TextColor.RED + type + TextColor.RESET + responseFromCommand.get(type));
                } else {
                    System.out.println(type + ": not found");
                }
            } else if (input.equalsIgnoreCase("exit 0")) {
                break;
            } else {
                System.out.println(input + ": command not found");
            }
        }
        scanner.close();
    }
}
