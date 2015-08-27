Documentazione Meme

![alt text](http://www.rossonet.org/wp-content/uploads/2015/01/logoRossonet4.png "Rossonet")

[http://www.rossonet.org](http://www.rossonet.org)

Licenza: [LGPL 3.0](https://www.gnu.org/licenses/lgpl.html)
Per maggiori dettagli sulla licenza rimando a [questa voce](http://it.wikipedia.org/wiki/GNU_Lesser_General_Public_License) di Wikipedia

![alt text](https://www.gnu.org/graphics/gplv3-88x31.png "LGPL Logo")

###Guida rapida per il deploy

La [spin Rossonet](http://www.rossonet.org/archives/94) di [Fedora 21](http://it.wikipedia.org/wiki/Fedora_%28informatica%29) è predisposta per contenere tutti gli strumenti utili per lo sviluppo sulla piattaforma Ar4k.

####Procedure ambiente di sviluppo agenteAr4k

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

###Perchè questo progetto?

>
> Da fare:
> - scelte progettuali e sponsorship.
> - modello di business cooperativa produzione lavoro e comunità.
> - localizzazione territoriale ed eventuale partnership con associazioni.
> - valorizzazione del modello open source nel contesto locale (con particolare attenzione ai vantaggi della scelta open nelle imprese)
> - compatibilità RedHat
> - integrazione Acantho
> - integrazione CloudPlugs
> - il mondo 3D
>

####Storia del progetto

>
> Da fare: verificare chi vuole essere citato.
>

####L'architettura software (la nostra proposta)

La classe principale su cui operare è [AccoppiatoreService.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/services/org/ar4k/AccoppiatoreService.groovy). Il file è ampiamente commentato.

[**Documentazione completa delle classi e metodi**](http://rossonet.github.io/agenteAr4k/web-app/docs/gapi/index.html)

> 
> - Target del progetto
> - Infrastuttura per lo sviluppo in cloud
> - Curva di apprendimento
> - Ambiente di sviluppo AngularJS
> - Ambiente user space Linux
> - Servizi SSH
>

>
> Da fare: 
> - schema architettura da foto lavagna
> - descrizione parte Angular
> - descrizione parte Service Java
> - descrizione parte SSH e tunnel autossh
> - integrazione noVNC e Term.js
> - descrizione modello dati e sua gestione tramite ssh
> - rimando a repository ssh
>

###Note sull'adozione di git come repository

>
> Da fare:
> - separazione dell'infrastruttura e del codice progetto in due repository distinti
> - agenteAr4k open source
> - repository master ssh privato
> - creazione repository base ssh su GitHub
> - rendicontazione delle ore/lavoro con riferimento a git
> - guida operativa script in bin per la gestione di git
>

###Descrizione ambiente di sviluppo

####Grails

Versione Grails 2.4.4

[Documentazione](http://grails.github.io/grails-doc/2.4.4/)

[IDE](http://spring.io/tools/ggts)

Con ./compila.sh si ottiene un jar eseguibile con java -jar (nome file).jar
Per configurare il tomcat 7.0.55 integrato editare [Launcher.java](https://github.com/rossonet/agenteAr4k/blob/master/src/java/grails/plugin/standalone/Launcher.java)

Per configurare il prodotto, salvare nella directory home dell'utente che esegue Java il file di configurazione ottenuto compilando il template [AgenteAr4k-config.groovy_template](https://github.com/rossonet/agenteAr4k/blob/master/AgenteAr4k-config.groovy_template) e posizionandolo in .rossonet/(nome app)-config.groovy

####Spring Security

I domini Grails Utente, Ruolo, UtenteRuolo e OAuthID contengono i dati relativi all'autenticazione;
all'avvio l'applicativo configura i ruoli ROLE_ADMIN, ROLE_USER, ROLE_MASTER e ROLE_REGISTRATO e l'utente admin con password rossonet2012 (Per configurare un utente differente configurare il file .rossonet/(nome app)-config.groovy con i relativi parametri.)

I permessi sono configurabili in [Config.grovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/conf/Config.groovy). Sempre in Config.groovy sono editabili i testi delle mail per la registrazione e il recupero della password.

Gli utenti registrati sono configurati nel ruolo ROLE_REGISTRATO, quelli riconosciuti tramite social auth nel ruolo ROLE_USER.

Il servizio di utilità per l'autenticazione è [ProcedureService.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/services/org/ar4k/ProcedureService.groovy).

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

Nel file [SshService.groovy](https://github.com/rossonet/agenteAr4k/blob/master/grails-app/services/org/ar4k/SshService.groovy) sono implementati i servizi base di connessione SSH utilizzati dagli oggetti della piattaforma.

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

####Configurazione Oggetti e relazione con repository di progetto

>
> da fare. Javadoc?
>

####Gestione DNS

>
> completamente da implementare...
> - integrazione OpenShift
> - integrazione provider esterni?
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
> Da discutere in Rossonet e con clienti
>


## TODO

###A FINE MAGGIO 2015

1. Completare integrazione Kettle
2. Porting ambienti produzione Rossonet
3. Integrazione con [RCloud](https://github.com/rossonet/Strumenti-RCloud);
4. Import repository remoti automaticamente da web in funzione dell'identificativo;
5. Integrare [noVNC](https://github.com/kanaka/noVNC);
6. Integrare [tty.js](https://github.com/chjj/tty.js);

###SENZA SCADENZA

1. File kickstart per installazione su CentOS 6.x CentOS 7.x Fedora 21 RHEL7;
2. Validare il repository su OpenShift Origin e RH OpenShift Online;
3. Integrare [OpenJSCAD](https://github.com/Spiritdude/OpenJSCAD.org);
4. Integrarare Activiti in AccoppiatoreService;
5. Procedura di connessione remota in modalità automatica (con sniffer o meno), manuale, da Olark;
6. Semplificazione inizio progetto con agenteAr4k (wizard bootstrap agenteAr4k);

## Prodotti open source integrati/integrabili nel sistema

###Linux

> Articoli Torvalds

###Networking


###Integrazione Microsof™

> da definire con un amico inglese

###Git


###Java


###Groovy


###CentOS


###RedHat EL


###OpenShift Origin


###Openshift


###Spring


###Grails

<a href="http://www.youtube.com/watch?feature=player_embedded&v=XrDXqoomws4
" target="_blank"><img src="http://img.youtube.com/vi/XrDXqoomws4/0.jpg" 
alt="Rossonet" width="640" height="360" border="10" /></a>


###SSH


###Pentaho Data Integration (aka Kettle)


###AngularJS


###Twitter BootStrap


###OpenLayers


###D3.js


###JasperReport


###Bash e Chef


###JSoup


###JCloud


###Integrazione VOIP

> da definire

###ImageMagic

> da definire

###Gestione QR

> da definire

###Proxy Apache

> da definire

