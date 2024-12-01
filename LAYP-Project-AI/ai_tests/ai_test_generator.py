import random

class AITestHuffman:
    def __init__(self, generator, num_cases=5):
        self.generator = generator
        self.num_cases = num_cases
        self.test_cases = []

    def generate_test_cases(self):

        print(f"Generating {self.num_cases} unique test cases based on BVA and ECP...")


        boundary_seeds = [
            "a", 
            "the", 
            "hello world", 
            "data science artificial intelligence", 
            " ".join([random.choice(self.generator.tokenizer.word_index) for _ in range(50)]),  
        ]

       
        uncommon_words = [
            "obfuscation", "polyglot", "intercalary", "hemidemisemiquaver", "schadenfreude"
        ]
        boundary_seeds += random.sample(uncommon_words, min(2, len(uncommon_words)))

       
        for _ in range(self.num_cases):
            seed_text = random.choice(boundary_seeds) 
            generated_text = self.generator.generate_text(seed_text, next_words=50).strip()
            self.test_cases.append(generated_text)
        
   
        self.test_cases = list(set(self.test_cases))
        print(f"Generated {len(self.test_cases)} unique test cases.")
        return self.test_cases
