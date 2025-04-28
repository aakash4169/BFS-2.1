//Time Complexity: 0(m*n)
//Space Complexity: 0(h)
/*
This code solves the Rotting Oranges problem using a Depth-First Search (DFS) approach.
It first locates all initially rotten oranges (value 2) and starts DFS from each,
marking the time it takes to rot neighboring fresh oranges (1).
During DFS, each cell is updated with the minimum time it gets rotten,
avoiding overwriting a faster rotting time. After processing,
it checks for any remaining fresh oranges; if found,
it returns -1, indicating it's impossible to rot all oranges.
Otherwise, it returns the maximum time taken minus three, adjusting for the initial offset used.
* */



class RottingOranges {
    public int orangesRotting(int[][] grid) {


        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==2)
                {
                    ///any offset above 2 will work
                    grid[i][j]=4;
                    dfs(grid,i,j,3);
                }
            }
        }

        int result=3;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                    return -1;
                else
                    result=Math.max(result,grid[i][j]);

            }
        }


        return result - 3;
    }
    private void dfs(int[][] grid,int r,int c,int time)
    {
        int[][] dirs=new int[][]{{-1,0},{1,0},{0,1},{0,-1}};

        if(r<0 || c<0 || r==grid.length || c==grid[0].length)
        {

            return;
        }
        if(grid[r][c]!=1 && grid[r][c]< time) return;
        grid[r][c]=time;
        for(int[] dir:dirs)
        {
            int newRow=r + dir[0];
            int newCol=c+dir[1];
            dfs(grid,newRow,newCol,time+1);
        }
    }
}