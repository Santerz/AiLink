import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ailink {
    private static final Graph graph = new Graph();

    public static void main(String[] args) {
        new Ailink().run();
    }

    public void run() {
        Scanner nm = new Scanner(System.in);
        System.out.println("spacebar x2 shows entire tree\n'remove'+(nodename) removes that node\n(nodename)+entr x2 shows all child nodes of that parent node\n(newparentnode)+entr+(newchildnode)+entr adds a new child node to a parent node");

        for (int i = 0; i < 1000; i++) {
            System.out.println("Enter target:");
            String link1 = nm.nextLine();

            if (link1.equals("exit")) {
                return;
            }
            Node targetNode = graph.findNode(link1);

            if (targetNode != null) {
                System.out.println("Target's children: " + targetNode.printChildren() + "\n");
            } else if (link1.contains("remove")) {
                graph.deleteNode(link1.substring(7));
            }

            if (!link1.contains("remove")) {
                System.out.println("Enter new value:");
                String link2 = nm.nextLine();
                if (!link2.isEmpty()) {
                    graph.addNode(link1, link2);
                }
                else if (link2.isEmpty() && link1.isEmpty()) {
                    System.out.println("");
                    graph.print();
                    System.out.println("");
                }
            }
        }
    }
}
