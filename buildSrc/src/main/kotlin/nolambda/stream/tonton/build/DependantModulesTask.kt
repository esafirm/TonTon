package nolambda.stream.tonton.build

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.internal.impldep.com.google.common.graph.Graphs
import org.gradle.internal.impldep.com.google.common.graph.MutableValueGraph
import org.gradle.internal.impldep.com.google.common.graph.ValueGraphBuilder

abstract class DependantModulesTask : DefaultTask() {
    @Option(
        option = "project-path",
        description = "The project path to find dependencies for"
    )
    @Input
    lateinit var projectPath: String

    @TaskAction
    fun action() {
        val rootProject = project.rootProject

        val buildGraph: MutableValueGraph<Project, Configuration> =
            ValueGraphBuilder.directed()
                .allowsSelfLoops(false)
                .expectedNodeCount(rootProject.subprojects.size)
                .build()


        rootProject.subprojects.forEach { sourceProject ->
            buildGraph.addNode(sourceProject)
            sourceProject
                .configurations
                .flatMap { configuration ->
                    configuration
                        .dependencies
                        .filterIsInstance<ProjectDependency>()
                        .map { projectDependency -> configuration to projectDependency }
                }.forEach { (configuration, projectDependency) ->
                    if (sourceProject != projectDependency.dependencyProject) {
                        // Invert the order since we are only interested in reverse dependencies
                        buildGraph.putEdgeValue(
                            projectDependency.dependencyProject,
                            sourceProject,
                            configuration
                        )
                    }
                }
        }

        val projectToFind = rootProject.project(projectPath)

        val dependants = Graphs.reachableNodes(buildGraph.asGraph(), projectToFind)

        logger.quiet(buildString {
            appendLine()
            append("${projectToFind.path}'s dependants :")
            appendLine()
            append(
                dependants
                    .filter { it != projectToFind }
                    .joinToString(separator = "\n") { it.path }
            )
        })
        logger.quiet("----------\n")

        val subgraph = Graphs.inducedSubgraph(buildGraph, dependants)
        subgraph
            .edges()
            .forEach { endpointPair ->
                val configuration = buildGraph
                    .edgeValue(endpointPair)
                    .map { it.name }
                    .orElse("")
                val sourceProject = endpointPair.nodeV().path
                val dependencyProject = endpointPair.nodeU().path
                logger.quiet(
                    """|$sourceProject
	                   |  $configuration(project("$dependencyProject"))"""
                        .trimMargin()
                )
            }
    }

    companion object {
        fun attach(target: Project) {
            target.tasks.register("dependantModules", DependantModulesTask::class.java)
        }
    }
}