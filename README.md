# arquillian-demo

## Getting started

```
mvn install
```

## Testing



## Setup

Install the JDK

```
wget https://download.java.net/java/GA/jdk14.0.2/205943a0976c4ed48cb16f1043c5c647/12/GPL/openjdk-14.0.2_linux-x64_bin.tar.gz
tar xvf openjdk-14.0.2_linux-x64_bin.tar.gz
sudo mv jdk-14.0.2 /opt/

sudo tee /etc/profile.d/jdk14.sh <<EOF
export JAVA_HOME=/opt/jdk-14.0.2
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

source /etc/profile.d/jdk14.sh
```

Install Maven

```
sudo apt update
sudo apt install maven
```

Lombok

Add the Lombok extension to your IDE

Wildfly

Use RedHat addon to download, install and run WildFly.