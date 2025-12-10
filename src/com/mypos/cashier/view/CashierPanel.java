package com.mypos.cashier.view;

public class CashierPanel extends javax.swing.JPanel {

    public CashierPanel() {
        initComponents();
        setupTableModel();   // <- tambahkan baris ini
    }
    
    private void setupTableModel() {
    javax.swing.table.DefaultTableModel model =
            new javax.swing.table.DefaultTableModel(
                    new Object[][]{},                 // tidak ada data awal
                    new String[]{
                            "Id Product", "Name Product", "Price", "Quantity", "Line Total"
                    }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // hanya kolom Quantity yang bisa diedit
                    return column == 3;
                }
            };
    jTable2.setModel(model);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        InputBarcode = new javax.swing.JTextField();
        ScanBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        CancelSale = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        CheckoutSale = new javax.swing.JButton();
        DeleteItem = new javax.swing.JButton();
        ReceiptNotes = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1030, 670));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("CASHIER");

        InputBarcode.setBackground(new java.awt.Color(204, 204, 255));
        InputBarcode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        InputBarcode.setForeground(new java.awt.Color(102, 102, 102));
        InputBarcode.setText("Input barcode..");
        InputBarcode.addActionListener(this::InputBarcodeActionPerformed);

        ScanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-qr-code-48.png"))); // NOI18N
        ScanBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ScanBtn.addActionListener(this::ScanBtnActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-24.png"))); // NOI18N
        jLabel4.setText("ADMIN");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        CancelSale.setBackground(new java.awt.Color(255, 255, 204));
        CancelSale.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        CancelSale.setForeground(new java.awt.Color(102, 102, 0));
        CancelSale.setText("Cancel");
        CancelSale.addActionListener(this::CancelSaleActionPerformed);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Product", "Name Product", "Price", "Quantity", "Line Total"
            }
        ));
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setMinimumSize(new java.awt.Dimension(800, 600));
        jTable2.setPreferredSize(new java.awt.Dimension(800, 600));
        jTable2.setShowGrid(false);
        jScrollPane2.setViewportView(jTable2);

        CheckoutSale.setBackground(new java.awt.Color(204, 255, 204));
        CheckoutSale.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        CheckoutSale.setForeground(new java.awt.Color(0, 153, 51));
        CheckoutSale.setText("Checkout");
        CheckoutSale.addActionListener(this::CheckoutPaymentActionPerformed);

        DeleteItem.setBackground(new java.awt.Color(255, 204, 204));
        DeleteItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteItem.setForeground(new java.awt.Color(204, 0, 0));
        DeleteItem.setText("Delete");
        DeleteItem.addActionListener(this::DeleteItemActionPerformed);

        ReceiptNotes.setBackground(new java.awt.Color(204, 204, 255));
        ReceiptNotes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReceiptNotes.setForeground(new java.awt.Color(102, 102, 255));
        ReceiptNotes.setText("Receipt");
        ReceiptNotes.addActionListener(this::ReceiptNotesActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CancelSale, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(CheckoutSale, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(InputBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ScanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ReceiptNotes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ScanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputBarcode)
                    .addComponent(ReceiptNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelSale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckoutSale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void InputBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBarcodeActionPerformed
       String barcode = InputBarcode.getText();

        // 1. Validasi input kosong
        if (barcode == null || barcode.trim().isEmpty()) {
            return; 
        }

        // 2. Ambil produk dari database
        com.mypos.product.dao.ProductDao productDao = new com.mypos.product.dao.ProductDao();
        // Pastikan Anda sudah membuat method getProductByCode di ProductDao
        com.mypos.product.model.Product product = productDao.getProductByCode(barcode);

        // Handle jika produk tidak ditemukan
        if (product == null) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Product with barcode '" + barcode + "' not found.", 
                "Warning", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            
            InputBarcode.setText("");
            InputBarcode.requestFocus();
            return;
        }

        // 3. Cek apakah produk sudah ada di keranjang (Tabel)
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable2.getModel();
        boolean productExistsInCart = false;
        int existingRow = -1;

        // Loop tabel untuk mencari duplikat
        for (int i = 0; i < model.getRowCount(); i++) {
            String codeInTable = (String) model.getValueAt(i, 0); // Kolom 0 = ID Product
            if (codeInTable.equals(product.getCode())) {
                productExistsInCart = true;
                existingRow = i;
                break;
            }
        }

        // 4. Update quantity atau tambah baris baru
        if (productExistsInCart) {
            // Increment quantity
            int currentQuantity = (int) model.getValueAt(existingRow, 3); // Kolom 3 = Qty
            int newQuantity = currentQuantity + 1;
            model.setValueAt(newQuantity, existingRow, 3);

            // Update Line Total (Harga x Qty Baru)
            // Pastikan model menyimpan harga sebagai BigDecimal
            java.math.BigDecimal price = (java.math.BigDecimal) model.getValueAt(existingRow, 2); 
            java.math.BigDecimal newLineTotal = price.multiply(new java.math.BigDecimal(newQuantity));
            model.setValueAt(newLineTotal, existingRow, 4); // Kolom 4 = Total

        } else {
            // Tambah baris baru jika belum ada
            int initialQuantity = 1;
            model.addRow(new Object[]{
                product.getCode(),
                product.getName(),
                product.getPrice(),
                initialQuantity,
                product.getPrice() // Total awal sama dengan harga satuan
            });
        }

        // 5. Reset input field agar siap scan lagi
        InputBarcode.setText("");
        InputBarcode.requestFocus();
        
        // (Opsional) Di sini nanti Anda bisa panggil fungsi updateGrandTotal() 
        // untuk menghitung total belanja keseluruhan di label bawah.
    
    }//GEN-LAST:event_InputBarcodeActionPerformed

    private void ScanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScanBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScanBtnActionPerformed

    private void CancelSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelSaleActionPerformed
        // Cari parent window (bisa JFrame atau JDialog)
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

            // Buat dialog AddProduct (modal = true)
            CancelSales dialog = new CancelSales((java.awt.Frame) parentWindow, true);

            // Tampilkan dialog
            dialog.setLocationRelativeTo(parentWindow); // Supaya muncul di tengah
            dialog.setVisible(true);
    }//GEN-LAST:event_CancelSaleActionPerformed
    
    private void CheckoutPaymentActionPerformed(java.awt.event.ActionEvent evt) {                                           
// 1. Ambil model tabel
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable2.getModel();

        // 2. Cek apakah keranjang kosong
        if (model.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Keranjang belanja kosong!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Hitung Total Belanja & Siapkan Data Barang (CartItem)
        java.math.BigDecimal grandTotal = java.math.BigDecimal.ZERO;
        java.util.List<com.mypos.cashier.model.CartItem> items = new java.util.ArrayList<>();
        
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                // Ambil data dari setiap kolom
                String code = (String) model.getValueAt(i, 0);
                String name = (String) model.getValueAt(i, 1);
                java.math.BigDecimal price = (java.math.BigDecimal) model.getValueAt(i, 2);
                int qty = (int) model.getValueAt(i, 3);
                java.math.BigDecimal subtotal = (java.math.BigDecimal) model.getValueAt(i, 4);
                
                // Tambahkan ke total
                grandTotal = grandTotal.add(subtotal);
                
                // Masukkan ke list items untuk dikirim ke DAO
                items.add(new com.mypos.cashier.model.CartItem(code, name, price, qty, subtotal));
            }
        } catch (Exception e) {
            System.err.println("Error reading table data: " + e.getMessage());
            return;
        }

        // 4. Buka Dialog Pembayaran
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        CheckoutPayment dialog = new CheckoutPayment((java.awt.Frame) parentWindow, true, grandTotal);
        dialog.setVisible(true); 

        // 5. Jika Pembayaran Sukses, SIMPAN KE DATABASE
        if (dialog.isPaymentSuccess()) {
            
            // Panggil DAO
            com.mypos.cashier.dao.TransactionDao dao = new com.mypos.cashier.dao.TransactionDao();
            java.math.BigDecimal paidAmount = grandTotal; // Ganti ini nanti dengan input asli
            java.math.BigDecimal changeAmount = java.math.BigDecimal.ZERO; // Ganti ini nanti
    
        boolean saved = dao.saveTransaction(1, grandTotal, dialog.getPaidAmount(), dialog.getChangeAmount(), items);
    
        if (saved) {
        javax.swing.JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan!", "Sukses", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        model.setRowCount(0); 
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Gagal menyimpan ke database! Cek Console.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
   }
    }
    
    private void DeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteItemActionPerformed
         int selectedRow = jTable2.getSelectedRow();

    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Pilih data yang ingin dihapus dulu.",
                "Tidak ada baris terpilih",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) jTable2.getModel();

    model.removeRow(selectedRow);
    }//GEN-LAST:event_DeleteItemActionPerformed

    private void ReceiptNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiptNotesActionPerformed
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

            // Buat dialog AddProduct (modal = true)
            ReceiptSale dialog = new ReceiptSale((java.awt.Frame) parentWindow, true);

            // Tampilkan dialog
            dialog.setLocationRelativeTo(parentWindow); // Supaya muncul di tengah
            dialog.setVisible(true);
    }//GEN-LAST:event_ReceiptNotesActionPerformed
    // === GETTER SEDERHANA ===
    public javax.swing.JTextField getTxtBarcode() {
        return InputBarcode;
    }
    
    public javax.swing.JTable getTableCart() {
    return jTable2;
    }
    
    private void scanBarcode() {
    String barcode = InputBarcode.getText().trim();
    
    if (barcode.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, 
                "Barcode belum diisi!", 
                "Warning", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // ============================================
    //  DI SINI kamu nanti panggil ProductDao (belum kita buat)
    //  Untuk sekarang kita buat dummy product agar tombol bekerja
    // ============================================

    // contoh dummy (sehingga tidak error):
    String productName = "Sample Product";
    double price = 10000;

    // masukkan ke tabel
    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) jTable2.getModel();

    Object[] row = {
            barcode,        // Id Product
            productName,    // Name Product
            price,          // Price
            1,              // Quantity default
            price * 1       // Line Total
    };

    model.addRow(row);

    // kosongkan input
    InputBarcode.setText("");
    InputBarcode.requestFocus();
}

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelSale;
    private javax.swing.JButton CheckoutSale;
    private javax.swing.JButton DeleteItem;
    private javax.swing.JTextField InputBarcode;
    private javax.swing.JButton ReceiptNotes;
    private javax.swing.JButton ScanBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
