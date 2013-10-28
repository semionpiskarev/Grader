package joshTest.wrappers;

import grader.checkers.ACheckResult;
import grader.checkers.AnAbstractFeatureChecker;
import grader.checkers.CheckResult;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/23/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ErrorHandlingFeatureChecker extends AnAbstractFeatureChecker {

    protected abstract CheckResult doCheck() throws Exception;

    @Override
    public final CheckResult check() {
        try {
            return doCheck();
        } catch (Exception e) {
            return fail();
        }
    }

    private CheckResult fail() {
        return fail("");
    }

    private CheckResult fail(String preMsg) {
        String input = JOptionPane.showInputDialog(null, preMsg + "Unable to auto grade. Please give a grade by hand:", "Auto grading failed", JOptionPane.QUESTION_MESSAGE);
        if (input == null)
            input = "0";
        try {
            Double score = Double.parseDouble(input);
            CheckResult result = new ACheckResult();
            result.setScore(score);
            if (score < feature.getMax()) {
                String notes = JOptionPane.showInputDialog(null, "Please leave a reason for receiving less than full credit.", "Notes", JOptionPane.QUESTION_MESSAGE);
                result.setLog(Arrays.asList(notes));
            }
            return result;
        } catch (Exception e) {
            return fail("Invalid score.\n");
        }
    }
}
