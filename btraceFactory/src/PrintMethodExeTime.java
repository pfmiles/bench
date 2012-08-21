import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.TLS;

/**
 * @author pf-miles
 * 
 */
@BTrace
public class PrintMethodExeTime {
    // store entry time in thread local
    @TLS
    private static long startTime;

    @OnMethod(clazz = "com.alibaba.cep.utils.SafeQueue", method = "poll")
    public static void onMethodEntry() {
        startTime = BTraceUtils.timeMillis();
    }

    @OnMethod(clazz = "com.alibaba.cep.utils.SafeQueue", method = "poll", location = @Location(Kind.RETURN))
    public static void onMethodReturn() {
        BTraceUtils.println(BTraceUtils.str(BTraceUtils.timeMillis() - startTime));
    }
}
