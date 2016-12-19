package com.game.temp.stage_loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StageLoader {
	public static void main(String args[]) throws IOException {
		int stage = 1;
		
		ArrayList<ArrayList<Road>> roads = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader("./stage/stage_" + stage + ".game"));
		String line;
		ArrayList<String> tokens = null;
		StringTokenizer tokenizer = null;
		int currentIndex = 0;
		
		while((line = br.readLine()) != null) {
			String param[] = line.split(":");
			
			if(param[0].equals("ROAD")) {
				tokenizer = new StringTokenizer(param[1], ",");
				tokens = new ArrayList<>();
				
				while(tokenizer.hasMoreTokens()) {
					tokens.add(tokenizer.nextToken());
				}
				
				int startCol = Integer.parseInt(tokens.get(0));
				int startRow = Integer.parseInt(tokens.get(1));
				int finishCol = Integer.parseInt(tokens.get(2));
				int finishRow = Integer.parseInt(tokens.get(3));
				
				if(roads.size() <= currentIndex) {
					roads.add(new ArrayList<Road>());
				}
				
				System.out.println(tokens.size());
				roads.get(currentIndex).add(new Road(startCol, startRow, finishCol, finishRow));
				
				if(tokens.size() == 5) {
					currentIndex++;
				}
				
			} else if (param[1].equals("DOOR")) {
				
			}
		}
		
		for(int i=0; i<roads.size(); i++) {
			System.out.printf("Road %d>>>\n", i+1);
			
			for(int j=0; j<roads.get(i).size(); j++) {
				roads.get(i).get(j).print();
			}
		}
	}
}

