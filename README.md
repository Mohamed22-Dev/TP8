# TP8 - Multithreading et Concurrence en Kotlin / Android

Ce projet Android (TP8) illustre la gestion des threads et de la concurrence en Kotlin sous Android à travers deux exercices pratiques.

---

## 📱 Aperçu des Fonctionnalités

### 1. Menu Principal (`MainActivity`)
Permet de naviguer facilement vers les deux exercices de l'application :
- **Bouton EX1** : Lance la simulation de téléchargement.
- **Bouton EX2** : Lance la simulation du problème Producteur-Consommateur.

---

### 2. Exercice 1 : Simulation de Téléchargement (`Ex1`)
- **Objectif** : Exécuter une tâche longue en arrière-plan sans bloquer l'interface utilisateur (UI Thread).
- **Technologies & Notions** :
  - `Thread` pour l'exécution en arrière-plan.
  - `runOnUiThread` pour mettre à jour la `ProgressBar` et le `TextView`.
  - Désactivation temporaire du bouton pendant le traitement.

---

### 3. Exercice 2 : Problème du Producteur - Consommateur (`Ex2`)
- **Objectif** : Gérer la synchronisation entre plusieurs threads accédant à une ressource partagée (stock).
- **Technologies & Notions** :
  - `synchronized`, `wait()` et `notifyAll()` pour le contrôle de la concurrence.
  - Variable volatile (`@Volatile private var running`) pour la visibilité entre threads.
  - `Handler` et `Looper.getMainLooper()` pour programmer l'arrêt automatique de la simulation après 20 secondes.

---

## 🛠️ Structure du Projet

```text
app/src/main/
├── java/com/example/tp8/
│   ├── [MainActivity.kt](file:///C:/Users/moham/AndroidStudioProjects/TP8/app/src/main/java/com/example/tp8/MainActivity.kt)    # Menu de navigation
│   ├── [Ex1.kt](file:///C:/Users/moham/AndroidStudioProjects/TP8/app/src/main/java/com/example/tp8/Ex1.kt)             # Exercice 1 (Téléchargement)
│   └── [Ex2.kt](file:///C:/Users/moham/AndroidStudioProjects/TP8/app/src/main/java/com/example/tp8/Ex2.kt)             # Exercice 2 (Producteur / Consommateur)
└── res/layout/
    ├── [activity_main.xml](file:///C:/Users/moham/AndroidStudioProjects/TP8/app/src/main/res/layout/activity_main.xml)  # Interface du menu
    ├── activity_ex1.xml   # Interface de l'exercice 1
    └── activity_ex2.xml   # Interface de l'exercice 2
