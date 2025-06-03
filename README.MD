# 🌐 LuxNet - Internet Parallèle en Java

LuxNet est une expérimentation d’un réseau alternatif auto-hébergé, avec son propre système d’adressage (`lux.X.X.X`), des paquets personnalisés, un navigateur minimal et un simulateur de routeur.

---

## 📁 Structure du projet

<pre><code>
Luxnet/
├── src/
│   └── luxnet/
│       ├── Main.java
│       ├── net/
│       │   ├── LuxAddress.java
│       │   └── LuxPacket.java
│       ├── browser/
│       │   └── LuxBrowserUI.java
│       ├── client/
│       │   └── LuxClient.java
│       └── router/
│           └── LuxRouter.java
├── out/
└── README.md

</code></pre>

---

## ⚙️ Prérequis

- Java 11 ou supérieur installé (`java` et `javac`)
- Un terminal (Linux, macOS ou Windows WSL/cmd/powershell)

---

## 🛠️ Compilation

Dans le dossier racine du projet (`Luxnet/`), exécute :

<pre><code>javac -d out $(find src -name "*.java")</code></pre>

Cela compile tous les fichiers `.java` dans `out/`.

---

## 🚀 Lancer le projet

### 🧠 Démarrer le routeur (serveur)

<pre><code>java -cp out luxnet.Main server</code></pre>

### 💬 Lancer le client (simulateur de navigateur)

Ouvre un second terminal, puis tape :

<pre><code>java -cp out luxnet.Main client</code></pre>

Tu verras une réponse comme :

<pre><code>Réponse du router : ACK LuxRouter</code></pre>

---

## 🖥️ Interface graphique (navigateur prototype)

Tu peux également lancer une interface Swing très simple (WIP) :

<pre><code>java -cp out luxnet.browser.LuxBrowserUI</code></pre>

---

## 🧹 Nettoyer les ports bloqués (en cas d'erreur "Address already in use")

Trouver le processus sur le port 9000 :

<pre><code>sudo lsof -i :9000</code></pre>

Terminer le processus :

<pre><code>kill -9 &lt;PID&gt;</code></pre>

---

## ✨ À venir

- Système DNS personnalisé
- Extensions de domaine entierement personnalisables
- Routage IP avec format `lux.X.X.X`
- WAN simulé pour la comm externe
- Système d’extensions réseau
- API LuxNet
- Browser LuxNet avec support de pages HTML/CSS/JS (pourquoi pas java ?)

---

## 📜 Licence

Ce projet est en développement expérimental par **Lux_**.  
Le code est publié sous licence libre, n'hésite pas à contribuer ou à expérimenter !


---

## 🙏 Remerciements

Merci à l’esprit de bidouille, au libre et à la curiosité 🐧❤️.
Merci à ceux qui installe le projet, c'est ce qu'il le fait vivre !
