package com.example.mylittleannotation.aop;

import com.example.mylittleannotation.api.controller.aes.AES;
import com.example.mylittleannotation.api.controller.aes.AESProperty;
import com.example.mylittleannotation.api.controller.dto.request.RoleTokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final AES aes;

    @Pointcut("@annotation(com.example.mylittleannotation.aop.annotation.RoleTokenAuthorization)")
    public void roleTokenAuthorization() {}

    @Pointcut("execution(* com.example.mylittleannotation.service..*.*(..))")
    public void allService() {}

    @Around("roleTokenAuthorization() && allService() && args(roleToken, ..)")
    public Object decodingToken(ProceedingJoinPoint joinPoint, RoleTokenRequest roleToken) throws Throwable {
        if (roleToken == null) {
            throw new IllegalArgumentException("RoleToken 가 파라미터 첫번째 위치에 없거나 파라미터에 존재하지 않습니다.");
        }
        roleToken.setDecoding(aes.decrypt(roleToken.getRoleToken(), AESProperty.securityKey));

        return joinPoint.proceed();
    }
}
