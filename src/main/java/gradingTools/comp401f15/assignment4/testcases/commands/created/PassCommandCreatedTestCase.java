package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class PassCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "pass";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "pass";}
        
    public PassCommandCreatedTestCase() {
        super("Pass Command Token Created Test Case");
    }

    
}