package com.trungns.manager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.trungns.bullet.Bullet;
import com.trungns.map.Map;
import com.trungns.shape.Bird;
import com.trungns.shape.Heart;
import com.trungns.shape.Shape;
import com.trungns.sound.SoundManager;
import com.trungns.tank.Tank;
import com.trungns.tank.TankAuto;
import com.trungns.tank.TankDad;
import com.trungns.ui.GUI;

public class Manager {
	public static final int SPEEDBULLETSTANK = 1;
	public static final int SPEEDBULLETSTANKAUTO = 3;
	public static final int SPEEDMOVE = 5;
	public static final int SPEEDFIRE = 30;
	public static final int SPEEDFIRETANKAUTO = 500;
	public static final int BAT_DAU = 1;
	public static final int KET_THUC = 0;
	private TankDad mtank;
	private int kiemTraBatDau;
	private Bird bird;
	private int so_Mang_Con_Lai = Heart.NUMBER_HEARTS;
	private ArrayList<Heart> arrHearts = new ArrayList<Heart>();
	private ArrayList<TankAuto> mtankAuto = new ArrayList<TankAuto>();
	private ArrayList<Bullet> listBullet = new ArrayList<Bullet>();
	private ArrayList<Bullet> listBulletTankAuto = new ArrayList<Bullet>();
	private Image[] imgTank = new Image[4];
	private Image[] imgTankAuto = new Image[4];
	private Image[] imgTankDie = new Image[4];
	//private SoundManager soundManager = new SoundManager();

