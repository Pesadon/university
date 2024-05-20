Vigenére rejtjel implementálása

Feladat ismertetése

A feladat során a Vigenére rejtjel kódolását és dekódolását fogjuk implementálni, ennek is az angol 26
karakteres ábécé variánsát. A rejtjel a kódoláshoz egy kulcsot és egy 26 x 26-os mátrixot használ, amely
minden sorban az egész angol ábécét tartalmazza, soronként egy-el balra eltolva:

A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
B C D E F G H I J K L M N O P Q R S T U V W X Y Z A
C D E F G H I J K L M N O P Q R S T U V W X Y Z A B
D E F G H I J K L M N O P Q R S T U V W X Y Z A B C
E F G H I J K L M N O P Q R S T U V W X Y Z A B C D
.
.
.
Z A B C D E F G H I J K L M N O P Q R S T U V W X Y

A kódolandó karakterláncunk n-edik karakterének kódolásához szükségünk van a megadott kulcs n-edik
karakterére is. Ha a kulcs túl rövid, és nincs n-edik karaktere, akkor az indexelést a kulcs elejéről folytassuk,
például, ha a kulcs az ALMA szó, és ennek kéne a 6. karaktere akkor:

1. - A
2. - L
3. - M
4. - A
5. - A -- folytatjuk az elejéről
6. - L -- a kulcs "6. karaktere" az L

A kulcsból kapott karakter angol ábécé beli pozíciója alapján állapíthatjuk meg, melyik oszlopban lesz a
kódolt karakter (ha a kapott karakter például az L, akkor a 12. oszlopot válasszuk, mert az L a 12. eleme az
angol ábécének), a sort pedig a kódolandó szövegünkből származó karakterből, ugyanezzel a módszerrel.

A kulcsot a feladat folyamán egy stringnek a típusszinonímájával fogjuk reprezentálni:

type VigenereKey = String

Egy teljes levezetett kódolás

Kódoljuk a BANANA szót az APPLE kulcs segítségével:

Kulcs Karaktere		Kódolandó Karakter		Magyarázat			Eredmény
A 			B 			B alapján 2. sor, A alapján 1. oszlop 	B
P 			A 			A alapján 1. sor, P alapján 16. oszlop 	P
P 			N 			N alapján 13. sor, P alapján 16. oszlop C
L 			A 			A alapján 1. sor, L alapján 12. oszlop 	L
E 			N 			N alapján 13. sor, E alapján 5. oszlop 	R
A 			A 			Kulcs kezdés elejéről! A alapján 1. sor és 1. oszlop A


Első Feladat: Végtelen kulcs és a Kódtábla

Definiáljuk az infiniteKey ami egy VigenereKey karaktereit ismétli egymás után!

infiniteKey :: VigenereKey -> String

Néhány teszteset:

infiniteKey "APPLE" !! 5 == 'A'
take 5 (drop 5 (infiniteKey "APPLE")) == "APPLE"
infiniteKey ['A'..'Z'] !! 53 == 'B'

Definiáljuk a codeTable konstans függvényt, amely egy karakter mátrixot ad vissza, ami soronként az egész
angol ábécét tartalmazza, minden sor egyel eltolva. A táblához mintát a feladat ismertetésénél lehet látni. A
táblázatnak elég csak az angol ábécé 26 betűjének a nagybetűs változatát tartalmazni!

codeTable :: [[Char]]

Néhány teszteset:

codeTable !! 0 !! 1 == 'B'
codeTable !! 10 !! 11 == codeTable !! 11 !! 10
codeTable !! 25 !! 0 == 'Z'


Második Feladat: Egy karakter kódolása és dekódolása

Definiáljuk a charEncode és charDecode függvényeket, amelyek első paraméterként a kulcs egy karakterét,
második paraméterként pedig a kódolandó/dekódolandó karaktert kapják, és visszaadják a dekódolt/kódolt
karaktert. Ha az egyik megadott karakter nem szerepel a kód táblázatban, akkor adjuk vissza az eredeti
karaktert! A függvényeknek elég csak az angol ábécé 26 karakterének a nagybetűs változatára működnie!

charEncode :: Char -> Char -> Char
charDecode :: Char -> Char -> Char

Néhány teszteset:

charEncode 'A' 'B' == 'B'
charEncode 'P' 'A' == 'P'
charEncode 'P' 'N' == 'C'
charDecode 'L' 'L' == 'A'
charDecode 'E' 'R' == 'N'
charDecode 'A' 'A' == 'A'
charEncode '?' 'A' == 'A'
charDecode '?' 'B' == 'B'


Harmadik Feladat: Szöveg kódolás és dekódolás

Definiáljuk az encode és decode függvényeket, amelyek kapnak egy VigenereKey típusú kulcsot és egy String
típusú kódolandó/dekódolandó értéket, amelyet a kulcs alapján kódolnak/dekódolnak! Olyan karakterek esetén
amelyek nem szerepelnek a kód táblázatban, az adott karakterrel ne csináljunk semmit (ne is használjuk el rá
a kulcs egy elemét, illetve ha a kulcsban van, ne használjunk fel rá egy kódolandó karaktert)! A függvényeknek
elég csak az angol ábécé 26 karakterének a nagybetűs változatára működnie!

encode :: VigenereKey -> String -> String
decode :: VigenereKey -> String -> String

Néhány teszteset:

encode "VIGENERE" "HELLO WORLD IN VIGENERE" == "CMRPB AFVGL OR IMXIIMXI"
encode "DUCK" "DUCK GOES QUACK" == "GOEU JIGC TOCMN"
encode "FALSE" "TWO PLUS TWO IS FIVE" == "YWZ HPZS EOS NS QAZJ"
decode "HASKELL" "OAKUIWW PS S QSZO WRGQVLXTIFQ PLYNUSQI" == "HASKELL IS A GOOD PROGRAMMING LANGUAGE"
decode "TRUE" "MNI TELM XPF CW YFOV" == "TWO PLUS TWO IS FOUR"
decode "BELLASO" "CIWWAKC JRGPNLSE XSTS UWQLPC" == "BELLASO INVENTED THIS CIPHER"