import java.util.Scanner;
import java.util.Stack;
public class PersistentArray {

    public static Stack<Head> headStack = new Stack<>();

    public static class Head {
        Node root;
        int val;
        public Head(Node root, int val) {
            this.root = root;
            this.val = val;
        }
    }

    public static class Node {
        Node left;
        Node right;
        int val;

        public Node() {}
        
        public Node (int val) {
            this.val = val;
        }
    } 

    public PersistentArray(Stack<Head> headStack) {
        this.headStack = headStack;
    }

    public static PersistentArray newarray() {
        PersistentArray array = new PersistentArray(headStack);
        Head head = new Head(new Node(), 0);
        headStack.push(head);
        return array;
    }

    public PersistentArray set(PersistentArray a, int i, int value) {
        Head currentHead = a.headStack.peek();
        Node newNode = new Node();

        Head newHead = new Head(newNode, currentHead.val);
        Node currentNode = currentHead.root;
        Node insertNode = new Node(value);

        for (int b = 30; b >= 0; b--) {
            if (((i >> b) & 1) == 1) {
                newNode.left = (currentNode.left != null) ? currentNode.left : null;
                newNode.right = new Node();
                currentNode = (currentNode.right != null) ? currentNode.right : null;
            } else {
                newNode.left = new Node();
                newNode.right = (currentNode.right != null) ? currentNode.right : null;
                currentNode = (currentNode.left != null) ? currentNode.left : null;
            }
        }

        if (currentNode.val == 0) {
            newHead.val += 1;
        }

        currentNode = insertNode;
        headStack.push(newHead);
        return a;
    }

    public static int get(PersistentArray a, int i) {
        Head currentHead = a.headStack.peek();
        Node currentNode = currentHead.root;
        for (int b = 30; b >= 0; b--) {
            if (((i >> b) & 1) == 1) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return (currentNode != null) ? currentNode.val : 0;
    }

    public static void main(String[] args) {

        PersistentArray array = newarray();

        Scanner myObj = new Scanner(System.in);
        while (true) {
            String [] str = myObj.nextLine().split(" ");
            if(str[0].equals("set")) {
                array.set(array, Integer.parseInt(str[1]), Integer.parseInt(str[2]));
            } else if (str[0].equals("get")) {
                System.out.println(array.get(array, Integer.parseInt(str[1])));
            } else if (str[0].equals("unset")) {
                if (!array.headStack.empty()) {
                    array.headStack.pop();
                }
            } else {
                System.out.println("Wrong format");
            }
        }
    }
}