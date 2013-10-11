package functionality;

import java.io.IOException;
import java.io.InputStream;

import datastructure.ByteAppender;

/**
 * 加载java message format形式的模板
 * 
 * @author pf-miles Nov 8, 2012 5:52:03 PM
 */
public abstract class SimpleTemplateUtil {

    /**
     * 根据传入类和相对路径，找到模板
     * 
     * @param relativeToCls
     *            计算模板相对位置的class
     * @param relativePath
     *            模板文件相对于class的位置
     * @return 字符串形式的模板内容，后续可使用MessageFormat.format()方法执行渲染
     */
    public static String loadTemplate(Class<?> relativeToCls, String relativePath) {
        return readStrStream(relativeToCls.getResourceAsStream(relativePath));
    }

    private static String readStrStream(InputStream stm) {
        if (stm == null)
            return null;
        ByteAppender ba = new ByteAppender();
        byte[] buf = new byte[1024];
        int count = 0;
        try {
            count = stm.read(buf);
            while (count != -1) {
                ba.append(buf, 0, count);
                count = stm.read(buf);
            }
            return new String(ba.toByteArray(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}