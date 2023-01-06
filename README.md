# PLA Plates  [![GitHub](https://img.shields.io/github/license/Pixelmon-Development/API)](https://www.gnu.org/licenses/lgpl-3.0.html)

This project introduces the Blank and Legend Plates from Pokemon Legends: Arceus into Pixelmon.  The Blank Plate maintains Arceus's Normal-typing, but provides the standard 20% boost.  It can be combined with an Azure Flute to form the Legend Plate, which changes Arceus's type whenever it uses Judgement.

Type is decided by:
1) Most supereffective against opponent
2) Most resistant to opponent's primary typing (if any)
3) Most resistant to opponent's secondary typing (if any)
4) Random pick

Each stage cuts down the number of types under consideration.
