package AStarSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    Node parent;
    int col;
    int row;
    int gCost;      //Distance between current node and start node
    int hCost;      //Distance between current position and goal node
    int fCost;      //Sum of gCost and hCost
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.BLACK);
        addActionListener(this);
    }

    public void setAsStart(){
        setBackground(Color.red);
        setForeground(Color.BLACK);
        setText("START");
        start = true;
    }

    public void setAsGoal(){
        setBackground(Color.green);
        setForeground(Color.BLACK);
        setText("GOAL");
        goal = true;
    }

    public void setAsSolid(){
        setBackground(Color.BLACK);
        solid = true;
    }
    public void setAsOpen(){
        open = true;
    }
    public void setAsChecked(){
        if(start == false && goal == false){
            setBackground(Color.cyan);
            setForeground(Color.BLACK);
        }
        checked = true;
    }

    public void setAsPath(){
        setBackground(Color.magenta);
        setForeground(Color.black);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        setBackground(Color.CYAN);
    }
}
