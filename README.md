# 🕵️ Metal Vip Liquid

**Metal Vip Liquid** est un jeu d’infiltration dans lequel vous devez progresser discrètement à travers des cartes remplies d’ennemis et de caméras de sécurité. Utilisez des armes disséminées sur la carte, évitez les zones surveillées et accomplissez vos objectifs de mission grâce à une planification stratégique.

---

## 🎮 Type de jeu

**Genre** : Stealth / Infiltration

---

## 📋 Description

Dans **Metal Vip Liquid**, vous incarnez un agent furtif dont la mission est de s’infiltrer dans des zones hostiles sans se faire repérer. Le jeu repose sur trois piliers :

- **🔍 Discrétion** : Évitez les ennemis et les caméras.
- **🔫 Ingéniosité** : Utilisez les armes et objets du décor à votre avantage.
- **🧠 Stratégie** : Planifiez vos mouvements pour atteindre vos objectifs.

---

## 🕹️ Comment jouer

- 🔍 **Avancez discrètement** : Restez hors du champ de vision des caméras et des ennemis.
- 🔫 **Récupérez et utilisez les armes** : Changez d’arme selon les situations rencontrées.
- 🧠 **Réfléchissez avant d’agir** : Adaptez votre approche à chaque zone du jeu.

---

## ⚙️ Installation

### Prérequis

Assurez-vous d’avoir les logiciels suivants installés :

- Java Development Kit (**JDK**) version **17** ou supérieure.
- **Gradle** version **8.10.2** ou supérieure.
- La bibliothèque **LibGDX** (incluse automatiquement via Gradle).

---

## 🚀 Lancement du jeu

### 🔧 Cloner le dépôt

Clonez le dépôt sur votre machine :

```bash
git clone https://github.com/mohummadpeer13/METAL-VIP-LIQUID.git
```

## 🐳 Exécution avec Docker

### Prérequis

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Démarrer le jeu avec Docker Compose

Dans le dossier du projet, exécutez la commande suivante :

```bash
docker-compose up --build
```
---

## 📁 Structure

* core/ — logique principale du jeu
* lwjgl3/ — lancement du jeu sur desktop
* assets/ — ressources du jeu
* build.gradle — configuration Gradle

---

## 🛠️ Développement

### Lancer localement

1. Ouvrez le projet dans votre IDE préféré (ex. : IntelliJ IDEA, Eclipse).
2. Importez-le en tant que **projet Gradle**.
3. Compilez le projet avec Gradle.
4. Exécutez la classe `Main` ou utilisez la commande suivante :

```bash
./gradlew lwjgl3:run
```

### 🔨 Construction du jeu (.JAR)

Pour générer un fichier `.jar` exécutable pour la distribution, exécutez la commande Gradle suivante :

```bash
./gradlew lwjgl3:dist
```

Après l'exécution de cette commande, Gradle créera le fichier .jar dans le dossier suivant : 

```bash
lwjgl3/build/libs/
```

#### ▶️ Exécuter le fichier `.jar`

```bash
java -jar [nom_du_fichier_jar]
Par exemple : java -jar Metal_Vip_Liquid-1.0.0.jar
```

## 📚 Javadoc

Pour une documentation détaillée des classes et des méthodes du projet, consultez la Javadoc générée pour **Metal Vip Liquid** ici :  
🔗 [Metal Vip Liquid Javadoc](https://mohummadpeer13.github.io/METAL-VIP-LIQUID-JAVADOC/)

