.RECIPEPREFIX +=
.DEFAULT_GOAL := clean

version-check:
  mvn org.codehaus.mojo:versions-maven-plugin:2.7:display-dependency-updates
