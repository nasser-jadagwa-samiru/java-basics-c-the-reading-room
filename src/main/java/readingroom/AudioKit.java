package readingroom;
public class AudioKit extends LendingItem {

    public AudioKit(String title) {
        super(title);
    }

    @Override
    public int getLoanPeriod(Member member) {
        return member.getType() == MemberType.STUDENT ? 14 : 7;
    }
}
