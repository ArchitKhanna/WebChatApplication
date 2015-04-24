# WebChatApplication

Repository for studying practise in Belarussian State University.

## How to get this project in Intelij IDEA

1. Go to File->New->Project from exiting sources.
2. Then choose Import project from external model->Maven.
3. Next choose some settings and finish importing the project.

## How to add local Tomcat server in Intelij IDEA

1. Go to Run->Edit Configurations.
2. Add New Configuration->Tomcat Server->Local.
3. Go to Run->Edit Configurations->Deployment.
4. Add artifact and change application context(or war files decompressed into ROOT folder in Tomcat->webapps.

## Current state of project

* **Task 10** :
The xml history is stored in your home directory. Please, before running server, put file history.xml to your home directory (to know your home directory open command promt and paste `echo %USERPROFILE%`).
<br />For writing history to xml used DOM parser, for parsing from xml used STAX parser.