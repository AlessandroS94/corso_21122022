## Corso JAVA JEE / Spring

| Tecnologia | Badge |
|------------|-------|
| Java  17   | ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) |
| Spring Boot| ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot) |
| JEE        | ![JEE](https://img.shields.io/badge/JEE-25A162?style=for-the-badge&logo=jee&logoColor=white) |

### Day
**Corso introduttivo Servlet, Sprinboot Rest e Servlet - 10 giorni**

| Giorno    | Contenuto                                       | Tecnologia                              |
|-----------|-------------------------------------------------|-----------------------------------------|
| Day 1 - 3 | Corso introduttivo Servlet                      | ![java](./img/java.jpg)            |
| Day 4 - 10| Corso Spring                                    | ![springboot](./img/springboot.png)     |

## Primi passi
Scaricare la repo per seguire le prime fasi del corso 
```
$ git clone https://github.com/AlessandroS94/corso_21122022.git
$ cd corso_21122022
```

## Technologies
Project is created with:
* Java 17 LTS 
* Mysql -> Xamp - Mamp - MySQL Workbench
* Intellij IDE : IDE

## Setup
Eseguire i seguenti setup:
<details>
<summary> <h4>Windows</h4></summary>

* Installare JDK 17 LTS [https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  ![Installer](./img/installer.png)

  *.* Verifica che la JDK sia stata installata correttamente: vai alla cartella in cui hai installato la JDK (ad esempio, "C:\Program Files\Java\jdk1...") e controlla che la cartella esista e che contenga le sottocartelle "bin", "jre", "lib", ecc.

  Imposta la variabile d'ambiente JAVA_HOME:
  Apri il Pannello di controllo di Windows e vai su Sistema > Impostazioni avanzate di sistema > Variabili d'ambiente.
  Nella sezione "Variabili sistema", cerca la variabile "JAVA_HOME" e selezionala.
  Clicca sul pulsante "Modifica" e inserisci il percorso della cartella di installazione della JDK (ad esempio, "C:\Program Files\Java\jdk1....") come valore della variabile.
  Clicca su OK per salvare le modifiche.

  Aggiorna la variabile PATH: per aggiungere il percorso della cartella "bin" della JDK alla variabile PATH, cerca la variabile PATH nella sezione "Variabili sistema" delle impostazioni delle variabili d'ambiente e selezionala. Clicca sul pulsante "Modifica" e aggiungi il percorso della cartella "bin" della JDK (ad esempio, "%JAVA_HOME%\bin") alla fine del valore esistente della variabile. Clicca su OK per salvare le modifiche.

  Riavvia il prompt dei comandi: dopo aver apportato modifiche alle variabili d'ambiente, è necessario riavviare il prompt dei comandi per rendere effettive le modifiche.

* Installare Tomcat [https://archive.apache.org/dist/tomcat/tomcat-10/v10.0.27/src/](https://archive.apache.org/dist/tomcat/tomcat-10/v10.0.27/src/)
  ![Tomcat](./img/Tomcat.png)
  Eseguire l' unzip e spostare il contenuto nel workspace del progetto
* Installare maven (Opzionale se si usa Sts o IntelliJ)
Scarica dal seguente link [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
maven. Scaricare l'ultima versione bisognerà adattare poi la versione che riportata nelle immagini con quella scaricata.
Seguire i seguenti step:
![fase1](img/fase1.png)
![fase2](img/fase2.png)
![fase3](img/fase3.png)
![fase4](img/fase4.png)
![fase5](img/fase5.png)
![fase6](img/fase6.png)
![fase7](img/fase7.png)
![fase8](img/fase8.png)
![fase9](img/fase9.png)
![fase10](img/fase10.png)
```
$ mvn -version
```

</details>

<details>
<summary> <h4>MacOS</h4></summary>

Utilizzare homebrew

* Installare JDK 17 LTS 
```
$ brew install openjdk@17
$ echo 'export PATH="/usr/local/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
$ export CPPFLAGS="-I/usr/local/opt/openjdk@17/include"  
$ javac -version
(controllo della visibilità)
```

* Installare Tomcat [https://tomcat.apache.org/download-10.cgi](https://tomcat.apache.org/download-10.cgi)
  ![Tomcat](./img/Tomcat.png)
  Eseguire l' unzip e spostare il contenuto nel workspace del progetto

* Installare maven (Opzionale se si usa Sts o IntelliJ)
```
$ brew install maven
```
</details>
<details>
<summary> <h4>Linux</h4></summary>

Seguire i seguenti comandi

```
$ sudo su -
$ wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.rpm
$ rpm -ivh jdk-17_linux-x64_bin.rpm
$ sudo apt update
$ sudo apt install -y libc6-x32 libc6-i386
$ wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb
$ sudo dpkg -i jdk-17_linux-x64_bin.deb
$ java -version
```
* Installare Tomcat [https://tomcat.apache.org/download-10.cgi](https://tomcat.apache.org/download-10.cgi)
  ![Tomcat](./img/Tomcat.png)
  Eseguire l' unzip e spostare il contenuto nel workspace del progetto

* Installare maven (Opzionale se si usa Sts o IntelliJ)
```
$ sudo apt install install maven
```

</details>
<details>
<summary> <h4>Linux Ubuntu</h4></summary>

```
$ apt install openjdk-17-jdk openjdk-17-jre
```


* Installare Tomcat [https://tomcat.apache.org/download-10.cgi](https://tomcat.apache.org/download-10.cgi)
  ![Tomcat](./img/Tomcat.png)
  Eseguire l' unzip e spostare il contenuto nel workspace del progetto

* Installare maven (Opzionale se si usa Sts o IntelliJ)
```
$ sudo apt install install maven
```
</details>

## Springboot 
 I requisiti precedenti sono più che sufficienti l'installazione di Tomcat per questa parte di corso non è necessaria sono sufficienti Java 17 (openjdk) e Mamp/Lamp/mysql workbench per l'esecuzione del server mysql.
Inoltre i progetti sono settatti con le credenziali diemme come username, password come password e come porta la 8888; in caso di problemi sostituirla con la <b> 3306 </b>  se state utilizzando altri server alternativi a Mamp.
Dal Day 4 in poi sono tutti progetti Springboot. 


