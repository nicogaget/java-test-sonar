# SonarQube

---

## Sommaire
##### Introduction
- l'intégration continue
- logiciel open source pour mesurer la qualité du code
- supporte beaucoup de langages
- outil d'analyse complet
- l'outil peut être entièrement automatisé avec :
    - Bamboo
    - Jenkis
    - Azure DevOps
    - BitBucket
    - Github
    - Gitlab
- l'outil est extensible par des plugins

##### Architecture
- le serveur SonarQube
- la base de données
- le sonarScanner

##### Lancer une analyse de code source
- exemple de lancement
- que produit une analyse de code ?
- que se passe-t-il pendant une analyse ?

#### TP dirigé :
- installation des pré-requis et projet maven
- installation avec docker
- aperçu des différentes pages
- executer une analyse avec maven
- analyse des résultats
- sonarLint avec IntelliJ
- executer une analyse avec SonarScanner
- obtenir la couverture de test

---

## Introduction

L'intégration continue est un ensemble de pratiques utilisées en génie logiciel consistant à vérifier à chaque modification de code sources que le résultat des modifications ne produit pas de régression dans 
l'application développée. 

L’objectif est de détecter les problèmes d'intégration au plus tôt lors du développement.

L’intégration continue permet d'automatiser l'exécution des suites de tests et de voir l'évolution du développement du logiciel.

L'intégration continue repose souvent sur la mise en place d'une brique logicielle permettant l'automatisation de tâches : **compilation, tests unitaires et fonctionnels, validation produit, tests de performances…** À chaque changement du code, cette brique logicielle va exécuter un ensemble de tâches et produire un ensemble de résultats, que le développeur peut par la suite consulter. Cette intégration permet ainsi de ne pas oublier d'éléments lors de la mise en production et donc ainsi améliorer la qualité du produit.

Les principaux avantages de l'intégration continue sont :

- le test immédiat des modifications
- la notification rapide en cas de code incompatible ou manquant
- les problèmes d'intégration sont détectés et réparés de façon continue,évitant les problèmes de dernière minute
- une version est toujours disponible pour un test, une démonstration ou une distribution


SonarQube permet de mesurer la qualité du code source de votre application. En effet, c'est un outil de "code review" automatique pour détecter les bugs, les vulnérabilités et le code sale (duplication de code, bonnes pratiques, code mort, etc...). Il peut s'intégrer à votre repository existant pour permettre une inspection continue du code dans les branches de votre projet et vos demandes de "pull requests".

Les objectifs de SonarQube sont clairs :
- Vérifier que le code répond à une exigence qualité
- Respect des normes de codage
- Respect des contraintes sur les degrés de complexités

Et grâce à SonarQube : 
- Plus de cowboy qui sait tout mieux que tout le monde et fait 
comme il veut
- Une validation à chaque build de l'application ou autre événement choisit
- Des règles standards et/ou customisées qui sont globales à 
toute l’entreprise

SonarQube est un logiciel Open Source, ce qui signifie que vous pouvez librement accéder au code source de l'application sur le lien suivant : https://github.com/SonarSource/sonarqube

Ainsi, vous pouvez analyser l'analyseur...  Et proposer des modifications sur le projet.

--- 

**Mais comment ça fonctionne SonarQube ?**

Dans un processus de développement typique :

