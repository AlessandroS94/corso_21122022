# DEMO 0
### Progetto base
Il progetto seguente contiene delle semplici dimostrazione di utilizzo e creazione di un progetto SpringBoot senza l'utilizzo di persistenza.
Si introduce l'idea di CRUD e quindi di accesso alle risorse.
Mancano inoltre una strutturazione più complessa dei pattern.

## Descrizione
Semplice applicativo server contente come DAO Persona ed delle API REST per poter aggiungere persone al gruppo e gestire le persone.
Non contiene nessun sistema di persistenza, ma solo la componente tomcat per le risposte REST.

## ISTRUZIONI
NB: Utilizzare Postman importando le chiamate all'interno del progetto per poter gestire le chiamate.

Come avviare il progetto:
  1. Aprire questa directory con Intellj IDE.
  2. Attendere che maven interno all'IDE scarichi tutte le dipendenze.
  3. Avviare Demo0Application
  4. Testare le api con Postman
<br>
OPPURE
  Se già presente nel sistema maven eseguire il seguente comando maven:

```sh
 $ mvn spring-boot:run
```
e poi testare le api con Postman.
