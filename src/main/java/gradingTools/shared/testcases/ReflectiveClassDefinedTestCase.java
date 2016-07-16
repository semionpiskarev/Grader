package gradingTools.shared.testcases;

import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 *
 * @author Andrew
 */
public class ReflectiveClassDefinedTestCase extends BasicTestCase {

    private final String myName, tag, nameRegex, tagRegex;
    
    public ReflectiveClassDefinedTestCase(String tag) {
        this(null, tag, ".*" + tag + ".*", ".*" + tag + ".*");
    }
    
    public ReflectiveClassDefinedTestCase(String tag, String nameRegex, String tagRegex) {
        super("Class tagged '" + tag + "'defined test case");
        this.myName = null;
        this.tag = tag;
        this.nameRegex = nameRegex;
        this.tagRegex = tagRegex;
    }
    
    public ReflectiveClassDefinedTestCase(String name, String tag, String nameRegex, String tagRegex) {
        super(name == null ? "Class tagged '" + tag + "'defined test case" : "Class '" + name + "' defined test case");
        this.myName = name;
        this.tag = tag;
        this.nameRegex = nameRegex;
        this.tagRegex = tagRegex;
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Set<Class> classList = BasicProjectIntrospection.findClasses(project, myName, tag, nameRegex, tagRegex);
        if (classList.isEmpty()) {
            if (myName != null) {
                return fail("Class '" + myName + "' not found");
            } else if (tag != null) {
                return fail("Class tagged '" + tag + "' not found");
            } else {
                return fail("Class not found");
            }
        } else {
            return pass();
        }
    }
    
}
