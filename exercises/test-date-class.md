# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

### Question 1 

#### Partitionnement de l'espace d'entrée:
#### ensemble initial d'entrée:  
- Tester année bissextile pour le mois de fevrier
- Tester certaines dates valides (mois 30, mois 31)
- Tester les bornes min et max(day, month, year)

#### Les caractéristiques:

- Valeur de day, month et year :
    - day [1-31]
    - month[1-12]
    - year[1-9999] //borne choisi volontairement
    - day < 1 ou day > 31
    - month < 1 ou month > 12
    - year < 1 ou year > 9999
- Type d'année
    - année bissextile
    - année non bissextile


#### public static boolean isLeapYear(int year) { ... }
- (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    - (true && true) || false : year = 2024  
    - (true && false) || false : year = 1700
    - (true && false) || true : year = 1200
#### public static boolean isValidDate(int day, int month, int year) { ... }
- Date correcte : (29,2,2024)
- Jour incorrecte : {(0,1,2024), (32,12,2024)}
- Mois incorrecte : {(1,0,2024), (28,13,2024)}
- Year incorrecte : {(5,9,10000), (5,6,0)}
#### public Date(int day, int month, int year) { ... }
- IllegalArgumentException : ans : [1-9999] avec les mois {2,4,6,9,11},  jour : 31
- IllegalArgumentException : ans : {2023, 2025, 2022, 2021, 2019, 2018} mois : 2, jour : 29 
#### public int compareTo(Date other) { ... }
- NullPointerException : null
- Egality : {(29,2,2024), (29,2,2024)}
- PosterieurDay : {(30,1,2024)(29,1,2024)}
- PosterieurMonth : {(29,2,2024), (29,1,2024)}
- PosterieurYear : {(29,1,2025),(29,1,2024)}
- AnteriorDay : {(29,1,2024),(30,1,2024)}
- AnteriorMonth : {(29,1,2024),(29,2,2024)}
- AnteriorYear : {(29,1,2024),(29,1,2025)}
#### public Date nextDate() { ... }
- Normal test : {(29,1,2024), (30,1,2024)}
- Bissextile test : {(28,2,2024), (29,2,2024)}
- Next Month : {(28,2,2023), (1,3,2023)}
- Next Month : {(31,7,2024), (1,8,2024)}
- Next Year : {(31,12,2024), (1,1,2025)}
#### public Date previousDate { ... }
- Normal test : {(29,1,2024),(30,1,2024)}
- Normal test : {(28,2,2024), (29,2,,2024)}
- Bissextile test : {(29,2,2024), (1,3,2024)}
- No Bissextile test : {(28,2,2023), (1,3,2023)}
- Previous Month test : {(31,7,2024), (1,8,2024)}
- Previous year test : {(31,12,2024),(1,1,2025)}
#### Coverage
100% (1/1)	100% (7/7)	100% (47/47)	100% (42/42)
- RAS
    
### Statistics Pit

 Generated 53 mutations Killed 51 (96%)
 Ran 129 tests (2.43 tests per mutation)

 **Mutants vivants**:

5. changed conditional boundary → SURVIVED
6. changed conditional boundary → SURVIVED

#### Line : 
19  if(month < 1 || month > 12 || day < 1 || day > 31 || year < 1 || year > 9999) 

### Tests rajoutés:
```java
@Test
    public void testBoundaryTrue(){
        assertTrue(Date.isValidDate(31,12,9999));
        assertTrue(Date.isValidDate(1,1,1));
    }
```
