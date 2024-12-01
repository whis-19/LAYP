import heapq


class BinaryTree:
    """
    Represents a node in the Huffman Binary Tree.
    """
    def __init__(self, value, frequency):
        self.value = value
        self.frequency = frequency
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.frequency < other.frequency

class HuffmanCoding:
    """
    Huffman Coding class for compressing and decompressing files.
    """
    def __init__(self, file_path):
        self.file_path = file_path
        self.heap = []
        self.codes = {}
        self.reverse_codes = {}

    def __build_frequency_dict(self, text):
        """
        Creates a frequency dictionary for characters in the given text.
        """
        frequency = {}
        for char in text:
            frequency[char] = frequency.get(char, 0) + 1
        return frequency

    def __build_heap(self, frequency):
        """
        Builds a min-heap from the frequency dictionary.
        """
        for char, freq in frequency.items():
            heapq.heappush(self.heap, BinaryTree(char, freq))

    def __build_tree(self):
        """
        Builds the Huffman Tree using the heap.
        """
        while len(self.heap) > 1:
            node1 = heapq.heappop(self.heap)
            node2 = heapq.heappop(self.heap)
            merged_node = BinaryTree(None, node1.frequency + node2.frequency)
            merged_node.left = node1
            merged_node.right = node2
            heapq.heappush(self.heap, merged_node)

    def __generate_codes_helper(self, root, current_code):
        """
        Recursively generates Huffman codes for characters.
        """
        if not root:
            return
        if root.value:
            self.codes[root.value] = current_code
            self.reverse_codes[current_code] = root.value
        self.__generate_codes_helper(root.left, current_code + "0")
        self.__generate_codes_helper(root.right, current_code + "1")

    def __generate_codes(self):
        """
        Generates Huffman codes by traversing the Huffman Tree.
        """
        root = heapq.heappop(self.heap)
        self.__generate_codes_helper(root, "")

    def __get_encoded_text(self, text):
        """
        Encodes text using the generated Huffman codes.
        """
        return ''.join(self.codes[char] for char in text)

    def __pad_encoded_text(self, encoded_text):
        """
        Pads the encoded text to make it a multiple of 8 bits.
        """
        extra_padding = 8 - len(encoded_text) % 8
        padded_info = f"{extra_padding:08b}"
        return padded_info + encoded_text + "0" * extra_padding

    def __remove_padding(self, padded_text):
        """
        Removes padding from the encoded text.
        """
        padding_info = padded_text[:8]
        extra_padding = int(padding_info, 2)
        return padded_text[8:-extra_padding]

    def __decode_text(self, encoded_text):
        """
        Decodes the encoded text using the reverse Huffman codes.
        """
        current_code = ""
        decoded_text = []
        for bit in encoded_text:
            current_code += bit
            if current_code in self.reverse_codes:
                decoded_text.append(self.reverse_codes[current_code])
                current_code = ""
        return ''.join(decoded_text)

    def compress(self):
        """
        Compresses the input file and writes the compressed data to a binary file.
        """
        with open(self.file_path, 'r') as file:
            text = file.read().rstrip()

        frequency = self.__build_frequency_dict(text)
        self.__build_heap(frequency)
        self.__build_tree()
        self.__generate_codes()

        encoded_text = self.__get_encoded_text(text)
        padded_encoded_text = self.__pad_encoded_text(encoded_text)

        byte_array = bytearray(int(padded_encoded_text[i:i + 8], 2) for i in range(0, len(padded_encoded_text), 8))
        output_path = self.file_path.split('.')[0] + '.bin'

        with open(output_path, 'wb') as output:
            output.write(byte_array)

        print(f"File compressed to {output_path}")
        return output_path

    def decompress(self, input_path):
        """
        Decompresses the binary file and writes the decompressed data to a text file.
        """
        with open(input_path, 'rb') as file:
            bit_string = ''.join(f"{byte:08b}" for byte in file.read())

        actual_text = self.__remove_padding(bit_string)
        decoded_text = self.__decode_text(actual_text)

        output_path = input_path.split('.')[0] + "_decompressed.txt"
        with open(output_path, 'w') as output:
            output.write(decoded_text)

        print(f"File decompressed to {output_path}")
        return output_path


# Example Usage
if __name__ == "__main__":
    huffman = HuffmanCoding("sample.txt")
    compressed_file = huffman.compress()
    huffman.decompress(compressed_file)
