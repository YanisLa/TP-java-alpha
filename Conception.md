**Questions :** 

Est-ce que les joueurs interagiront en temps réel, ou au tour par tour et pourquoi ?
Les joueurs interagiront au tour par tour car la base de données n’est pas en temps réel mais est en accès par API.

Comment sera stocké l'état (toutes les données permettant de représenter le jeu) ?
Le serveur qui servira de DataBase possèdera tous les états des rovers.

Comment seront stockées les informations confidentielles des joueurs (emails, etc.)?
Les informations confidentielles des joueurs seront stockées par la DataBase.

Comment gérer plusieurs parties en même temps ?
Pour gérer plusieurs parties en même temps il faut simplement changer de port.

Comment gérer plusieurs parties par joueurs en même temps ?
Lors de l'appel de l’API, les informations sont demandées puis le socket se ferme. Cela ne prend que quelque milliseconde et l'ordinateur le fait tout seul.

Que se passera-t-il si un serveur plante ? (Considérer la machine / vm / conteneur éteint(e) et inaccessible)
Si le serveur plante on ne pourra plus utiliser l’API.

Comment les joueurs s'authentifieront et sauvegardent leur progression ?
C’est grâce à l'API que les joueurs s’authentifieront et sauvegarderont leur progression.

Comment gérer une charge imprévue ? (100x plus de joueurs que prévu par ex)
Il faut créer d'autre parties en cours et les rediriger ou alors allumer un autre ordinateur prenant les nouvelles connections.
