/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mypos.util;
import com.mypos.cashier.model.CartItem;
import java.awt.*;
import java.awt.print.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author raysu
 */
public class StrukPrinter implements Printable {
    private final List<CartItem> items;
    private final String receiptNo;
    private final String cashierName;
    private final BigDecimal total;
    private final BigDecimal pay;
    private final BigDecimal change;
    
    // Format mata uang
    private final DecimalFormat currency = new DecimalFormat("#,###");

    public StrukPrinter(String receiptNo, String cashierName, List<CartItem> items, BigDecimal total, BigDecimal pay, BigDecimal change) {
        this.receiptNo = receiptNo;
        this.cashierName = cashierName;
        this.items = items;
        this.total = total;
        this.pay = pay;
        this.change = change;
    }

    // Method utama untuk memanggil printer
    public void printStruk() {
        PrinterJob job = PrinterJob.getPrinterJob();
        
        // Buat format kertas 58mm
        // 1 inch = 72 points. 
        // 58mm = skitar 2.28 inch = ~164 points.
        // Kita set tinggi kertas 'dinamis' (panjang ke bawah) misal 2000 point agar tidak terpotong
        PageFormat pf = job.defaultPage();
        Paper paper = pf.getPaper();
        
        double width = 164d; // 58mm dalam points
        double height = 9999d; // Anggap roll paper panjang sekali
        double margin = 5d; 

        paper.setSize(width, height);
        paper.setImageableArea(margin, margin, width - (margin * 2), height - (margin * 2));
        pf.setPaper(paper);

        job.setPrintable(this, pf);

        boolean doPrint = job.printDialog(); // Tampilkan dialog pilih printer
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Setting Font (PENTING: Thermal printer butuh font kecil & Monospaced)
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 8)); 
        
        int y = 10; // Posisi Y awal
        int yShift = 10; // Jarak antar baris

        // --- HEADER ---
        g2d.drawString("VENDRA POS STORE", 10, y); y += yShift;
        g2d.drawString("Jl. Maju Mundur No. 1", 10, y); y += yShift;
        g2d.drawString("Telp: 0812-3456-7890", 10, y); y += yShift;
        g2d.drawString("--------------------------------", 0, y); y += yShift;

        // --- INFO TRANSAKSI ---
        g2d.drawString("No   : " + receiptNo, 0, y); y += yShift;
        g2d.drawString("Kasir: " + cashierName, 0, y); y += yShift;
        g2d.drawString("Tgl  : " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()), 0, y); y += yShift;
        g2d.drawString("--------------------------------", 0, y); y += yShift;

        // --- ITEMS ---
        for (CartItem item : items) {
            // Nama Barang (Potong jika kepanjangan biar tidak merusak layout 58mm)
            String name = item.getProductName();
            if (name.length() > 20) name = name.substring(0, 20) + "..";
            
            g2d.drawString(name, 0, y); y += yShift;
            
            // Format: 2 x 10.000       20.000
            String qtyPrice = item.getQuantity() + " x " + currency.format(item.getPrice());
            String subtotal = currency.format(item.getSubtotal());
            
            // Logika sederhana untuk rata kanan subtotal (manual spacing)
            // Di printer thermal asli, biasanya kita hitung panjang string
            g2d.drawString(qtyPrice, 0, y); 
            g2d.drawString(subtotal, 110, y); // 110 adalah koordinat X rata kanan (kira-kira)
            y += yShift;
        }

        g2d.drawString("--------------------------------", 0, y); y += yShift;

        // --- TOTAL ---
        g2d.setFont(new Font("Monospaced", Font.BOLD, 8)); 
        g2d.drawString("TOTAL   :", 0, y); 
        g2d.drawString(currency.format(total), 110, y); y += yShift;
        
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 8)); 
        g2d.drawString("BAYAR   :", 0, y); 
        g2d.drawString(currency.format(pay), 110, y); y += yShift;
        
        g2d.drawString("KEMBALI :", 0, y); 
        g2d.drawString(currency.format(change), 110, y); y += yShift;

        y += yShift;
        g2d.drawString("--------------------------------", 0, y); y += yShift;
        g2d.drawString("     TERIMA KASIH     ", 10, y); y += yShift;
        g2d.drawString("  BARANG YANG SUDAH DIBELI  ", 0, y); y += yShift;
        g2d.drawString("   TIDAK DAPAT DITUKAR   ", 5, y); y += yShift;

        return PAGE_EXISTS;
    }
}
