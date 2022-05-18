 /* Game Class Starter File
 * Last Edit: 5/6/2021
 * Author: _____________________
 */

public class Game {

  private Grid grid;
  private int userRow;
  private int userCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String userPic = "images/traveler.png";
  
  public Game() {

    grid = new Grid(20,26,"images/bg.png");
		System.out.println(grid.getNumRows()/2);
    userRow = (grid.getNumRows()/2);
    userCol = (grid.getNumCols()/2);
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, userCol), userPic);
  }
  
  public void play() {

    while (!isGameOver()) {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0) {
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  
  public void handleKeyPress(){

    //check last key pressed
    int key = grid.checkLastKeyPressed();
    System.out.println(key);

    //set "w" key to move the plane up
    if(key == 38 || key == 73 || key==32 || key == 87){
        //check case where out of boundsd
			if(userRow != 0) {
        //change the field for userrow
		
        userRow--;
        
        //shift the user picture up in the array
        Location loc = new Location(userRow, userCol);
        grid.setImage(loc, userPic);
        
        Location oldLoc = new Location(userRow+1, userCol);
        grid.setImage(oldLoc, null);

				}

  }
    //if I push down arrow, then plane goes down

    if(key == 83 || key==75 || key==40){
        //check case where out of boundsd
			if(userRow < grid.getNumRows()-1)
        //change the field for userrow
		
        userRow++;
        
        //shift the user picture up in the array
        Location loc = new Location(userRow, userCol);
        grid.setImage(loc, userPic);
        
        Location oldLoc = new Location(userRow-1, userCol);
        grid.setImage(oldLoc, null);
				}
        //goes left
    if(key==65 || key==74 || key==37){
      if(userRow < grid.getNumCols()-1)
        //change the field for userrow
		
        userCol--;
        
        //shift the user picture up in the array
        Location loc = new Location(userRow, userCol);
        grid.setImage(loc, userPic);
        
        Location oldLoc = new Location(userRow,userCol+1);
        grid.setImage(oldLoc, null);
				}


  
  
        //goes right
    if(key==68 || key==76 || key==39){ 
      if(userRow < grid.getNumCols()-1){
      userCol++;
        
        //shift the user picture up in the array
        Location loc = new Location(userRow,userCol);
        grid.setImage(loc, userPic);
        
        Location oldLoc = new Location(userRow, userCol-1);
        grid.setImage(oldLoc, null);
      }
        }

  }
  
  public void populateRightEdge(){

  }
  
  public void scrollLeft(){

  }
  
  public void handleCollision(Location loc) {

  }
  
  public int getScore() {
    return 0;
  }
  
  public void updateTitle() {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver() {
    return false;
  }
    

}