import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

public class Node implements Serializable {
    private static final long serialVersionUID = 4040426144898426467L;

    private Node parent;
    private List<Node> children = new ArrayList<>();
    String value;

    public Node(String value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean addNode(Node node) {
        node.setParent(this);
        return children.add(node);
    }

    public boolean deleteNode(Node node) {
        return children.remove(node);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String printChildren() {
        return children.stream().map(Node::getValue).collect(Collectors.joining(","));
    }

//    private void writeObject(java.io.ObjectOutputStream s)
//            throws java.io.IOException {
//
//        s.writeObject(parent);
//        s.writeObject(value);
//        if (children != null) {
//            s.writeInt(children.size());
//            for (Node child : children) {
//                s.writeObject(child);
//            }
//        } else {
//            s.writeInt(0);
//        }
//    }
//
//    private void readObject(java.io.ObjectInputStream s)
//            throws java.io.IOException, ClassNotFoundException {
//
////        s.defaultReadObject();
//        parent = (Node) s.readObject();
//        value = (String) s.readObject();
//        int childrenCount = s.readInt();
//        for (int i = 0; i < childrenCount; i++) {
//            children.add((Node) s.readObject());
//        }
//    }
}
