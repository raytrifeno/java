package com.mypos.sales.service;

import com.mypos.sales.dao.SalesDao; // Import our corrected DAO
import com.mypos.sales.model.Sale;   // Import the correct model
import com.mypos.sales.model.SalesSummary;
import java.math.BigDecimal;
import java.util.List;

public class SalesService {
    
    // Use our corrected SalesDao
    private final SalesDao salesDao;

    public SalesService() {
        this.salesDao = new SalesDao();
    }

    /**
     * This method now acts as a direct pass-through to the DAO,
     * which contains all the necessary logic for filtering by date and joining tables.
     * @param interval The time period ("Today", "Weekly", etc.)
     * @return A list of Sale objects, now including the cashier's name.
     * @throws Exception
     */
    public List<Sale> getSalesByInterval(String interval) throws Exception {
        return salesDao.getSalesByInterval(interval);
    }

    /**
     * This method calculates a summary from a list of sales.
     * It is more efficient than running a separate DB query.
     * @param interval The time period to get the summary for.
     * @return A SalesSummary object.
     * @throws Exception
     */
    public SalesSummary getSummaryByInterval(String interval) throws Exception {
        List<Sale> sales = getSalesByInterval(interval);
        
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalPaid = BigDecimal.ZERO;
        BigDecimal totalChange = BigDecimal.ZERO;
        
        for (Sale sale : sales) {
            totalAmount = totalAmount.add(sale.getTotalAmount());
            totalPaid = totalPaid.add(sale.getAmountPaid());
            totalChange = totalChange.add(sale.getChangeAmount());
        }
        
        return new SalesSummary(sales.size(), totalAmount, totalPaid, totalChange);
    }
}