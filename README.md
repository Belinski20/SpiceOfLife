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
#### Default Food and Saturation Values
```yml
Food:
  APPLE:
    foodAmount: 4
    saturation: 2.4
  MUSHROOM_STEW:
    foodAmount: 6
    saturation: 7.2
  BREAD:
    foodAmount: 5
    saturation: 6
  PORKCHOP:
    foodAmount: 3
    saturation: 1.8
  COOKED_PORKCHOP:
    foodAmount: 8
    saturation: 12.8
  GOLDEN_APPLE:
    foodAmount: 4
    saturation: 9.6
  ENCHANTED_GOLDEN_APPLE:
    foodAmount: 4
    saturation: 9.6
  COD:
    foodAmount: 2
    saturation: 0.4
  SALMON:
    foodAmount: 2
    saturation: 0.4
  TROPICAL_FISH:
    foodAmount: 1
    saturation: 0.2
  PUFFERFISH:
    foodAmount: 1
    saturation: 0.2
  COOKED_COD:
    foodAmount: 5
    saturation: 6
  COOKED_SALMON:
    foodAmount: 6
    saturation: 9.6
  COOKIE:
    foodAmount: 2
    saturation: 0.4
  MELON_SLICE:
    foodAmount: 2
    saturation: 1.2
  DRIED_KELP:
    foodAmount: 1
    saturation: 0.6
  BEEF:
    foodAmount: 3
    saturation: 1.8
  COOKED_BEEF:
    foodAmount: 8
    saturation: 12.8
  CHICKEN:
    foodAmount: 2
    saturation: 0.4
  COOKED_CHICKEN:
    foodAmount: 6
    saturation: 7.2
  ROTTEN_FLESH:
    foodAmount: 4
    saturation: 0.8
  SPIDER_EYE:
    foodAmount: 2
    saturation: 3.2
  CARROT:
    foodAmount: 3
    saturation: 3.6
  POTATO:
    foodAmount: 1
    saturation: 0.6
  BAKED_POTATO:
    foodAmount: 5
    saturation: 6
  POISONOUS_POTATO:
    foodAmount: 2
    saturation: 1.2
  GOLDEN_CARROT:
    foodAmount: 6
    saturation: 14.4
  PUMPKIN_PIE:
    foodAmount: 8
    saturation: 4.8
  RABBIT:
    foodAmount: 4
    saturation: 1.8
  COOKED_RABBIT:
    foodAmount: 5
    saturation: 6
  RABBIT_STEW:
    foodAmount: 10
    saturation: 12
  MUTTON:
    foodAmount: 2
    saturation: 1.2
  COOKED_MUTTON:
    foodAmount: 6
    saturation: 9.6
  CHORUS_FRUIT:
    foodAmount: 4
    saturation: 2.4
  BEETROOT:
    foodAmount: 1
    saturation: 1.2
  BEETROOT_SOUP:
    foodAmount: 6
    saturation: 7.3
  SUSPICIOUS_STEW:
    foodAmount: 6
    saturation: 7.2
  SWEET_BERRIES:
    foodAmount: 2
    saturation: 0.4
  GLOW_BERRIES:
    foodAmount: 2
    saturation: 0.4
  HONEY_BOTTLE:
    foodAmount: 6
    saturation: 1.2
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
