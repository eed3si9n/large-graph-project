def commonBuildSettings: Seq[Def.Setting[_]] = Seq(
  organization := "com.example.large",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.10.3",
  resolvers ++= Seq(
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
    "Concurrent Maven Repo" at "http://conjars.org/repo",
    "Clojars Repository" at "http://clojars.org/repo",
    "Twitter Maven" at "http://maven.twttr.com"
  )
)

// lazy val app = (project in file("app")).
//   settings(commonBuildSettings: _*).
//   dependsOn(mod1, mod2, mod3)

// def modularProject(id: String) = Project(id, file(id)).
//   settings(commonBuildSettings: _*).
//   settings(
//     libraryDependencies := Nil
//   ).
//   dependsOn(common) 

// lazy val mod1 = modularProject("mod1")
// lazy val mod2 = modularProject("mod2")
// lazy val mod3 = modularProject("mod3")

lazy val common = (project in file("common")).
  settings(commonBuildSettings: _*).
  settings(
  ).
  dependsOn(util1, util2, util3, biz1, biz2, biz3,
    lib1, lib2, lib3,
    lib4, lib5, lib6,
    lib7, lib8, lib9,
    wrench1, wrench2)

val sprayV = "1.1.1"
val playVersion = "2.2.0"
val summingbirdVersion = "0.4.0"

lazy val biz1 = (project in file("biz1")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "0.9.0-incubating",
      "org.apache.mesos" % "mesos" % "0.9.0-incubating",
      "org.apache.hadoop" % "hadoop-core" % "1.2.0",
      "org.apache.avro" % "avro" % "1.7.6",
      "org.apache.zookeeper" % "zookeeper" % "3.4.2",
      "org.drools" % "drools-spring" % "6.0.0.Beta2",
      // finagle
      "com.twitter" %% "finagle-http" % "6.2.0",
      // Dispatch
      "net.databinder.dispatch" %% "dispatch-core" % "0.10.0",
      // spray
      "io.spray"            %   "spray-servlet" % sprayV,
      "io.spray"            %   "spray-routing" % sprayV,
      "org.eclipse.jetty"   %   "jetty-webapp"  % "9.1.0.v20131115",
      "org.eclipse.jetty"   %   "jetty-plus"    % "9.1.0.v20131115",
      "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016",
      // cascading
      "cascading" % "cascading-core" % "2.5.1",
      "cascading" % "cascading-local" % "2.5.1",
      "cascading" % "cascading-hadoop" % "2.5.1",
      "com.twitter" %% "scalding-core" % "0.9.0rc1",
      "com.twitter" %% "summingbird-core" % summingbirdVersion,
      "com.twitter" %% "summingbird-batch" % summingbirdVersion,
      "com.twitter" %% "summingbird-client" % summingbirdVersion,
      "com.twitter" %% "summingbird-storm" % summingbirdVersion,
      "com.twitter" %% "summingbird-scalding" % summingbirdVersion
    )
  ).
  dependsOn(util2)

lazy val biz2 = (project in file("biz2")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "13.0.1",
      "javax.servlet" % "javax.servlet-api" % "3.0.1",
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.apache.avro" % "avro" % "1.7.4",
      "org.apache.zookeeper" % "zookeeper" % "3.3.6" excludeAll (
        ExclusionRule(organization = "com.sun.jdmk"),
        ExclusionRule(organization = "com.sun.jmx"),
        ExclusionRule(organization = "javax.jms")
      ),
      // Dispatch
      "net.databinder.dispatch" %% "dispatch-core" % "0.10.0"
    )
  ).
  dependsOn(util2)

lazy val biz3 = (project in file("biz3")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "10.0",
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.apache.avro" % "avro" % "1.4.0",
      "org.apache.zookeeper" % "zookeeper" % "3.3.1" excludeAll (
        ExclusionRule(organization = "com.sun.jdmk"),
        ExclusionRule(organization = "com.sun.jmx"),
        ExclusionRule(organization = "javax.jms")
      )
    )
  ).
  dependsOn(util2)

def playLibrary(id: String) = Project(id, file(id)).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "14.0.1",
      "commons-collections" % "commons-collections" % "3.0",
      "org.slf4j" % "slf4j-api" % "1.7.0",
      // Play
      "com.typesafe.play" %% "play" % playVersion,
      "com.typesafe.slick" %% "slick" % "1.0.1",
      "javax.servlet" % "javax.servlet-api" % "3.0.1",
      "org.slf4j" % "slf4j-api" % "1.7.5"
    )
  ).
  dependsOn(biz3)

lazy val lib1 = playLibrary("lib1")
lazy val lib2 = playLibrary("lib2")
lazy val lib3 = playLibrary("lib3")

