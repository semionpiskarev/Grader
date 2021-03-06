package gradingTools.comp110s15.assignment2.testcases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class WelcomePromptTest extends BasicTestCase{

	public WelcomePromptTest() {
		super("WelcomePromptTestCase");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		boolean hasWelcome=false;
		try{
			RunningProject runningProject = RunningProjectUtils.runProject(project, 10,"200\n55");
			String output=runningProject.await().toLowerCase();
			if( output.contains("welcome") )
				hasWelcome=true;
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasWelcome) {
			return pass();
		}
		else {
			return fail("No Welcome or Thank You message");
		}
	}

}