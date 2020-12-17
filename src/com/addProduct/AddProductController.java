package com.addProduct;

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

public class AddProductController extends ScreenController {

    AddProductView view;

    public AddProductController(AppController app ) {
        super( app );
        this.view = new AddProductView();

        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearField();
                hideScreen();
                app.changeScreen( app.ADMIN );
            }
        });
        this.view.addChooseFileListener( new ChooseFileListener() );
        this.view.addSaveListener( new SaveListener() );
    }
    @Override
    public JFrame getView() { return this.view; }

    @Override
    public void showScreen() { view.setVisible( true ); }

    @Override
    public void hideScreen() { view.setVisible( false ); }

    private File addFile;

    public class ChooseFileListener implements ActionListener {

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
        }
    }
    public File getAddFile(){ return addFile; }
    public class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductData insertData = view.getInputInfo();
            try {
                ProductDatabaseModel.insertProduct( insertData, getAddFile() );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            view.clearField();
            hideScreen();
        }
    }
}
