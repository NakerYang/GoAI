GoAI
====

Compiling from source
---
from command prompt simply use :
	ant

Runnning the Program
---
from command prompt simply use :
	java -jar Go.jar

AI Documentation
---

Human - human player that will use mouse inputs to determine what pieces to play
The Fish - random player that plays a random LEGAL move
The Puppy - Heuristic Player that will play based on a hueristic
Fluffy - The main AI player that uses an 9-ply breadth-limited AlphaBeta Tree

Adding an AI
---
add the AI into the player/ folder and have it extend the Player abstract class
in order for the AI to be playable you must add it into the hashmap in the PlayerFactory.java file in the same folder

