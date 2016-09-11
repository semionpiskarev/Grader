package grail;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"wait"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})
public class Wait extends AToken implements Token {
	String value = null;
	public Wait(String aString){
		input = aString;
		value = input.toLowerCase();
	}
	public String getValue(){
		return value;
	}
	public void setInput(String aString){
		input = aString;
		value = input.toLowerCase();
	}
}