package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

//Алгоритм Хаффмана для компрессии данных
public class Task_11 {

    public static void main(String[] args) {
        String text = "where there's a will there's a way";

        TreeMap<Character, Integer> frequencies = countFrequency(text);

        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for (Character c : frequencies.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
        }

        CodeTreeNode tree = huffman(codeTreeNodes);

        TreeMap<Character, String> codes = new TreeMap<>();
        for (Character c : frequencies.keySet()) {
            codes.put(c, tree.getCodeForCharacters(c, ""));
        }
        System.out.println("Таблица префексных кодов: " + codes.toString());

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }
        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Биты сжатой строки: " + encoded);

        String decoded = huffmanDecode(encoded.toString(), tree);
        System.out.println("Расшифровано: " + decoded);

//        fileCompressTest();
    }

    //сколько раз символ встречается в строке
    private static TreeMap<Character, Integer> countFrequency(String text) {
        TreeMap<Character, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = freqMap.get(c);
            freqMap.put(c, count != null ? count + 1 : 1);
        }
        return freqMap;
    }

    private static CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            Collections.sort(codeTreeNodes);
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            CodeTreeNode parent = new CodeTreeNode(null, right.weight + left.weight, left, right);
            codeTreeNodes.add(parent);
        }
        return codeTreeNodes.get(0);
    }

    private static String huffmanDecode(String encoded, CodeTreeNode tree) {
        StringBuilder decoded = new StringBuilder();

        CodeTreeNode node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if (node.content != null) {
                decoded.append(node.content);
                node = tree;
            }
        }
        return decoded.toString();
    }

    private static class CodeTreeNode implements Comparable<CodeTreeNode> {

        Character content;
        int weight;
        CodeTreeNode left;
        CodeTreeNode right;

        public CodeTreeNode(Character content, int weight) {
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.content = content;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        public String getCodeForCharacters(Character ch, String parentPath) {
            if (content == ch) {
                return parentPath;
            } else {
                if (left != null) {
                    String path = left.getCodeForCharacters(ch, parentPath + 0);
                    if (path != null) {
                        return path;
                    }
                }
                if (right != null) {
                    String path = right.getCodeForCharacters(ch, parentPath + 1);
                    if (parentPath != null) {
                        return path;
                    }
                }
            }
            return null;
        }
    }

    // сохрняем тблицы частот и сжатой информации в файл
//    private static void saveToFile(File output, Map<Character, Integer> frequencies, String bits) {
//        try {
//            DataOutputStream os = new DataOutputStream(new FileOutputStream(output));
//            os.writeInt(frequencies.size());
//            for (Character character : frequencies.keySet()) {
//                os.writeChar(character);
//                os.writeInt(frequencies.get(character));
//            }
//            int compressedSizeBits = bits.length();
//            Task_11_1.BitArray bitArray = new Task_11_1.BitArray(compressedSizeBits);
//            for (int i = 0; i < bits.length(); i++) {
//                bitArray.set(i, bits.charAt(i) != '0' ? 1 : 0);
//            }
//
//            os.writeInt(compressedSizeBits);
//            os.write(bitArray.bytes, 0, bitArray.getSizeInBytes());
//            os.flush();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    // загрузка сжатой информации и таблицы частот из файла
//    private static void loadFromFile(File input, Map<Character, Integer> frequencies, StringBuilder bits) {
//
//        try {
//            DataInputStream os = new DataInputStream(new FileInputStream(input));
//            int frequencyTablesSize = os.readInt();
//            for (int i = 0; i < frequencyTablesSize; i++) {
//                frequencies.put(os.readChar(), os.readInt());
//            }
//            int dataSizeBits = os.readInt();
//            Task_11_1.BitArray bitArray = new Task_11_1.BitArray(dataSizeBits);
//            os.read(bitArray.bytes, 0, bitArray.getSizeInBytes());
//            os.close();
//            for (int i = 0; i < bits.length(); i++) {
//                bits.append(bitArray.get(i) != '0' ? 1 : 0);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //проверка алгоритма сжатия на тестовом файле
//    private static void fileCompressTest() {
//        try {
    //загрузка содержимого файла в виде строки
//            String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\GE60\\Downloads\\Java 8. Полное руководство. ( PDFDrive.com ).pdf")));
    //вычисление таблицы частот с которыми встречаются символы в тексте
//            TreeMap<Character, Integer> frequencies = countFrequency(content);
//            ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
// генераия листов будущего дерева для символов текста
//            for (Character c : frequencies.keySet()) {
//                codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
//            }
    //построение кодового дерева флгоритма Хаффмана
//            CodeTreeNode tree = huffman(codeTreeNodes);
//
    //построение таблицы префиксных кодов для символов исходного текста
//            TreeMap<Character, String> codes = new TreeMap<>();
//            for (Character c : frequencies.keySet()) {
//                codes.put(c, tree.getCodeForCharacters(c, ""));
//            }
    //кодирование текста префексными кодами
//            StringBuilder encoded = new StringBuilder();
//            for (int i = 0; i < content.length(); i++) {
//                encoded.append(codes.get(content.charAt(i)));
//            }
//
    //сохранение сжфтой информации из файла
//            File file = new File("C:\\Users\\GE60\\Downloads");
//            saveToFile(file, frequencies, encoded.toString());
//
//            TreeMap<Character, Integer> frequencies2 = new TreeMap<>();
//            StringBuilder encoded2 = new StringBuilder();
//            codeTreeNodes.clear();
//
    //извлечение сжатой информации из файла
//            loadFromFile(file, frequencies2, encoded2);
//
    //генерация листов и постоение кодового дерева Хаффмана на основе таблицы частот сжатого файла
//            for (Character c : frequencies2.keySet()) {
//                codeTreeNodes.add(new CodeTreeNode(c, frequencies2.get(c)));
//            }
//            CodeTreeNode tree2 = huffman(codeTreeNodes);
//
    // декодирование обратно исходной информации из сжатой
//            String decoded = huffmanDecode(encoded2.toString(), tree2);
//
    // сохранение в файл декодированной информации
//            Files.write(Paths.get("C:\\Users\\GE60\\Downloads\\decompressed.txt"), decoded.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
