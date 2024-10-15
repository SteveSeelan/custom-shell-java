import java.util.HashMap;
import java.util.Map;

public class Type {
    private Map<String, String> responseFromCommand = new HashMap<>();
    private String COMMAND_NOT_FOUND = ": not found";

    public Type(Map<String, String> commandMap) {
        this.responseFromCommand = commandMap;
    }

    public void setResponseFromCommand(String type, String command) {
        this.responseFromCommand.put(type, command);
    }

    public String getResponse(String type) {
        if (responseFromCommand.containsKey(type)) {
            return this.responseFromCommand.get(type);
        }
        else {
            return this.COMMAND_NOT_FOUND;
        }
    }
}
