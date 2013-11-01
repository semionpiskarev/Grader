package joshTest.assignment7;

import framework.grading.ProjectRequirements;
import framework.gui.SettingsWindow;
import framework.utils.GradingEnvironment;
import framework.wrappers.ProjectStepperDisplayerWrapper;
import gradingTools.assignment7.Assignment7ProjectRequirements;
import joshTest.ManualFeedbackIgnorer;
import framework.wrappers.ProjectDatabaseWrapper;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/23/13
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment7Grader {

    public static void main(String[] args) {
//        String bulkFolder = "/Users/josh/Downloads/a7";
//        String dataFolder = "/Users/josh/Documents/School/Fall 2013/COMP401/Grader2/GraderData";

        SettingsWindow window = SettingsWindow.create();
        window.awaitBegin();

        System.out.println("Loading projects...");
        long start = System.currentTimeMillis();
        ProjectDatabaseWrapper database = new ProjectDatabaseWrapper();
        System.out.println("Done. Time taken: " + ((System.currentTimeMillis() - start) / 1000) + " seconds");

        GradingEnvironment.get().setAssignmentName("Assignment7");

        // Create the project requirements/features
        ProjectRequirements requirements = new Assignment7ProjectRequirements();
        database.addProjectRequirements(requirements);

        database.setProjectStepperDisplayer(new ProjectStepperDisplayerWrapper());

        database.nonBlockingRunProjectsInteractively();
    }
}
