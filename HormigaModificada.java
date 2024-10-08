package hlm;

import hl.*;

public class HormigaModificada extends Hormiga {

    public HormigaModificada() {
        super();
    }

    @Override
    public Giro girar(ICuadricula cuadricula) {
        if (super.coordenadas()[0] < 0 || super.coordenadas()[0] >= Cuadricula.DIM || super.coordenadas()[1] < 0 || super.coordenadas()[1] >= Cuadricula.DIM) {
            orientacion = orientacion.girarMediaVuelta();
            return Giro.MEDIA_VUELTA;
        }
        Casilla casillaActual = cuadricula.casilla(super.coordenadas()[0], super.coordenadas()[1]);
        if (casillaActual.color() == 0) {
            orientacion = orientacion.girarIzquierda();
            return Giro.IZQUIERDA;
        } else if (casillaActual.color() == 1) {
            orientacion = orientacion.girarDerecha();
            return Giro.DERECHA;
        } else if (casillaActual.color() == 2) {
            orientacion = orientacion.girarMediaVuelta();
            return Giro.MEDIA_VUELTA;
        } else {
            throw new IllegalStateException();
        }
    }
}