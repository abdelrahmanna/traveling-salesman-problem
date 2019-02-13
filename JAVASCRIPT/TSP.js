        var l = 0;

        //input sample
        var w = [[0, 2, 9, 200],
                 [1, 0, 6, 4],
                 [200, 7, 0, 8],
                 [6, 3, 200, 0]];
        
        var n = w[0].length;
        var p = [];
        for (var i = 0; i <= n ; i++) {
            p[i] = [];
        }
         
         //ptint shortest cylce's  length
        console.log("The shortest cycle is of legnth "+ trvel(n, w, p));
        
        //print the shotrest cycle
        console.log("The shortest cycle is ");
        console.log("V0 ");
        print_path(0,p,k-1);
        console.log("V0 ");
    function  trvel(n,w, p) {
      var k =  Math.pow(2, (n - 1));
      var D =  [];
      for (var i = 0; i <= n ; i++) {
            D[i] = [];
      }
      
      //initializing D[A][v1]
      for (var i = 1; i < n; i++) {
            D[i][0] = w[i][0];
      }
    
      //finding the shortest path excluding v1
        for (var i = 1; i <= n - 2; i++) {
            for (var subset = 1; subset < k; subset++) {
                if (len(subset) == i) {
                    for (var v = 1; v < n; v++) {
                        if (!haveI(subset, v-1)) {
                            D[v][subset] = min(v, w, D, subset, n);
                            p[v][subset] = l;
                        }
                    }
                }
            }
        }
      var m = min(0, w, D, k-1, n);
      p[0][k-1] = l;
      return m;
    }

    // finding the cardinality of a subset
    function len(j){
        var count = 0;
        while (j != 0) {
            j = j & (j - 1);
            count++;
        }
        return count;
    }
       
    //checking if Vi for some i belongs to a subset 
    function haveI(subset, position) {
        var num = subset & ~(1 << (position));
        return (num & subset) != subset;
    }

    // finding the minimum of (W[v][j]+D[j][subsrt - v] for every j
    function min(v,w, D,set,n) {
        var m = [];
        var i = [];
        var ind = 0;
            for(var j = 0 ; j< n-1 ; j++)
                if(haveI(set,j))
                {
                    var num = set & ~(1 << (j));
                    num = set & num;
                    m[ind] = w[v][j+1]+D[j+1][num];
                    i[ind]=j+1;
                    ind++;
                }
            var min = m[0];
            l = i[0];
            for(var j = 1; j < len(set);j++)
                if(min>m[j]){
                    min=m[j];
                    l = i[j];
                }
                            
        return min;
       
    }
    
    //printing the shortest path
    function print_path(i,  p, n) {
       while(n > 0 ){
           console.log("V"+p[i][n]+" ");
           i =p[i][n];
           n = n& ~(1 << (i-1));
       }
    }
