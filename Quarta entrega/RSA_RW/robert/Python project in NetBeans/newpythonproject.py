from Crypto.PublicKey import RSA
from Crypto.PublicKey.RSA import importKey
from fractions import gcd
from glob import glob

#openssl rsa -in robert.almar_pubkeyRSA_RW.pem -pubin -text -modulus > salida.txt
#Modulus=CA12368EA56434AA4C232B344471C65991C07E52F589B39EAB12EEDB15110AE6160BD89659F39FA91DE360C142612B5E8DBE6951E391C7C3DF4EF25B17A20970AAE868888BA3F95954D29784486C6B6472F36DBE4362A9639ED1E0689B9A1EE66B2A2BE3264CE2CCC1803DFA28C477366565805F0E2601B24D59797F944D2551D8AEDB3C0F9A3A0ADA35C144B68EB6C060ED1E700A8D5C8A6642EC84D1EAE24E096C4D4A744DB5F553EE6A26CD225E0B3A3777297A80795FAAED32DDBD4141785162FF32A2A80DB9194BCB6E9F1F92372C3816F339DDD01946023FAC24ECBF5705E77D7F450E1A8BC9ADCAE800CFB3088734D96AA0E0028D74E0A3BBC13498D5

n = 25509118824444283334924238339215975861354741342567549684006293740733055936053085558304718450880881140632514998164663201026006294267879021999965900658456793321309832373946843545433434608945947100763639325349617855382857791733036968166348073557610503832820083270120793009868488576749082052747438702463553447919878614098905947299396039024516971684484371205311448164763235350682961439128337260967988424579255079670951844444439821564722745893711208790072603827599849863629332754606150284278162024169069250835819332069830673986628616149538186972847779238413168501895650346347273316786261268442747974383673475010303083452629
e = 65537

'''
https://news.ycombinator.com/item?id=3591429
Posting what I said in the other thread: As far as I can tell, this is what they're doing: if two different keys have a factor in common (i.e. A=PQ, B=PR), then you
can use Euclid's algorithm (which just requires repeated subtraction, and is thus really easy) to find P (=gcd(A,B)), and then just use division to find Q (=A/P) and R (=B/P) easily.
So what the researchers did, apparently, was to gather all the RSA public keys they could find (6 million or so) and then calculate the gcds of all pairs of keys.
Whenever they found a gcd that wasn't equal to 1, they'd cracked (at least) 2 keys.
'''

for filename in glob('*pubkeyRSA_RW*.pem'):
    f = open(filename)
    key = importKey(f.read())
    if gcd(n, key.n) != 1:
        print (f.name)
        print ("key.n = " + str(key.n))
        p = gcd(n, key.n)
        break
        
print ("p = " + str(p))
d = int(input("d = "))
privateKey = RSA.construct((n,e,d))
f = open('key.pem', 'wb')
f.write(privateKey.exportKey('PEM'))
f.close
print("The private key has been generated")

'''
Output
------
arnau.bago_pubkeyRSA_RW.pem
key.n = 26046456260781993432232176983543263666433698762035878077113540612258528897289266463277192386796710768930490078363112347457798666873349905923691957384922827910663311034716245980787164388936300331717090319642121476604353618531563045988687653355678579745660627656797438310307813442602405029179870792728933400654958687674400060656707659041549499872011167863180647786898911145837910160009102554487354197114303554842605516784834845200178428580451401097011105100835506244670205176010937986449077146644519853869359090182710953099618275646852772414774643201342464190644707921408583889452684810858474713276619200348438021935713
p = 153711510173494016430328761369292348914610856584712012899745683273464230743076543040978833369024102681734676415544282245464012678404070499828880786574935029482116792676799455429798948976166204630394515432599859050207287151349801350577419816837316027805972679206360786623313633642299259407865758341520355262377
d = 5144872859934152266979394576617128781228725316478597917560998987823817589495242151909177540683026182414217667054343625603273741514454810455094820863077069351985494671999471718014848690984444341027111167775016384827663980516765836782623385816935405033221170646573029616925426583570645079469851301847250400149544992533440291822648366442778143171431951729099928244396944516666838171241885054689539453659247981365192040453923666762768917074336810206284861048511350245271851091239281242148337815722388271430145086558225763649394536055637692056186801954795209746829416824714372623616573122374490439187023181050412010342337
The private key has been generated
'''

'''
SageMath
--------
Modulus = "CA12368EA56434AA4C232B344471C65991C07E52F589B39EAB12EEDB15110AE6160BD89659F39FA91DE360C142612B5E8DBE6951E391C7C3DF4EF25B17A20970AAE868888BA3F95954D29784486C6B6472F36DBE4362A9639ED1E0689B9A1EE66B2A2BE3264CE2CCC1803DFA28C477366565805F0E2601B24D59797F944D2551D8AEDB3C0F9A3A0ADA35C144B68EB6C060ED1E700A8D5C8A6642EC84D1EAE24E096C4D4A744DB5F553EE6A26CD225E0B3A3777297A80795FAAED32DDBD4141785162FF32A2A80DB9194BCB6E9F1F92372C3816F339DDD01946023FAC24ECBF5705E77D7F450E1A8BC9ADCAE800CFB3088734D96AA0E0028D74E0A3BBC13498D5"
n = long(Modulus,16)
n
p = long(153711510173494016430328761369292348914610856584712012899745683273464230743076543040978833369024102681734676415544282245464012678404070499828880786574935029482116792676799455429798948976166204630394515432599859050207287151349801350577419816837316027805972679206360786623313633642299259407865758341520355262377)
q = n/p
q
e = long(65537)
# n = p * q => fiDeN = (p-1) * (q-1)
fiDeN = (p-1)*(q-1)
d = long(inverse_mod(e,fiDeN))
d

25509118824444283334924238339215975861354741342567549684006293740733055936053085558304718450880881140632514998164663201026006294267879021999965900658456793321309832373946843545433434608945947100763639325349617855382857791733036968166348073557610503832820083270120793009868488576749082052747438702463553447919878614098905947299396039024516971684484371205311448164763235350682961439128337260967988424579255079670951844444439821564722745893711208790072603827599849863629332754606150284278162024169069250835819332069830673986628616149538186972847779238413168501895650346347273316786261268442747974383673475010303083452629L
165954513072262241892477432133065246741206825499167884875855169845979790570203914387264760338228029892032211512872287039671313159261478546765818778936220551776108005616082954232262649334364678470713170566233548157001620307136227634199967553561503769447561579888239006637501067917638916746125600123098666924877L
5144872859934152266979394576617128781228725316478597917560998987823817589495242151909177540683026182414217667054343625603273741514454810455094820863077069351985494671999471718014848690984444341027111167775016384827663980516765836782623385816935405033221170646573029616925426583570645079469851301847250400149544992533440291822648366442778143171431951729099928244396944516666838171241885054689539453659247981365192040453923666762768917074336810206284861048511350245271851091239281242148337815722388271430145086558225763649394536055637692056186801954795209746829416824714372623616573122374490439187023181050412010342337L
'''

#openssl rsautl -decrypt -inkey key.pem -in robert.almar_RSA_RW.enc -out descifrado.txt
#robert.almar 65537 11_04_15_21_02