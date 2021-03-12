package org.learn;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 注解的方式自定义切面
 */
@Aspect
@Component
public class MyAspect {

    /*
    * 指定切点
    * */
    @Pointcut("execution(* org.learn.AopWorker.work(..))")
    public void aopWorkerCut(){}

    @Pointcut("@annotation(org.learn.MyAspectAnno)")
    public void aopRestCut(){}

    /**
     * 前置通知
     */
    @Before("aopWorkerCut()")
    public void before(){
        System.out.println("前置通知....");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="aopWorkerCut()",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }


    /**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("aopRestCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前....");
        Object obj= (Object) joinPoint.proceed();
        System.out.println("环绕通知后....");
        return obj;
    }

    /**
     * 抛出通知
     * @param e
     */
    @AfterThrowing(value="aopWorkerCut()",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("出现异常:msg="+e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value="aopWorkerCut()")
    public void after(){
        System.out.println("最终通知....");
    }
}
