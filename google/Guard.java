package google;


public class Guard {

	public static void main(String[] args){
		String[][] input = new String[4][5];
		input[0][0] = ".";
		input[0][1] = "#";
		input[0][2] = ".";
		input[0][3] = "G";
		input[0][4] = ".";
		
		input[1][0] = ".";
		input[1][1] = ".";
		input[1][2] = "#";
		input[1][3] = ".";
		input[1][4] = ".";
		
		input[2][0] = "G";
		input[2][1] = ".";
		input[2][2] = ".";
		input[2][3] = ".";
		input[2][4] = ".";
		
		input[3][0] = ".";
		input[3][1] = ".";
		input[3][2] = "#";
		input[3][3] = ".";
		input[3][4] = ".";
		
		printInput(input);
		getDistanceMatrix(input);
		printInput(input);
	}
	
	static void printInput(String[][] input){
		for(int row = 0; row < input.length; row++){
			for(int col = 0; col < input[0].length; col++){
				System.out.print(input[row][col]);
				if(col == input[0].length-1){
					System.out.print("\n");
				}
			}
		}
	}
	
	static void getDistanceMatrix(String[][] input){
		for(int row = 0; row < input.length; row++){
			for(int col = 0; col < input[0].length; col++){
				if(input[row][col].equals(".")){
					input[row][col] = getDistance(input, row, col);
				}
			}
		}
	}
	
	static String getDistance(String[][] input, int row, int col){
		int MAX = Integer.MAX_VALUE;
		int rowDistance = MAX; 
		int r = row; 
		//previous row
		while(r > 0){
			r--;
			if(input[r][col].equals("G")){
				rowDistance = 1;
				break;
			}
			try{
				rowDistance = Integer.parseInt(input[r][col])+1;
				break;
			}catch(Exception e){
				
			}
		}
		
		int guardIdx = -1; 
		int obstacleNum = 0; 
		// next row
		for(r = row+1; r < input.length; r++){
			if(input[r][col].equals("G")){
				guardIdx = r; 
				break;
			}
			if(input[r][col].equals("#")){
				obstacleNum++;
			}
		}
		if(guardIdx > 0){
			rowDistance = Math.min(rowDistance, guardIdx-row-obstacleNum);
		}
		
		int colDistance = MAX; 
		int c = col; 
		//previous col
		while(c > 0){
			c--;
			if(input[row][c].equals("G")){
				rowDistance = 1;
				break;
			}
			try{
				colDistance = Integer.parseInt(input[row][c])+1;
				break;
			}catch(Exception e){
				
			}
		}
		
		guardIdx = -1; 
		obstacleNum = 0; 
		//next Col
		for(c = col+1; c < input[0].length; c++){
			if(input[row][c].equals("G")){
				guardIdx = c; 
				break;
			}
			if(input[row][c].equals("#")){
				obstacleNum++; 
			}
		}
		
		if(guardIdx > 0){
			colDistance = Math.min(colDistance, guardIdx-col-obstacleNum);
		}
		
		int distance = Math.min(rowDistance, colDistance);
		return String.valueOf(distance);
	}
}
