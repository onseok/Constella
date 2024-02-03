import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    publications.withType<MavenPublication> {
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        pom {
            name.set("Constella")
            description.set("A Compose Multiplatform UI component library designed for creating an engaging, star-shaped rating bar, enhancing user interfaces with customizable, interactive star ratings.")
            url.set("https://github.com/onseok/Constella")

            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://opensource.org/licenses/Apache-2.0")
                }
            }
            developers {
                developer {
                    id.set("onseok")
                    name.set("onseok")
                    email.set("dnjstjr245@gmail.com")
                }
            }
            issueManagement {
                system.set("Github")
                url.set("https://github.com/onseok/Constella/issues")
            }
            scm {
                connection.set("https://github.com/onseok/Constella.git")
                url.set("https://github.com/onseok/Constella")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
