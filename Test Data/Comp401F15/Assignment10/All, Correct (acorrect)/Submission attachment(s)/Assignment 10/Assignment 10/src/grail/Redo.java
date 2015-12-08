package grail;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"redo"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input","Value"}) 
@EditablePropertyNames({"Input"})
public class Redo  extends WordTokenImpl implements TokenStringValue{
	public Redo(String input){
		super(input);
	}
}