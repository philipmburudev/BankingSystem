package BankingSystem;


/** 
 * This class represents a bank account.  The account may be a current account or
 * a savings account.  Current accounts must maintain a minimum balance of GHC 6.00,
 * while savings accounts must maintain a minimum balance of 30.00.
 * Current accounts have a monthly maintenance fee of GHC 2.00, whereas savings
 * accounts have no monthly maintenance fee.  Savings accounts have an interest rate
 * of 6%, but current accounts have no interest.  Lastly, withdrawals from savings
 * accounts may only be be made 2 times a month, whereas there is no limit
 * on withdrawals from current accounts.
 */
public class BankAccount
{
  // Defined enumeration specifying the valid types of accounts
  public enum AccountType {CURRENT, SAVINGS}

  private AccountType acctType;   // The type of account (CURRENT or SAVINGS)
  private String acctID;          // The account's identifier
  
  private double balance;         // The current balance on the account
  private int numWithdrawals;     // The number of withdrawals that have been made in the current month.
  private boolean inTheRed;       // Whether or not this account is "in the red"
                                  // (i.e. whether its balance is less than the
                                  //  minimum required balance.)
 
  private double minBalance;      // This account's minimum balance requirement
  private double interestRate;    // This account's annual interest rate
  private double maintenanceFee;  // This account's maintenance fee
  private int withdrawalLimit;    // The maximum number of withdrawals per month.


  // Defined constants
  public static final int MONTHS_PER_YEAR = 12;
  public static final double SAVINGS_ACCT_MIN_BALANCE = 30.0;
  public static final double CURRENT_ACCT_MIN_BALANCE = 6.00;
  public static final double SAVINGS_ACCT_INTEREST_RATE = 0.06;
  public static final double CURRENT_ACCT_MAINTENANCE_FEE = 2.00;
  public static final int SAVINGS_WITHDRAWAL_LIMIT = 2;
 


  //A Constructor, BankAccount, that takes the account type and id
  public BankAccount(AccountType type, String id){
    acctType = type;
    acctID = id;
    balance = 0;
    numWithdrawals = 0;
    
    if (acctType == AccountType.CURRENT){
      minBalance = CURRENT_ACCT_MIN_BALANCE;
      interestRate = 0;
      maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
      withdrawalLimit = -1;
    }
    else {
      minBalance = SAVINGS_ACCT_MIN_BALANCE;
      interestRate = SAVINGS_ACCT_INTEREST_RATE;
      maintenanceFee = 0;
      withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
    }
    
    inTheRed = (balance < minBalance); // account is in the red when the balance is less than the minimum balance
  }
  


  //Constructor that takes the account type, id, and opening balance
  public BankAccount(AccountType type, String id, double openingBalance){    //method overloading - we have two methods
                                                                             //with the same name, BankAccount, but taking different number of parameters.  
    acctType = type;
    acctID = id;
    balance = openingBalance;
    numWithdrawals = 0;
    
    if (acctType == AccountType.CURRENT){
      minBalance = CURRENT_ACCT_MIN_BALANCE;
      interestRate = 0;
      maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
      withdrawalLimit = -1;
    }
    else {
      minBalance = SAVINGS_ACCT_MIN_BALANCE;
      interestRate = SAVINGS_ACCT_INTEREST_RATE;
      maintenanceFee = 0;
      withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
    }
    
    inTheRed = (balance < minBalance); //account is in the red when the balance is less than the minimum balance
  }



  //getBalance() method
  public double getBalance(){
    return balance;
  }
  

  //getAccountType() method
  public AccountType getAccountType(){
    return acctType;
  }


  //getAccountID() method 
  public String getAccountID(){
    return acctID;
  }


  //getMinBalance() method
  public double getMinBalance(){
    return minBalance;
  }



  //deposit() method
  public void deposit(double amount){
    balance += amount;
    System.out.println("Congratulations! You successfully deposited $" + amount + " into your account.");
    System.out.println("Your current balance is $" + balance);
  }
  



