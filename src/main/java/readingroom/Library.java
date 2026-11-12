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

        if (item.isOnLoan()) {
            System.out.println("Item is already on loan");
            return;
        }

        int loanDays = item.getLoanPeriod(member);

        item.borrow(member);

        member.getHistory().add("Borrowed " + item.getTitle());

        loans.add(new Loan(member, item, loanDays));

        System.out.println(
            "Lent " + item.getTitle() + " to " + member.getName() + " for " + loanDays + " days."
        );
    }
}