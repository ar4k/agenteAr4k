# agenteAr4k
Template applicazione AR4K
by Rossonet s.c.a r.l.
[http://www.rossonet.org](http://www.rossonet.org)
![alt text](http://www.rossonet.org/wp-content/uploads/2015/01/logoRossonet4.png "Rossonet")

### Ambiente di sviluppo preconfigurato

Per scaricare l'intero sistema:
```bash
git clone https://github.com/rossonet/agentear4k.git
```
Per lavorare con git in bash:
```bash
git config --global push.default matching
git config credential.helper store
git config color.ui true
git config --global user.name "Nome Cognome - Rossonet -"
git config --global user.email "nome.cognome@rossonet.com"
```

Per creare un'applicazione in un unico file .jar con tutte le librerie incluse:
```bash
./compila.sh
```
esecuzione:
```bash
./ar4k.sh
```

Per aggiornare tutto il progetto e eseguirlo in ambiente di sviluppo:
```bash
./rigenera.sh
```

Per creare un war installabile su Tomcat > 7
```bash
./grailsw war
```

## Autenticazione demo: admin/rossonet2012

### Descrizione ambiente di sviluppo

####Grails

Versione Grails 2.4.4
[Documentazione](https://grails.org/documentation.html)
[IDE](http://spring.io/tools/ggts)

Con ./compila.sh si ottiene un jar eseguibile con java -jar (nome file).jar
Per configurare il Launcher iniziale src/java/grails/plugin/standalone/Launcher.java

Per configurare il prodotto, salvare nella directory home dell'utente che esegue Java il file di configurazione ottenuto compilando il template AgenteAr4k-config.groovy_template e posizionandolo in .rossonet/(nome app)-config.groovy

####Spring Security

I domini Grails Utente, Ruolo, UtenteRuolo e OAuthID contengono i dati relativi all'autenticazione;
all'avvio l'applicativo configura i ruoli ROLE_ADMIN, ROLE_USER, ROLE_MASTER e ROLE_REGISTRATO e l'utente admin con password rossonet2012.
I permessi sono configurabili in Config.grovy. Sempre in Config.groovy sono configurabili i testi delle mail per la registrazione e il recupero della password.
Gli utenti registrati sono configurati nel ruolo ROLE_REGISTRATO, quelli riconosciuti tramite social auth nel ruolo ROLE_USER.

Il servizio di utilità per l'autenticazione è ProcedureService.groovy.

####Mail Server

La posta in uscita si appoggia a un smtp esterno con eventuale autenticazione. In AgenteAr4k-config.groovy_template maggiori dettagli.

####Camel

da fare...

####Kettle

da fare...

####JasperReport

da fare...

####SSH Tunnel

da fare...

####SSH Olark

da fare...

## TODO

1. Integrazione con [RCloud](https://github.com/rossonet/Strumenti-RCloud);
2. Creare file .spec per RPM integrando gli script base ( +tmux );
3. File kickstart per installazione su CentOS 6.x CentOS 7.x Fedora 21 RHEL7;
4. Validare il repository su OpenShift Origin e RH OpenShift Online;
6. Import repository Kettle automaticamente da web in funzione dell'ID applicazione;
7. Procedura di connessione remota in modalità automatica (con sniffer o meno), manuale, da Olark;
9. Integrare [noVNC](https://github.com/kanaka/noVNC);
10. Integrare [tty.js](https://github.com/chjj/tty.js) o [GateOne](https://github.com/liftoff/GateOne);
11. Integrare [OpenJSCAD](https://github.com/Spiritdude/OpenJSCAD.org).

<a href="http://www.youtube.com/watch?feature=player_embedded&v=r47CTqU6F4g
" target="_blank"><img src="http://img.youtube.com/vi/r47CTqU6F4g/0.jpg" 
alt="Rossonet" width="640" height="360" border="10" /></a>
