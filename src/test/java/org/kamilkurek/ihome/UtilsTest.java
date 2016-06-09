package org.kamilkurek.ihome;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.testng.Assert.assertEquals;


/**
 * Created by kamil on 2016-06-05.
 */

@Test
public class UtilsTest {

    @DataProvider
    public Object[][] getDateStringDataProvider(){
        return new Object[][] {
                { LocalDateTime.of(2016, Month.JUNE,     1,  0,  0,  0),  "2016-06-01T00:00:00" },
                { LocalDateTime.of(2016, Month.JUNE,     5,  1,  10, 11), "2016-06-05T01:10:11" },
                { LocalDateTime.of(1900, Month.JANUARY,  15, 12, 30, 31), "1900-01-15T12:30:31" },
                { LocalDateTime.of(2100, Month.DECEMBER, 31, 15, 50, 51), "2100-12-31T15:50:51" },
                { LocalDateTime.of(2111, Month.JULY,     20, 20, 55, 56), "2111-07-20T20:55:56" },
                { LocalDateTime.of(3000, Month.MARCH,    25, 23, 59, 59), "3000-03-25T23:59:59" },
                { LocalDateTime.of(3000, Month.MARCH,    25, 23, 59, 59, 330), "3000-03-25T23:59:59" }
        };
    }

    @Test(dataProvider = "getDateStringDataProvider")
    public void testGetDateString(LocalDateTime localDateTime, String expected) throws Exception {
        String dateString = Utils.getDateString(localDateTime);

        assertEquals(dateString, expected);
    }

}