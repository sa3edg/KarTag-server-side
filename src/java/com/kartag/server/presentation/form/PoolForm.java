package com.kartag.server.presentation.form;

import java.util.ArrayList;

import com.kartag.common.presentation.forms.DefaultActionForm;
import com.kartag.server.model.Pool;

public class PoolForm extends Pool{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1382613305696434920L;
	private ArrayList<? extends DefaultActionForm> pools;
	public void setPools(ArrayList<? extends DefaultActionForm> pools) {
		this.pools = pools;
	}
	public ArrayList<? extends DefaultActionForm> getPools() {
		return pools;
	}
	

	
}
