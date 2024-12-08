# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### Question 1
Cette assertion échoue en raison des imprécisions d'arrondi dans la représentation des nombres à virgule flottante.
#### Solution
il suffit de rajouter une tolérance:
```java
 @org.junit.Test
    public void testFloatingPointComparison() {
        assertEquals(1.2, 3 * 0.4, 1e-9);
    }
```

### Question 2
#### assertEquals()
Compare le contenu de valeur entre deux objets.
#### assertSame()
Verifie si deux objets pointent vers la même référence en mémoire.
#### Scénarios:
```java
    private ArrayList<Integer> liste1;
    private ArrayList<Integer> liste2;
    private ArrayList<Integer> liste3;


    @BeforeEach
    public void setUp() {
        liste1 = new ArrayList<>();
        liste1.add(1);
        liste2 = new ArrayList<>();
        liste2.addAll(liste1);
        liste3 = liste1;
    }

    @Test
    public void testTest1() {
        assertEquals(liste1, liste3); // Ok
    }

    @Test
    public void testTest2() {
        assertEquals(liste1, liste2);// Ok
    }

    @Test
    public void testTest3() {
        assertSame(liste1, liste2); // Fail ici car ne pointent pas sur la même référence
    }

    @Test
    public void testTest4() {
        assertSame(liste1, liste3); // Ok
    }
```
### Question 3

#### Autres utilisations de fail() en JUnit :
- Assurer qu'une exception est levée.
```java
@Test
    public void testExceptionNotThrown() {
        try {
            ArrayList<String> list;
            list.add("test");
            fail("Expected exception was not thrown");  
        } catch (NullException e) {
            assertNotNull(e);
        }
    }
```

- Exception innatendue:
```java
@Test
public void unexpectedException() {
    try {
        safeMethod();
    } catch (Exception e) {
        fail("Unexpected exception was thrown");
    }
}
```


- Vérifier des conditions de tests.
```java
@Test
public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Result cannot exceed integer max value");
    }
}
```

- ReturningBefore.
```java
@Test
public void returnBefore() {
    int value = randomInteger();
    for (int i = 0; i < 5; i++) {
        if ((i + value) % 2 == 0) {
            return;
        }
    }
    fail("Should have returned before");
}
```
### Question 4
#### Les avantages avec assertThrows(): 
- Le code testé est isolé dans une lambda ou une méthode, ce qui permet de spécifier exactement quelle ligne ou quel bloc doit lever l'exception et donc d'avoir également une meilleur lisibilité et isolation.
- On peut tester le contenu de l'exception comme un message d'erreur.
- Combinaison avec d'autres assertions	car permet de combiner plusieurs assertions dans un même test.








