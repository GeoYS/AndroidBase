package com.me.simplebase.blockharvest.screens;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Highscores {
	
	private static ArrayList<Integer> scores;
	private static Preferences preferences;
	
	public static void addScore(Integer score){
		scores.add(score);
		Collections.sort(scores);
		Collections.reverse(scores);
		while(scores.size() > 10){
			scores.remove(10);
		}
	}
	
	public static ArrayList<Integer> getScores(){
		return scores;
	}
	
	public static void save(){
		for(int i = 0; i < scores.size(); i++){
			preferences.putInteger("" + (i + 1), scores.get(i));
		}
	}
	
	public static void load(){
		preferences = Gdx.app.getPreferences("harvestblockshighscores");
		scores = new ArrayList<Integer>();
		if(preferences == null){
			return;
		}
		for(int i = 1; i < 11; i ++){
			if(preferences.contains(i + "")){
				scores.add(preferences.getInteger(i + ""));
			}
			else{
				return;
			}
		}
	}
}
