package taller2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Rectangulo {
        int x; // Coordenada x del inicio
        int X2; // Ancho
        int altura; // Altura

        public Rectangulo(int x, int X2, int altura) {
            this.x = x;
            this.X2 = X2;
            this.altura = altura;
        }
    }

    static class punto {
        int X; // Coordenada x del inicio
        int Y; // Ancho

        public punto(int x, int i) {
            this.X = x;
            this.Y = i;
        }

        @Override
        public String toString() {
            return "[" + X + ", " + Y + "]";
        }

    }

    public static punto contienen(Rectangulo r, Rectangulo maxRec) {
        // r esta contenida en maxRec
        int contieneX = 0;
        int contieneY = 0;
        if (r.x >= maxRec.x && r.X2 <= maxRec.X2) {
            // Si la coordenada x del rectángulo r es mayor o igual a la coordenada x del
            // rectángulo máximo
            // y el ancho del rectángulo r es menor o igual al ancho del rectángulo máximo,
            // entonces r está contenido en maxRec en el eje X.
            contieneX = 1;
        }
        if (r.x < maxRec.X2) {
            // Si la coordenada x del rectángulo r es menor que el ancho del rectángulo
            // máximo,
            // entonces hay una posible intersección en el eje X.

            if (r.altura <= maxRec.altura) {
                // Si la altura del rectángulo r es menor o igual a la altura del rectángulo
                // máximo,
                // entonces r está contenido en maxRec en el eje Y.
                contieneY = 1;
            }
        }
        return new punto(contieneX, contieneY);

    }

    public static List<punto> calcularSilueta(List<Rectangulo> rectangles) {

        Rectangulo FigMax = new Rectangulo(0, 0, 0);
        List<punto> silueta = new ArrayList<>();
        int Xfin = 0;
        int y = 0;
        int size = rectangles.size();

        System.err.println(size);

        int i = 0;
        do {
            // Obtiene el rectángulo actual de la lista.
            Rectangulo r = rectangles.get(i);
            // Encuentra la intersección entre el rectángulo actual y el rectángulo más
            // grande.
            punto G = (contienen(r, FigMax));
            // Obtengo el siguiente rectangulo de la lista
            Rectangulo r2 = rectangles.get(i);
            if (i + 1 < rectangles.size()) {
                r2 = rectangles.get(i + 1);
            }
            // si r no esta contenido en FigMax
            if (G.X == 0 && G.Y == 0) {
                // agrega el punto con cordenada x = r.x , r.y
                silueta.add(new punto(r.x, r.altura));
                // si el siguiente rectangulo no es null
                if (r2.x > r.X2) {
                    silueta.add(new punto(r.X2, y));
                }
                // si es null agrega el punto con cordenada x = r.X2 , 0 esquina inferior
                else if (r2 == null) {
                    silueta.add(new punto(r.X2, 0));
                }
                // asigna a la figura mas grande r
                FigMax = r;
            }

            // si r esta contenido en FigMax en X pero no en Y
            if (G.X == 1 && G.Y == 0) {
                // agrega el punto con cordenada x = r.x , r.y
                silueta.add(new punto(r.x, r.altura));
                // agrega el punto con cordenada x = r.anchura + FigMax.Y ,
                silueta.add(new punto(r.X2, FigMax.altura));
            }
            // si r esta contenido en FigMax en Y pero no en X
            if (G.X == 0 && G.Y == 1) {
                // agrega el punto con cordenada x = rgrande.anchura + r.Y
                silueta.add(new punto(FigMax.X2, r.altura));
                // asigna a la figura mas grande r
                FigMax = r;
            }
            // si la anchura del r actual es la mayor de todas la asigna a xfin para el
            // punto final
            if (Xfin < r.X2) {
                Xfin = r.X2;
            }

            i++;

        } while (i < size);

        // agrega el punto final
        silueta.add(new punto(Xfin, 0));
        return silueta;

    }

    public static void main(String[] args) {
        List<Rectangulo> rectangles = new ArrayList<>();
        rectangles.add(new Rectangulo(1, 3, 4));
        rectangles.add(new Rectangulo(2, 4, 6));
        rectangles.add(new Rectangulo(5, 9, 3));
        rectangles.add(new Rectangulo(6, 8, 4));
        // prueba con 1000 rectangulos

        for (int j = 0; j < 1000; j++) {
            rectangles
                    .add(new Rectangulo(j, (int) (Math.random() * (20 - 1) + 1), (int) (Math.random() * (20 - 1) + 1)));
        }

        long startTime = System.nanoTime();
        List<punto> silueta2 = calcularSilueta(rectangles);
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        // Imprimir la silueta
        for (punto r : silueta2) {
            System.out.println(r);
        }
        System.out.println("Con " + rectangles.size() + " rectangulos");
        System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + executionTime + " nanosegundos");
        System.out
                .println("Tiempo de ejecución de calcularSiluetaIterativo: " + (executionTime / 1e6) + " milisegundos");
        System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + (executionTime / 1e9) + " segundos");
    }

}
