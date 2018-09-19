package server;

import general.Results;

public class TrimCommand implements ICommand {

    private String data;

    public TrimCommand(String data) {
        this.data = data;
    }

    @Override
    public Results execute() {
        String trimmedString = StringProcessor.get().trim(data);

        return new Results(true, trimmedString, null);
    }
}
