import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String entry = scanner.next();

        Pattern patternArabic = Pattern.compile("^(10|[1-9])(\\+|\\-|\\*|\\/)(10|[1-9])$");
        Pattern patternRoman = Pattern.compile("^(i|ii|iii|iv|v|vi|vii|viii|ix|x)(\\+|\\-|\\*|\\/)(i|ii|iii|iv|v|vi|vii|viii|ix|x)$", Pattern.CASE_INSENSITIVE);

        Matcher matcherArabic = patternArabic.matcher(entry);
        Matcher matcherRoman = patternRoman.matcher(entry);

        if (matcherArabic.matches()) {  // Введены арабские числа
            System.out.println("Введены арабские числа");
            if (entry.contains("+")) {  // Сложение
                String[] splitEntries = entry.split("\\+");
                int arabicResult = Integer.parseInt(splitEntries[0]) + Integer.parseInt(splitEntries[1]);
                System.out.println("Arabic result: " + arabicResult);
            } else if (entry.contains("-")) {   // Вычитание
                String[] splitEntries = entry.split("-");
                int arabicResult = Integer.parseInt(splitEntries[0]) - Integer.parseInt(splitEntries[1]);
                System.out.println("Arabic result: " + arabicResult);
            }
            else if (entry.contains("/")) {   // Деление
                String[] splitEntries = entry.split("/");
                int arabicResult = Integer.parseInt(splitEntries[0]) / Integer.parseInt(splitEntries[1]);
                System.out.println("Arabic result: " + arabicResult);
            } else {   // Умножение
                String[] splitEntries = entry.split("\\*");
                int arabicResult = Integer.parseInt(splitEntries[0]) * Integer.parseInt(splitEntries[1]);
                System.out.println("Arabic result: " + arabicResult);
            }
        } else if (matcherRoman.matches()) {    // Введены римские числа
            if (entry.contains("+")) {  // Сложение
                String[] splitEntries = entry.split("\\+");
                int romanResult = toArabic(splitEntries[0]) + toArabic(splitEntries[1]);
                System.out.println("Roman result: " + toRoman(romanResult));
            } else if (entry.contains("-")) {   // Вычитание
                String[] splitEntries = entry.split("-");
                int romanResult = toArabic(splitEntries[0]) - toArabic(splitEntries[1]);

                if (romanResult <= 0) {
                    System.out.println("Получено отрицательное либо нулевое значение");
                } else {
                    System.out.println("Roman result: " + toRoman(romanResult));
                }
            } else if (entry.contains("*")) {   // Умножение
                String[] splitEntries = entry.split("\\*");
                int romanResult = toArabic(splitEntries[0]) * toArabic(splitEntries[1]);
                System.out.println("Roman result: " + toRoman(romanResult));
            } else {    // Деление
                String[] splitEntries = entry.split("/");
                int romanResult = toArabic(splitEntries[0]) / toArabic(splitEntries[1]);
                if (romanResult < 1) {
                    System.out.println("Результат деления меньше единицы");
                } else {
                    System.out.println("Roman result: " + toRoman(romanResult));
                }
            }
        } else {

            throw new IOException("Неверный формат!");
        }

    }

    private static int toArabic (String roman) {    // Переводит римские числа в арабские
        int i = 0;
        int current = 0;
        int previous = 0;
        int arabic = 0;

        roman = roman.toUpperCase();

        while (i < roman.length()) {
            String letter = String.valueOf(roman.charAt(i));
            switch (letter) {
                case ("I") -> current = 1;
                case ("V") -> current = 5;
                case ("X") -> current = 10;
            }
            if (current > previous) {
                arabic += current - (previous * 2);
            } else {
                arabic += current;
            }
            previous = current;
            i++;
        }
        return arabic;
    }

    private static String toRoman(int arabic) {
        int units = arabic%10;
        int tens = (arabic%100)/10;
        int hundreds = (arabic%1000)/100;

        return hundreds(hundreds) + tens(tens) + units(units);
    }

    private static String units(int arabic) {
        return switch (arabic) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> "";
        };
    }

    private static String tens(int arabic) {
        return switch (arabic) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            default -> "";
        };
    }

    private static String hundreds(int arabic) {
        if (arabic == 1) {
            return "C";
        }
        return "";
    }
}
