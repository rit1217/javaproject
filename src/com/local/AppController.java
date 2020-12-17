package com.local;

import com.addProduct.AddProductController;
import com.admin.AdminController;
import com.cart.CartController;
import com.cart.ConfirmOrderController;
import com.catalog.*;
import com.editProduct.EditProductController;
import com.editProduct.RestockController;
import com.login.*;
import com.order.AdminOrderController;
import com.orderHistory.HistoryController;
import com.payment.SignCardController;
import com.profile.ProfileController;
import com.signup.SignupController;

import java.sql.*;
import java.util.ArrayList;


public class AppController {

    public CartController cart;
    public EditProductController editPage;
    public AdminController adminApp;
    public ProfileController profileScreen;
    private CatalogController catalog;
    private ScreenController currentScreen;
    public UserData userData;
    public boolean guestLogin;
    public ArrayList<ScreenController> screens;
    public ArrayList<ProductData> shirtsList;
    public ArrayList<ProductData> pantsList;
    public ArrayList<ProductData> productList;

    public final static int LOGIN = 0;
    public final static int SIGNUP = 1;
    public final static int CATALOG = 2;
    public final static int ADMIN = 3;
    public final static int ADD_PROD = 4;
    public final static int PROFILE = 5;
    public final static int EDIT_PROD = 6;
    public final static int SIGN_CARD = 7;
    public final static int HISTORY = 8;
    public final static int CART = 9;
    public final static int RESTOCK = 10;
    public final static int ADMIN_ORDER = 11;
    public final static int CONFIRM_ORDER = 12;



    public AppController() throws SQLException {
        screens = new ArrayList<>();
        screens.add( new LoginController( this ));
        cart = new CartController( this );
        screens.add( new SignupController( this ));
        catalog = new CatalogController( this );
        screens.add( catalog );
        adminApp = new AdminController( this );
        screens.add( adminApp );
        screens.add( new AddProductController( this ));
        profileScreen = new ProfileController( this );
        screens.add( profileScreen );
        editPage = new EditProductController( this, adminApp);
        adminApp.setEditPage( editPage );
        screens.add( editPage );
        screens.add( new SignCardController( this ));
        screens.add( new HistoryController( this ));
        screens.add( cart );
        screens.add( new RestockController( this, adminApp ));
        screens.add( new AdminOrderController( this ));
        ConfirmOrderController confirm = new ConfirmOrderController( this, cart );
        screens.add( confirm );
        cart.setConfirmOrder( confirm );

        currentScreen = screens.get( 0 );
        screens.get( 0 ).showScreen();
        guestLogin = true;

    }

    public void changeScreen ( int nextScreen ) {
        if ( nextScreen >= 0 && nextScreen <= screens.size()) {
            if ( nextScreen == CATALOG && guestLogin == true )
                catalog.setUserView("Guest");
            else if ( nextScreen == CATALOG && guestLogin == false )
                catalog.setUserView("Customer");
            switchScreen( currentScreen, screens.get( nextScreen ));
            currentScreen = screens.get( nextScreen );
        }
    }

    public void switchScreen(ScreenController sc1, ScreenController sc2 ) {
        sc1.hideScreen();
        sc2.getView().setLocation( sc1.getView().getLocation() );
        sc2.showScreen();
    }

    public static void main( String[] args ) throws SQLException {
        AppController app = new AppController();
    }

    public void addScreen( int screenNum ) {
        screens.get(screenNum).showScreen();
    }

    public void clearUserData( ) {
        userData = null;
    }


}
