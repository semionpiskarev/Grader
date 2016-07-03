package gradingTools.comp110s15.assignment4.testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class ThankUser extends BasicTestCase {
	/*
	 * Program thanks user
	 */
	Pattern thanks = Pattern.compile(".*go.*heels.*");

	public ThankUser() {
		super("Test Case for Thanking User");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {

		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();

		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"DUKE\n");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);

		if (thanks.matcher(output1).find()) {
			return pass();
		}

		else if (output1.contains("thank")||output1.contains("thank you")) {
			return partialPass(0.5, "Thanked User but Didnt say (Go Heels)");
		}

		return fail("Completely Failed to Thank User");
	}
}