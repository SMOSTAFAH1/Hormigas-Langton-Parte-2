package hlm;

import java.util.Random;
import hl.Casilla;

public class CasillaTresColores extends Casilla {
    static final Random r = new Random(31416);
    private int color;

    public CasillaTresColores() {
        int aleatorio = r.nextInt(100);
        if (aleatorio < 20) {
            this.color = 1;
        } else if (aleatorio < 40) {
            this.color = 2;
        } else {
            this.color = 0;
        }
    }

    @Override
    public void cambiarColor() {
        color = (color + 1) % 3;
    }

    @Override
    public int color() {
        return color;
    }
}