import java.io.*;

public class Graph {
    public static final String AILINK_DATA = "ailink.data";
    Node root = new Node("ROOT");

    public Graph() {
        load();
    }

    public void addNode(String target, String value) {
        Node targetNode = findNode(target);
        if (targetNode == null) {
            Node newTarget = new Node(target);
            root.addNode(newTarget);
            newTarget.addNode(new Node(value));
        } else {
            targetNode.addNode(new Node(value));
        }
        store();
    }

    public boolean deleteNode(String value) {
        Node targetNode = findNode(value);
        if (targetNode == null) {
            return false;
        }
        Node parentNode = targetNode.getParent();
        boolean result = parentNode.deleteNode(targetNode);
        store();
        return result;
    }

    public Node findNode(String value) {
        return findNode(root, value);
    }

    private Node findNode(Node node, String value) {
        if (node == root && !node.hasChildren()) {
            Node newNode = new Node(value);
            node.addNode(newNode);
            return newNode;
        }

        if (node.getValue().equals(value)) {
            return node;
        }

        if (node.hasChildren()) {
            for (Node childNode : node.getChildren()) {
                Node foundNode = findNode(childNode, value);
                if (foundNode != null) {
                    return foundNode;
                }
            }
        }
        return null;
    }

    public void print() { //
        print(root, 0);
    }

    private void print(Node node, int tab) {
        for (int i = 0; i < tab; i++) {
            System.out.print(' ');
        }
        System.out.println(node.getValue());
        for (Node childNode : node.getChildren()) {
            print(childNode, tab + 2);
        }
    }

    private void store() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AILINK_DATA))) {
            oos.writeObject(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AILINK_DATA))) {
            root = (Node) ois.readObject();
        } catch (FileNotFoundException fnf) {
            // expected at first run
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
