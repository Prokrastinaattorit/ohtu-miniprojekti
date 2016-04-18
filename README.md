# ohtu-miniprojekti
[![Build Status](https://travis-ci.org/Prokrastinaattorit/ohtu-miniprojekti.svg?branch=master)](https://travis-ci.org/Prokrastinaattorit/ohtu-miniprojekti) [![Coverage Status](
https://coveralls.io/repos/github/Prokrastinaattorit/ohtu-miniprojekti/badge.svg?branch=master
)](
https://coveralls.io/github/Prokrastinaattorit/ohtu-miniprojekti?branch=master
)

Ohjelmistotuotantokurssin miniprojekti, kevät 2016.

Sovellus:  https://bibtexinator.herokuapp.com/bibtexinator

Backlog: https://trello.com/b/I76kFLET/ohtuminiproju

**HUOMIO!** Travis ei ainakaan toistaiseksi aja easyb-testejä, niiden ajamiseksi suorita:
```
mvn exec:java
```
Ja toisella komentorivillä (ohjelman käynnistyttyä):
```
mvn integration-test
```
Tämä ajaa sekä tavalliset testit että easyb-testit.
