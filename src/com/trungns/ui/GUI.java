package com.trungns.ui;

import java.awt.CardLayout;

import javax.swing.JFrame;


public class GUI extends JFrame{
	public static final int WIDH_FRAME = 1000;
	public static final int HEIGHT_FRAME = 600;
	public static final int WIDTH_GAME = 800;
	public static final int HEIGHT_GAME = 600;

	private MyContainer myContainer;

	public GUI() {
		initGUI();
		initComps();
		addComps();
	}

	private void initGUI() {
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDH_FRAME, HEIGHT_FRAME);
		setLocationRelativeTo(null);
	}

	private void initComps() {
		myContainer = new MyContainer();
		//myContainer.setGuiExit(this);
	}

	private void addComps() {
		add(myContainer);
	}
}
