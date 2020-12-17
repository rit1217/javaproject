package com.local;

import javax.swing.*;
import java.awt.*;

public abstract class ScreenController {
    protected AppController app;

    public ScreenController( AppController app ) {
        this.app = app;
    }

    public abstract JFrame getView();
    public abstract void showScreen();
    public abstract void hideScreen();

}
