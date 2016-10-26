package gradingTools.sharedTestCase.checkstyle;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.sakai.project.SakaiProject;
import gradingTools.shared.testcases.MethodExecutionTest;

import java.util.List;


public class CheckstyleMethodDefinedTestCase extends CheckStyleTestCase {
	public static final String WARNING_NAME = "expectedDeclaredSignature";

	 protected String method;
//	 protected String typeTag;
//	 protected String typeName;
	 public CheckstyleMethodDefinedTestCase(String aType, String aMethod) {
	        super(aType, aType + "!" + aMethod);
	        typeTag = aType;
	        method = aMethod;
	        
	  }
	 @Override
	 protected String typeTag() {
			return typeTag;
		}
	 @Override
		protected String warningName() {
			return WARNING_NAME;
		}
		
	@Override
	public String regexLineFilter() {
		
//		return "(.*)Signature(.*)" + method + "(.*)" + type + "(.*)";
//		return "(.*)Signature(.*)" + method + "(.*)" + getActualType() + "(.*)";
		return  MethodExecutionTest.toRegex("In type " + getActualType() +", missing declared signature: " + method);


	}
	 public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//	     Class aClass = IntrospectionUtil.getOrFindClass(project, this, typeTag); 
//	     if (aClass == null) {
//	    	 return fail("Type " + typeTag + " not defined, cannot check method:" + typeTag);
//	     }
//	     typeName = aClass.getSimpleName();
		 TestCaseResult aResult = super.test(project, autoGrade);
//		 TestCaseResult aResult = fail("foo");
	        return aResult;

	        
	        
	        
	 }

//	 protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
////	    	int aNumFailedInstances = aFailedLines.size();
////	        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
////	        String aNotes = failMessageSpecifier() + " in " + aNumFailedInstances + " out of " + aTotalClassCount + " classes ";
////	        return partialPass((aTotalClassCount - aNumFailedInstances)/aTotalClassCount, aNotes, autoGrade);  
//	    	int aNumMatchedInstances = aMatchedLines.size();
//	    	if (aNumMatchedInstances == 0 && failOnMatch() || aNumMatchedInstances == 1 && !failOnMatch())
//	    		return pass();
//	    	return computeResult(aProject, aCheckStyleLines, aMatchedLines, autoGrade);
//	    	
//	    }

//	@Override
//	public String failMessageSpecifier(List<String> aFailedLines) {
//		// TODO Auto-generated method stub
//		return "Method call matching " + method + " not defined by " + getActualType();
//	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
		 return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
//		 if (aResult.getPercentage() != 1.0) {
//			 if (aProject.getEntryPoints() == null || aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT) == null)
//				 return aResult;
//			 String aMainClassUsed = aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT);
//			 if (aMainClassUsed.contains("main.") || aMainClassUsed.contains("Main.") ) {
//				 return partialPass(0.5, aResult.getNotes() + " but main package defined or main package has wrong case");
//			 }
//		 }
//		 return aResult;
	    	
	}

}
