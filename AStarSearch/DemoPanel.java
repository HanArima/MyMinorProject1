package AStarSearch;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DemoPanel extends JPanel {
    //Displaying "Nodes" (here Tiles)

    // Screen Settings
    final int maxCol = 10;
    final int maxRow = 10;
    final int nodeSize = 90;
    final int screenWidth = nodeSize*maxCol;
    final int screenHeight = nodeSize*maxRow;

    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();       //All the nodes open(currently being evaluated, all the fcosts in consideration) are in this list
    ArrayList<Node> checkedList = new ArrayList<>();    //All the nodes that are selected(after evaluation, the oned with lowest fCost) are in this list

    boolean goalreached = false;
    public DemoPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxCol,maxRow));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // NODES ON THE SCREEN

        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow){
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);

            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }

        //SET START AND END GOAL
        setStartNode(3,6);
        setGoalNode(8,2);

        //SET OBSTACLES
        setSolidNode(5,1);
        setSolidNode(5,2);
        setSolidNode(5,5);
        setSolidNode(5,6);
        setSolidNode(6,1);
        setSolidNode(7,1);
        setSolidNode(7,2);
        setSolidNode(7,3);
        setSolidNode(7,4);
        setSolidNode(8,4);
        setSolidNode(8,5);

        //Calculate Cost
        setCostOnNode();


    }

    private void setStartNode(int col, int row){
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row){
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }

    private void setCostOnNode(){
        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow){
            getCost(node[col][row]);
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }

    }

    private void getCost(Node node){
        //gCost
        int xDist = Math.abs(node.col - startNode.col);
        int yDist = Math.abs(node.row - startNode.row );
        node.gCost = xDist + yDist;

        //hCost
        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row );
        node.hCost = xDist + yDist;

        //fCost
        node.fCost = node.gCost + node.hCost;
        if(node != startNode && node != goalNode){
            node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "<br>H:" + node.hCost);
        }
    }

    public void search(){
        //Before Evaluating the node or path, each time we will first check if have reached the goal or not
        if(goalreached == false){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            //Check the Neighbours
            if( row -1 >= 0 ){
                //Open the UP Node
                openNode(node[col][row-1]);
            }
            if( col-1 >= 0){
                //Open the LEFT Node
                openNode(node[col-1][row]);
            }
            if( col+1 < maxCol){
                //Open the RIGHT Node
                openNode(node[col+1][row]);
            }
            if( row+1 < maxRow){
                //Open the DOWN Node
                openNode(node[col][row+1]);
            }
            //FINDING THE BEST FCOST(LOWEST) FROM THE OPEN LIST
            int bestNodeIndex = 0;
            int bestNodefCost = 100;

            for(int i = 0; i < openList.size(); i++){
                //Check if the "F COST" of i Node is better or not
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //If F cost is equal, we use G Cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            // ??? After the loop ends, we get our next step. Therefore, we will get that node from open.
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalreached = true;
            }
        }
    }

    public void autoSearch(){
        //Before Evaluating the node or path, each time we will first check if have reached the goal or not
        while(goalreached == false ){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            //Check the Neighbours
            if( row -1 >= 0 ){
                //Open the UP Node
                openNode(node[col][row-1]);
            }
            if( col-1 >= 0){
                //Open the LEFT Node
                openNode(node[col-1][row]);
            }
            if( col+1 < maxCol){
                //Open the RIGHT Node
                openNode(node[col+1][row]);
            }
            if( row+1 < maxRow){
                //Open the DOWN Node
                openNode(node[col][row+1]);
            }
            //FINDING THE BEST FCOST(LOWEST) FROM THE OPEN LIST
            int bestNodeIndex = 0;
            int bestNodefCost = 100;

            for(int i = 0; i < openList.size(); i++){
                //Check if the "F COST" of i Node is better or not
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //If F cost is equal, we use G Cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            // ??? After the loop ends, we get our next step. Therefore, we will get that node from open.
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalreached = true;
                trackThePath();
            }
        }
    }
    private void openNode(Node node){
        if(node.open == false && node.checked == false && node.solid == false){
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackThePath(){
        Node current = goalNode;

        while(current != startNode){
            current = current.parent;

            if (current != startNode){
                current.setAsPath();
            }
        }
    }
}
