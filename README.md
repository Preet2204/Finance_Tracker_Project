# 🏦 Banking System – Java OOP Project

A command-line based banking system implemented in **Java** using fundamental **Object-Oriented Programming (OOP)** principles. This project was developed as part of academic coursework to simulate real-world banking operations and strengthen understanding of abstraction, encapsulation, and inheritance.

---

## 🚀 Features

- 🔐 **User Authentication**
  - Sign up and secure login with password strength validation.
  - Password must include uppercase, lowercase, digit, and be at least 5 characters long.

- 🧾 **Account Management**
  - Users can create multiple accounts of different types:
    - **Savings Account**
    - **Fixed Deposit (FD) Account**
    - **Checking Account**
    - **Credit Card Account**
  - Unique account numbers and custom starting balances.

- 💰 **Account Operations**
  - **Deposit** and **Withdraw** funds with business logic (e.g., minimum balance penalties for Savings).
  - Console-based interactive system for performing operations on selected accounts.

- 📦 **OOP Implementation**
  - Abstract base class `Accounts` for shared methods and structure.
  - Derived classes (`Savings`, `FD`, `Checking`, `CreditCard`) with custom behavior.
  - Modular and maintainable code using proper access modifiers and constructors.

---

## 🛠 Tech Stack

- **Language:** Java  
- **Paradigms:** Object-Oriented Programming (Abstraction, Inheritance, Encapsulation)  
- **Data Structures:** ArrayList  
- **User Input:** Scanner (CLI)  
- **Validation:** Input and password validation logic

---

## 📁 Folder Structure

├── Main.java # Entry point: handles login, signup, and user navigation

├── User.java # User class managing user credentials and account list

├── Accounts.java # Abstract class for common account properties

├── Savings.java # Concrete account type with specific withdrawal rules

├── FD.java # Fixed Deposit account (structure defined)

├── Checking.java # Checking account (placeholder)

├── Credit_Card.java # Credit Card account (placeholder)



## 🔍 Sample Flow

```bash
> Welcome. Press 0 to Sign Up or 1 to Sign In!
> [Sign up]
> Enter User Name:
> Create Password:
> You have successfully created an account.

> What would you like to do?
1. Add an account
2. Perform actions on existing account
...
```

👨‍💻 Contributors:

[Preet Sidhhapura](https://github.com/Preet2204)

[Akshat Shah](https://github.com/Akshat1815)

