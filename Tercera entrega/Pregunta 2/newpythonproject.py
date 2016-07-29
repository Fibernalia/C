from Crypto.Cipher import AES

#KS=random(16)
#kiv=random(1) -> possible values: 0x00 to 0xFF
#for i in range(0,16) {IV[i]=KS[i]^kiv} -> KS[i] = IV[i]^kiv
#aes_encryptor = AES.new(KS, AES.MODE_CBC,IV)
#cryptogram = aes_encryptor.encrypt(Message)
#result = IV || cryptogram -> IV = c0 = c[0:16], cryptogram = c[16:]
#open("file.enc",'wb').write(result)

'''
enc = open("robert.puerta_trasera.enc", 'rb').read()
iv = enc[:AES.block_size]
cryptogram = enc[AES.block_size:]
for kiv in range(256):
    key = bytes(list(map(lambda b : b ^ kiv, iv)))
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plaintext = cipher.decrypt(cryptogram)
    print(plaintext)
    another = input("Do you wanna try with another key?  Y | N  ")
    if another == "Y" and kiv == 255:
        print("No more candidate keys")
    if another == "N":
        print ("kiv = " + str(kiv)) # kiv = 113
        open('soluciones/robert.html', 'wb').write(plaintext)
        break
''' 

enc = open("daniel.puerta_trasera.enc", 'rb').read()
iv = enc[:AES.block_size]
cryptogram = enc[AES.block_size:]
for kiv in range(256):
    key = bytes(list(map(lambda b : b ^ kiv, iv)))
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plaintext = cipher.decrypt(cryptogram)
    open('soluciones/daniel' + str(kiv) + '.jpg', 'wb').write(plaintext) # kiv = 24
    
enc = open("alvaro.puerta_trasera.enc", 'rb').read()
iv = enc[:AES.block_size]
cryptogram = enc[AES.block_size:]
for kiv in range(256):
    key = bytes(list(map(lambda b : b ^ kiv, iv)))
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plaintext = cipher.decrypt(cryptogram)
    open('soluciones/alvaro' + str(kiv) + '.jpg', 'wb').write(plaintext) # kiv = 129
