#include <iostream>
#include <string>
#include <fstream>
#include <algorithm>

void reverseFile(std::ifstream& inFile, std::ofstream& outFile) {
    std::string content((std::istreambuf_iterator<char>(inFile)),
                        std::istreambuf_iterator<char>());
    std::reverse(content.begin(), content.end());
    outFile << content;
}

void appendToFile(const std::string& filePath, const std::string& content) {
    std::ofstream outFile(filePath, std::ios::app);
    if (outFile.is_open()) {
        outFile << content << std::endl;
        outFile.close();
    } else {
        std::cerr << "Error opening file for appending!" << std::endl;
    }
}

int main() {
    std::cout << "Welcome to the CT5 File Manager!" << std::endl;
    std::string CT5FilePath = "CSC450_CT5_mod5.txt";
    std::string userChoice;

    while (true) {
        std::cout << "Would you like to 'reverse' a file, 'add' to a file, or 'exit'?" << std::endl;
        std::getline(std::cin, userChoice);

        if (userChoice == "exit") {
            break;
        } else if (userChoice == "reverse") {
            std::ifstream inFile(CT5FilePath);
            std::string outputFilePath = "CSC450-mod5-reverse.txt";
            std::ofstream outFile(outputFilePath);
            if (inFile.is_open() && outFile.is_open()) {
                reverseFile(inFile, outFile);
                std::cout << "File has been reversed and saved to " << outputFilePath << std::endl;
            } else {
                std::cerr << "Error opening files!" << std::endl;
            }
        } else if (userChoice == "add") {
            std::cout << "Enter the content you want to add to the file:" << std::endl;
            std::string content;
            std::getline(std::cin, content);
            appendToFile(CT5FilePath, content);
            std::cout << "Content added to " << CT5FilePath << std::endl;
        } else {
            std::cout << "Invalid choice. Please enter 'reverse', 'add', or 'exit'." << std::endl;
        }
    }

    return 0;
}
