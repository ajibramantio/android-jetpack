package com.dicoding.movielist.utils

import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse

object DataDummy {

    fun generateDummyMovie(): List<FilmEntity> {

        val movies = ArrayList<FilmEntity>()

        movies.add(FilmEntity(
            "m01",
            "Captain America: The First Avenger",
            "Action, Adventure, Sci-Fi",
            "Steve Rogers, a rejected military soldier, transforms into Captain America after taking a dose of a \"Super-Soldier serum\". But being Captain America comes at a price as he attempts to take down a war monger and a terrorist organization.",
            69,
            2011,
            124,
            "@drawable/poster_ca_first",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m02",
                "Captain Marvel",
                "Action, Adventure, Sci-Fi",
                "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.",
                69,
                2019,
                123,
                "@drawable/poster_cm",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m03",
                "Iron Man",
                "Action, Adventure, Sci-Fi",
                "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
                79,
                2008,
                126,
                "@drawable/poster_im",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m04",
                "Iron Man 2",
                "Action, Adventure, Sci-Fi",
                "With the world now aware of his identity as Iron Man, Tony Stark must contend with both his declining health and a vengeful mad man with ties to his father's legacy.",
                70,
                2010,
                124,
                "@drawable/poster_im_2",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m05",
                "Thor",
                "Action, Adventure, Fantasy",
                "The powerful but arrogant god Thor is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.",
                70,
                2011,
                115,
                "@drawable/poster_th",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m06",
                "The Avengers",
                "Action, Adventure, Sci-Fi",
                "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                80,
                2012,
                143,
                "@drawable/poster_av",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m07",
                "Thor: The Dark World",
                "Action, Adventure, Fantasy",
                "When the Dark Elves attempt to plunge the universe into darkness, Thor must embark on a perilous and personal journey that will reunite him with doctor Jane Foster.",
                69,
                2013,
                112,
                "@drawable/poster_th_dark",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m08",
                "Iron Man 3",
                "Action, Adventure, Sci-Fi",
                "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.",
                71,
                2013,
                130,
                "@drawable/poster_im_3",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m09",
                "Captain America: The Winter Soldier",
                "Action, Adventure, Sci-Fi",
                "As Steve Rogers struggles to embrace his role in the modern world, he teams up with a fellow Avenger and S.H.I.E.L.D agent, Black Widow, to battle a new threat from history: an assassin known as the Winter Soldier.",
                77,
                2014,
                136,
                "@drawable/poster_ca_winter",
            false,
            "movie")
        )
        movies.add(FilmEntity(
                "m10",
                "Guardians of The Galaxy",
                "Action, Adventure, Comedy",
                "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.",
                80,
                2014,
                121,
                "@drawable/poster_guardians",
            false,
            "movie")
        )

        return movies
    }

    fun generateDummyTvShows(): List<FilmEntity> {

        val tvShows = ArrayList<FilmEntity>()

        tvShows.add(FilmEntity(
                "t01",
                "The Simpsons",
                "Animation, Comedy",
                "The satiric adventures of a working-class family in the misfit city of Springfield.",
                86,
                1989,
                22,
                "R.drawable.poster_simpsons",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t02",
                "Friends",
                "Comedy, Romance",
                "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
                89,
                1994,
                22,
                "R.drawable.poster_friends",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t03",
                "How I Met Your Mother",
                "Comedy, Romance",
                "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
                83,
                2005,
                22,
                "R.drawable.poster_himym",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t04",
                "Money Heist",
                "Action, Crime, Mystery",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                83,
                2017,
                70,
                "R.drawable.poster_money",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t05",
                "Stranger Things",
                "Drama, Fantasy, Horror",
                "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
                87,
                2016,
                51,
                "R.drawable.poster_stranger",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t06",
                "The Mandalorian",
                "Action, Adventure, Sci-Fi",
                "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
                88,
                2019,
                40,
                "R.drawable.poster_mandalorian",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t07",
                "Game of Thrones",
                "Action, Adventure, Drama",
                "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
                93,
                2011,
                57,
                "R.drawable.poster_got",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t08",
                "Attack on Titan",
                "Animation, Action, Adventure",
                "After his hometown is destroyed and his mother is killed, young Eren Jaeger vows to cleanse the earth of the giant humanoid Titans that have brought humanity to the brink of extinction.",
                89,
                2013,
                24,
                "R.drawable.poster_aot",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t09",
                "Wanda Vision",
                "Action, Comedy, Drama",
                "Blends the style of classic sitcoms with the MCU, in which Wanda Maximoff and Vision - two super-powered beings living their ideal suburban lives - begin to suspect that everything is not as it seems.",
                81,
                2021,
                27,
                "R.drawable.poster_wanda",
            false,
            "tvshow")
        )
        tvShows.add(FilmEntity(
                "t10",
                "The Walking Dead",
                "Drama, Horror, Thriller",
                "Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins and must lead a group of survivors to stay alive.",
                82,
                2010,
                44,
                "R.drawable.poster_walking_dead",
            false,
            "tvshow")
        )

        return tvShows
    }

