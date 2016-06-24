package grader.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import edu.emory.mathcs.backport.java.util.Arrays;

public class AGradedClassProxyInvocationHandler implements InvocationHandler {
	Class actualClass;
	Object actualObject;
	public AGradedClassProxyInvocationHandler (Object anActualObject) {
		actualObject = anActualObject;
		actualClass = anActualObject.getClass();
	}

	@Override
	public Object invoke(Object proxy, Method aProxyMethod, Object[] args)
			throws Throwable {
		Method anActualMethod = ProjectIntrospection.findMethod(actualClass, aProxyMethod);
		if (anActualMethod == null) {
			return null;
		}
		int[] aPermutedIndices= ProjectIntrospection.getArgIndices(actualClass, aProxyMethod);
		if (aPermutedIndices != null) {
			Object[] anOriginalArgs = Arrays.copyOf(args, args.length);
			for (int i = 0; i < args.length; i++) {
				args[i] = anOriginalArgs[aPermutedIndices[i]];
			}
		}
		return ProjectExecution.timedInvoke(actualObject, anActualMethod, args);
	}

}
