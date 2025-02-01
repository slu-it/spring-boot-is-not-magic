package bfdif

import java.io.File

fun getClasses(classLoader: ClassLoader, rootPackage: Package): Set<Class<*>> {
    val rootPackageName = rootPackage.name
    val rootPackagePath = rootPackageName.replace('.', '/')

    val packageResource = classLoader.getResource(rootPackagePath)
        ?: error("package path $rootPackagePath not found")
    val packageFolder = File(packageResource.path)

    return getClasses(classLoader, rootPackagePath, packageFolder)
}

private fun getClasses(classLoader: ClassLoader, basePackagePath: String, folder: File): Set<Class<*>> {
    val files = folder.listFiles() ?: return emptySet()
    return buildSet {
        files.filter(::isClassFile)
            .forEach { file ->
                val subPath = folder.path.split(basePackagePath).last()
                val packagePath = basePackagePath + subPath
                val packageName = packagePath.replace('/', '.')
                val className = packageName + "." + file.nameWithoutExtension

                try {
                    add(Class.forName(className, true, classLoader))
                } catch (e: ClassNotFoundException) {
                    println("Could not initialize class $className - ${e.message}")
                }
            }
        files.filter(::isPackage)
            .forEach { folder ->
                addAll(getClasses(classLoader, basePackagePath, folder))
            }
    }
}

private fun isClassFile(it: File) = it.isFile && it.extension == "class"
private fun isPackage(it: File) = it.isDirectory
