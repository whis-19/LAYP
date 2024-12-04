import os
import csv

from ai_test_generator import AITestHuffman
from huffman_coding import HuffmanCoding
import datetime
from openpyxl import Workbook
from ai_test_generator import TextGenerator

def save_results_to_excel(results, file_path):
    workbook = Workbook()
    sheet = workbook.active
    sheet.title = "Test Results"

    headers = ["Timestamp", "Unique ID", "Test Case Number", "Input Text", "Expected Output", "Real Output", "Result"]
    sheet.append(headers)

    for result in results:
        sheet.append(result)

    workbook.save(file_path)

if __name__ == "__main__":
    data_path = "data/training_data.txt"
    output_file = "results/huffman_test_results.xlsx"

    num_cases = int(input("Enter the number of test cases to generate: "))

    text_gen = TextGenerator(data_path)
    text_gen.train_model(epochs=5)

    test_case_generator = AITestHuffman(text_generator=text_gen, num_cases= num_cases)
    test_cases = test_case_generator.generate_test_cases()

    huffman = HuffmanCoding()
    results = []

    for idx, test_case in enumerate(test_cases, start=1):
        compressed = huffman.compress(test_case)
        decompressed = huffman.decompress(compressed)

        result = "Pass" if test_case == decompressed else "Fail"
        results.append([
            datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            f"TC-{idx:03}",
            idx,
            test_case,
            test_case,
            decompressed,
            result
        ])

    save_results_to_excel(results, output_file)
    print(f"Test results saved to {output_file}")