	public Manager(Map map) {
		kiemTraBatDau = BAT_DAU;

		// imgTank

		ImageIcon iconLeft = new ImageIcon(getClass().getResource(
				"/IMAGE/player_green_left.png").getPath());
		ImageIcon iconRight = new ImageIcon(getClass().getResource(
				"/IMAGE/player_green_right.png").getPath());
		ImageIcon iconUp = new ImageIcon(getClass().getResource(
				"/IMAGE/player_green_up.png").getPath());
		ImageIcon iconDown = new ImageIcon(getClass().getResource(
				"/IMAGE/player_green_down.png").getPath());
		imgTank[0] = iconLeft.getImage();
		imgTank[1] = iconRight.getImage();
		imgTank[2] = iconUp.getImage();
		imgTank[3] = iconDown.getImage();

		ImageIcon iconTankAutoLeft = new ImageIcon(getClass().getResource(
				"/IMAGE/bossyellow_left.png").getPath());
		ImageIcon iconTankAutoRight = new ImageIcon(getClass().getResource(
				"/IMAGE/bossyellow_right.png").getPath());
		ImageIcon iconTankAutoUp = new ImageIcon(getClass().getResource(
				"/IMAGE/bossyellow_up.png").getPath());
		ImageIcon iconTankAutoDown = new ImageIcon(getClass().getResource(
				"/IMAGE/bossyellow_down.png").getPath());
		imgTankAuto[0] = iconTankAutoLeft.getImage();
		imgTankAuto[1] = iconTankAutoRight.getImage();
		imgTankAuto[2] = iconTankAutoUp.getImage();
		imgTankAuto[3] = iconTankAutoDown.getImage();

		// mTank

		mtank = new Tank(Tank.TANK_BEGIN_X, Tank.TANK_BEGIN_Y,
				TankDad.WIDTH_TANK, TankDad.HEIGHT_TANK, Bullet.LEFT,
				Tank.TANK_HP, Manager.SPEEDMOVE, Manager.SPEEDFIRE, imgTank);

		// imgTankDie

		ImageIcon iconTankdieLeft = new ImageIcon(getClass().getResource(
				"/IMAGE/bullet_exp4.png").getPath());
		ImageIcon iconTankdieRight = new ImageIcon(getClass().getResource(
				"/IMAGE/bullet_exp4.png").getPath());
		ImageIcon iconTankdieUp = new ImageIcon(getClass().getResource(
				"/IMAGE/bullet_exp4.png").getPath());
		ImageIcon iconTankdieDown = new ImageIcon(getClass().getResource(
				"/IMAGE/bullet_exp4.png").getPath());

		Image imgTankdieLeft = iconTankdieLeft.getImage();
		Image imgTankdieRight = iconTankdieRight.getImage();
		Image imgTankdieUp = iconTankdieUp.getImage();
		Image imgTankdieDown = iconTankdieDown.getImage();

		imgTankDie[0] = imgTankdieLeft;
		imgTankDie[1] = imgTankdieRight;
		imgTankDie[2] = imgTankdieUp;
		imgTankDie[3] = imgTankdieDown;
		// Hearts
		ImageIcon iconHeart = new ImageIcon(getClass().getResource(
				"/IMAGE/heart.png").getPath());
		Image imgHeart = iconHeart.getImage();
		int heartsX = GUI.WIDTH_GAME + 10;
		int heartsY = 10;
		for (int i = 0; i < Heart.NUMBER_HEARTS; i++) {
			Heart hearts = new Heart(heartsX, heartsY, Heart.WIDTH_HEART,
					Heart.HEIGHT_HEART, Shape.HEART, imgHeart);
			arrHearts.add(hearts);
			heartsX = Heart.WIDTH_HEART + heartsX + 5;
		}
		// Bird

		ImageIcon iconBird = new ImageIcon(getClass().getResource(
				"/IMAGE/bird.png").getPath());
		Image imgBird = iconBird.getImage();
		bird = new Bird(Bird.BIRD_X, Bird.BIRD_Y, Bird.WIDTH_BIRD,
				Bird.HEIGHT_BIRD, Shape.BIRD, imgBird, Bird.HP_BIRD);

		// Random vi tri xuat hien cua cac xe tank tu dong
		for (int i = 0; i < TankAuto.NUMBER_TANKAUTO; i++) {
			Random rdXAppear = new Random();
			int newXLocationAppear = rdXAppear.nextInt(GUI.WIDTH_GAME
					- TankDad.WIDTH_TANK);
			Random rdYAppear = new Random();
			int newYLocationAppear = rdYAppear.nextInt(GUI.HEIGHT_GAME
					- TankDad.HEIGHT_TANK);
			Random rdOrient = new Random();
			int newOrient = rdOrient.nextInt(TankDad.ALL_ORIENT);
			Random rdSmart = new Random();
			int newSmart = rdSmart.nextInt(TankAuto.MAX_SMART) + 1;
			mtankAuto.add(new TankAuto(newXLocationAppear, newYLocationAppear,
					TankDad.WIDTH_TANK, TankDad.HEIGHT_TANK, newOrient,
					TankAuto.TANK_AUTO_HP, SPEEDMOVE, SPEEDFIRETANKAUTO,
					imgTankAuto, newSmart));

			boolean kt = true;
			while (kt) {
				boolean kt_vitri = true;
				boolean ktVaChamVoiChim = false;
				boolean ktVaChamVoiBeTong = false;
				boolean ktVaChamVoiTankKhac = false;
				boolean ktVaChamVoiTankDieuKhien = false;
				if (mtankAuto.get(i).isIntersectShape(bird)) {
					ktVaChamVoiChim = true;
				}
				for (int j = 0; j < map.getArrConcrete().size(); j++) {
					if (mtankAuto.get(i).isIntersectShape(
							map.getArrConcrete().get(j))) {
						ktVaChamVoiBeTong = true;
						break;
					} else {
						ktVaChamVoiBeTong = false;
					}
				}
				for (int j = 0; j < map.getArrBrick().size(); j++) {
					if (mtankAuto.get(i).isIntersectShape(
							map.getArrBrick().get(j))) {
						kt_vitri = false;
						break;
					} else {
						kt_vitri = true;
					}
				}
				if (mtankAuto.get(i).isIntersectTank(mtank)) {
					ktVaChamVoiTankDieuKhien = true;
				}
				if (i > 0) {
					for (int h = 0; h < i; h++) {
						if (mtankAuto.get(i).isIntersectTank(mtankAuto.get(h))) {
							ktVaChamVoiTankKhac = true;
							break;
						} else {
							ktVaChamVoiTankKhac = false;
						}
					}
				}
				if (kt_vitri == false || ktVaChamVoiTankKhac
						|| ktVaChamVoiTankDieuKhien || ktVaChamVoiBeTong
						|| ktVaChamVoiChim) {
					mtankAuto.remove(i);
					i--;
					kt = false;
				}
				if (kt_vitri) {
					kt = false;
				}
			}
		}

	}

	public void drawObjects(Graphics2D g2d) {

		mtank.draw(g2d);
		for (int i = 0; i < listBullet.size(); i++) {
			listBullet.get(i).draw(g2d);
		}
		for (int i = 0; i < listBulletTankAuto.size(); i++) {
			listBulletTankAuto.get(i).draw(g2d);
		}
		for (int i = 0; i < mtankAuto.size(); i++) {
			mtankAuto.get(i).draw(g2d);
		}
		for (int i = 0; i < arrHearts.size(); i++) {
			arrHearts.get(i).draw(g2d);
		}
		bird.draw(g2d);
	}

