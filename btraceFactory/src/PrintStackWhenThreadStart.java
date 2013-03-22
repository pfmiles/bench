import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

@BTrace
public class PrintStackWhenThreadStart {
    @OnMethod(clazz = "java.lang.Thread", method = "start")
    public static void traceExecute() {
        BTraceUtils.jstack();
    }
}
