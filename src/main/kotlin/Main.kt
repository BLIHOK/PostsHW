package ru.netology

data class Post(
    var id: Int = 0,
    val ownerId: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val reposts: Int = 0,
    val views: Int = 0,
    val postType: String = "",
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val likes: Likes
) {

    data class Likes(
        var quantity: Int = 0,
        val users: MutableList<String> = mutableListOf()
    ) {
        fun addLike(user: String) {
            quantity++
            users.add(user)
        }

        fun removeLike(user: String) {
            if (users.contains(user)) {
                users.remove(user)
                quantity = users.size
            }
        }
    }
}

object WallService {
    private var nextId = 1
    val posts = mutableListOf<Post>()

    fun add(post: Post): Post {
        val postCopy = post.copy(likes = post.likes.copy())
        postCopy.id = nextId++
        posts.add(postCopy)
        return postCopy
    }
    fun update(post: Post): Boolean {
        val existingPostIndex = posts.indexOfFirst { it.id == post.id }
        if (existingPostIndex != -1) {
            posts[existingPostIndex] = post
            return true
        }
        return false
    }

    fun likeById(postId: Int, userId: String) {
        val post = posts.find { it.id == postId }
        if (post != null) {
            post.likes.addLike(userId)
        }
    }

    fun unlikeById(postId: Int, userId: String) {
        val post = posts.find { it.id == postId }
        if (post != null) {
            post.likes.removeLike(userId)
        }
    }

    fun getById(postId: Int): Post? {
        return posts.find { it.id == postId }
    }

    fun clear() {
        posts.clear()
        nextId = 1
    }

}

fun main(args: Array<String>) {
    val likes = Post.Likes(quantity = 0, users = mutableListOf())
    val post = Post(
        1, 1, 2000, "Привет",
        reposts = 0, views = 0,
        postType = "Простой", canDelete = true,
        canEdit = true, markedAsAds = false,
        isFavorite = false, likes = likes
    )
    val userId = "Юзер1"


    val post2 = Post(ownerId = 2, date = 2010, text = "Добрый день", likes = likes)
    val userId2 = "Юзер2"


    val post3 = Post(ownerId = 3, date = 2012, text = "Добрый вечер", likes = likes)
    val userId3 = "Юзер3"


    WallService.add(post)
    WallService.likeById(post.id, userId)

    WallService.add(post2)
    WallService.likeById(post.id, userId2)

    WallService.add(post3)
    WallService.likeById(post2.id, userId3)

    println(post)
    println(post.likes)
    println(post2)
    println(post2.likes)
    println(post3)
    println(post3.likes)

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
        likes = likes
    )

    val isUpdated = WallService.update(updatedPost)
    if (isUpdated) {
        println(true)
    } else {
        println(false)
    }

    val retrievedPost = WallService.getById(1)
    println(retrievedPost)

    WallService.unlikeById(post.id, userId)

    println(post.likes)
}