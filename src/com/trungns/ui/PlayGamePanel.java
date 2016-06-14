package com.trungns.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.trungns.bullet.Bullet;
import com.trungns.manager.Manager;
import com.trungns.map.Map;
import com.trungns.shape.Bird;
import com.trungns.sound.SoundManager;
import com.trungns.tank.Tank;

public class PlayGamePanel extends BaseContainer {
	private Manager mng;
	private Map map1 = new Map();
	private BitSet mKeyValue;
	private JLabel score;
	private JLabel scoreTf;
	private JLabel madeBy;
	private JLabel email;
	private JLabel hpTank;
	private JLabel gthpTank;
	private JLabel hpBird;
	private JLabel gthpBird;
	private OnPlayGameAgainListener listener;
	private Font fontScore;
	private Font fontMadeByAndEmail;
	public static final int DIEM_SO_KHI_BAN_DUOC_1_TANKAUTO = 5;

	private SoundManager soundManager = new SoundManager();

	public void setOnPlayGameAgainListener(OnPlayGameAgainListener listener) {
		this.listener = listener;
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int time = 0;
			boolean kt = true;
			while (kt) {
				mng.moveTankAuto(time, map1);
				mng.tankAutoFire(time);
				if (mKeyValue.get(KeyEvent.VK_ENTER)) {
					mng.tankFire(time);
					soundManager.getSoundShoot().play();
				}
				if (mKeyValue.get(KeyEvent.VK_UP)) {
					mng.changeOrient(Bullet.UP);
					mng.moveTank(time, map1);
					// soundManager.getSoundMove().play();

				}
				if (mKeyValue.get(KeyEvent.VK_DOWN)) {
					mng.changeOrient(Bullet.DOWN);
					mng.moveTank(time, map1);
					// soundManager.getSoundMove().play();

				}
				if (mKeyValue.get(KeyEvent.VK_LEFT)) {
					mng.changeOrient(Bullet.LEFT);
					mng.moveTank(time, map1);
					// soundManager.getSoundMove().play();

				}
				if (mKeyValue.get(KeyEvent.VK_RIGHT)) {
					mng.changeOrient(Bullet.RIGHT);
					mng.moveTank(time, map1);
					// .getSoundMove().play();

				}

				mng.moveBullets(time, map1);
				mng.kiemTraTankAuToIsShot();
				if (scoreTf.getText() == "") {
					scoreTf.setText("0");
				}
				if (mng.tankAuToDie()) {
					soundManager.getSoundShootTank().play();
					int diemLucTruoc = Integer.parseInt(scoreTf.getText());
					int diemLucSau = diemLucTruoc
							+ DIEM_SO_KHI_BAN_DUOC_1_TANKAUTO;
					String diemLucSauString = "" + diemLucSau;
					scoreTf.setText(diemLucSauString);
				}
				if (mng.checkYouWin()) {
					JOptionPane.showMessageDialog(null, "YOU WIN");
					stopGame();
				}

				String hp_Bird = Bird.HP_BIRD + "";
				if (gthpBird.getText() == "") {
					gthpBird.setText(hp_Bird);
				}
				if (mng.isBirdIsShot()) {
					int hpLucTruoc = Integer.parseInt(gthpBird.getText());
					int hpLucSau = mng.getBird().getHp();
					String strhpLucSau = "" + hpLucSau;
					gthpBird.setText(strhpLucSau);
				}

				String hp_Tank = Tank.TANK_HP + "";
				if (gthpTank.getText() == "") {
					gthpTank.setText(hp_Tank);
				}
				if (mng.kiemTraTankIsShot()) {
					int hpLucTruoc = Integer.parseInt(gthpTank.getText());
					int hpLucSau = mng.getMtank().getHp();
					String strhpLucSau = "" + hpLucSau;
					gthpTank.setText(strhpLucSau);
					if (hpLucSau == 0) {
						/*try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						mng.tankHoiSinh();
						gthpTank.setText(hp_Tank);
					}

				}
				if (mng.tankDie()) {
					mng.xoaHearts();
				}
				if (mng.checkGameOver()) {
					JOptionPane
							.showMessageDialog(null, "Game Over : YOU LOSE ");
					stopGame();

				}
				PlayGamePanel.this.repaint();
				PlayGamePanel.this.setFocusable(true);
				PlayGamePanel.this.setRequestFocusEnabled(true);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time++;

			}
		}
	};

	public PlayGamePanel() {
		setBackground(Color.BLACK);
		mKeyValue = new BitSet(256);
		map1.redFileMap(getClass().getResource("/map.txt").getPath());
		initManager();
		setLayout(null);
		initContainer();
		initComps();
		addComps();

	}

	private void initManager() {
		mng = new Manager(map1);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		map1.drawMap(g2d);
		mng.drawObjects(g2d);

	}

	@Override
	protected void initContainer() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(runnable);
		thread.start();
		PlayGamePanel.this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				mKeyValue.set(arg0.getKeyCode());

			}

			@Override
			public void keyReleased(KeyEvent e) {
				mKeyValue.clear(e.getKeyCode());
			}

		});
	}

	@Override
	protected void initComps() {
		// TODO Auto-generated method stub
		fontScore = new Font("Tahoma", Font.BOLD, 15);

		FontMetrics hpTankMetris = getFontMetrics(fontScore);
		String hpTanklb = "HP TANK: ";
		int widthhpTank = hpTankMetris.stringWidth(hpTanklb);
		int heighthpTank = hpTankMetris.getHeight();
		hpTank = new JLabel(hpTanklb);
		hpTank.setFont(fontScore);
		hpTank.setForeground(Color.YELLOW);
		int hpTankX = GUI.WIDTH_GAME + 10;
		int hpTankY = 70;
		hpTank.setBounds(hpTankX, hpTankY, widthhpTank, heighthpTank);

		gthpTank = new JLabel();
		gthpTank.setFont(fontScore);
		gthpTank.setBounds(hpTankX + widthhpTank + 20, hpTankY,
				hpTankMetris.stringWidth("555"), hpTankMetris.getHeight());
		gthpTank.setForeground(Color.RED);

		FontMetrics hpBirdMetris = getFontMetrics(fontScore);
		String hpBirdlb = "HP BIRD: ";
		int widthhpBird = hpBirdMetris.stringWidth(hpBirdlb);
		int heighthpBird = hpBirdMetris.getHeight();
		hpBird = new JLabel(hpBirdlb);
		hpBird.setFont(fontScore);
		hpBird.setForeground(Color.YELLOW);
		int hpBirdX = GUI.WIDTH_GAME + 10;
		int hpBirdY = 110;
		hpBird.setBounds(hpBirdX, hpBirdY, widthhpBird, heighthpBird);

		gthpBird = new JLabel();
		gthpBird.setFont(fontScore);
		gthpBird.setBounds(hpBirdX + widthhpBird + 20, hpBirdY,
				hpBirdMetris.stringWidth("555"), hpBirdMetris.getHeight());
		gthpBird.setForeground(Color.RED);

		FontMetrics scoreMetris = getFontMetrics(fontScore);
		String scorelb = "Score: ";
		int widthScore = scoreMetris.stringWidth(scorelb);
		int heightScore = scoreMetris.getHeight();
		score = new JLabel(scorelb);
		score.setFont(fontScore);
		score.setForeground(Color.YELLOW);
		int scoreX = GUI.WIDTH_GAME + 10;
		int scoreY = 250;
		score.setBounds(scoreX, scoreY, widthScore, heightScore);

		scoreTf = new JLabel();
		scoreTf.setFont(fontScore);
		scoreTf.setBounds(scoreX + widthScore + 20, scoreY,
				scoreMetris.stringWidth("555"), scoreMetris.getHeight());
		scoreTf.setForeground(Color.RED);

		fontMadeByAndEmail = new Font("Tahoma", Font.ITALIC, 9);
		FontMetrics madeByMetris = getFontMetrics(fontMadeByAndEmail);
		String madeBylb = "Made By : Nguyen Si Trung";
		int widthmadeBy = madeByMetris.stringWidth(madeBylb);
		int heightmadeBy = madeByMetris.getHeight();
		madeBy = new JLabel(madeBylb);
		madeBy.setFont(fontMadeByAndEmail);
		madeBy.setForeground(Color.YELLOW);
		int madeByX = GUI.WIDTH_GAME + 10;
		int madeByY = GUI.HEIGHT_FRAME - heightmadeBy - 40;
		madeBy.setBounds(madeByX, madeByY, widthmadeBy, heightmadeBy);

		FontMetrics emailMetris = getFontMetrics(fontMadeByAndEmail);
		String emaillb = "Email: nguyensitrung1210@gmail.com";
		int widthemail = emailMetris.stringWidth(emaillb);
		int heightemail = emailMetris.getHeight();
		email = new JLabel(emaillb);
		email.setFont(fontMadeByAndEmail);
		email.setForeground(Color.YELLOW);
		int emailX = GUI.WIDTH_GAME + 10;
		int emailY = GUI.HEIGHT_FRAME - heightemail - 30;
		email.setBounds(emailX, emailY, widthemail, heightemail);
	}

	@Override
	protected void addComps() {
		// TODO Auto-generated method stub
		add(score);
		add(scoreTf);
		add(madeBy);
		add(email);
		add(hpTank);
		add(gthpTank);
		add(hpBird);
		add(gthpBird);
	}

	public void stopGame() {
		int result = JOptionPane.showConfirmDialog(this, "Start Game Again.",
				"Game Over", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			listener.restartGame();
			map1 = new Map();
			map1.redFileMap(getClass().getResource("/mapgame/map.txt").getPath());
			initManager();
			scoreTf.setText("0");
			String gthp_Tank = Tank.TANK_HP + "";
			gthpTank.setText(gthp_Tank);
			String gthp_Bird = Bird.HP_BIRD + "";
			gthpBird.setText(gthp_Bird);
		} else {
			if (result == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}

	}

	public interface OnPlayGameAgainListener {
		void restartGame();
	}
}
