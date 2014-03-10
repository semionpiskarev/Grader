package grader.settings;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AGraderSettingsModelAR implements ExecutableCommand {
	public Object execute(Object theFrame) {

		ObjectEditor.setAttribute(AGraderSettingsModel.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setPropertyAttribute(AGraderSettingsModel.class, "NavigationSetter", AttributeNames.VISIBLE, true);
		ObjectEditor.setPropertyAttribute(AGraderSettingsModel.class, "NavigationSetter", AttributeNames.ROW, 2);





		return null;
	}

}
