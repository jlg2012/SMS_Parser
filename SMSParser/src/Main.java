import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: lester
 * Date: 12/26/12
 * Time: 2:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String [] args) throws IOException{
        final String path = "/home/lester/Downloads/test2.vmg";
//        FileInputStream stream = new FileInputStream(new File(path));
//        try {
//
//            Charset charset = Charset.forName("UTF-8");
//            CharsetDecoder decoder = charset.newDecoder();
//
//            FileChannel fc = stream.getChannel();
//            MappedByteBuffer bb= fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
//            System.out.println(decoder.decode(bb).toString());
////            System.out.println(Charset. defaultCharset().decode(bb).toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        finally {
//            stream.close();
//        }
//
//        System.out.println("AAA");
        readFile(path);
    }

    private static void readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));


        DataInputStream in = new DataInputStream(stream);
        BOMInputStream bom = new BOMInputStream(in, false, ByteOrderMark.UTF_8);
        BufferedReader br = new BufferedReader(new InputStreamReader(bom));
        String strLine;

        System.out.println (bom.getBOMCharsetName());

        if (bom.hasBOM()){
            System.out.println ("TRUE");
        }
        else {
            System.out.println("FALSE");
        }


        try {
            while ((strLine = br.readLine()) != null)   {
                // Print the content on the console
                String[] s = strLine.split("\\u0000E");
                System.out.println (strLine.replaceAll("\\u0000", ""));
                System.out.println (s.length);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
