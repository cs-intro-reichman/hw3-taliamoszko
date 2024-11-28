// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    // Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
    public static void main(String[] args) {        
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {
        double balance = loan;
        for (int i = 0; i < n; i++) {
            balance = (balance - payment) * (1 + rate/100);
        }
        return balance;
    }

    // Uses sequential search to compute an approximation of the periodical payment
    // that will bring the ending balance of a loan close to 0.
    // Given: the sum of the loan, the periodical interest rate (as a percentage),
    // the number of periods (n), and epsilon, the approximation's accuracy
    // Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
    
        double payment = loan / n;
    
        double incr = epsilon;
    
        while (true) {
            double balance = endBalance(loan, rate, n, payment);
    
            if (balance <= 0) {
                break; 
            }
    
            
            payment += incr;
    
            
            iterationCounter++;
        }
    
        
        return payment;
    }
    
    

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double lowBound = loan / n;
        double topBound = loan;
        double point;

        while (topBound - lowBound > epsilon) {
            point = (lowBound + topBound) / 2;
            double balance = endBalance(loan, rate, n, point);
            if (balance > epsilon) {
                lowBound = point;
            } else if (balance < -epsilon) {
                topBound = point;
            } else {
                return point;
            }
            iterationCounter++;
        }
        return (lowBound + topBound) / 2;
    }
}