# PluginEvent-Minitel
Plugin pour l'incroyable événement organisé par Minitel EI21

# Structure du projet
  - Main.java
    > Initialisation des variables globales et des commandes
  - Commandes.java
    > Implémente les différentes commandes définies dans *plugin.yml*.
  - PluginListener.java
    > Gère les événements ayant lieu sur le seveur : action d'un joueur ou d'une entité par exemple.
  - VariablesGlobales.java
    > Contient des variables globales d'état du jeu :
    + **gameStarted** : Booléen égal à vrai lorsque la partie est démarrée par la commande go.
    + **pvpOn** : Booléen égal à vrai lorsque la partie a démarrée depuis plus de 30 minutes.
    + **SB** : Scoreboard (Minecraft) principal.
    + **equipes** : ***ArrayList*** d'***Equipe***s : Liste des équipes de participants
    + **achi_check** : ***"achievement checker"*** : est une table de hachage (***HashMap***) contenant des couples (Advancement, Equipe), permettant de vérifier (rapidement) si une équipe a obtenue un succès donné. 
    
  - Equipes.java
    > Classe implémentant une équipe : nom, score, couleur, liste des joueurs **ArrayList<Player>** .
     + Récupérer l'équipe d'un joueur :
     ```java
       Equipe getEquipe(ArrayList<Equipe> equipes, Player p);
     ```
     + Supprime un joueur d'une équipe :
       ```java
         void popPlayer(ArrayList<Equipe> equipes, Player p);
       ```
     + Ajoute un joueur à une équipe :
       ```java
         void addPlayer(ArrayList<Equipe> equipes, Player p);
       ```
     + Divers :
       ```java
         void updateScore(int pointsGagnes);
         int getScore();
         String getCouleurEquipe();
         String getNomEquipe();
       ```
  - plugin.yml
    > Contient un descriptif de chaque commande :
      + Commande all : permet d'envoyer un message à l'ensemble des joueurs, visible sous ce format : [ADMIN] > votreMessage .
      ``` 
        /all <votreMessage>
     ```
      + Commande go : démarre le mode de jeu
     ``` 
        /go
     ```
# Problèmes actuels
  - Un joueur n'est pas en mesure de changer d'équipe une fois qu'il en possède une (Pour changer d'équipe dans les tests : Tenir un bloc de laine bleue, rouge, verte ou orange et cliquer dans le  vide). Cela fonctionnait pourtant bien avant.
  - Quand un succès est débloqué, la fonction de détection de succès débloqué : (PluginListener.java > void onUnlockedAchievment(PlayerAdvancementDoneEvent e)) s'active plusieurs fois, l'équipe cumule trop de points de ce fait.
  
# Attentes du projet
  - Le jeu doit être démarré sur demande (via une commande (go) par exemple).
  - Un scoreboard affichant un classement des équipes par points doit être présent.
  - Si une équipe est la première à débloquer un succès, elle remporte des points et dans le cas échéant, elle ne remporte rien.
  - Le PVP doit s'activer après un certains temps de jeu (30 Minutes par exemple).
  - Si un joueur meurt, il respawn en (0, 0, 0) ou proche d'un memebre de son équipe si possible (À décider).
  - Si une équipe reste première du classement pendant 5 minutes, elle gagne des points supplémentaires. (Points supplémentaires par 5-aine de minutes à la première place).
  - Si une équipe élimine un ennemi, elle emporte des points.

# Fonctionnel
  - Rejoindre une équipe tant que le jeu n'est pas lancé (partiellement : bug).
  - Donner des points à une équipe quand elle est la première à débloquer un succès (partiellement : bug).
  - Empêcher le PVP quand il n'est pas autorisé.
  - Éxecuter la commande all.
  - Éxécuter la commande go.
  - Afficher le nom d'équipe à côté du joueur dans le chat.
  
# À réaliser
  - Ajouter un chronomètre pour activer le PVP après un temps voulu et effectuer des actions périodiquement.
  - Afficher le scoreboard.
  - Création d'une zone de combar (worldborder) et téléportation des joueurs dans cette dernière.
  - Réduire la zone de combat.
  - Donner des points à l'équipe en tête toutes les 5 minutes.
  - Donner des points à une équipe pour le kill d'un ennemi.
