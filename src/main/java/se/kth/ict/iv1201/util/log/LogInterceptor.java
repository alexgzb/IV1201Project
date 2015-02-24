package se.kth.ict.iv1201.util.log;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Logs all method calls including parameters, return values and exceptions.
 * 
 */
@Interceptor
@Log
public class LogInterceptor implements Serializable {

    private static final Logger LOGGER = Logger.getLogger("se.kth.ict.iv1201");
    private static final Level INFO = Level.INFO;
    private static final Level FINE = Level.FINE;

    
    
    /**
     * Logs entry to and exit from a method. Also logs parameter values, return
     * value and exceptions.
     *
     * @param ctx
     * @return The value returned by the intercepted method.
     * @throws Exception
     */
    @AroundInvoke
    public Object logInvocation(InvocationContext ctx) throws Exception {
        Method targetMethod = logEntry(ctx);
        Object returnValue = null;
        try {
            returnValue = ctx.proceed();
        } catch (Exception e) {
            logException(targetMethod, e);
        }
        logExit(targetMethod, returnValue);
        return returnValue;
    }

    private void logExit(Method targetMethod, Object returnValue) {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName(),
            targetMethod.getName()};
        LOGGER.log(FINE, "Call to {0}.{1} completed", args);
        if (!isVoid(targetMethod)) {
            LOGGER.log(FINE, "    Return value: {0}", returnValue);
        }
    }

    private void logException(Method targetMethod, Exception e) throws Exception {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName(),
            targetMethod.getName(), e.getClass().getCanonicalName()};
        LOGGER.log(FINE, "{0}.{1} threw {2}", args);
        e.printStackTrace();
        throw (e);
    }

    private Method logEntry(InvocationContext ctx) {
        Method targetMethod = ctx.getMethod();
        Object[] params = ctx.getParameters();
        LOGGER.log(FINE, "Entering: {0}", targetMethod.toGenericString());
        LOGGER.log(FINE, "    Parameters: {0}", Arrays.toString(params));
        return targetMethod;
    }

    private boolean isVoid(Method targetMethod) {
        return targetMethod.getReturnType().isAssignableFrom(Void.TYPE);
    }
}
