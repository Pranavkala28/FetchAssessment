# Fetch Assessment

I have used Spring boot to build the backend API. Implemented the functionality as described. Dockerized the application for easy deployment and testing.

## Architecture

I have used MVC architecture with Item and Receipt as model classes. The ReceiptController class handles the https requests and maps them to corresponding handler methods. It has two endpoints ``` /process ``` and ``` /{id}/points ```. ReceiptService class is responsible for implementing the core functionality to support the controller layer.

## Running the application

* Clone the git repo from ```  https://github.com/Pranavkala28/FetchAssessment.git  ```
* Go to the project directory ```  ReceiptProcessor  ```
* Build the application using the command ```  docker build -t receiptprocessor .  ```
* Run the application ```  docker run -p 8080:8080 receiptprocessor  ```
* Now, it is ready for testing. Hit the endpoints ```  https://localhost:8080/receipts/process  ``` and ```  https://localhost:8080/receipts/{id}/points  ``` to test the functionality.
