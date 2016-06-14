package com.trungns.sound;

public class SoundManager {
	private SourceEffect soundEnterGame;
	//private SourceEffect soundPlayGame;
	private SourceEffect soundMove;
	private SourceEffect soundShoot;
	private SourceEffect soundShootTank;
	public SoundManager() {
		soundEnterGame=new SourceEffect("enterGame.wav");
		soundMove = new SourceEffect("move.wav");
		//soundPlayGame=new SourceEffect("life.wav");
		soundShoot = new SourceEffect("shoot.wav");
		soundShootTank = new SourceEffect("shootTank.wav");
	}
	public SourceEffect getSoundBG() {
		return soundEnterGame;
	}/*
	public SourceEffect getSoundPlayGame() {
		return soundPlayGame;
	}*/
	public SourceEffect getSoundMove() {
		return soundMove;
	}
	public SourceEffect getSoundShoot() {
		return soundShoot;
	}
	public SourceEffect getSoundShootTank() {
		return soundShootTank;
	}
	
	
}

