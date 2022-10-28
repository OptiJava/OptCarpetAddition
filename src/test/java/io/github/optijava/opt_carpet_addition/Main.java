package io.github.optijava.opt_carpet_addition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:\\Program\\Project\\resources\\app\\Git\\Projects\\OptCarpetAddition\\src\\test\\resources\\test.properties"));
        // System.out.println(prop.getProperty("TpWhitelist"));

        for (String s : prop.getProperty("TpWhitelist").split(";")) {
            System.out.println(s);
        }
    }
}
