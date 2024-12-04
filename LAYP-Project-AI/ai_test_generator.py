import random
from sklearn.utils import shuffle
from sklearn.feature_extraction.text import ENGLISH_STOP_WORDS

from text_generator import TextGenerator
class AITestHuffman:
    def __init__(self, text_generator, num_cases=5):
        self.text_generator = text_generator
        self.num_cases = num_cases
        self.test_cases = []

    def generate_test_cases(self):
        print(f"Generating {self.num_cases} unique test cases using TextGenerator and predefined cases...")

        boundary_cases = [
            "",
            "a",
            "ab",
            "The quick brown fox jumps over the lazy dog",
            "Data Science AI ML Deep Learning Neural Networks",
        ]

        stopword_cases = [" ".join(shuffle(list(ENGLISH_STOP_WORDS))[:random.randint(5, 20)]) for _ in range(3)]
        generated_cases = [
            self.text_generator.generate_text("artificial intelligence", next_words=50).strip() for _ in range(3)
        ]

        all_cases = list(set(boundary_cases + stopword_cases + generated_cases))
        random.shuffle(all_cases)
        self.test_cases = all_cases[:self.num_cases]
        print(f"Generated {len(self.test_cases)} unique test cases.")
        return self.test_cases