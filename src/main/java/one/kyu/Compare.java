package one.kyu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Compare {

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(4));
        nodes.add(new Node(5));
        nodes.add(new Node(3));
        nodes.add(new Node(0));

        nodes.sort(Comparator.comparing(Node::getScore).reversed());
        for (Node node:nodes
             ) {
            System.out.println(node.getScore());
        }

    }

    public static class Node{

        public Node(Integer score){
            this.score = score;
        }

        private Integer score;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }
}
