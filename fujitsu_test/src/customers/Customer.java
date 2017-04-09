package customers;


/**
 * Created by User on 04.04.2017.
 */
public class Customer {
    private int bonusPoints;
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
