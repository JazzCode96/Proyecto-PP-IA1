for(int i = 0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    System.out.print(((char)table[i][j] == 'o' || (char)table[i][j] == 'x') ? table[i][j] : '-');
                }
                System.out.println();
            }