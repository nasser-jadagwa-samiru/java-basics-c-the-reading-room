package readingroom;
import java.util.ArrayList;
import java.util.List;

public class Member {

    private static int counter = 1;

    private int id;
    private String name;
    private MemberType type;
    private List<String> history = new ArrayList<>();

    public Member(String name, MemberType type) {
        this.id = counter++;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MemberType getType() {
        return type;
    }

    public List<String> getHistory() {
        return history;
    }
}
