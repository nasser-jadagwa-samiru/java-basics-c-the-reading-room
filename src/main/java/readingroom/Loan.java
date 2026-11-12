package readingroom;
public class Loan {

    private static int counter = 1;

    private int id;
    private Member member;
    private LendingItem item;
    private int loanDays;
    private LoanStatus status;

    public Loan(Member member, LendingItem item, int loanDays) {
        this.id = counter++;
        this.member = member;
        this.item = item;
        this.loanDays = loanDays;
        this.status = LoanStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public LendingItem getItem() {
        return item;
    }

    public int getLoanDays() {
        return loanDays;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void markReturned() {
        this.status = LoanStatus.RETURNED;
    }
}