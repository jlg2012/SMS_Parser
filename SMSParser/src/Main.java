import Model.SMS;
import Model.VMG;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import util.VMGReader;

import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: lester
 * Date: 12/26/12
 * Time: 2:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException {
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

    public static SMS readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));


        DataInputStream in = new DataInputStream(stream);
        BOMInputStream bom = new BOMInputStream(in, false, ByteOrderMark.UTF_8);
        BufferedReader br = new BufferedReader(new InputStreamReader(bom));
        String strLine;

        try {
            SMS sms = new SMS();
            VMG vmg = new VMG();

            boolean msgbody = false;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                String vmgLine = VMGReader.read(strLine);

                if (vmgLine.startsWith("TEL:")) {
                    sms.setNumber(vmgLine.substring(vmgLine.lastIndexOf(":") + 1));
                }

                if (msgbody) {
                    if (vmgLine.startsWith("END:VBODY")) {
                        msgbody = false;
                    } else {
                        sms.appendMessageBody(vmgLine);
                    }
                }

                if (vmgLine.startsWith("Date:")) {
                    String msg = vmgLine.substring(vmgLine.lastIndexOf("Date:"));
//                    msg.substring(0, vmgLine.indexOf("END:VBODY"));
                    sms.setDate(msg);
                    msgbody = true;
                }
            }
//            System.out.println ("Avsender " + sms.getNumber());
//            System.out.println(sms.getDate());
//            System.out.println("Melding " + sms.getMessageBody());
//            System.out.println();
            return sms;

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public static void generateReport(String sFileName, Map<Date, SMS> smsTreeMap) {
        try {
            FileWriter writer = new FileWriter(sFileName + "test.csv");

            for (Map.Entry<Date, SMS> sms : smsTreeMap.entrySet()) {

                // writer.append(sms.getKey().toString());
                //writer.append(';');
                writer.append(sms.getValue().getDate().toString());
                writer.append(';');

                Date date; // your date
                Calendar cal = Calendar.getInstance();
                cal.setTime(sms.getValue().getDate());
                int year = cal.get(Calendar.YEAR);

                writer.append((char) year);
                writer.append(';');

                writer.append(sms.getValue().getMessageBody());
                writer.append(';');
                writer.append(sms.getValue().getNumber());

                writer.append('\n');
            }


            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
