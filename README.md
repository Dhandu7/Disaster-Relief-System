## Disaster Relief System Application Usage

This repository contains the code for Assignment #3, developed by Debojeet Dam (UCID: 30171419).

### Prerequisites

Before compiling and running the application, make sure you have:

- The Java Development Kit (JDK) installed on your computer.
- The necessary JAR files in the `lib` folder of the project directory.

### Compiling the Code

To compile the source code, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the project directory.

Run the following command to compile the source files:

javac -cp lib/* edu/ucalgary/oop/*.java

### Running the Application

After you have compiled the source code, you can run the application using one of the following commands:

To run the `DisasterVictimInterface` class, use:
java -cp "lib/postgresql-42.7.3.jar;." edu.ucalgary.oop.DisasterVictimInterface

To run the `InquirerInterface` class, use:
java -cp "lib/postgresql-42.7.3.jar;." edu.ucalgary.oop.InquirerInterface
