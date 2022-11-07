import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int[][] posiciones = {
                { 0, 0 }, { 0, 1 }, { 0, 2 },
                { 1, 0 }, { 1, 1 }, { 1, 2 },
                { 2, 0 }, { 2, 1 }, { 2, 2 }
        };
        int pos_c;
        String sig_o;
        char table[][] = new char[3][3];
        Awtron agente;
        System.out.print("Escoge x ó o: ");
        sig_o = leer.nextLine();
        if (sig_o.equals("x"))
            agente = new Awtron('o');
        else
            agente = new Awtron('x');
        System.out.print("¿Comenzar primero?[y/n]: ");
        sig_o = leer.nextLine();
        boolean turno = true, first = false;
        if (sig_o.equals("y")) {
            turno = false;
        } else
            first = true;
        if (first) {
            System.out.println("Awtron");
            int jO = (int) (Math.random() * 9);
            int[] jA = posiciones[jO];
            table[jA[0]][jA[1]] = agente.getSigno();
            turno = !turno;
            showTable(table);
        }
        while ((!gano(table, 'x') && !gano(table, 'o'))) {
            if (turno) {
                System.out.println("Awtron");
                agente.jugar(table);
            } else {
                System.out.print("Jugada [1..9]: ");
                pos_c = leer.nextInt();
                pos_c--;
                int[] p = posiciones[pos_c];
                table[p[0]][p[1]] = agente.getSigno() == 'x' ? 'o' : 'x';
            }
            showTable(table);
            turno = !turno;
            if(fin(table)) break;
        }
        leer.close();
    }

    static void showTable(char table[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(((char) table[i][j] == 'o' || (char) table[i][j] == 'x') ? table[i][j] : '-');
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean fin(char table[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    static boolean gano(char table[][], char c) {
        int total = 3 * c;
        int filas, columnas, dig1, dig2;
        filas = columnas = dig1 = dig2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                filas += table[i][j];
                columnas += table[j][i];
            }
            if (filas == total || columnas == total)
                return true;
            filas = 0;
            columnas = 0;
        }

        for (int i = 0; i < 3; i++) {
            dig1 += table[i][i];
            dig2 += table[i][3 - i - 1];
        }
        return dig1 == total || dig2 == total;
    }
}