val luceneVersion = "4.0.0"
def luceneLibrary(id: String) = Project(id, file(id)).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "13.0.1",
      "commons-collections" % "commons-collections" % "3.0",
      "org.slf4j" % "slf4j-api" % "1.7.0",
      // Lucene
      "org.apache.lucene" % "lucene-queries" % luceneVersion,
      "org.apache.lucene" % "lucene-analyzers-common" % luceneVersion,
      "org.apache.lucene" % "lucene-queryparser" % luceneVersion,
      "org.apache.solr" % "solr" % luceneVersion excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "org.apache.solr" % "solr-solrj" % luceneVersion excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "org.apache.solr" % "solr-core" % luceneVersion excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "org.apache.commons" % "commons-compress" % "1.2"
    )
  ).
  dependsOn(util2)

lazy val lib4 = luceneLibrary("lib4")
lazy val lib5 = luceneLibrary("lib5")
lazy val lib6 = luceneLibrary("lib6")

val akkaVersion = "2.3.1"
def akkaLibrary(id: String) = Project(id, file(id)).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "13.0.1",
      "commons-collections" % "commons-collections" % "3.0",
      "org.slf4j" % "slf4j-api" % "1.7.0",
      // Akka
      "com.typesafe.akka" %% "akka-actor"   % akkaVersion,
      "com.typesafe.akka" %% "akka-kernel"  % akkaVersion,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j"   % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote"  % akkaVersion
    )
  ).
  dependsOn(biz1)

lazy val lib7 = akkaLibrary("lib7")
lazy val lib8 = akkaLibrary("lib8")
lazy val lib9 = akkaLibrary("lib9")

lazy val util1 = (project in file("util1")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "2.3.11" % "test",
      // Scalaz
      "org.scalaz"        %% "scalaz-effect" % "7.0.6",
      "commons-codec" % "commons-codec" % "1.4",
      "org.apache.httpcomponents" % "httpclient" % "4.1.1",
      "org.apache.httpcomponents" % "httpcore" % "4.1",
      "jaxen" % "jaxen" % "1.0-FCS"
    )
  )

lazy val util2 = (project in file("util2")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.codehaus.jackson" % "jackson-mapper-asl" % "1.4.2",
      "commons-lang" % "commons-lang" % "2.6",
      "commons-io" % "commons-io" % "2.2",
      "javax.activation" % "activation" % "1.1",
      "javax.servlet" % "javax.servlet-api" % "3.0.1",
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.json4s" %% "json4s-native" % "3.2.6",
      "joda-time" % "joda-time" % "2.0"
    )
  )

lazy val util3 = (project in file("util3")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.5",
      "commons-lang" % "commons-lang" % "2.5",
      "commons-io" % "commons-io" % "1.4",
      "org.slf4j" % "slf4j-api" % "1.6.2"
    )
  )

lazy val util4 = (project in file("util4")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "commons-lang" % "commons-lang" % "2.4",
      "org.slf4j" % "slf4j-api" % "1.5.6"
    )
  )

val someOtherAkkaVersion = "2.1.4"
val someOtherAkkaVersion2 = "2.3.2"
val someOtherLucene = "4.2.0"
val someOtherLucene2 = "4.5.0"
val someOtherPlayVerion = "2.2.2"
val someOtherSumminngbirdVersion = "0.4.2"
val scalaLoggingVersion = "2.1.2"

lazy val wrench1 = (project in file("wrench1")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % someOtherAkkaVersion,
      "org.apache.solr" % "solr" % someOtherLucene excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "org.apache.solr" % "solr-solrj" % someOtherLucene excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "org.apache.solr" % "solr-core" % someOtherLucene excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "com.typesafe.play" %% "play" % someOtherPlayVerion,
      "com.google.guava" % "guava" % "15.0",
      "com.twitter" %% "summingbird-scalding" % someOtherSumminngbirdVersion,
      "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
      "commons-codec" % "commons-codec" % "1.8",
      "commons-collections" % "commons-collections" % "3.2",
      "io.netty" % "netty" % "3.9.0.Final",
      "com.typesafe.slick" %% "slick" % "2.0.1",
      "com.typesafe.scala-logging" %% "scala-logging-slf4j" % scalaLoggingVersion
    )
  )

lazy val wrench2 = (project in file("wrench2")).
  settings(commonBuildSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % someOtherAkkaVersion2,
      "org.apache.solr" % "solr-core" % someOtherLucene2 excludeAll (
        ExclusionRule(organization = "org.restlet.jee")
      ),
      "com.google.guava" % "guava" % "16.0",
      "commons-codec" % "commons-codec" % "1.9",
      "commons-collections" % "commons-collections" % "3.2.1"
    )
  )
