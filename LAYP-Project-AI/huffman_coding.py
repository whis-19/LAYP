class PriorityQueue:
    def __init__(self):
        self.queue = []

    def push(self, node):
        self.queue.append(node)
        self.queue.sort(key=lambda x: x.frequency) 

    def pop(self):
        if not self.is_empty():
            return self.queue.pop(0) 
        return None

    def is_empty(self):
        return len(self.queue) == 0

class BinaryTree:
    def __init__(self, value, frequency):
        self.value = value
        self.frequency = frequency
        self.left = None
        self.right = None

class HuffmanCoding:
    def __init__(self):
        self.priority_queue = PriorityQueue()
        self.codes = {}
        self.reverse_codes = {}

    def __build_frequency_dict(self, text):
        frequency = {}
        for char in text:
            frequency[char] = frequency.get(char, 0) + 1
        return frequency

    def __build_queue(self, frequency):
        for char, freq in frequency.items():
            self.priority_queue.push(BinaryTree(char, freq))

    def __build_tree(self):
        while len(self.priority_queue.queue) > 1:
            node1 = self.priority_queue.pop()
            node2 = self.priority_queue.pop()
            merged_node = BinaryTree(None, node1.frequency + node2.frequency)
            merged_node.left = node1
            merged_node.right = node2
            self.priority_queue.push(merged_node)

    def __generate_codes_helper(self, root, current_code):
        if not root:
            return
        if root.value is not None:
            self.codes[root.value] = current_code
            self.reverse_codes[current_code] = root.value
        self.__generate_codes_helper(root.left, current_code + "0")
        self.__generate_codes_helper(root.right, current_code + "1")

    def __generate_codes(self):
        root = self.priority_queue.pop()
        if root is not None:
            self.__generate_codes_helper(root, "")

    def __get_encoded_text(self, text):
        return ''.join(self.codes[char] for char in text)

    def __pad_encoded_text(self, encoded_text):
        extra_padding = 8 - len(encoded_text) % 8
        padded_info = f"{extra_padding:08b}"
        return padded_info + encoded_text + "0" * extra_padding

    def __remove_padding(self, padded_text):
        padding_info = padded_text[:8]
        extra_padding = int(padding_info, 2)
        return padded_text[8:-extra_padding] if extra_padding < len(padded_text) else ""

    def __decode_text(self, encoded_text):
        current_code = ""
        decoded_text = []
        for bit in encoded_text:
            current_code += bit
            if current_code in self.reverse_codes:
                decoded_text.append(self.reverse_codes[current_code])
                current_code = ""
        return ''.join(decoded_text)

    def compress(self, text):
        if not text:
            return "00000000"  
        frequency = self.__build_frequency_dict(text)
        self.__build_queue(frequency)
        self.__build_tree()
        self.__generate_codes()
        encoded_text = self.__get_encoded_text(text)
        return self.__pad_encoded_text(encoded_text)

    def decompress(self, binary_string):
        if not binary_string or binary_string == "00000000":
            return ""  
        actual_text = self.__remove_padding(binary_string)
        return self.__decode_text(actual_text)