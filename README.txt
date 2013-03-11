NICULAESCU OANA 321CB

Tema contine clasele Container.java, Type.java si Main.java.
Clasa Type.java:
Implementarea unei structuri ajutatoare in care retinem un cuvant din dictionar si frecventa cu care acesta apare. Am folosit structura pentru a retine structura dictionarului in ordine lexicografica, initializam o variabila dic de tipul acestei structuri, in care vom retine toate cuvintele din dictionar.

Clasa Container.java:
Implementarea unei structuri ajutatoare in care vom retine distanta Levenshtein, numarul de cuvinte obtinute pana intr-un anumit punct, frecventa cuvintelor precum si cuvantul obtinut pana intr-un punct. Utilizez o structura de tip Container in momentul in care declar o matrice superior triunghiulara in care computez sirul corect dupa metoda prezentata in laborator pentru parantezarea matricelor.

Clasa Main.java:
Clasa principala, contine o metoda compute(String str1, String str2) unde se calculeaza distanta levenshtein dintre cuvantul ce se doreste corectat si fiecare cuvant din dictionar. Initializam o matrice superior triunghiulara dupa modelul celei de mai jos:
s o r e s e r v e d
| o r e s e r v e d
| | r e s e r v e d
| | | e s e r v e d
| | | | s e r v e d
| | | | | e r v e d
| | | | | | r v e d
| | | | | | | v e d
| | | | | | | | e d
| | | | | | | | | d

Aceasta este o matrice de containere, in fiecare container retinand distanta minima dintre cuvantul cautat si cel mai apropiat matching, cuvantul(matchingul) gasit, frecventa de aparitie a matchingului si numarul de cuvinte ce formeaza sirul nostru cautat, initial este 1 cuvant. 
Parcurgem matricea superior triunghiulara deplasandu-ne spre coltul dreapta sus si verificam daca cele 4 conditii sunt indeplinite pe rand concatenand in acelasi timp sirul obtinut. In coltul din dreapta sus am obtinut sirul corectat.

Pentru rezolvarea acestei teme am utilizat tehnica programarii dinamice cu memoizare, matricea superior triunghiulara calculata initial unde pastram rezultatele de la pasul anterior.

Complexitatea algoritmilor utilizati:
- metoda compute ce calculeaza matricea Levenshtein si care intoarce doar ultima linie din aceasta matrice(singura relevanta pentru noi in acest caz):
O(m * n), unde m si n sunt lungimile celor doua stringuri trimise ca parametru;
- complexitatea generarii stringului corectat este O(n^3), fiind folosita tehnica programarii dinamice si a memoizarii.
