package $package;

public class CreditCard {
    
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private int cvv;
    private double creditLimit;
    private double balance;

    public CreditCard(String cardNumber, String cardHolderName, String expirationDate, int cvv, double creditLimit) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.creditLimit = creditLimit;
        this.balance = 0.0;
    }

    public boolean charge(double amount) {
        if (amount <= 0 || amount > (creditLimit - balance)) {
            return false;
        }

        balance += amount;
        return true;
    }

    public boolean makePayment(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    
    public static void main(String[] args) {
        // Ejemplo de Uso (Generado por Chatgpt)
        CreditCard card = new CreditCard("0123456701234567", "Abril Sueños", "07/24", 012, 10000.0);
        card.charge(500.0);
        System.out.println("Balance: " + card.getBalance()); // Output: 500.0
        card.makePayment(200.0);
        System.out.println("Balance: " + card.getBalance()); // Output: 300.0
    }
}