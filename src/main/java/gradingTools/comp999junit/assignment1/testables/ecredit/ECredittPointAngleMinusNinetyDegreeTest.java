package gradingTools.comp999junit.assignment1.testables.ecredit;


//import org.junit.Test;
import org.junit.Test;


public class ECredittPointAngleMinusNinetyDegreeTest extends ECreditPointAngleTest {
	
	public  ECredittPointAngleMinusNinetyDegreeTest() {
		checkStructure = false;
	}
	@Test
	public void test() {	
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}
	

}
