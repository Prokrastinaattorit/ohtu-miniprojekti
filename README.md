# ohtu-miniprojekti
[![Build Status](https://travis-ci.org/Prokrastinaattorit/ohtu-miniprojekti.svg?branch=master)](https://travis-ci.org/Prokrastinaattorit/ohtu-miniprojekti)

Ohjelmistotuotantokurssin miniprojekti, kevät 2016.

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
