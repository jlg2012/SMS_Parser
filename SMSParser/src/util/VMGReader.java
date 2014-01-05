package util;

/**
 * Created with IntelliJ IDEA.
 * User: lester
 * Date: 05/01/14
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class VMGReader {
    public static String read(String strLine) {
        return strLine.replaceAll("\\u0000", "");
    }

    public static int getNumberOfLines(String strLine) {
        String[] s = strLine.split("\\u0000E");

        return s.length;
    }

}
