package cs.exam;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.junit.rules.ExpectedException;

public class AppSystemTest {
    public static ProjectSystem projectSystem = ProjectSystem.getInstance();
    @Test
    public void testCaseRegister() throws PalindromeException{
        var newUser = new User("pepe","alla");
        projectSystem.register("pepe", newUser);
        Assert.assertEquals(projectSystem.currentUser.getUsername(), "pepe");
        Assert.assertEquals(projectSystem.currentUser.getPassword(), "alla");
        Assert.assertEquals(projectSystem.currentUser, newUser);
    }

    /* @Test
    public void testCasePalindrome() throws PalindromeException {
        var newUser = new User("pepe","pepe");
        ExpectedException expectedExp = ExpectedException.none();
        expectedExp.expect(PalindromeException.class);
        expectedExp.expectMessage("Elige una contrasena palindroma");
        Assert.assertNull(newUser.getUsername());
        Assert.assertNull(newUser.getPassword());
    } */

    @Test
    public void testCaseLogout() throws PalindromeException{
        var newUser = new User("lica","apa");
        projectSystem.register("lica", newUser);
        Assert.assertEquals(projectSystem.currentUser, newUser);
        projectSystem.logout();
        Assert.assertEquals(projectSystem.currentUser, null);
    }

    @Test(threadPoolSize = 500, invocationCount = 10)
    public void testCaseAddSensor() throws PalindromeException{
        var newUser = new User("marta","apa");
        projectSystem.register("marta", newUser);
        projectSystem.addSensor("1");
        var current = projectSystem.sensors.get("1");
        Assert.assertEquals(current.getID(), "1");
        projectSystem.logout();
    }

    @Test(invocationCount = 10)
    public void testCaseRemoveSensor() throws PalindromeException{
        projectSystem.login("marta", "apa");
        projectSystem.removeSensor("1");
        var current = projectSystem.sensors.get("1");
        Assert.assertNull(current);
    }

    @Test(invocationCount = 10)
    public void testCaseGetSensor() throws PalindromeException{
        projectSystem.login("lica", "apa");
        var start = System.currentTimeMillis();
        var foundSensor = projectSystem.printSensor("1");
        var end = System.currentTimeMillis();
        var expectedSensor = projectSystem.sensors.get("1");
        Assert.assertEquals(foundSensor, expectedSensor);
        var final_time = end - start;
        Assert.assertTrue(final_time <= 2000);
    }
}
