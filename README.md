# Paukemon - JEE Project

<img src="src\main\resources\static\logo.png"/>

### Configuration de la version

Ce projet utilise Java 21.


### Lancer l'application
Télécharger le projet sur votre machine.

(LINUX) Rendez-vous dans votre terminal à la racine du projet et lancer l'application via Docker grâce au script :
```bash
./run-docker.sh
```

(WINDOWS) Rendez-vous dans votre terminal à la racine du projet et lancer l'application via Docker grâce au script :
```bat
./run-docker.bat
```

L'image Docker est bien construite quand le script se termine sans message d'erreur.

### Explorer le site

Saisissez l'adresse http://localhost:8080 dans votre navigateur préféré. Une connexion Internet est nécessaire pour les appels à l'API fournissant les cartes.

### Point d'attention !

Il est possible que les réponses de l'API prennent du temps (parfois quelques minutes) soit lors du chargement initial du site, soit lors du premier accès à une page d'ouverture de boosters. Cela peut être dû à votre connexion Internet, ou à la disponibilité de l'API.
