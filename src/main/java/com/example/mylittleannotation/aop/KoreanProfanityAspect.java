package com.example.mylittleannotation.aop;

import com.example.mylittleannotation.aop.profanity.KoreanProfanityFilter;
import com.example.mylittleannotation.api.controller.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class KoreanProfanityAspect {

    private final KoreanProfanityFilter koreanProfanityFilter;

    @Pointcut("@annotation(com.example.mylittleannotation.aop.annotation.ProfanityFilter)")
    public void responseType() {}

    @Pointcut("execution(* com.example.mylittleannotation.service..*.*(..))")
    public void allService() {}

    @Around("responseType() && allService() && args(request, ..)")
    public Object responseType(ProceedingJoinPoint joinPoint, UserRequest request) throws Throwable {
        if (request == null) {
            throw new IllegalArgumentException("UserRequest 가 파라미터 첫번째 위치에 없습니다.");
        }

        request.setProfanityFilter(
                koreanProfanityFilter.filterProfanity(request.getName()),
                koreanProfanityFilter.filterProfanity(request.getDescription())
        );

        return joinPoint.proceed();
    }
}
