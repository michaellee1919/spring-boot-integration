package com.zimu.boot.integration.common.aop;

import com.zimu.boot.integration.common.context.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.zimu.boot.integration.common.annotation.Master) " +
            "&& (execution(* com.zimu.boot.integration.service..*.select*(..)) " +
            "|| execution(* com.zimu.boot.integration.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.zimu.boot.integration.common.annotation.Master) " +
            "|| execution(* com.zimu.boot.integration.service..*.insert*(..)) " +
            "|| execution(* com.zimu.boot.integration.service..*.add*(..)) " +
            "|| execution(* com.zimu.boot.integration.service..*.update*(..)) " +
            "|| execution(* com.zimu.boot.integration.service..*.edit*(..)) " +
            "|| execution(* com.zimu.boot.integration.service..*.delete*(..)) " +
            "|| execution(* com.zimu.boot.integration.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DataSourceContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DataSourceContextHolder.master();
    }
}
