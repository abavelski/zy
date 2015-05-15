package com.zy.app.invoice.main;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class VatServiceImplTest  {


    VatServiceImpl vatService;

    @Test
    public void testCalculateVat() {
        vatService = new VatServiceImpl();
        vatService.setVatPct(25);
        assertThat(vatService.calculateVat(40d), equalTo(10d));
    }
}