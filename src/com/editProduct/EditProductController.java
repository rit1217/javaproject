package com.editProduct;

import com.addProduct.AddProductController;
import com.addProduct.AddProductView;
import com.admin.AdminController;
import com.local.AppController;
import com.local.ProductData;
import com.local.ProductDatabaseModel;
import com.local.ScreenController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class EditProductController extends ScreenController {

    EditProductView view;
    AdminController adminApp;
    public String editingProduct;
    File addFile;

    public EditProductController(AppController app, AdminController adminApp) throws SQLException {
        super( app );
        view = new EditProductView();
        this.adminApp = adminApp;

        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearField();
                hideScreen();
                app.changeScreen( app.ADMIN );
            }
        });

        this.view.addSaveListener( new SaveEditListener() );
        this.view.addChooseFileListener( new ChooseFileEditListener() );
    }
    @Override
    public JFrame getView() {
        return view;
    }

    @Override
    public void showScreen() {
        view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        view.setVisible( false );
    }

    class SaveEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDatabaseModel.removeByProductName( editingProduct );
            try {
                ProductDatabaseModel.insertProduct( view.getInputInfo(), addFile );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            hideScreen();
        }
    }

    class ChooseFileEditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(null);
            File f = fileChooser.getSelectedFile();

            addFile = f;
            try {
                BufferedImage bufferedImage = ImageIO.read( f );
                WritableRaster raster = bufferedImage.getRaster();
                DataBufferByte dataBuffer = (DataBufferByte) ( raster.getDataBuffer() );
                view.imgBytes = dataBuffer.getData();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

//            JOptionPane.showMessageDialog(null, "file chosen");
        }
    }

    public void setProduct(ProductData prod ) {
        view.setProduct( prod );
    }

}
