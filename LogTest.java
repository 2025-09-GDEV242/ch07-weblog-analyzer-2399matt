

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LogTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LogTest
{
    private LogAnalyzer logAnalyzer;
    /**
     * Default constructor for test class LogTest
     */
    public LogTest()
    {
        logAnalyzer = new LogAnalyzer("test.txt");
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        logAnalyzer.analyzeHourlyData();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        logAnalyzer.getReader().reset();
    }
    
    /**
     * Busiest hour test on test.txt
     */
    @Test
    public void busiestShouldBeOne() {
        int hour = logAnalyzer.busiestHour();
        assertEquals(1, hour);
    }
    
    /**
     * Quitest hour test on test.txt
     */
    @Test
    public void quietestShouldBeTwo() {
        int hour = logAnalyzer.quitestHour();
        assertEquals(2, hour);
    }
    
    /**
     * Busiest two hour test. 0 = 2, 1 = 4. Method should return 0 (starting hour).
     */
    @Test
    public void busiestTwoHourRange() {
        int twoHourMax = logAnalyzer.getBusiestTwoHours();
        assertEquals(0, twoHourMax);
        
    }
    /**
     * Busiest day test on test.txt
     */
    @Test
    public void busiestDayShouldBeOne() {
        int day = logAnalyzer.busiestDay();
        assertEquals(1, day);
    }
}