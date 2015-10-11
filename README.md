#agenteAr4k
Interfaccia Java sistema AR4K

![alt text](http://www.rossonet.org/wp-content/uploads/2015/01/logoRossonet4.png "Rossonet")

[http://www.rossonet.org](http://www.rossonet.org)

Licenza: [LGPL 3.0](https://www.gnu.org/licenses/lgpl.html)
Per maggiori dettagli sulla licenza rimando a [questa voce](http://it.wikipedia.org/wiki/GNU_Lesser_General_Public_License) di Wikipedia

![alt text](https://www.gnu.org/graphics/gplv3-88x31.png "LGPL Logo")


<a href="http://www.youtube.com/watch?feature=player_embedded&v=9uQAiXJah4U
" target="_blank"><img src="http://img.youtube.com/vi/9uQAiXJah4U/0.jpg" 
alt="Rossonet" width="640" height="360" border="10" /></a>


###Guida rapida per il deploy

La [spin Rossonet](http://www.rossonet.org/archives/94) di [Fedora 21](http://it.wikipedia.org/wiki/Fedora_%28informatica%29) è predisposta per contenere tutti gli strumenti utili per lo sviluppo sulla piattaforma Ar4k.


Un'installazione Ar4k funzionante è composta da due elementi: un ambiente JVM in cui eseguire agenteAr4k e un account ssh su una macchina Linux che ospita il broker [ActiveMQ](http://activemq.apache.org/) e i servizi per la gestione dell'infrastruttura cloud gestiti con [Consul](https://www.consul.io/).

####Procedure ambiente di sviluppo agenteAr4k

La procedura illustrata vale per macchine CentOS/RedHat/Fedora (il codice è per una sessione di [Bash](https://it.wikipedia.org/wiki/Bash)) 

#####Installazione automatica

Per installare in automatico tutto il sistema compreso le dipendenza,
utilizzare il seguente comando.
Se non eseguito con privilegi di root, verrà chiesta l'autenticazione 
per installare Java e git.

```bash
sh <(curl -L -s http://boot.ar4k.net/interfaccia)
```

Se "curl" non fosse presente nel sistema, installarlo con:
```bash
yum install curl
```

#####Installazione manuale

Per scaricare l'intero sistema:
```bash
git clone https://github.com/rossonet/agentear4k.git
```

Per lavorare con git in bash:
```bash
git config --global push.default matching
git config credential.helper store
```

Per creare un'applicazione in un unico file .jar con tutte le librerie incluse e Tomcat 7 integrato:
```bash
./compila.sh
```
esecuzione:
```bash
./ar4k.sh
```

Per aggiornare tutto il progetto e eseguirlo in ambiente di sviluppo (Ctrl-C per interrompere l'esecuzione):
```bash
./rigenera.sh && ./grailsw run-app
```

Per eseguirlo in ambiente di sviluppo (Ctrl-C per interrompere l'esecuzione):
```bash
./grailsw run-app
```

Per eseguire i test:
```bash
./grailsw test-app
```

Per creare un war installabile su Tomcat >= 7
```bash
./grailsw war
```

####Installazione automatica su sistema operativo esistente
>
> Da fare: script di installazione automatica su [RCloud](http://www.rossonet.org/archives/43)/CentOS e RedHat EL
>

##Autenticazione demo: admin/rossonet2012

###Descrizione ambiente

Un sistema Ar4k operativo è composto da vari servizi e componenti logici. L'interfaccia è responsabile per la gestione dei processi [Activiti](http://activiti.org/) e l'erogazione dei servizi web. L'interfaccia può lavorare in due modalità: bootstrap del sistema o interfaccia amministrativa.

####BootStrap sistema Ar4k da interfaccia

<a href="http://www.youtube.com/watch?feature=player_embedded&v=HQIvqNOF4l4
" target="_blank"><img src="http://img.youtube.com/vi/HQIvqNOF4l4/0.jpg" 
alt="Rossonet" width="640" height="360" border="10" /></a>

Dopo la compilazione e l'avvio dell'applicazione Java, sarà possibile collegarsi alla maschera principale su http://indirizzo server:6630/AgenteAr4k. Se l'ambiente viene lanciato come singolo pacchetto jar, l'interfaccia sarà raggiungibile su http://indirizzo server:6630/ -senza AgenteAr4k finale-.

![alt text](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/prima-configurazione-benvenuto.png "Maschera di benvenuto BootStrap").

L'interfaccia è ampiamente documentata. I passi principali per avviare il sistema sono:
1. configurare il nodo ssh -chiamato [vaso](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/vaso.md)- nel sistema;
2. selezionare il contesto tra quelli disponibili sul vaso;
3. selezionare l'interfaccia tra quelle disponibili nel contesto;
4. configurare l'utenza amministrativa base.

![alt text](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/prima-configurazione-ssh.png "Configurazione SSH accesso al vaso master").

![alt text](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/bootstrap-scelta-interfaccia.png "Bootstrap interfaccia").

![alt text](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/bootstrap-prima-utenza.png "Configurazione utenza amministrativa").

#### Ambiente Operativo Ar4k

![alt text](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/IMG_20150908_231216.jpg "Schema generale API AngularJS").

Dopo il bootstrap -o tramite un file di configurazione predisposto da [questo template]([AgenteAr4k-config.groovy_template](https://github.com/rossonet/agenteAr4k/blob/master/AgenteAr4k-config.groovy_template))- il sistema offrirà i seguenti servizi:

...da completare... 

#### Sviluppare memi per Ar4k

##### Documentazione Ar4k

1. [Memoria](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/memoria.md)
2. [Meme](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/meme.md)
3. [Rete](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/rete.md)
4. [Ricettario](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/ricettario.md)
5. [Servizio](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/servizio.md)
6. [Vaso](https://github.com/rossonet/agenteAr4k/blob/master/web-app/documentazione/vaso.md)

7. [Groovy API Ar4k](http://rossonet.github.io/agenteAr4k/web-app/docs/gapi/index.html)
8. [JavaDoc API Ar4k](http://rossonet.github.io/agenteAr4k/web-app/docs/api/index.html)

###Componenti Software

####Grails

Versione Grails 2.4.4

[Documentazione](http://grails.github.io/grails-doc/2.4.4/)

[IDE](http://spring.io/tools/ggts)

Con ./compila.sh si ottiene un jar eseguibile con "java -jar (nome file).jar"
Per configurare il tomcat 7.0.55 integrato editare [Launcher.java](https://github.com/rossonet/agenteAr4k/blob/master/src/java/grails/plugin/standalone/Launcher.java)

Per configurare il prodotto, salvare nella directory home dell'utente che esegue Java il file di configurazione ottenuto compilando il template [AgenteAr4k-config.groovy_template](https://github.com/rossonet/agenteAr4k/blob/master/AgenteAr4k-config.groovy_template) e posizionandolo in .rossonet/(nome app)-config.groovy

####Spring Security

I domini Grails Utente, Ruolo, UtenteRuolo e OAuthID contengono i dati relativi all'autenticazione;
all'avvio l'applicativo configura i ruoli ROLE_ADMIN, ROLE_USER, ROLE_MASTER, ROLE_SISTEMISTA e ROLE_REGISTRATO e l'utente admin con password rossonet2012 (Per configurare un utente differente configurare il file .rossonet/(nome app)-config.groovy con i relativi parametri.)

I permessi sono configurabili in [Config.grovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/conf/Config.groovy). Sempre in Config.groovy sono editabili i testi delle mail per la registrazione e il recupero della password.

Gli utenti appena registrati -anche tramite social auth- sono configurati nel ruolo ROLE_REGISTRATO, per renderli operativi sul sistema devono acquisire il ruolo ROLE_USER.

####Activiti

Activiti è BPMS open source disponibile in Java per l'integrazione in altri applicativi. La documentazione specifica della versione adottata da Ar4k è disponibile qui: [documentazione Activiti](http://www.activiti.org/userguide/)

Le classi Java sono ispezionabili su [JavaDoc Activiti API](http://activiti.org/javadocs/)

####Mail Server

La posta in uscita si appoggia a un smtp esterno con eventuale autenticazione. In [AgenteAr4k-config.groovy_template](https://github.com/rossonet/agenteAr4k/blob/master/AgenteAr4k-config.groovy_template) maggiori dettagli.

####Apache Camel

Implementato in Grails con [Apache Camel Plugin](https://grails.org/plugin/routing). Nel file [MasterCamelRoute.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/routes/org/ar4k/MasterCamelRoute.groovy) sono definite nel [linguaggio specifico di Camel](http://camel.apache.org/routes.html) le rotte istanziate da Grails.

Importando le opportune dipendenze nel file [BuildConfig.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/conf/BuildConfig.groovy) è possibile utilizzare i [vari componenti di Camel](http://camel.apache.org/components.html)

####Kettle

L'applicativo espone un orchestratore Kettle tramite il service [KettleService](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/services/org/rossonet/kettle/KettleService.groovy).

>
> da fare: 
> - l'orchestratore Kettle diverrà un interfaccia di lettura dei metadati dai repository remoti tramite tunnel SSH
> - interfacciandosi con AccoppiatoreService eseguirà, tramite ssh le lavorazioni sui singoli nodi 
>


####JasperReport

Installato [il plugin Jasper](https://grails.org/plugin/jasper) in Grails. 

####SSH Tunnel, Esecuzione e Stream

Il sistema agisce sui nodi attraverso il protocollo [SSH](http://it.wikipedia.org/wiki/Secure_shell), più nello specifico utilizzando la libreria Java [Jsch](http://www.jcraft.com/jsch/).

####WebSocket

Integrato in Grails [atmosphere-meteor plugin](https://grails.org/plugin/atmosphere-meteor).

Il file di configurazione principale per i websocket è [DefaultMeteorHandler.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/atmosphere/org/ar4k/DefaultMeteorHandler.groovy).

####Quartz (Schedulatore)

Integrato in Grails il plugin [quartz](https://grails.org/plugin/quartz).

Una schedulazione che stampa la memoria libera ogni 10 min è configurata in [PingJob.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/jobs/org/ar4k/PingJob.groovy)

####JCloud

>
> Da completare
>

####JSoup

>
> Da completare
>

####Consul

>
> completamente da implementare...
> - integrazione dns
>

####FrontEnd Web in Angular.js

>
> In attesa di repository aggiuntivo con base sviluppo
>

###Versioni

>
> In attesa di primo rilascio
>

###Casi d'uso

>
> Da dimplementare
>


## TODO

###A FINE OTTOBRE 2015

1. Completare integrazione Kettle
2. Porting ambienti produzione Rossonet
3. Integrazione con [RCloud](https://github.com/rossonet/Strumenti-RCloud);
4. Import repository remoti automaticamente da web in funzione dell'identificativo;

###SENZA SCADENZA

1. File kickstart per installazione su CentOS 6.x CentOS 7.x Fedora 21 RHEL7;
2. Integrare [OpenJSCAD](https://github.com/Spiritdude/OpenJSCAD.org);
3. Procedura di connessione remota in modalità automatica (con sniffer o meno), manuale, da Olark;
4. Integrare D3.js, OpenLayer;
5. Valutare Hawtio;
