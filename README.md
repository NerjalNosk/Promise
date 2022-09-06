# Java-Promise

A Java implementation of JavaScript's promises.

Can be used with threads from the default
`ThreadedPromise` implementation, or custom
implementations can be made.

---

**Import the API**

Use [Jitpack](https://jitpack.io/) to import the
API in your own project.

**With Maven**

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

**With Gradle**

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.NerjalNosk:Promise:1.0.0"
}
```