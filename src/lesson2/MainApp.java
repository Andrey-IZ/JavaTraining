package lesson2;

import lesson2.word_count.WordCounter;
import lesson2.cars_grouping.Car;
import lesson2.cars_grouping.CarInitializing;
import lesson2.iterators.ReverseListIterator;
import lesson2.utils.DataHolder;

import java.io.IOException;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        cars_grouping();
        Collection<String> lines;
        try {
            lines = DataHolder.loadFromFile("text.txt");
        } catch (IOException e) {
            System.out.println("файл не найден: " + e.getMessage());
            return;
        }
        var wordCounter = new WordCounter(lines);
        task1(wordCounter.calculate());
        task2(wordCounter.getSortedUniqueWords());
        task3(wordCounter.getWordsCount());
        task4(wordCounter.getReversedLines());
        task5(wordCounter.getLines());
        task6(wordCounter);
    }

    /**
     * задача
     * Имеется список парка машин Car(String model, String type). Необходимо разбить его на списки сгруппированные по type.
     * Пример исходного списка: Лада седан, Лада хэтчбек, Мерседес седан, Бмв кроссовер,  Форд хэтчбек, Пежо кроссовер, Тойота седан и т.п.
     */
    private static void cars_grouping() {
        var cars = new ArrayList<Car>();
        CarInitializing.init(cars);

//        var hm = cars.stream().collect(Collectors.groupingBy(Car::getType));
//        System.out.println(hm);

        HashMap<String, List<Car>> map = new HashMap<>();
        for (Car car : cars) {
            var key = car.getType();
            if (map.containsKey(key)) {
                var list = map.get(key);
                list.add(car);
                map.put(key, list);
            } else {
                var list = new ArrayList<Car>();
                list.add(car);
                map.put(key, list);
            }
        }
        System.out.println(map);
    }

    /**
     * Задание 1: Подсчитайте количество различных слов в файле.
     */
    private static void task1(int wordAmount) {
        System.out.println("количество различных слов: " + wordAmount);
    }

    /**
     * Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины
     * (компаратор сначала по длине слова, потом по тексту).
     */
    private static void task2(Collection<String> words) {
        System.out.println("список различных слов файла, отсортированный по возрастанию их длины: " + words);
    }

    /**
     * Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
     */
    private static void task3(Map<String, Integer> wordDict) {
        System.out.println("каждое слово встречается в файле: " + wordDict);
    }

    /**
     * Задание 4: Выведите на экран все строки файла в обратном порядке.
     */
    private static void task4(Collection<String> lines) {
        System.out.println("все строки файла в обратном порядке: \n" + lines);
    }

    /**
     * Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
     */
    private static void task5(Collection<String> lines) {
        System.out.println("обход списка в обратном порядке:\n");
        for (String str : new ReverseListIterator<>((List<String>) lines)) {
            System.out.println(str);
        }
    }

    /**
     * Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
     */
    private static void task6(WordCounter wordCounter) {
        System.out.println("Введите номера строк:");
        Scanner in = new Scanner(System.in);
        String number;
        var numbers = new HashSet<Integer>();
        while (!(number = in.nextLine()).isEmpty()) {
            try {
                numbers.add(Integer.valueOf(number));
                System.out.println("Вы ввели: " + number);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число:" + number + ". Попробуйте ещё раз");
            }
        }
        System.out.println("--- all lines you've entered ---");
        wordCounter.filterLines(numbers).forEach(System.out::println);
    }
}
