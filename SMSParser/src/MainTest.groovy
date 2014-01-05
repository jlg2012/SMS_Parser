import Model.SMS
/**
 * Created with IntelliJ IDEA.
 * User: lester
 * Date: 05/01/14
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
class MainTest extends GroovyTestCase {
    private static final String PATH = "/Users/lester/Google Drive/Ukesplaner/test/2007-06-11 - SMS mobil"

    void setUp() {

    }

    void tearDown() {

    }

    void testReadFile() {
        File folder = new File(PATH);

        int smss
        Map<Date, SMS> m = new TreeMap<Date, SMS>();

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                //System.out.println(file.name);

                SMS sms = Main.readFile(file.absolutePath);
                //break

                m.put(sms.getDate(), sms);

                smss++;
            }
        }
        for (Map.Entry<Date, SMS> sms : m) {

            System.out.println(sms.getKey().toString());
            System.out.println(sms.getValue().getMessageBody());
            System.out.println(sms.getValue().getNumber());
            System.out.println();

        }

        Main.generateReport(PATH, m);

        System.out.println("Lastet " + smss + " filer");
    }
}
