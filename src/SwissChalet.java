/**NAME: Alex Adusei
 * DATE: Friday, March 2, 2012
 * COURSE CODE: ICS4U1
 * PROGRAM: Swiss Chalet Program.
 */

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class SwissChalet extends JPanel implements ActionListener
{
	private JRadioButton rbQuatreChicken, rbDoubleLeg, rbHalfChicken, rbChickenRib;
	private JCheckBox chkFries, chkBakedPotatoes, chkRice, chkCorn, chkMashedPotatoes, chkMedley, chkColesaw, 
	                  chkSalad;
	private JLabel lblDinners, lblSides, lblBeverages, lblCoke, lblSprite, lblOrange, lblRootBeer, lblSubTotal2, 
	               lblDeliveryFee2, lblHST2, lblGrandTotal2;
	private JComboBox cbCoke, cbSprite, cbOrange, cbRootBeer;
	private JButton btnCalculate, btnClear, btnCheckout, btnExit;
	private DecimalFormat fm;
	private double dinnerTotal, sidesTotal, beveragesTotal, subTotal, deliveryFee, HST, grandTotal;
	private int count, amount;
	private ButtonGroup group;
	private boolean optionSelection;
	private JRadioButton rbChecked;
	private String dishString;
	private String[] quantity;
	
	//Declare main method
	public static void main(String[] args)
	{
		new SwissChalet();
	}
	
	public SwissChalet()
	{
		/*Declare new DecimalFormat to leave the calculated prices to two digits, with proper commas and
		 * a dollar sign
		 */
		fm = new DecimalFormat("$,##0.00");
		
		dishString = ""; //String for labelling all the selected side dishes. Initially set to nothing by default
		optionSelection = false;
		
		//Set the quantity of the beverages to 0 by default.
		quantity = new String[4];
		
		for (int i = 0; i < 4; i++)
		{
			quantity[i] = "0";
		}
		
		count = 0; //counter for the side dish JCheckBoxes
		
		//Mathematical values for the food pricing
		dinnerTotal = 0;
		sidesTotal = 0;
		beveragesTotal = 0;
		subTotal = 0;
		deliveryFee = 0;
		HST = 0;
		grandTotal = 0;
		
		//Declare and set qualities to JPanels showing images
		JLabel lblTitle = new JLabel(new ImageIcon("swisschalet.png"));
		JLabel lblPaymentOptions = new JLabel(new ImageIcon("PaymentOptions.png"));
		
		//Declare all JPanels
		JPanel pnlDinners = new JPanel();
		JPanel pnlSides = new JPanel();
		JPanel pnlBeverages = new JPanel();
		
		JPanel pnlMiddle1 = new JPanel();
		JPanel pnlMiddle2 = new JPanel();
		
		JPanel pnlBottomLeft = new JPanel();
		JPanel pnlBottomCenter = new JPanel();
		JPanel pnlBottomRight = new JPanel();
		
		//Declare and set qualities to all JRadioButtons (title, names), add ItemListeners and remove focus.
		rbQuatreChicken = new JRadioButton("1/4 Chicken Dinner");
		rbDoubleLeg = new JRadioButton("Double Leg Dinner");
		rbHalfChicken = new JRadioButton("1/2 Chicken Dinner");
		rbChickenRib = new JRadioButton("Chicken & Rib Combo");
		
		rbQuatreChicken.setFocusable(false);
		rbDoubleLeg.setFocusable(false);
		rbHalfChicken.setFocusable(false);
		rbChickenRib.setFocusable(false);
		
		//ItemListener set to the inner class DinnersListener
		rbQuatreChicken.addItemListener(new DinnersListener());
		rbDoubleLeg.addItemListener(new DinnersListener());
		rbHalfChicken.addItemListener(new DinnersListener());
		rbChickenRib.addItemListener(new DinnersListener());
		
		rbQuatreChicken.setActionCommand("8.29");
		rbDoubleLeg.setActionCommand("9.99");
		rbHalfChicken.setActionCommand("11.99");
		rbChickenRib.setActionCommand("17.99");
		
		rbQuatreChicken.setName("1/4 Chicken Dinner");
		rbDoubleLeg.setName("Double Leg Dinner");
		rbHalfChicken.setName("1/2 Chicken Dinner");
		rbChickenRib.setName("Chicken & Rib Combo");
		
		//Declare a ButtonGroup for the JRadioButtons
		group = new ButtonGroup();
		
		group.add(rbQuatreChicken);
		group.add(rbDoubleLeg);
		group.add(rbHalfChicken);
		group.add(rbChickenRib);
		
		//Declare and set properties to JCheckBoxes (title, names). Also remove focus and add ItemListeners
		chkFries = new JCheckBox("Fresh-Cut Fries");
		chkBakedPotatoes = new JCheckBox("Oven-Baked Potato");
		chkRice = new JCheckBox("Seasoned Rice");
		chkCorn = new JCheckBox("Sweet Kernel Corn");
		chkMashedPotatoes = new JCheckBox("Mashed Potatoes");
		chkMedley = new JCheckBox("Vegetable Medley");
		chkColesaw = new JCheckBox("Creamy Colesaw");
		chkSalad = new JCheckBox("Side Garden Salad");
		
		chkFries.setName("Fresh-Cut Fries");
		chkBakedPotatoes.setName("Oven-Baked Potato");
		chkRice.setName("Seasoned Rice");
		chkCorn.setName("Sweet Kernel Corn");
		chkMashedPotatoes.setName("Mashed Potatoes");
		chkMedley.setName("Vegetable Medley");
		chkColesaw.setName("Creamy Colesaw");
		chkSalad.setName("Side Garden Salad");
		
		chkFries.setFocusable(false);
		chkBakedPotatoes.setFocusable(false);
		chkRice.setFocusable(false);
		chkCorn.setFocusable(false);
		chkMashedPotatoes.setFocusable(false);
		chkMedley.setFocusable(false);
		chkColesaw.setFocusable(false);
		chkSalad.setFocusable(false);
		
		//ItemListener set to the inner class SidesListener
		chkFries.addItemListener(new SidesListener());
		chkBakedPotatoes.addItemListener(new SidesListener());
		chkRice.addItemListener(new SidesListener());
		chkCorn.addItemListener(new SidesListener());
		chkMashedPotatoes.addItemListener(new SidesListener());
		chkMedley.addItemListener(new SidesListener());
		chkColesaw.addItemListener(new SidesListener());
		chkSalad.addItemListener(new SidesListener());
		
		//Declare and set qualities to JLabels showing text values (Size, location, borders, text, etc)
		lblDinners = new JLabel();
		lblSides = new JLabel();
		lblBeverages = new JLabel();
		lblCoke = new JLabel("Coke");
		lblSprite = new JLabel("Sprite");
		lblOrange = new JLabel("Orange");
		lblRootBeer = new JLabel("RootBeer");
		
		JLabel lblMiddle = new JLabel("First two (2) sides are FREE!");
		JLabel lblSubTotal1 = new JLabel("SUBTOTAL:");
		JLabel lblDeliveryFee1 = new JLabel("DELIVERY FREE:");
		JLabel lblHST1 = new JLabel("HST:");
		JLabel lblGrandTotal1 = new JLabel("GRAND TOTAL:");
		
		lblSubTotal2 = new JLabel();
		lblDeliveryFee2 = new JLabel();
		lblHST2 = new JLabel();
		lblGrandTotal2 = new JLabel();
		
		lblRootBeer.setPreferredSize(new Dimension(60, 20));
		
		lblDinners.setPreferredSize(new Dimension(100, 25));
		lblDinners.setMaximumSize(new Dimension(100, 25));
		lblDinners.setMinimumSize(new Dimension(100, 25));
		
		lblDinners.setText(fm.format(dinnerTotal));
		lblDinners.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinners.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lblSides.setText(fm.format(sidesTotal));
		lblSides.setHorizontalAlignment(SwingConstants.CENTER);
		lblSides.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lblBeverages.setText(fm.format(beveragesTotal));
		lblBeverages.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeverages.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		lblSubTotal2.setPreferredSize(new Dimension(100, 25));
		lblSubTotal2.setText(fm.format(subTotal));
		lblSubTotal2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTotal2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lblDeliveryFee2.setOpaque(true);
		lblDeliveryFee2.setText(fm.format(deliveryFee));
		lblDeliveryFee2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeliveryFee2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lblHST2.setText(fm.format(HST));
		lblHST2.setHorizontalAlignment(SwingConstants.CENTER);
		lblHST2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lblGrandTotal2.setText(fm.format(grandTotal));
		lblGrandTotal2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrandTotal2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//Declare an array of strings for the JComboBoxes. Make default start 0
		String[] coke = {"0", "1", "2", "3", "4", "5", "6"};
		String[] sprite = {"0", "1", "2", "3", "4", "5", "6"};
		String[] orange = {"0", "1", "2", "3", "4", "5", "6"};
		String[] rootbeer = {"0", "1", "2", "3", "4", "5", "6"};
		
		//Declare and set qualities to JComboBoxes, add ItemListener, and remove focus
		cbCoke = new JComboBox(coke);
		cbSprite = new JComboBox(sprite);
		cbOrange = new JComboBox(orange);
		cbRootBeer = new JComboBox(rootbeer);
		
		cbCoke.setFocusable(false);
		cbSprite.setFocusable(false);
		cbOrange.setFocusable(false);
		cbRootBeer.setFocusable(false);
		
		//ItemListener set to the inner class BeveragesListener
		cbCoke.addItemListener(new BeveragesListener());
		cbSprite.addItemListener(new BeveragesListener());
		cbOrange.addItemListener(new BeveragesListener());
		cbRootBeer.addItemListener(new BeveragesListener());
		
		/* Declare and set qualities to all JButtons(default, maximum, and minimum size, titles)
		 * add ActionListeners, remove focus, and disable the calculate and checkout buttons
		 */
		btnCalculate = new JButton("CALCULATE");
		btnClear = new JButton("CLEAR");
		btnCheckout = new JButton("CHECKOUT");
		btnExit = new JButton("EXIT");
		
		btnCalculate.setPreferredSize(new Dimension(110, 30));
		btnCalculate.setMaximumSize(new Dimension(110, 30));
		btnCalculate.setMinimumSize(new Dimension(110, 30));
		
		btnClear.setPreferredSize(new Dimension(110, 30));
		btnClear.setMaximumSize(new Dimension(110, 30));
		btnClear.setMinimumSize(new Dimension(110, 30));
		
		btnCheckout.setPreferredSize(new Dimension(110, 30));
		btnCheckout.setMaximumSize(new Dimension(110, 30));
		btnCheckout.setMinimumSize(new Dimension(110, 30));
		
		btnExit.setPreferredSize(new Dimension(110, 30));
		btnExit.setMaximumSize(new Dimension(110, 30));
		btnExit.setMinimumSize(new Dimension(110, 30));
		
		btnCalculate.setFocusable(false);
		btnClear.setFocusable(false);
		btnCheckout.setFocusable(false);
		btnExit.setFocusable(false);
		
		btnCalculate.addActionListener(this);
		btnClear.addActionListener(this);
		btnCheckout.addActionListener(this);
		btnExit.addActionListener(this);
		
		btnCalculate.setEnabled(false);
		btnCheckout.setEnabled(false);
		
		//Set the qualities of all sub-panels. Set all layouts, borders, titles, and sizes
		pnlDinners.setLayout(new GridLayout(4, 1, 5, 5));
		pnlDinners.setPreferredSize(new Dimension(180, 137));
		pnlDinners.setBorder(BorderFactory.createTitledBorder("CHICKEN DINNERS"));
		
		pnlSides.setLayout(new GridLayout(4, 2, 5, 5));
		pnlSides.setBorder(BorderFactory.createTitledBorder("SIDES"));
		
		pnlBeverages.setLayout(new GridLayout(4, 2, 5, 4));
		pnlBeverages.setBorder(BorderFactory.createTitledBorder("BEVERAGES"));
		
		pnlMiddle2.setLayout(new GridLayout(1, 3, 130, 5));
		
		pnlBottomCenter.setLayout(new GridLayout(4, 2, 5, 5));
		
		pnlBottomRight.setLayout(new GridLayout(4, 1, 5, 5));
		
		//Add respective JComponents to all sub-panels
		pnlDinners.add(rbQuatreChicken);
		pnlDinners.add(rbDoubleLeg);
		pnlDinners.add(rbHalfChicken);
		pnlDinners.add(rbChickenRib);
		
		pnlSides.add(chkFries);
		pnlSides.add(chkMashedPotatoes);
		pnlSides.add(chkBakedPotatoes);
		pnlSides.add(chkMedley);
		pnlSides.add(chkRice);
		pnlSides.add(chkColesaw);
		pnlSides.add(chkCorn);
		pnlSides.add(chkSalad);
		
		pnlBeverages.add(lblCoke);
		pnlBeverages.add(cbCoke);
		pnlBeverages.add(lblSprite);
		pnlBeverages.add(cbSprite);
		pnlBeverages.add(lblOrange);
		pnlBeverages.add(cbOrange);
		pnlBeverages.add(lblRootBeer);
		pnlBeverages.add(cbRootBeer);
		
		pnlMiddle1.add(lblMiddle);
		
		pnlMiddle2.add(lblDinners);
		pnlMiddle2.add(lblSides);
		pnlMiddle2.add(lblBeverages);
		
		pnlBottomLeft.add(lblPaymentOptions);
		
		pnlBottomCenter.add(lblSubTotal1);
		pnlBottomCenter.add(lblSubTotal2);
		pnlBottomCenter.add(lblDeliveryFee1);
		pnlBottomCenter.add(lblDeliveryFee2);
		pnlBottomCenter.add(lblHST1);
		pnlBottomCenter.add(lblHST2);
		pnlBottomCenter.add(lblGrandTotal1);
		pnlBottomCenter.add(lblGrandTotal2);
		
		pnlBottomRight.add(btnCalculate);
		pnlBottomRight.add(btnClear);
		pnlBottomRight.add(btnCheckout);
		pnlBottomRight.add(btnExit);
		
		//Specify the constraints of the components by using the GridBagConstraints class
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weightx = 100;
		gc.weighty = 100;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.NORTH;
		gc.fill = GridBagConstraints.NONE;
		
		//Alter the constraints for placing certain panels in specific cells
		setLayout(new GridBagLayout()); //set main panel's layout to GridBagLayout. Add all sub panels to main panel
		gc.gridwidth = 3;
		add(lblTitle, gc);
		
		gc.gridy = 1;
		gc.gridwidth = 1;
		add(pnlDinners, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(pnlSides, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		add(pnlBeverages, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 3;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 55, 5, 30);
		add(pnlMiddle1, gc);
		
		gc.gridy = 3;
		gc.insets = new Insets(5, 50, 5, 20);
		add(pnlMiddle2, gc);
		
		gc.gridy = 4;
		gc.gridwidth = 1;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.NORTH;
		add(pnlBottomLeft, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(pnlBottomCenter, gc);
		
		gc.gridx = 2;
		add(pnlBottomRight, gc);
		
		//Declare JFrame with title and other properties, as well as adding the main panel to the JFrame
		JFrame frame = new JFrame("Swiss Chalet");
		
		frame.setContentPane(this);
		frame.pack(); // Ensure that the frame is only as big as the combined space of all the JComponents
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.addWindowListener(new CloseListener()); // Add the inner class for closing the program, CloseListener
		Image icon = Toolkit.getDefaultToolkit().getImage("sclogo.png"); // Set the frame icon
		frame.setIconImage(icon);
		frame.setVisible(true);
	}
	
	// Create an inner class that specifically listens for JRadioButtons
	class DinnersListener implements ItemListener
	{
		public void itemStateChanged (ItemEvent e)
		{
			//Create one JRadioButton that converts everything it listens for into a radio button
			rbChecked = (JRadioButton) e.getSource(); 
			
			if (rbChecked.isSelected())
			{
				//Convert the price of the JRadioButton selected into a double
				dinnerTotal = Double.parseDouble(rbChecked.getActionCommand());
			}
			
			/* Add the actionCommand to the Dinner label. Also enable the calculate and checkout button now that
			 * the conditions have been met to proceed.
			 */
			lblDinners.setText(fm.format(dinnerTotal));
			btnCalculate.setEnabled(true);
			btnCheckout.setEnabled(true);
		}
	};
	
	// Create an inner class that specifically listens for JCheckBoxes
	class SidesListener implements ItemListener
	{
		public void itemStateChanged (ItemEvent e)
		{
			//Create one JCheckBox that converts everything it listens for into a check box
			JCheckBox chkChecked = (JCheckBox) e.getSource();
			
			if (chkChecked.isSelected())
			{
				count++; //If a JCheckBox is clicked, the counter will increase by one.
				
				//When the counter is greater than 2, the user will be charged for extra side dishes
				if (count > 2)
				{
					sidesTotal += 1.99; //Price begins adding to side dish total once counter is greater than 2
				}
				
				/* On each JCheckBox selection, the string dishString will add the name of the JCheckBox selected 
				 * at the end of the string. Also, the end of the substring will conclude onto the next line
				 * so the string as a whole visually makes sense
				 */
				dishString = dishString.concat(chkChecked.getName() + "\n");
				
			}
			//Actions when a JCheckBox is unchecked
			else
			{
				count--; //If a JCheckBox is unchecked, the counter will continuously decrease by one.
				sidesTotal -= 1.99; //Price is deducted on each initiated uncheck.
				
				if (count <= 2)
				{
					/* To avoid getting an output of -0.00 when making deductions, sidesTotal will be set to 0
					 * when count is less than 2
					 */
					sidesTotal = 0; 
				}
				
				/* If a JCheckBox were to be unchecked, the string as a whole will replace the last
				 * check box checked, along with the next line with nothing ("").
				 */
				dishString = dishString.replaceAll(chkChecked.getName() + "\n", "");
				
			}
			
			//Outputs the calculated total price of the side dishes in a JLabel
			lblSides.setText(fm.format(sidesTotal));
		}
	};
	
	// Create an inner class that specifically listens for JComboBoxes
	class BeveragesListener implements ItemListener
	{
		public void itemStateChanged (ItemEvent e)
		{
			/* Makes sure the amount is zero by default during each interaction with the JComboBoxes. This way the
			 * value does not continually add numbers even when the JComboBox was set back to 0.
			 */
			amount = 0;
			
			//Set each array value to respective JComboBox's selected item.
			quantity[0] = (String) cbCoke.getSelectedItem();
			quantity[1] = (String) cbSprite.getSelectedItem();
			quantity[2] = (String) cbOrange.getSelectedItem();
			quantity[3] = (String) cbRootBeer.getSelectedItem();
			
			//Add the amount to the value of each combo box quantity.
			for (int i = 0; i < 4; i++)
			{
				amount += Integer.parseInt(quantity[i]);
			}
			
			//Multiply the total amount by 0.99 to get the total price of the beverages. Output it in a JLabel
			beveragesTotal = amount * 0.99;
			lblBeverages.setText(fm.format(beveragesTotal));
			
		}
	};
	
	//Method that sets actions for the JButtons.
	public void actionPerformed (ActionEvent e)
	{
		//Actions if the calculate button were to be clicked
		if (e.getSource() == btnCalculate)
		{
			//Add all prices to get sub total, then multiply it by 0.13 to get the HST
			subTotal = dinnerTotal + sidesTotal + beveragesTotal;
			HST = subTotal * 0.13;
			
			//Output said prices in their respective JLabels
			lblSubTotal2.setText(fm.format(subTotal));
			lblHST2.setText(fm.format(HST));
			
			//If sub total is 15 or greater, set JLabel to green, change text and set delivery value to zero
			if (subTotal >= 15)
			{
				lblDeliveryFee2.setBackground(Color.GREEN);
				lblDeliveryFee2.setText("FREE");
				deliveryFee = 0;
			}
			//Otherwise, change the back colour back to its default, with delivery fee set to 3,output its price
			else 
			{
				lblDeliveryFee2.setBackground(null);
				deliveryFee = 3;
				lblDeliveryFee2.setText(fm.format(deliveryFee));
			}
			
			//Add all final prices to get the grand total, along with outputting it in a JLabel
			grandTotal = subTotal + HST + deliveryFee;
			lblGrandTotal2.setText(fm.format(grandTotal));
		}
		
		//Commands for the clear button
		else if (e.getSource() == btnClear)
		{
			//Clear the ButtonGroup, set dinnerTotal to 0, and reset dinner JLabel
			group.clearSelection();
			dinnerTotal = 0;
			lblDinners.setText(fm.format(dinnerTotal));
			
			/* Clear all JCheckBoxes. Each JCheckBox reset will deduct it's given price along with the counter, 
			 * bringing them all back to 0
			 */
			chkFries.setSelected(false);
			chkBakedPotatoes.setSelected(false);
			chkRice.setSelected(false);
			chkCorn.setSelected(false);
			chkMashedPotatoes.setSelected(false);
			chkMedley.setSelected(false);
			chkColesaw.setSelected(false);
			chkSalad.setSelected(false);
			
			//Set all JComboBoxes to 0
			cbCoke.setSelectedIndex(0);
			cbSprite.setSelectedIndex(0);
			cbOrange.setSelectedIndex(0);
			cbRootBeer.setSelectedIndex(0);
			
			//Reset all mathematical pricing to 0
			subTotal = 0;
			HST = 0;
			deliveryFee = 0;
			grandTotal = 0;
			
			//Refresh all price JLabels, reset deliveryFee back colour to default.
			lblSubTotal2.setText(fm.format(subTotal));
			lblDeliveryFee2.setBackground(null);
			lblDeliveryFee2.setText(fm.format(deliveryFee));
			lblHST2.setText(fm.format(HST));
			lblGrandTotal2.setText(fm.format(grandTotal));
			
			//Disable both calculate and checkout button until user meets the requirements again(selecting a dinner)
			btnCalculate.setEnabled(false);
			btnCheckout.setEnabled(false);
			
			//Set the side dish string to nothing
			dishString = "";
		}
		
		//Set the commands for the checkout button
		else if (e.getSource() == btnCheckout)
		{
			//Calculate pricing even if the user does not click the Calculate button
			subTotal = dinnerTotal + sidesTotal + beveragesTotal;
			HST = subTotal * 0.13;
			grandTotal = subTotal + HST + deliveryFee;
			
			/* Make string array/strings for the text of the drinks, along with the title labels for side dishes and
			 * beverages. Set them to nothing by default, so that if certain conditions are not met, they will show 
			 * up as nothing. (e.g: If no beverages are chosen the beverage title will not appear on the 
			 * verification dialog
			 */
			String[] drink = new String[4];
			String dishTitle = "";
			String beverageTitle = "";
			
			/* If at least one side dish is selected, set the title of the side dish title so that it appears in the
			 * verification dialog of the JOptionPane
			 */
			if (count > 0)
			{
				dishTitle = "\nSIDE DISHES\n------------------------------\n";
			}
			
			//Set up names for the drinks in their respected index
			drink[0] = " Coke\n";
			drink[1] = " Sprite\n";
			drink[2] = " Orange\n";
			drink[3] = " Root Beer\n";
			
			for (int i = 0; i < 4; i++)
			{
				/* If the quantity is 0 by the time the checkout button is clicked, set the quantity to nothing
				 * and set the drink names to nothing, so they will not appear. Also check if quantity is
				 * nothing in case that quantity was set to nothing by the time checkout button is pressed again
				 */
				if (quantity[i].equals("0") || quantity[i].equals(""))
				{
					quantity[i] = "";
					drink[i] = "";
				}
				/* If quantity is not 0 or is not nothing, set the title of the beverages, while drinks were 
				 * left with their names
				 */
				else
				{
					beverageTitle = "\nBEVERAGES\n------------------------------\n";
				}
			}
			
			/* Notify that the user still has a certain amount of free side dish choices and ask them if they 
			 * would like to go back to make a selection. If count is 2 or greater, set the boolean to true (will
			 * skip the notification JOptionPane). Otherwise, set to false, which will initiation the 
			 * notification
			 */
			if (count >= 2)
			{
				optionSelection = true;
			}
			else
			{
				optionSelection = false;
			}
			
			if (optionSelection == false)
			{
				int option = JOptionPane.showConfirmDialog(null, "You have chosen " + count + 
					" side dish(es). Customers can choose up to two side \ndishes free of charge. " +
					"Would you like to go back to make a selection?", 
						"Swiss Chalet", 0, 0, new ImageIcon("sclogo.png"));
				
				/* If user is aware of their decision and chooses not to make a selection, set the boolean to true,
				 * which will bring him/her to the verification JOptionPane. Otherwise, set the boolean to false
				 * and bring him/her back to the main frame for another selection.
				 */
				if (option == JOptionPane.NO_OPTION)
				{
					optionSelection = true;
				}
				else if (option == JOptionPane.YES_OPTION)
				{
					optionSelection = false;
				}
			}
			if (optionSelection == true)
			{
				//Confirm if their order is correct, and list all items that have been selected.
				int option = JOptionPane.showConfirmDialog(null, "Is this order correct? You have ordered: \n\n " +
						"CHICKEN DINNER\n------------------------------\n" 
						+ rbChecked.getName() + "\n" + dishTitle + dishString + beverageTitle + quantity[0] + drink[0] +
						 quantity[1] + drink[1] + quantity[2] + drink[2] + quantity[3] + drink[3] + 
						 "\n                 Total: " +
						 fm.format(grandTotal), 
						"Swiss Chalet", JOptionPane.YES_NO_OPTION);
				
				//If their order is correct, initiate a closing JOptionPane message and terminate program
				if (option == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null,  "Thank you for choosing Swiss Chalet!" +
							" Your order \nwill be delivered in 30 minutes or it's free!", "Swiss Chalet",
							0, new ImageIcon("sclogo.png"));
					
					System.exit(0);
				}
			} //If they click NO, doing nothing will bring the user back to the frame
		}
		
		//Set commands for the last button, the Exit Button
		else
		{
			//Ask the user if they want to quit in the form of an integer if they click the exit button on the frame
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Swiss Chalet", 
					JOptionPane.YES_NO_OPTION);
			
			// If the the user clicks YES, terminate the program
			if (option == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null,  "Thank you for choosing Swiss Chalet!", "Swiss Chalet", 
						JOptionPane.INFORMATION_MESSAGE);
				
				System.exit(0);
			}
			
			/* If the user clicks NO or clicks the exit button on the JOptionPane, doing nothing will bring 
			 * the user back to the main frame
			 */
		}
	}
	
	//Make a nested class for the top exit button of the frame without implementing unnecessary methods
	class CloseListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			btnExit.doClick(); //Repeat execution of exit button, which initiates termination of program
		}
	};
}