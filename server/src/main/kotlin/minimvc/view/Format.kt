package minimvc.view

enum class Format(val contentType: String) {

    HTML5("text/html; charset=utf-8"),
    CSS3("text/css"),
    JAVASCRIPT("text/javascript"),
    IMAGE_APNG("image/apng"),
    IMAGE_AVIV("image/avif"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpg"),
    IMAGE_PNG("image/png"),
    IMAGE_SVG("image/svg+xml"),
    IMAGE_WEBP("image/webp"),
    RAW_BYTES("application/octet-stream"),
    XML("application/xml"),
    XHTML("application/xhtml+xml")
}
