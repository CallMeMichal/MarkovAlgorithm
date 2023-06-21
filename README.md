Napisz program generujący tekst z podanej sekwencji początkowej.
Dane treningowe to plik tekstowy.
Na podstawie tekstu uczącego program uczy się prawdopodobieństwa wystąpienia następnego znaku na podstawie poprzednich n znaków, zwanymi n-gramami.

Na przykład biorąc pod uwagę tekst uczący:

aabbbaabcde

Jeśli weźmiemy pod uwagę n=0, to prawdopodobieństwo znaku „a” wynosi 4/11, a prawdopodobieństwo „c” wynosi 1/11.

Jeśli n=1 (unigram), to biorąc pod uwagę początkowy znak „a”, prawdopodobieństwo, że następnym znakiem jest „b”, wynosi 1/2,  prawdopodobieństwo kolejnego znaku „a” również wynosi 1/2, ale prawdopodobieństwo c wynosi 0, ponieważ istnieje nie ma takiego c, które następuje po a w tej sekwencji.

jeśli n=2 (bigram) i sekwencja początkowa to „ab”, to prawdopodobieństwo „a” wynosi 0, „b” wynosi 1/2, a c również wynosi 1/2.

Za każdym razem, gdy program generuje nowy znak, bierze pod uwagę n poprzednich znaków.
Jeśli dwa lub więcej znaków ma takie samo prawdopodobieństwo, wybierz spośród nich losowo następny znak

(1p) napisać program generujący sekwencję z podanej sekwencji początkowej w sposób opisany powyżej (losowy znak w każdym przypadku opisanym poniżej). Należy wziąć pod uwagę wszystkie znaki łącznie ze znakami interpunkcyjnymi.
(1p) jeśli sekwencja powtarza ten sam znak, wybierz następny najbardziej prawdopodobny znak
(1p) jeśli w tekście nie ma takiego n-gramu, spróbuj n-1-gram i tak dalej, aż znajdziesz znak.

n, długość sekwencji do wygenerowania oraz sekwencja startowa jest podawana przez użytkownika

Przykład tekstu uczącego (fragment z Władcy Pierścieni J.R.R. Tolkiena):

Three rings for the Elven-kings under the sky,
Seven for the Dwarf-lords in their halls of stone,
Nine for mortal men doomed to die,
One for the Dark Lord on his dark throne;
In the Land of Mordor where the shadows lie.
 
One ring to rule them all, one ring to find them,
One ring to bring them all, and in the darkness bind them;
In the Land of Mordor where the shadows lie.
