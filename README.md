# SpiceOfLife SpinalCraft Edition
A Paper plugin which mimics SpiceOfLife to an extent

![alt text](https://github.com/Belinski20/SpiceOfLife/blob/main/images/example.PNG?raw=true)

### Dependencies

* [Protocollib](https://www.spigotmc.org/resources/protocollib.1997/)

### Commands

/opt
* Opt into SpiceOfLife

/solreload
* Reloads Config and Food Values

### Configuration

```yml
OptIn: true
FoodAmounts: 15
Food:
  APPLE:
    foodAmount: 1
    saturation: 1
```
OptIn
* True : Lets players opt in or out of SpiceOfLife
* False : Forces players into SpiceOfLife on login

FoodAmounts
* The amount of food which is remembered and effects food amounts

Food
* This is generated on startup if the config.yml has not bee created before.

foodAmount
* This is the amount of hunger which is returned by a food item

saturation
* This is the amount of saturation which is given to a player
