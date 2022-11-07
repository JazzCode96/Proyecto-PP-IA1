import java.util.*;

public class Awtron {
    final int INF_P = Integer.MAX_VALUE;
    final int INF_N = Integer.MIN_VALUE;

    private char signo;

    public Awtron(char c) {
        this.signo = c;
    }

    public void jugar(char table[][]) {
        // ejecutar MINIMAX
        int[] jugada = miniMax(table);
        // jugar
        table[jugada[0]][jugada[1]] = this.signo;
    }

    public int[] miniMax(char table[][]) {
        ArrayList<int[]> jugadasAgente = new ArrayList<int[]>();
        ArrayList<int[]> jugadasContrincante = new ArrayList<int[]>();
        jugadasAgente = getJugadasPosibles(table);
        int max = INF_N, min = INF_P;
        int[] sgtJugada = jugadasAgente.get(0);
        for (int i = 0; i < jugadasAgente.size(); i++) {
            int[] jugada = jugadasAgente.get(i);
            table[jugada[0]][jugada[1]] = this.signo;
            jugadasContrincante = getJugadasPosibles(table);
            for (int[] jE : jugadasContrincante) {
                table[jE[0]][jE[1]] = this.signo == 'x' ? 'o' : 'x';
                min = Math.min(funcionEvaluacion(table), min);
                table[jE[0]][jE[1]] = 0;
            }

            if (min > max) {
                sgtJugada = jugadasAgente.get(i);
                max = min;
            }
            min = INF_P;
            table[jugada[0]][jugada[1]] = 0;
        }
        return sgtJugada;
    }

    private int funcionEvaluacion(char table[][]) {
        return cantJugGanadoras(table, this.signo) - cantJugGanadoras(table, this.signo == 'x' ? 'o' : 'x');
    }

    private boolean valid(char table[][], int i, int j, char c) {
        return table[i][j] == 0 || table[i][j] == c;
    }

    private int cantJugGanadoras(char table[][], char c) {
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

        if (gano(table, c)) {
            return c == this.signo ? INF_P : INF_N;
        }
        return cant;
    }

    private boolean gano(char table[][], char c) {
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

    private ArrayList<int[]> getJugadasPosibles(char table[][]) {
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
