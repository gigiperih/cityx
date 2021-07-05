# cityx
Find some cities that you wanted to go!

![](https://www.programcreek.com/wp-content/uploads/2014/05/implement-trie-prefix-tree.png)

## configuration
- Android Studio `4.2.1`
- Kotlin `v1.4.10`
- Gradle Kotlin DSL
- Android Architecture Component with clean arch approach
- Unit tested in all layers

## feature
- implemented using `pragmatic TDD`
- use `Trie()` data structure for faster runtime search
- automated kaban github-project can be found here: https://github.com/gigiperih/cityx/projects/1
- sneak peak:

![](https://i.ibb.co/D5Vsmvx/20210705-213407.gif)


## data format

From hundreds of thousands item
```json
{
    "country": "RU",
    "name": "Novinki",
    "_id": 519188,
    "coord": {
      "lon": 37.666668,
      "lat": 55.683334
    }
  }
```

## known issue:
- Code coverage is still very low.
- Can be modularized.
- `Trie` implementation truncates some duplicates data.

