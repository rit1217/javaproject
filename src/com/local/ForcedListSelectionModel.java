package com.local;

import javax.swing.*;

public class ForcedListSelectionModel extends DefaultListSelectionModel {
    public ForcedListSelectionModel() {
        setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    }
}