  //withdraw() method
  //This method tries to deduct the specified amount from the account balance and increments the number of withdrawals. 
  //If the transaction is successful, it returns true.  Otherwise, it returns false. 
  //The transaction will be unsuccessful if the number of allowed withdrawals has been exceeded, 
  //the bank account is “in the red” or the bank account does not have a sufficient balance (i.e. removing the 
  //amount would leave less than the required minimum balance in the account).
  //If the transaction is not successful, the method should print out an explanation before it returns.  E.g.:
  //Sorry, could not perform withdrawal: Insufficient balance.
  //Remember that when there is no withdrawal limit, the withdrawalLimit member variable is set to -1, so your
  //code must check for this case and behave appropriately.

  public boolean withdraw(double amount){
    if (withdrawalLimit == -1){
     if (balance - amount >= minBalance){
        balance -= amount;
        numWithdrawals++;
        System.out.println("Congratulations! You successfully withdrew $" + amount + " from your account.");
        System.out.println("Your current balance is $" + balance);
        return true;
      }
      else{
        System.out.println("Sorry, could not perform withdrawal. You have insufficient balance.");
        return false;
      }
    }

    else{
      if (numWithdrawals < withdrawalLimit){
        if (balance - amount >= minBalance){
          balance -= amount;
          numWithdrawals++;
          System.out.println("Congratulations! You successfully withdrew $" + amount + " from your account.");
          System.out.println("Your current balance is $" + balance);
          return true;
        }
        else{
          System.out.println("Sorry, could not perform withdrawal. You have insufficient balance.");
          return false;
        }
      }

      else{
        System.out.println("Sorry, could not perform withdrawal: Withdrawal limit exceeded. Try again next month.");
        return false;
      }
    }
  }
  




  //performMonthlyMaintenance() method
  // This method performs monthly maintenance on the account.
  // It computes any interest earned on the account (given the interest rate on the account) and adds the earned interest to the current balance.
  // Note that the interestRate member variable specifies the annual interest rate on the account.  As such, it will need to be divided by the 
  //number of months in the year in order to compute the interest for one month.
  // This method also deducts the account’s monthly maintenance fee (specified by the maintenanceFee  member variable) from the current account 
  //balance.  After subtracting the maintenance fee, the method determines whether or not the account is in the red (i.e. if the account balance 
  //is less than the minimum balance).  The method resets the number of withdrawals for the current month to 0.

  public void performMonthlyMaintenance(){
    balance += balance * (interestRate / MONTHS_PER_YEAR);
    balance -= maintenanceFee;
    if (balance < minBalance){     //determine if account is in the red 
      System.out.println("WARNING! This account is in the red!");
      } 
      
      else {
      inTheRed = false;
    }
    numWithdrawals = 0;  //reset number of withdrawals to 0

    System.out.println("Earned interest: " + (interestRate / MONTHS_PER_YEAR) + "\n" + "Maintenance fee: " + maintenanceFee + 
      "\n" + "Updated balance: " + balance);
  }

    
  


  //transfer() method
  //This method performs a transfer between this bank account and another bank account (specified by the input parameter otherAccount) 
  // and returns whether or not the transaction was successful. The amount being transferred is specified by the input parameter amount. 
  // If the input parameter transferTo is true, the amount is withdrawn from this bank account and deposited into the other bank account.  
  // On the other hand, if the input parameter transferTo is false, the amount is withdrawn from the other bank account and deposited
  // into this bank account. Note that you should implement this method by making calls to the previously defined withdraw() and deposit()
  //  methods.  If the withdrawal from the account the money is coming from is not successful (e.g. due to insufficient balance or exceeding
  // the withdrawal limit), then no transfer is performed and the method returns false.
  // Otherwise, the transfer is successful and the method returns false.

  public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount){
    if (transferTo == true){
      if (balance - amount >= minBalance){
        balance -= amount;
        otherAccount.balance += amount;
        System.out.println("Congratulations! You successfully transferred $" + amount + " to " + otherAccount.acctID + " account.");
        System.out.println("Your current balance is $" + balance);
        return true;
      }
      else{
        System.out.println("Sorry, could not perform transfer: Insufficient balance.");
        return false;
      }
    }
    else{
      if (otherAccount.balance - amount >= otherAccount.minBalance){
        otherAccount.balance -= amount;
        System.out.println("Your current balance is $" + balance);
        return true;
      }
      else{
        System.out.println("Sorry, could not perform transfer: Insufficient balance.");
        return false;
      }
    }

  }



  //call the transfer method 



}

