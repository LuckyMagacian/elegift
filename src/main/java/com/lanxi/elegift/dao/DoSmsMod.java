package com.lanxi.elegift.dao;

import java.util.List;

import com.lanxi.elegift.bean.in.SmsMod;

public interface DoSmsMod {
	public void addSmsMod(SmsMod mod);
	public void addSmsModDefault(SmsMod mod);
	public void deleteSmsMod(SmsMod mod);
	public void updateSmsMod(SmsMod mod);
	public List<SmsMod> selectSmsMod(SmsMod mod);
}
