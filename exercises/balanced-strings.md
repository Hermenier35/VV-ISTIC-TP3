# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

### Question 1 

#### Partitionnement de l'espace d'entrée:
#### ensemble initial d'entrée: 
{"", "a", "(", "abc", "()[]{}", "([)", "([{}])", "[(])", "Object:{id:1, arg:[(1,2), (2,3)]}"} 

#### Les caractéristiques:

- Longueur de la chaîne :
    - Chaîne vide ou null.
    - Chaînes avec un seul caractère.
    - Chaînes de plusieurs caractères.
- Contenu de la chaîne :
    - Chaînes ne contenant aucun symbole {}, [], ().
    - Chaînes contenant uniquement des symboles équilibrés.
    - Chaînes contenant des symboles déséquilibrés.
    - Chaînes contenant des symboles bien imbriqués.
    - Chaine contenant des symboles mal imbriqués 
    - Chaînes avec des caractères mixtes (symboles + autre caractères).

#### Premier tests à partir de l'ensemble initial d'entrée

```java
 @Test
    public void testEmptyString() {
        String emptyString = "";
        assertTrue(StringUtils.isBalanced(emptyString));
    }

    @Test
    public void testNullString() {
        String nullString = null;
        assertTrue(StringUtils.isBalanced(nullString));
    }

    @Test
    public void testOneLetter() {
        String emptyString = "a";
        assertTrue(StringUtils.isBalanced(emptyString));
    }

    @Test
    public void testFailSimpleBracket() {
        String testString = "(";
        assertFalse(StringUtils.isBalanced(testString));
    }

    @Test
    public void testMultipleLetters() {
        String emptyString = "abc";
        assertTrue(StringUtils.isBalanced(emptyString));
    }

    @Test
    public void testMultipleBracket() {
        String testString = "()[]{}";
        assertTrue(StringUtils.isBalanced(testString));
    }

    @Test
    public void testFailBadMiddleOpenBracket() {
        String testString = "([)";
        assertFalse(StringUtils.isBalanced(testString));
    }

    @Test
    public void testSimpleMultipleImbrication() {
        String testString = "([{}])";
        assertTrue(StringUtils.isBalanced(testString));
    }

    @Test
    public void testFailsimpleBadImbrication() {
        String testString = "[(])";
        assertFalse(StringUtils.isBalanced(testString));
    }

    @Test
    public void testComplexeBrackets() {
        String testString = "Object:{id:1, arg:[(1,2), (2,3)]}";
        assertTrue(StringUtils.isBalanced(testString));
    }
```
**Taux de couverture** : 94%

**Test supplémentaire** : 
```java
 @Test
    public void testFailCloseSimpleBracket() {
        String testString = ")";
        assertFalse(StringUtils.isBalanced(testString));
    }
```
**Taux de couverture** : 100%

#### Predicat a plus de deux opérateurs booléens
```java

if (!symbolsFound.isEmpty() && 
    indexSymbol % 2 == 1 &&
    symbols.charAt(indexSymbol - 1) != symbolsFound.charAt(symbolsFound.length()-1))
```
Les tests effectués satisfont déjà la couverture total.

#### PIT : 

**Statistics**

- Generated 25 mutations Killed 25 (100%)
- Ran 37 tests (1.48 tests per mutation)

=> pas de nouveau test ajouté.