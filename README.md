
# Yape Test

Test project for the position of Mobile Android Engineer

## API Reference

### themealdb api to recipe app

#### https://www.themealdb.com

#### Search meal by name

```http
  GET /api/json/v1/1/search.php?s=
```


#### Filter by main ingredient

```http
  GET /api/json/v1/1/filter.php?i=
```

#### Lookup full meal details by id

```http
  GET /api/json/v1/1/lookup.php?i=
```

### restcountries api to get Lat and Lng from codeArea

#### https://restcountries.com/

#### Lookup full meal details by id

```http
  GET /v3.1/alpha/{code}
```


## Third Party Libraries and Arquitechure

- Jetpack Navigation Component
- Hilt
- Retrofit
- ConstraintLayout
- Moshi (JSON library)
- Coil
- Google Maps SDK
- Other

## Arquitechure and Pattern
- MVVM Patter 
- ViewModel
- Clean Arquitechure (package Data, DI, Repository, UI)
- Mappers to retrieve layer models from external data sources and transform them into data for the UI
- Repository Patter


## Screenshots

<img src="https://i.imgur.com/bxBSoGr.png" width="300">
<img src="https://i.imgur.com/Snjryig.png" width="300">
<img src="https://i.imgur.com/utiHMw0.png" width="300">

