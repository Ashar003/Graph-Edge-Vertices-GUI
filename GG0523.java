import javax.swing.*;

import java.util.* ;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;


public class GG0523 extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    protected GraphPicturePanel picture;

    protected int buttonNum; // here so it's accessible everywhere


public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel(UIManager
            .getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
        | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        new GG0523();
        }//main

    
   private GG0523()  {
       super("Graph GUI"); //name
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close on x
	    setSize(1200,900); //height and width
        setLocation  (400,200); //location
        setLayout(new GridLayout(1,2 )); //grid
        
        picture = new GraphPicturePanel(this);
        addButtonsRadios(); //function to add buttons
        add(picture);  //Allows for the gridlayout
       
        setVisible(true); //you can see it
   }//GG0523
//layout stuff

 private void addButtonsRadios(){
    JPanel buttonContainer;
    JRadioButton addVertex;
    JRadioButton addEdge;
    JRadioButton moveVertex;
    JRadioButton removeEdge;
    JRadioButton removeVertex ;
    ButtonGroup Button; 

    JButton addAllEdges;
    JButton connectedComponents;
    JButton showCutVertices;
    JButton helpText;

    buttonContainer = new JPanel(new GridLayout(9, 1)); //create a grid on left side with 9 button placements
    Button = new ButtonGroup(); //Allows me to group buttons together

    //RadioButtons
    addVertex = new JRadioButton("Add vertex");
    addVertex.addActionListener(this); //actionlistener set
    Button.add(addVertex); //add it to the special group
    buttonContainer.add(addVertex); //button container group in which all 9 buttons are contained

    addEdge = new JRadioButton("Add edge");
    addEdge.addActionListener(this);
    Button.add(addEdge);
    buttonContainer.add(addEdge);

    moveVertex = new JRadioButton("Remove vertex");
    moveVertex.addActionListener(this);
    Button.add(moveVertex);
    buttonContainer.add(moveVertex);

    removeVertex = new JRadioButton("Remove edge");
    removeVertex.addActionListener(this);
    Button.add(removeVertex);
    buttonContainer.add(removeVertex);

    removeEdge = new JRadioButton("Move vertex");
    removeEdge.addActionListener(this);
    Button.add(removeEdge);
    buttonContainer.add(removeEdge);

    
    
    


   //4 JBUttons
    addAllEdges = new JButton("Add All Edges");
    addAllEdges.addActionListener(this);
    buttonContainer.add(addAllEdges);
    
    connectedComponents = new JButton("Connected Components");
    connectedComponents.addActionListener(this);
    buttonContainer.add(connectedComponents);
    
    showCutVertices = new JButton("Show Cut Vertices");
    showCutVertices.addActionListener(this);
    buttonContainer.add(showCutVertices);
    
    helpText = new JButton("Help");
    helpText.addActionListener(this);
    buttonContainer.add(helpText);

    add(buttonContainer);//At the bottom to reflect recent changes.
    

 }//addButtonRadios
                public void actionPerformed(ActionEvent event) { //Mandatory naming
                    String s = event.getActionCommand(); //pulled off the event in terms of a string

                    switch(s){ //Cleaner than Deprecated Method below
                        case "Add vertex": //Case looks for astring which I put on the button in "AddButtonRadios"
                        //  System.out.println("No add vertexes boy ");
                              buttonNum = 1; //Assignment of the "handy-dandy" buttonNum!
                         break;

                        case "Add edge":
                        // System.out.println("No add edge boy");
                        buttonNum = 2;
                        break;

                        case  "Remove vertex":
                        // System.out.println("No move vertex boy");
                        buttonNum = 3;
                        break;

                        case "Remove edge":
                        // System.out.println("No remove edge boy");
                        buttonNum = 4;
                        break;
                        
                        case "Move vertex":
                        buttonNum = 5;
                        // System.out.println("Can't remove vertexes boy");
                        break;

                        case "Add All Edges":
                        buttonNum = 6;
                        // System.out.println("Click GUI once done");
                        break;
                        
                        case "Connected Components":
                        buttonNum = 7;
                        // System.out.println("Connected Components boy");
                        break;

                        case "Show Cut Vertices":
                        buttonNum = 8;
                        // System.out.println("Show Cut Vertices boy");
                        break;

                        case "Help":
                        JOptionPane.showMessageDialog(
                            null, "To start, add atleast 2 vertices, " +
                            "then, you can: \n" +"Add edges by click on two disinct vertices \n" + 
                            "Remove a vertex and its edge \n" +
                             "Remove a edge by selecting a particular vertex \n" +
                             " Move a vertex,and its edge\n" + 
                             "Add all possible edges between your vertices \n " +
                             " Highlight connected components in colors other than blue\n");
                        break;

                        default: s.equals(""); //Default is blank, since there is nothing for it
                        break;
                    }//switch
        /*Deprecated Method
                      if (s.equals("Add vertex")) {
                        System.out.println("No add vertexes boy ");
                        buttonNum = 1;
                    } else if (s.equals("Add edge")) {
                        System.out.println("No add edge boy");
                        buttonNum = 2;
                    } else if (s.equals("Remove vertex")) {
                        System.out.println("No move vertex boy");
                        buttonNum = 3;
                    } else if (s.equals("Remove edge")) {
                        System.out.println("No remove edge boy");
                        buttonNum = 4;
                    } else if (s.equals("Move vertex")) {
                        buttonNum = 5;
                        System.out.println("Can't remove vertexes boy");
                    } else if (s.equals("Add All Edges")) {
                        buttonNum = 6;
                        System.out.println("Click GUI once done");
                    } else if (s.equals("Connected Components")) {
                        buttonNum = 7;
                        System.out.println("Connected Components boy");
                    } else if (s.equals("Show Cut Vertices")) {
                        buttonNum = 8;
                        System.out.println("Show Cut Vertices boy");
                    }
                    else if (s.equals("Help")) {
                        JOptionPane.showMessageDialog(
                            null, "The only way to start is by adding a vertex " +
                            "then, you can add edges, remove vertex(s), remove edge(s), move vertex..." + "Bottom 3 buttons don't work.");
                    }
                    */
                        } //actionPerformed


        private class MouseClicker extends MouseAdapter {
            protected GraphPicturePanel picture;
            protected Vertex pointUn, pointDos, temp;
            protected Edge newEdge, x, tinal, tempE;
            protected ArrayList<Vertex> Vertexes;
            protected ArrayList<Edge> Edges, Hope ;
            protected Point2D vPoints;
            
            
            public MouseClicker(GraphPicturePanel picture) { //Constructor
                this.picture = picture;
                picture.addMouseListener(this);

                Vertexes = new ArrayList<>();
                Hope = new ArrayList<>();
                Edges = new ArrayList<>();
            }//MouseClicker

            public void verticesColor(Graphics2D f){ //method to set color of vertices
                Iterator<Vertex> listVertex = Vertexes.iterator();
                while(listVertex.hasNext()){
                    Vertex temp = listVertex.next();
                    f.setColor(temp.getVertexColor()); //using the passed in value , I set the color use the specific vertice's get method
                    Shape s = temp.getVertex(); //the shape
                    f.fill(s); //fill the shape with the color
                }
            } //verticesColor

            public void edgesColor(Graphics2D f ){ //method to set color of edges
                Iterator<Edge> listEdge = Edges.iterator();
                while(listEdge.hasNext()){
                    Edge tempE = listEdge.next();
                    Shape line = tempE.getEdge();

                    f.setColor(tempE.getEdgeColor()); //set 
                    f.setStroke(new BasicStroke(4));//How thick the line is
                    f.draw(line); //draw as in draw the line
                } 
            }//edgesColor
           

            // public void Color(Graphics2D f, boolean Y ){ //method to set color of edges
            //     if(Y){
            //         for (Edge containeEdges : Edges) { // search
            //             f.setColor(containeEdges.getEdgeColor()); //set 
            //             Shape line = containeEdges.getEdge();
            //             f.draw(line);
            //         }
            //     }
            //     if(Y!= true){ for (Vertex vertices : Vertexes) { // class type vertex is looked for in the arraylist
            //         f.setColor(vertices.getVertexColor()); //using the passed in value , I set the color use the specific vertice's get method
            //         Shape s = vertices.getVertex(); //
            //         f.fill(s);
            //     }}
            // }//edgesColor

            public boolean pointeUn(){ //A check to see if it is null
                if(pointUn != null){
                    return true;
                }
                return false;
            }//pointeUn

            public boolean pointeDos(){ 
                if(pointDos!= null){
                    return true;
                }
                return false;
            }//pointeDos

            public void removeThis(Edge top){  //Removes an edge by iterating through the arrayList..
                try{ //Since I was facing errors in terminal
                    if(top != null){
                        for( Iterator<Edge> listEdge = Edges.iterator(); listEdge.hasNext();){
                            Edge tempE = listEdge.next();
                            if(tempE.equals(top)){listEdge.remove();}
                            
                        }
                    }
                }catch(Exception e){
                }
            }//removeThis

            public void departureNGames(){ //a method to null the points
                pointUn = null;
                pointDos= null;
                temp = null;
                tinal = null;
            }//departureNGames

            public void mousePressed(MouseEvent e) {
                int numButton = picture.mainFrame.getNum(); //Strip the number
                // System.out.println("e is here" + e);
                
                if (numButton == 1) {
                    pointUn= null;
                    if(pointeUn() == false) pointUn = new Vertex(e.getX(), e.getY()); //call to the method for firstpoint
                    Vertexes.add(pointUn); //Addition of the new vertex
                    departureNGames();
                    // System.out.println("Add vertex pressed");
                    
                }//Button1 Add Vertex
                
                if (numButton == 2) {
                    // System.out.println("Add edge pressed");
                    if(pointeUn() == false) {pointUn = new Vertex(e.getX(), e.getY());  //using the mouseadapter, I pull off the x & y
                         for (Vertex v : Vertexes) { //traverse the AL to find the vertex
                            if (v.getVertex().contains(pointUn.getX(), pointUn.getY())) {
                                pointUn = v;
                                pointUn.setVertexColor(Color.GREEN); //indicate that it is clicked
                                }
                            }
                        }
                     else if(pointeDos() == false) {pointDos= new Vertex(e.getX(), e.getY());
                            for (Vertex v : Vertexes) {
                                if (v.getVertex().contains(pointDos.getX(), pointDos.getY())) {
                                    pointDos= v;
                                    newEdge = new Edge(pointUn, pointDos);
                                    System.out.println(newEdge+ "hhhh");
                                    Edges.add(newEdge);  //As the succession, create a edge
                                }
                            }
                        pointUn.setVertexColor(Color.RED);  //set it red 
                        pointDos.setVertexColor(Color.RED);
                        departureNGames(); //null
                    }
                    
                    picture.repaint();
                    
                }//Button2 Add Edge
                
                if (numButton == 3) {
                    try{ //Prevent errors in terminal with certain vertices
                        if(pointeUn() == false) {pointUn = new Vertex(e.getX(), e.getY());} //call to method
                            System.out.println(pointUn);
                        for (Vertex v : Vertexes) { //for each loop which looks through the specified class ib the array list
                            if (v.getVertex().contains(pointUn.getX(), pointUn.getY())) { // specific v using shape method and contains method of arraylist
                                     System.out.println(v.getVertex() + "heree");
                                pointUn = v;
                                Vertexes.remove(v);//arraylist method to remove
                                picture.repaint();

                            for (Edge x : Edges) { //Edge search
                            //   if(  x.getEdge().getX1() == pointUn.getX() && x.getEdge().getY1() == pointUn.getY()){
                                if(pointUn.equalsT(x.getEdge().getP1()));  tinal = x;   //if gets the Point 1 of Point of edge, and is checked against pointUn                   
                                    removeThis(tinal);
                                 }// Edge for each
                            }//if
                        }//Vertex for each
                      
                    }catch(Exception eu){
                    }
                        departureNGames(); //null
                        picture.repaint();
                }//Button3 Remove Vertex
                
                if (numButton == 4) {
                    System.out.println("remove edge pressed");
                    pointUn = new Vertex(e.getX(), e.getY()); //To prevent extra work. I could have created a Points instance, but alas I choose this.
                            for (Edge x : Edges){ 
                                // System.out.println(x.getEdge().ptSegDist(pointUn.getX(), pointUn.getY()) +"edges");
                            if( x.getEdge().ptSegDist(pointUn.getX(), pointUn.getY()) <= 5 ){ // The value returned by ptSegDist tells me how far away the click is from the edge. Through my meticulous clicks, I ascertained 5.
                                //  System.out.println("Lucky x" + x);
                                tinal = x;        
                            }
                        }
                        removeThis(tinal); //method in this class to remove something fom an AL
                        // }
                        departureNGames(); //Longevity of this method
                        picture.repaint();        
            }//Button4 Remove Edge

                if (numButton == 5) {
                    // System.out.println("move vertex");
                    if(pointeUn() == false){ pointUn = new Vertex(e.getX(), e.getY());
                        // System.out.println(pointUn+"ab");
                            for (Vertex v : Vertexes) { //loop through vertex classes
                                if (v.getVertex().contains(pointUn.getX(), pointUn.getY())) { // specific v using shape method and contains method of arraylist
                                    pointUn = v; // set it equal to pointUn
                                     temp = v;
                                pointUn.setVertexColor(Color.GREEN); //show that it is higlighted
                                // System.out.println(pointUn+"aouub");
                            }
                        }
                    }
                                else if(pointeDos() == false) {pointDos= new Vertex(e.getX(), e.getY()); // pointDos is the point to which pointUn is to be moved to
                                    // System.out.println(pointUn+ "jdj" + pointDos+"aouub");
                                    pointUn.updateLocation(pointDos.getX(), pointDos.getY()); //updateLocation method on vertex
                                    pointUn.setVertexColor(Color.RED); //chnage color from green to red
                                for (Edge x : Edges) {
                                        // System.out.println("THEY ARE" + x.getEdge());
                                //   if(   x.getEdge().getX() == temp.getX() && x.getEdge().getY() == temp.getY()){
                                    try{ //catch errors of empty click
                                if(temp.equalsT(x.getEdge().getP1())); {
                                        Hope.add(x); //Assistant arrayList
                            
                            
                        }
                    }
                                catch(Exception eu){

                    }
                        newEdge.updateELocation(pointUn, Hope);  //update locatiion method in edge
                        }
                        departureNGames(); //null
                        }
                        picture.repaint();

                                        }//Move Vertex Button
                if (numButton == 6) {
                    System.out.println("Add all edges");
                    for (Vertex v : Vertexes) {
                        // System.out.println(v+ "gh");
                        for (Vertex x : Vertexes) {
                            // System.out.println(x+ "vh");
                            pointUn = x;
                            pointDos= v;
                            if(!(pointUn.getVertex().equals(pointDos.getVertex()))){ //Basically,to prevent edges with itself
                                newEdge = new Edge(pointUn, pointDos);
                                Edges.add(newEdge);
                                // System.out.println(newEdge+ "Please click the gui to see the update!!");
                                
                                
                            }
                            // System.out.println(newEdge+ "hhhh");
                        }
                    }
                    JOptionPane.showMessageDialog( //I couldn't find a solution to gui not refreshing after pressing the jbutton
                    null, "Please click the gui to see the update!!");
                    
                    departureNGames();//If you choose to proceed to other buttons, this should come in handy!
                    picture.revalidate();
                        picture.repaint(); //GUI needs to be clicked once
                    

                }//Button6
                if (numButton == 7) {
                    System.out.println("Connected components boy");
                    for (Edge x : Edges) {
                        x.setRandomColor();
                     
                    }//for loop
                    JOptionPane.showMessageDialog( //I couldn't find a solution to gui not refreshing after pressing the jbutton
                    null, "Please click the gui to see the update!!");
                    picture.revalidate();
                    picture.repaint();
                }//Button7
                if (numButton == 8) {
                    System.out.println("Shortest cut vertices");
       
                    
                }//Button8
            }//MousePressed
    
        }//MouseClicker
    

        private int getNum() { //From the actionlistener and switch statement where the buttonNum is assigned, I call this method which is used in mouselicker
            // System.out.println(buttonNum + "blah");
            return buttonNum;
        }//getNum

        
        public void paint(Graphics g) {// method to be sure the
            super.paint(g);  // picture gets redrawn as it is modified
            picture.repaint();
            setVisible(true);
        }//paint
           
        
        private class GraphPicturePanel extends JPanel { //GPC
            private static final long serialVersionUID = 1L;
            protected GG0523 mainFrame;  //instance of frame
            protected MouseClicker mouseClick; // assists in the process of putting colors to buttons because that's where the method resides
    
    
            private GraphPicturePanel(GG0523 mainFrame) {
                this.mainFrame = mainFrame; //mainFrame is set herem upon which methods can be called from
                mouseClick = new MouseClicker(this); //using my frame
            }//GraphicPicturePanel
    
    
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2d = (Graphics2D) g; //Graphics 2d is casted. To creare dots, lines, shapes, and more...
               
                mouseClick.verticesColor(g2d); //call to methods to color, draw, fillin..
                mouseClick.edgesColor(g2d); 
                
                revalidate();
                repaint();
            }//paintComponent
        }//GraphPicturePanel

       
        
        private class Edge{
      
            private Color edgeColor = Color.BLUE; //default
            private Line2D edge;
            private Point2D v1,v2;
            private Random rand; //Random number generator
            
            public Edge(Vertex x, Vertex y){
                v1 = new Point2D.Double(x.getX(), x.getY()); //Creation  of Point2Ds using vertices
                v2 = new Point2D.Double(y.getX(),y.getY()); // I thought that it would assist in moving edges
                edge = new Line2D.Double(v1,v2); //the get methods of vertex are used. a line is then created(edge)
                
            }
            protected  Line2D getEdge() { //line2d itself
                return  edge;
            }
            protected void setRandomColor(){ //Connected Component Assistor
                rand = new Random(); 
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r,g,b); //Creating distinguishable colors each time
                this.edgeColor = randomColor;
                
            }
            private Color getEdgeColor() { 
                return edgeColor; 
            }//getEdgeColor

             public void updateELocation(Vertex f, ArrayList<Edge> a){ // Vertex which was moved, Helper ArrayList 

                for(Edge b : a){ //For each loop; There's only a particular edge, which is why it's not qualified further.
                // System.out.println(b);
                b.getEdge().setLine(f.getX(),f.getY(), b.getEdge().getX2(), b.getEdge().getY2()); //getEdge method of Edge class & setLine method of Line2D
            }
                /*

                Assisstance in updating of the edge locations !!BUT I CANNOT HELP NOT PERSERVE other edges to other vertices!!

                Deprecated Method
                //  protected void updateELocation(Edge x, int i ,Vertex f, Edges a){ 
                    // System.out.println(a.size()+"size" + f +"f");
                    */
                /*
                Deprecated Method
                */
                // if ( i ==1 ){ //Conceptually, I was trying to discern how moving vertexes and,thereby, edges would work
                //     x.getEdge().setLine(f.getX(),f.getY(), this.getEdge().getX2(), this.getEdge().getY2()); //getEdge method of Edge class & setLine method of Line2D
                // }
                // if ( i ==0 ){
                //     x.getEdge().setLine(this.getEdge().getX2(), this.getEdge().getY2(), f.getX(),f.getY() );
                // }
 
            }//updateELocation
        }//Edge Class
        
    
        private class Vertex {
            protected Point2D vPoints;
            protected Color vertexColor = Color.red;//red by default
            protected Shape vertex;
    
            private Vertex(int x, int y) {
                vPoints = new Point2D.Double(x, y); //coordinates to create a Point
                vertex = new Ellipse2D.Double( x, y, 16, 16); //coordinates, size of dot : for gui
            }
            private void updateLocation(double x , double y){
                vPoints.setLocation(x, y); //Point class has move method which eases the process
                vertex = new Ellipse2D.Double( x, y, 16, 16); // creation of the new point 
            }
            protected double getX() {
                return vPoints.getX(); //get the "x" of the poiint2d instance
            }
            protected double getY() {
                return vPoints.getY(); 
            }
            protected boolean equalsT (Point2D f){ //Used in determining whether a particular vertex has equal Point2D points to another
                // System.out.println(f + "f");
                // System.out.println(this.vPoints +"fjfj");
                if(this.vPoints.equals(f)) return true; //"this! If this instance calling it's points equal those points passed in...
                return false;
            }
    
            private Color getVertexColor() { //getthecolor 
                return vertexColor;
            }
            public void setVertexColor(Color vertexColor) { //set 
                this.vertexColor = vertexColor;
            }
            protected  Shape getVertex() { //returns the vertex itself, which assists in locating
                return vertex;
            }
                    
    }//Vertex Class
                
}//GG0523
                
            




    

  


     




