package framework.grading;

import framework.grading.testing.CheckResult;
import framework.gui.GradingWindow;
import framework.gui.SettingsWindow;
import framework.navigation.BulkDownloadFolder;
import framework.navigation.NotValidDownloadFolderException;
import framework.navigation.SakaiBulkDownloadFolder;
import framework.navigation.StudentFolder;
import framework.project.Project;
import scala.Option;

import java.util.List;

/**
 * This class is responsible for the entire grading pipeline, which is:
 *  1. Getting Download location w/ onyen information
 *  2. Getting student folders and the associated java project. For each of those
 *      a. Auto grade the project
 *      b. Get manual grading and verification for the project
 *      c. Log the results
 *
 * Like ProjectStepper
 */
public class GradingManager {

    private String projectName;
    private ProjectRequirements projectRequirements;

    // Settings that affect what to grade
    private String downloadPath;
    private String start;
    private String end;

    public GradingManager(String projectName, ProjectRequirements projectRequirements) {
        this.projectName = projectName;
        this.projectRequirements = projectRequirements;
    }

    public void run() {
        getGradingOptions();

        try {
            // Get the student folders, starting and ending with the specified onyens
            BulkDownloadFolder downloadFolder = new SakaiBulkDownloadFolder(downloadPath);
            List<StudentFolder> folders = downloadFolder.getStudentFolders(start, end);

            // Grade each one
            for (StudentFolder folder : folders) {
                Option<Project> project = folder.getProject(projectName);
                if (project.isDefined()) {

                    // Run all the checks/test cases
                    List<CheckResult> featureResults = projectRequirements.checkFeatures(project.get());
                    List<CheckResult> restrictionResults = projectRequirements.checkRestrictions(project.get());

                    // TODO: Do manual grading and verification
                    GradingWindow window = GradingWindow.create(projectRequirements, folder, project, featureResults, restrictionResults);
                    boolean continueGrading = window.awaitDone();
                    String comments = window.getComments();

                    // Log the results
                    logResults(folder, featureResults, restrictionResults, comments);

                    if (!continueGrading)
                        System.exit(0);
                } else {
                    // TODO: Gracefully handle absence of project
                    System.out.println("No project for: " + folder.getUserId());
                }
            }
            System.out.println("Done!");
        } catch (NotValidDownloadFolderException e) {
            System.out.println("Not a valid Sakai download folder");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void getGradingOptions() {
        SettingsWindow window = SettingsWindow.create();
        window.awaitBegin();
        downloadPath = window.getDownloadPath();
        start = window.getStart();
        end = window.getEnd();
    }

    private void logResults(StudentFolder folder, List<CheckResult> featureResults,
                            List<CheckResult> restrictionResults, String comments) {
        // TODO: Log the results
    }

}
