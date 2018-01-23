/* Phillip Mathew
 * November 18, 2013
 * Collide.java
 * This program runs the collide game.
*/

public class Collide {
    Piece s1, d1, j1, piece;        //slider, diag, jumper
    //boolean salive, jalive, dalive;
    int count;              //count number of turns taken
    
    public Collide() {
        s1 = new Slider();
        d1 = new Diag();
        j1 = new Jumper();
        piece = new Piece();
        count = 1;        
    }
    
    public static void main(String[] args) {
        Collide cg = new Collide();
        cg.Play();
    }
    
    public void Play() {
       
       System.out.println("Game Board    " + "count = " + count + "    Turn = Initial");
       piece.movePiece(); 
       s1.salive = true;
       d1.dalive = true;
       j1.jalive = true;
       int loopcount = 0;
       //while ((salive == true && dalive == true) || (salive == true && jalive == true) || (dalive == true && jalive == true)) {    
          //System.out.println(salive + " " + jalive + " " + dalive);
       while (true) {
           
           if (s1.salive) {
               count += 1;
               System.out.println("Game Board    " + "count = " + count + "    Turn = S");         
               s1.movePiece();   
           }             
           if ((s1.salive) && !(d1.dalive) && !(j1.jalive)) {
                System.out.println("Slider wins!!!");
                break;
           }
           
           if (d1.dalive) {
               count += 1;
               System.out.println("Game Board    " + "count = " + count + "    Turn = D");            
               d1.movePiece();
            }
           if ((d1.dalive) && !(s1.salive || j1.jalive)) {
                System.out.println("Diag wins!!!");
                break;
           }
           
           if (j1.jalive) {
               count += 1;
               System.out.println("Game Board    " + "count = " + count + "    Turn = J");           
               j1.movePiece(); 
           }                
           if ((j1.jalive) && !(d1.dalive) && !(s1.salive)) {
                System.out.println("Jumper wins!!!");
                break;
           }
                         
           loopcount += 1;
           
       }
       
    }

}

class Piece {
    public static int x, y, dx, dy, jx, jy;         //location on the board
    public static boolean salive, dalive, jalive;           //is the piece still alive?
    
    public void movePiece() {       //the method to move the piece
    
        do {
            x = (int)(Math.random() * 5 + 1);
            y = (int)(Math.random() * 5 + 1);
            dx = (int)(Math.random() * 5 + 1);
            dy = (int)(Math.random() * 5 + 1);
            jx = (int)(Math.random() * 5 + 1);
            jy = (int)(Math.random() * 5 + 1);
        } while ((x == dx && y == dy) || (x == jx && y == jy) || (dx == jx && dy == jy));
        
        for (int row = 1; row <= 5; row++) {            
            for (int column = 1; column <= 5; column++) {   
                if (row == x && column == y) {
                    System.out.print(" S ");
                } else if (row == dx && column == dy) {
                    System.out.print(" D ");
                } else if (row == jx && column == jy) {
                    System.out.print(" J ");
                } else          
                System.out.print(" - ");
            }
            System.out.println();
        }
        System.out.print("\n\n");
    }
    
    
}

class Slider extends Piece {
    //public boolean salive = true;
    public void movePiece() {       //the method to move the slider
        
        int holdx = x;
        int holdy = y;
        int moveInt = (int)(Math.random() * 4 + 1);
                  
        if (moveInt == 1) {
            x = (int)(x + 1);
            if (x > 5) {
                x = holdx;
                x = (int)(x - 1);
            }
        }
        
        if (moveInt == 2) {
            x = (int)(x - 1);
            if (x < 1) {
                x = holdx;
                x = (int)(x + 1);
            }
        }
        
        if (moveInt == 3) {
            y = (int)(y + 1);
            if (y > 5) {
                y = holdy;
                y = (int)(y - 1);
            }
        }
        
        if (moveInt == 4) {
            y = (int)(y - 1);
            if (y < 1) {
                y = holdy;
                y = (int)(y + 1);
            }
        }
        
        if (dx == x && dy == y) 
            dalive = false;        
        if (jx == x && jy == y) 
            jalive = false;
            
        for (int row = 1; row <= 5; row++) {            
            for (int column = 1; column <= 5; column++) {   
                if ((row == x && column == y) && (salive)) {
                    System.out.print(" S ");
                } 
                else if ((row == dx && column == dy) && ((dx != x && dy == y) || (dx == x && dy != y) || (dx != x && dy != y)) && (dalive == true)) {       //only print out D when its x and y values are not equal to the slider's
                    System.out.print(" D ");
                }                
                else if ((row == jx && column == jy) && ((jx != x && jy == y) || (jx == x && jy != y) || (jx != x && jy != y)) && (jalive == true)) {       //only print J when its x and y values aren't equal to the slider's
                    System.out.print(" J ");
                }
                else 
                    System.out.print(" - ");
            }
            System.out.println();
        }
        System.out.print("\n\n");
    }   
}

