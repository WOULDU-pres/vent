package me.hwjoo.backend.common.config;

import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    private final RedissonClient redissonClient;

    public RateLimiterConfig(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Bean(name = "apiRateLimiter")
    public RRateLimiter apiRateLimiter() {
        RRateLimiter limiter = redissonClient.getRateLimiter("globalRateLimiter");
        // 1000 requests per second
        limiter.trySetRate(RateType.OVERALL, 1000, 1, RateIntervalUnit.SECONDS);
        return limiter;
    }

    @Bean(name = "userRateLimiter")
    public RRateLimiter userRateLimiter() {
        RRateLimiter limiter = redissonClient.getRateLimiter("userRateLimiter");
        // 10 requests per minute per user
        limiter.trySetRate(RateType.PER_CLIENT, 10, 1, RateIntervalUnit.MINUTES);
        return limiter;
    }
}