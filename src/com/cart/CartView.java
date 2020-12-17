package com.cart;

import com.local.ForcedListSelectionModel;
import com.local.ProductData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CartView extends JFrame {

    private JTable table;
    private ButtonGroup group;
    private ButtonGroup group1;
    private JButton cancelButton;
    private JButton checkOutButton;
    private JButton signCardButton;
    private JButton removeButton;
    private JRadioButton cashRadio;
    private JRadioButton creditRadio;
    private JRadioButton pickupRadio;
    private JRadioButton deliveryRadio;
    private DefaultTableModel productsTableModel;
    private JLabel totalLabel;
    private String radioButton;

    public CartView()
    {
        initialize();
    }

    private void initialize()
    {
        setBounds(100, 100, 1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(877, 623, 117, 29);
        getContentPane().add(cancelButton);

        checkOutButton = new JButton("Checkout ");
        checkOutButton.setBounds(760, 623, 117, 29);
        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        getContentPane().add(checkOutButton);

        totalLabel = new JLabel("Total Cost :   0");
        totalLabel.setBounds(16, 623, 400, 16);
        getContentPane().add(totalLabel);

        JLabel Show_price = new JLabel("");
        Show_price.setBounds(120, 623, 299, 16);
        getContentPane().add(Show_price);

        JLabel credit_message = new JLabel("Didn't have a credit card?");
        credit_message.setBounds(448, 628, 186, 16);
        getContentPane().add(credit_message);

        signCardButton = new JButton("Card Register");
        signCardButton.setBounds(616, 623, 130, 29);
        getContentPane().add(signCardButton);

        removeButton = new JButton( "Remove Item");
        removeButton.setBounds(16, 563,117, 29);
        getContentPane().add(removeButton);

        cashRadio = new JRadioButton("Cash on Delivery");
        cashRadio.setBounds(749, 559, 141, 23);
        getContentPane().add(cashRadio);
        cashRadio.setSelected( true );

        creditRadio = new JRadioButton("Credit/Debit");
        creditRadio.setBounds(749, 588, 141, 23);
        getContentPane().add(creditRadio);

        JLabel lblNewLabel = new JLabel("Payment Type:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(616, 563, 117, 16);
        getContentPane().add(lblNewLabel);

        pickupRadio = new JRadioButton("Self Pick-up");
        pickupRadio.setBounds(471, 559, 141, 23);
        getContentPane().add(pickupRadio);
        pickupRadio.setSelected( true );

        deliveryRadio = new JRadioButton("Delivery ");
        deliveryRadio.setBounds(471, 588, 141, 23);
        getContentPane().add(deliveryRadio);

        JLabel lblNewLabel_1 = new JLabel("Delivery Type:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(319, 563, 117, 16);
        getContentPane().add(lblNewLabel_1);

        group =new ButtonGroup();
        group.add(pickupRadio);
        group.add(deliveryRadio);

        group1 =new ButtonGroup();
        group1.add(creditRadio);
        group1.add(cashRadio);

        creditRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButton = "credit";
            }
        });

        cashRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButton = "cash";
            }
        });

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
        productsTableModel = (DefaultTableModel) table.getModel();
        table.setFillsViewportHeight(true);

//		table.setBounds(16,10,930,540);
//		JPanel itemPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(16, 45, 958, 500);
        scrollPane.add(table);
        getContentPane().add(scrollPane);
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }
        table.setSelectionModel( new ForcedListSelectionModel());


    }

    public String getDeliveryType() {
        if ( deliveryRadio.isSelected() ) return "Delivery";
        else return "Self Pick-up";
    }

    public String getPaymentType() {
        if ( cashRadio.isSelected() ) return "Cash";
        else return "Credit Card";
    }

    public void addCancelListener( ActionListener listenToCancelButton ) {
        cancelButton.addActionListener( listenToCancelButton );
    }

    public void addSignCardListener( ActionListener listenToSignCard ) {
        signCardButton.addActionListener( listenToSignCard );
    }

    public void addConfirmListener( ActionListener listenToConfirmButton ) {
        checkOutButton.addActionListener( listenToConfirmButton );
    }

    public void updateCart(ArrayList<ProductData> inCartList ) {
        productsTableModel.setDataVector(
                new Object [][] {
                },
                new String [] {
                        "Product Name", "Price", "Quantity", "Total"
                });
        Object[] o;
        float totalCost = 0;
        for ( ProductData p : inCartList ) {
            totalCost += p.getPrice() * p.getQuantity();
            o = new Object[]{ p.getName(), p.getPrice(), p.getQuantity(), p.getQuantity() * p.getPrice()};
            productsTableModel.addRow( o );
        }
        totalLabel.setText( "Total :    " + totalCost );
        if ( inCartList.size() > 0 ) {
            table.changeSelection( 0, 0, false, false );
        }

    }

    public void addRemoveListener( ActionListener listenToRemoveButton ) {
        removeButton.addActionListener( listenToRemoveButton );
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }


    public String getRadioButton(){ return radioButton; }


}
