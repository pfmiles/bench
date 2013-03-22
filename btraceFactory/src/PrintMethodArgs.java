import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

@BTrace
public class PrintMethodArgs {
    @OnMethod(clazz = "sample.Sample1", method = "hello")
    public static void func(AnyType[] args) {
        BTraceUtils.println("Class: sample.Sample1, Method: hello, args: ");
        BTraceUtils.println(args[0]);
        BTraceUtils.println(args[1]);
    }

}
