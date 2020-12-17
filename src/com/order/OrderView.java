package com.order;

import com.local.OrderData;
import com.local.OrderItemData;
import com.local.ProductData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderView extends JFrame {
    private JTable table;
    /**
     * Create the application.
     */
    public OrderView(OrderData data)
    {
        initialize( data );
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(OrderData data)
    {
        setBounds(100, 100, 550, 361);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JLabel orderldLabel = new JLabel("OrderID : " + data.getOrderID() );
        orderldLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        orderldLabel.setBounds(16, 10, 300, 20);
        getContentPane().add(orderldLabel);

        JLabel totalLabel = new JLabel("Total Price : ");
        totalLabel.setBounds(16, 200, 170, 16);
        getContentPane().add(totalLabel);

        JLabel shippingLabel = new JLabel("Shipping :");
        shippingLabel.setBounds(16, 230, 200, 16);
        getContentPane().add(shippingLabel);

        JLabel paymentLabel = new JLabel("Payment :");
        paymentLabel.setBounds(16, 260, 230, 16);
        getContentPane().add(paymentLabel);

        if( data.getDeliveryType().equals("Delivery")) {
            JLabel addressLabel = new JLabel("Address : ");
            addressLabel.setBounds(250, 170, 70, 16);
            getContentPane().add(addressLabel);

            JLabel addrLabel = new JLabel(data.getAddress());
            addrLabel.setBounds(300, 170, 125, 60);
            getContentPane().add(addrLabel);
        }
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(425, 300, 115, 29);
        getContentPane().add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible( false );
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setBounds(15, 33, 520, 150);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setBounds(16, 33, 410, 150);
        getContentPane().add(table);

        JLabel orderLabel = new JLabel("");
        orderLabel.setBounds(97, 23, 109, 16);
        getContentPane().add(orderLabel);

        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product Name", "Quantity", "Total Price"
                })
        {
            Class[] types = new Class [] {
                    String.class, Integer.class, Float.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
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
        float totalPrice = 0;
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setDataVector(
                new Object [][] {
                },
                new String [] {
                        "Product Name", "Quantity", "Total Price"
                });
        Object[] o;
        for ( OrderItemData p : data.getItemList() ) {
            o = new Object[]{ p.getProdName(), p.getQuantity(), p.getTotalPrice()};
            tableModel.addRow( o );
            totalPrice += p.getTotalPrice();
        }
        if ( data.getItemList().size() > 0 ) {
            table.changeSelection( 0, 0, false, false );
        }
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
        }
        table.setModel( tableModel );

        totalLabel.setText( "Total Price : " + totalPrice);
        shippingLabel.setText( "Delivery Type : " + data.getDeliveryType());
        paymentLabel.setText( "Payment Type : " + data.getPaymentType() );
        if ( data.getPaymentType().equals("Credit Card")) {
            paymentLabel.setText( paymentLabel.getText() + " (" + data.getCreditCard() + ")");
        }
    }
}
