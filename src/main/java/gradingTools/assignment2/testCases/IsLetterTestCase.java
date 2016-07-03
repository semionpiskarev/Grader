package gradingTools.assignment2.testCases;

import java.lang.reflect.Method;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class IsLetterTestCase extends BasicTestCase {
    public IsLetterTestCase() {
        super("Custom isLetter method test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        for (ClassDescription description : project.getClassesManager().get().getClassDescriptions()) {
            try {
                Method m1 = description.getJavaClass().getMethod("isLetter", char.class);
                if (m1.getReturnType() == boolean.class)
                    return pass(autoGrade);
            } catch (NoSuchMethodException e) {
                // Move along
            }
        }
        return fail("No boolean isLetter(char) method found.", autoGrade);
    }
}

