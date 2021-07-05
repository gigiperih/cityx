# cityx
Find some cities that you wanted to go!

## configuration
- Android Studio `4.2.1`
- Kotlin `v1.4.10`
- Gradle Kotlin DSL
- Android Architecture Component with clean arch approach
- Unit tested in all layers

## feature
- automated kaban github-project can be found here: https://github.com/gigiperih/cityx/projects/1
- apk can be downloaded here 
- sneak peak:

![](https://i.ibb.co/qYcBfxs/114031424-90206500-98a5-11eb-8ab4-e1e8c345f049.gif)


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

