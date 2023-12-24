# Esempio

## Esempio progetto con server TOMCAT versione 10.0.27
Questo progetto sfrutta il design pattern delle facedes e MVC. Inoltre sfrutta le tecnologie HTML, MSQL, JSP e JAKARTA.

## SETUP
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

## ENDPOINT
http://localhost:8080/Esempio2_war_exploded/
http://localhost:8080/Esempio2_war_exploded//hello-servlet
http://localhost:8080/Esempio2_war_exploded/ServletInserUser