package com.trungns.ui;

import javax.swing.JPanel;

public abstract class BaseContainer extends JPanel{
	public BaseContainer() {
		initContainer();
		initComps();
		addComps();
	}

	protected abstract void initContainer();

	protected abstract void initComps();

	protected abstract void addComps();
}
