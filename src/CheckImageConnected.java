public class CheckImageConnected {
	
	public boolean find(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        boolean[][] v = new boolean[n][m];
        for(int i=0;i<n;i++) {
            int j = 0;
            for(j=0;j<m;j++) {
                if(arr[i][j] == 1) {
                    dfs(i, j, arr, v);
                    break;
                }
            }
            if(j != m)
                break;
        }
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(arr[i][j] == 1 && !v[i][j])
                    return false;
        return true;
    }
	
    private void dfs(int i, int j, int[][] arr, boolean[][] v) {
        if(i < 0 || j < 0 || i >= arr.length || j>= arr[0].length)
            return;
        if(v[i][j] || arr[i][j] != 1)
            return;
        v[i][j] = true;
        dfs(i-1, j, arr, v);
        dfs(i-1, j-1, arr, v);
        dfs(i-1, j+1, arr, v);
        dfs(i, j-1, arr, v);
        dfs(i, j+1, arr, v);
        dfs(i+1, j, arr, v);
        dfs(i+1, j-1, arr, v);
        dfs(i+1, j+1, arr, v);
    }
	
}

