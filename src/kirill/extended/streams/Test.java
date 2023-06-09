package kirill.extended.streams;

import java.util.Arrays;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        int[] array = {3, 8, 1, 5, 9, 12, 4, 21, 81, 7, 18};
        // 1. Отфильтровать, чтобы остались только нечетные числа
        // 2. Поделим на 3 только те числа, которые делятся без остатка
        // 3. Найдем сумму оставшихся чисел
        int result = Arrays.stream(array)
                .filter(x -> x % 2 != 0)
                .map(x -> x % 3 == 0 ? x / 3 : x)
                .reduce((acc, x) -> acc + x)
                .getAsInt();
        System.out.println(result);
    }
}
