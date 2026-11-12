package readingroom;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;
    private List<LendingItem> items;
    private List<Member> members;
    private List<Loan> loans;

    public Library(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addItem(LendingItem item) {
        items.add(item);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void borrow(Member member, LendingItem item) {
        if (member == null || item == null) {
            throw new IllegalArgumentException("Member and item cannot be null");
}
        
        try {
            item.borrow(member);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        int loanDays = item.getLoanPeriod(member);

        member.getHistory().add("Borrowed " + item.getTitle());

        loans.add(new Loan(member, item, loanDays));

        System.out.println(
            "Lent \"" + item.getTitle() + "\" to " + member.getName() + " for " + loanDays + " days"
        );
    }

    public void returnItem(LendingItem item) {
         if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        try {
            item.returnItem();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }


        for (Loan loan : loans) {

            if (loan.getItem() == item
                    && loan.getStatus() == LoanStatus.ACTIVE) {

                loan.markReturned();
            }
        }

        System.out.println("Returned " + item.getTitle() + ".");
    }

    public void printReport() {

        System.out.println();
        System.out.println("=== " + name + " - end of day ===");

        System.out.println("Items:");

        for (LendingItem item : items) {

            System.out.println(
                    "  \"" + item.getTitle()
                            + "\" (" + item.getItemType() + "): "
                            + (item.isOnLoan() ? "ON_LOAN" : "AVAILABLE")
                            + (item.getCurrentBorrower() != null
                                    ? " - with "
                                            + item.getCurrentBorrower().getName()
                                    : ""));
        }

        System.out.println("Active loans:");

        for (Loan loan : loans) {

            if (loan.getStatus() == LoanStatus.ACTIVE) {

                System.out.println(
                        "  Loan #"
                                + loan.getId()
                                + ": \""
                                + loan.getItem().getTitle()
                                + "\" -> "
                                + loan.getMember().getName());
            }
        }
    }
}