# Java-Promise

A Java implementation of JavaScript's promises.

Can be used with threads from the default
`ThreadedPromise` implementation, or custom
implementations can be made.

---

**Import the API**

### With [Jitpack](https://jitpack.io/) 

**In a Maven project**

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>..</groupId>
    <artifactId>..</artifactId>
    <version>..</version>

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.github.NerjalNosk</groupId>
            <artifactId>Promise</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</project>
```

**In a Gradle project**

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.NerjalNosk:Promise:1.0.0"
}
```

### With the GitHub package system

Please refer to the [Maven](
https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package)
and [Gradle](
https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package)
pages for more information