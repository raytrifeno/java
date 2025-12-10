/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mypos.sales.dao;

/**
 *
 * @author HP
 */

import com.mypos.sales.model.Sale;
import com.mypos.sales.model.SalesSummary;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleDao {
    List<Sale> findAll() throws Exception;
    List<Sale> findBetween(LocalDateTime from, LocalDateTime to) throws Exception;
    List<Sale> findToday() throws Exception;
    List<Sale> findWeekly() throws Exception;
    List<Sale> findMonthly() throws Exception;
    Sale findByReceipt(String receipt) throws Exception;
    SalesSummary getSummaryBetween(LocalDateTime from, LocalDateTime to) throws Exception;
}

