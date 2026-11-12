package readingroom;
public abstract class LendingItem {

    private static int counter = 1;

    private int id;
    private String title;
    private boolean onLoan;
    private Member currentBorrower;

    public LendingItem(String title) {
        this.id = counter++;
        this.title = title;
        this.onLoan = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public Member getCurrentBorrower() {
        return currentBorrower;
    }

    public void borrow(Member member) {
        
        if (onLoan) {
            throw new IllegalStateException("Item is already on loan");
        }

        this.onLoan = true;
        this.currentBorrower = member;
    }

    public void returnItem() {
        if (onLoan) {
            throw new IllegalStateException("This item has already been returned.");
        }
        this.onLoan = false;
        this.currentBorrower = null;
    }

    public String getItemType() {
        return getClass().getSimpleName();
    }

    public abstract int getLoanPeriod(Member member);
}
