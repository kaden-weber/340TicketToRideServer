package server;

import general.Results;

public class ParseDoubleCommand implements ICommand {

    private String data;

    public ParseDoubleCommand(String data) {
        this.data = data;
    }

    @Override
    public Results execute() {

        Double parsedDouble = StringProcessor.get().parseDouble(data);
        return new Results(true, parsedDouble, null);
    }
}
