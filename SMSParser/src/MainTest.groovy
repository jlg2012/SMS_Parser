
/**
 * Created with IntelliJ IDEA.
 * User: lester
 * Date: 05/01/14
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
class MainTest extends GroovyTestCase {
    private static final String PATH = "../"

    void setUp() {

    }

    void tearDown() {

    }

    void testReadFile() {
        File folder = new File(PATH);

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                System.out.println(file.name);
            }
        }
    }
}
