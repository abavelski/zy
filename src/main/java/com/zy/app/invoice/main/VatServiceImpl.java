package com.zy.app.invoice.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class VatServiceImpl implements VatService {

    @Value("${config.vatPct}")
    private Integer vatPct;

    public void setVatPct(Integer vatPct) {
        this.vatPct = vatPct;
    }

    @Override
    public double calculateVat(double totalExclVat) {
        return totalExclVat*vatPct/100;
    }

    @Override
    public double calculateExclVat(double total) {
        return total/(1+vatPct/100);
    }
}
