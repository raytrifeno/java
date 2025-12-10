/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mypos.sales.service;

/**
 *
 * @author HP
 */

import com.mypos.sales.dao.SaleDao;
import com.mypos.sales.dao.SaleDaoImpl;
import com.mypos.sales.model.Sale;
import com.mypos.sales.model.SalesSummary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SalesService {
    private final SaleDao saleDao;

    public SalesService() {
        this.saleDao = new SaleDaoImpl();
    }

    public List<Sale> getAllSales() throws Exception {
        return saleDao.findAll();
    }

    public List<Sale> getSalesByInterval(String interval) throws Exception {
        if ("Today".equalsIgnoreCase(interval)) return saleDao.findToday();
        if ("Weekly".equalsIgnoreCase(interval)) return saleDao.findWeekly();
        if ("Monthly".equalsIgnoreCase(interval)) return saleDao.findMonthly();
        if ("All Time".equalsIgnoreCase(interval)) return saleDao.findAll();
        return saleDao.findAll();
    }

    public SalesSummary getSummaryBetween(LocalDateTime from, LocalDateTime to) throws Exception {
        return saleDao.getSummaryBetween(from, to);
    }

    public SalesSummary getSummaryByInterval(String interval) throws Exception {
        LocalDateTime from;
        LocalDateTime to = LocalDateTime.now();
        from = switch (interval.toLowerCase()) {
            case "today" -> LocalDate.now().atStartOfDay();
            case "weekly" -> LocalDate.now().minusDays(6).atStartOfDay();
            case "monthly" -> LocalDate.now().withDayOfMonth(1).atStartOfDay();
            default -> LocalDateTime.of(1970,1,1,0,0);
        };
        return getSummaryBetween(from, to);
    }
}
