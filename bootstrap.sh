#!/bin/bash

# This is a first pass at a shell script for configuring a clean Linux installation ready to run sysconfws.
# 
# At present this means installing Java (OpenJDK), Scala and SBT.  However, this will probably move to just
# installing Java and incorporating sbt launcher into the build package.

scalaversion=2.10.2
sbtversion=0.13.0

# Update packages
sudo apt-get update
# Install Java 7 (Open JDK)
sudo apt-get install -y openjdk-7-jdk
# Download Scala 2.10.2
wget http://www.scala-lang.org/files/archive/scala-$scalaversion.tgz
tar -xzf scala-$scalaversion.tgz
rm scala-$scalaversion.tgz
sudo mkdir -p /opt/share/java
sudo mv scala-$scalaversion /opt/share/java/
sudo ln -s /opt/share/java/scala-$scalaversion /opt/share/java/scala
sudo mkdir -p /opt/bin
sudo ln -s /opt/share/java/scala/bin/scala /opt/bin/scala
sudo sh -c 'echo "PATH=$PATH:/opt/bin" > /etc/profile.d/opt.sh'
. /etc/profile.d/opt.sh
wget http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/$sbtversion/sbt-launch.jar
sudo mkdir -p /opt/share/java/sbt-$sbtversion
sudo mv sbt-launch.jar /opt/share/java/sbt-$sbtversion/
sudo ln -s /opt/share/java/sbt-$sbtversion /opt/share/java/sbt
cat > scalas <<EOF
#!/bin/bash

sbtlaunch=/opt/share/java/sbt/sbt-launch.jar

java -Dsbt.main.class=sbt.ScriptMain \
     -Dsbt.boot.directory=~/.sbt/boot \
     -Dsbt.log.noformat=true \
     -jar \$sbtlaunch "\$@"
EOF
sudo mv scalas /opt/bin/scalas
sudo chmod +x /opt/bin/scalas
