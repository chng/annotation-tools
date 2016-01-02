import org.junit.Test;

/**
 * Created by OurEDA on 2015/12/17.
 */
public class TestFinally {

    @Test
    public void test() {
        System.out.println(method());
    }

    public int method() {
        int a = 0;
        try{

            return a;

        } catch(Exception e) {

        } finally {
            System.out.println("finally");
            a=1;
        }
        return a;
    }

}
