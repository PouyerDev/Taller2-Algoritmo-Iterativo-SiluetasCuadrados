package taller2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Rectangle {
        int x; // Coordenada x del inicio
        int width; // Ancho
        int height; // Altura

        public Rectangle(int x, int width, int height) {
            this.x = x;
            this.width = width;
            this.height = height;
        }
    }

    static class XYRectangle {
        int X; // Coordenada x del inicio
        int Y; // Ancho

        public XYRectangle(int x, int i) {
            this.X = x;
            this.Y = i;
        }

        @Override
        public String toString() {
            return "[" + X + ", " + Y + "]";
        }

    }

    public static XYRectangle contienen(Rectangle r, Rectangle maxRec) {
        // r esta contenida en maxRec
        int contieneX = 0;
        int contieneY = 0;
        if (r.x >= maxRec.x && r.width <= maxRec.width) {
            // Si la coordenada x del rectángulo r es mayor o igual a la coordenada x del
            // rectángulo máximo
            // y el ancho del rectángulo r es menor o igual al ancho del rectángulo máximo,
            // entonces r está contenido en maxRec en el eje X.
            contieneX = 1;
        }
        if (r.x < maxRec.width) {
            // Si la coordenada x del rectángulo r es menor que el ancho del rectángulo
            // máximo,
            // entonces hay una posible intersección en el eje X.

            if (r.height <= maxRec.height) {
                // Si la altura del rectángulo r es menor o igual a la altura del rectángulo
                // máximo,
                // entonces r está contenido en maxRec en el eje Y.
                contieneY = 1;
            }
        }
        return new XYRectangle(contieneX, contieneY);

    }

    public static List<XYRectangle> calcularSilueta(List<Rectangle> rectangles) {

        Rectangle FigMax = new Rectangle(0, 0, 0);
        List<XYRectangle> silueta = new ArrayList<>();
        int Xfin = 0;
        int y = 0;
        int size = rectangles.size();

        System.err.println(size);

        int i = 0;
        do {
            // Obtiene el rectángulo actual de la lista.
            Rectangle r = rectangles.get(i);
            // Encuentra la intersección entre el rectángulo actual y el rectángulo más
            // grande.
            XYRectangle G = (contienen(r, FigMax));
            // Obtengo el siguiente rectangulo de la lista
            Rectangle r2 = rectangles.get(i);
            if (i + 1 < rectangles.size()) {
                r2 = rectangles.get(i + 1);
            }
            // si r no esta contenido en FigMax
            if (G.X == 0 && G.Y == 0) {
                // agrega el punto con cordenada x = r.x , r.y
                silueta.add(new XYRectangle(r.x, r.height));
                // si el siguiente rectangulo no es null
                if (r2.x > r.width) {
                    silueta.add(new XYRectangle(r.width, y));
                }
                // si es null agrega el punto con cordenada x = r.width , 0 esquina inferior
                else if (r2 == null) {
                    silueta.add(new XYRectangle(r.width, 0));
                }
                // asigna a la figura mas grande r
                FigMax = r;
            }

            // si r esta contenido en FigMax en X pero no en Y
            if (G.X == 1 && G.Y == 0) {
                // agrega el punto con cordenada x = r.x , r.y
                silueta.add(new XYRectangle(r.x, r.height));
                // agrega el punto con cordenada x = r.anchura + FigMax.Y ,
                silueta.add(new XYRectangle(r.width, FigMax.height));
            }
            // si r esta contenido en FigMax en Y pero no en X
            if (G.X == 0 && G.Y == 1) {
                // agrega el punto con cordenada x = rgrande.anchura + r.Y
                silueta.add(new XYRectangle(FigMax.width, r.height));
                // asigna a la figura mas grande r
                FigMax = r;
            }
            // si la anchura del r actual es la mayor de todas la asigna a xfin para el
            // punto final
            if (Xfin < r.width) {
                Xfin = r.width;
            }

            i++;

        } while (i < size);

        // agrega el punto final
        silueta.add(new XYRectangle(Xfin, 0));
        return silueta;

    }

    public static void main(String[] args) {
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(1, 3, 4));
        rectangles.add(new Rectangle(2, 4, 6));
        rectangles.add(new Rectangle(5, 9, 3));
        rectangles.add(new Rectangle(6, 8, 4));
        // prueba con 1000 rectangulos

        for (int j = 0; j < 1000; j++) {
            rectangles
                    .add(new Rectangle(j, (int) (Math.random() * (20 - 1) + 1), (int) (Math.random() * (20 - 1) + 1)));
        }

        long startTime = System.nanoTime();
        List<XYRectangle> silueta2 = calcularSilueta(rectangles);
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        // Imprimir la silueta
        for (XYRectangle r : silueta2) {
            System.out.println(r);
        }
        System.out.println("Con " + rectangles.size() + " rectangulos");
        System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + executionTime + " nanosegundos");
        System.out
                .println("Tiempo de ejecución de calcularSiluetaIterativo: " + (executionTime / 1e6) + " milisegundos");
        System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + (executionTime / 1e9) + " segundos");
    }

}
