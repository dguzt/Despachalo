package org.guzman.despachalo.tasks

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import javax.crypto.SecretKey

abstract class GenJwtKeyTask extends DefaultTask {

    @TaskAction
    def generate() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
        String secretString = Encoders.BASE64.encode(key.getEncoded())

        println "jwt secret key ==> ${secretString}"
    }
}
