# Projet Othello - Gestion de projets


## Prérequis
Avant de pouvoir exécuter le projet, il est nécessaire de s'assurer que l'environnement de développement est correctement configuré.

- **JAVA** : Java version 20 ou plus récente doit être installé.
- **GRADLE** : Gradle 8 ou plus récente doit être installé

## Description et Utilité

### OthelloController.java
`OthelloController` est la classe principale qui coordonne les différentes stratégies pour résoudre un problème donné. Elle utilise les interfaces et diverses stratégies pour atteindre la solution optimale.


## Usage de Gradle

Le projet utilise Gradle pour l'automatisation de la construction. Voici quelques commandes utiles :

- **Construire le projet :**
    ```bash
    ./gradlew build
    ```

- **Nettoyer le projet :**
    ```bash
    ./gradlew clean
    ```

- **Exécuter le projet :**
    ```bash
    ./gradlew run
    ```

## Tests en Gradle

Les tests unitaires et d'intégration sont gérés par Gradle. Voici comment exécuter les tests :

- **Exécuter tous les tests :**
    ```bash
    ./gradlew test
    ```

- **Exécuter un test spécifique :**
    ```bash
    ./gradlew test --tests "nom.de.la.ClasseDeTest"
    ```