	public void moveBullets(int time, Map map) {
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/IMAGE/bullet_exp1.png"));
		Image img = icon.getImage();
		for (int i = 0; i < listBullet.size(); i++) {
			listBullet.get(i).moveBullet(time);
			if (listBullet.get(i).getX() > GUI.WIDTH_GAME
					|| listBullet.get(i).getY() > GUI.HEIGHT_GAME) {
				listBullet.remove(i);
				i--;
			} else {
				for (int j = 0; j < map.getArrBrick().size(); j++) {
					if (listBullet.get(i).bulletIsIntersectShape(
							map.getArrBrick().get(j))) {
						listBullet.get(i).chanImage(img);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						listBullet.remove(i);
						map.getArrBrick().remove(j);
						j--;
						i--;
						break;
					}
				}
			}
		}
		for (int i = 0; i < listBullet.size(); i++) {
			for (int j = 0; j < map.getArrConcrete().size(); j++) {
				if (listBullet.get(i).bulletIsIntersectShape(
						map.getArrConcrete().get(j))) {
					listBullet.get(i).chanImage(img);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listBullet.remove(i);
					i--;
					break;
				}
			}
		}
		for (int j = 0; j < listBulletTankAuto.size(); j++) {
			listBulletTankAuto.get(j).moveBullet(time);
			if (listBulletTankAuto.get(j).getX() > GUI.WIDTH_GAME
					|| listBulletTankAuto.get(j).getY() > GUI.HEIGHT_GAME) {
				listBulletTankAuto.remove(j);
				j--;
			} else {
				for (int i = 0; i < map.getArrBrick().size(); i++) {
					if (listBulletTankAuto.get(j).bulletIsIntersectShape(
							map.getArrBrick().get(i))) {
						listBulletTankAuto.get(j).chanImage(img);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						listBulletTankAuto.remove(j);
						map.getArrBrick().remove(i);
						i--;
						j--;
						break;
					}
				}
			}
		}
		for (int i = 0; i < listBulletTankAuto.size(); i++) {
			for (int j = 0; j < map.getArrConcrete().size(); j++) {
				if (listBulletTankAuto.get(i).bulletIsIntersectShape(
						map.getArrConcrete().get(j))) {
					listBulletTankAuto.get(i).chanImage(img);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listBulletTankAuto.remove(i);
					i--;
					break;
				}
			}
		}
		for (int i = 0; i < listBullet.size(); i++) {
			for (int j = 0; j < listBulletTankAuto.size(); j++) {
				if (listBullet.get(i).bulletIntersectBulletDifferrent(
						listBulletTankAuto.get(j))) {
					listBullet.get(i).chanImage(img);
					listBulletTankAuto.get(j).chanImage(img);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listBullet.remove(i);
					listBulletTankAuto.remove(j);
					i--;
					j--;
					break;
				}
			}
		}
	}

	public void moveTank(int time, Map map) {
		boolean kt = false;
		boolean ktVaChamVoiTankAuto = false;
		boolean ktVaChamVoiBeTong = false;
		boolean ktVaChamVoiBird = false;
		for (int i = 0; i < map.getArrBrick().size(); i++) {
			if (mtank.isIntersectShape(map.getArrBrick().get(i))) {
				kt = true;
				break;
			} else {
				kt = false;
			}
		}
		for (int i = 0; i < map.getArrConcrete().size(); i++) {
			if (mtank.isIntersectShape(map.getArrConcrete().get(i))) {
				ktVaChamVoiBeTong = true;
				break;
			} else {
				ktVaChamVoiBeTong = false;
			}
		}
		for (int i = 0; i < mtankAuto.size(); i++) {
			if (mtank.isIntersectTank(mtankAuto.get(i))) {
				ktVaChamVoiTankAuto = true;
				break;
			} else {
				ktVaChamVoiTankAuto = false;
			}
		}
		if (mtank.isIntersectShape(bird)) {
			ktVaChamVoiBird = true;
		}
		if (kt == true || ktVaChamVoiTankAuto == true || mtank.isScreenOut()
				|| ktVaChamVoiBeTong || ktVaChamVoiBird) {
			return;
		} else {
			mtank.speedMove(time);
			// soundManager.getSoundMove().play();
		}
	}

