import java.util.*;

public class Awtron {
    final int INF_P = Integer.MAX_VALUE;
    final int INF_N = Integer.MIN_VALUE;

    private char sig;

    public static void main(String[] args) {
        char table[][] = {
                { 'x', 0, 0 },
                { 0, 'o', 0 },
                { 'o', 0, 'x' }
        };

        Awtron it = new Awtron('x');

        it.jugada(table);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(((char) table[i][j] == 'o' || (char) table[i][j] == 'x') ? table[i][j] : '-');
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
        int valueA = INF_N, valueC = INF_P, jugS = 0;
        int[] sgtJug;
        for (int i = 0; i < jugadasA.size(); i++) {
            int[] jug = jugadasA.get(i);
            table[jug[0]][jug[1]] = this.sig;
            jugadasC = getJugadas(table);
            for (int[] jE : jugadasC) {
                table[jE[0]][jE[1]] = this.sig == 'x' ? 'o' : 'x';
                valueC = Math.min(fun_val(table), valueC);
                table[jE[0]][jE[1]] = 0;
            }

            if (valueC > valueA) {
                jugS = i;
                valueA = valueC;
            }
            valueC = INF_P;
            table[jug[0]][jug[1]] = 0;
        }
        sgtJug = jugadasA.get(jugS);

        // jugar
        table[sgtJug[0]][sgtJug[1]] = this.sig;
    }

    private int fun_val(char table[][]) {
        return cantJugGan(table, this.sig) - cantJugGan(table, this.sig == 'x' ? 'o' : 'x');
    }

    private boolean valid(char table[][], int i, int j, char c) {
        return table[i][j] == 0 || table[i][j] == c;
    }

    private int cantJugGan(char table[][], char c) {
        int cant = 0;
        // filas
        if (valid(table, 0, 0, c) && valid(table, 0, 1, c) && valid(table, 0, 2, c))
            cant++;
        if (valid(table, 1, 0, c) && valid(table, 1, 1, c) && valid(table, 1, 2, c))
            cant++;
        if (valid(table, 2, 0, c) && valid(table, 2, 1, c) && valid(table, 2, 2, c))
            cant++;

        // diagonales
        if (valid(table, 0, 0, c) && valid(table, 1, 1, c) && valid(table, 2, 2, c))
            cant++;
        if (valid(table, 0, 2, c) && valid(table, 1, 1, c) && valid(table, 2, 0, c))
            cant++;

        // columnas
        if (valid(table, 0, 0, c) && valid(table, 1, 0, c) && valid(table, 2, 0, c))
            cant++;
        if (valid(table, 0, 1, c) && valid(table, 1, 1, c) && valid(table, 2, 1, c))
            cant++;
        if (valid(table, 0, 2, c) && valid(table, 1, 2, c) && valid(table, 2, 2, c))
            cant++;

        if (winner(table, c)) {
            return c == this.sig ? INF_P : INF_N;
        }
        return cant;
    }

    private boolean winner(char table[][], char c) {
        int total = 3 * c;
        int filas, columnas, dig1, dig2;
        filas = columnas = dig1 = dig2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                filas += table[i][j];
                columnas += table[j][i];
            }
        }

        for (int i = 0; i < 3; i++) {
            dig1 += table[i][i];
            dig2 += table[i][3 - i - 1];
        }
        return filas == total || columnas == total || dig1 == total || dig2 == total;
    }

    private ArrayList<int[]> getJugadas(char table[][]) {
        ArrayList<int[]> res = new ArrayList<int[]>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 0) {
                    int[] r = { i, j };
                    res.add(r);
                }
            }
        }
        return res;
    }
}
