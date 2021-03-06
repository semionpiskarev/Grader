package gradingTools.comp401f15.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment1.testCases.*;
import gradingTools.assignment2.testCases.BeanClassTestCase;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.comp401f15.assignment1.testcases.ImageEnclosedTestCase;
import gradingTools.comp401f15.assignment1.testcases.MinDeclaredMethodsInSameOrDifferentClassTestCase;
import gradingTools.comp401f15.assignment1.testcases.TerminateWithPeriodTestCase;
import gradingTools.comp401f15.assignment1.testcases.VariableSpacesTestCase;
import gradingTools.comp401f15.assignment2.testcases.NumberTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.QuotedStringTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.WordTokensTestCase;
import gradingTools.sharedTestCase.ClassDefinedlTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.MinCalledMethodsTestCase;
import gradingTools.sharedTestCase.MinDeclaredMethodsTestCase;


public class Assignment2Requirements extends FrameworkProjectRequirements {

    public Assignment2Requirements() {
    	addDueDate("09/02/2015 23:59:00", 1.05);
    	addDueDate("09/04/2015 23:59:00", 1);
    	addDueDate("09/09/2015 23:59:00", 0.9);
    	addDueDate("09/11/2015 23:59:00", 0.75);
    	 addFeature("Bean class", 20, new BeanClassTestCase());
         addFeature("Numbers and words", 20,
                 new NumberTokensTestCase(),
                 new WordTokensTestCase());
         addFeature("Quoted string", 30, new QuotedStringTokensTestCase());
    	
//        // Functionality
//        addFeature("Process & print tokens", 40,
//                new SingleTokenTestCase(),
//                new MultipleTokensTestCase()
//              //  new RemovePrecedingZerosTestCase()
//                );
//        addFeature("Sum and product", 10,
//                new SumTestCase(),
//                new ProductTestCase());
//        addFeature("Terminates with period", 10, new TerminateWithPeriodTestCase());

        // Style
//        addManualFeature("One loop on string", 20, new QuestionTestCase("Is there only one loop over the input string?", "Input string one loop test case"));
//        addFeature("Two declared methods", 3, new MinDeclaredMethodsInSameOrDifferentClassTestCase(2));
//        addFeature("One called method", 7, new MinDeclaredMethodsInSameOrDifferentClassTestCase(1));
//        addFeature("Variable spaces", 5, true, new VariableSpacesTestCase());
//        addFeature("Handle invalid chars", 5, true, new VariableSpacesTestCase());
        addFeature("Screenshots enclosed", 10, new ImageEnclosedTestCase());
//        addManualFeature("Use an extra class with iterator like interface", 10, true);


//        addManualFeature("Screenshots", 10, new QuestionTestCase("Screenshots included showing test data output?", "Screenshots testcase"));

        // TODO: Extra Credit
//        addManualFeature("Handle invalid chars", 5, true);
//        addManualFeature("No-array parser class", 10, true);
//        addManualFeature("Variable spaces", 5, true);
        addRestriction("Illegal import or call", 25, new IllegalImportOrCallTestCase());
        addRestriction("Single main.Assignment", 1, new ClassDefinedlTestCase("main.Assignment(.*)"));
        addRestriction("Scanner Bean Tagged ", 5, new ClassDefinedlTestCase("@ScannerBean"));


       
        

//        addManualFeature("Nice code", 10, true);

        // Restrictions
//        addRestriction("No .split allowed", -10, new NoSplitTestCase());

    }
}
