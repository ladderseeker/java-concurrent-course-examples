package miner.game.simulation.workers;

import miner.game.simulation.constants.Constants;
import miner.game.simulation.view.Board;

import java.util.Random;

public class MineSweeper implements Runnable{

	private int id;
	private Board board;
	private volatile boolean sweeperRunning;
	
	public MineSweeper(int id, Board board){
		this.id = id;
		this.board = board;
		this.sweeperRunning = true;
	}
	
	@Override
	public void run() {
		
		Random random = new Random();
		
		while(sweeperRunning){
			
			if( Thread.currentThread().isInterrupted()){
				return;
			}
			
			int index = random.nextInt(Constants.BOARD_ROWS*Constants.BOARD_COLUMNS);
			board.sweepMine(index);
			
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
				e.printStackTrace();
			}			
		}
	}
	
	public void setSweeperRunning(boolean sweeperRunning){
		this.sweeperRunning = sweeperRunning;
	}

	@Override
	public String toString() {
		return ""+this.id;
	}
}
