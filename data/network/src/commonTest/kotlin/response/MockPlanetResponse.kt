package response

val mockPlanetsResponse =
    """
     {
                            "type": "com.example.features.planet.PlanetsResponse",
                            "status": "Success",
                            "message": "Get planets successful",
                            "planets": [
                                {
                                    "id": "81b5584a-25ac-11ee-9456-0242ac110177",
                                    "name": "Earth",
                                    "description": "Earth is the third planet from the Sun and the only place known in the universe where life has originated and found habitability. This is enabled by Earth being a water world, the only one in the Solar System sustaining liquid surface water.",
                                    "size": "510.1 million km²",
                                    "distanceFromSun": "149.6 million km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                },
                                {
                                    "id": "9eadcaae-25ac-11ee-9456-0242ac110177",
                                    "name": "Jupiter",
                                    "description": "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass more than two and a half times that of all the other planets in the Solar System combined, and slightly less than one one-thousandth the mass of the Sun.",
                                    "size": "61.42 billion km²",
                                    "distanceFromSun": "778.5 million km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                },
                                {
                                    "id": "b93e6dc4-25ac-11ee-9456-0242ac110177",
                                    "name": "Saturn",
                                    "description": "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine and a half times that of Earth. It has only one-eighth the average density of Earth, but is over 95 times more massive.",
                                    "size": "42.7 billion km²",
                                    "distanceFromSun": "1.434 billion km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                },
                                {
                                    "id": "dc2ce626-25ac-11ee-9456-0242ac110177",
                                    "name": "Uranus",
                                    "description": "Uranus is the seventh planet from the Sun and is a gaseous cyan ice giant. Most of the planet is made out of water, ammonia, and methane in a supercritical phase of matter, which in astronomy is called ice or volatiles.",
                                    "size": "8.083 billion km²",
                                    "distanceFromSun": "2.871 billion km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                },
                                {
                                    "id": "f73cb14e-25ac-11ee-9456-0242ac110177",
                                    "name": "Neptune",
                                    "description": "Neptune—the eighth and most distant major planet orbiting our Sun—is dark, cold and whipped by supersonic winds. It was the first planet located through mathematical calculations.",
                                    "size": "7.618 billion km²",
                                    "distanceFromSun": "4.495 billion km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                },
                                {
                                    "id": "066459b9-4d18-42d5-9796-5b5409b8e8c6",
                                    "name": "Mars",
                                    "description": "Mars is the fourth planet and the furthest terrestrial planet from the Sun. The reddish color of its surface is due to finely grained iron(III) oxide dust in the soil, giving it the nickname \"the Red Planet\". Mars’s radius is second smallest among the planets in the Solar System at 3,389.5 km",
                                    "size": "144.4 million km²",
                                    "distanceFromSun": "249.23 million km",
                                    "isPopular": true,
                                    "createdTimestamp": 1689675536508,
                                    "updatedTimestamp": 1689675536508
                                },
                                {
                                    "id": "1ce8a61a-25ac-11ee-9456-0242ac110177",
                                    "name": "Mercury",
                                    "description": "From the surface of Mercury, the Sun would appear more than three times as large as it does when viewed from Earth, and the sunlight would be as much as 11 times brighter.",
                                    "size": "74.8 million km²",
                                    "distanceFromSun": "55.785 million km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                },
                                {
                                    "id": "614520ea-25ac-11ee-9456-0242ac110177",
                                    "name": "Venus",
                                    "description": "Venus is the second planet from the Sun. It is a rocky planet with the densest atmosphere of all the rocky bodies in the Solar System, and the only one with a mass and size that is close to that of its orbital neighbour Earth.",
                                    "size": "460.2 million km²",
                                    "distanceFromSun": "108.2 million km",
                                    "isPopular": false,
                                    "createdTimestamp": 1689678531115,
                                    "updatedTimestamp": 1689678531115
                                }
                            ]
                        }
    """.trimIndent()

val mockPlanetResponse =
    """
   {
        "id": "81b5584a-25ac-11ee-9456-0242ac110177",
        "name": "Earth",
        "description": "Earth is the third planet from the Sun and the only place known in the universe where life has originated and found habitability. This is enabled by Earth being a water world, the only one in the Solar System sustaining liquid surface water.",
        "size": "510.1 million km²",
        "distanceFromSun": "149.6 million km",
        "isPopular": false,
        "createdTimestamp": 1689678531115,
        "updatedTimestamp": 1689678531115
    }

    """.trimIndent()
