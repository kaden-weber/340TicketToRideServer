package server;

import general.Results;

public class ToLowerCaseCommand implements ICommand {

    private String data;

    public ToLowerCaseCommand(String data) {
        this.data = data;
    }

    @Override
    public Results execute() {
        String lowerCaseString = StringProcessor.get().toLowerCase(data);
        return new Results(true, lowerCaseString, null);
    }
}
