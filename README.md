# test-exercices
1. Loo uus Sring Boot 3.x rakendus:
	1. Java 17
	2. Maven
2. Loo objekt kolme täisarvulise atribuudiga, mis kajastavad kahte liidetavat ja nende summat
3. Loo kontroller kahe teenusega
	1. arvude liitmise teenus
		1. meetod: GET
		2. sisendid: võtab sisse kaks täisarvu
			1. mõlemad peavad olema väärtustatud
			2. lubatud väärtused: 0 - 100 (kaasa arvatud)
		3. väljund: loob eelmises punktis mainitud objekti (kaks liidetavat ja summa) ja tagastab selle teenuse väljundis
	2. liitmiste otsimise teenus
		1. meetod: GET
		2. sisendid: 
			1. täisarv: mittekohustuslik, lubatud värtused: 0 - 100 (kaasa arvatud)
			2. järjestust määrav atribuut: kohustuslik (peab võimaldama valida, kas järjestus tuleb kasvav või kahanev, atribuudi tüüp ei ole oluline)
		3. väljund: 
			1. kui sisendisse esitati täisarv, siis otsi varasemate liitmistehete hulgast need liitmised, kus üks liidetavatest või summa langeb selle arvuga kokku
			2. vastavalt järjestamise atribuudile järjesta summa järgi kas kasvavalt või kahanevalt
