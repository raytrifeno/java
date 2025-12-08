package com.mypos.app;

import com.mypos.cashier.view.CashierPanel;
import com.mypos.dashboard.view.DashboardPanel;
import com.mypos.product.view.ProductPanel;
import com.mypos.sales.view.SalesPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The main application window that holds all other panels.
 * It uses a CardLayout to switch between different views.
 */
public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    // Define unique string keys for each panel
    private final static String DASHBOARD_PANEL = "DASHBOARD";
    private final static String PRODUCT_PANEL = "PRODUCT";
    private final static String CASHIER_PANEL = "CASHIER";
    private final static String SALES_PANEL = "SALES";

    public MainFrame() {
        // --- 1. Main Frame Setup ---
        setTitle("Vendra POS - Minimarket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 700));

        // --- 2. Navigation Panel (WEST) ---
        JPanel navigationPanel = createNavigationPanel();
        add(navigationPanel, BorderLayout.WEST);

        // --- 3. Content Panel (CENTER) with CardLayout ---
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // --- 4. Add all view panels to the content panel ---
        contentPanel.add(new DashboardPanel(), DASHBOARD_PANEL);
        contentPanel.add(new ProductPanel(), PRODUCT_PANEL);
        contentPanel.add(new CashierPanel(), CASHIER_PANEL);
        contentPanel.add(new SalesPanel(), SALES_PANEL);
        
        add(contentPanel, BorderLayout.CENTER);

        // --- 5. Finalize Frame ---
        pack();
        setLocationRelativeTo(null); // Center the frame on screen
    }

    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setPreferredSize(new Dimension(200, 0));
        navPanel.setBackground(new Color(37, 51, 61));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("VENDRA POS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        navPanel.add(titleLabel);

        // Create and add navigation buttons
        navPanel.add(createNavButton("Dashboard", DASHBOARD_PANEL));
        navPanel.add(createNavButton("Products", PRODUCT_PANEL));
        navPanel.add(createNavButton("Cashier", CASHIER_PANEL));
        navPanel.add(createNavButton("Sales", SALES_PANEL));

        return navPanel;
    }

    private JButton createNavButton(String text, String panelKey) {
        JButton button = new JButton(text);
        
        // Style the button
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(47, 61, 71));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        // Add the action listener to switch panels
        button.addActionListener((ActionEvent e) -> {
            cardLayout.show(contentPanel, panelKey);
        });

        return button;
    }
}
