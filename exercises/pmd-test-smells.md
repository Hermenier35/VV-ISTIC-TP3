# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you wi    ll find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

- JUnitTestContainsTooManyAsserts
    - category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts
- JUnitTestsShouldIncludeAssert
    - category/java/bestpractices.xml/JUnitTestsShouldIncludeAssert
- UnitTestShouldUseTestAnnotation
    - category/java/bestpractices.xml/UnitTestShouldUseTestAnnotation
- UnnecessaryBooleanAssertion
    - category/java/errorprone.xml/UnnecessaryBooleanAssertion

### Test de JUnitTestContainsTooManyAsserts:
**project** : Apache Commons Collections

**Found** :
```java
@Test
    @SuppressWarnings("unchecked")
    public void testSearch() {
        final ArrayList<E> list = makeObject();
        list.add((E) "First Item");
        list.add((E) "Last Item");
        assertEquals("First Item", list.get(0), "First item is 'First Item'");
        assertEquals("Last Item", list.get(1), "Last Item is 'Last Item'");
    }
```
**Correction**
```java
@Test
    @SuppressWarnings("unchecked")
    public void testSearch1() {
        final ArrayList<E> list = makeObject();
        list.add((E) "First Item");
        list.add((E) "Last Item");
        assertEquals("First Item", list.get(0), "First item is 'First Item'");
    }

@Test
    @SuppressWarnings("unchecked")
    public void testSearch2() {
        final ArrayList<E> list = makeObject();
        list.add((E) "First Item");
        list.add((E) "Last Item");
        assertEquals("Last Item", list.get(1), "Last Item is 'Last Item'");
    }
```
La différence avec la correction, c'est que nous pouvons savoir directement quel élément donne une erreur si il y en a une.
C'est pour celà qu'il est conseillé d'avoir uniquement un assert par methode de test.
