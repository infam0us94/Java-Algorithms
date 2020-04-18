package com.company.Trees;

// Рекурсивный обход в глубину

public class Trees {

    public static void main(String args[]) {
        Tree mTree =
                new Tree(20,
                        new Tree(7,
                                new Tree(4, null, new Tree(6)), new Tree(9)),
                        new Tree(35, new Tree(31, new Tree(28), null), new Tree(40, new Tree(38), new Tree(52))));

        System.out.println("Сумма дерева: " + mTree.sum());

        System.out.println("Сумма дерева: " + sumWide(mTree));
    }

    static class Tree {

        int value;
        Tree left;
        Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Tree(int value) {
            this.value = value;
        }

        public int sum() {

            int sum = value;

            if (left != null) {
                sum += left.sum();
            }
            if (right != null) {
                sum += right.sum();
            }
            return sum;
        }
    }

    //Обход в ширину
    public static int sumWide(Tree root) {
        SimpleQueue<Tree> queue = new SimpleQueue<>();
        queue.add(root);

        int sum = 0;

        while (!queue.isEmpty()) {

            Tree node = queue.remove();

            System.out.println(node.value);

            sum = sum + node.value;

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return sum;
    }
}
