# VMware vFabric tc Server Integration for Eclipse
      
  The VMware vFabric tc Server Integration for Eclipse adds server adaptors for the VMware
  vFabric tc Server to the Eclipse JEE tooling. It makes it easy to deploy apps, start, debug
  and stop your tc Server, create new instances with various configurations, all from within Eclipse.

  It comes with Spring UAA (User Agent Analysis), an optional component that help us to
  collect some usage data. This is completely anonymous and helps us to understand better how
  the tooling is used and how to improve it in the future.

  It also comes with the SpringSource Dashboard is an optional component, which brings you
  up-to-date information about SpringSource-related projects as well as an easy-to-use extension
  install to get additional tooling add-ons, like the famous Spring IDE or the Cloud Foundry
  Integration for Eclipse.

## Installation (Release)

  You can install the latest release of the vFabric tc Server Integration for Eclipse from the
  Eclipse Marketplace by looking for "tc Server". You can also install it manually from this update site:

  http://dist.springsource.com/release/TOOLS/eclipse-integration-tcserver/

## Installation (Milestone)

  You can install the latest milestone build of the vFabric tc Server Integration for Eclipse
  manually from this udpate site:

  http://dist.springsource.com/milestone/TOOLS/eclipse-integration-tcserver/

## Installation (CI builds)

  If you want to live on the leading egde, you can also install always up-to-date continuous
  integration buids from this update site:

  http://dist.springsource.com/snapshot/TOOLS/eclipse-integration-tcserver/nightly

  But take care, those builds could be broken from time to time and might contain non-ship-ready
  features that might never appear in the milestone or release builds.

## Questions and bug reports:

  If you have a question that Google can't answer, the best way is to go to the forum:

  http://forum.springsource.org/forumdisplay.php?32-SpringSource-Tool-Suite

  There you can also ask questions and search for other people with related or similar problems
  (and solutions). New versions of the vFabric tc Server Integration for Eclipse (and other
  tooling that is brought to you by SpringSource) are announced there as well.

  With regards to bug reports, please go to:

  https://issuetracker.springsource.com/browse/STS

  and choose the SERVER component when filing new issues.

## Developing VMware vFabric tc Server Integration for Eclipse

  Just clone the repo and import the projects into an Eclipse workspace. The easiest way to ensure
  that your target platform contains all the necessary dependencies, install a CI build into
  your target platform and proceed.

## Building VMware vFabric tc Server Integration for Eclipse
  
  The VMware vFabric tc Server Integration for Eclipse project uses Maven Tycho to do continuous integration
  builds and to produce p2 repos and update sites. To build the project yourself, you can execute:

  `mvn -Pe37 -Dmaven.test.skip=true clean install`

## Contributing

  Here are some ways for you to get involved in the community:

  * Get involved with the Spring community on the Spring Community Forums.  Please help out on the [forum](http://forum.springsource.org/forumdisplay.php?32-SpringSource-Tool-Suite) by responding to questions and joining the debate.
  * Create [JIRA](https://issuetracker.springsource.com/browse/STS) tickets for bugs and new features and comment and vote on the ones that you are interested in.  
  * Github is for social coding: if you want to write code, we encourage contributions through pull requests from [forks of this repository](http://help.github.com/forking/). If you want to contribute code this way, please reference a JIRA ticket as well covering the specific issue you are addressing.
  * Watch for upcoming articles on Spring by [subscribing](http://www.springsource.org/node/feed) to springframework.org

Before we accept a non-trivial patch or pull request we will need you to sign the [contributor's agreement](https://support.springsource.com/spring_committer_signup). Signing the contributor's agreement does not grant anyone commit rights to the main repository, but it does mean that we can accept your contributions, and you will get an author credit if we do. Active contributors might be asked to join the core team, and given the ability to merge pull requests.
