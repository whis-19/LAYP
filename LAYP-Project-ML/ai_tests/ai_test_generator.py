import random

class AITestHuffman:
    def __init__(self, generator, num_cases=5):
        self.generator = generator
        self.num_cases = num_cases
        self.test_cases = []

    def generate_test_cases(self):
        """
        Generates test cases based on Boundary Value Analysis (BVA) and Edge Coverage Criteria (ECP),
        ensuring no duplication.
        """
        print(f"Generating {self.num_cases} unique test cases based on BVA and ECP...")

        # Seed examples for boundary and edge cases
        boundary_seeds = [
            "a",  # Minimum length
            "the",  # Common word
            "hello world",  # Simple sentence
            "data science artificial intelligence",  # Longer, more complex seed
            " ".join([random.choice(self.generator.tokenizer.word_index) for _ in range(50)]),  # Random word sequence
        ]

        # Add some rare or less frequent words from the training corpus (sample BVA and ECP)
        uncommon_words = [
            "obfuscation", "polyglot", "intercalary", "hemidemisemiquaver", "schadenfreude"
        ]
        boundary_seeds += random.sample(uncommon_words, min(2, len(uncommon_words)))

        # Ensure uniqueness by adding randomization and avoiding duplicates
        for _ in range(self.num_cases):
            seed_text = random.choice(boundary_seeds)  # Select a random boundary seed
            generated_text = self.generator.generate_text(seed_text, next_words=50).strip()
            self.test_cases.append(generated_text)
        
        # Ensure no duplicates
        self.test_cases = list(set(self.test_cases))
        print(f"Generated {len(self.test_cases)} unique test cases.")
        return self.test_cases
