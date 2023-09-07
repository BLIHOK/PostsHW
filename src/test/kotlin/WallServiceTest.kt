import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.netology.Post
import ru.netology.WallService

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun testAdd() {
        val post = Post(
            id = 0,
            ownerId = 1,
            date = 0,
            text = "Test post",
            reposts = 0,
            views = 0,
            postType = "text",
            canDelete = true,
            canEdit = true,
            markedAsAds = false,
            isFavorite = false,
            likes = Post.Likes()
        )
        val returnedPost = WallService.add(post)
        assertEquals(1, returnedPost.id)
//        WallService.add(post)
//
//        assertEquals(1, post.id)
    }

    @Test
    fun updateExisting() {
        val post = Post(
            id = 1,
            ownerId = 1,
            date = 0,
            text = "Привет",
            reposts = 0,
            views = 0,
            postType = "Простой",
            canDelete = true,
            canEdit = true,
            markedAsAds = false,
            isFavorite = false,
            likes = Post.Likes()
        )

        WallService.add(post)

        val updatedPost = Post(
            id = 1,
            ownerId = 1,
            date = 0,
            text = "Новый привет",
            reposts = 0,
            views = 0,
            postType = "Обновленный",
            canDelete = true,
            canEdit = true,
            markedAsAds = false,
            isFavorite = false,
            likes = Post.Likes()
        )

        val isUpdated = WallService.update(updatedPost)
        assertEquals(true, isUpdated)
    }
    @Test
    fun updateIsNotExisting() {
        val updatedPost = Post(
            id = 1,
            ownerId = 1,
            date = 0,
            text = "Новый привет",
            reposts = 0,
            views = 0,
            postType = "Обновленный",
            canDelete = true,
            canEdit = true,
            markedAsAds = false,
            isFavorite = false,
            likes = Post.Likes()
        )

        val isUpdated = WallService.update(updatedPost)
        assertEquals(false, isUpdated)
    }
}