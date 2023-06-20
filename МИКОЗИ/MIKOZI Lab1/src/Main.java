import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] alphabet = new String[] {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к",
        "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};
        //                  абвгдеёжзийклмнопрстуфхцчшщъыьэюя
        String [] cipher = "ьиузроыэбхгштцнёкжплвщчймюсяъдфае".split("");
        String[] text = "криптография".split("");
        List<String> alphabet2 = Arrays.asList(alphabet);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length; i++) {
            sb.append(cipher[alphabet2.indexOf(text[i])]);
        }
        String cipherText = sb.toString();
        System.out.println("Шифровка простой заменой: " + cipherText);

        String[] cipherText2 = "ьюинлцщё".split("");
        sb.delete(0, sb.length());
        int a = 8, b = 29, m = 33;
        for(int i = 0; i < cipherText2.length; i++) {
            int y = (a * (alphabet2.indexOf(cipherText2[i]) + m - b)) % m;
            sb.append(alphabet2.get(y));
        }
        System.out.println("Дешифровка аффинного шифра: " + sb.toString());
    }
}