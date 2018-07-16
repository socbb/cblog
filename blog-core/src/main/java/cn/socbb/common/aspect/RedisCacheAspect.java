package cn.socbb.common.aspect;

import cn.socbb.common.annotation.RedisCache;
import cn.socbb.common.utils.CacheKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Redis业务层数据缓存
 *
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut(value = "@annotation(cn.socbb.common.annotation.RedisCache)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        // 获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        if (!currentMethod.isAnnotationPresent(RedisCache.class)) {
            throw new RuntimeException("未指定RedisChache注解！");
        }
        StringBuilder key = new StringBuilder(point.getTarget().getClass().getName());
        // 获取操作名称
        RedisCache cache = currentMethod.getAnnotation(RedisCache.class);
        if (cache.flush()) {
            log.info("{}*-清空缓存", key);
            Set<String> keys = redisTemplate.keys(key.toString() + "*");
            redisTemplate.delete(keys);
            log.info("Clear all the cached query result from redis");
            return point.proceed();
        }
        key.append(".").append(currentMethod.getName());
        key.append(CacheKeyUtil.getMethodParamsKey(point.getArgs())).append(cache.key());
        String realKey = key.toString();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(realKey);
        if (hasKey) {
            try {
                log.info("{}从缓存中获取数据", realKey);
                return redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                log.error("从缓存中获取数据失败！", e);
            }
        }
        // 先执行业务
        Object result = point.proceed();
        // 向Redis中添加数据，有效时间是30天
        redisTemplate.opsForValue().set(realKey, result, cache.expire(), cache.unit());
        log.info("Put query result to redis");
        log.info("{}从数据库中获取数据", realKey);
        return result;
    }
}
