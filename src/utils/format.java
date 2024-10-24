package utils;

public class format {
    /*
     * Remove espaços em branco duplicados e espaços em branco no início e no final
     * da string
     */
    public static String formatString(String str) {
        return str.trim().replaceAll("\\s+", " ");
    }

    /*
     * Formata um código para ter 4 dígitos
     */
    public static String formatCode(Integer code) {
        return String.format("%04d", code);
    }

}
