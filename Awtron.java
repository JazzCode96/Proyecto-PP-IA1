import java.util.*;
import java.util.concurrent.CountDownLatch;
//import javafx.util.*;
public class Awtron {
    final int INF_P = Integer.MAX_VALUE;
    final int INF_N = Integer.MIN_VALUE;

    private char sig;

    public static void main(String[] args) {
        char table[][] = {
            {'o', 0, 'x'},
            {0, 'x', 0},
            { 'o',    0,   'o'}
        };

        Awtron it = new Awtron('x');

        it.jugada(table);
        
        

        for(int i = 0; i<3; i++) {
            for(int j=0; j<3; j++) {
                System.out.print(((char)table[i][j] == 'o' || (char)table[i][j] == 'x') ? table[i][j] : '-');
            }
            System.out.println();
        }
    }

    public Awtron(char c) {
        this.sig = c;
    }

    public void jugada(char table[][]) {
        ArrayList<int[]> jugadasA = new ArrayList<int[]>();
        ArrayList<int[]> jugadasC = new ArrayList<int[]>();
        jugadasA = getJugadas(table);
        int op = (int)Math.random() * jugadasA.size();
        
        int alfa, beta;
        int value = INF_P;
        int[] sgtJug;

        //rama inicial

        int[] jI = jugadasA.get(op);
        table[jI[0]][jI[1]] = sig;

        jugadasC = getJugadas(table);
        for(int[] pC : jugadasC) {
            table[pC[0]][pC[1]] = this.sig == 'x' ? 'o' : 'x';
            value = Math.min(value, fun_val(table));
            table[pC[0]][pC[1]] = 0;
        }
        alfa = value;
        sgtJug = jI;
        table[jI[0]][jI[1]] = 0;
        //rama inicial

        for(int i= 0; i<jugadasA.size(); i++) {
            int[] jug = jugadasA.get(i);
            if(i != op ) {
                table[jug[0]][jug[1]] = this.sig;
                jugadasC = getJugadas(table);
                int[] jE = jugadasC.get((int)Math.random() * jugadasC.size());
                table[jE[0]][jE[1]] = this.sig == 'x' ? 'o' : 'x';
                beta = fun_val(table);
                if(beta > alfa) {
                    alfa = beta;
                    sgtJug = jugadasA.get(i);
                }
                table[jE[0]][jE[1]] = 0;
                table[jug[0]][jug[1]] = 0;
            }
        }

        //jugar
        table[sgtJug[0]][sgtJug[1]] = this.sig;
    }

    private int fun_val(char table[][]) {
        //System.out.println(cantJugGan(table, this.c) + " " + cantJugGan(table, this.c == 'x' ? 'o' : 'x'));
        return cantJugGan(table, this.sig) - cantJugGan(table, this.sig == 'x' ? 'o' : 'x');
    }

    private boolean valid(char table[][], int i, int j, char c) {
        return table[i][j] == 0 || table[i][j] == c;
    }

    private int cantJugGan(char table[][], char c) {
        int cant = 0;
        //filas
        if(valid(table, 0, 0, c) && valid(table, 0, 1, c) && valid(table,0,2,c)) cant++;
        if(valid(table, 1, 0, c) && valid(table, 1, 1, c) && valid(table,1,2,c)) cant++;
        if(valid(table, 2, 0, c) && valid(table, 2, 1, c) && valid(table,2,2,c)) cant++;

        //diagonales
        if(valid(table, 0, 0, c) && valid(table, 1, 1, c) && valid(table,2,2,c)) cant++;
        if(valid(table, 0, 2, c) && valid(table, 1, 1, c) && valid(table,2,0,c)) cant++;
        
        //columnas
        if(valid(table, 0, 0, c) && valid(table, 1, 0, c) && valid(table,2,0,c)) cant++;
        if(valid(table, 0, 1, c) && valid(table, 1, 1, c) && valid(table,2,1,c)) cant++;
        if(valid(table, 0, 2, c) && valid(table, 1, 2, c) && valid(table,2,2,c)) cant++;

        if(winner(table, c)) return c == this.sig ? INF_P : INF_N;
        return cant;
    }

    public boolean winner(char table[][], char c) {
        int r = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++ ) {
                if(table[i][j] == c) r++;
                else {
                    if(table[i][j] != 0) r--;
                }
            }
            if(r == 3) return true;
            r = 0;
        }

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++ ) {
                if(table[j][i] == c) r++;
                else {
                    if(table[j][i] != 0) r--;
                }
            }
            if(r == 3) return true;
            r = 0;
        }

        for(int i=0; i<3; i++) {
            if(table[i][i] == c) r++;
            else {
                if(table[i][i] != 0) r--;
            }
        }
        if(r == 3) return true;
        r = 0;
        if(table[0][2] == c) r++;
        if(table[1][1] == c) r++;
        if(table[2][0] == c) r++;
        return r == 3;
    }

    public ArrayList<int[]> getJugadas(char table[][]) {
        ArrayList<int[]> res = new ArrayList<int[]>();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(table[i][j] == 0) {
                    int[] r = {i, j};
                    //System.out.println(i + ", " + j);
                    res.add(r);           
                }
            }
        }
        //System.out.println();
        return res;
    }
}
/*
 REAS

 R          E            A                  S

 */
