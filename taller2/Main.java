package taller2;

import java.util.ArrayList;
import java.util.Arrays;
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
            contieneX = 1;
        }
        if (r.x < maxRec.width) {

            if (r.height <= maxRec.height) {
                contieneY = 1;
            }
        }
        return new XYRectangle(contieneX, contieneY);

    }

    public static List<XYRectangle> calcularSilueta2(List<Rectangle> rectangles) {

        Rectangle FigMax = new Rectangle(0, 0, 0);
        List<XYRectangle> silueta = new ArrayList<>();
        int Xfin = 0;
        int y = 0;
        int size = rectangles.size();

        System.err.println(size);

        int i = 0;
        do {

            // for (int i = 0; i < size; i++) {
            Rectangle r = rectangles.get(i);
            XYRectangle G = (contienen(r, FigMax));
            Rectangle r2 = rectangles.get(i);

            if (i + 1 < rectangles.size()) {
                r2 = rectangles.get(i + 1);
            }

            if (G.X == 0 && G.Y == 0) {

                silueta.add(new XYRectangle(r.x, r.height));
                if (r2.x > r.width) {
                    silueta.add(new XYRectangle(r.width, y));
                } else if (r2 == null) {
                    silueta.add(new XYRectangle(r.width, 0));
                }

                FigMax = r;
            }

            if (G.X == 1 && G.Y == 0) {
                silueta.add(new XYRectangle(r.x, r.height));
                silueta.add(new XYRectangle(r.width, FigMax.height));
            }
            if (G.X == 0 && G.Y == 1) {
                silueta.add(new XYRectangle(FigMax.width, r.height));
                FigMax = r;
            }
            if (Xfin < r.width) {
                Xfin = r.width;
            }
           
            i++;

        } while (i < size);

        silueta.add(new XYRectangle(Xfin, 0));
        return silueta;

    }

  public static void main(String[] args) {
      List<Rectangle> rectangles = new ArrayList<>();
      rectangles.add(new Rectangle(1, 3, 4));
      rectangles.add(new Rectangle(2, 4, 6));
      rectangles.add(new Rectangle(5, 9, 3));
      rectangles.add(new Rectangle(6, 8, 4));
  //prueba con 1000 rectangulos

  /*    for (int j = 0; j < 1000; j++) {
          rectangles.add(new Rectangle(j, (int) (Math.random() * (20 - 1) + 1), (int) (Math.random() * (20 - 1) + 1)));
      }
  */
      long startTime = System.nanoTime();
      List<XYRectangle> silueta2 = calcularSilueta2(rectangles);
      long endTime = System.nanoTime();
  
      long executionTime = endTime - startTime;
  
  
      // Imprimir la silueta
      for (XYRectangle r : silueta2) {
          System.out.println(r);
      }
      System.out.println("Con " + rectangles.size() + " rectangulos");
      System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + executionTime + " nanosegundos");
      System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + (executionTime / 1e6) + " milisegundos");
      System.out.println("Tiempo de ejecución de calcularSiluetaIterativo: " + (executionTime / 1e9) + " segundos");
  }

}
  