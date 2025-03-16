# DES Algorithm Implementation

A Java implementation of the **Data Encryption Standard (DES)** – a symmetric-key block cipher used for secure data encryption and decryption. The program demonstrates the core principles of DES, including key generation, initial and final permutations, and the Feistel structure.

## Features
- Full implementation of the DES encryption and decryption process.
- Handles plaintext and key input via console.
- Performs all major DES steps:
  - Initial and final permutations
  - 16 Feistel rounds with subkey generation
  - Expansion, substitution (S-boxes), permutation, and XOR operations
- Outputs the encrypted ciphertext and the decrypted plaintext.
- Simple and clear structure to illustrate how DES works internally.

## About
This project was developed as part of my academic work to explore fundamental cryptographic algorithms. It serves as a demonstration of how classic encryption algorithms like DES operate internally, focusing on educational and proof-of-concept purposes.

> ⚠ **Note:** DES is no longer considered secure for modern applications but is still useful for learning symmetric cryptography concepts.
