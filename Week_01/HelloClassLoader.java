import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    private String path;
    private String method;
    private String className;
    private boolean flag;
    public HelloClassLoader() {
    }
    public HelloClassLoader(String path, String className , String method, boolean flag) {
        this.path = path;
        this.method = method;
        this.className = className;
        this.flag = flag;
    }

    public void classLoaderInvokeMethod(){
        try {
            Class<?> aClass = this.findClass(this.className);
            Object object = aClass.newInstance();
            Method hello = aClass.getDeclaredMethod(this.method);
            hello.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.path);
            BufferedInputStream buff = new BufferedInputStream(fileInputStream);

            int len = buff.available();
            byte [] bytes = new byte[len];
            buff.read(bytes, 0, len);
            if (flag){
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (255 - bytes[i]);
                }
            }
            return defineClass(name ,bytes ,0 ,bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
