package gradingTools.comp401f15.assignment3.testcases;

import java.util.List;
import java.util.Map;

import util.misc.Common;
import wrappers.framework.project.ProjectWrapper;
import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.ExecutionUtil;
import gradingTools.assignment1.FlexibleProgramRunner;


public class NumberTokenBeanTestCase extends AbstractTokenBeanTestCase {
	public  String classIdentifier() { return "Number";}
	protected String[] beanDescriptions() { return new String[] {null, "Number", ".*Number.*", ".*Number.*"};};
//	protected double missingClassPenalty() {return 1.0;};
//	protected double missingExpectedConstructorPenalty() {return 0.2;};
//	protected double missingNullConstructorPenalty() {return 0.0;}
//	protected double missingPropertyPenalty() {return 0.2;};
//	protected double getsNotSetsPenalty() {return 0.2;}
//	protected double incorrectDependentsPenalty(){return 0.3;}
	
	protected String input(){ return "006500";};
	protected Object value() {return 6500;};

    public NumberTokenBeanTestCase() {
        super("Number Token Bean Case");
    }
//    protected boolean isNullOrTrue (Boolean aBoolean) {
//    	return aBoolean == null || aBoolean;
//    }
//    protected boolean isNotNullAndTrue (Boolean aBoolean) {
//    	return aBoolean != null && aBoolean;
//    }
//    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//        try {
//        	Map<String, Object> retVal = ExecutionUtil.testBeanWithStringConstructor(getCheckable().getName(), project, beanDescriptions(), input(), "Input", input(), "Value", value());
//        	Boolean missingExpectedConstructor = (Boolean) retVal.get(ExecutionUtil.MISSING_CONSTRUCTOR);
//        	Boolean missingClass = (Boolean) retVal.get(ExecutionUtil.MISSING_CLASS);
//        	double penalty = 0.0;
//        	String aMessage = "";
//        	
//        	
//        	Boolean missingNullConstructor = null;
//        	if (missingClass != null) {
//        		return fail ("Class matching:" + Common.toString(beanDescriptions()) + " not found");
//        	} else {
//        		getCheckable().getRequirements().putUserObject(classIdentifier(), retVal.get(ExecutionUtil.CLASS_MATCHED));
//        	}
//        	if (missingExpectedConstructor != null) {
//            	 retVal = ExecutionUtil.testBeanWithNoConstructor(getCheckable().getName(), project, beanDescriptions(), "Input", input(), "Value", value());
//            	 penalty += missingExpectedConstructorPenalty();
//            	 aMessage += "Expected constructor missing ";
//
//        	}
//        	
//        	        	
//        	boolean getterReturnsSetter = isNullOrTrue((Boolean) retVal.get(ExecutionUtil.EXPECTED_EQUAL_ACTUAL));
//        	boolean correctDependent = isNullOrTrue((Boolean) retVal.get(ExecutionUtil.EXPECTED_EQUAL_ACTUAL));
//        	boolean missingProperty = isNotNullAndTrue((Boolean) retVal.get(ExecutionUtil.MISSING_PROPERTY));
//        	if (missingProperty) {
//        		penalty += missingPropertyPenalty();
//        		aMessage += "Property missing " ;
//        	}
//        	if (!correctDependent) {
//        		penalty += missingPropertyPenalty();
//        		aMessage += "Incorrect dependent property value ";
//        	}
//        	if (!getterReturnsSetter) {
//        		penalty += getsNotSetsPenalty();
//        		aMessage += "Get is not set  ";
//
//        	}
//        	double score = Math.max(0, 1 - penalty);
//        	if (score == 1.0)
//        		return pass();
//        	return partialPass(score, aMessage);
//        	
//        	
//        } catch (NotRunnableException e) {
//            throw new NotGradableException();
//        }
//    }
   
}

