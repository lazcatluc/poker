# Product Development at the mountains: Poker application

The purpose of product development is to create a functional software product during 2 days of coding starting from (almost) nothing. We will work in an agile fashion using 1 hour sprints followed by 10 minutes of retrospective. After each sprint we will have some incremental functionality which we will deploy live. The team is cross-functional, made from programmers, testers, analysts, product owners and anyone else who wants to join us. 

During this edition the focus will be on tests and test driven development. 

# GIT procedure

1. Fork this repository on Github. 
2. A master and a develop branch have been set-up. We will use the develop branch for any new code.
3. Once you've added something of value, push to your own forked repository and do a pull request.
4. Once the code is on the develop branch, an automatic Jenkins job starts building, running unit tests, deploying to the staging JBoss Wildfly instance and running JMeter integration tests. The staged app will be available at http://contezi.ro:9081/poker
5. Once all is well on staging, we will merge develop into master. Another automatic Jenkins build starts, similar to the staging one, but deploying on production: http://contezi.ro/poker

# Kanban board

Please register with kanbanflow.com in order to access our very own Kanban board: https://kanbanflow.com/board/4cde4ef382fa44314a8ba60d4c644c65
