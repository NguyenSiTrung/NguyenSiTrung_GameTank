package com.trungns.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class MenuPanel extends BaseContainer implements ActionListener{
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ImageIcon iconHinhnen = new ImageIcon(getClass().getResource(
				"/IMAGE/bbgg.jpg").getPath());
		Image hinhNen = iconHinhnen.getImage();
		g2d.drawImage(hinhNen, 0, 0, GUI.WIDH_FRAME, GUI.HEIGHT_FRAME, this);
	}

	private JButton btPlayGame;
	private JButton btExitGame;
	private CardLayout mCard;
	//private ActionMenuPanal actionMenuPanal;

	private static final String EXIT_ID = "EXIT_ID";
	private static final String PLAY_ID = "PLAY_ID";

	public void setCard(CardLayout mCard) {
		this.mCard = mCard;
	}

	@Override
	protected void initContainer() {
		//setBackground(Color.BLUE);
		setLayout(null);
	}

	@Override
	protected void initComps() {
		btPlayGame = new JButton("Play game");
		btPlayGame.setBounds(GUI.WIDH_FRAME / 2 - 60, GUI.HEIGHT_FRAME / 2,
				120, 50);
		btPlayGame.setBackground(Color.YELLOW);
		btPlayGame.setForeground(Color.BLACK);
		btPlayGame.setActionCommand(PLAY_ID);
		btPlayGame.addActionListener(this);

		btExitGame = new JButton("Exit game");
		btExitGame.setBounds(GUI.WIDH_FRAME / 2 - 60,
				GUI.HEIGHT_FRAME / 2 + 60, 120, 50);
		btExitGame.setBackground(Color.YELLOW);
		btExitGame.setForeground(Color.BLACK);
		btExitGame.setActionCommand(EXIT_ID);
		btExitGame.addActionListener(this);
	}

	@Override
	protected void addComps() {
		add(btPlayGame);
		add(btExitGame);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource().equals(btExitGame)) {
			System.exit(0);
		} else if (event.getSource().equals(btPlayGame)) {
					mCard.show(getParent(), MyContainer.PLAY_ID);
					getParent().getComponent(Integer.parseInt(MyContainer.PLAY_ID))
					.requestFocus();
		}
	}
}
