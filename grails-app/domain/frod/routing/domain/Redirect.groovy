package frod.routing.domain

class Redirect {

    String fromUrl

    String toUrl

    Boolean permanent

    static constraints = {
        permanent(defaultValue: true)
    }
}
