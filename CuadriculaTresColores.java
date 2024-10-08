package hlm;

import hl.ICuadricula;
import hl.Casilla;

public class CuadriculaTresColores implements ICuadricula {
    public static final int DIM = 11;
    protected CasillaTresColores[][] casillas;

    public CuadriculaTresColores() {
        casillas = new CasillaTresColores[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                casillas[i][j] = new CasillaTresColores();
            }
        }
    }

    @Override
    public Casilla casilla(int i, int j) {
        if (i < 0 || i >= DIM || j < 0 || j >= DIM) {
            throw new IndexOutOfBoundsException();
        }
        return casillas[i][j];
    }
}