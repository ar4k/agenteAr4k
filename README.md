# agenteAr4k
Template applicazione AR4K
by Rossonet s.c.a r.l.
[http://www.rossonet.org](http://www.rossonet.org)
![alt text](http://www.rossonet.org/wp-content/uploads/2015/01/logoRossonet4.png "Rossonet")

### Ambiente di sviluppo preconfigurato

Per scaricare l'intero sistema:
```bash
git clone https://github.com/rossonet/agenteAr4k.git
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

da fare...

####Spring Security

da fare...

####OAuth

da fare...

####Mail Server

da fare...

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
5. Consolidamento agente Kettle; ripristino job avvio;
6. Import repository Kettle automaticamente da web in funzione dell'ID applicazione;
7. Procedura di connessione remota in modalità automatica (con sniffer o meno), manuale, da Olark;
8. Maschere Angularjs/Twitter Bootstrap
9. Integrare [noVNC](https://github.com/kanaka/noVNC)
10. Integrare [tty.js](https://github.com/chjj/tty.js) o [GateOne](https://github.com/liftoff/GateOne)
11. Integrare [OpenJSCAD](https://github.com/Spiritdude/OpenJSCAD.org)

<a href="http://www.youtube.com/watch?feature=player_embedded&v=r47CTqU6F4g
" target="_blank"><img src="http://img.youtube.com/vi/r47CTqU6F4g/0.jpg" 
alt="Rossonet" width="480" height="240" border="10" /></a>
