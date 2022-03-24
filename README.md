# Arquillian Demo

![build and tests](https://github.com/epomatti/arquillian-demo/workflows/maven/badge.svg)

A demo project for Arquillian with WildFly.

## Getting Started

Running with standalone remote WildFly:

```
mvn test
```

Or... let Arquillian handle the infrastructure for you 😎

```
mvn -Pmanaged test
```


## Setup

You'll need Maven and the JDK:

```
sudo apt update
sudo apt install maven openjdk-11-jdk
```

## Development

Coding tools:

- **(Required)** You need Lombok installed on your IDE. [All major IDEs are supported](https://projectlombok.org/setup/overview).
- Add the JBoss/RedHat Server Connector plugin for your IDE.


## References

[Arquillian Core](http://arquillian.org/arquillian-core/)

[Arquillian Example](https://github.com/tolis-e/arquillian-wildfly-example)
