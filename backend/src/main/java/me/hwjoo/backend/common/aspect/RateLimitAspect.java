package me.hwjoo.backend.common.aspect;

import me.hwjoo.backend.common.annotation.RateLimited;
import me.hwjoo.backend.common.exception.TooManyRequestsException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final RedisTemplate<String, String> redisTemplate;

    @Around("@annotation(rateLimited)")
    public Object applyRateLimit(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        String key = "rate_limit:" + // 키 생성 로직
                joinPoint.getSignature().toShortString();

        Long count = redisTemplate.opsForValue().increment(key, 1);
        if (count != null && count == 1) {
            redisTemplate.expire(key,
                    rateLimited.duration(),
                    rateLimited.timeUnit());
        }

        if (count != null && count > rateLimited.requests()) {
            throw new TooManyRequestsException("요청 제한 초과");
        }

        return joinPoint.proceed();
    }
}

