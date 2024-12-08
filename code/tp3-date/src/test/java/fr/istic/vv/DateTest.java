package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    //methode isLeapYear

    @Test
    public void testIsLeapYear1() {
        int year = 2024;
        //(year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        //true && true || false
        assertTrue(Date.isLeapYear(year));
    }

    @Test
    public void testIsLeapYear2() {
        int year = 1700;
        //(year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        //true && false || false
        assertFalse(Date.isLeapYear(year));
    }

    @Test
    public void testIsLeapYear3() {
        int year = 1200;
        //(year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            //true && false || true
        assertTrue(Date.isLeapYear(year));
    }

    //methode isValidate

    @Test
    public void testFalseDay(){
        assertFalse(Date.isValidDate(0,1,2024));
        assertFalse(Date.isValidDate(32,12,2024));
    }

    @Test
    public void testFalseMonth(){
        assertFalse(Date.isValidDate(1,0,2024));
        assertFalse(Date.isValidDate(28,13,2024));
    }

    @Test
    public void testFalseYear(){
        assertFalse(Date.isValidDate(5,9,10000));
        assertFalse(Date.isValidDate(5,6,0));
    }

    @Test
    public void testBissextileYear1(){
        assertTrue(Date.isValidDate(29,2,2024));
    }

    @Test
    public void testBissextileYear2(){
        assertFalse(Date.isValidDate(29,2,2023));
    }

    //Rajout de tests pour Pit

    @Test
    public void testBoundaryTrue(){
        assertTrue(Date.isValidDate(31,12,9999));
        assertTrue(Date.isValidDate(1,1,1));
    }

    //test Constructor
    @Test
    public void testIllegalArgumentException(){
        int[] monthTab= {2, 4, 6, 9, 11};
        for(int year = 1; year<=9999; year++){
            for(int month = 0; month < 5; month++){
                final int YEAR = year;
                final int MONTH = monthTab[month];
                final int DAY = 31;
                assertThrows(IllegalArgumentException.class, () -> {new Date(DAY, MONTH, YEAR);});
            }
        }
    }

    @Test
    public void testIllegalArgumentExceptionNoBissextileYear(){
        int[] yearNoBissextile = {2023, 2025, 2022, 2021, 2019, 2018};
        for(int year = 0; year < 6; year++){
            final int YEAR = yearNoBissextile[year];
            final int MONTH = 2;
            final int DAY = 29;
            assertThrows(IllegalArgumentException.class, () -> {new Date(DAY, MONTH, YEAR);});
        }
    }

    //methode CompareTo

    @Test
    public void testNullOtherDate(){
        Date date = new Date(29,2,2024);
        Date nullDate = null;
                    assertThrows(NullPointerException.class, () -> {date.compareTo(nullDate);});
    }

    @Test
    public void testEgality(){
        Date date1 = new Date(29,2,2024);
        Date date2 = new Date(29,2,2024);
        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    public void testPosterieurByDay(){
        Date date1 = new Date(30,1,2024);
        Date date2 = new Date(29,1,2024);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    public void testPosterieurByMonth(){
        Date date1 = new Date(29,2,2024);
        Date date2 = new Date(29,1,2024);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    public void testPosterieurByYear(){
        Date date1 = new Date(29,1,2025);
        Date date2 = new Date(29,1,2024);
        assertTrue(date1.compareTo(date2) > 0);
    }

    @Test
    public void testAnterieurByDay(){
        Date date1 = new Date(29,1,2024);
        Date date2 = new Date(30,1,2024);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    public void testAnterieurByMonth(){
        Date date1 = new Date(29,1,2024);
        Date date2 = new Date(29,2,2024);
        assertTrue(date1.compareTo(date2) < 0);
    }

    @Test
    public void testAnterieurByYear(){
        Date date1 = new Date(29,1,2024);
        Date date2 = new Date(29,1,2025);
        assertTrue(date1.compareTo(date2) < 0);
    }

    //Test NextDate

    @Test
    public void testNextDate1(){
        Date date1 = new Date(29,1,2024);
        Date date2 = new Date(30,1,2024);
        assertEquals(0, date2.compareTo(date1.nextDate()));
    }

    @Test
    public void testNextDate2(){
        Date date1 = new Date(28,2,2024);
        Date date2 = new Date(29,2,2024);
        assertEquals(0, date2.compareTo(date1.nextDate()));
    }

    @Test
    public void testNextDate3(){
        Date date1 = new Date(28,2,2023);
        Date date2 = new Date(1,3,2023);
        assertEquals(0, date2.compareTo(date1.nextDate()));
    }

    @Test
    public void testNextDate4(){
        Date date1 = new Date(31,7,2024);
        Date date2 = new Date(1,8,2024);
        assertEquals(0, date2.compareTo(date1.nextDate()));
    }

    @Test
    public void testNextDate5(){
        Date date1 = new Date(31,12,2024);
        Date date2 = new Date(1,1,2025);
        assertEquals(0, date2.compareTo(date1.nextDate()));
    }

    //Test previousDate
    @Test
    public void testPreviousDate1(){
        Date date1 = new Date(29,1,2024);
        Date date2 = new Date(30,1,2024);
        assertEquals(0, date1.compareTo(date2.previousDate()));
    }

    @Test
    public void testPreviousDate2(){
        Date date1 = new Date(28,2,2024);
        Date date2 = new Date(29,2,2024);
        assertEquals(0, date1.compareTo(date2.previousDate()));
    }

    @Test
    public void testPreviousDate3(){
        Date date1 = new Date(28,2,2023);
        Date date2 = new Date(1,3,2023);
        assertEquals(0, date1.compareTo(date2.previousDate()));
    }

    @Test
    public void testPreviousDate4(){
        Date date1 = new Date(31,7,2024);
        Date date2 = new Date(1,8,2024);
        assertEquals(0, date1.compareTo(date2.previousDate()));
    }

    @Test
    public void testPreviousDate5(){
        Date date1 = new Date(31,12,2024);
        Date date2 = new Date(1,1,2025);
        assertEquals(0, date1.compareTo(date2.previousDate()));
    }

    @Test
    public void testPreviousDate6(){
        Date date1 = new Date(29,2,2024);
        Date date2 = new Date(1,3,2024);
        assertEquals(0, date1.compareTo(date2.previousDate()));
    }
}