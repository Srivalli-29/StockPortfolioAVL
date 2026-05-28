class Node {

    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

public class StockPortfolioAVL {

    Node root;

    // Height of node
    int height(Node N) {

        if (N == null)
            return 0;

        return N.height;
    }

    // Maximum of two numbers
    int max(int a, int b) {

        return (a > b) ? a : b;
    }

    // Right Rotation
    Node rightRotate(Node y) {

        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;

        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    Node leftRotate(Node x) {

        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;

        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Get Balance Factor
    int getBalance(Node N) {

        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    // Insert Node
    Node insert(Node node, int key) {

        // Normal BST insertion
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);

        else if (key > node.key)
            node.right = insert(node.right, key);

        else
            return node;

        // Update height
        node.height = 1 + max(height(node.left),
                              height(node.right));

        // Get balance factor
        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && key > node.left.key) {

            node.left = leftRotate(node.left);

            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && key < node.right.key) {

            node.right = rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    // Inorder Traversal
    void inorder(Node node) {

        if (node != null) {

            inorder(node.left);

            System.out.print(node.key + " ");

            inorder(node.right);
        }
    }

    // Search Operation
    boolean search(Node node, int key) {

        if (node == null)
            return false;

        if (node.key == key)
            return true;

        if (key < node.key)
            return search(node.left, key);

        return search(node.right, key);
    }

    // Main Method
    public static void main(String[] args) {

        StockPortfolioAVL tree = new StockPortfolioAVL();

        int stockPrices[] = {
                450, 320, 780, 620,
                510, 890, 430, 700
        };

        // Insert stock prices
        for (int price : stockPrices) {

            tree.root = tree.insert(tree.root, price);
        }

        // Display AVL Tree
        System.out.println("Inorder Traversal of AVL Tree:");

        tree.inorder(tree.root);

        // Search stock price
        int searchKey = 620;

        if (tree.search(tree.root, searchKey))

            System.out.println("\n\nStock Price "
                    + searchKey + " Found");

        else

            System.out.println("\nStock Price Not Found");
    }
}