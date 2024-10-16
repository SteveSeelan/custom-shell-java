import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PathHelper {
    public Map<String, String> pathToLocation;

    public static Optional<Map<String, String>> extractPath(String pathArg) {
        Map<String, String> execToPath = new HashMap<>();

        if (pathArg != null) {
            String[] pathDirs = pathArg.split("PATH=");
            String[] paths = pathDirs[1].split(":");

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
}
