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
	  private int particleCount;
    private int mondstadtScore;
    private int liyueScore;
    private int inazumaScore;
		private int level;
    private String userPic = "images/traveler.png";
    private String bomb = "images/avoid.png";
    private String particle;
		private String region;

    
    public Game() {
			splash = new Grid(5,7, "images/splash.png");
      splash.fullscreen();
    	grid = new Grid(15,20,"");
      msElapsed = 0;
      particleCount = 0;
      timesAvoid = 0;
      updateTitle();
      
    }
    
    public void play() {
			boolean start = true;
      //WavPlayer.play("sounds/Ricky Mondsat.wav");

			while (start) {
		//start Game
		setBadges();
		boolean isValid = false;
		while(!isValid) {
		Location selection = splash.waitForClick();
		if (selection.equals(new Location(3, 2))) {
			level(1);
			isValid = true;
		}
		if (selection.equals(new Location(3, 4))) {
			level(2);
			isValid = true;
		}
		if (selection.equals(new Location(3, 6))) {
			level(3);
			isValid = true;
		}
			}
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
			updateScore();
			grid.showMessageDialog("You have reached the goal!");
			}
    }
	
		public void level(int level){
      userRow = (grid.getNumRows()/2)+1;
      userCol = 0;
      this.level = level;
      grid.setImage(getUserLoc(), userPic);
      grid = new Grid(15, 20, "images/bgInazuma.png");
      if (level == 1) {
        grid.setBackground("images/bgMondstadt.png");
        particle = "images/getA.png";
        region = "Mondstadt";
      }
      if (level == 2) {
        grid.setBackground("images/bgLiyue.png");
        particle = "images/getG.png";
        region = "Liyue";
      }
      if (level == 3) {
        grid.setBackground("images/bgInazuma.png");
        particle = "images/getE.png";
        region = "Inazuma";
      }
      System.out.println("Level " + level + " selected");
      splash.close();
      System.out.println("Splash Closed");

    }

		public void setBadges() {
		splash.setTitle("Level Selection");
		grid.close();
		System.out.println("Gameplay Closed");

	//mondstadt
	Location badgeLoc = new Location(3,2);
	splash.setImage(badgeLoc, "images/badges/blank.png");

	if(mondstadtScore > 30000) {
		splash.setImage(badgeLoc, "images/badges/gold.png");
	} else if(mondstadtScore > 20000 && mondstadtScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/silver.png");
	} else if (mondstadtScore > 10000) {
		splash.setImage(badgeLoc, "images/badges/bronze.png");
	} 
	
	//liyue
	badgeLoc = new Location(3,4);
	splash.setImage(badgeLoc, "images/badges/blank.png");

		if(liyueScore > 30000) {
		splash.setImage(badgeLoc, "images/badges/gold.png");
	} else if(liyueScore > 20000 && liyueScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/silver.png");
	} else if (liyueScore > 10000) {
		splash.setImage(badgeLoc, "images/badges/bronze.png");
	} 
//inazuma
	badgeLoc = new Location(3,6);
	splash.setImage(badgeLoc, "images/badges/blank.png");

			if(inazumaScore > 30000) {
		splash.setImage(badgeLoc, "images/badges/gold.png");
	} else if(inazumaScore > 20000 && inazumaScore < 30000) {
		splash.setImage(badgeLoc, "images/badges/silver.png");
	} else if(inazumaScore > 10000) {
		splash.setImage(badgeLoc, "images/badges/bronze.png");
	} 
}
	
		public void handleKeyPress(){
  
      //check last key pressed
      int key = grid.checkLastKeyPressed();
      System.out.print(key + " ");
  
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
        
					//if user is left pic and bomb or particle is rightpic
					//handlecollision(loc)
					
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
public void updateScore() {
	if (isGameOver()) {
		if(level == 1) {
			mondstadtScore = getScore();
		}
		if(level == 2) {
			liyueScore = getScore();
		}
		if(level == 3) {
			inazumaScore = getScore();
		}
	}
	msElapsed = 0;
	timesAvoid = 0;
	particleCount = 0;
	
}
	
    public int getScore() {
      return particleCount + (msElapsed*10);
    }
    
    public void updateTitle() {
      grid.setTitle(region + "  Score:  " + getScore());
    }
    
    public boolean isGameOver() {
			if (timesAvoid == 5) return true;
			if ((msElapsed/250) > 50) return true;
      else return false;
      //ways to wing 1) reach end or 2)hit bombs 3 times
    }

    public void addGravity(){

    }
    
  
  }