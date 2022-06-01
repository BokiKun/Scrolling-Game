/* Game Class Starter File
 * Last Edit: 5/6/2021
 * Author: _____________________
 */

public class Game {

    private Grid grid;
    private int userRow;
    private int userCol;
    private int msElapsed;
    private int timesAvoid; 
    private int timesGet;
    private int mondstadtScore;
    private int liyueScore;
    private int inazumaScore;
    private int particleCount;
    private String userPic = "images/traveler.png";
    private String bomb = "images/avoid.png";
    private String particle= "images/getA.png";

    
    public Game() {
  
      grid = new Grid(15,20,"images/bgMondstadt");
          System.out.println(grid.getNumRows()/2);
      userRow = (grid.getNumRows()/2)+1;
      userCol = 0;
      msElapsed = 0;
      particleCount = 0;
      timesAvoid = 0;
      updateTitle();
      grid.setImage(new Location(userRow, userCol), userPic);
    }
    
    public void play() {
  
      while (!isGameOver()) {
        grid.pause(50);
        handleKeyPress();
        if (msElapsed % 300 == 0) {
          scrollLeft();
          populateRightEdge();
          handleCollision();
        }
        updateTitle();
        msElapsed += 50;
      }
    }
    
    public void handleKeyPress(){
  
      //check last key pressed
      int key = grid.checkLastKeyPressed();
      System.out.println(key);
  
      //goes up
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
        if(userCol != 0){
          //change the field for userrow
          
          userCol--;
          
          //shift the user picture up in the array
          Location loc = new Location(userRow, userCol);
          grid.setImage(loc, userPic);
          
          Location oldLoc = new Location(userRow,userCol+1);
          grid.setImage(oldLoc, null);
                  }
        }
          //goes right
      if(key==68 || key==76 || key==39){ 
        if(userCol < grid.getNumCols()-1)
        userCol++;
          
          //shift the user picture up in the array
          Location loc = new Location(userRow,userCol);
          grid.setImage(loc, userPic);
          
          Location oldLoc = new Location(userRow, userCol-1);
          grid.setImage(oldLoc, null);
        
          }
        
  
    }
    
    public void populateRightEdge(){
      int lastRow = grid.getNumRows()-1;
      int lastCol = grid.getNumCols()-1;
    for(int i=0; i<=lastRow;i++){
    Location loc = new Location(i,lastCol);
      double random = Math.random();
      double thresh = 0.065;

      if(random < thresh){
      grid.setImage(loc,bomb);
    }

    }
    }
    
    public void scrollLeft(){ 
      int lastRow = grid.getNumRows()-1;
      int lastCol = grid.getNumCols()-1;
      Location userLoc = new Location(userRow, userCol);


      for(int c=1; c<=lastCol;c++){
        int leftCol = c-1;
        int rightCol = c;
        for(int r=0;r<=lastRow;r++){
          Location rightLoc = new Location(r, rightCol);
          Location leftLoc = new Location(r, leftCol);
          String rightPic = grid.getImage(rightLoc);
        if(!userPic.equals(rightPic)){




          grid.setImage(leftLoc, rightPic);
          grid.setImage(rightLoc, null);

          
              }
          }
        }

        grid.setImage(userLoc, userPic);


      }


    
    public void handleCollision() {
      Location userLoc= getUserLoc();
      for(int c=1; c<=grid.getNumCols()-1;c++){
        for(int r=0;r<=grid.getNumRows()-1;r++){
          if (userPic.equals(bomb)){
            System.out.println("over");
          }
        }
      }
   /*   if(loc.equals(bomb)) {
		timesAvoid++;
		System.out.println("Traveler Hit " + timesAvoid + " times");
	}
			if(loc.equals(particle)) {
				particleCount++;
				System.out.println("Traveler collected " + timesAvoid + " particles");
			}
      */
			}
      
    public int getScore() {
      return 0;
    }
    
    public void updateTitle() {
      grid.setTitle("Game:  " + getScore());
    }
    
    public boolean isGameOver() {
      return false;
      //ways to wing 1) reach end or 2)hit bombs 3 times
    }

    public void addGravity(){

    }
    
  
  }