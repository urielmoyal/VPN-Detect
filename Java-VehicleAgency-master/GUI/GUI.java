package GUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import Agancy.Ampv;
import Agancy.Bike;
import Agancy.CruiseShip;
import Agancy.Drone;
import Agancy.ElectricBike;
import Agancy.Frigate;
import Agancy.HybridPlane;
import Agancy.Jeap;
import Agancy.SpyDrone;
import Vehicles.Sea_Vehicle;
import Vehicles.Vehicle;

public class GUI implements ActionListener {
	ImageIcon loadingBar = createImageIcon("images/Gif.gif");
	JLabel loadingLable = new JLabel(loadingBar);
	static JFrame loadingFrame = new JFrame("Updating database… Please wait");

	static Vector<Vehicle> vehicle = new Vector<>();
	static Vector<Sea_Vehicle> seaVehicle = new Vector<>();
	static Vector<JButton> carB = new Vector<>();
	static Vector<JLabel> carL = new Vector<>();
	
	static int s, t, carIndex;
	
	static JFrame carFrame = new JFrame("Car Agancy");

	static JPanel Panel3 = new JPanel();
	
	static JFrame frame = new JFrame("Agancy Building");
	
	JPanel combPanel = new JPanel();
	JPanel carsPanel = new JPanel();
	
    ImageIcon[] images = new ImageIcon[4];
    Integer[] intArray = new Integer[4];
    JComboBox<Integer> picList;
    ComboBoxRenderer renderer;
	
	JButton jeapButton = new JButton("Add Jeap");
	JButton frigateButton = new JButton("Add Frigate");
	JButton SpyDroneButton = new JButton("Add SpyDrone");
	JButton droneButton = new JButton("Add Drone");
	JButton AmpvButton = new JButton("Add Ampv");
	JButton bikeButton = new JButton("Add Bike");
	JButton CruiseshipButton = new JButton("Add CruiseShip");
	JButton HybridPlaneButton = new JButton("Add H-Plane");
	JButton ElectricBikeButton = new JButton("Add E-Bike");
	JButton finishButton = new JButton("Finish");
  
	JButton stockButton = new JButton("Stock Report");
  	
	String picture;

	JButton addImageButton = new JButton("Upload Image");

	JLabel model = new JLabel("Model: ");
	JTextField modeltext = new JTextField(10);
	
	JLabel distance = new JLabel("Distance: ");
	JTextField distancetext = new JTextField(10);

	JLabel max_speed = new JLabel("Max Speed: ");
	JTextField max_speedtext = new JTextField(10);
	
	JLabel max_pass = new JLabel("Max Passengers: ");
	JTextField max_passtext = new JTextField(10);
	
	JLabel fuel = new JLabel("Fuel: ");
	JTextField fueltext = new JTextField(10);
	
	JLabel lifetime = new JLabel("Lifetime: ");
	JTextField lifetimetext = new JTextField(10);
	
	JLabel wind = new JLabel("Wind: ");
	JTextField windtext = new JTextField(10);
	
	JLabel flag = new JLabel("Flag: ");
	JTextField flagtext = new JTextField(10);
	
	JLabel power = new JLabel("Power: ");
	JTextField powertext = new JTextField(10);
	
	JLabel wheel_num = new JLabel("Wheel Num: ");
	JTextField wheel_numtext = new JTextField(10);
	
	JLabel road = new JLabel("Road: ");
	JTextField roadtext = new JTextField(10);
	
  	String[] names = {"","Jeap","Frigate","SpyDrone","Drone","Ampv","Bike","CruiseShip","HybridPlane","ElectricBike"};
  	JComboBox<String> comb = new JComboBox<String>(names);
  	
