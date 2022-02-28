package com.edbinns.starwarsapp.utils.data


fun getFilmsImages(): String{
    return """[
    {
        "name": "A New Hope",
        "image":"https:\\static.wikia.nocookie.net\starwars\images\0\06\Star_Wars_Style_A_poster_1977.jpg\revision\latest?cb=20100708051712"
    },
    {
        "name": "The Empire Strikes Back",
        "image":"https:\\m.media-amazon.com\images\M\MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg"
    },
    {
        "name": "Return of the Jedi",
        "image":"https:\\m.media-amazon.com\images\M\MV5BOWZlMjFiYzgtMTUzNC00Y2IzLTk1NTMtZmNhMTczNTk0ODk1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg"
    },
    {
        "name": "The Phantom Menace",
        "image":"https:\\m.media-amazon.com\images\M\MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg"
    },
    {
        "name": "Attack of the Clones",
        "image":"https:\\m.media-amazon.com\images\M\MV5BMDAzM2M0Y2UtZjRmZi00MzVlLTg4MjEtOTE3NzU5ZDVlMTU5XkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_.jpg"
    },
    {
        "name": "Revenge of the Sith",
        "image":"https:\\www.denofgeek.com\wp-content\uploads\2019\11\star-wars-revenge-sith-hero_0.jpg?resize=768%2C432"
    }
]""".replace('\\','/').trimIndent()
}