package client;

import general.CommandData;
import general.Results;
import general.iStringProcessor;

public class StringProcessorProxy implements iStringProcessor {

    private static StringProcessorProxy stringProcessorProxy;

    public static StringProcessorProxy get(){
        if(stringProcessorProxy == null){
            stringProcessorProxy = new StringProcessorProxy();
        }
        return stringProcessorProxy;
    }

    @Override
    public String toLowerCase(String s) throws Exception {
        CommandData command = new CommandData(s, CommandData.CommandType.TOLOWERCASE);
        Results results = ClientCommunicator.get().send(command);
        if(results.success()){
            return (String) results.getData();
        } else {
            throw new Exception("Could not get result");
        }

    }

    @Override
    public String trim(String s) throws Exception {
        CommandData command = new CommandData(s, CommandData.CommandType.TRIM);
        Results results = ClientCommunicator.get().send(command);
        if(results.success()){
            return (String) results.getData();
        } else {
            throw new Exception("Could not get result");
        }
    }

    @Override
    public Double parseDouble(String s) throws NumberFormatException {
        CommandData command = new CommandData(s, CommandData.CommandType.PARSEDOUBLE);
        Results results = ClientCommunicator.get().send(command);
        if(results.success()){
            return (Double) results.getData();
        } else {
            throw new NumberFormatException("Could not get result");
        }
    }



}
