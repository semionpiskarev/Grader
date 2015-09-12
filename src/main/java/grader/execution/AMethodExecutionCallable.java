package grader.execution;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class AMethodExecutionCallable implements Callable{
	Method method;
	Object object;
	Object[] args;
	public AMethodExecutionCallable(Object anObject, Method aMethod, Object[] anArgs) {
		method = aMethod;
		object = anObject;
		args = anArgs;
	}
	@Override
	public Object call() throws Exception {
		System.out.println ("calling method: " + method + " " + args);
		Object retVal = method.invoke(object, args);
		System.out.println ("called method: " + method + " " + args);

		return retVal;
	}
	

}