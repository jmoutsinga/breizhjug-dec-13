import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import groovy.json.JsonSlurper

class ChuckNorrisTask extends DefaultTask {
    @Input String firstName = "Chuck"
    @Input String lastName = "Norris"
    @OutputFile File joke = new File("${project.buildDir}/joke.txt")

    @TaskAction
    void status() {
        def slurper = new JsonSlurper()
        def json = slurper.parse(
                new URL("http://api.icndb.com/jokes/random?firstName=$firstName&lastName=$lastName").newReader())
        joke.write "${json.value.joke}\n"
        logger.lifecycle "${json.value.joke}"
    }
}