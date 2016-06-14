package com.trungns.map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.trungns.shape.Brick;
import com.trungns.shape.Concrete;
import com.trungns.shape.Grass;
import com.trungns.shape.Shape;
import com.trungns.shape.Tree;
import com.trungns.shape.Water;

public class Map {
	public static final int SO_HANG = 30;
	public static final int SO_COT = 40;

	private int[][] arrayMap = new int[SO_HANG][SO_COT];
	private ArrayList<Brick> arrBrick = new ArrayList<Brick>();
	private ArrayList<Water> arrWater = new ArrayList<Water>();
	private ArrayList<Tree> arrTree = new ArrayList<Tree>();
	private ArrayList<Grass> arrGrass = new ArrayList<Grass>();
	private ArrayList<Concrete> arrConcrete = new ArrayList<Concrete>();
	public void redFileMap(String path) {
		File file = new File(path);
		try {
			RandomAccessFile ranf = new RandomAccessFile(file, "r");
			int soHang = 0;
			int soCot = 0;
			String line = ranf.readLine();
			String type = "";
			boolean kt = true;
			while (line != null) {

				String banSaoline = line;
				int index = banSaoline.indexOf(" ");
				while (kt) {
					if (index < 0) {
						arrayMap[soHang][soCot] = Integer.parseInt(banSaoline);
						break;
					}
					type = banSaoline.substring(0, banSaoline.indexOf(" "));
					arrayMap[soHang][soCot] = Integer.parseInt(type);
					banSaoline = banSaoline.substring(
							banSaoline.indexOf(" ") + 1, banSaoline.length());
					soCot++;
					index = banSaoline.indexOf(" ");
				}
				soHang++;
				soCot = 0;
				line = ranf.readLine();
			}
			ranf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < arrayMap.length; i++) {
			for (int j = 0; j < arrayMap[i].length; j++) {
				if (arrayMap[i][j] == Shape.BRICK) {
					ImageIcon iconBrick = new ImageIcon(getClass().getResource(
							"/IMAGE/brick.png").getPath());
					Image imageBrick = iconBrick.getImage();
					Brick shapeBrick = new Brick(j * Shape.WIDTH, i
							* Shape.HEIGHT, Shape.WIDTH, Shape.HEIGHT,
							arrayMap[i][j], imageBrick);
					arrBrick.add(shapeBrick);
				}
				if (arrayMap[i][j] == Shape.WATER) {
					ImageIcon iconWater = new ImageIcon(getClass().getResource(
							"/IMAGE/water.png").getPath());
					Image imageWater = iconWater.getImage();
					Water shapeWater = new Water(j * Shape.WIDTH, i
							* Shape.HEIGHT, Shape.WIDTH, Shape.HEIGHT,
							arrayMap[i][j], imageWater);
					arrWater.add(shapeWater);
				}
				if (arrayMap[i][j] == Shape.TREE) {
					ImageIcon iconTree = new ImageIcon(getClass().getResource(
							"/IMAGE/tree.png").getPath());
					Image imageTree = iconTree.getImage();
					Tree shapeTree = new Tree(j * Shape.WIDTH, i * Shape.HEIGHT, Shape.WIDTH,
							Shape.HEIGHT, arrayMap[i][j], imageTree);
					arrTree.add(shapeTree);
					
				}
				if (arrayMap[i][j] == Shape.GRASS) {
					ImageIcon iconGrass = new ImageIcon(getClass().getResource(
							"/IMAGE/grass.png").getPath());
					Image imageGrass = iconGrass.getImage();
					Grass shapeGrass = new Grass(j * Shape.WIDTH, i * Shape.HEIGHT, Shape.WIDTH,
							Shape.HEIGHT, arrayMap[i][j], imageGrass);
					arrGrass.add(shapeGrass);
				}
				if (arrayMap[i][j] == Shape.CONCRETE) {
					ImageIcon iconConcrete = new ImageIcon(getClass().getResource(
							"/IMAGE/concrete.png").getPath());
					Image imageConcrete = iconConcrete.getImage();
					Concrete shapeConcrete = new Concrete(j * Shape.WIDTH, i * Shape.HEIGHT, Shape.WIDTH,
							Shape.HEIGHT, arrayMap[i][j], imageConcrete);
					arrConcrete.add(shapeConcrete);
				}
			}
		}
	}
	
	public void drawMap(Graphics2D g2d) {
		for (int i = 0; i < arrBrick.size(); i++) {
			arrBrick.get(i).draw(g2d);
		}
		for (int i = 0; i < arrWater.size(); i++) {
			arrWater.get(i).draw(g2d);
		}
		for (int i = 0; i < arrTree.size(); i++) {
			arrTree.get(i).draw(g2d);
		}
		for (int i = 0; i < arrGrass.size(); i++) {
			arrGrass.get(i).draw(g2d);
		}
		for (int i = 0; i < arrConcrete.size(); i++) {
			arrConcrete.get(i).draw(g2d);
		}
	}

	public ArrayList<Brick> getArrBrick() {
		return this.arrBrick;
	}

	public ArrayList<Concrete> getArrConcrete() {
		return arrConcrete;
	}
}
