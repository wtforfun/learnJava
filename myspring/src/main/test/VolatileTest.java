public class VolatileTest {

    private  int i = 0;

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        final Object object = new Object();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized (object){
                    for(int i  = 0;i<1000;i++){
                        volatileTest.i = volatileTest.i+1;
                    }
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                synchronized (object){
                    for(int i  = 0;i<1000;i++){
                        volatileTest.i = volatileTest.i+1;
                    }
                }
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                synchronized (object){
                    for(int i  = 0;i<1000;i++){
                        volatileTest.i = volatileTest.i+1;
                    }
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();


        while(Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(volatileTest.i);

    }
}
