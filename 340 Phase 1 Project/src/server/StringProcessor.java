package server;

import general.iStringProcessor;

public class StringProcessor implements iStringProcessor {
    private static StringProcessor stringProcessor;

    public static StringProcessor get() {
        if(stringProcessor == null){
            stringProcessor = new StringProcessor();
        }
        return stringProcessor;
    }

    @Override
    public String toLowerCase(String s) {
        return s.toLowerCase();
    }

    @Override
    public String trim(String s) {
        return s.trim();
    }

    @Override
    public Double parseDouble(String s) {
        return Double.parseDouble(s);
    }
}
