package com.example.mylittleannotation.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class OptimizeHitsAspect {

    public static final String REDIS_HIT_KEY = "REDIS_HIT_KEY";

    private final RedisTemplate<String, Long> redisTemplate;

    @Pointcut("@annotation(com.example.mylittleannotation.aop.annotation.OptimizeHits)")
    public void optimizeHitsFilter() {}

    @Pointcut("execution(* com.example.mylittleannotation.service..*.*(..))")
    public void allService() {}

    @Around("optimizeHitsFilter() && allService()")
    public Object optimizeHits(ProceedingJoinPoint joinPoint) throws Throwable {
        Long articleId = (Long) joinPoint.getArgs()[0];

        HashOperations<String, Long, Integer> operations = redisTemplate.opsForHash();

        Integer originHit = operations.get("REDIS_HIT_KEY", articleId);
        if(originHit == null) {
            originHit = 0;
        }

        operations.put("REDIS_HIT_KEY", articleId, originHit + 1);

        return joinPoint.proceed();
    }
}
