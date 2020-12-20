package lesson2.word_count;

import lesson2.utils.DataHolder;

import java.util.*;

public class WordCounter {
    private final Collection<String> lines;

    public WordCounter(Collection<String> lines) {
        this.lines = lines;
    }

    /**
     * @return список всех файлов
     */
    public Collection<String> getWordList() {
        var wordList = new ArrayList<String>();
        lines.forEach(line -> Collections.addAll(wordList, DataHolder.splitByWords(line)));
        return wordList;
    }

    /**
     * @return количество различных слов
     */
    public Collection<String> getDistinctWords() {
        var wordList = getWordList();
        return new HashSet<>(wordList);
    }

    /**
     * @return считает количество различных слов
     */
    public int calculate() {
        return getDistinctWords().size();
    }

    /**
     * возвращает список различных слов файла, отсортированный по возрастанию их длины
     */
    public Collection<String> getSortedUniqueWords() {
        Set<String> words = new TreeSet<>((s, t1) -> {
            if (s.length() <= t1.length()) {
                return -1;
            } else
                return 1;
        });
        var dw = getDistinctWords();
        words.addAll(dw);
        return words;
    }

    /**
     * Подсчитайте и выведите на экран сколько раз каждое слово встречается
     */
    public Map<String, Integer> getWordsCount() {
        var distinctWords = getWordList();
        var wordDict = new HashMap<String, Integer>();
        distinctWords.forEach(word -> {
            if (wordDict.containsKey(word)) {
                wordDict.put(word, wordDict.get(word) + 1);
            } else {
                wordDict.put(word, 1);
            }
        });
        return wordDict;
    }

    /**
     * @return Все строки в обратном порядке.
     */
    public Collection<String> getReversedLines() {
        var rows = new ArrayList<String>();
        lines.forEach(s -> rows.add(reverseString(s)));
        return rows;
    }

    public Collection<String> filterLines(Set<Integer> lineNumbers) {
        var rowsFiltered = new ArrayList<String>();
        for (int number : lineNumbers) {
            if (number > 0 && number <= lines.size())
                rowsFiltered.add(((List<String>) lines).get(number - 1));
        }
        return rowsFiltered;
    }

    private String reverseString(String str) {
//        return new StringBuilder(str).reverse().toString();
        Stack<Character> stack = new Stack<>();
        for (Character character : str.toCharArray()) {
            stack.add(character);
        }
        StringBuilder stringBuffer = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.toString();
    }


    public Collection<String> getLines() {
        return lines;
    }
}
