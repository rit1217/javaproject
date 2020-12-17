package com.cart;

import com.local.ProductData;
import com.local.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ConfirmOrderView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel zipCodeLabel;
    private JLabel provinceLabel;
    private JLabel addressLabel;
    private JLabel paymentTypeLabel;
    private JLabel totalLabel;
    private JLabel deliveryTypeLabel;


    public ConfirmOrderView( ) {
        initialize();
    }

    public void initialize() {
        int posx=100;
        int posy=100;

        setTitle("Confirm Order");
        setResizable(false);
        setBounds( posx, posy, 650, 361);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JScrollPane panel = new JScrollPane();
        panel.setBounds(0, 0, 650, 317);
        getContentPane().add(panel);
        panel.setLayout(null);



        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        confirmButton.setBounds(534, 240, 110, 29);
        panel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(534, 282, 110, 29);
        panel.add(cancelButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 39, 638, 148);
        panel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product Name", "Price", "Quantity", "Total"
                })
        {
            Class[] types = new Class [] {
                    String.class, Float.class, Integer.class, Float.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                //				productsTableMouseClicked(evt);
            }
        });
        tableModel = (DefaultTableModel) table.getModel();
        table.setFillsViewportHeight(true);
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        JLabel receiptlabel = new JLabel("Confirm Order");
        receiptlabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        receiptlabel.setBounds(16, 6, 200, 34);
        panel.add(receiptlabel);

        paymentTypeLabel = new JLabel("Payment type :   ");
        paymentTypeLabel.setBounds(6, 199, 350, 16);
        panel.add(paymentTypeLabel);

        deliveryTypeLabel = new JLabel("Delivery type :   ");
        deliveryTypeLabel.setBounds(6, 243, 200, 16);
        panel.add(deliveryTypeLabel);

        totalLabel = new JLabel("Total cost :   ");
        totalLabel.setBounds(6, 287, 200, 16);
        panel.add(totalLabel);

        addressLabel = new JLabel("Address :    ");
        addressLabel.setBounds(350, 199, 350, 16);
        panel.add(addressLabel);

        provinceLabel = new JLabel("Province :  ");
        provinceLabel.setBounds(350, 243, 200, 16);
        panel.add(provinceLabel);

        zipCodeLabel = new JLabel("Postal Code :    ");
        zipCodeLabel.setBounds(350, 287, 200, 16);
        panel.add(zipCodeLabel);

    }

    public void updateOrder(ArrayList<ProductData> inCartList, String deliveryType, String paymentType, UserData user) {
        tableModel.setDataVector(
                new Object[][]{
                },
                new String[]{
                        "Product Name", "Price", "Quantity", "Total"
                });
        Object[] o;
        float totalCost = 0;
        for (ProductData p : inCartList) {
            totalCost += p.getPrice() * p.getQuantity();
            o = new Object[]{p.getName(), p.getPrice(), p.getQuantity(), p.getQuantity() * p.getPrice()};
            tableModel.addRow(o);
        }
        totalLabel.setText("Total :    " + totalCost);
        deliveryTypeLabel.setText("Delivery Type :    " + deliveryType);
        if (paymentType.equals("Credit Card")) {
            paymentTypeLabel.setText("Payment Type :    Credit Card (" + user.getCardNum() + ").");
        } else {
            paymentTypeLabel.setText("Payment Type :   " + paymentType);
        }
        if (deliveryType.equals("Self Pick-up")) {
            addressLabel.setText("");
            provinceLabel.setText("");
            zipCodeLabel.setText("");
        } else {
            addressLabel.setText("Address :    " + user.getAddress());
            provinceLabel.setText("Province :  " + user.getProvince());
            zipCodeLabel.setText("Postal Code :    " + user.getZip_code());
        }
    }

    public void addConfirmListener ( ActionListener listenToConfirmButton) {
        confirmButton.addActionListener( listenToConfirmButton );
    }

    public void addCancelListener( ActionListener listenToCancelButton ) {
        cancelButton.addActionListener( listenToCancelButton );
    }
}
