public class Main {

    public static boolean gano = false;
    public static void main(String[] args) {
        char table[][] = new char[3][3];
        Awtron j1 = new Awtron('x');
        Awtron j2 = new Awtron('o');
        boolean turno = true;
        while(!fin(table) && !gano) {
            if(turno) j1.jugada(table);
            else j2.jugada(table);
            for(int i = 0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    System.out.print(((char)table[i][j] == 'o' || (char)table[i][j] == 'x') ? table[i][j] : '-');
                }
                System.out.println();
            }
            System.out.println();
            turno = !turno;            
        }
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