  	public GUI() {
  		loadingFrame.add(loadingLable);
  		loadingFrame.setSize(360,150);
  		loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  		loadingFrame.setResizable(false);
  		loadingFrame.setLocation(841, 97);
  		
  		frame.add(combPanel);
  		frame.add(carsPanel, BorderLayout.EAST);
  		
        for (int i = 0; i < 4 ; i++) {
            intArray[i] = new Integer(i);
        }

        picList = new JComboBox<Integer>(intArray);
        renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(110, 120));
        picList.setMaximumRowCount(2);
        picList.setRenderer(renderer);


  		JLabel chooseCar = new JLabel("Choose car: ");
  		JLabel choosePic = new JLabel("Choose Picture: ");
  		combPanel.add(chooseCar);
  		combPanel.add(comb);
  		combPanel.add(picList);
  		combPanel.add(choosePic);
  		combPanel.add(addImageButton);
  		combPanel.add(stockButton);

  		
  		carsPanel.setLayout(new GridLayout(10,100));
  		comb.setMaximumRowCount(4);
  		combPanel.setLayout(new BorderLayout());
  		
  		combPanel.getComponent(0).setBounds(90,170, 100, 100);
  		combPanel.getComponent(1).setBounds(175,205, 100, 30);
  		combPanel.getComponent(2).setBounds(3,210, 130, 100);
  		combPanel.getComponent(3).setBounds(5,150, 100, 100);
  		combPanel.getComponent(4).setBounds(5,170, 115, 20);
  		combPanel.getComponent(5).setBounds(5,148, 115, 20);
  		combPanel.getComponent(2).setVisible(false);
  		combPanel.getComponent(3).setVisible(false);
  		combPanel.getComponent(4).setVisible(false);
  		combPanel.getComponent(5).setVisible(false);
  		
  		addImageButton.addActionListener(this);
  		addImageButton.addMouseListener(new MouseAdapter()
			{public void mouseEntered(MouseEvent evt){addImageButton.setToolTipText("130 x 130");}});
  		stockButton.addActionListener(this);
  		comb.addActionListener(this);

  		jeapButton.addActionListener(this);
  		frigateButton.addActionListener(this);
  		SpyDroneButton.addActionListener(this);
  		droneButton.addActionListener(this);
  		AmpvButton.addActionListener(this);
  		bikeButton.addActionListener(this);
  		CruiseshipButton.addActionListener(this);
  		HybridPlaneButton.addActionListener(this);
  		ElectricBikeButton.addActionListener(this);
  		finishButton.addActionListener(this);

