package hr.tvz.android.listapetermanec.Main.Model

data class URLs (
    val regular: String
        )

data class Image (
    val id: String,
    val alt_description: String,
    val urls: URLs
)