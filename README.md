# TicTacToe

Coding homework for an interview with Mobilewalla

Home Exercise (Backend Engineer)
Description
Your task is to implement is a text based 2-player tic-tac-toe game. The players would need to
take turns at placing an X or an O on a 3X3 board.
Link: https://en.wikipedia.org/wiki/Tic-tac-toe

Game Rules
1. Player 1’s marker is ‘X’, Player 2’s marker is ‘O’.
2. The two players take turns placing their marker on the board, starting with Player 1.
3. The players can place their marker on any vacant spot.
4. The objective of the game is for a player to get three of their markers in a row
(horizontally, vertically or diagonally).

Requirements
1. Ask for names of players 1 and 2.
2. Alternate between input for player 1 and player 2.
3. Stop when one of the players has won.


Sample Game
Enter name for Player 1:
>> John Doe

Enter name for Player 2:
>> Michael Doe


 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9
John Doe, choose a box to place an 'x' into:
>> 1
 x | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9
Michael Doe, choose a box to place an 'o' into:
>> 2
 x | o | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9
John Doe, choose a box to place an 'x' into:
>> 4
 x | o | 3
-----------
 x | 5 | 6
-----------
 7 | 8 | 9
Michael Doe, choose a box to place an 'o' into:
>> 5
 x | o | 3
-----------
 x | o | 6
-----------
 7 | 8 | 9
John Doe, choose a box to place an 'x' into:
>> 7
 x | o | 3
-----------
 x | o | 6
-----------
 x | 8 | 9
 
Congratulations John Doe! You have won.

Note: Lines beginning with “>>” denote user input.

Intermediate Challenge
To impress us further, you could consider implementing extending the board to be a generic
NxN board where the game ends if either player gets 3 (not N) ​ in a row in any direction.
(Note: N is determined by user input at the beginning of the game)
