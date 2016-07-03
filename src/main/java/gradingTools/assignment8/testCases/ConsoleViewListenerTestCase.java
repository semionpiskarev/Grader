package gradingTools.assignment8.testCases;

import java.beans.PropertyChangeListener;

import scala.Option;
import tools.classFinder.ManualClassFinder;
import tools.classFinder.RootTagFinder;
import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/29/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleViewListenerTestCase extends BasicTestCase {

    public ConsoleViewListenerTestCase() {
        super("Console view implements PropertyChangeListener test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Make sure we can get the class description
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        Option<ClassDescription> classDescription = new RootTagFinder(project).findClass("ConsoleSceneView");
        if (classDescription.isEmpty()) {
            if (autoGrade)
                throw new NotAutomatableException();
            classDescription = ManualClassFinder.find(project, "ConsoleSceneView");
        }

        if (PropertyChangeListener.class.isAssignableFrom(classDescription.get().getJavaClass()))
            return pass();
        else
            return fail("Console scene view should implement PropertyChangeListener");
    }
}

