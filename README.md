LESS Maven
============

LESS Maven is a Maven 2 Plugin based on [lesscss-engine]http://github.com/asual/lesscss-engine (thanks for asual work)

Usage
-----

The plugin search for:

    ${lesscss.dir.in}/*.less

and compile them to:

    ${lesscss.dir.out}/*.less.css

Default values are:

    lesscss.dir.in=src/main/webapp/css/
    lesscss.dir.out=src/main/webapp/css/

In order to compile less files run the following:

    mvn com.jcheype:LessCssMaven:1.0-SNAPSHOT:lesscss    