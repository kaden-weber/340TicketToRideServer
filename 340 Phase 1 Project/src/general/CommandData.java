package general;

public class CommandData {

    public enum CommandType {
        TOLOWERCASE, TRIM, PARSEDOUBLE
    }
    String data;
    CommandType type;

    public CommandData(String data, CommandType type) {
        this.data = data;
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
