package com.catalog;

import com.cart.CartController;
import com.local.ProductData;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCatalog extends JPanel {
    public JSpinner itemNumSpn;
    public JButton addToCartBtn;
    public JButton previewBtn;
    private JLabel picture;
    private int fullQuantity;
    private JPanel rowOne;
    private JPanel rowTwo;
    private CartController cart;

    public int getFullQuantity() {
        return fullQuantity;
    }

    private JPanel rowThree;
    public ProductData data;
    private CatalogView catalogView;

    public JLabel getPicture() {
        return picture;
    }

    public ItemCatalog(ProductData prod, CartController cart, CatalogView catalogView ){
        this.data = prod;
        this.fullQuantity = data.getQuantity();
        this.cart = cart;
        this.catalogView = catalogView;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(prod.getName() + " : " + prod.getPrice() + " Baht"));
        this.setPreferredSize(new Dimension(200, 250));

        GridBagConstraints gbc = new GridBagConstraints();

        picture = new JLabel();
        itemNumSpn = new JSpinner();
        addToCartBtn = new JButton("Add To Cart");
        previewBtn = new JButton("Preview");

        rowOne = new JPanel();
        rowTwo = new JPanel(new FlowLayout());
        rowThree = new JPanel();
        try {
            picture.setIcon(new ImageIcon(prod.getImage()));
        } catch ( Exception e) { System.out.println(e.getMessage()); }

        itemNumSpn.setPreferredSize(new Dimension(45, 30));
        addToCartBtn.setPreferredSize(new Dimension(100, 30));

        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(rowOne, gbc);

        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.gridy = 1;
        this.add(rowTwo,gbc);

        gbc.gridy = 2;
        this.add(rowThree,gbc);

        
        rowOne.add(picture);
        rowOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        rowOne.setBorder(BorderFactory.createEmptyBorder(5, 5, 3, 5));

        rowTwo.add(itemNumSpn);
        rowTwo.setBorder(BorderFactory.createEmptyBorder(0, 5, 3, 5));
        rowTwo.add(addToCartBtn);

        rowThree.add(previewBtn);
        rowThree.setBorder(BorderFactory.createEmptyBorder(0, 5, 3, 5));

        addToCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addToCart( data );
            }
        });

        itemNumSpn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                data.setQuantity((Integer) (itemNumSpn.getValue()));

            }
        });

        previewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogView.setPreview( new ImageIcon(new ImageIcon(
                        data.getImage()).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))
                        , data.getCategory() );
            }
        });

    }

}