		frame.setResizable(false);
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		frame.setVisible(true);
  		frame.setSize(400,500);
  	}
  	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == addImageButton) {

			String name = comb.getSelectedItem().toString() + "4" + ".jpg";
	
			FileDialog fd = new FileDialog(new Frame(), "Please choose a file (130 x 130):",FileDialog.LOAD);
			fd.setVisible(true);
			
			if (fd.getFile() != null) {
				File sourceFile = new File(fd.getDirectory() + fd.getFile());
				File destinationFile = new File("images\\" + name);
				try {
				FileInputStream fileInputStream = new FileInputStream(sourceFile);
				FileOutputStream fileOutputStream = new FileOutputStream(
				                destinationFile);
	
				int bufferSize;
				byte[] bufffer = new byte[512];
					while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
					    fileOutputStream.write(bufffer, 0, bufferSize);
					}
				fileInputStream.close();
				fileOutputStream.close();
				
				} catch (IOException e1){
					e1.printStackTrace();
				}
				comb.setSelectedIndex(comb.getSelectedIndex());
				picList.setSelectedIndex(3);
			}
		}
		
		if (e.getSource().equals(comb)) {
			String cars = comb.getItemAt(comb.getSelectedIndex());

			switch(cars) {
			
			case "":
		  		combPanel.getComponent(0).setBounds(90,170, 100, 100);
		  		combPanel.getComponent(1).setBounds(175,205, 100, 30);
		  		combPanel.getComponent(2).setVisible(false);
		  		combPanel.getComponent(3).setVisible(false);
		  		combPanel.getComponent(4).setVisible(false);
		  		combPanel.getComponent(5).setVisible(false);
		  		
				carsPanel.removeAll();
				
		  		frame.repaint();
		  		frame.validate();
		  		break;
				
			case "Jeap":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] jeapStrings = {"Jeap1", "Jeap2", "Jeap3" ,"Jeap4"};

				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		 
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(fuel);
		  		carsPanel.add(fueltext);
		  		
		  		carsPanel.add(lifetime);
		  		carsPanel.add(lifetimetext);
		  		
		  		carsPanel.add(jeapButton);
		  		carsPanel.add(finishButton);

		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < jeapStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + jeapStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(jeapStrings[i]);
		            }
		        }
		  		
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "Frigate":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] frigateStrings = {"Frigate1", "Frigate2", "Frigate3", "Frigate4"};

				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_pass);
		  		carsPanel.add(max_passtext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(wind);
		  		carsPanel.add(windtext);
		  		
		  		carsPanel.add(flag);
		  		carsPanel.add(flagtext);
		  		
		  		carsPanel.add(frigateButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < frigateStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + frigateStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(frigateStrings[i]);
		            }
		        }
		  		
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "SpyDrone":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] spyDroneStrings = {"SpyDrone1", "SpyDrone2", "SpyDrone3", "SpyDrone4"};
		  		
				carsPanel.removeAll();

		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(power);
		  		carsPanel.add(powertext);
		  		
		  		carsPanel.add(SpyDroneButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < spyDroneStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + spyDroneStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(spyDroneStrings[i]);
		            }
		        }
		  		
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "Drone":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] droneStrings = {"Drone1", "Drone2", "Drone3", "Drone4"};
		  		
				carsPanel.removeAll();
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(droneButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < droneStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + droneStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(droneStrings[i]);
		            }
		        }
		  		
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "Ampv":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] ampvStrings = {"Ampv1", "Ampv2", "Ampv3", "Ampv4"};
		  		
				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_pass);
		  		carsPanel.add(max_passtext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(wind);
		  		carsPanel.add(windtext);
		  		
		  		carsPanel.add(flag);
		  		carsPanel.add(flagtext);
		  		
		  		carsPanel.add(lifetime);
		  		carsPanel.add(lifetimetext);
		  		
		  		carsPanel.add(fuel);
		  		carsPanel.add(fueltext);
		  		
		  		carsPanel.add(wheel_num);
		  		carsPanel.add(wheel_numtext);
		  		
		  		carsPanel.add(AmpvButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < ampvStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + ampvStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(ampvStrings[i]);
		            }
		        }

		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "Bike":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] bikeStrings = {"Bike1", "Bike2", "Bike3", "Bike4"};
		  		
				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_pass);
		  		carsPanel.add(max_passtext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(wheel_num);
		  		carsPanel.add(wheel_numtext);
		  		
		  		carsPanel.add(road);
		  		carsPanel.add(roadtext);
		  		
		  		carsPanel.add(bikeButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < bikeStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + bikeStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(bikeStrings[i]);
		            }
		        }
		  		
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "CruiseShip":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] cruiseShipStrings = {"CruiseShip1", "CruiseShip2", "CruiseShip3", "CruiseShip4"};
		  		
				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_pass);
		  		carsPanel.add(max_passtext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(flag);
		  		carsPanel.add(flagtext);
		  		
		  		carsPanel.add(lifetime);
		  		carsPanel.add(lifetimetext);
		  		
		  		carsPanel.add(fuel);
		  		carsPanel.add(fueltext);	
		  		
		  		carsPanel.add(CruiseshipButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < cruiseShipStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + cruiseShipStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(cruiseShipStrings[i]);
		            }
		        }
		  
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "HybridPlane":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] HybridPlaneStrings = {"HybridPlane1", "HybridPlane2", "HybridPlane3", "HybridPlane4"};
		  		
				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_pass);
		  		carsPanel.add(max_passtext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(wind);
		  		carsPanel.add(windtext);
		  		
		  		carsPanel.add(flag);
		  		carsPanel.add(flagtext);
		  		
		  		carsPanel.add(lifetime);
		  		carsPanel.add(lifetimetext);
		  		
		  		carsPanel.add(fuel);
		  		carsPanel.add(fueltext);
		  		
		  		carsPanel.add(wheel_num);
		  		carsPanel.add(wheel_numtext);
		  		
		  		carsPanel.add(HybridPlaneButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < HybridPlaneStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + HybridPlaneStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(HybridPlaneStrings[i]);
		            }
		        }

		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			case "ElectricBike":
		  		combPanel.getComponent(0).setBounds(8,-30, 100, 100);
		  		combPanel.getComponent(1).setBounds(5,35, 100, 30);
		  		
		  		picList.setSelectedIndex(0);
		  	    String[] ElectricBikeStrings = {"ElectricBike1", "ElectricBike2", "ElectricBike3", "ElectricBike4"};
		  		
				carsPanel.removeAll();

		  		carsPanel.add(model);
		  		carsPanel.add(modeltext);
		  		
		  		carsPanel.add(distance);
		  		carsPanel.add(distancetext);
		  		
		  		carsPanel.add(max_pass);
		  		carsPanel.add(max_passtext);
		  		
		  		carsPanel.add(max_speed);
		  		carsPanel.add(max_speedtext);
		  		
		  		carsPanel.add(road);
		  		carsPanel.add(roadtext);
		  		
		  		carsPanel.add(lifetime);
		  		carsPanel.add(lifetimetext);
		  		
		  		carsPanel.add(ElectricBikeButton);
		  		carsPanel.add(finishButton);
		  		
		  		combPanel.getComponent(2).setVisible(true);
		  		combPanel.getComponent(3).setVisible(true);
		  		combPanel.getComponent(4).setVisible(true);
		  		combPanel.getComponent(5).setVisible(true);
		  		
		        for (int i = 0; i < ElectricBikeStrings.length ; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("images/" + ElectricBikeStrings[i] + ".jpg");
		            if (images[i] != null) {
		                images[i].setDescription(ElectricBikeStrings[i]);
		            }
		        }
		  		
		  		frame.repaint();
		  		frame.validate();
		  		break;
		  		
			}	
		}
		
			if (images[picList.getSelectedIndex()] == null)
				picture = "NoImage";
			else
				picture = images[picList.getSelectedIndex()].getDescription();

			try {
				if (e.getSource() == jeapButton){
					vehicle.add(new Jeap(modeltext.getText(),Float.parseFloat(distancetext.getText()),Float.parseFloat(max_speedtext.getText()),Float.parseFloat(fueltext.getText()),Float.parseFloat(lifetimetext.getText()),picture + ".jpg"));
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
				if (e.getSource() == frigateButton){
					Vehicle temp = new Frigate(modeltext.getText(),Float.parseFloat(distancetext.getText()),Integer.parseInt(max_passtext.getText()), Float.parseFloat(max_speedtext.getText()), windtext.getText(), flagtext.getText(),picture + ".jpg");
					vehicle.add(temp);
					seaVehicle.add((Sea_Vehicle)temp);
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
				if (e.getSource() == SpyDroneButton){
					if(powertext.getText().equals("Manual") || (powertext.getText().equals("Electric")) || powertext.getText().equals("manual") || (powertext.getText().equals("electric"))){
						vehicle.add(new SpyDrone(Float.parseFloat(distancetext.getText()), powertext.getText(),picture + ".jpg"));
			  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
			  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
						JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
						if(Stock.stockFrame.isShowing())
							stockButton.doClick();
						if(carFrame.isShowing())
							finishButton.doClick();
					}
					else
						JOptionPane.showMessageDialog(null, "Enter 'Manual' Or 'Electric'", "Error!",JOptionPane.ERROR_MESSAGE);
				}
				if (e.getSource() == droneButton){
					vehicle.add(new Drone(Float.parseFloat(distancetext.getText()),picture + ".jpg"));
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
					
				}
				if (e.getSource() == AmpvButton){
					Vehicle temp = new Ampv(modeltext.getText(),Float.parseFloat(distancetext.getText()), Integer.parseInt(max_passtext.getText()), Float.parseFloat(max_speedtext.getText()), windtext.getText(), flagtext.getText() , Float.parseFloat(lifetimetext.getText()), Float.parseFloat(fueltext.getText()),Integer.parseInt(wheel_numtext.getText()),picture + ".jpg");
					vehicle.add(temp);
					seaVehicle.add((Sea_Vehicle)temp);
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
				if (e.getSource() == bikeButton){
					vehicle.add(new Bike(modeltext.getText(),Float.parseFloat(distancetext.getText()) ,Integer.parseInt(max_passtext.getText()), Float.parseFloat(max_speedtext.getText()),Integer.parseInt(wheel_numtext.getText()), roadtext.getText(),picture + ".jpg"));
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
					if (e.getSource() == CruiseshipButton){
					Vehicle temp = new CruiseShip(modeltext.getText(),Float.parseFloat(distancetext.getText()), Integer.parseInt(max_passtext.getText()), Float.parseFloat(max_speedtext.getText()), flagtext.getText() , Float.parseFloat(lifetimetext.getText()), Float.parseFloat(fueltext.getText()),picture + ".jpg");
					vehicle.add(temp);
					seaVehicle.add((Sea_Vehicle)temp);
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
					if (e.getSource() == HybridPlaneButton){
					Vehicle temp = new HybridPlane(modeltext.getText(),Float.parseFloat(distancetext.getText()), Integer.parseInt(max_passtext.getText()), Float.parseFloat(max_speedtext.getText()), windtext.getText(), flagtext.getText() , Float.parseFloat(lifetimetext.getText()), Float.parseFloat(fueltext.getText()),Integer.parseInt(wheel_numtext.getText()),picture + ".jpg");
					vehicle.add(temp);
					seaVehicle.add((Sea_Vehicle)temp);
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
				if (e.getSource() == ElectricBikeButton){
					vehicle.add(new ElectricBike(modeltext.getText(),Float.parseFloat(distancetext.getText()) ,Integer.parseInt(max_passtext.getText()), Float.parseFloat(max_speedtext.getText()), roadtext.getText(), Float.parseFloat(lifetimetext.getText()), picture + ".jpg"));
		  			carB.add(new JButton(createImageIcon("images\\" + picture + ".jpg")));
		  			carL.add(new JLabel(createImageIcon("images\\" + picture + ".jpg")));
					JOptionPane.showMessageDialog(null, "Added Successfully!", "Success!", JOptionPane.DEFAULT_OPTION);
					if(Stock.stockFrame.isShowing())
						stockButton.doClick();
					if(carFrame.isShowing())
						finishButton.doClick();
				}
					
				
			}catch(NumberFormatException ee) {
			JOptionPane.showMessageDialog(null, "Input Error!", "Error!",JOptionPane.ERROR_MESSAGE);
		}
			
			if (e.getSource() == stockButton){
	  			Thread stock = new Thread(new Stock());
	  			stock.start();
			}
	  		
			if (e.getSource() == finishButton){
	  			Thread finish = new Thread(new Finish());
	  			finish.start();
			}
	}
		
			  				
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else
                return null;
    }
	
    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
        private Font uhOhFont;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus) {
            int selectedIndex = ((Integer)value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            ImageIcon icon = images[selectedIndex];
            setIcon(icon);
            if (icon == null) {
            	setUhOhText("No Image",list.getFont());
            }

            return this;
        }

        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) {
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }

    
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}