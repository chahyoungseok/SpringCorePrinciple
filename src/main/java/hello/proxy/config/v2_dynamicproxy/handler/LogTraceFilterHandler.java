package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;
    private final String[] pattern;

    public LogTraceFilterHandler(Object target, LogTrace trace, String[] pattern) {
        this.target = target;
        this.trace = trace;
        this.pattern = pattern;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 특정 메서드 이름이 매칭되는경우에 로그를 찍지 않는다.
        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(pattern, methodName)){
            return method.invoke(target, args);
        }

        TraceStatus status = null;

        try {
            String message = method.getDeclaringClass().getSimpleName() + "," + method.getName() + "()";
            status = trace.begin(message);

            Object result = method.invoke(target, args);
            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
