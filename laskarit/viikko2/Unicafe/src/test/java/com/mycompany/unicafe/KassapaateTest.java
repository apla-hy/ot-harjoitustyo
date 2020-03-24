/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lauri
 */
public class KassapaateTest {
    
    Kassapaate paate;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
     public void luodunKassapaatteenRahamaaraOikein() {
         assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void luodunKassapaatteenMyytyjenEdullistenLounaidenMaaraOikein() {
         assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
     
    @Test
    public void luodunKassapaatteenMyytyjenMaukkaidenLounaidenMaaraOikein() {
         assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    
}