package com.order;

import com.local.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminOrderView extends JFrame {

    private JTable table;
    private JButton deleteButton;
    private JButton backButton;
    private JButton statusButton;
    private JButton viewButton;
    private JRadioButton pendingRadioButton;
    private JRadioButton shippingRadioButton;
    private DefaultTableModel orderTableModel;
    public ArrayList<OrderData> orderList;
    public AdminOrderView()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        orderList = new ArrayList<OrderData>( );
        setBounds(100, 100, 530, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JLabel lblNewLabel = new JLabel("Order");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(22, 18, 61, 16);
        getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 47, 497, 189);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Order ID", "Customer Username", "Total Price", "Status", "Payment Type", "Delivery Type"
                })
        {
            Class[] types = new Class [] {
                    String.class, String.class, Float.class, String.class, String.class, String.class
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
        orderTableModel = (DefaultTableModel) table.getModel();
        table.setFillsViewportHeight(true);

        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }
        table.setSelectionModel( new ForcedListSelectionModel());


        pendingRadioButton = new JRadioButton("Pending");
        pendingRadioButton.setBounds(22, 248, 141, 23);
        getContentPane().add(pendingRadioButton);
        pendingRadioButton.setSelected( true );

        shippingRadioButton = new JRadioButton("Shipping");
        shippingRadioButton.setBounds(22, 280, 141, 23);
        getContentPane().add(shippingRadioButton);

        ButtonGroup group = new ButtonGroup();
        group.add( pendingRadioButton );
        group.add( shippingRadioButton );

        viewButton = new JButton("View");
        viewButton.setBounds(308, 265, 90, 29);
        getContentPane().add(viewButton);

        statusButton = new JButton("Set Status");
        statusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        statusButton.setBounds(308, 312, 90, 29);
        getContentPane().add(statusButton);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        backButton.setBounds(429, 312, 90, 29);
        getContentPane().add(backButton);
    }

    public void addBackListener( ActionListener listenToBackButton ) {
        backButton.addActionListener( listenToBackButton );
    }

    public void addStatusListener( ActionListener listenToStatusButton ) {
        statusButton.addActionListener( listenToStatusButton );
    }

    public void addViewListener( ActionListener listenToViewButton ) {
        viewButton.addActionListener( listenToViewButton );
    }

    public void updateTable() {
        orderTableModel.setDataVector(
                new Object [][] {

                },
                new String [] {
                        "Order ID", "Customer Username", "Total Price", "Status", "Payment Type", "Delivery Type"
                });
        Object[] o;
        for ( OrderData p : orderList ) {
            o = new Object[]{ p.getOrderID(), p.getCustomerUserName(), p.getTotalPrice(), p.getStatus(), p.getPaymentType(),
            p.getDeliveryType()};
            orderTableModel.addRow( o );
        }
        if ( orderList.size() > 0 ) {
            table.changeSelection( 0, 0, false, false );
        }
    }

    public OrderData getSelectedOrder() {
        OrderData res = OrderDatabaseModel.getDataFromOrderID( orderList.get( table.getSelectedRow()).getOrderID() );
        res.setItemList(OrderItemDatabaseModel.getItemsFromOrderID( res.getOrderID()));
        return res;
    }

    public String getSelectedStatus() {
        if ( pendingRadioButton.isSelected() ) return "Pending";
        else return "Shipping";
    }
}

