/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cryptography;

import java.util.Arrays;

/**
 *
 * @author Daniel
 */

public class AES 
{
    private static byte[] sbox =
    {
    (byte) 0x63, (byte) 0x7C, (byte) 0x77, (byte) 0x7B, (byte) 0xF2, (byte) 0x6B, (byte) 0x6F, (byte) 0xC5, (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2B, (byte) 0xFE, (byte) 0xD7, (byte) 0xAB, (byte) 0x76,
    (byte) 0xCA, (byte) 0x82, (byte) 0xC9, (byte) 0x7D, (byte) 0xFA, (byte) 0x59, (byte) 0x47, (byte) 0xF0, (byte) 0xAD, (byte) 0xD4, (byte) 0xA2, (byte) 0xAF, (byte) 0x9C, (byte) 0xA4, (byte) 0x72, (byte) 0xC0,
    (byte) 0xB7, (byte) 0xFD, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3F, (byte) 0xF7, (byte) 0xCC, (byte) 0x34, (byte) 0xA5, (byte) 0xE5, (byte) 0xF1, (byte) 0x71, (byte) 0xD8, (byte) 0x31, (byte) 0x15,
    (byte) 0x04, (byte) 0xC7, (byte) 0x23, (byte) 0xC3, (byte) 0x18, (byte) 0x96, (byte) 0x05, (byte) 0x9A, (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xE2, (byte) 0xEB, (byte) 0x27, (byte) 0xB2, (byte) 0x75,
    (byte) 0x09, (byte) 0x83, (byte) 0x2C, (byte) 0x1A, (byte) 0x1B, (byte) 0x6E, (byte) 0x5A, (byte) 0xA0, (byte) 0x52, (byte) 0x3B, (byte) 0xD6, (byte) 0xB3, (byte) 0x29, (byte) 0xE3, (byte) 0x2F, (byte) 0x84,
    (byte) 0x53, (byte) 0xD1, (byte) 0x00, (byte) 0xED, (byte) 0x20, (byte) 0xFC, (byte) 0xB1, (byte) 0x5B, (byte) 0x6A, (byte) 0xCB, (byte) 0xBE, (byte) 0x39, (byte) 0x4A, (byte) 0x4C, (byte) 0x58, (byte) 0xCF,
    (byte) 0xD0, (byte) 0xEF, (byte) 0xAA, (byte) 0xFB, (byte) 0x43, (byte) 0x4D, (byte) 0x33, (byte) 0x85, (byte) 0x45, (byte) 0xF9, (byte) 0x02, (byte) 0x7F, (byte) 0x50, (byte) 0x3C, (byte) 0x9F, (byte) 0xA8,
    (byte) 0x51, (byte) 0xA3, (byte) 0x40, (byte) 0x8F, (byte) 0x92, (byte) 0x9D, (byte) 0x38, (byte) 0xF5, (byte) 0xBC, (byte) 0xB6, (byte) 0xDA, (byte) 0x21, (byte) 0x10, (byte) 0xFF, (byte) 0xF3, (byte) 0xD2,
    (byte) 0xCD, (byte) 0x0C, (byte) 0x13, (byte) 0xEC, (byte) 0x5F, (byte) 0x97, (byte) 0x44, (byte) 0x17, (byte) 0xC4, (byte) 0xA7, (byte) 0x7E, (byte) 0x3D, (byte) 0x64, (byte) 0x5D, (byte) 0x19, (byte) 0x73,
    (byte) 0x60, (byte) 0x81, (byte) 0x4F, (byte) 0xDC, (byte) 0x22, (byte) 0x2A, (byte) 0x90, (byte) 0x88, (byte) 0x46, (byte) 0xEE, (byte) 0xB8, (byte) 0x14, (byte) 0xDE, (byte) 0x5E, (byte) 0x0B, (byte) 0xDB,
    (byte) 0xE0, (byte) 0x32, (byte) 0x3A, (byte) 0x0A, (byte) 0x49, (byte) 0x06, (byte) 0x24, (byte) 0x5C, (byte) 0xC2, (byte) 0xD3, (byte) 0xAC, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xE4, (byte) 0x79,
    (byte) 0xE7, (byte) 0xC8, (byte) 0x37, (byte) 0x6D, (byte) 0x8D, (byte) 0xD5, (byte) 0x4E, (byte) 0xA9, (byte) 0x6C, (byte) 0x56, (byte) 0xF4, (byte) 0xEA, (byte) 0x65, (byte) 0x7A, (byte) 0xAE, (byte) 0x08,
    (byte) 0xBA, (byte) 0x78, (byte) 0x25, (byte) 0x2E, (byte) 0x1C, (byte) 0xA6, (byte) 0xB4, (byte) 0xC6, (byte) 0xE8, (byte) 0xDD, (byte) 0x74, (byte) 0x1F, (byte) 0x4B, (byte) 0xBD, (byte) 0x8B, (byte) 0x8A,
    (byte) 0x70, (byte) 0x3E, (byte) 0xB5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xF6, (byte) 0x0E, (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xB9, (byte) 0x86, (byte) 0xC1, (byte) 0x1D, (byte) 0x9E,
    (byte) 0xE1, (byte) 0xF8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xD9, (byte) 0x8E, (byte) 0x94, (byte) 0x9B, (byte) 0x1E, (byte) 0x87, (byte) 0xE9, (byte) 0xCE, (byte) 0x55, (byte) 0x28, (byte) 0xDF,
    (byte) 0x8C, (byte) 0xA1, (byte) 0x89, (byte) 0x0D, (byte) 0xBF, (byte) 0xE6, (byte) 0x42, (byte) 0x68, (byte) 0x41, (byte) 0x99, (byte) 0x2D, (byte) 0x0F, (byte) 0xB0, (byte) 0x54, (byte) 0xBB, (byte) 0x16
    };
    
    private static byte[] rcon =
    {
    (byte) 0x8d, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80, (byte) 0x1b, (byte) 0x36, (byte) 0x6c, (byte) 0xd8, (byte) 0xab, (byte) 0x4d, (byte) 0x9a, 
    (byte) 0x2f, (byte) 0x5e, (byte) 0xbc, (byte) 0x63, (byte) 0xc6, (byte) 0x97, (byte) 0x35, (byte) 0x6a, (byte) 0xd4, (byte) 0xb3, (byte) 0x7d, (byte) 0xfa, (byte) 0xef, (byte) 0xc5, (byte) 0x91, (byte) 0x39, 
    (byte) 0x72, (byte) 0xe4, (byte) 0xd3, (byte) 0xbd, (byte) 0x61, (byte) 0xc2, (byte) 0x9f, (byte) 0x25, (byte) 0x4a, (byte) 0x94, (byte) 0x33, (byte) 0x66, (byte) 0xcc, (byte) 0x83, (byte) 0x1d, (byte) 0x3a, 
    (byte) 0x74, (byte) 0xe8, (byte) 0xcb, (byte) 0x8d, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80, (byte) 0x1b, (byte) 0x36, (byte) 0x6c, (byte) 0xd8, 
    (byte) 0xab, (byte) 0x4d, (byte) 0x9a, (byte) 0x2f, (byte) 0x5e, (byte) 0xbc, (byte) 0x63, (byte) 0xc6, (byte) 0x97, (byte) 0x35, (byte) 0x6a, (byte) 0xd4, (byte) 0xb3, (byte) 0x7d, (byte) 0xfa, (byte) 0xef, 
    (byte) 0xc5, (byte) 0x91, (byte) 0x39, (byte) 0x72, (byte) 0xe4, (byte) 0xd3, (byte) 0xbd, (byte) 0x61, (byte) 0xc2, (byte) 0x9f, (byte) 0x25, (byte) 0x4a, (byte) 0x94, (byte) 0x33, (byte) 0x66, (byte) 0xcc, 
    (byte) 0x83, (byte) 0x1d, (byte) 0x3a, (byte) 0x74, (byte) 0xe8, (byte) 0xcb, (byte) 0x8d, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80, (byte) 0x1b, 
    (byte) 0x36, (byte) 0x6c, (byte) 0xd8, (byte) 0xab, (byte) 0x4d, (byte) 0x9a, (byte) 0x2f, (byte) 0x5e, (byte) 0xbc, (byte) 0x63, (byte) 0xc6, (byte) 0x97, (byte) 0x35, (byte) 0x6a, (byte) 0xd4, (byte) 0xb3, 
    (byte) 0x7d, (byte) 0xfa, (byte) 0xef, (byte) 0xc5, (byte) 0x91, (byte) 0x39, (byte) 0x72, (byte) 0xe4, (byte) 0xd3, (byte) 0xbd, (byte) 0x61, (byte) 0xc2, (byte) 0x9f, (byte) 0x25, (byte) 0x4a, (byte) 0x94, 
    (byte) 0x33, (byte) 0x66, (byte) 0xcc, (byte) 0x83, (byte) 0x1d, (byte) 0x3a, (byte) 0x74, (byte) 0xe8, (byte) 0xcb, (byte) 0x8d, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, 
    (byte) 0x40, (byte) 0x80, (byte) 0x1b, (byte) 0x36, (byte) 0x6c, (byte) 0xd8, (byte) 0xab, (byte) 0x4d, (byte) 0x9a, (byte) 0x2f, (byte) 0x5e, (byte) 0xbc, (byte) 0x63, (byte) 0xc6, (byte) 0x97, (byte) 0x35, 
    (byte) 0x6a, (byte) 0xd4, (byte) 0xb3, (byte) 0x7d, (byte) 0xfa, (byte) 0xef, (byte) 0xc5, (byte) 0x91, (byte) 0x39, (byte) 0x72, (byte) 0xe4, (byte) 0xd3, (byte) 0xbd, (byte) 0x61, (byte) 0xc2, (byte) 0x9f, 
    (byte) 0x25, (byte) 0x4a, (byte) 0x94, (byte) 0x33, (byte) 0x66, (byte) 0xcc, (byte) 0x83, (byte) 0x1d, (byte) 0x3a, (byte) 0x74, (byte) 0xe8, (byte) 0xcb, (byte) 0x8d, (byte) 0x01, (byte) 0x02, (byte) 0x04, 
    (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80, (byte) 0x1b, (byte) 0x36, (byte) 0x6c, (byte) 0xd8, (byte) 0xab, (byte) 0x4d, (byte) 0x9a, (byte) 0x2f, (byte) 0x5e, (byte) 0xbc, (byte) 0x63, 
    (byte) 0xc6, (byte) 0x97, (byte) 0x35, (byte) 0x6a, (byte) 0xd4, (byte) 0xb3, (byte) 0x7d, (byte) 0xfa, (byte) 0xef, (byte) 0xc5, (byte) 0x91, (byte) 0x39, (byte) 0x72, (byte) 0xe4, (byte) 0xd3, (byte) 0xbd, 
    (byte) 0x61, (byte) 0xc2, (byte) 0x9f, (byte) 0x25, (byte) 0x4a, (byte) 0x94, (byte) 0x33, (byte) 0x66, (byte) 0xcc, (byte) 0x83, (byte) 0x1d, (byte) 0x3a, (byte) 0x74, (byte) 0xe8, (byte) 0xcb, (byte) 0x8d
    };
    
    private static byte[] block, key;
    private static int Nb, Nk, Nr;
    private static word[] arrayWords;
    
    public static class word
    {
        byte byte1;
        byte byte2;
        byte byte3;
        byte byte4;
    }
    
    public static void printWords(word[] w) 
    {
        System.out.printf("%02X %02X %02X %02X", w[0].byte1, w[1].byte1, w[2].byte1, w[3].byte1);
        System.out.println("");
        System.out.printf("%02X %02X %02X %02X", w[0].byte2, w[1].byte2, w[2].byte2, w[3].byte2);
        System.out.println("");
        System.out.printf("%02X %02X %02X %02X", w[0].byte3, w[1].byte3, w[2].byte3, w[3].byte3);
        System.out.println("");
        System.out.printf("%02X %02X %02X %02X", w[0].byte4, w[1].byte4, w[2].byte4, w[3].byte4);
        System.out.println("");
    }
    
    public static byte GF_product_p(byte a, byte b) 
    {
        byte r = 0;
        int raux, aaux, baux, xor;
        for (int i = 0; i < 8; ++i)
        {
            if ((b & 0x01) == 1)
            {
                raux = r & 0xFF;
                aaux = a & 0xFF;
                xor = raux ^ aaux;
                r = (byte)(xor);
            }
            int msb = a & 0x80;
            aaux = a & 0xFF;
            aaux = aaux << 1;
            a = (byte)(aaux);
            if (msb == 128)
            {
                xor = aaux ^ 0x1B;
                a = (byte)(xor);
            }
            baux = b & 0xFF;
            baux = baux >> 1;
            b = (byte)(baux);
        }
        return r;
    }
    
    public static word rotWord(word w)
    {
        // This does the following simple cyclic permutation of a word:
        // change [a0,a1,a2,a3] to [a1,a2,a3,a0]
        word aux = new word();
        aux.byte1 = w.byte2;
        aux.byte2 = w.byte3;
        aux.byte3 = w.byte4;
        aux.byte4 = w.byte1;
        return aux;
    }
    
    public static word subWord(word w)
    {
        // This just applies the S-box value used in SubBytes to each of the 4 bytes
        // in the argument
        word aux = new word();
        aux.byte1 = sbox[w.byte1 & 0xff];
        aux.byte2 = sbox[w.byte2 & 0xff];
        aux.byte3 = sbox[w.byte3 & 0xff];
        aux.byte4 = sbox[w.byte4 & 0xff];
        return aux;
    }
    
    public static word[] subBytes(word[] state) 
    {
        // This takes each byte of the state and independently looks it up
        // in the S-box table to substitute a different byte for it
        word[] aux = new word[4];
        for (int i = 0; i < 4; ++i) aux[i] = subWord(state[i]);
        return aux;
    }
    
    public static word[] shiftRows(word[] state) 
    {
        // This simply moves around the rows of the state array
        word[] aux = new word[4];
        for (int i = 0; i < 4; ++i)
        {
            word aux2 = new word();
            aux2.byte1 = state[i].byte1;
            aux2.byte2 = state[(i+1)%4].byte2;
            aux2.byte3 = state[(i+2)%4].byte3;
            aux2.byte4 = state[(i+3)%4].byte4; 
            aux[i] = aux2;
        }
        return aux;
    }
    
    public static word[] mixColumns(word[] state) 
    {
        // This is based on addition (XOR) and multiplication in GF(2^8)
        word[] aux = new word[4];
        for (int i = 0; i < 4; ++i)
        {
            word aux2 = new word();
            aux2.byte1 = (byte) (GF_product_p((byte)0x02,state[i].byte1) ^ GF_product_p((byte)0x03,state[i].byte2)
                                ^ state[i].byte3 ^ state[i].byte4);
            aux2.byte2 = (byte) (state[i].byte1 ^ GF_product_p((byte)0x02,state[i].byte2)
                                ^ GF_product_p((byte)0x03,state[i].byte3) ^ state[i].byte4);
            aux2.byte3 = (byte) (state[i].byte1 ^ state[i].byte2 ^ GF_product_p((byte)0x02,state[i].byte3)
                                ^ GF_product_p((byte)0x03,state[i].byte4));
            aux2.byte4 = (byte) (GF_product_p((byte)0x03,state[i].byte1) ^ state[i].byte2 ^ state[i].byte3
                                ^ GF_product_p((byte)0x02,state[i].byte4));
            aux[i] = aux2;
        }
        return aux;
    }
    
    public static word[] addRoundKey(word[] state, int round) 
    {
        // This does an XOR of successive portions of the
        // expanded key with the changing state array
        word[] aux = new word[4];
        for (int i = 0; i < 4; ++i)
        {
            word aux2 = new word();
            aux2.byte1 = (byte) (state[i].byte1 ^ arrayWords[round*4 + i].byte1);
            aux2.byte2 = (byte) (state[i].byte2 ^ arrayWords[round*4 + i].byte2);
            aux2.byte3 = (byte) (state[i].byte3 ^ arrayWords[round*4 + i].byte3);
            aux2.byte4 = (byte) (state[i].byte4 ^ arrayWords[round*4 + i].byte4);
            aux[i] = aux2;
        }
        return aux;
    }
    
    public static void keyExpansion()
    {
        arrayWords = new word[44];
        word aux;
        int i = 0;
        while (i < 44)
        {
            // The first four columns are filled with the given Cipher key
            if (i < 4) 
            {
                aux = new word();
                aux.byte1 = key[i*4];
                aux.byte2 = key[i*4 + 1];
                aux.byte3 = key[i*4 + 2];
                aux.byte4 = key[i*4 + 3];
                arrayWords[i] = aux;
            }
            else
            {
                // Words in positions that are multiples of 4 (W4, W8, ... , W40) are calculated by:
                // a) applying the RotWord and SubBytes transformation to the previous word
                // b) this result XOR the word 4 positions earlier XOR Rcon[position/4]
                if (i % 4 == 0) 
                {
                    aux = new word();
                    aux = rotWord(arrayWords[i-1]);
                    aux = subWord(aux);
                    aux.byte1 = (byte) (aux.byte1 ^ rcon[i/4]);
                    aux.byte1 = (byte) (aux.byte1 ^ arrayWords[i-4].byte1);
                    aux.byte2 = (byte) (aux.byte2 ^ arrayWords[i-4].byte2);
                    aux.byte3 = (byte) (aux.byte3 ^ arrayWords[i-4].byte3);
                    aux.byte4 = (byte) (aux.byte4 ^ arrayWords[i-4].byte4);
                    arrayWords[i] = aux;
                }
                // The remaining words are calculated by doing XOR with the previous word
                // and the word 4 positions earlier
                else 
                {
                    aux = new word();
                    aux.byte1 = (byte) (arrayWords[i-1].byte1 ^ arrayWords[i-4].byte1);
                    aux.byte2 = (byte) (arrayWords[i-1].byte2 ^ arrayWords[i-4].byte2);
                    aux.byte3 = (byte) (arrayWords[i-1].byte3 ^ arrayWords[i-4].byte3);
                    aux.byte4 = (byte) (arrayWords[i-1].byte4 ^ arrayWords[i-4].byte4);
                    arrayWords[i] = aux;
                }
            }
            ++i;
        }
    }
    
    public static void encrypt()
    {
        // Initial round
        word[] state = new word[4];
        for (int i = 0; i < 4; ++i) 
        {
            word aux = new word();
            aux.byte1 = block[i*4];
            aux.byte2 = block[i*4 + 1];
            aux.byte3 = block[i*4 + 2];
            aux.byte4 = block[i*4 + 3];
            state[i] = aux;
        }
        System.out.println("ROUND 0");
        System.out.println("-------");
        System.out.println("START OF ROUND");
        printWords(state);
        System.out.println("ROUND KEY");
        int index = 0;
        printWords(Arrays.copyOfRange(arrayWords, index, index+4));
        index += 4;
        state = addRoundKey(state,0);
        // 9 main rounds
        for (int round = 1; round < Nr; ++round)
        {
            System.out.println("");
            System.out.println("ROUND " + round);
            System.out.println("-------");
            System.out.println("START OF ROUND");
            printWords(state);
            state = subBytes(state);
            System.out.println("AFTER SUBBYTES");
            printWords(state);
            state = shiftRows(state);
            System.out.println("AFTER SHIFTROWS");
            printWords(state);
            state = mixColumns(state);
            System.out.println("AFTER MIXCOLUMNS");
            printWords(state);
            System.out.println("ROUND KEY");
            printWords(Arrays.copyOfRange(arrayWords, index, index+4));
            index += 4;
            state = addRoundKey(state,round);
        }
        // Final round
        System.out.println("");
        System.out.println("ROUND 10");
        System.out.println("--------");
        System.out.println("START OF ROUND");
        printWords(state);
        state = subBytes(state);
        System.out.println("AFTER SUBBYTES");
        printWords(state);
        state = shiftRows(state);
        System.out.println("AFTER SHIFTROWS");
        printWords(state);
        System.out.println("ROUND KEY");
        printWords(Arrays.copyOfRange(arrayWords, index, index+4));
        state = addRoundKey(state,Nr);
        System.out.println("");
        System.out.println("CIPHERTEXT");
        System.out.println("----------");
        printWords(state);
    }
    
    public static void main(String[] args) 
    {
        block = new byte[] {(byte) 0x32,(byte) 0x43,(byte) 0xf6, (byte) 0xa8,(byte) 0x88,(byte) 0x5a,(byte) 0x30,(byte) 0x8d,(byte) 0x31, (byte) 0x31, (byte) 0x98,(byte) 0xa2,(byte) 0xe0,(byte) 0x37, (byte) 0x07, (byte) 0x34};
        key = new byte[] {(byte) 0x2b, (byte) 0x7e,(byte) 0x15,(byte) 0x16,(byte) 0x28,(byte) 0xae,(byte) 0xd2,(byte) 0xa6,(byte) 0xab,(byte) 0xf7,(byte) 0x15,(byte) 0x88,(byte) 0x09,(byte) 0xcf,(byte) 0x4f,(byte) 0x3c};
        Nb = 4; // number of bits of the block divided by 32 (16 bytes => 128 bits, 128/32 = 4)
        Nk = 4; // number of bits of the key divided by 32 (16 bytes => 128 bits, 128/32 = 4)
        Nr = 10; // number of rounds
        keyExpansion();
        encrypt();
    }
}