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
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        CancelSale = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        CheckoutPayment = new javax.swing.JButton();
        DeleteItem = new javax.swing.JButton();
        Receipt = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1030, 670));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("CASHIER");

        jTextField1.setBackground(new java.awt.Color(204, 204, 255));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setText("Input barcode..");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-qr-code-48.png"))); // NOI18N
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(this::jButton5ActionPerformed);

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

        CheckoutPayment.setBackground(new java.awt.Color(204, 255, 204));
        CheckoutPayment.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        CheckoutPayment.setForeground(new java.awt.Color(0, 153, 51));
        CheckoutPayment.setText("Checkout");
        CheckoutPayment.addActionListener(this::CheckoutPaymentActionPerformed);

        DeleteItem.setBackground(new java.awt.Color(255, 204, 204));
        DeleteItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteItem.setForeground(new java.awt.Color(204, 0, 0));
        DeleteItem.setText("Delete");
        DeleteItem.addActionListener(this::DeleteItemActionPerformed);

        Receipt.setBackground(new java.awt.Color(204, 204, 255));
        Receipt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Receipt.setForeground(new java.awt.Color(102, 102, 255));
        Receipt.setText("Receipt");
        Receipt.addActionListener(this::ReceiptActionPerformed);

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
                        .addComponent(CheckoutPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Receipt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1)
                    .addComponent(Receipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelSale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckoutPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       scanBarcode();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
        // Cari parent window (bisa JFrame atau JDialog)
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

            // Buat dialog AddProduct (modal = true)
            CheckoutPayment dialog = new CheckoutPayment((java.awt.Frame) parentWindow, true);

            // Tampilkan dialog
            dialog.setLocationRelativeTo(parentWindow); // Supaya muncul di tengah
            dialog.setVisible(true);
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

    private void ReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReceiptActionPerformed
    // === GETTER SEDERHANA ===
    public javax.swing.JTextField getTxtBarcode() {
        return jTextField1;
    }
    
    public javax.swing.JTable getTableCart() {
    return jTable2;
    }
    
    private void scanBarcode() {
    String barcode = jTextField1.getText().trim();
    
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
    jTextField1.setText("");
    jTextField1.requestFocus();
}

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelSale;
    private javax.swing.JButton CheckoutPayment;
    private javax.swing.JButton DeleteItem;
    private javax.swing.JButton Receipt;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
