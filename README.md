# Midterm-project
JAVA Bootcamp Midterm Project powered IronHack - Accenture 

Created by Laura Zambrano

## Introduction

In the following project a banking system was created from the set of requirements. the system has four types of StudentChecking, Checking, Savings y CreditCard accounts.

## Accounts

These accounts inherit from a parent abstract class called account with the common characteristics of all of them. For example the account number, the secret key, the balance, primary owner, secondary owner optional, a penalty, an account status (frozen or active), an account creation date, an account type, and the date of the last query. 

Each daughter account has its own characteristics, such as its interest rate, handling fee or credit limit. In addition, the corresponding interest rates and penalties are correctly applied. For example, the penaltyFee for all accounts should be 40, and if any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically.

* Savings accounts have a default interest rate of 0.0025 and a default minimumBalance of 1000. Savings accounts have been instantiated with an interest rate other than the default, with a maximum interest rate of 0.5. Savings accounts have been instantiated with a minimum balance of less than 1000 but no lower than 100. Interest on savings accounts is added to the account annually at the rate of specified interestRate per year. 

* CreditCard accounts have a default creditLimit of 100 and a interestRate of 0.2. CreditCards have been instantiated with a creditLimit higher than 100 but not higher than 100000. CreditCards have been instantiated with an interestRate less than 0.2 but not lower than 0.1 Interest on credit cards is added to the balance monthly.

* When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account have been created otherwise a regular Checking Account should be created. Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12

## Users
The system must have 3 types of Users: Admins and AccountHolders.

* The AccountHolders access their own accounts when passing the correct credentials using Basic Auth. AccountHolders have a name, date of birth, a primaryAddress, an optional mailingAddress. 
AccountHolders can access their own account balance and transfer money from any of their accounts to any other account. The transfer is only processed if the account has sufficient funds. The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer. Addionally, AccountHolders transfer money to ThirdParty users. AccountHolders methods are:
```java
    // Get methods
    Set<Account> findMyAccountsByAccountHolderId(CustomUserDetails userDetails);
    Account findMyAccountByAccountId(Long accountId, CustomUserDetails userDetails);
    Money findMyBalanceByAccountId(Long accountId, CustomUserDetails userDetails);
    List<Transfer> findMyTransfersReceiverByAccountId(Long accountId, CustomUserDetails userDetails);
    
    // Patch methods
    List<Transfer> findMyTransfersSenderByAccountId(Long accountId, CustomUserDetails userDetails);
    void sendMoneyAccountHolder(Long accountId, CustomUserDetails userDetails, TransferSendMoneyDTO transferSendMoneyDTO);
    void sendMoneyThirdParty(Long accountId, CustomUserDetails userDetails, TransferSendMoneyDTO transferSendMoneyDTO);
 ```
* Admins only have a name. Admins can access the balance for any account and to modify it. Admins methods are:
```java
    // Get methods
    List<Account> findAllAccounts();
    Account findAccountById(Long accountId);
    Money getBalanceByAccountId(Long accountId);
    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);
    List<Transfer> findAllTransfer();

    // Post methods
    Account createNewAccount(Checking checkingAccount);
    Account createNewAccount(Saving savingAccount);
    Account createNewAccount(CreditCardCreateDTO creditCard);
    ThirdParty createThirdParty(ThirdParty thirdParty);

    // Patch methods
    void updateBalanceByAccountId(Long accountId, AccountBalanceDTO accountBalanceDTO);

    // Delete methods
    void deleteAccount(Long accountId);
 ```
* The ThirdParty Accounts have a hashed key and a name. Third-party users can receive and send money to other accounts. Third-party users can be added to the database by an admin. In order to receive and send money, Third-Party Users provide their hashed key in the header of the HTTP request. They also must provide the amount, the Account id and the account secret key.
```java
    // Get methods
    List<Transfer> findTransfersReceiverByThirdId(Long thirdId, String hashKey);
    List<Transfer> findTransfersSenderByThirdId(Long thirdId, String hashKey);
    
     // Patch methods
    void sendMoneyAccountHolder(Long thirdId, String hashKey, TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO);
    void receiveMoneyAccountHolder(Long thirdId, String hashKey, TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO);
 ```

## Fraud Detection
Additional fraud detection features are included. The application recognize patterns that indicate fraud and Freeze the account status when potential fraud is detected. Patterns that indicate fraud include:

* Transactions made in 24 hours total to more than 150% of the customers highest daily total transactions in any other 24 hour period.
* More than 2 transactions occurring on a single account within a 1 second period.

## Technical Requirements
* The backend has been realized in Java/Spring Boot.

* The Spring boot authentication has been included.

* The corresponding unit and integration tests have been performed and passed for all controllers.

* The file to create the DDL database is located in the corresponding static resources folder for both the main (validate) and test (create-drop) methods.

* The necessary validations have also been included. And the postman collection is included with the requests made (working REST API), verifying that the response matches what is expected.


### Class diagram

The following is the class diagram of the project carried out
https://app.diagrams.net/#G1jqVpWdKamQYIkgSQM4v7PJa6imnD4tlf

<p align="center">
    <img src = /src/main/resources/static/diagram-midterm-project.png>
</p>