	public void tankFire(int time) {
		if (mtank.isFire(time)) {
			int testOrient = mtank.getOrient();
			ImageIcon icon = new ImageIcon(getClass().getResource(
					"/IMAGE/bullet.png").getPath());
			Image img = icon.getImage();
			if (mtank.getOrient() == Bullet.RIGHT) {
				int testX = mtank.getX() + mtank.getWidth();
				int testY = mtank.getY() + (mtank.getHeight() / 2);
				Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
						Bullet.HEIGHT_BULLET, testOrient, SPEEDBULLETSTANK, img);
				listBullet.add(test);
			}
			if (mtank.getOrient() == Bullet.LEFT) {
				int testX = mtank.getX();
				int testY = mtank.getY() + (mtank.getHeight() / 2);
				Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
						Bullet.HEIGHT_BULLET, testOrient, SPEEDBULLETSTANK, img);
				listBullet.add(test);
			}
			if (mtank.getOrient() == Bullet.UP) {
				int testX = mtank.getX() + (mtank.getWidth() / 2);
				int testY = mtank.getY();
				Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
						Bullet.HEIGHT_BULLET, testOrient, SPEEDBULLETSTANK, img);
				listBullet.add(test);
			}
			if (mtank.getOrient() == Bullet.DOWN) {
				int testX = mtank.getX() + (mtank.getWidth() / 2);
				int testY = mtank.getY() + mtank.getHeight();
				Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
						Bullet.HEIGHT_BULLET, testOrient, SPEEDBULLETSTANK, img);
				listBullet.add(test);
			}
			//soundManager.getSoundShoot().play();
		}

	}

	public void tankAutoFire(int time) {
		for (int i = 0; i < mtankAuto.size(); i++) {
			if (mtankAuto.get(i).isFire(time) == true) {
				int testOrient = mtankAuto.get(i).getOrient();
				ImageIcon icon = new ImageIcon(getClass().getResource(
						"/IMAGE/bullet.png").getPath());
				Image img = icon.getImage();
				if (mtankAuto.get(i).getOrient() == Bullet.RIGHT) {
					int testX = mtankAuto.get(i).getX()
							+ mtankAuto.get(i).getWidth();
					int testY = mtankAuto.get(i).getY()
							+ (mtankAuto.get(i).getHeight() / 2);
					Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
							Bullet.HEIGHT_BULLET, testOrient,
							SPEEDBULLETSTANKAUTO, img);
					listBulletTankAuto.add(test);
				}
				if (mtankAuto.get(i).getOrient() == Bullet.LEFT) {
					int testX = mtankAuto.get(i).getX();
					int testY = mtankAuto.get(i).getY()
							+ (mtankAuto.get(i).getHeight() / 2);
					Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
							Bullet.HEIGHT_BULLET, testOrient,
							SPEEDBULLETSTANKAUTO, img);
					listBulletTankAuto.add(test);
				}
				if (mtankAuto.get(i).getOrient() == Bullet.UP) {
					int testX = mtankAuto.get(i).getX()
							+ (mtankAuto.get(i).getWidth() / 2);
					int testY = mtankAuto.get(i).getY();
					Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
							Bullet.HEIGHT_BULLET, testOrient,
							SPEEDBULLETSTANKAUTO, img);
					listBulletTankAuto.add(test);
				}
				if (mtankAuto.get(i).getOrient() == Bullet.DOWN) {
					int testX = mtankAuto.get(i).getX()
							+ (mtankAuto.get(i).getWidth() / 2);
					int testY = mtankAuto.get(i).getY()
							+ mtankAuto.get(i).getHeight();
					Bullet test = new Bullet(testX, testY, Bullet.WIDTH_BULLET,
							Bullet.HEIGHT_BULLET, testOrient,
							SPEEDBULLETSTANKAUTO, img);
					listBulletTankAuto.add(test);
				}
			}
		}
	}

	public void moveTankAuto(int time, Map map) {
		boolean kt = false;
		boolean ktVaChamVoiBeTong = false;
		boolean ktVaChamVoiTank = false;
		boolean ktVaChamVoiTankKhac = false;
		boolean ktVaChamVoiBird = false;
		for (int j = 0; j < mtankAuto.size(); j++) {
			for (int h = 0; h < mtankAuto.size(); h++) {
				if (h != j) {
					if (mtankAuto.get(j).isIntersectTank(mtankAuto.get(h))) {
						ktVaChamVoiTankKhac = true;
						break;
					} else {
						ktVaChamVoiTankKhac = false;
					}
				}
			}
			for (int i = 0; i < map.getArrBrick().size(); i++) {
				if (mtankAuto.get(j).isIntersectShape(map.getArrBrick().get(i))) {
					kt = true;
					break;
				} else {
					kt = false;
				}
			}

			for (int i = 0; i < map.getArrConcrete().size(); i++) {
				if (mtankAuto.get(j).isIntersectShape(
						map.getArrConcrete().get(i))) {
					ktVaChamVoiBeTong = true;
					break;
				} else {
					ktVaChamVoiBeTong = false;
				}
			}
			if (mtankAuto.get(j).isIntersectShape(bird)) {
				ktVaChamVoiBird = true;
			}
			if (mtankAuto.get(j).isIntersectTank(mtank)) {
				ktVaChamVoiTank = true;
			}
			if (kt == true || ktVaChamVoiTankKhac == true || ktVaChamVoiBird
					|| ktVaChamVoiTank || ktVaChamVoiBeTong
					|| mtankAuto.get(j).isScreenOut()) {
				int newOrient = ((TankAuto) mtankAuto.get(j))
						.randomChangeOrient();
				mtankAuto.get(j).setOrient(newOrient);
				mtankAuto.get(j).speedMove(time);
			} else {
				mtankAuto.get(j).speedMove(time);
			}
		}
	}

	public boolean kiemTraTankIsShot() {
		for (int i = 0; i < listBulletTankAuto.size(); i++) {
			if (mtank.isIntersectBullet(listBulletTankAuto.get(i))) {
				mtank.tankIsShot();
				listBulletTankAuto.remove(i);
				i--;
				return true;
			}
		}
		return false;
	}

	public boolean tankDie() {

		if (mtank.isDie()) {
			return true;
		}
		return false;
	}

	public void xoaHearts() {
		mtank.setImg(imgTankDie);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mtank.setImg(imgTank);
		if (so_Mang_Con_Lai > 0) {
			arrHearts.remove(so_Mang_Con_Lai - 1);
			so_Mang_Con_Lai--;
			mtank.setHp(Tank.TANK_HP);
		}
	}

	public boolean checkGameOver() {
		if (kiemTraBatDau == BAT_DAU) {
			if (arrHearts.size() == 0 || bird.birdIsDie()) {
				kiemTraBatDau = KET_THUC;
				return true;
			}
		}
		return false;
	}

	public void kiemTraTankAuToIsShot() {
		for (int i = 0; i < mtankAuto.size(); i++) {
			for (int j = 0; j < listBullet.size(); j++) {
				if (mtankAuto.get(i).isIntersectBullet(listBullet.get(j))) {
					mtankAuto.get(i).tankIsShot();
					listBullet.remove(j);
					j--;
				}
			}
		}
	}

	public boolean tankAuToDie() {
		boolean kt = false;
		for (int i = 0; i < mtankAuto.size(); i++) {
			if (mtankAuto.get(i).isDie()) {
				mtankAuto.get(i).setImg(imgTankDie);
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mtankAuto.remove(i);
				i--;
				kt = true;
				break;
			}
		}
		if (kt) {
			return true;
		}
		return false;
	}

	public boolean checkYouWin() {
		if (kiemTraBatDau == BAT_DAU) {
			if (mtankAuto.size() == 0) {
				kiemTraBatDau = KET_THUC;
				return true;
			}
		}
		return false;
	}

	public void changeOrient(int newOrient) {
		mtank.changeOrient(newOrient);
	}

	public boolean isBirdIsShot() {
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/IMAGE/bullet_exp1.png"));
		Image img = icon.getImage();
		for (int i = 0; i < listBullet.size(); i++) {
			if (listBullet.get(i).bulletIsIntersectShape(bird)) {
				listBullet.get(i).chanImage(img);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listBullet.remove(i);
				bird.birdIsShot();
				i--;
				return true;
			}
		}
		for (int i = 0; i < listBulletTankAuto.size(); i++) {
			if (listBulletTankAuto.get(i).bulletIsIntersectShape(bird)) {
				listBulletTankAuto.get(i).chanImage(img);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				listBulletTankAuto.remove(i);
				bird.birdIsShot();
				i--;
				return true;
			}
		}
		return false;
	}
	public void tankHoiSinh(){
		mtank.setX(Tank.TANK_BEGIN_X);
		mtank.setY(Tank.TANK_BEGIN_Y);
	}
	public TankDad getMtank() {
		return mtank;
	}

	public Bird getBird() {
		return bird;
	}

}
