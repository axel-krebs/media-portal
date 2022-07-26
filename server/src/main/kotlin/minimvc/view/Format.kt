package minimvc.view

enum class Format(val contentType: String, val fileSuffix: String) {

    HTML5("text/html; charset=utf-8", "html"),
    CSS3("text/css", "css"),
    JAVASCRIPT("text/javascript","js"),
    IMAGE_APNG("image/apng","apng"),
    IMAGE_AVIV("image/avif","avi"),
    IMAGE_GIF("image/gif","gif"),
    IMAGE_JPEG("image/jpg","jpg"),
    IMAGE_PNG("image/png","png"),
    IMAGE_SVG("image/svg+xml","svg"),
    IMAGE_WEBP("image/webp","webp"),
    RAW_BYTES("application/octet-stream",""),
    XML("application/xml","xml"),
    XHTML("application/xhtml+xml","xhtml");

    companion object {
        fun getFormatForPath(path: String?): Format {
            if(null != path && path.isNotBlank()) {
                if (path.endsWith(HTML5.fileSuffix)) return HTML5
                if (path.endsWith(CSS3.fileSuffix)) return CSS3
                if(path.endsWith(JAVASCRIPT.fileSuffix)) return JAVASCRIPT
                if(path.endsWith(IMAGE_APNG.fileSuffix)) return IMAGE_APNG
                if(path.endsWith(IMAGE_AVIV.fileSuffix)) return IMAGE_AVIV
                if (path.endsWith(IMAGE_GIF.fileSuffix)) return IMAGE_GIF
                if(path.endsWith(IMAGE_JPEG.fileSuffix)) return IMAGE_JPEG
                if(path.endsWith(IMAGE_PNG.fileSuffix)) return IMAGE_PNG
                if(path.endsWith(IMAGE_SVG.fileSuffix)) return IMAGE_SVG
                if(path.endsWith(IMAGE_WEBP.fileSuffix)) return IMAGE_WEBP
                if(path.endsWith(XML.fileSuffix)) return XML
                if(path.endsWith(XHTML.fileSuffix)) return XHTML
            }
            // should we introduce a "null-format"?
            return RAW_BYTES
        }
    }
}