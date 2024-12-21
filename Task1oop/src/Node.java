import java.util.HashMap;
import java.util.Map;

public class Node {
    public Map<Character, Node> children;
    public boolean isEndOfWord;

    public Node() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}