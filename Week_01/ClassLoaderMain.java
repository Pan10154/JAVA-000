public class ClassLoaderMain {
    public static void main(String[] args) {
        HelloClassLoader helloClassLoader1 = new HelloClassLoader("D:\\myproject\\app\\demo\\Hello\\Hello.xlass","Hello" ,"hello",true);
        helloClassLoader1.classLoaderInvokeMethod();
        HelloClassLoader helloClassLoader2 = new HelloClassLoader("D:\\myproject\\app\\demo\\Hello\\Hello.class","Hello" ,"hello",false);
        helloClassLoader2.classLoaderInvokeMethod();
    }
}
