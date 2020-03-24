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
    
    @Test
    public void saldoOikeinEdullisenLounaanOsto() {
        paate.syoEdullisesti(500);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void saldoOikeinMaukkaanLounaanOsto() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinEdullisenLounaanOsto() {
        int vaihtoraha = paate.syoEdullisesti(500);
        assertEquals(260, vaihtoraha);
    }
    
    @Test
    public void vaihtorahaOikeinMaukkaanLounaanOsto() {
        int vaihtoraha = paate.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);
    }
    
    @Test
    public void myytyjenMaaraOikeinEdullisenLounaanOsto() {
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenMaaraOikeinMaukkaanLounaanOsto() {
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void saldoOikeinEdullisenLounaanOstoLiianVahanRahaa() {
        paate.syoEdullisesti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void saldoOikeinMaukkaanLounaanOstoLiianVahanRahaa() {
        paate.syoMaukkaasti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void vaihtoraahaOikeinEdullisenLounaanOstoLiianVahanRahaa() {
        int vaihtoraha = paate.syoEdullisesti(100);
        assertEquals(100, vaihtoraha);
    }

    @Test
    public void vaihtorahaOikeinMaukkaanLounaanOstoLiianVahanRahaa() {
        int vaihtoraha = paate.syoMaukkaasti(100);
        assertEquals(100, vaihtoraha);
    }
    
    @Test
    public void maaraOikeinEdullisenLounaanOstoLiianVahanRahaa() {
        paate.syoEdullisesti(100);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maaraOikeinMaukkaanLounaanOstoLiianVahanRahaa() {
        paate.syoMaukkaasti(100);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void saldoOikeinEdullisenLounaanOstoKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        boolean tulos = paate.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
        assertEquals(true, tulos);
    }
    
    @Test
    public void saldoOikeinMaukkaanLounaanOstoKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        boolean tulos = paate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
        assertEquals(true, tulos);
    }
    
    @Test
    public void maaraOikeinEdullisenLounaanOstoKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maaraOikeinMaukkaanLounaanOstoKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void saldoPalauteJaMaaraOikeinEdullisenLounaanOstoKortillaLiianVahanRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        boolean tulos = paate.syoEdullisesti(kortti);
        assertEquals(false, tulos);
        assertEquals(100, kortti.saldo());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void saldoPalauteJaMaaraOikeinMaukkaanLounaanOstoKortillaLiianVahanRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        boolean tulos = paate.syoMaukkaasti(kortti);
        assertEquals(false, tulos);
        assertEquals(100, kortti.saldo());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanSaldoOikeinEdullisenLounaanOstoKortillaLiianVahanRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        boolean tulos = paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
        
    @Test
    public void kassanSaldoOikeinMaukkaanLounaanOstoKortillaLiianVahanRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        boolean tulos = paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void saldotOikeinKortinLataus() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, paate.kassassaRahaa());
        assertEquals(1000,kortti.saldo());
    }

    @Test
    public void saldotOikeinKortinLatausNegatiivinenLataussumma() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0,kortti.saldo());
    }

    
}