/**
 * @author pf-miles
 *
 */
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintWhenCalled {
    @OnMethod(clazz = "java.util.zip.Inflater", method = "/.*/")
    public static void traceExecute(@ProbeMethodName String methodName) {
        BTraceUtils.println(BTraceUtils.concat("who call Inflater: ", methodName));
        BTraceUtils.jstack();
    }
}
