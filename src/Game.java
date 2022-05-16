 /* Game Class Starter File
 * Last Edit: 5/6/2021
 * Author: _____________________
 */

public class Game {

  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String userPic = "images/user.gif";
  
  public Game() {

    grid = new Grid(20,25);
		System.out.println(grid.getNumRows()/2);
    userRow = (grid.getNumRows()/2);
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), userPic);
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
		
        userRow++;
        
        //shift the user picture up in the array
        Location loc = new Location(userRow, 0);
        grid.setImage(loc, "images/user.gif");
        
        Location oldLoc = new Location(userRow-1, 0);
        grid.setImage(oldLoc, null);
				}

  }
    //if I push down arrow, then plane goes down

  if(key == 83){
        //check case where out of boundsd
			if(userRow < grid.getNumRows()-1)
        //change the field for userrow
		
        userRow--;
        
        //shift the user picture up in the array
        Location loc = new Location(userRow, 0);
        grid.setImage(loc, "images/user.gif");
        
        Location oldLoc = new Location(userRow+1, 0);
        grid.setImage(oldLoc, null);
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