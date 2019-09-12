package com.example.test.logger;

import com.example.test.dto.ContractDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final Log log = LogFactory.getLog(this.getClass());

    @AfterReturning("execution(* com.example.test.controller.TransactionController.addContract(..))")
    public void logInserMethod(JoinPoint joinPoint)throws Throwable{
        ContractDto dto = (ContractDto) joinPoint.getArgs()[0];
        String message = "Added transaction: " + "code-" + dto.getCode() + " status-" + dto.getStatus() +
                " contact-number-" + dto.getContactNumber();
        log.info(message);
    }

    @AfterReturning(value = "execution(* com.example.test.controller.TransactionController.*(..))", returning = "ret")
    public void logAfterMethod(JoinPoint joinPoint, Object ret) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String signatureName = joinPoint.getSignature().getName();
        String param = joinPoint.getArgs().toString();

        String message = "Completed " + className + "." + signatureName + "("
                + param + ")" + " Returned: " + ret;
        log.info(message) ;
    }
}
