# 🌍 Country Explorer App

📱 **An Android application for exploring country details**  

## 📌 Overview
The **Country Explorer App** is an Android application that fetches and displays country details, including country name, region, code, and capital. It retrieves data from a remote API and presents it in a structured and user-friendly UI.

This project follows **MVVM with Clean Architecture** to ensure **modularity, scalability, and maintainability**.

---

## 🎯 Features
✔️ Fetches and displays a list of countries from a remote API.  
✔️ Provides search functionality to filter countries by name or capital.  
✔️ Displays country details, including region, code, and capital.  
✔️ Implements **Kotlin Flows** for reactive state management.  
✔️ Follows **MVVM with Clean Architecture** for separation of concerns.  
✔️ Uses **Retrofit** for API calls and **ViewBinding** for UI updates.  
✔️ Implements **unit testing** using MockK, JUnit, and Turbine.  

---

## 🛠️ Technologies Used

| **Technology**       | **Purpose**                                   |
|----------------------|----------------------------------------------|
| **Kotlin**          | Primary language for Android development     |
| **Jetpack ViewModel** | UI state management                         |
| **Retrofit**        | Networking library for API calls             |
| **Kotlin Coroutines & Flow** | Asynchronous programming             |
| **Dependency Injection** | Service Locator pattern                  |
| **MockK**           | Unit testing framework for mocking dependencies |
| **JUnit**          | Framework for unit testing                    |
| **Turbine**        | Flow testing framework                        |

---

## 🏗️ Architecture

This project follows the **MVVM with Clean Architecture** approach, ensuring a well-structured and maintainable codebase.

### 🔹 Layered Architecture

1️⃣ **Presentation Layer**  
   - Contains **ViewModels** that interact with use cases and manage UI state.  
   - Uses **ViewBinding** for UI rendering.  
   - Implements **state management** to update the UI reactively.  

2️⃣ **Domain Layer**  
   - Contains **use cases** that encapsulate business logic.  
   - Defines an **interface** for the repository to enforce dependency inversion.  

3️⃣ **Data Layer**  
   - Implements the **repository interface**.  
   - Uses **Retrofit** to fetch data from a remote API.  
   - Handles **data transformation** before passing it to the domain layer.  

✅ **Why Clean Architecture?**  
- Improves **scalability**, **testability**, and **maintainability**.  
- Ensures **separation of concerns**.  

---

## 🌐 API Integration

✔️ The app fetches **country data** from a REST API.  
✔️ Uses **Retrofit** to handle network requests efficiently.  
✔️ Maps API responses to **domain models** for better maintainability.  
✔️ Implements **error handling** to manage network failures gracefully.  

---

## 🛠️ Dependency Injection

The app follows a **Service Locator** pattern for dependency injection to:  
- Manage instances of **Retrofit** for API communication.  
- Inject **Repository** for data fetching.  
- Inject **ViewModels** for UI state management.  
- Ensure **loose coupling** and improve testability.

---

## 🧪 Testing Strategy

### ✅ Unit Testing
- **Use Case Testing**: Verifies business logic.  
- **Repository Testing**: Ensures API calls are handled correctly.  
- **ViewModel Testing**: Checks if state updates correctly.
  
---

## 🎯 Future Enhancements
🔹 **Implement caching for offline support.**  
🔹 **Add dark mode support.**  
🔹 **Improve error handling with retry mechanisms.**  

---

## 📺 Demo Video
🎥 Watch a demo of the app:  

https://github.com/user-attachments/assets/42b869cd-b58d-4ad0-b75c-4d5f24187514 

---

## 📦 How to Run the Project

1️⃣ Clone the repository:  
```bash
git clone https://github.com/manan86/CountryExplorerApp.git
cd CountryExplorerApp
