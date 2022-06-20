# Käsk testide käivitamiseks

 `gradle build test`



# Testlood 


| Testlugu | Kirjeldus| Tulemus|
| --- | --- | --- |
|**Happy-path**| Klient täidab laenutaotluse.|OK |
|**Non-happy path**| Klient täidab laenutaotluse, vahepeal kustutab sisendid ning navigeerib  kliendiandmete töötlemise põhimõtete lehele ja tagasi.|FAIL |
|**UI elementide validatsioon**| Pealdised, tekstid, elemendid on nähtavad| OK |

## Veakirjeldus

Laenutaotlust täites ja kliendiandmete töötlemise põhimõtete lingile vajutades suunatakse vastavale alamlehele, tagasi laenuvormi lehele liikudes sisestatud laenusumma, neto sissetulek jt pole salvestunud ning taotlusel on vaikimisi väärtused.

