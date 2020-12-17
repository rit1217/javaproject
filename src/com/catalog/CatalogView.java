package com.catalog;

import com.cart.CartController;
import com.local.AppController;
import com.local.ProductData;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CatalogView extends JFrame {
    private ButtonGroup group;
    private JButton profileButton;
    private ArrayList<ItemCatalog> shirts;
    private ArrayList<ItemCatalog> pants;
    private JButton cartButton;
    private JButton clearButton, backButton;
    private CartController cart;
    private JPanel shirtPanel, pantPanel;
    private JScrollPane pantScrollPane, shirtScrollPane;
    private JLabel shirtPreview, pantPreview;

    public CatalogView(ArrayList<ProductData> shirts, ArrayList<ProductData> pants, CartController cart)
    {
        this.cart = cart;
        initialize( shirts, pants );
    }

    private void initialize( ArrayList<ProductData> shirtList, ArrayList<ProductData> pantList)
    {

        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setBounds(100, 100, 1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        profileButton = new JButton("PROFILE");
        profileButton.setBounds(775, 6, 85, 45);
        getContentPane().add(profileButton);

        cartButton = new JButton("");
        cartButton.setBounds(890, 6, 85, 45);
        getContentPane().add(cartButton);

        backButton = new JButton( "BACK");
        backButton.setBounds( 890, 6, 85,45);
        getContentPane().add(backButton);

        cartButton.setIcon(new ImageIcon("asset/shopping-cart.png"));

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        clearButton.setBounds(836, 508, 117, 29);
        getContentPane().add(clearButton);

        shirtScrollPane = new JScrollPane();
        shirtScrollPane.setBounds(50, 18, 700, 305);
        getContentPane().add(shirtScrollPane);

        pantScrollPane = new JScrollPane();
        pantScrollPane.setBounds(50, 335, 700, 305);
        getContentPane().add(pantScrollPane);

        updateCatalog( shirtList, pantList );
        pantScrollPane.setBorder(BorderFactory.createTitledBorder("Pants"));

        shirtPreview = new JLabel();
        shirtPreview.setForeground(Color.PINK);
        shirtPreview.setBounds(785, 115, 200, 200);
        getContentPane().add(shirtPreview);

        pantPreview = new JLabel();
        pantPreview.setForeground(Color.PINK);
        pantPreview.setBounds(785, 240, 200, 200);
        getContentPane().add(pantPreview);




    }

    public void addCartListener( ActionListener listenToCartButton ) {
        cartButton.addActionListener( listenToCartButton );
    }

    public void addProfileListener( ActionListener listenToProfileButton ) {
        profileButton.addActionListener( listenToProfileButton );
    }

    public void addBackListener( ActionListener listenToBackButton ) {
        backButton.addActionListener( listenToBackButton );
    }

    public void addClearListener( ActionListener listenToClearButton ) {
        clearButton.addActionListener( listenToClearButton );
    }

    public void guestLogin() {
        profileButton.setVisible( false );
        backButton.setVisible( true );
        cartButton.setVisible( false );
    }
    public void userLogin() {
        profileButton.setVisible( true );
        backButton.setVisible( false );
        cartButton.setVisible( true );
    }

    private ImageIcon createImageIcon( byte[] b ) {
        return new ImageIcon( new ImageIcon(b).getImage().getScaledInstance( 128, 128, Image.SCALE_DEFAULT ) );
    }

    public void updateCatalog( ArrayList<ProductData> shirtList, ArrayList<ProductData> pantList ) {
        shirts = new ArrayList<>();
        pants = new ArrayList<>();
        shirtPanel = new JPanel();
        shirtScrollPane.setViewportView(shirtPanel);
        ItemCatalog tempItem;
        for(int i = 0; i < shirtList.size(); i++) {
            if ( shirtList.get(i).getQuantity() <= 0 ) continue;
            tempItem = new ItemCatalog( new ProductData( shirtList.get(i)), cart, this );
            tempItem.data.setQuantity( 0 );
            tempItem.getPicture().setIcon( createImageIcon(shirtList.get(i).getImage() ));
            tempItem.itemNumSpn.setModel(
                    new SpinnerNumberModel( 0, 0, tempItem.getFullQuantity(), 1));
            shirts.add(tempItem);
            shirtPanel.add( tempItem );
        }

        pantPanel = new JPanel();
        pantScrollPane.setViewportView(pantPanel);
        for(int i = 0; i < pantList.size(); i++) {
            if ( pantList.get(i).getQuantity() <= 0 ) continue;
            tempItem = new ItemCatalog( new ProductData( pantList.get(i)), cart , this);
            tempItem.data.setQuantity( 0 );
            tempItem.getPicture().setIcon( createImageIcon(pantList.get(i).getImage() ));
            tempItem.itemNumSpn.setModel( new SpinnerNumberModel( 0, 0, tempItem.getFullQuantity(), 1));
            tempItem.previewBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            pants.add(tempItem);
            pantPanel.add( tempItem );
        }

    }

    public void setPreview( ImageIcon image, String category ) {
        if ( category.equals("Shirt"))
            shirtPreview.setIcon( image );
        else if ( category.equals("Pant"))
            pantPreview.setIcon( image );
    }

    public void setPantPreview( ImageIcon image ) {
        pantPreview.setIcon( image );
    }
}
