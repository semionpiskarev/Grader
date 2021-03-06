package grader.trace.assignment_data;

import grader.trace.file.FileInfo;
import grader.trace.file.SerializableFileInfo;

public class FeatureGradeFileLoaded extends FileInfo {

	public FeatureGradeFileLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static FeatureGradeFileLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "feature grade file loaded: " + aFileName;
		FeatureGradeFileLoaded retVal = new FeatureGradeFileLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
