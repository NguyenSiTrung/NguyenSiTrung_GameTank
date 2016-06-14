package com.trungns.ui;


import java.awt.CardLayout;
import java.awt.Color;

import com.trungns.ui.PlayGamePanel.OnPlayGameAgainListener;


public class MyContainer extends BaseContainer implements OnPlayGameAgainListener{
	public static final String MENU_ID = "0";
	public static final String PLAY_ID = "1";

	private MenuPanel menuPanel;
	private PlayGamePanel playGamePanel;

	private CardLayout mCard;

	public MyContainer() {
		super();
		
	}

	@Override
	protected void initContainer() {
		setBackground(Color.WHITE);
		mCard = new CardLayout();
		setLayout(mCard);
	}

	@Override
	protected void initComps() {
		menuPanel = new MenuPanel();
		playGamePanel = new PlayGamePanel();
		menuPanel.setCard(mCard);
		playGamePanel.setOnPlayGameAgainListener(this);
	}
	
	

	@Override
	protected void addComps() {
		add(menuPanel, MENU_ID);
		add(playGamePanel, PLAY_ID);
	}

	@Override
	public void restartGame() {
		// TODO Auto-generated method stub
		menuPanel.setVisible(true);
		playGamePanel.setVisible(false);
	}
}
