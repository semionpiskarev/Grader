package gradingTools.comp401f15.assignment4.testcases.commands;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import static gradingTools.comp401f15.assignment2.testcases.BasicTokenDefinitions.wordTokenDescription;
import java.util.HashSet;

/**
 *
 * @author Andrew
 */
public class CommandClassesExtendWordTokenTestCase extends BasicTestCase {
    
    private static final int CLASS_COUNT = 13;
    //private static final String[] wordTokenDescription = new String[] {null, "Word", ".*Word.*", ".*Word.*"};
    /*private static final String[][] tokenDescriptions = new String[][]{{null, "call", ".*call.*", ".*call.*"},
                                                                       {null, "define", ".*define.*", ".*define.*"},
                                                                       {null, "move", ".*move.*", ".*move.*"},
                                                                       {null, "proceedAll", ".*proceedAll.*", ".*proceedAll.*"},
                                                                       {null, "redo", ".*redo.*", ".*redo.*"},
                                                                       {null, "repeat", ".*repeat.*", ".*repeat.*"},
                                                                       {null, "rotateLeftArm", ".*rotateLeftArm.*", ".*rotateLeftArm.*"},
                                                                       {null, "rotateRightArm", ".*rotateRightArm.*", ".*rotateRightArm.*"},
                                                                       {null, "say", ".*say.*", ".*say.*"},
                                                                       {null, "sleep", ".*sleep.*", ".*sleep.*"},
                                                                       {null, "thread", ".*thread.*", ".*thread.*"},
                                                                       {null, "undo", ".*undo.*", ".*undo.*"},
                                                                       {null, "wait", ".*wait.*", ".*wait.*"}};*/

    public CommandClassesExtendWordTokenTestCase() {
        super("Command Classes Extend Word Token Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        int extend = 0;
        Class wordClass = IntrospectionUtil.findClass(project, 
                                                    wordTokenDescription[0],
                                                    wordTokenDescription[1],
                                                    wordTokenDescription[2],
                                                    wordTokenDescription[3]);
        
        for(String[] description : CommandTokenDefinitions.baseCommandTokens()) {
            Class clazz = IntrospectionUtil.findClass(project, 
                                                    description[0],
                                                    description[1],
                                                    description[2],
                                                    description[3]);
            if (clazz.isAssignableFrom(wordClass)) {
                extend ++;
            }
        }
        
        if (extend == CLASS_COUNT) {
            return pass();
        } else if (extend == 0) {
            return fail("No command classes extend word token");
        } else {
            return partialPass(((double)extend)/CLASS_COUNT, "Only " + extend + " of 13 command tokens extend word token");
        }
    }

    
}
