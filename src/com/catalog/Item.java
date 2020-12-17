package com.catalog;

import javax.swing.*;
import java.awt.*;

public class Item extends JPanel
{
	private JLabel nameLabel;
	private JLabel ordIdLabel;
	private JLabel priceLabel;
	private JLabel dateLabel;
	
	private JLabel itemName;
	private JLabel itemOrdId;
	private JLabel itemPrice;
	private JLabel itemDate;
	
	private JPanel colOne;
	private JPanel colTwo;
	private JPanel colThree;
	private JPanel colFour;
	
	
	public Item(String name, String orderId, String price, String purchaseDate)
	{
		this.setLayout(new GridLayout(1, 4));
		this.setPreferredSize(new Dimension(500, 90));
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		colOne = new JPanel(new GridLayout(2,1));
		colTwo = new JPanel(new GridLayout(2,1));
		colThree = new JPanel(new GridLayout(2,1));
		colFour = new JPanel(new GridLayout(2,1));
		
		this.add(colOne);
		this.add(colTwo);
		this.add(colThree);
		this.add(colFour);
		
		nameLabel = new JLabel("Item Name:");
		ordIdLabel = new JLabel("Order ID:");
		priceLabel = new JLabel("Price:");
		dateLabel = new JLabel("Purchase Date:");
		
		itemName = new JLabel(name);
		itemOrdId = new JLabel(orderId);
		itemPrice = new JLabel(price);
		itemDate = new JLabel(purchaseDate);
		
		colOne.add(nameLabel);
		colOne.add(ordIdLabel);
		
		colTwo.add(itemName);
		colTwo.add(itemOrdId);
		
		colThree.add(priceLabel);
		colThree.add(dateLabel);
		
		colFour.add(itemPrice);
		colFour.add(itemDate);
		
	}

}