    fun generateRemoteDummyMovie(): List<MovieResponse> {

        val movies = ArrayList<MovieResponse>()

        movies.add(MovieResponse(
                "m01",
                "Captain America: The First Avenger",
                "Action, Adventure, Sci-Fi",
                "Steve Rogers, a rejected military soldier, transforms into Captain America after taking a dose of a \"Super-Soldier serum\". But being Captain America comes at a price as he attempts to take down a war monger and a terrorist organization.",
                69,
                2011,
                124,
                "@drawable/poster_ca_first",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m02",
                "Captain Marvel",
                "Action, Adventure, Sci-Fi",
                "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.",
                69,
                2019,
                123,
                "@drawable/poster_cm",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m03",
                "Iron Man",
                "Action, Adventure, Sci-Fi",
                "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
                79,
                2008,
                126,
                "@drawable/poster_im",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m04",
                "Iron Man 2",
                "Action, Adventure, Sci-Fi",
                "With the world now aware of his identity as Iron Man, Tony Stark must contend with both his declining health and a vengeful mad man with ties to his father's legacy.",
                70,
                2010,
                124,
                "@drawable/poster_im_2",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m05",
                "Thor",
                "Action, Adventure, Fantasy",
                "The powerful but arrogant god Thor is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.",
                70,
                2011,
                115,
                "@drawable/poster_th",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m06",
                "The Avengers",
                "Action, Adventure, Sci-Fi",
                "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                80,
                2012,
                143,
                "@drawable/poster_av",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m07",
                "Thor: The Dark World",
                "Action, Adventure, Fantasy",
                "When the Dark Elves attempt to plunge the universe into darkness, Thor must embark on a perilous and personal journey that will reunite him with doctor Jane Foster.",
                69,
                2013,
                112,
                "@drawable/poster_th_dark",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m08",
                "Iron Man 3",
                "Action, Adventure, Sci-Fi",
                "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.",
                71,
                2013,
                130,
                "@drawable/poster_im_3",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m09",
                "Captain America: The Winter Soldier",
                "Action, Adventure, Sci-Fi",
                "As Steve Rogers struggles to embrace his role in the modern world, he teams up with a fellow Avenger and S.H.I.E.L.D agent, Black Widow, to battle a new threat from history: an assassin known as the Winter Soldier.",
                77,
                2014,
                136,
                "@drawable/poster_ca_winter",
            false,
            "movie")
        )
        movies.add(MovieResponse(
                "m10",
                "Guardians of The Galaxy",
                "Action, Adventure, Comedy",
                "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.",
                80,
                2014,
                121,
                "@drawable/poster_guardians",
            false,
            "movie")
        )

        return movies
    }

    fun generateRemoteDummyTvShows(): List<TvShowResponse> {

        val tvShows = ArrayList<TvShowResponse>()

        tvShows.add(TvShowResponse(
            "t01",
            "The Simpsons",
            "Animation, Comedy",
            "The satiric adventures of a working-class family in the misfit city of Springfield.",
            86,
            1989,
            22,
            "R.drawable.poster_simpsons",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t02",
            "Friends",
            "Comedy, Romance",
            "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
            89,
            1994,
            22,
            "R.drawable.poster_friends",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t03",
            "How I Met Your Mother",
            "Comedy, Romance",
            "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
            83,
            2005,
            22,
            "R.drawable.poster_himym",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t04",
            "Money Heist",
            "Action, Crime, Mystery",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            83,
            2017,
            70,
            "R.drawable.poster_money",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t05",
            "Stranger Things",
            "Drama, Fantasy, Horror",
            "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
            87,
            2016,
            51,
            "R.drawable.poster_stranger",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t06",
            "The Mandalorian",
            "Action, Adventure, Sci-Fi",
            "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
            88,
            2019,
            40,
            "R.drawable.poster_mandalorian",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t07",
            "Game of Thrones",
            "Action, Adventure, Drama",
            "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
            93,
            2011,
            57,
            "R.drawable.poster_got",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t08",
            "Attack on Titan",
            "Animation, Action, Adventure",
            "After his hometown is destroyed and his mother is killed, young Eren Jaeger vows to cleanse the earth of the giant humanoid Titans that have brought humanity to the brink of extinction.",
            89,
            2013,
            24,
            "R.drawable.poster_aot",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t09",
            "Wanda Vision",
            "Action, Comedy, Drama",
            "Blends the style of classic sitcoms with the MCU, in which Wanda Maximoff and Vision - two super-powered beings living their ideal suburban lives - begin to suspect that everything is not as it seems.",
            81,
            2021,
            27,
            "R.drawable.poster_wanda",
            false,
            "tvshow")
        )
        tvShows.add(TvShowResponse(
            "t10",
            "The Walking Dead",
            "Drama, Horror, Thriller",
            "Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins and must lead a group of survivors to stay alive.",
            82,
            2010,
            44,
            "R.drawable.poster_walking_dead",
            false,
            "tvshow")
        )

        return tvShows
    }
}