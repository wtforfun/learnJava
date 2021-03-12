package org.learn.synchronizedemo;

public class Producer extends Thread{
    //每次生产的数量
    private int num ;

    private String pname;

    public void setPname(String pname) {
        this.pname = pname;
    }

    //所属的仓库
    public AbstractStorage abstractStorage;

    public Producer(AbstractStorage abstractStorage){
        this.abstractStorage = abstractStorage;
    }

    public void setNum(int num){
        this.num = num;
    }

    // 线程run函数
    @Override
    public void run()
    {
        produce(num);
    }

    // 调用仓库Storage的生产函数
    public void produce(int num)
    {
        abstractStorage.produce(num);
    }
}