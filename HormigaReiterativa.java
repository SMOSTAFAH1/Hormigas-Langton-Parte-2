package hlm;

import hl.*;
import tads.IQueue;
import tads.LinkedQueue;

public class HormigaReiterativa extends HormigaModificada {
    private static enum Estatus { MOVIENDO, REITERANDO }
    private Estatus estatus;
    private int movimientos = 0;
    public static int vecesParaAlternar = 5;
    private final IQueue<Giro> giros = new LinkedQueue<>();

    public HormigaReiterativa() {
        this.estatus = Estatus.MOVIENDO;
    }

    public void cambiarEstatus() {
        if (estatus == Estatus.MOVIENDO && movimientos == vecesParaAlternar) {
            movimientos = 0;
            estatus = Estatus.REITERANDO;
        } else if (estatus == Estatus.REITERANDO && giros.isEmpty()) {
            estatus = Estatus.MOVIENDO;
        }
    }

    @Override
    public Giro girar(ICuadricula cuadricula) {
        cambiarEstatus();
        Giro giro = Giro.SIN_GIRO;
        if (estatus == Estatus.MOVIENDO) {
            giro = super.girar(cuadricula);
            giros.add(giro);
            movimientos++;
        } else if (super.coordenadas()[0] < 0 || super.coordenadas()[0] >= Cuadricula.DIM || super.coordenadas()[1] < 0 || super.coordenadas()[1] >= Cuadricula.DIM) {
            orientacion = orientacion.girarMediaVuelta();
            giros.poll();
            return Giro.MEDIA_VUELTA;
        } else if (!giros.isEmpty()) {
            giro = giros.peek();
            giros.poll();
            if (giro == null) {
                giro = Giro.MEDIA_VUELTA;
            }
            switch (giro) {
                case IZQUIERDA:
                    orientacion = orientacion.girarIzquierda();
                    break;
                case DERECHA:
                    orientacion = orientacion.girarDerecha();
                    break;
                case MEDIA_VUELTA:
                    orientacion = orientacion.girarMediaVuelta();
                    break;
                case SIN_GIRO:
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
        return giro;
    }

    @Override
    public String rutaDeLaImagen() {
        return estatus == Estatus.MOVIENDO ? "res/ant_1.png" : "res/ant_2.png";
    }

    @Override
    public String toString() {
        return String.format("st: %d, mov: %d, seq: %s, alt: %d", estatus.ordinal(), movimientos, giros.toString(),
                vecesParaAlternar);
    }
}