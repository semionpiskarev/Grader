package gradingTools.assignment7.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/14/13
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocatableTagTestCase extends BasicTestCase {
    public LocatableTagTestCase() {
        super("Locatable tag test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (BasicProjectIntrospection.findClass(project, "Locatable") != null) {
            return pass();
        } else {
            return fail("Can't find class tagged Locatable");
        }
//        if (project.getClassesManager().isEmpty())
//            throw new NotGradableException();
//
//        // Look for a class with the "Locatable" tag and that isn't inheriting from something else
//        Option<ClassDescription> classDescription = new RootTagFinder(project).findClass("Locatable");
//        if (classDescription.isDefined())
//            return pass(autoGrade);
//        return fail("No superclass tagged \"Locatable\"", autoGrade);
    }
}

