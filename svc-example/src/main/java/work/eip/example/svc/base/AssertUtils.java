package work.eip.example.svc.base;

public class AssertUtils {

    public static void assertTrue(boolean b, String errorMessage) {
        if (!b) {
            throw new SvcExecption(errorMessage);
        }
    }

    public static void assertNotNull(Object o, String errorMessage) {
        if (o == null) {
            throw new SvcExecption(errorMessage);
        }
    }
}
