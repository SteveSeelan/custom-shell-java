import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PathHelper {
    private static Map<String, String> pathToLocation;
    private static final String path = System.getenv("PATH");
    private final HashSet<String> shellBuiltins;

    public PathHelper() {
        shellBuiltins = new HashSet<>();
        pathToLocation = extractPath().isPresent() ? extractPath().get() : null;
    }

    public void runExec(String pathCommand, List<String> args) throws IOException {
        if (pathToLocation != null) {
            try {
                List<String> runCommand = new ArrayList<>();
                runCommand.add(pathToLocation.get(pathCommand));  // The executable
                runCommand.addAll(args);
//                    System.out.println("Trying exec: " + execToPath.get().get(command) + Arrays.toString(restOfCommand));
                // Create a ProcessBuilder instance
                ProcessBuilder processBuilder = new ProcessBuilder(runCommand);

                // Start the process

                Process process = processBuilder.start();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                // Capture and display the error output (stderr), if any
                BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line);
                }
                // Wait for the process to complete
                int exitCode = process.waitFor();
//                    System.out.println("Process exited with code: " + exitCode);

            } catch (IOException | InterruptedException ioException) {
                throw new IOException(ioException);
            }
        }
    }

    private static Optional<Map<String, String>> extractPath() {
        Map<String, String> execToPath = new HashMap<>();

        if (path != null) {
            String[] paths = path.split(":");

            for (String path : paths) {
                File dir = new File(path);
                File[] directoryListing = dir.listFiles();

                if (directoryListing != null) {
                    for (File file : directoryListing) {
                        if (file.isFile() && file.canExecute()) {
//                            System.out.println("Adding file(key) and path(val): " + file.getName() + " " + file.getAbsolutePath());
                            if (!execToPath.containsKey(file.getName())) {
                                execToPath.put(file.getName(), file.getAbsolutePath());
                            }
                        }
                    }
                }

            }
        }
        return Optional.of(execToPath);
    }

    public Map<String, String> getPathToLocation() {
        return pathToLocation;
    }

    public void addShellBuiltins(String shellBuiltin) {
        this.shellBuiltins.add(shellBuiltin);
    }

    public HashSet<String> getShellBuiltins() {
        return shellBuiltins;
    }
}
