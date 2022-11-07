import java.util.Scanner;

public class Main {

    public static boolean gano = false;

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int[][] posiciones = {
            {0, 0},{0,1},{0,2},
            {1, 0},{1,1},{1,2},
            {2, 0},{2,1},{2,2}
        };
        int pos_c;
        char table[][] = new char[3][3];
        Awtron j1 = new Awtron('x');
        boolean turno = true;
        while (!fin(table) && !gano) {
            if (!turno) {
                System.out.println("Awtron >:)");
                j1.jugar(table);
            } else {
                System.out.print("Jugada [1..9]: ");
                pos_c = leer.nextInt();
                pos_c--;
                int[] p = posiciones[pos_c];
                table[p[0]][p[1]] = 'o';
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(((char) table[i][j] == 'o' || (char) table[i][j] == 'x') ? table[i][j] : '-');
                }
                System.out.println();
            }
            System.out.println();
            turno = !turno;
        }
        leer.close();
    }

    static boolean fin(char table[][]) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(table[i][j] == 0) return false;
            }
        }
        return true;
    }
}
