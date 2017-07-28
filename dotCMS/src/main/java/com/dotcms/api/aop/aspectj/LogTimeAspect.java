package com.dotcms.api.aop.aspectj;

import com.dotcms.api.aop.LogTimeMethodInterceptor;
import com.dotcms.api.aop.MethodInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import com.google.common.annotations.VisibleForTesting;

/**
 * This aspect handles the @{@link com.dotcms.util.LogTime} with AspectJ
 * @author jsanca
 */
@Aspect
public class LogTimeAspect {

    private final MethodInterceptor<Object> interceptor;

    public LogTimeAspect() {
        this(LogTimeMethodInterceptor.INSTANCE);
    }

    @VisibleForTesting
    public LogTimeAspect(MethodInterceptor<Object> interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * Aspect implementation for the {@link com.dotcms.util.LogTime} annotation
     *
     * <p>Try NOT to change the signature of this method, in order to keep
     * it backward compatible.
     *
     * @param point Joint point
     * @return The result of call
     * @throws Throwable If something goes wrong inside
     */
    @Around("execution(* *(..))"
            + " && @annotation(com.dotcms.util.LogTime)")
    public Object invoke(final ProceedingJoinPoint point) throws Throwable {

        return
                this.interceptor.invoke
                        (new AspectJDelegateMethodInvocation<Object>(point));
    } // invoke.
} // E:O:F:LogTimeAspect.
