package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void rahanLisaysToimii() {
        kortti.lataaRahaa(500);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void rahanOttoToimii() {
        boolean tulos = kortti.otaRahaa(500);
        assertEquals(true, tulos);
    }
    
    @Test
    public void rahanOttoSaldoOikein() {
        kortti.otaRahaa(500);
        assertEquals(500, kortti.saldo());
    }

    
    @Test
    public void rahanOttoEiToimiJosEiRittavastiRahaa() {
        boolean tulos = kortti.otaRahaa(1500);
        assertEquals(false, tulos);
    }
    
    @Test
    public void rahanOttoSaldoOikeinKunEiRiittavastiRahaa() {
        kortti.otaRahaa(1500);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void saldoOikeinToStringiaKayttaen() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
}
