# Maze
A self-played game in Java with a random maze a hero and a villian chasing him

This is a project I did at the subject data structures in the university.
The goal was for the hero to collect all 4 loots before the villian catches him.
At first I had to make a random maze that each square doesn't have more than 2 walls.

After the maze was made I placed 4 supplies which we consider as loot for the player.
Then I created the hero and the villian which move random by rolling a dice as long as there is no wall at the direction the dice sent them too(they roll the dice again at that case).
Now because if it was random the hero would never win, we gave him a special sword. By creating a child of the class hero I wrote some MinMax functions that evaluate the best move taking
into consideration if they get closer to the villian or a loot. To achieve that the player was looking 3 moves deep and the nearest the loot and the furthest the villian the better the evaluation.
Starting the game the hero and the villian take turns moving one after the other for a 100 moves each.
The moves were all being stored and shown at the end as well as the result of the game.

To see the example go to the code version
Example.
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|                               | S3  M     |               |
+   +---+---+---+---+   +---+   +   +---+   +   +   +   +   +
|   |               | T         |       |                   |
+   +   +---+---+   +   +---+---+---+   +---+---+   +   +---+
|       |                                                   |
+---+   +   +---+   +   +---+---+   +---+   +---+---+   +   +
|       |           |       |       |                       |
+   +---+---+   +---+---+   +   +---+   +---+---+---+   +   +
|                           |                       |       |
+---+---+---+---+---+---+   +---+   +   +---+   +   +---+   +
|                           |       |   |           |       |
+   +---+---+---+---+   +---+   +   +   +   +---+   +   +---+
|   |                           |   |   |                   |
+   +   +---+   +---+---+---+---+   +   +---+   +   +---+   +
|   |                                                       |
+   +   +   +---+---+---+---+   +   +   +---+   +   +---+---+
|       |                           |           |   |       |
+   +---+---+---+---+   +---+---+---+   +---+   +   +   +   +
|           | S2    |               |                       |
+   +---+   +   +   +   +---+   +   +---+---+   +---+---+---+
|       |                       |                           |
+---+   +---+   +   +   +   +---+   +---+---+---+---+---+   +
|           |   |               |                   |       |
+   +---+   +   +---+---+---+   +---+---+   +---+   +   +   +
|                           |                               |
+   +---+---+---+---+   +   +   +---+---+   +---+---+   +---+
|                               |       |   |             S4|
+   +---+---+---+---+   +---+---+   +   +   +   +   +---+   +
|                                               |           |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
      Round 30
Theseus rolled 2 this turn
He had collected 1 supply.
He couldn't see any supplies.
He didn't sense the enemy near.

After the last round.
Theseus rolled 1 on the dice: 26 times
Theseus rolled 2 on the dice: 33 times
Theseus rolled 3 on the dice: 13 times
Theseus rolled 4 on the dice: 28 times
It's a draw...nobody won.

At this project I learned a lot about object oriented programming as well as nodes and inheritance. 
