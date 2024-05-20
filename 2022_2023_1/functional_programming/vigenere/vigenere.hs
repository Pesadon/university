module Vigenere where

type VigenereKey = String


-- Első feladat

infiniteKey :: VigenereKey -> String
infiniteKey key = key ++ infiniteKey key

codeTable :: [[Char]]
codeTable = [shift n ['A'..'Z'] | n <- [0..25]]
    where shift n xs = drop n xs ++ take n xs


-- Második feladat

contains :: String -> Char -> Bool
contains [] _ = False
contains (x:xs) c
    | x == c = True
    | otherwise = contains xs c

charEncode :: Char -> Char -> Char
charEncode key c
    | not (contains (codeTable !! 0) c) = c
    | otherwise = codeTable !! (fromEnum key - fromEnum 'A') !! (fromEnum c - fromEnum 'A')

findIndex' :: String -> Char -> Int
findIndex' [] _ = -1
findIndex' (x:xs) c
   | x == c = 0
   | otherwise = 1 + findIndex' xs c

intToChar :: Int -> Char
intToChar n = codeTable !! 0 !! n

charDecode :: Char -> Char -> Char
charDecode key c
    | not (contains (codeTable !! 0) c) || not (contains (codeTable !! 0) key)= c
    | otherwise = intToChar (findIndex' (codeTable !! (fromEnum key - fromEnum 'A')) (c))


-- Harmadik feladat


removeNonLetters :: String -> String
removeNonLetters [] = []
removeNonLetters (x:xs)
    | contains (codeTable !! 0) x = x : removeNonLetters xs
    | otherwise = removeNonLetters xs

isNotLetter :: Char -> Bool
isNotLetter c
    | c >= '0' && c <= '9' = True
    | c == ' ' || c == '\'' || c == ',' || c == '*' = True
    | otherwise = False

encode :: VigenereKey -> String -> String
encode key str = encode' (removeNonLetters (infiniteKey key)) str
    where encode' _ [] = []
          encode' (k:ks) (c:cs)
            | isNotLetter c = c : encode' (k:ks) cs
            | otherwise = charEncode k c : encode' ks cs

decode :: VigenereKey -> String -> String
decode key str = decode' (removeNonLetters(infiniteKey key)) str
    where decode' _ [] = []
          decode' (k:ks) (c:cs)
            | isNotLetter c= c : decode' (k:ks) cs
            | otherwise = charDecode k c : decode' ks cs