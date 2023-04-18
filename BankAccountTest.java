
/**
 * This class tests the BankAccount class.
 * You will need to add to it incrementally as you implement
 * the methods of the BankAccount class.
 */
public class BankAccountTest{
    public static void main(final String[] args) {
      BankAccount myCurrentAccount, mySavingsAccount, yourCurrentAccount, otherAccount;
      
      myCurrentAccount = new BankAccount(BankAccount.AccountType.CURRENT,
                                         "003456CURRENT");
      mySavingsAccount = new BankAccount(BankAccount.AccountType.SAVINGS,
                                         "003456SAVINGS");
      yourCurrentAccount = new BankAccount(BankAccount.AccountType.CURRENT,
                                         "003457CURRENT");
      otherAccount = new BankAccount(BankAccount.AccountType.CURRENT,
                                         "003454CURRENT");
      
  
          
      //call the getAccountType(), getBalance(), getAccountID(), and getMinBalance() methods for each account
      //details of the current account
      System.out.println("Account Type: " + myCurrentAccount.getAccountType() + "\n" + "Balance: " + myCurrentAccount.getBalance() + 
      "\n" + "Account ID: " + myCurrentAccount.getAccountID() + "\n" + "Minimum Balance: " + myCurrentAccount.getMinBalance());
  
      System.out.println();
  
      //details of the savings account
      System.out.println("Account Type: " + mySavingsAccount.getAccountType() + "\n" + "Balance: " + mySavingsAccount.getBalance() +
      "\n" + "Account ID: " + mySavingsAccount.getAccountID() + "\n" + "Minimum Balance: " + mySavingsAccount.getMinBalance());
  
      System.out.println();
  
  
  
    //CURRENT ACCOUNT
      //Depositing into Current account by calling the deposit() method. 
      myCurrentAccount.deposit(1000);
  
      System.out.println();
       
      //call the withdraw() method
      myCurrentAccount.withdraw(100.00);
  
      System.out.println();
  
      //call the performMonthlyMaintenance() method
      System.out.println("Current Account Monthly Report:");
      myCurrentAccount.performMonthlyMaintenance();
  
      System.out.println();
  
  
  
  
   //SAVINGS ACCOUNT
      //Depositing into Current account by calling the deposit() method. 
      mySavingsAccount.deposit(2000);
  
      System.out.println();
        
      //call the withdraw() method
      mySavingsAccount.withdraw(200.00);
  
      System.out.println();
  
      //call the performMonthlyMaintenance() method
      System.out.println("Savings Account Monthly Report:");
      mySavingsAccount.performMonthlyMaintenance();
  
      System.out.println();
  
      
  
  
  
   //YOUR CURRENT ACCOUNT
      //Depositing into Current account by calling the deposit() method. 
      yourCurrentAccount.deposit(500);
  
      System.out.println();
        
      //call the withdraw() method
      yourCurrentAccount.withdraw(50.00);
  
      System.out.println();
  
      //call the performMonthlyMaintenance() method
      System.out.println("Your Current Account Monthly Report:");
      yourCurrentAccount.performMonthlyMaintenance();
  
      System.out.println();
  
  
  
  
  
    //call the transfer() method
      myCurrentAccount.transfer(true, otherAccount, 100);   //transfer from myCurrentAccount to otherAccount
      System.out.println();
  
  
    //transfer from myCurrentAccount to mySavingsAccount
      myCurrentAccount.transfer(true, mySavingsAccount, 200); 
      System.out.println();
  
  
   ///transfer from otherAccount to myCurrentAccount
      otherAccount .transfer(true, myCurrentAccount, 100);    
     
    }
  }