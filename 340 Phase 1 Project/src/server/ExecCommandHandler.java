package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import general.CommandData;
import general.Results;
import general.Serializer;

import java.io.*;

import static java.net.HttpURLConnection.HTTP_OK;

public class ExecCommandHandler implements HttpHandler {

    private Serializer serializer;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        serializer = new Serializer();
        InputStream inputStream = exchange.getRequestBody();
        String requestString = readString(inputStream);
        try {
            Results results = processString(requestString);
            exchange.sendResponseHeaders(HTTP_OK, 0);
            String serializedResults = serializer.serializeResults(results);
            OutputStream os = exchange.getResponseBody();
            writeString(serializedResults, os);
            exchange.close();
        } catch (NumberFormatException e) {
            throw new IOException("Number");
        } catch (Exception e) {
            throw new IOException();
        }

    }

    private Results processString(String requestString) throws Exception {
        CommandData commandData = serializer.deserializeCommandData(requestString);
        ICommand command = getCommand(commandData);
        return command.execute();
    }

    private ICommand getCommand(CommandData data) throws Exception {
        if(data.getType() == CommandData.CommandType.TOLOWERCASE){
            return new ToLowerCaseCommand(data.getData());
        } else if (data.getType() == CommandData.CommandType.TRIM) {
            return new TrimCommand(data.getData());
        } else if (data.getType() == CommandData.CommandType.PARSEDOUBLE) {
            return new ParseDoubleCommand(data.getData());
        } else throw new Exception();
    }


    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}
