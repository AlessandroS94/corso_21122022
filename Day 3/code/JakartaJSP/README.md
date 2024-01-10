# Jakarta JSP

Questo progetto mira ad esplorare la basi di un progetto che sfrutta il concetto di servlet all'interno di un progetto 
Java Enterprise Edition (JEE) o meglio nota come Jakarta.
Il nome Jakarta proviene dalle nuove versioni di java che hanno deciso di rinominare JEEE in Jakarta.

## Setup 

### Esempio progetto con server TOMCAT versione 10.0.27
Questo progetto sfrutta il design pattern delle facedes e MVC. Inoltre sfrutta le tecnologie HTML, MSQL, JSP e JAKARTA.

### SETUP
Nel file User DAO sono riportate le impostazioni per potersi collegare al server MSQL. Variare quindi l'istruzione
``` java
private String jdbcURL = "jdbc:mysql://localhost:8889/demo?useSSL=false"; 
// variare 8889 con il proprio indirizzo server msql e demo con il nome del proprio database
private String jdbcUsername = "diemme";
private String jdbcPassword = "";
// variare  jdbcUsername e jdbcPassword con i valori del proprio server msql
```


Prima di avviare il progetto importare il file "localhost.sql" che contiene il database con le tabelle.
Ora aprendo il progetto con IntelliJ CE o Ultimate potrete avviare in automatico il progetto dato che
il file di configuarazione sono state incluse nel progetto git.
Si raccomanda di aprire il progetto con IntelliJ dalla cartella Esempio2 altrimenti non si avranno le impostazioni
ereditate.

### In esecuzione

![Screenshot 2024-01-10 alle 08.21.07.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fbl%2Fc45fyd3n15d7_xs9dg8q37g40000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_HUcJM8%2FScreenshot%202024-01-10%20alle%2008.21.07.png)
![Screenshot 2024-01-10 alle 08.21.48.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fbl%2Fc45fyd3n15d7_xs9dg8q37g40000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_JP3ANs%2FScreenshot%202024-01-10%20alle%2008.21.48.png)