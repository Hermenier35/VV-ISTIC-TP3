package fr.istic.vv;

class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;
    private static final int tabDayInMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year))
            throw new IllegalArgumentException("Invalid date");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if(month < 1 || month > 12 || day < 1 || day > 31 || year < 1 || year > 9999)
            return false;
        int dayEndMonth = tabDayInMonth[month];
        if(month==2 && isLeapYear(year))
            dayEndMonth = 29;
        return day <= dayEndMonth;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = this.day;
        int nextMonth = this.month;
        int nextYear = this.year;

        if (isValidDate(day + 1, month, year)) {
            nextDay++;
        } else if (month < 12) {
            nextDay = 1;
            nextMonth++;
        } else {
            nextDay = 1;
            nextMonth = 1;
            nextYear++;
        }

        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int prevDay = this.day;
        int prevMonth = this.month;
        int prevYear = this.year;

        if (isValidDate(day - 1, month, year)) {
            prevDay--;
        } else if (month > 1) {
            prevMonth--;
            prevDay = tabDayInMonth[prevMonth];
            if (prevMonth == 2 && isLeapYear(year)) {
                prevDay = 29;
            }
        } else {
            prevDay = 31;
            prevMonth = 12;
            prevYear--;
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    public int compareTo(Date other) {
        if(other == null)
            throw  new NullPointerException();
        if (this.year != other.year) {
            return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }
}