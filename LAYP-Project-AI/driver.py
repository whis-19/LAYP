import os
import csv
from ai_tests.text_generator import TextGenerator
from src.huffman_coding import HuffmanCoding

class HuffmanAITestDriver:
    def __init__(self, training_data_path, output_test_cases_path, num_cases, epochs):

        self.training_data_path = training_data_path
        self.output_test_cases_path = output_test_cases_path
        self.num_cases = num_cases
        self.epochs = epochs
        self.generator = TextGenerator(data_path=self.training_data_path)

    def train_model(self):

        print("Training the text generator model...")
        self.generator.train_model(epochs=self.epochs)
        print("Model training completed.")

    def generate_test_cases(self):

        print(f"Generating {self.num_cases} unique test cases...")
        test_cases = []
        for i in range(self.num_cases):
            seed_text = f"test case {i+1}" 
            generated_text = self.generator.generate_text(seed_text, next_words=50).strip()
            test_cases.append(generated_text)

        with open(self.output_test_cases_path, "w") as file:
            for case in test_cases:
                file.write(case + "\n\n")
        print(f"Test cases generated and saved to {self.output_test_cases_path}.")
        return test_cases

    def run_tests(self, test_cases):

        print("Running tests on generated cases...")
        

        with open('test_case_results.csv', mode='w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(["Test Case", "Expected Output", "Real Output", "Test Result"])
            
            for i, test_case in enumerate(test_cases):
                test_file = f"test_case_{i + 1}.txt"
                

                with open(test_file, "w") as f:
                    f.write(test_case)


                huffman = HuffmanCoding(test_file)
                compressed_file = huffman.compress()
                decompressed_file = huffman.decompress(compressed_file)


                with open(decompressed_file, "r") as f:
                    decompressed_content = f.read().strip()


                test_result = "Pass" if test_case.strip() == decompressed_content else "Fail"


                writer.writerow([test_case.strip(), test_case.strip(), decompressed_content, test_result])


                os.remove(test_file)
                os.remove(compressed_file)
                os.remove(decompressed_file)

        print("All tests completed. Results saved to 'test_case_results.csv'.")

    def execute(self):

        self.train_model()
        test_cases = self.generate_test_cases()
        self.run_tests(test_cases)


if __name__ == "__main__":

    training_data_path = "data/training_data.txt"
    output_test_cases_path = "data/generated_test_cases.txt"

    print("AI-Based Test Automation for Huffman Coding")
    num_cases = int(input("Enter the number of test cases to generate: "))
    epochs = int(input("Enter the number of training epochs for the AI model: "))

    driver = HuffmanAITestDriver(
        training_data_path=training_data_path,
        output_test_cases_path=output_test_cases_path,
        num_cases=num_cases,
        epochs=epochs
    )
    driver.execute()
