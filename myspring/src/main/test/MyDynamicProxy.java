import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDynamicProxy {

    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        // 调用代理方法
        proxyHello.sayHello();
        proxyHello.saySomething();
    }
}

    interface Hello {
        void sayHello();

        void saySomething();
    }

    class HelloImpl implements Hello {
        @Override
        public void sayHello() {
            System.out.println("Hello World");
        }

        @Override
        public void saySomething() {
            System.out.println("saySomething");
        }
    }

    class MyInvocationHandler implements InvocationHandler {

        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("Invoking sayHello");
            Object result = method.invoke(target, args);
            return result;
        }
}