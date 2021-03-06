package framework.grading.testing;

import java.util.List;

import util.misc.Common;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.project.Project;
import gradingTools.assignment1.FlexibleProgramRunner;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class OutputAndErrorCheckingTestCase extends BasicTestCase {
	public enum OutputErrorStatus {
		CORRECT_OUTPUT_NO_ERRORS,
		CORRECT_OUTPUT_ERRORS,
		INCORRECT_OUTPUT_NO_ERRORS,
		INCORRECT_OUTPUT_ERRORS
	}
    public OutputAndErrorCheckingTestCase(String aMessage) {
        super(aMessage);
    }


    protected boolean isValidOutputInDifferentLines(List<String> anOutput, String[] anExpectedStrings){
   	 for (String anExpectedString: anExpectedStrings) {
//      		if (!anOutput.contains(anExpectedString))
//          	if (!anOutput.match(anExpectedString))
            if (!matchesConsuming(anOutput, anExpectedString))
      			return false;
      	}
      	return true;
      }
   protected boolean isValidOutputInSameOrDifferentLines(List<String> anOutput, String[] anExpectedStrings){
	 for (String anExpectedString: anExpectedStrings) {
//   		if (!anOutput.contains(anExpectedString))
//       	if (!anOutput.match(anExpectedString))
         if (!matchesNonConsuming(anOutput, anExpectedString))
   			return false;
   	}
   	return true;
   }
   protected boolean isValidOutput(List<String> anOutput, String[] anExpectedStrings){
	   return isValidOutputInSameOrDifferentLines(anOutput, anExpectedStrings);
   }
   
   protected int indexOf (List<String> anOutputs, String anExpectedString) {
	   for (int index = 0; index < anOutputs.size(); index++) {
		   String anOutput = anOutputs.get(index);
		   if (anOutput.matches(anExpectedString))
			   return index;
	   }
	   return -1;
	   
   }
   protected boolean matchesNonConsuming (List<String> anOutputs, String anExpectedString) {
	   return indexOf(anOutputs, anExpectedString) != -1;
	   
   }
   protected boolean matchesConsuming (List<String> anOutputs, String anExpectedString) {
	   int index = indexOf(anOutputs, anExpectedString);
	   if (index  == -1)
		   return false;
	   anOutputs.remove(index);
	   return true;	   
   }

   protected boolean isValidOutput(String anOutput, String[] anExpectedStrings){
	   	 List anOutputLines = Common.arrayToArrayList(anOutput.split("\n"));
	   			 

   		return isValidOutput(anOutputLines, anExpectedStrings);
   }
   protected  boolean hasError(String anError){
    	return !anError.isEmpty();
    }
    
   protected  OutputErrorStatus test (Project project, String anInput, String[] anExpectedStrings, boolean autoGrade) {
    	RunningProject runner = project.launch(anInput, 1);
        String output = runner.await();
        boolean validOutput = isValidOutput(output, anExpectedStrings);
        String error = runner.getErrorOutput();
        boolean hasError = hasError(error);
        if (validOutput && !hasError) {
        	return OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS;
        }
        if (validOutput && hasError) {
        	return OutputErrorStatus.CORRECT_OUTPUT_ERRORS;
        }
        if (!validOutput && !hasError) {
        	return OutputErrorStatus.INCORRECT_OUTPUT_NO_ERRORS;
        }
        return OutputErrorStatus.INCORRECT_OUTPUT_ERRORS;        
    }
   
   public static void main (String[] args) {
	   System.out.println ("30".matches(".*30"));
	   System.out.println (" 30".matches(".*30"));
	   System.out.println ("\nfoo is 30 \n".matches(".*30.*"));
   }
    
}

