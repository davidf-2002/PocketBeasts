/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cis2039.pocketbeasts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author David Foomeni
 */
public class CardTest {

    private ICard card;

    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        card = new Beast("BR", "Barn Rat", 1, 1, 1);
    }
    
    @After
    public void tearDown() {
        card = null;
    }

    /**
     * Test of getId method, of class Card.
     */
    @Test
    public void testGetId() {
        ICard instance = card;
        String expResult = "BR";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Card.
     */
    @Test
    public void testGetName() {
        ICard instance = card;
        String expResult = "Barn Rat";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getManaCost method, of class Card.
     */
    @Test
    public void testGetManaCost() {
        ICard instance = card;
        int expResult = 1;
        int result = instance.getManaCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttack method, of class Card.
     */
    @Test
    public void testGetAttack() {
        ICard instance = card;
        int expResult = 1;
        int result = instance.getAttack();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHealth method, of class Card.
     */
    @Test
    public void testGetHealth() {
        ICard instance = card;
        int expResult = 1;
        int result = instance.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of damage method, of class Card.
     */
    @Test
    public void testDamage() {
        int amount = 0;
        ICard instance = card;
        instance.damage(amount);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        ICard instance = card;
        String expResult = "Barn Rat";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Card.
     */
//    @Test
//    public void testCompareTo() {
//        System.out.println("compareTo");
//        Card o = null;
//        Card instance = null;
//        int expResult = 0;
//        int result = instance.compareTo(o);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