1. Les développeurs développent du code dans un IDE (de préférence en utilisant SonarLint pour recevoir des commentaires immédiats dans l'éditeur) et push leur code sur leur repository distant.

2. L'outil d'intégration continue (CI) construit l'application et exécute des tests unitaires. Un scanner SonarQube intégré analyse les résultats.

3. Le scanner publie les résultats sur le serveur SonarQube qui fournit des commentaires aux développeurs via l'interface SonarQube, les e-mails, les notifications in-IDE (via SonarLint) et la décoration sur les pull requests ou merge requests.

![dev_cycle](./assets/dev-cycle.png)

![successfull_project](./assets/successfulproject.png)

---

**Votre projet est en Java ? en C#? en PHP?**
Pas de panique, SonarQube est capable d'analyser le code de plus de 27 langages de programmation : 

- Java (incluant Android)
- C# 
- C
- C++ 
- JavaScript 
- TypeScript
- Python
- Go
- Swift
- COBOL
- Apex
- PHP
- Kotlin
- Ruby
- Scala
- HTML
- CSS
- ABAP
- Flex
- Objective-C
- PL/I
- PL/SQL
- RPG
- T-SQL
- VBNET
- VB6
- XML

--- 

**SonarQube, ça analyse quoi ?**
L'analyse de SonarQube est assez complète car elle permet d'analyser : 
- La duplication de code
- Le niveau de documentation
- Le non respect des règles et des conventions
- Les bugs potentiels que contiennent l'application
- La couverture de test de votre application
- l'analyse du design pattern et de l'architecture de l'application
- La répartition de la complexité cyclomatique par méthode ou par classe. 


La compléxité est calculée en fonction du nombre de chemins à travers le code. Chaque fois que le flux de contrôle d'une fonction se divise, le compteur de complexité est incrémenté de un. Chaque fonction a une complexité minimale de 1. Ce calcul varie légèrement selon le langage car les mots-clés et les fonctionnalités sont différents :
![complexity_by_language](./assets/complexity_by_language.png)

Plus de détails sur les différentes données analysées et ce qu'elle signifient sur cette page : https://docs.sonarqube.org/latest/user-guide/metric-definitions/

--- 

SonarQube peut être entièrement automatisé avec :
    - Bamboo
    - Jenkis
    - Azure DevOps
    - BitBucket
    - Github
    - Gitlab

En effet, ces outils vont permettre d'éxécuter le scan de l'application lors d'un événement choisi (par ex: Une pull requests, un push sur la branche main,...)
Rappelons ce schema :
![dev-cycle](./assets/dev-cycle.png)

---

## Architecture

SonarQube est composé de trois principaux éléments : 
1. Un serveur Sonar
2. Une base de donnée
3. Un SonarScanner

![architecture](./assets/architecture.png)
![SQ-instance-components](./assets/SQ-instance-components.png)

##### Le serveur SonarQube

Il est composé de 3 processus principaux :

- Un serveur web pour les développeurs et les gestionnaires.
- Un serveur de recherche basé sur Elasticsearch.
- Du Compute Engine Server qui est en charge du traitement des rapports d’analyse du code et de leur enregistrement en base de donnée

##### La base de données

Elle permet de stocker la configuration de l’instance de SonarQube et les instantanés de qualité des projets.

##### Le SonarScanner

Il s’exécute sur les serveurs de build, test, ect … Il permet d’analyser le projet et de transmettre le résultat au serveur SonarQube  

---

## Lancer une analyse de code source

#### Exemple de lancement

1. Les développeurs codent dans leurs IDE et utilisent SonarLint pour exécuter une analyse locale.

2. Les développeurs insèrent leur code dans leur SCM préféré: git, SVN, TFVC, ...

3. Continuous Integration Server déclenche une génération automatique et l'exécution de SonarScanner requise pour exécuter l'analyse SonarQube.

4. Le rapport d'analyse est envoyé au SonarQube Server pour traitement.

5. SonarQube Server traite et stocke les résultats du rapport d'analyse dans la base de données SonarQube, puis les affiche dans l'interface utilisateur.

6. Les développeurs examinent, commentent et mettent au défi leurs problèmes afin de gérer et de réduire leur dette technique via l'interface utilisateur SonarQube.

7. Les gestionnaires reçoivent les rapports de l'analyse. 

![exemple](./assets/integration_exemple.png)

---

#### Que produit une analyse de code ?

Les résultats de toutes vos analyses sont stockées en base de données et sont accessibles sur l'interface graphique du serveur SonarQube.
![analysis_list](./assets/analysis_list.png)

Vous pouvez séléctionner une analyse pour voir le détail des résultats :
![detailed_analysis](./assets/detailed_analysis.png)

Une statistique est disponible pour vous donner le taux de duplication de code dans votre application. (Une action de refactorisation est nécessaire du côté du développeur)
![duplication](./assets/duplication.png)

SonarQube analyse également les commentaires et la documentation de votre application (la documentation est une part très importante dans la maintenabilité d'une application)
![documentation](./assets/documentation.png)

Vous pouvez également appercevoir une statistique sur le non respect des règles et des conventions, avec une estimation de la dette technique (temps qu'il faudrait pour corriger)
![rules](./assets/rules.png)

Retrouvez aussi dans les analyses les bugs potentiels que contient l'application
![bugs](./assets/bugs.png)

La couverture de test de votre application(le test coverage est un nombre que le client peut ordonner pour justifier de la qualité du code... souvent entre 80%-100%)
![coverage](./assets/coverage.png)

La répartition de la complexité cyclomatique par méthode ou par classe. 
![complexity](./assets/complexity.png)

l'analyse du design pattern et de l'architecture de l'application
![design_pattern](./assets/design_pattern.png)

---

#### Que se passe-t-il pendant une analyse ?

1. Lors de l'analyse, des données sont demandées au serveur, les fichiers fournis à l'analyse sont analysés et les données résultantes sont renvoyées au serveur à la fin sous la forme d'un rapport

2. Les données sont ensuite analysé et traitée de manière asynchrone côté serveur.

3. Les développeurs peuvent ensuite exploiter ce rapport grâce à l’interface de SonarQube.

![during_analysis](./assets/during_analysis.png)

--- 

## TP Dirigé

---

#### Installation des pré-requis et projet maven

Pour la mise en pratique de SonarQube nous allons créer un projet Java qui contient quelques tests unitaires.

Ce projet Java tourne avec Maven et la dépendance Junit 5.

Installation de Java : 
- Télécharger JDK11 (dernière version de Java prise en charge par SonarQube)
![java_version](./assets/java_version.png)
- Rendez-vous sur le lien suivant : https://www.oracle.com/fr/java/technologies/javase/jdk11-archive-downloads.html
- Téléchargez et installer la dernière version du JDK 11.
- Appuyez sur la touche "Windows" et tapez 
````
variables
````
- Puis modifiez vos variables d'environnement %JAVA_HOME% car Maven en a besoin pour builder votre application.

![windows_var](./assets/windows_var.png)
Ouvrez le gestionnaire de variables d'environnement
![variable_env](./assets/vari_env.png)
Ajoutez une nouvelle variable système
![add_new](./assets/add_new_var.png)
Ajoutez JAVA_HOME avec le chemin vers votre JDK
![java_home](./assets/java_home.png)
Modifier la variable "Path" et ajoutez %JAVA_HOME%
![modifier_path](./assets/modifier_path.png)
![add_java_home](./assets/add_java_home_path.png)

- Vérifiez que le système reconnait votre version de Java sur la JDK 11 : 
![java-version](./assets/java-version.png)

- Pour installer Maven sur notre ordinateur, rendez-vous sur le lien suivant : 
https://maven.apache.org/download.cgi
et téléchargez *"apache-maven-3.8.4-bin.zip"*

- Dé-zippez le fichier à l'endroit où vous le souhaitez (de préférence proche de JAVA)

- Ajoutez deux variables d'environnement pointant vers le répertoire : 
![maven_home](./assets/maven_home.png)
![m2_home](./assets/m2_home.png)

- Modifiez la variable PATH : 
![m2_home_path](./assets/m2_home_path.png)

- Enfin, testez que l'installation s'est correctement passer en tapant dans un terminal la commande :
````
mvn -version
````
![mvn_version](./assets/mvn_version.png)

---

#### Installation avec docker

Il existe deux manières d'installer SonarQube :
- En téléchargeant un executable sur votre ordinateur
- En téléchargeant une image docker 

![sonar_install](./assets/sonar_install.png)

Pour des raisons de simplicité, nous allons choisir la deuxième option : 

- Ouvrez un invité de commande et tapez la commande : 
````
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
````

![sonar_docker](./assets/sonar_docker.png)

- Accéder à votre application sonar en vous rendant sur le lien : http://localhost:9000

- Connectez vous avec les identifiants : 
admin
admin

![sonar_login](./assets/sonar_login.png)

- Sonar est désormais installé et prêt à lancer sa première analyse : 
![sonar_interface](./assets/sonar_interface.png)

---

#### Aperçu des différentes pages

- **Page projects**
Cette page réunis tous les projets sur lesquels vous avez déjà lancé un ou plusieurs tests. Par défaut, la page vous propose de lancer une analyse en selectionnant l'emplacement de l'application.
Si le projet est hébergé sur Azure DevOps, bitBucket, gitHub ou Gitlab mais également en local avec l'option "manually"
![sonar_interface](./assets/sonar_interface.png)

- **Page Issues**
La page issues reporte tous les scans que vous avez pu faire avan,t avant avec un récapitulatif de tous les resultats
![sonar_issues](./assets/sonar_issues.png)

- **Page Rules**
La page rules permet d'accéder à toutes les règles et bonnes pratiques des langages pris en charge par SonarQube. Vous pouvez vous apercevoir qu'il existe plus de 600 règles que SonarQube analysera pour un projet Java par exemple. Vous avez même la possibilité de regarder quelles règles sont analysées.
![sonar_rules](./assets/sonar_rules.png)

- **Page Quality Profiles**
La page quality profile permet de définir quelles règles vous voulez tester sur tel ou tel langage. Des profils par défaut sont présents pour tous les langages pris en charge par SonarQube. Vous pouvez donc personnaliser vos tests en fonction de vos besoins.
![sonar_quality_profiles](./assets/sonar_quality_profiles.png)

- **Page Quality Gates**
La page quality gates permet de définir les jalons de test. Par exemple si vous ne nécessité qu'une couverture de test de 70% vous pouvez modifier la valeur par défaut à 80%.
Elle permet de définir le résultat attendu du scanner de votre application.
![sonar_quality_gates](./assets/sonar_quality_gates.png)

- **Page Administration**
La page administration est uniquement accessible car vous êtes connectés en tant qu'administrateur. Elle permet de définir la configuration de SonarQube, la sécurité pour l'accès au serveur. Cette page permet également d'installer de nouveaux plugins à SonarQube.
![sonar_administration](./assets/sonar_administration.png)

---

#### Executer une analyse avec maven

- Pour exécuter un test avec Maven, retournez sur la page "projects" et sélectionnez l'import de projet avec l'option "manually" car notre projet ne se situe pas sur Github ou autre repository distant.

- Saisissez le nom que votre projet portera sur SonarQube ainsi que la clé permettant de l'identifier
![sonar_manually_first](./assets/sonar_import_manually_first.png)

- Choisissez de tester votre projet localement
![sonar_manually_second](./assets/sonar_manually_second.png)

- Générer un token qui permettra à sonar de détecter votre projet et éxecuter les tests
![sonar_manually_third](./assets/sonar_manually_third.png)

- Pensez à bien **copier cette clé**, il ne faut pas la perdre !!!!!!
![sonar_manually_fourth](./assets/sonar_manually_fourth.png)

- Selectionnez l'option maven car notre projet Java tourne sur Maven
![sonar_manually_fifth](./assets/sonar_manually_fifth.png)

- Copiez la commande qui vous est donnée qui permet d'exécuter un scan de votre application à l'aide de Maven.
Les informations données sont la clé de projet, l'url du serveur sonar ainsi que le token permettant d'identifier le projet.

````
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=java-test-sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=cb54b1f7bb8a78c0f8bf4b955bd56a92d99d22c4
````
**Cette commande doit être lancée à partir du dossier de votre projet**

---

#### Analyse des résultats

Sans rien toucher sur le serveur SonarQube, nous pouvons nous apercevoir que ce dernier a récupéré les resultats de notre analyse.

![maven_scan_result](./assets/maven_scan-results.png)

Le scan par maven ne prend pas en charge le "test coverage", nous devrons passer par un test complet avec le SonarScanner pour obtenir ce résultat.

Nous pouvons voir que notre application contient 0 bugs et 0 vulnerabilité à la date présente. (Peut être que des failles apparaitront plus tard avec les mises à jour de dépendance etc...)

Nous pouvons également voir que 7 "mauvaises pratiques ont été mises en place. Concentrons nous dessus :

![maven_code_smells](./assets/mlaven_scan_code_smells.png)

Nous devons donc : 
- Ajouter un constructeur privé dans notre classe static Calculatrice
- Ajouter du contenu à notre méthode PSVM
- Retirer les attributs "public" de nos méthodes de test

---

#### SonarLint avec IntelliJ

Installons sonarLint sur IntelliJ afin de constater directement sur notre code les éléments qui posent problème.
Rendez-vous sur l'url suivante : https://www.sonarlint.org/intellij

![sonarlint-intellij](./assets/sonarlint-intellij.png)

Suivez les instructions pour installer sonarLint facilement en deux trois clics

![sonarlint-validation](./assets/sonarlint-validation.png)

Retournez sur IntelliJ et cliquez sur "Ok"
Relancez intelliJ et observez les changements apportés à votre IDE.

sonarLint nous propose de corriger les erreurs majeures de notre code : 

![sonarlint-major-fixs](./assets/sonarlint-major-fixs.png)
![sonarlint-minors-fixs](./assets/sonarlint-minor-fixs.png)
![sonarlint-main-fixs](./assets/sonarlint-main-fixs.png)

Au travail, resolvons les erreurs sur notre IDE et nous relancerons l'analyse juste après pour analyser les résultats.

---

#### Executer une analyse avec SonarScanner

Pour lancer un test directement avec le sonarScanner, nous allons passer par la commande :

**N'oubliez pas de remplacer votre projectKey et votre Token !!!**

````
docker run --rm -e SONAR_HOST_URL="http://192.168.176.1:9000" -e SONAR_LOGIN="cb54b1f7bb8a78c0f8bf4b955bd56a92d99d22c4" -v "D:\dev\java\java-test-sonar:/usr/src" sonarsource/sonar-scanner-cli -Dsonar.projectKey=java-test-sonar -Dsonar.java.binaries=target
````

---

#### Obtenir le coverage

Avec Maven, obtenir la couverture n'est pas automatique.
Deux solutions s'offrent donc à vous : 
1. Passer directement par IntelliJ
![launch_coverage](./assets/launch-coverage.png)
![coverage_result](./assets/coverage-result.png)
2. Passer par le plugin maven JaCoCo :
https://community.sonarsource.com/t/coverage-test-data-importing-jacoco-coverage-report-in-xml-format/12151


