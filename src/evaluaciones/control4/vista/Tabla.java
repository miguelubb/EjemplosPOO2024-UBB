package evaluaciones.control4.vista;

import java.util.*;

public class Tabla {
    private static char vBox = '|';
    private static char hBox = '-';
    private static char[] topBox = new char[]{'*', '*', '*', '-'};
    private static char[] midBox = new char[]{'|', '+', '|', '-'};
    private static char[] dwnBox = new char[]{'*', '*', '*', '-'};

    //estilos de cajas
    public static void setSimpleStyle() {
        vBox = '|';
        hBox = '-';
        topBox = new char[]{'*', '*', '*', '-'};
        midBox = new char[]{'|', '+', '|', '-'};
        dwnBox = new char[]{'*', '*', '*', '-'};
    }

    public static void setLineStyle() {
        vBox = '│';
        hBox = '─';
        topBox = new char[]{'┌', '┬', '┐', '─'};
        midBox = new char[]{'├', '┼', '┤', '─'};
        dwnBox = new char[]{'└', '┴', '┘', '─'};
    }

    public static void setDoubleStyle() {
        vBox = '║';
        hBox = '═';
        topBox = new char[]{'╔', '╦', '╗', '═'};
        midBox = new char[]{'╠', '╬', '╣', '═'};
        dwnBox = new char[]{'╚', '╩', '╝', '═'};
    }

    public static void setTopDoubleStyle() {
        vBox = '│';
        hBox = '═';
        topBox = new char[]{'╒', '╤', '╕', '═'};
        midBox = new char[]{'├', '┼', '┤', '─'};
        dwnBox = new char[]{'╘', '╧', '╛', '═'};
    }

    public static void noneStyle() {
        vBox = ' ';
        hBox = ' ';
        topBox = new char[]{' ', ' ', ' ', ' '};
        midBox = new char[]{' ', ' ', ' ', ' '};
        dwnBox = new char[]{' ', ' ', ' ', ' '};
    }

    //
    //imprime un título centrado a 80 caracteres subrayado, en
    //mayúscula y espaciado
    public static void printTitle(String titulo) {
        titulo = espaciarTexto(titulo.toUpperCase());
        System.out.println(alignText(titulo, 80, 'c'));
        System.out.println(alignText(genString(titulo.length(), hBox), 80, 'c'));
    }

    //calcula el ancho de la tabla resultante a partir del ancho de sus columnas.
    //para ello incluye los caracteres especiales para dibujar la tabla en el
    //cálculo final.
    public static int anchoTabla(int[] columnWidth) {
        int ancho = columnWidth.length + 1;
        for (int i = 0; i < columnWidth.length; i++) {
            ancho += columnWidth[i];
        }
        return ancho;
    }

