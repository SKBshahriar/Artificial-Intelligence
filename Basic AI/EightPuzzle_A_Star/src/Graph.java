import java.util.ArrayList;
import java.util.HashMap;

public class Graph {


    private int x,y;

    public Node startNode(){

        int[][] state = {
                {3,0,7},
                {2,8,1},
                {6,4,5}
        };

        Node stNode = new Node(state,0,null);


        return stNode;

    }

    public boolean reachedDestination(Node curNode){
        int cnt=1;

        for(int i=0;i<3;i++)
            for (int j=0;j<3;j++) {
                if (curNode.state[i][j] != cnt) return false;
                if(cnt == 8) cnt = 0;
                else cnt ++;
            }
        return  true;
    }

    private int simpleHeuristics(Node curNode) {
        int hValue=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int value = curNode.state[i][j];

                if(value!=0){
                    int originalIndexXAxis=0,originalIndexYAxis=0;

                    boolean flg=false;

                    int cnt=1;

                    for(int x=0;x<3;x++){
                        for(int y=0;y<3;y++){
                            if(cnt==value){
                                originalIndexXAxis=x;
                                originalIndexYAxis=y;
                                flg=true;
                                break;
                            }
                            if(cnt == 8) cnt = 0;
                            else cnt ++;
                        }
                        if(flg) break;;
                    }
                    hValue+=Math.abs(i-originalIndexXAxis) + Math.abs(j-originalIndexYAxis);
                }

            }

        }
        return  hValue;
    }
    static void swap(int[][] state,int a,int b,int c,int d)
    {
        int temp =state[a][b];
        state[a][b]=state[c][d];
        state[c][d]=temp;
    }
    public ArrayList<Node> getNeighbours(Node curNode){

        ArrayList<Node> neighbours = new ArrayList<Node>();

        int[][] state = new int[3][3];
        int cost=curNode.cost;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                state[i][j]=curNode.state[i][j];

        int row=0,col=0;
        boolean flg=false;
        for(row=0;row<3;row++) {
            for (col = 0; col < 3; col++) {
                if (state[row][col] == 0) {
                    flg = true;
                    break;
                }
            }
            if (flg) break;
        }

        if(row>0){
            swap(state,row,col,row-1,col);
            Node v = new Node(state,cost+1,curNode);
            v.predictedCost =simpleHeuristics(v);
            neighbours.add(v);
            swap(state,row,col,row-1,col);
        }
        if(row<2){
            swap(state,row,col,row+1,col);
            Node v = new Node(state,cost+1,curNode);
            v.predictedCost =simpleHeuristics(v);
            neighbours.add(v);
            swap(state,row,col,row+1,col);
        }
        if(col>0){
            swap(state,row,col,row,col-1);
            Node v = new Node(state,cost+1,curNode);
            v.predictedCost =simpleHeuristics(v);
            neighbours.add(v);
            swap(state,row,col,row,col-1);
        }
        if(col<2){
            swap(state,row,col,row,col+1);
            Node v = new Node(state,cost+1,curNode);
            v.predictedCost =simpleHeuristics(v);
            neighbours.add(v);
            swap(state,row,col,row,col+1);
        }
        return neighbours;
    }
}
