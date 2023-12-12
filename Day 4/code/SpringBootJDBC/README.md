# SPRINGBOOTJDBC
## Progetto base
Il progetto seguente contiene delle semplici dimostrazione di utilizzo di Springboot 3 con le librerie JDBC. 
Il progetto è composto in diversi package per sfruttare il pattern delle facade. 
Le componenti sono:
1. Business per includere tutte le interfacce e implementazioni dellalogica di business.
2. Configuration per includere tutte le classi necessarie alla configurazione dell'applicazione.
3. Controller per poter fornire le risposte json alle chiamate
4. DAO per include tutte le chiamate dei preperament statement necessari 
5. Domain per definire il dominio delle classi da utilizzare 
6. Mapper per poter includere tutte le classi necessarie alla deserializzazione delle informazioni provenienti dal DB

Il Demo3Application.java contiene il la funzione main per poter avviare l'applicazione server descritta.