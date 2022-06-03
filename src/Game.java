/* Game Class Starter File
 * Last Edit: 5/6/2021
 * Author: _____________________
 */

public class Game {

    private Grid grid;
		private Grid splash;
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
    private String particle;
		private String region;

    
    public Game() {
  
      grid = new Grid(15,20,"images/bgMondstadt");
          System.out.println(grid.getNumRows()/2);
      userRow = (grid.getNumRows()/2)+1;
      userCol = 0;
      msElapsed = 0;
      particleCount = 0;
      timesAvoid = 0;
      updateTitle();
      grid.setImage(getUserLoc(), userPic);
    }
    
    public void play() {
			level(1);
      while (!isGameOver()) {
        grid.pause(50);
        handleKeyPress();
        if (msElapsed % 250 == 0) {
          scrollLeft();
          populateRightEdge();
          handleCollision(getUserLoc());
        }
        updateTitle();
	    msElapsed += 50;
      }
    }
	
	public void splashScreen()){
	splash = new Grid(5, 7, "");
		setBadges();
	}
		
	public void level(int level){
		if (level == 1) {
		grid.setBackground("images/bgMondstadt");
		particle = "images/getA.png";
		region = "Mondstadt";
	}
	if (level == 2) {
			grid.setBackground("images/bgLiyue");
		particle = "images/getG.png";
		region = "Liyue";
	}
	if (level == 3) {
			grid.setBackground("images/bgInazuma");
		particle = "images/getE.png";
		region = "Inazuma";
	}
}

public void setBadges() {
	//mondstadt
	Location badgeLoc = new Location(3,2);
	splash.setImage(badgeLoc, "images/badges/blank.png");
	if (mondstadtScore > 10000) {
		splash.setImage(badgeLoc, "images/badges/bronze.png");
	} else if(mondstadtScore > 200000 && mondstadtScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/silver.png");
	} else if(mondstadtScore > 30000) {
		splash.setImage(badgeLoc, "images/badges/gold.png");
	}
	//liyue
	badgeLoc = new Location(3,4);
	splash.setImage(badgeLoc, "images/badges/blank.png");
if (liyueScore > 10000) {
		splash.setImage(badgeLoc, "images/badges/bronze.png");
	} else if(liyueScore > 200000 && liyueScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/silver.png");
	} else if(liyueScore > 300000) {
		splash.setImage(badgeLoc, "images/badges/gold.png");
	}
//inazuma
	badgeLoc = new Location(3,6);
	splash.setImage(badgeLoc, "images/badges/blank.png");
if(inazumaScore > 10000) {
		splash.setImage(badgeLoc, "images/badges/bronze.png");
	} else if(inazumaScore > 200000 && inazumaScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/silver.png");
	} else if(inazumaScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/gold.png");
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
      handleCollision(loc);
    }

    }
    }
	
		public Location getUserLoc() {
		Location userLoc = new Location(userRow, userCol); 
		return userLoc;
	}
    
    public void scrollLeft(){ 
      int lastRow = grid.getNumRows()-1;
      int lastCol = grid.getNumCols()-1;
      getUserLoc();

      for(int c=1; c<=lastCol;c++){
        int leftCol = c-1;
        int rightCol = c;
        for(int r=0;r<=lastRow;r++){
          Location rightLoc = new Location(r, rightCol);
          Location leftLoc = new Location(r, leftCol);
          String rightPic = grid.getImage(rightLoc);
        if(!userPic.equals(rightPic)){
          grid.setImage(getUserLoc(), userPic);
          grid.setImage(leftLoc, rightPic);
          grid.setImage(rightLoc, null);
              }
          }
				}
        grid.setImage(getUserLoc(), userPic);
      }

    public void handleCollision(Location Loc) {
          if (Loc==getUserLoc()){
            System.out.println("over");
          }
        }
         
    public int getScore() {
      return particleCount + (msElapsed/250);
    }
    
    public void updateTitle() {
      grid.setTitle(region + "  Score:  " + getScore());
    }
    
    public boolean isGameOver() {
      return false;
      //ways to wing 1) reach end or 2)hit bombs 3 times
    }

    public void addGravity(){

    }
    
  
  }