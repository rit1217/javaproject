package com.addProduct;

import com.local.ProductData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddProductView extends JFrame {
    protected JTextField productNameField;
    protected JTextField priceField;
    protected JTextField descriptionField;
    protected JTextField quantityField;
    protected JButton saveButton;
    protected JButton cancelButton;
    protected JComboBox<String> categoriesCombo;
    protected JButton chooseFilebutton;

    protected String category;
    protected File addFile;
    public byte[] imgBytes;

    /**
     * Launch the application.
     */


    /**
     * Create the application.
     */
    public AddProductView( )
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setBounds(100, 100, 450, 361);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JLabel productLabel = new JLabel("Product Name");
        productLabel.setBounds(46, 10, 93, 26);
        getContentPane().add(productLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(104, 38, 35, 26);
        getContentPane().add(priceLabel);

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(78, 66, 61, 26);
        getContentPane().add(quantityLabel);

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(78, 94, 61, 26);
        getContentPane().add(categoryLabel);

        JLabel pictureLabel = new JLabel("Picture");
        pictureLabel.setBounds(86, 122, 53, 26);
        getContentPane().add(pictureLabel);

        JLabel descLabel = new JLabel("Description");
        descLabel.setBounds(60, 150, 79, 26);
        getContentPane().add(descLabel);

        setProductNameField(new JTextField());
        getProductNameField().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = getPriceField().getText();
            }
        });

        getProductNameField().setBounds(173, 10, 174, 26);
        getContentPane().add(getProductNameField());
        getProductNameField().setColumns(10);

        setPriceField(new JTextField());
        getPriceField().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String price = getPriceField().getText();
            }
        });
        getPriceField().setBounds(173, 38, 106, 26);
        getContentPane().add(getPriceField());
        getPriceField().setColumns(10);

        setQuantityField(new JTextField());
        getQuantityField().setBounds(173, 66, 106, 26);
        getContentPane().add(getQuantityField());
        getQuantityField().setColumns(10);
        setCategory(new String("Shirt"));
        setCategoriesCombo(new JComboBox());
        getCategoriesCombo().setModel( new DefaultComboBoxModel<>( new String[] { "Shirt", "Pant"}));
        getCategoriesCombo().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( getCategoriesCombo().getSelectedIndex() == 0) setCategory("Shirt");
                if ( getCategoriesCombo().getSelectedIndex() == 1) setCategory("Pant");
            }
        });
        getCategoriesCombo().setBounds(173, 94, 106, 27);
        getContentPane().add(getCategoriesCombo());

        setChooseFilebutton(new JButton("Choose File"));
        getChooseFilebutton().setBounds(173, 122, 106, 29);
        getContentPane().add(getChooseFilebutton());

        setDescriptionField(new JTextField());
        getDescriptionField().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String description = getPriceField().getText();
            }
        });
        getDescriptionField().setBounds(173, 150, 234, 117);
        getContentPane().add(getDescriptionField());
        getDescriptionField().setColumns(10);

        setCancelButton(new JButton("Cancel"));
        getCancelButton().setBounds(314, 290, 93, 29);
        getContentPane().add(getCancelButton());

        setSaveButton(new JButton("Save"));
        getSaveButton().setBounds(214, 290, 88, 29);
        getContentPane().add(getSaveButton());
    }

    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();
        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }

    public void addSaveListener( ActionListener listenToSaveButton ) {
        getSaveButton().addActionListener( listenToSaveButton );
    }

    public void addCancelListener( ActionListener listenToCancelButton ) {
        getCancelButton().addActionListener( listenToCancelButton );
    }

    public void addChooseFileListener( ActionListener listenToChooseButton ) {
        getChooseFilebutton().addActionListener( listenToChooseButton );
    }

    public String getCategory() {
        if ( getCategoriesCombo().getSelectedIndex() == 0) category = "Shirt";
        if ( getCategoriesCombo().getSelectedIndex() == 1) category = "Pant";
        return category;
    }

    public ProductData getInputInfo() {
        ProductData temp = new ProductData( getProductNameField().getText(), getDescriptionField().getText(),
                Integer.parseInt(getQuantityField().getText()), Float.parseFloat(getPriceField().getText()), getCategory() );
        temp.setImage( imgBytes );
        return temp;
    }

    public void clearField() {
        getProductNameField().setText("");
        getDescriptionField().setText("");
        getQuantityField().setText("");
        getPriceField().setText("");
        imgBytes = null;
    }

    public JTextField getProductNameField() {
        return productNameField;
    }

    public void setProductNameField(JTextField productNameField) {
        this.productNameField = productNameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public void setPriceField(JTextField priceField) {
        this.priceField = priceField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(JTextField descriptionField) {
        this.descriptionField = descriptionField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(JTextField quantityField) {
        this.quantityField = quantityField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JComboBox<String> getCategoriesCombo() {
        return categoriesCombo;
    }

    public void setCategoriesCombo(JComboBox<String> categoriesCombo) {
        this.categoriesCombo = categoriesCombo;
    }

    public JButton getChooseFilebutton() {
        return chooseFilebutton;
    }

    public void setChooseFilebutton(JButton chooseFilebutton) {
        this.chooseFilebutton = chooseFilebutton;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public File getAddFile() {
        return addFile;
    }

    public void setAddFile(File addFile) {
        this.addFile = addFile;
    }
}
