import tensorflow as tf
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
import numpy as np

class TextGenerator:
    def __init__(self, data_path, max_sequence_len=20, embedding_dim=64, lstm_units=64):
        self.data_path = data_path
        self.tokenizer = Tokenizer()
        self.model = None
        self.max_sequence_len = max_sequence_len
        self.embedding_dim = embedding_dim
        self.lstm_units = lstm_units

    def load_data(self):
        with open(self.data_path, "r") as file:
            text = file.read()

        corpus = text.lower().split("\n")
        self.tokenizer.fit_on_texts(corpus)
        total_words = len(self.tokenizer.word_index) + 1

        input_sequences = []
        for line in corpus:
            token_list = self.tokenizer.texts_to_sequences([line])[0]
            for i in range(1, len(token_list)):
                n_gram_sequence = token_list[:i + 1]
                input_sequences.append(n_gram_sequence)

        max_seq_len = max(len(seq) for seq in input_sequences)
        input_sequences = pad_sequences(input_sequences, maxlen=max_seq_len, padding="pre")
        inputs, labels = input_sequences[:, :-1], input_sequences[:, -1]
        labels = tf.keras.utils.to_categorical(labels, num_classes=total_words)

        return inputs, labels, total_words, max_seq_len

    def build_model(self, total_words):
        model = tf.keras.Sequential([
            tf.keras.layers.Embedding(total_words, self.embedding_dim, input_length=self.max_sequence_len - 1),
            tf.keras.layers.LSTM(self.lstm_units),
            tf.keras.layers.Dense(total_words, activation="softmax")
        ])
        model.compile(optimizer="adam", loss="categorical_crossentropy", metrics=["accuracy"])
        return model

    def train_model(self, epochs=10):
        inputs, labels, total_words, max_seq_len = self.load_data()
        self.max_sequence_len = max_seq_len
        self.model = self.build_model(total_words)
        self.model.fit(inputs, labels, epochs=epochs, verbose=2)

    def generate_text(self, seed_text, next_words=20):
        for _ in range(next_words):
            token_list = self.tokenizer.texts_to_sequences([seed_text])[0]
            token_list = pad_sequences([token_list], maxlen=self.max_sequence_len - 1, padding="pre")
            predicted = np.argmax(self.model.predict(token_list, verbose=0), axis=-1)
            output_word = self.tokenizer.index_word.get(predicted[0], "")
            seed_text += " " + output_word
        return seed_text.