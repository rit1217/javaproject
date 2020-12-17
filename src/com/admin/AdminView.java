package com.admin;

import com.local.AppController;
import com.local.ForcedListSelectionModel;
import com.local.ProductData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminView extends JFrame {
    private JTable productsTable;
    private DefaultTableModel productsTableModel;
    private JButton editButton, addButton, restockButton, removeButton;
    private JButton refreshButton, logoutButton, orderButton;

    /**
     * Create the application.
     */
    public AdminView( ArrayList<ProductData> productList ){
        initialize( productList );
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize( ArrayList<ProductData> productList ) {
        getContentPane().setBackground(new Color(211, 211, 211));
        getContentPane().setForeground(Color.GRAY);
        setBounds(100, 100, 530, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setBounds(20, 45, 500, 190);
        getContentPane().add(scrollPane);

        productsTable = new JTable();
        scrollPane.setViewportView(productsTable);
        productsTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product Name", "Price", "Quantity", "Category"
                })
        {
            Class[] types = new Class [] {
                    String.class, Float.class, Integer.class, String.class
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
        productsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//				productsTableMouseClicked(evt);
            }
        });
        productsTableModel = (DefaultTableModel) productsTable.getModel();
        productsTableModel.setDataVector(
                new Object [][] {
                },
                new String [] {
                        "Product Name", "Price", "Quantity", "Category"
                });
        Object[] o;
        for ( ProductData p : productList ) {
            o = new Object[]{ p.getName(), p.getPrice(), p.getQuantity(), p.getCategory()};
            productsTableModel.addRow( o );
        }
        if ( productList.size() > 0 ) {
            productsTable.changeSelection( 0, 0, false, false );
        }
        scrollPane.setViewportView(productsTable);
        if (productsTable.getColumnModel().getColumnCount() > 0) {
            productsTable.getColumnModel().getColumn(0).setResizable(false);
            productsTable.getColumnModel().getColumn(1).setResizable(false);
            productsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        productsTable.setSelectionModel( new ForcedListSelectionModel());

        addButton = new JButton("Add");
        addButton.setBackground(Color.WHITE);
        addButton.setBounds(106, 254, 101, 29);
        getContentPane().add(addButton);

        restockButton = new JButton("Restock");
        restockButton.setBounds(401, 254, 101, 29);
        getContentPane().add(restockButton);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        removeButton.setBounds(302, 254, 101, 29);
        getContentPane().add(removeButton);

        editButton = new JButton("Edit");
        editButton.setBounds(204, 254, 101, 29);
        getContentPane().add(editButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(401, 297, 101, 29);
        getContentPane().add(logoutButton);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(302, 297, 101, 29);
        getContentPane().add(refreshButton);

        orderButton = new JButton("Order List");
        orderButton.setBounds(155, 297, 150, 29);
        getContentPane().add(orderButton);

        JLabel adminLabel = new JLabel("Admin");
        adminLabel.setFont(new Font("Lucida Grande", Font.BOLD, 28));
        adminLabel.setBounds(50, 10, 101, 26);
        getContentPane().add(adminLabel);

    }

    public void addEditListener( ActionListener listenToEditButton ) {
        editButton.addActionListener( listenToEditButton );
    }

    public void addRestockListener( ActionListener listenToRestockButton ) {
        restockButton.addActionListener( listenToRestockButton );
    }

    public DefaultTableModel getProductsTableModel() {
        return productsTableModel;
    }

    public void addRefreshListener(ActionListener listenToRefreshButton ) {
        refreshButton.addActionListener( listenToRefreshButton );
    }

    public void addAddListener( ActionListener listenToAddButton ) {
        addButton.addActionListener( listenToAddButton );
    }

    public void addRemoveListener( ActionListener listenToRemoveButton ) {
        removeButton.addActionListener( listenToRemoveButton );
    }

    public void addLogOutListener( ActionListener listenToLogOutButton ) {
        logoutButton.addActionListener( listenToLogOutButton );
    }

    public void addOrderListener( ActionListener listenToOrderButton ) {
        orderButton.addActionListener( listenToOrderButton );
    }

    public String getSelectedRow() {
        return (String) (productsTable.getValueAt(productsTable.getSelectedRow(), 0));
    }


}
