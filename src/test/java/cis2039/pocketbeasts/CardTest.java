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

    private Card card;

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

    @Test
    public void testGetId() {
        Card instance = card;
        String expResult = "BR";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetName() {
        Card instance = card;
        String expResult = "Barn Rat";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetManaCost() {
        Card instance = card;
        int expResult = 1;
        int result = instance.getManaCost();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAttack() {
        Card instance = card;
        int expResult = 1;
        int result = instance.getAttack();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetHealth() {
        Card instance = card;
        int expResult = 1;
        int result = instance.getHealth();
        assertEquals(expResult, result);
    }

    @Test
    public void testDamage() {
        int amount = 0;
        Card instance = card;
        instance.damage(amount);
    }

}