    //pre:data[0].length==len.length==align.length
    //input:
    //  data:matriz de string con los datos de a desplegar
    //  len :arreglo con el ancho en número de caracteres de cada columna
    //  align: arreglo con caracteres de alineación para cada columna
    //  leftSpace: cantidad de espacio en blanco que queda por el lado izquierdo
    //    de la tabla.
    //process:
    //  se recorre la matriz de modo que se imprime cada dato, pero limitado
    //  al tamaño máximo definido para su columna.  En el caso de tener menos
    //  caracteres se rellenan con espacio para alcanzar el tamaño deseado.
    //  Esto es fundamental para lograr el aspecto de una tabla.
    //output:
    //  La salida de la función es puesta directamente a la salida estandar!.
    public static void printTable(String[][] data, int[] len, char[] align, int leftSpace) {
        //se incrementa en 2 el tamaño de cada columna, para que incluya el espacio anterior y posterior
        for (int i = 0; i < len.length; i++) {
            len[i] += 2;
        }
        String left = spaceString(leftSpace);
        System.out.println(left + hLine(topBox, len));
        for (int i = 0; i < data.length; i++) {
            System.out.print(left);
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(vBox + alignText(" " + data[i][j] + " ", len[j], align[j]));
            }
            System.out.println(vBox);
            if (i < data.length - 1) {
                System.out.println(left + hLine(midBox, len));
            } else {
                System.out.println(left + hLine(dwnBox, len));
            }
        }
    }

    public static void printTable(String[][] data, String[] column, int[] len, char[] align, int leftSpace) {
        //se agrega la columna como primera fila
        String[][] data2 = new String[data.length + 1][data[0].length];
        System.arraycopy(column, 0, data2[0], 0, column.length);
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, data2[i + 1], 0, data[0].length);
        }
        printTable(data2, len, align, leftSpace);
    }

    public static void printTableNumbers(String[][] data, String[] column, int[] len, char[] align, int leftSpace) {
        //se agrega la columna como primera fila
        String[][] data2 = new String[data.length + 1][data[0].length];
        System.arraycopy(column, 0, data2[0], 0, column.length);
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, data2[i + 1], 0, data[0].length);
        }
        printTableNumbers(data2, len, align, leftSpace, false, true);
    }

    //imprime la tabla pero con números de fila y columna
    public static void printTableNumbers(String[][] data, int[] len, char[] align, int leftSpace, boolean columNumber, boolean hasHeader) {
        leftSpace = (leftSpace == 0) ? (int) Math.log(data.length) + 2 : leftSpace;
        //se incrementa en 2 el tamaño de cada columna, para que incluya el espacio anterior y posterior
        for (int i = 0; i < len.length; i++) {
            len[i] += 2;
        }
        String left = spaceString(leftSpace);

        if (columNumber) {
            System.out.print(left + " ");
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(alignText("" + j, len[j], 'c') + " ");
            }
            System.out.println();
        }

        //línea superior4

        System.out.println(left + hLine(topBox, len));
        int d = 1;
        if (hasHeader) {
            d = 0;
        }
        for (int i = 0; i < data.length; i++) {
            if (!hasHeader || i != 0) {
                System.out.print(alignText((i + d) + " ", leftSpace, 'r'));
            } else {
                System.out.print(alignText(" ", leftSpace, 'r'));
            }
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(vBox + alignText(" " + data[i][j] + " ", len[j], align[j]));
            }
            System.out.println(vBox);
            if (i < data.length - 1) {
                System.out.println(left + hLine(midBox, len));
            } else {
                System.out.println(left + hLine(dwnBox, len));
            }
        }
    }

    //--------------------------------------------------------------------
    //Tabla tipo Excell
    //imprime la tabla pero con números de fila (desde 1) y Letras en columna
    public static void printTableLetersColumns(String[][] data, int[] len, char[] align, int leftSpace) {
        String left = spaceString(leftSpace);
        //Letras de columnas centradas
        System.out.print(left + " ");
        char colName;
        for (int j = 0; j < data[0].length; j++) {
            colName = (char) (j + (int) 'A');
            System.out.print(alignText("" + colName, len[j], align[j]) + " ");
        }
        System.out.println();
        //línea superior
        System.out.println(left + hLine(topBox, len));
        for (int i = 0; i < data.length; i++) {
            System.out.print(alignText((i + 1) + " ", leftSpace, 'r'));
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(vBox + alignText(data[i][j], len[j], align[j]));
            }
            System.out.println(vBox);
            if (i < data.length - 1) {
                System.out.println(left + hLine(midBox, len));
            } else {
                System.out.println(left + hLine(dwnBox, len));
            }
        }
    }

    //-----------------------------------------------------------------------------
    //Nombre función: hLine
    //Descripción: Esta función genera una línea horizontal, la cual será una línea
    //  superior, intermedia o inferior, dependiendo del conjunto de caracteres
    //  pasado como parámetro. Para construir la línea es necesario indicar el
    //  ancho de cada columna para poner el separador adecuado en cada caso.
    //Entrada:
    //  boxCharSet: es uno de los arreglos con caracteres especiales para dibujar
    //    el cuadrado.
    //  len: es un arreglo que contiene el ancho de cada columna.
    //Salida:
    //  String: un string que corresponde a la línea vertical que se ha formado.
    public static String hLine(char[] boxCharSet, int[] len) {
        StringBuilder line = new StringBuilder("" + boxCharSet[0]);
        for (int i = 0; i < len.length; i++) {
            for (int j = 0; j < len[i]; j++) {
                line.append(boxCharSet[3]);
            }
            if (i < len.length - 1) {
                line.append(boxCharSet[1]);
            } else {
                line.append(boxCharSet[2]);
            }
        }
        return line.toString();
    }

    //------------------------------------------------------------------------------
    //Nombre función: alignText
    //Descripción: alinea un texto dentro de un string a la derecha, izquierda o
    //  centrado dado un número máximo de caracteres.  Si el texto es mayor que el
    //  largo dado para el string resultante se trunca text.
    //Entrada:
    //  text: el texto a centrar
    //  len: largo del string resultante.
    //  align: caracter de alinea1ción que puede ser 'l':izquierda, 'r': derecha, 'c' centro
    //Salida:
    //  String: String con el texto alineado según align de largo máximo len.
    //
    public static String alignText(String text, int len, char align) {
        int diff = len - text.length();
        int halfDiff = diff / 2;
        int halfDiff1 = diff - halfDiff;
        String result = "";
        if (diff > 0) {
            result = switch (align) {
                case 'l' -> text + spaceString(diff);
                case 'r' -> spaceString(diff) + text;
                case 'c' -> spaceString(halfDiff1) + text + spaceString(halfDiff);
                default -> result;
            };
        } else {
            result = text.substring(0, len);
        }
        return result;
    }

    //Genera n caracteres c
    //Ejemplo: si n=5 y c='*' entonces s="*****"
    public static String genString(int n, char c) {
        char[] gen = new char[n];
        Arrays.fill(gen, c);
        return new String(gen);
    }

    //genera un string dado de largo n de caracteres en blanco
    //ejemplo: si n=5, s="     ";
    public static String spaceString(int n) {
        return genString(n, ' ');
    }

    //genera un nuevo texto s pero intercalando espacios en blanco
    //ejemplo: s="Hola", sOut="H o l a"
    public static String espaciarTexto(String s) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            out.append(s.charAt(i));
            out.append(' ');
        }
        out.deleteCharAt(out.length() - 1);
        return out.toString();
    }
}

