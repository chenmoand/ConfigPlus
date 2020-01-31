package com.brageast.configplus.bean

data class Library(
        val groupId: String? = null,
        val artifactId: String? = null,
        val version: String? = null,
        val downloadLink: String? = null
) {
    fun toGroupBy(): String = "$groupId : $artifactId".toLowerCase()

    fun toLink() = downloadLink ?: "https://maven.aliyun.com/repository/public/${groupId?.replace(".","/")}/${artifactId}/${version}/${artifactId}-${version}.jar"
}