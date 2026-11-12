package readingroom;
public class Book extends LendingItem {

    public Book(String title) {
        super(title);
    }

    @Override
    public int getLoanPeriod(Member member) {
        return member.getType() == MemberType.STUDENT ? 28 : 14;
    }
}