public class TestVolatile {
    public volatile int inc = 0;
     
    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final TestVolatile testVolatile = new TestVolatile();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<10000;j++)
                        testVolatile.increase();
                }
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){
//
//        }
        System.out.println(testVolatile.inc);
    }
}