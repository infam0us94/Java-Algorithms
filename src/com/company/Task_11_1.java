package com.company;



public class Task_11_1 {

    public static void main(String[] args) {

        int i = 0b00000100;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        System.out.println("---побитовый сдвиг вправо---");
        System.out.println("в десятичном виде: " + (i >> 1));
        System.out.println("в двоичном виде: " + binaryStr(i >> 1));

        System.out.println("---побитовый сдвиг влево---");
        System.out.println("в десятичном виде: " + (i << 1));
        System.out.println("в двоичном виде: " + binaryStr(i << 1));

        BitArray bitArray = new BitArray(100);
        bitArray.set(0, 1);
        bitArray.set(9, 1);
        bitArray.set(5, 1);
        bitArray.set(5, 0);

        System.out.println(bitArray.toString());
    }

    public static String binaryStr(int b) {
        return "0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1);
    }

    public static class BitArray {
        int size;
        byte[] bytes;

        private byte[] masks = new byte[]{0b00000001, 0b00000010, 0b00000100, 0b00001000,
                0b00010000, 0b000100000, 0b01000000, (byte) 0b10000000};

        public BitArray(int size) {
            this.size = size;
            int sizeInBytes = size / 8;
            if (size % 8 > 0) {
                sizeInBytes = sizeInBytes + 1;
            }
            bytes = new byte[sizeInBytes];
        }

        public int get(int index) {
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            return (bytes[byteIndex] & masks[bitIndex]) > 0 ? 1 : 0;
        }

        public void set(int index, int value) {
            int byteIndex = index / 8;
            int bitIndex = index % 8;
            if (value > 0) {
                bytes[byteIndex] = (byte) (bytes[byteIndex] | masks[bitIndex]);
            } else {
                bytes[byteIndex] = (byte) (bytes[byteIndex] & ~masks[bitIndex]);
            }
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(get(i) > 0 ? '1' : '0');
            }
            return sb.toString();
        }

        public int getSize() {
            return size;
        }

        public byte[] getBytes() {
            return bytes;
        }
        public int getSizeInBytes() {
            return bytes.length;
        }
    }
}
