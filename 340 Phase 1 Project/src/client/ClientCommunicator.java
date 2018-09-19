package client;

import general.CommandData;
import general.Results;
import general.Serializer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientCommunicator {

    private static ClientCommunicator clientCommunicator;

    public static ClientCommunicator get(){
        if(clientCommunicator == null) {
            clientCommunicator = new ClientCommunicator();
        }
        return clientCommunicator;
    }

    private Serializer serializer = new Serializer();


    Results send(CommandData requestInfo){

        String requestString = serializer.serializeCommandData(requestInfo);
        try {
            URL url = new URL("http://" + "localhost" + ":" + "8080" + "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            writeString(requestString, os);
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                String resultString = readString(is);
                return serializer.deserializeResults(resultString);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Results(false, null, "IOException");
        }
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