class Diag extends Piece {
    //public boolean dalive = true;
    public void movePiece() {       //the method to move the slider
        
        int holddx = dx;
        int holddy = dy;
        int moveInt = (int)(Math.random() * 4 + 1);
        
        if (moveInt == 1) {
            dx = (int)(dx + 1);
            dy = (int)(dy - 1);
            if (dx > 5 || dy < 1) {
                dx = holddx;
                dy = holddy;
                dx = (int)(dx - 1);
                dy = (int)(dy + 1);
            }            
        }
        
        if (moveInt == 2) {
            dx = (int)(dx - 1);
            dy = (int)(dy - 1);
            if (dx < 1 || dy < 1) {
                dx = holddx;
                dy = holddy;
                dx = (int)(dx + 1);
                dy = (int)(dy + 1);   
            }
        }
            
        if (moveInt == 3) {
            dx = (int)(dx + 1);
            dy = (int)(dy + 1);        
            if (dx > 5 || dy > 5) {
                dx = holddx;
                dy = holddy;
                dx = (int)(dx - 1);
                dy = (int)(dy - 1);
            }
        }
        
        if (moveInt == 4) {
            dx = (int)(dx - 1);
            dy = (int)(dy + 1);
            if (dx < 1 || dy > 5) {
                dx = holddx;
                dy = holddy;
                dx = (int)(dx + 1);
                dy = (int)(dy - 1);
            }
        }   
    
    
        if (x == dx && y == dy) 
            salive = false;        
        if (jx == dx && jy == dy) 
            jalive = false;
    
        for (int row = 1; row <= 5; row++) {            
            for (int column = 1; column <= 5; column++) {   
                if ((row == x && column == y) && ((x == dx && y != dy) || (x != dx && y == dy) || (x != dx && y != dy)) && (salive == true)) {
                    System.out.print(" S ");
                } else if ((row == dx && column == dy) && (dalive)) {
                    System.out.print(" D ");
                } else if ((row == jx && column == jy) && ((jx == dx && jy != dy) || (jx != dx && jy == dy) || (jx != dx && jy != dy)) && (jalive == true)) {
                    System.out.print(" J ");
                } else          
                System.out.print(" - ");
            }
            System.out.println();
        }
        System.out.print("\n\n");
    }   
}

class Jumper extends Piece {
    //public boolean jalive = true;
    public void movePiece() {
        
        jx = (int)(Math.random() * 5 + 1);
        jy = (int)(Math.random() * 5 + 1);
        
        if (x == jx && y == jy) 
            salive = false;        
        if (dx == jx && dy == jy) 
            dalive = false;
         
        for (int row = 1; row <= 5; row++) {            
            for (int column = 1; column <= 5; column++) {   
                if ((row == x && column == y) && ((x == jx && y != jy) || (x != jx && y == jy) || (x != jx && y != jy)) && (salive == true)) {
                    System.out.print(" S ");
                } else if ((row == dx && column == dy) && ((dx == jx && dy != jy) || (dx != jx && dy == jy) || (dx != jx && dy != jy)) && (dalive == true)) {
                    System.out.print(" D ");
                } else if ((row == jx && column == jy) && (jalive)) {
                    System.out.print(" J ");
                } else          
                System.out.print(" - ");
            }
            System.out.println();
        }
        System.out.print("\n\n");
    }
}