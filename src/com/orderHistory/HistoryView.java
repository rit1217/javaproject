package com.orderHistory;

import com.catalog.Item;
import com.local.AppController;
import com.local.OrderData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HistoryView extends JFrame{
    private JMenuItem infoMenu;
    private JMenuItem historyMenu;
    private JButton backButton;
    private JTable table;
    private DefaultTableModel historyTableModel;
    public ArrayList<OrderData> historyList;

    /**
     * Create the application.
     */
    public HistoryView()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        int posx = 100;
        int posy = 100;

        historyList = new ArrayList<>();

        setTitle("History");
        setResizable(false);
        setBounds(posx, posy, 650, 361);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 650, 317);
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(534, 282, 110, 29);
        mainPanel.add(backButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 39, 638, 235);
        mainPanel.add(scrollPane);

        JPanel historyPanel = new JPanel();
        historyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setViewportView(historyPanel);
        historyPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel historyLabel = new JLabel("History");
        historyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
        historyLabel.setBounds(6, 6, 132, 34);
        mainPanel.add(historyLabel);
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Order ID", "Total Price", "Status", "Payment Type", "Delivery Type"
                })
        {
            Class[] types = new Class [] {
                    String.class, Float.class, String.class, String.class, String.class
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
        historyTableModel = (DefaultTableModel) table.getModel();
        table.setFillsViewportHeight(true);


        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu profileMenu = new JMenu("Profile");
        menuBar.add(profileMenu);

        infoMenu = new JMenuItem("Info");
        profileMenu.add(infoMenu);

        JSeparator separator = new JSeparator();
        profileMenu.add(separator);

        historyMenu = new JMenuItem("History ");
        profileMenu.add(historyMenu);

    }

    public void addBackListener( ActionListener listenToBackButton ) {
        backButton.addActionListener( listenToBackButton );
    }

    public void addInfoListener( ActionListener listenToInfo ) {
        infoMenu.addActionListener( listenToInfo );
    }

    public void addHistoryListener( ActionListener listenToHistory ){
        historyMenu.addActionListener( listenToHistory );
    }
    public void updateTable() {
        historyTableModel.setDataVector(
                new Object [][] {

                },
                new String [] {
                        "Order ID", "Total Price", "Status", "Payment Type", "Delivery Type"
                });
        Object[] o;
        for ( OrderData p : historyList ) {
            o = new Object[]{ p.getOrderID(), p.getTotalPrice(), p.getStatus(), p.getPaymentType(),
                    p.getDeliveryType()};
            historyTableModel.addRow( o );
        }
        if ( historyList.size() > 0 ) {
            table.changeSelection( 0, 0, false, false );
        }
    }
}
