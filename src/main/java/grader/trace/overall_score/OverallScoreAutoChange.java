package grader.trace.overall_score;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallScoreAutoChange extends OverallScoreInfo {
//String overallScore;



public OverallScoreAutoChange(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static OverallScoreAutoChange newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			double aScore,
			Object aFinder) {
		String aMessage = "Overall Score Changed to:" + aScore;
		OverallScoreAutoChange retVal = new OverallScoreAutoChange(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
