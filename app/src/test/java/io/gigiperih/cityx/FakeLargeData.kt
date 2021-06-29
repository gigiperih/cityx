package io.gigiperih.cityx

object FakeLargeData {
    val jsonSample = """
        [
            {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
            {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
            {"country":"NP","name":"Gorkhā","_id":1283378,"coord":{"lon":84.633331,"lat":28}},
            {"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}},
            {"country":"UA","name":"Holubynka","_id":708546,"coord":{"lon":33.900002,"lat":44.599998}},
            {"country":"NP","name":"Bāgmatī Zone","_id":1283710,"coord":{"lon":85.416664,"lat":28}},
            {"country":"RU","name":"Mar’ina Roshcha","_id":529334,"coord":{"lon":37.611111,"lat":55.796391}},
            {"country":"IN","name":"Republic of India","_id":1269750,"coord":{"lon":77,"lat":20}},
            {"country":"NP","name":"Kathmandu","_id":1283240,"coord":{"lon":85.316666,"lat":27.716667}},
            {"country":"UA","name":"Laspi","_id":703363,"coord":{"lon":33.733334,"lat":44.416668}},
            {"country":"VE","name":"Merida","_id":3632308,"coord":{"lon":-71.144997,"lat":8.598333}},
            {"country":"RU","name":"Vinogradovo","_id":473537,"coord":{"lon":38.545555,"lat":55.423332}},
            {"country":"IQ","name":"Qarah Gawl al ‘Ulyā","_id":384848,"coord":{"lon":45.6325,"lat":35.353889}},
            {"country":"RU","name":"Cherkizovo","_id":569143,"coord":{"lon":37.728889,"lat":55.800835}},
            {"country":"UA","name":"Alupka","_id":713514,"coord":{"lon":34.049999,"lat":44.416668}},
            {"country":"DE","name":"Lichtenrade","_id":2878044,"coord":{"lon":13.40637,"lat":52.398441}},
            {"country":"RU","name":"Zavety Il’icha","_id":464176,"coord":{"lon":37.849998,"lat":56.049999}},
            {"country":"IL","name":"‘Azriqam","_id":295582,"coord":{"lon":34.700001,"lat":31.75}},
            {"country":"IN","name":"Ghūra","_id":1271231,"coord":{"lon":79.883331,"lat":24.766666}},
            {"country":"UA","name":"Tyuzler","_id":690856,"coord":{"lon":34.083332,"lat":44.466667}},
            {"country":"RU","name":"Zaponor’ye","_id":464737,"coord":{"lon":38.861942,"lat":55.639999}},
            {"country":"UA","name":"Il’ichëvka","_id":707716,"coord":{"lon":34.383331,"lat":44.666668}},
            {"country":"UA","name":"Partyzans’ke","_id":697959,"coord":{"lon":34.083332,"lat":44.833332}},
            {"country":"RU","name":"Yurevichi","_id":803611,"coord":{"lon":39.934444,"lat":43.600555}},
            {"country":"GE","name":"Gumist’a","_id":614371,"coord":{"lon":40.973888,"lat":43.026943}},
            {"country":"GE","name":"Ptitsefabrika","_id":874560,"coord":{"lon":40.290558,"lat":43.183613}},
            {"country":"GE","name":"Orekhovo","_id":874652,"coord":{"lon":40.146111,"lat":43.351391}},
            {"country":"NG","name":"Birim","_id":2347078,"coord":{"lon":9.997027,"lat":10.062094}},
            {"country":"RU","name":"Priiskovyy","_id":2051302,"coord":{"lon":132.822495,"lat":42.819168}},
            {"country":"RU","name":"Dzhaga","_id":563692,"coord":{"lon":42.650002,"lat":43.25}},
            {"country":"RU","name":"Tret’ya Rota","_id":481725,"coord":{"lon":39.681389,"lat":43.741943}},
            {"country":"GB","name":"Ruislip","_id":2638976,"coord":{"lon":-0.42341,"lat":51.573441}},
            {"country":"DE","name":"Karow","_id":2892705,"coord":{"lon":13.48117,"lat":52.609039}},
            {"country":"DE","name":"Gatow","_id":2922336,"coord":{"lon":13.18285,"lat":52.483238}},
            {"country":"ZA","name":"Mkuze","_id":975511,"coord":{"lon":32.038609,"lat":-27.616409}},
            {"country":"CN","name":"Lhasa","_id":1280737,"coord":{"lon":91.099998,"lat":29.65}},
            {"country":"TR","name":"İstanbul","_id":745042,"coord":{"lon":28.983311,"lat":41.03508}},
            {"country":"DO","name":"Mao","_id":3496831,"coord":{"lon":-71.078133,"lat":19.551861}},
            {"country":"RU","name":"Russian Federation","_id":2017370,"coord":{"lon":100,"lat":60}},
            {"country":"RU","name":"De-Friz","_id":2045761,"coord":{"lon":131.991394,"lat":43.27861}},
            {"country":"IN","name":"Rumbak","_id":1257986,"coord":{"lon":77.416664,"lat":34.049999}},
            {"country":"RU","name":"Vavibet","_id":476350,"coord":{"lon":34.916668,"lat":67.933334}},
            {"country":"PK","name":"Surtagān Chib","_id":1343000,"coord":{"lon":64.656113,"lat":26.474443}},
            {"country":"LV","name":"Rīgas Rajons","_id":456169,"coord":{"lon":24.333332,"lat":57}},
            {"country":"RU","name":"Verkhneye Shchekotikhino","_id":475279,"coord":{"lon":36.133331,"lat":53}},
            {"country":"UA","name":"Bucha","_id":711349,"coord":{"lon":30.366671,"lat":50.583328}},
            {"country":"PL","name":"Republic of Poland","_id":798544,"coord":{"lon":20,"lat":52}},
            {"country":"PL","name":"Kuchary","_id":3094325,"coord":{"lon":19.383329,"lat":52.150002}},
            {"country":"KN","name":"Brumaire","_id":3575514,"coord":{"lon":-62.73333,"lat":17.299999}},
            {"country":"JP","name":"Ishikawa-ken","_id":1861387,"coord":{"lon":136.770493,"lat":36.77145}},
            {"country":"JP","name":"Matoba","_id":1857578,"coord":{"lon":133.949997,"lat":34.25}},
            {"country":"MM","name":"Pya","_id":1299298,"coord":{"lon":95.599998,"lat":21.51667}},
            {"country":"BA","name":"Kalanac","_id":3256023,"coord":{"lon":18.78389,"lat":44.86861}},
            {"country":"DE","name":"Federal Republic of Germany","_id":2921044,"coord":{"lon":10.5,"lat":51.5}},
            {"country":"DE","name":"Land Nordrhein-Westfalen","_id":2861876,"coord":{"lon":7,"lat":51.5}},
            {"country":"RU","name":"Mutaykutan","_id":802899,"coord":{"lon":47.660641,"lat":42.818859}},
            {"country":"RU","name":"Nalchik","_id":523523,"coord":{"lon":43.618889,"lat":43.498058}},
            {"country":"RU","name":"Kolganov","_id":546448,"coord":{"lon":40.066669,"lat":44.366669}},
            {"country":"RU","name":"Rybatskiy","_id":500023,"coord":{"lon":44.166389,"lat":44.799171}},
            {"country":"AU","name":"Bellara","_id":2207349,"coord":{"lon":153.149597,"lat":-27.063919}},
            {"country":"ZA","name":"Bartlett","_id":7870412,"coord":{"lon":28.25263,"lat":-26.17061}},
            {"country":"ZA","name":"Rietfontein","_id":961935,"coord":{"lon":29.200001,"lat":-25.5}},
            {"country":"NA","name":"Hardap","_id":3371200,"coord":{"lon":17.25,"lat":-24.5}},
            {"country":"ZA","name":"Botswana","_id":1016666,"coord":{"lon":30.533331,"lat":-24.33333}},
            {"country":"AR","name":"El Destierro","_id":3858204,"coord":{"lon":-62.47662,"lat":-24.1}},
            {"country":"US","name":"Jones Crossroads","_id":4070245,"coord":{"lon":-85.484657,"lat":31.21073}},
            {"country":"US","name":"Vernon Parish","_id":4344544,"coord":{"lon":-93.183502,"lat":31.11685}},
            {"country":"US","name":"Pennick","_id":4215307,"coord":{"lon":-81.55899,"lat":31.313}},
            {"country":"US","name":"Black Bear Spring","_id":5285039,"coord":{"lon":-110.288139,"lat":31.386209}},
            {"country":"US","name":"Bee House","_id":4673179,"coord":{"lon":-98.081139,"lat":31.40266}},
            {"country":"CA","name":"Morden","_id":6078447,"coord":{"lon":-98.101357,"lat":49.191898}},
            {"country":"FJ","name":"Nasirotu","_id":2201316,"coord":{"lon":178.25,"lat":-18.033331}},
            {"country":"ID","name":"Sisali","_id":1938756,"coord":{"lon":124.531387,"lat":-9.19167}},
            {"country":"ID","name":"Puntan","_id":2009359,"coord":{"lon":110.553329,"lat":-7.51944}},
            {"country":"CG","name":"Tsiémé-Mandiélé","_id":2566086,"coord":{"lon":15.2875,"lat":-4.22694}},
            {"country":"TZ","name":"Masama","_id":154733,"coord":{"lon":37.183331,"lat":-3.23333}},
            {"country":"ID","name":"Purukcahu","_id":1630349,"coord":{"lon":114.583328,"lat":-0.58333}},
            {"country":"CM","name":"Néméyong II","_id":2224928,"coord":{"lon":13.5,"lat":2.91667}},
            {"country":"ID","name":"Pondok Genteng","_id":6716279,"coord":{"lon":99.0709,"lat":3.2245}},
            {"country":"CF","name":"Mbongoté","_id":2384618,"coord":{"lon":18.283331,"lat":4.25}},
            {"country":"SS","name":"Amiling","_id":378867,"coord":{"lon":32.355831,"lat":4.19417}},
            {"country":"CM","name":"Kélkoto","_id":2230362,"coord":{"lon":11.16667,"lat":4.43333}},
            {"country":"ET","name":"Angetu","_id":343846,"coord":{"lon":39.48333,"lat":6.33333}},
            {"country":"SD","name":"Massa","_id":370366,"coord":{"lon":29.466669,"lat":10.98333}},
            {"country":"SD","name":"Tumko","_id":365618,"coord":{"lon":24.6,"lat":12.01667}},
            {"country":"RU","name":"Moskva","_id":524894,"coord":{"lon":37.606667,"lat":55.761665}},
            {"country":"JP","name":"Japan","_id":1861060,"coord":{"lon":139.753098,"lat":35.68536}},
            {"country":"JP","name":"Hokkaidō","_id":2130037,"coord":{"lon":141.346603,"lat":43.06451}},
            {"country":"ID","name":"Sanggrahan","_id":6199126,"coord":{"lon":110.246109,"lat":-7.46056}},
            {"country":"ID","name":"Karangmangle","_id":6388445,"coord":{"lon":109.0075,"lat":-7.43028}},
            {"country":"RU","name":"Sheremetyevskiy","_id":494806,"coord":{"lon":37.491112,"lat":55.98}},
            {"country":"RU","name":"Yershovo","_id":467104,"coord":{"lon":36.858055,"lat":55.771111}},
            {"country":"RU","name":"Znamenka","_id":462352,"coord":{"lon":35.981392,"lat":52.896671}},
            {"country":"PT","name":"Lisbon","_id":2267057,"coord":{"lon":-9.13333,"lat":38.716671}},
            {"country":"PL","name":"Walbrzych","_id":3082707,"coord":{"lon":16.284321,"lat":50.771412}},
            {"country":"PL","name":"Naklo nad Notecia","_id":3091150,"coord":{"lon":17.60181,"lat":53.142139}},
            {"country":"CN","name":"Zhengzhou","_id":1784658,"coord":{"lon":113.648613,"lat":34.757778}},
            {"country":"GB","name":"Tonyrefail","_id":7301040,"coord":{"lon":-3.41503,"lat":51.580238}},
            {"country":"IN","name":"Bankra","_id":1348747,"coord":{"lon":88.298058,"lat":22.627501}},
            {"country":"RU","name":"Moskovskaya Oblast’","_id":524925,"coord":{"lon":37.628334,"lat":55.75639}},
        ]
    """

