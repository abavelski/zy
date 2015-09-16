package com.zy.app.invoice.main;


public interface VatService {

    double calculateVat(double totalExclVat);
    double calculateExclVat(double total);
}
