package mappers

import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.mappers.toCharacter
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import kotlin.test.Test
import kotlin.test.assertEquals

//TODO Run in the end
class MapperTests {
    @Test
    fun `character response to character without missing values works`() {
       val mockCharacterResponse = CharacterResponse(
           url = "url/1/",
           name = "C",
           height = "123",
           mass = "234",
           skinColor = "black",
           eyeColor = "blue",
           hairColor = "blond",
           gender = "Male",
           birthYear = "345 BYD",
           filmUrls = emptyList(),
           speciesUrls = emptyList(),
           starshipUrls = emptyList(),
           vehicleUrls = emptyList()
       )

        val character = mockCharacterResponse.toCharacter()

        assertEquals(
            Character(
                id = 1,
                name = "C",
                height = 123,
                birthYear = "345 BYD"
            ),
            character
        )
    }

    @Test
    fun `character response to character with unknown height and birthYear maps to null`() {
        val mockCharacterResponse = CharacterResponse(
            url = "url/1/",
            name = "C",
            height = "unknown",
            mass = "234",
            skinColor = "black",
            eyeColor = "blue",
            hairColor = "blond",
            gender = "Male",
            birthYear = "n/a",
            filmUrls = emptyList(),
            speciesUrls = emptyList(),
            starshipUrls = emptyList(),
            vehicleUrls = emptyList()
        )

        val character = mockCharacterResponse.toCharacter()

        assertEquals(
            Character(
                id = 1,
                name = "C",
                height = null,
                birthYear = null
            ),
            character
        )
    }
}