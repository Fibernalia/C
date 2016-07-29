from Crypto.Cipher import AES

key = open("robert.key", 'rb').read()
enc = open("robert.enc", 'rb').read()
iv = enc[:AES.block_size]
cipher = AES.new(key, AES.MODE_CBC, iv)
plaintext = cipher.decrypt(enc[AES.block_size:])
open('soluciones/robert.html', 'wb').write(plaintext)

key = open("alvaro.key", 'rb').read()
enc = open("alvaro.enc", 'rb').read()
iv = enc[:AES.block_size]
cipher = AES.new(key, AES.MODE_OFB, iv)
plaintext = cipher.decrypt(enc[AES.block_size:])
open('soluciones/alvaro.jpeg', 'wb').write(plaintext)

key = open("daniel.key", 'rb').read()
enc = open("daniel.enc", 'rb').read()
iv = enc[:AES.block_size]
cipher = AES.new(key, AES.MODE_OFB, iv)
plaintext = cipher.decrypt(enc[AES.block_size:])
open('soluciones/daniel.jpg', 'wb').write(plaintext)