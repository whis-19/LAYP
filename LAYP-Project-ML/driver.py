import os
import csv
from ai_tests.text_generator import TextGenerator
from src.huffman_coding import HuffmanCoding

class HuffmanAITestDriver:
    def __init__(self, training_data_path, output_test_cases_path, num_cases, epochs):
        """
        Initializes the AI-based Huffman test driver.
        
        :param training_data_path: Path to the training data for the text generator.
        :param output_test_cases_path: Path to save the generated test cases.
        :param num_cases: Number of test cases to generate.
        :param epochs: Number of training epochs for the text generator.
        """
        self.training_data_path = training_data_path
        self.output_test_cases_path = output_test_cases_path
        self.num_cases = num_cases
        self.epochs = epochs
        self.generator = TextGenerator(data_path=self.training_data_path)

    def train_model(self):
        """
        Trains the text generator model.
        """
        print("Training the text generator model...")
        self.generator.train_model(epochs=self.epochs)
        print("Model training completed.")

    def generate_test_cases(self):
        """
        Generates unique test cases using the trained model and saves them to a file.
        """
        print(f"Generating {self.num_cases} unique test cases...")
        test_cases = []
        for i in range(self.num_cases):
            seed_text = f"test case {i+1}"  # Make each test case unique by modifying the seed text
            generated_text = self.generator.generate_text(seed_text, next_words=50).strip()
            test_cases.append(generated_text)

        with open(self.output_test_cases_path, "w") as file:
            for case in test_cases:
                file.write(case + "\n\n")
        print(f"Test cases generated and saved to {self.output_test_cases_path}.")
        return test_cases

    def run_tests(self, test_cases):
        """
        Runs Huffman compression and decompression for the generated test cases.
        Also stores the generated test case, expected output, and real output.
        """
        print("Running tests on generated cases...")
        
        # Open the CSV file to store the test case data
        with open('test_case_results.csv', mode='w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(["Test Case", "Expected Output", "Real Output", "Test Result"])
            
            for i, test_case in enumerate(test_cases):
                test_file = f"test_case_{i + 1}.txt"
                
                # Write the test case to a temporary file
                with open(test_file, "w") as f:
                    f.write(test_case)

                # Initialize HuffmanCoding and run compression and decompression
                huffman = HuffmanCoding(test_file)
                compressed_file = huffman.compress()
                decompressed_file = huffman.decompress(compressed_file)

                # Read the decompressed content
                with open(decompressed_file, "r") as f:
                    decompressed_content = f.read().strip()

                # Check if the test case passed
                test_result = "Pass" if test_case.strip() == decompressed_content else "Fail"

                # Write the test case, expected output, real output, and result to the CSV file
                writer.writerow([test_case.strip(), test_case.strip(), decompressed_content, test_result])

                # Clean up temporary files
                os.remove(test_file)
                os.remove(compressed_file)
                os.remove(decompressed_file)

        print("All tests completed. Results saved to 'test_case_results.csv'.")

    def execute(self):
        """
        Executes the complete workflow: training, generating cases, and testing.
        """
        self.train_model()
        test_cases = self.generate_test_cases()
        self.run_tests(test_cases)


if __name__ == "__main__":
    # Prompt user for input parameters
    training_data_path = "data/training_data.txt"
    output_test_cases_path = "data/generated_test_cases.txt"

    print("AI-Based Test Automation for Huffman Coding")
    num_cases = int(input("Enter the number of test cases to generate: "))
    epochs = int(input("Enter the number of training epochs for the AI model: "))

    # Initialize and execute the driver
    driver = HuffmanAITestDriver(
        training_data_path=training_data_path,
        output_test_cases_path=output_test_cases_path,
        num_cases=num_cases,
        epochs=epochs
    )
    driver.execute()
