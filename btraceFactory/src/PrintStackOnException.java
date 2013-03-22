import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.OnMethod;

/**
 * print the stack trace when the exception specified is thrown;
 * 
 * 
 * @author pf-miles
 * 
 */
public class PrintStackOnException {
    @OnMethod(clazz = "java.lang.ClassNotFoundException", method = "<init>")
    public static void traceExecute() {
        BTraceUtils.jstack();
    }
}