    val expectedSample = listOf(
        City(
            country = "UA",
            name = "Hurzuf",
            _id = 707860,
            coord = Coordinate(lon = 34.283333, lat = 44.549999)
        ),
        City(
            country = "RU",
            name = "Novinki",
            _id = 519188,
            coord = Coordinate(lon = 37.666668, lat = 55.683334)
        ),
        City(
            country = "NP",
            name = "Gorkhā",
            _id = 1283378,
            coord = Coordinate(lon = 84.633331, lat = 28.0)
        ),
        City(
            country = "IN",
            name = "State of Haryāna",
            _id = 1270260,
            coord = Coordinate(lon = 76.0, lat = 29.0)
        ),
        City(
            country = "UA",
            name = "Holubynka",
            _id = 708546,
            coord = Coordinate(lon = 33.900002, lat = 44.599998)
        ),
        City(
            country = "NP",
            name = "Bāgmatī Zone",
            _id = 1283710,
            coord = Coordinate(lon = 85.416664, lat = 28.0)
        ),
        City(
            country = "RU",
            name = "Mar’ina Roshcha",
            _id = 529334,
            coord = Coordinate(lon = 37.611111, lat = 55.796391)
        ),
        City(
            country = "IN",
            name = "Republic of India",
            _id = 1269750,
            coord = Coordinate(lon = 77.0, lat = 20.0)
        ),
        City(
            country = "NP",
            name = "Kathmandu",
            _id = 1283240,
            coord = Coordinate(lon = 85.316666, lat = 27.716667)
        ),
        City(
            country = "UA",
            name = "Laspi",
            _id = 703363,
            coord = Coordinate(lon = 33.733334, lat = 44.416668)
        ),
        City(
            country = "VE",
            name = "Merida",
            _id = 3632308,
            coord = Coordinate(lon = -71.144997, lat = 8.598333)
        ),
        City(
            country = "RU",
            name = "Vinogradovo",
            _id = 473537,
            coord = Coordinate(lon = 38.545555, lat = 55.423332)
        ),
        City(
            country = "IQ",
            name = "Qarah Gawl al ‘Ulyā",
            _id = 384848,
            coord = Coordinate(lon = 45.6325, lat = 35.353889)
        ),
        City(
            country = "RU",
            name = "Cherkizovo",
            _id = 569143,
            coord = Coordinate(lon = 37.728889, lat = 55.800835)
        ),
        City(
            country = "UA",
            name = "Alupka",
            _id = 713514,
            coord = Coordinate(lon = 34.049999, lat = 44.416668)
        ),
        City(
            country = "DE",
            name = "Lichtenrade",
            _id = 2878044,
            coord = Coordinate(lon = 13.40637, lat = 52.398441)
        ),
        City(
            country = "RU",
            name = "Zavety Il’icha",
            _id = 464176,
            coord = Coordinate(lon = 37.849998, lat = 56.049999)
        ),
        City(
            country = "IL",
            name = "‘Azriqam",
            _id = 295582,
            coord = Coordinate(lon = 34.700001, lat = 31.75)
        ),
        City(
            country = "IN",
            name = "Ghūra",
            _id = 1271231,
            coord = Coordinate(lon = 79.883331, lat = 24.766666)
        ),
        City(
            country = "UA",
            name = "Tyuzler",
            _id = 690856,
            coord = Coordinate(lon = 34.083332, lat = 44.466667)
        ),
        City(
            country = "RU",
            name = "Zaponor’ye",
            _id = 464737,
            coord = Coordinate(lon = 38.861942, lat = 55.639999)
        ),
        City(
            country = "UA",
            name = "Il’ichëvka",
            _id = 707716,
            coord = Coordinate(lon = 34.383331, lat = 44.666668)
        ),
        City(
            country = "UA",
            name = "Partyzans’ke",
            _id = 697959,
            coord = Coordinate(lon = 34.083332, lat = 44.833332)
        ),
        City(
            country = "RU",
            name = "Yurevichi",
            _id = 803611,
            coord = Coordinate(lon = 39.934444, lat = 43.600555)
        ),
        City(
            country = "GE",
            name = "Gumist’a",
            _id = 614371,
            coord = Coordinate(lon = 40.973888, lat = 43.026943)
        ),
        City(
            country = "GE",
            name = "Ptitsefabrika",
            _id = 874560,
            coord = Coordinate(lon = 40.290558, lat = 43.183613)
        ),
        City(
            country = "GE",
            name = "Orekhovo",
            _id = 874652,
            coord = Coordinate(lon = 40.146111, lat = 43.351391)
        ),
        City(
            country = "NG",
            name = "Birim",
            _id = 2347078,
            coord = Coordinate(lon = 9.997027, lat = 10.062094)
        ),
        City(
            country = "RU",
            name = "Priiskovyy",
            _id = 2051302,
            coord = Coordinate(lon = 132.822495, lat = 42.819168)
        ),
        City(
            country = "RU",
            name = "Dzhaga",
            _id = 563692,
            coord = Coordinate(lon = 42.650002, lat = 43.25)
        ),
        City(
            country = "RU",
            name = "Tret’ya Rota",
            _id = 481725,
            coord = Coordinate(lon = 39.681389, lat = 43.741943)
        ),
        City(
            country = "GB",
            name = "Ruislip",
            _id = 2638976,
            coord = Coordinate(lon = -0.42341, lat = 51.573441)
        ),
        City(
            country = "DE",
            name = "Karow",
            _id = 2892705,
            coord = Coordinate(lon = 13.48117, lat = 52.609039)
        ),
        City(
            country = "DE",
            name = "Gatow",
            _id = 2922336,
            coord = Coordinate(lon = 13.18285, lat = 52.483238)
        ),
        City(
            country = "ZA",
            name = "Mkuze",
            _id = 975511,
            coord = Coordinate(lon = 32.038609, lat = -27.616409)
        ),
        City(
            country = "CN",
            name = "Lhasa",
            _id = 1280737,
            coord = Coordinate(lon = 91.099998, lat = 29.65)
        ),
        City(
            country = "TR",
            name = "İstanbul",
            _id = 745042,
            coord = Coordinate(lon = 28.983311, lat = 41.03508)
        ),
        City(
            country = "DO",
            name = "Mao",
            _id = 3496831,
            coord = Coordinate(lon = -71.078133, lat = 19.551861)
        ),
        City(
            country = "RU",
            name = "Russian Federation",
            _id = 2017370,
            coord = Coordinate(lon = 100.0, lat = 60.0)
        ),
        City(
            country = "RU",
            name = "De-Friz",
            _id = 2045761,
            coord = Coordinate(lon = 131.991394, lat = 43.27861)
        ),
        City(
            country = "IN",
            name = "Rumbak",
            _id = 1257986,
            coord = Coordinate(lon = 77.416664, lat = 34.049999)
        ),
        City(
            country = "RU",
            name = "Vavibet",
            _id = 476350,
            coord = Coordinate(lon = 34.916668, lat = 67.933334)
        ),
        City(
            country = "PK",
            name = "Surtagān Chib",
            _id = 1343000,
            coord = Coordinate(lon = 64.656113, lat = 26.474443)
        ),
        City(
            country = "LV",
            name = "Rīgas Rajons",
            _id = 456169,
            coord = Coordinate(lon = 24.333332, lat = 57.0)
        ),
        City(
            country = "RU",
            name = "Verkhneye Shchekotikhino",
            _id = 475279,
            coord = Coordinate(lon = 36.133331, lat = 53.0)
        ),
        City(
            country = "UA",
            name = "Bucha",
            _id = 711349,
            coord = Coordinate(lon = 30.366671, lat = 50.583328)
        ),
        City(
            country = "PL",
            name = "Republic of Poland",
            _id = 798544,
            coord = Coordinate(lon = 20.0, lat = 52.0)
        ),
        City(
            country = "PL",
            name = "Kuchary",
            _id = 3094325,
            coord = Coordinate(lon = 19.383329, lat = 52.150002)
        ),
        City(
            country = "KN",
            name = "Brumaire",
            _id = 3575514,
            coord = Coordinate(lon = -62.73333, lat = 17.299999)
        ),
        City(
            country = "JP",
            name = "Ishikawa-ken",
            _id = 1861387,
            coord = Coordinate(lon = 136.770493, lat = 36.77145)
        ),
        City(
            country = "JP",
            name = "Matoba",
            _id = 1857578,
            coord = Coordinate(lon = 133.949997, lat = 34.25)
        ),
        City(
            country = "MM",
            name = "Pya",
            _id = 1299298,
            coord = Coordinate(lon = 95.599998, lat = 21.51667)
        ),
        City(
            country = "BA",
            name = "Kalanac",
            _id = 3256023,
            coord = Coordinate(lon = 18.78389, lat = 44.86861)
        ),
        City(
            country = "DE",
            name = "Federal Republic of Germany",
            _id = 2921044,
            coord = Coordinate(lon = 10.5, lat = 51.5)
        ),
        City(
            country = "DE",
            name = "Land Nordrhein-Westfalen",
            _id = 2861876,
            coord = Coordinate(lon = 7.0, lat = 51.5)
        ),
        City(
            country = "RU",
            name = "Mutaykutan",
            _id = 802899,
            coord = Coordinate(lon = 47.660641, lat = 42.818859)
        ),
        City(
            country = "RU",
            name = "Nalchik",
            _id = 523523,
            coord = Coordinate(lon = 43.618889, lat = 43.498058)
        ),
        City(
            country = "RU",
            name = "Kolganov",
            _id = 546448,
            coord = Coordinate(lon = 40.066669, lat = 44.366669)
        ),
        City(
            country = "RU",
            name = "Rybatskiy",
            _id = 500023,
            coord = Coordinate(lon = 44.166389, lat = 44.799171)
        ),
        City(
            country = "AU",
            name = "Bellara",
            _id = 2207349,
            coord = Coordinate(lon = 153.149597, lat = -27.063919)
        ),
        City(
            country = "ZA",
            name = "Bartlett",
            _id = 7870412,
            coord = Coordinate(lon = 28.25263, lat = -26.17061)
        ),
        City(
            country = "ZA",
            name = "Rietfontein",
            _id = 961935,
            coord = Coordinate(lon = 29.200001, lat = -25.5)
        ),
        City(
            country = "NA",
            name = "Hardap",
            _id = 3371200,
            coord = Coordinate(lon = 17.25, lat = -24.5)
        ),
        City(
            country = "ZA",
            name = "Botswana",
            _id = 1016666,
            coord = Coordinate(lon = 30.533331, lat = -24.33333)
        ),
        City(
            country = "AR",
            name = "El Destierro",
            _id = 3858204,
            coord = Coordinate(lon = -62.47662, lat = -24.1)
        ),
        City(
            country = "US",
            name = "Jones Crossroads",
            _id = 4070245,
            coord = Coordinate(lon = -85.484657, lat = 31.21073)
        ),
        City(
            country = "US",
            name = "Vernon Parish",
            _id = 4344544,
            coord = Coordinate(lon = -93.183502, lat = 31.11685)
        ),
        City(
            country = "US",
            name = "Pennick",
            _id = 4215307,
            coord = Coordinate(lon = -81.55899, lat = 31.313)
        ),
        City(
            country = "US",
            name = "Black Bear Spring",
            _id = 5285039,
            coord = Coordinate(lon = -110.288139, lat = 31.386209)
        ),
        City(
            country = "US",
            name = "Bee House",
            _id = 4673179,
            coord = Coordinate(lon = -98.081139, lat = 31.40266)
        ),
        City(
            country = "CA",
            name = "Morden",
            _id = 6078447,
            coord = Coordinate(lon = -98.101357, lat = 49.191898)
        ),
        City(
            country = "FJ",
            name = "Nasirotu",
            _id = 2201316,
            coord = Coordinate(lon = 178.25, lat = -18.033331)
        ),
        City(
            country = "ID",
            name = "Sisali",
            _id = 1938756,
            coord = Coordinate(lon = 124.531387, lat = -9.19167)
        ),
        City(
            country = "ID",
            name = "Puntan",
            _id = 2009359,
            coord = Coordinate(lon = 110.553329, lat = -7.51944)
        ),
        City(
            country = "CG",
            name = "Tsiémé-Mandiélé",
            _id = 2566086,
            coord = Coordinate(lon = 15.2875, lat = -4.22694)
        ),
        City(
            country = "TZ",
            name = "Masama",
            _id = 154733,
            coord = Coordinate(lon = 37.183331, lat = -3.23333)
        ),
        City(
            country = "ID",
            name = "Purukcahu",
            _id = 1630349,
            coord = Coordinate(lon = 114.583328, lat = -0.58333)
        ),
        City(
            country = "CM",
            name = "Néméyong II",
            _id = 2224928,
            coord = Coordinate(lon = 13.5, lat = 2.91667)
        ),
        City(
            country = "ID",
            name = "Pondok Genteng",
            _id = 6716279,
            coord = Coordinate(lon = 99.0709, lat = 3.2245)
        ),
        City(
            country = "CF",
            name = "Mbongoté",
            _id = 2384618,
            coord = Coordinate(lon = 18.283331, lat = 4.25)
        ),
        City(
            country = "SS",
            name = "Amiling",
            _id = 378867,
            coord = Coordinate(lon = 32.355831, lat = 4.19417)
        ),
        City(
            country = "CM",
            name = "Kélkoto",
            _id = 2230362,
            coord = Coordinate(lon = 11.16667, lat = 4.43333)
        ),
        City(
            country = "ET",
            name = "Angetu",
            _id = 343846,
            coord = Coordinate(lon = 39.48333, lat = 6.33333)
        ),
        City(
            country = "SD",
            name = "Massa",
            _id = 370366,
            coord = Coordinate(lon = 29.466669, lat = 10.98333)
        ),
        City(
            country = "SD",
            name = "Tumko",
            _id = 365618,
            coord = Coordinate(lon = 24.6, lat = 12.01667)
        ),
        City(
            country = "RU",
            name = "Moskva",
            _id = 524894,
            coord = Coordinate(lon = 37.606667, lat = 55.761665)
        ),
        City(
            country = "JP",
            name = "Japan",
            _id = 1861060,
            coord = Coordinate(lon = 139.753098, lat = 35.68536)
        ),
        City(
            country = "JP",
            name = "Hokkaidō",
            _id = 2130037,
            coord = Coordinate(lon = 141.346603, lat = 43.06451)
        ),
        City(
            country = "ID",
            name = "Sanggrahan",
            _id = 6199126,
            coord = Coordinate(lon = 110.246109, lat = -7.46056)
        ),
        City(
            country = "ID",
            name = "Karangmangle",
            _id = 6388445,
            coord = Coordinate(lon = 109.0075, lat = -7.43028)
        ),
        City(
            country = "RU",
            name = "Sheremetyevskiy",
            _id = 494806,
            coord = Coordinate(lon = 37.491112, lat = 55.98)
        ),
        City(
            country = "RU",
            name = "Yershovo",
            _id = 467104,
            coord = Coordinate(lon = 36.858055, lat = 55.771111)
        ),
        City(
            country = "RU",
            name = "Znamenka",
            _id = 462352,
            coord = Coordinate(lon = 35.981392, lat = 52.896671)
        ),
        City(
            country = "PT",
            name = "Lisbon",
            _id = 2267057,
            coord = Coordinate(lon = -9.13333, lat = 38.716671)
        ),
        City(
            country = "PL",
            name = "Walbrzych",
            _id = 3082707,
            coord = Coordinate(lon = 16.284321, lat = 50.771412)
        ),
        City(
            country = "PL",
            name = "Naklo nad Notecia",
            _id = 3091150,
            coord = Coordinate(lon = 17.60181, lat = 53.142139)
        ),
        City(
            country = "CN",
            name = "Zhengzhou",
            _id = 1784658,
            coord = Coordinate(lon = 113.648613, lat = 34.757778)
        ),
        City(
            country = "GB",
            name = "Tonyrefail",
            _id = 7301040,
            coord = Coordinate(lon = -3.41503, lat = 51.580238)
        ),
        City(
            country = "IN",
            name = "Bankra",
            _id = 1348747,
            coord = Coordinate(lon = 88.298058, lat = 22.627501)
        ),
        City(
            country = "RU",
            name = "Moskovskaya Oblast’",
            _id = 524925,
            coord = Coordinate(lon = 37.628334, lat = 55.75639)
        )
    )
}